package com._2491nomythic.helios.commands;

import com._2491nomythic.helios.settings.VisionConstants;
import com._2491nomythic.helios.subsystems.Camera;
import com._2491nomythic.util.Scores;
import com._2491nomythic.util.TargetReport;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;

import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class RunVision extends Command {
	TargetReport target;
	int verticalTargets[] = new int[VisionConstants.MAX_PARTICLES];
	int horizontalTargets[] = new int[VisionConstants.MAX_PARTICLES];
	int verticalTargetCount, horizontalTargetCount;
	ParticleFilterCriteria2[] criteria = new ParticleFilterCriteria2[1];

	public RunVision() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		target = new TargetReport();
		criteria[0] = new ParticleFilterCriteria2(MeasurementType.MT_AREA, VisionConstants.AREA_MINIMUM, 65535, 1, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		try {
			ColorImage image;
			image = new RGBImage("/testImage.jpg");
			BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183);
			BinaryImage filteredImage = thresholdImage.particleFilter(criteria);

			// iterate through each particle and score to see if it is a target
			Scores scores[] = new Scores[filteredImage.getNumberParticles()];
			horizontalTargetCount = 0;
			verticalTargetCount = 0;

			if (filteredImage.getNumberParticles() > 0) {
				for (int i = 0; i < VisionConstants.MAX_PARTICLES && i < filteredImage.getNumberParticles(); i++) {
					ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
					scores[i] = new Scores();

					// Score each particle on rectangularity and aspect ratio
					scores[i].rectangularity = scoreRectangularity(report);
					scores[i].aspectRatioVertical = scoreAspectRatio(filteredImage, report, i, true);
					scores[i].aspectRatioHorizontal = scoreAspectRatio(filteredImage, report, i, false);

					// Check if the particle is a horizontal target, if not,
					// check if it's a vertical target
					if (scoreCompare(scores[i], false)) {
						System.out.println("particle: " + i + "is a Horizontal Target centerX: " + report.center_mass_x
								+ "centerY: " + report.center_mass_y);
						horizontalTargets[horizontalTargetCount++] = i; // Add
																		// particle
																		// to
																		// target
																		// array
																		// and
																		// increment
																		// count
					} else if (scoreCompare(scores[i], true)) {
						System.out.println("particle: " + i + "is a Vertical Target centerX: " + report.center_mass_x
								+ "centerY: " + report.center_mass_y);
						verticalTargets[verticalTargetCount++] = i; // Add
																	// particle
																	// to target
																	// array and
																	// increment
																	// count
					} else {
						System.out.println("particle: " + i + "is not a Target centerX: " + report.center_mass_x
								+ "centerY: " + report.center_mass_y);
					}
					System.out.println(
							"rect: " + scores[i].rectangularity + "ARHoriz: " + scores[i].aspectRatioHorizontal);
					System.out.println("ARVert: " + scores[i].aspectRatioVertical);
				}

				// Zero out scores and set verticalIndex to first target in case
				// there are no horizontal targets
				target.totalScore = target.leftScore = target.rightScore = target.tapeWidthScore = target.verticalScore = 0;
				target.verticalIndex = verticalTargets[0];
				for (int i = 0; i < verticalTargetCount; i++) {
					ParticleAnalysisReport verticalReport = filteredImage.getParticleAnalysisReport(verticalTargets[i]);
					for (int j = 0; j < horizontalTargetCount; j++) {
						ParticleAnalysisReport horizontalReport = filteredImage
								.getParticleAnalysisReport(horizontalTargets[j]);
						double horizWidth, horizHeight, vertWidth, leftScore, rightScore, tapeWidthScore, verticalScore,
								total;

						// Measure equivalent rectangle sides for use in score
						// calculation
						horizWidth = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false,
								MeasurementType.MT_EQUIVALENT_RECT_LONG_SIDE);
						vertWidth = NIVision.MeasureParticle(filteredImage.image, verticalTargets[i], false,
								MeasurementType.MT_EQUIVALENT_RECT_SHORT_SIDE);
						horizHeight = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false,
								MeasurementType.MT_EQUIVALENT_RECT_SHORT_SIDE);

						// Determine if the horizontal target is in the expected
						// location to the left of the vertical target
						leftScore = ratioToScore(
								1.2 * (verticalReport.boundingRectLeft - horizontalReport.center_mass_x) / horizWidth);
						// Determine if the horizontal target is in the expected
						// location to the right of the vertical target
						rightScore = ratioToScore(1.2 * (horizontalReport.center_mass_x
								- verticalReport.boundingRectLeft - verticalReport.boundingRectWidth) / horizWidth);
						// Determine if the width of the tape on the two targets
						// appears to be the same
						tapeWidthScore = ratioToScore(vertWidth / horizHeight);
						// Determine if the vertical location of the horizontal
						// target appears to be correct
						verticalScore = ratioToScore(
								1 - (verticalReport.boundingRectTop - horizontalReport.center_mass_y)
										/ (4 * horizHeight));
						total = leftScore > rightScore ? leftScore : rightScore;
						total += tapeWidthScore + verticalScore;

						// If the target is the best detected so far store the
						// information about it
						if (total > target.totalScore) {
							target.horizontalIndex = horizontalTargets[j];
							target.verticalIndex = verticalTargets[i];
							target.totalScore = total;
							target.leftScore = leftScore;
							target.rightScore = rightScore;
							target.tapeWidthScore = tapeWidthScore;
							target.verticalScore = verticalScore;
						}
					}
					// Determine if the best target is a Hot target
					target.Hot = hotOrNot(target);
				}

				if (verticalTargetCount > 0) {
					// Information about the target is contained in the "target"
					// structure
					// To get measurement information such as sizes or locations
					// use the
					// horizontal or vertical index to get the particle report
					// as shown below
					ParticleAnalysisReport distanceReport = filteredImage
							.getParticleAnalysisReport(target.verticalIndex);
					double distance = computeDistance(filteredImage, distanceReport, target.verticalIndex);
					if (target.Hot) {
						System.out.println("Hot target located");
						System.out.println("Distance: " + distance);
					} else {
						System.out.println("No hot target present");
						System.out.println("Distance: " + distance);
					}
				}

			}
		} catch (NIVisionException ex) {
			ex.printStackTrace();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber,
			boolean vertical) throws NIVisionException {
		double rectLong, rectShort, aspectRatio, idealAspectRatio;

		rectLong = NIVision.MeasureParticle(image.image, particleNumber, false,
				MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
		rectShort = NIVision.MeasureParticle(image.image, particleNumber, false,
				MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
		idealAspectRatio = vertical ? (4.0 / 32) : (23.5 / 4); // Vertical
																// reflector 4"
																// wide x 32"
																// tall,
																// horizontal
																// 23.5" wide x
																// 4" tall

		// Divide width by height to measure aspect ratio
		if (report.boundingRectWidth > report.boundingRectHeight) {
			// particle is wider than it is tall, divide long by short
			aspectRatio = ratioToScore((rectLong / rectShort) / idealAspectRatio);
		} else {
			// particle is taller than it is wide, divide short by long
			aspectRatio = ratioToScore((rectShort / rectLong) / idealAspectRatio);
		}
		return aspectRatio;
	}

	/**
	 * Compares scores to defined limits and returns true if the particle
	 * appears to be a target
	 * 
	 * @param scores
	 *            The structure containing the scores to compare
	 * @param outer
	 *            True if the particle should be treated as an outer target,
	 *            false to treat it as a center target
	 * 
	 * @return True if the particle meets all limits, false otherwise
	 */
	boolean scoreCompare(Scores scores, boolean vertical) {
		boolean isTarget = true;

		isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
		if (vertical) {
			isTarget &= scores.aspectRatioVertical > ASPECT_RATIO_LIMIT;
		} else {
			isTarget &= scores.aspectRatioHorizontal > ASPECT_RATIO_LIMIT;
		}

		return isTarget;
	}

	/**
	 * Computes a score (0-100) estimating how rectangular the particle is by
	 * comparing the area of the particle to the area of the bounding box
	 * surrounding it. A perfect rectangle would cover the entire bounding box.
	 * 
	 * @param report
	 *            The Particle Analysis Report for the particle to score
	 * @return The rectangularity score (0-100)
	 */
	double scoreRectangularity(ParticleAnalysisReport report) {
		if (report.boundingRectWidth * report.boundingRectHeight != 0) {
			return 100 * report.particleArea / (report.boundingRectWidth * report.boundingRectHeight);
		} else {
			return 0;
		}
	}

	/**
	 * Converts a ratio with ideal value of 1 to a score. The resulting function
	 * is piecewise linear going from (0,0) to (1,100) to (2,0) and is 0 for all
	 * inputs outside the range 0-2
	 */
	double ratioToScore(double ratio) {
		return (Math.max(0, Math.min(100 * (1 - Math.abs(1 - ratio)), 100)));
	}

	/**
	 * Takes in a report on a target and compares the scores to the defined
	 * score limits to evaluate if the target is a hot target or not.
	 * 
	 * Returns True if the target is hot. False if it is not.
	 */
	boolean hotOrNot(TargetReport target) {
		boolean isHot = true;

		isHot &= target.tapeWidthScore >= TAPE_WIDTH_LIMIT;
		isHot &= target.verticalScore >= VERTICAL_SCORE_LIMIT;
		isHot &= (target.leftScore > LR_SCORE_LIMIT) | (target.rightScore > LR_SCORE_LIMIT);

		return isHot;
	}
}
