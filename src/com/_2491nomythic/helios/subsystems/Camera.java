package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.RunCamera;
import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.ShapeMode;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
	
	private int session;
	private Image frame;
	//private NIVision.Rect rect;
	private boolean cameraFound = true;
	private static Camera instance;
	
	public static Camera getInstance() {
		if (instance == null) {
			instance = new Camera();
		}
		return instance;
	}
	
	private Camera() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		try {
			session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		}
		catch (VisionException e) {
			System.out.println(e.getMessage());
			cameraFound = false;
		}
		if (cameraFound) {
			NIVision.IMAQdxConfigureGrab(session);
		}
	}
	
	public void startImageAcquisition() {
		if (cameraFound) {
			NIVision.IMAQdxStartAcquisition(session);
			//rect = new NIVision.Rect(10, 10, 100, 100);
		}
	}
	
	public void stopImageAcquisition() {
		if (cameraFound) {
			NIVision.IMAQdxStopAcquisition(session);
		}
	}
	
	public void updateDriverstationImage() {
		if (cameraFound) {
			NIVision.IMAQdxGrab(session, frame, 1);
//			NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
			CameraServer.getInstance().setImage(frame);
		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new RunCamera());
	}
}