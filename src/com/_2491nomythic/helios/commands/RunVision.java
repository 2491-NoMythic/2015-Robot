package com._2491nomythic.helios.commands;

import com._2491nomythic.helios.settings.VisionConstants;
import com._2491nomythic.helios.subsystems.Camera;
import com._2491nomythic.util.TargetReport;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.MeasurementType;

import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
	
public class RunVision extends Command {
    public RunVision() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	TargetReport target = new TargetReport();
    	int verticalTargets[] = new int[VisionConstants.MAX_PARTICLES];
    	int horizontalTargets[] = new int[VisionConstants.MAX_PARTICLES];
    	int verticalTargetCount, horizontalTargetCount;
    	NIVision.ParticleFilterCriteria2 criteria = new NIVision.ParticleFilterCriteria2(MeasurementType.MT_AREA, VisionConstants.AREA_MINIMUM, 65535, calibrated, exclude)
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
    		ColorImage image;
    		image = new RGBImage("/testImage.jpg");
    		BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183);
    		BinaryImage filteredImage = thresholdImage.particleFilter(criteria)
    		
    	} catch(NIVisionException ex) {
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
}
