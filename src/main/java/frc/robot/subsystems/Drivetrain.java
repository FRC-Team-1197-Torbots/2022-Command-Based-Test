// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax Right1;	
	private final CANSparkMax Right2;
	private final CANSparkMax RightFlipped;
	
	private final CANSparkMax Left1;
	private final CANSparkMax Left2;
	private final CANSparkMax LeftFlipped;

  public static Encoder leftEncoder;
  public static Encoder rightEncoder;

  //private final ADXRS450_Gyro gyro;
  private final AHRS NavX;
  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    //gyro = new ADXRS450_Gyro();
    NavX = new AHRS(SPI.Port.kMXP);

    Left1 = new CANSparkMax(DriveTrainConstants.Left1ID, MotorType.kBrushless);
		Left2 = new CANSparkMax(DriveTrainConstants.Left2ID, MotorType.kBrushless);
		LeftFlipped = new CANSparkMax(DriveTrainConstants.LeftFlippedID, MotorType.kBrushless); 

		RightFlipped = new CANSparkMax(DriveTrainConstants.Right1ID, MotorType.kBrushless); 
		Right1 = new CANSparkMax(DriveTrainConstants.Right2ID, MotorType.kBrushless);		
		Right2 = new CANSparkMax(DriveTrainConstants.RightFlippedID, MotorType.kBrushless);

    leftEncoder = new Encoder(10, 11, false, Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(16, 17, false, Encoder.EncodingType.k4X);

    resetEncoder();
    resetGyro();
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }

  // Method to reset the encoder values
	public void resetEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	// Method to reset the spartan board gyro values
	public void resetGyro() {
		NavX.reset();
	}

  public void setMotorSpeeds(double leftSpeed, double rightSpeed){
    Left1.set(-leftSpeed);
		Left2.set(-leftSpeed);
		LeftFlipped.set(leftSpeed);
    Right1.set(rightSpeed); //in correct setting, but "software fix"		
		Right2.set(rightSpeed);
		RightFlipped.set(-rightSpeed);
  }
}
