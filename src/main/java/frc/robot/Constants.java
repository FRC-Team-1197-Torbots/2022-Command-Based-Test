// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DriveTrainConstants {
    /***********************
     *        CAN IDs      *
     ***********************/
    public static final int Left1ID = 1;
    public static final int Left2ID = 2;
    public static final int LeftFlippedID = 3;

    public static final int Right1ID = 13;
    public static final int Right2ID = 14;
    public static final int RightFlippedID = 15;
  } 

  public static class IntakeConstants{
    /***********************
     *      Intake IDs     *
     *                     *
     ***********************/
    public static final int IntakeID = 4;
    public static final int RollerID = 11;

    public static final double UP_TARGET = 0;
    public static final double DOWN_TARGET = 500;

    public static final double IntakekP = 0.001;//0.00075;
    public static final double IntakekI = 0;
    public static final double IntakekD = 0;//0.0000062;
  }

    public static final int kDriverControllerPort = 0;
    public static final int kMechControllerPort = 1;

    public static final double encoderTicksPerFoot = 7416;//7827;//7230;//7057//5460.0; //push the robot forward one foot and take the average of the two encoder distances
    public static final double absoluteMaxVelocity = 0.0; //use encoder ticks per foot, and using the robot, set it to the max speed on both wheels and see how many encoder ticks it goes forward
    //then using the encoder ticks per foot calculation, calculate its absolute Max Velocity [Units: Feet/Second]
    public static final double absoluteMaxAcceleration = 0.0;//[Units:(delta feet/seconds)/seconds
    //just see how many seconds it takes to go from 0 to 100% speed and divide the absolute max velocity by that number of seconds
    public static final double absoluteMaxOmega = 0.0;//use the gyro and set one motor to 100% and the other to -100% [Units: Radians/Second]
    public static final double absoluteMaxAlpha = 0.0;

    /********************************
     *     TELEOP DRIVE CONSTANTS   *
     ********************************/
  public static class TeleopDriveConstants {
    public static final double STEER_SCALAR = 1.15;
    public static final double POSRANGE_MAX_ACCEL = 0.02;//0.03;//0.08;
    public static final double NEGRANGE_MAX_ACCEL = 0.03;//0.02;//0.03;
    public static final double MAX_DECEL = 0.03;//0.04;//0.06;
    public static final double MAX_VELOCITY = 50000f;//45000f; //28000 //39000 // 34,000 // 39,000

    public static final double velocitykP = 0.0000125;// velocity stuff probably not needed at all and should keep 0
    public static final double velocitykI = 0.0;
    public static final double velocitykD = 0;//0.0000008;

    public static final double TIME_INTERVAL = 0.005f;
    public static final double UP_TARGET = 0;
    public static final double DOWN_TARGET = 500;
  }
}

