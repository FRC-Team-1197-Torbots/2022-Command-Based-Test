package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants.OperatorConstants;

public class Drivetrain extends SubsystemBase{

  private ADXRS450_Gyro gyro;

  private final CANSparkMax Right1;	
	private final CANSparkMax Right2;
	private final CANSparkMax RightFlipped;
	
	private final CANSparkMax Left1;
	private final CANSparkMax Left2;
	private final CANSparkMax LeftFlipped;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	private double leftSpeed;
	private double rightSpeed;

  private double currentVoltage;

  private double heading = 0.0;
  

    public Drivetrain() {
      gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		
      //solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  
      Left1 = new CANSparkMax(1, MotorType.kBrushless);
      Left2 = new CANSparkMax(2, MotorType.kBrushless);
      LeftFlipped = new CANSparkMax(3, MotorType.kBrushless); 
  
      RightFlipped = new CANSparkMax(13, MotorType.kBrushless); 
      Right1 = new CANSparkMax(14, MotorType.kBrushless);		
      Right2 = new CANSparkMax(15, MotorType.kBrushless);
  
      leftEncoder = new Encoder(10, 11, false, Encoder.EncodingType.k4X);
      rightEncoder = new Encoder(16, 17, false, Encoder.EncodingType.k4X);
      
      //leftMaster.setInverted(false); // Left master must be attached to the farthest CIM from the output shaft
      //leftSlave1.setInverted(false); 
      //leftSlave2.setInverted(true);
      
      // Right1.setInverted(false); // Right master must be attached to the farthest CIM from the output shaft
      // RightFlipped.setInverted(false);
      // Right2.setInverted(false); 
  
      //leftSlave1.follow(leftMaster);
      //leftSlave2.follow(leftMaster);
      //rightSlave1.follow(rightMaster);
      // rightSlave2.follow(rightMaster);
      resetEncoder();
      resetGyro();
    }
  
    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
      SmartDashboard.putNumber("left raw", getLeftEncoder());
      SmartDashboard.putNumber("right raw", getRightEncoder());
      SetLeft(-leftSpeed); //- for auto
      SetRight(-rightSpeed); //+ for teleop
    }
  
    // Setting the left master Talon's speed to the given parameter
    public void SetLeft(double speed) {
      Left1.set(-speed);
      Left2.set(-speed);
      LeftFlipped.set(speed);
    }
  
    // Setting the right master Talon's speed to the given parameter
    public void SetRight(double speed) {
      Right1.set(speed); //in correct setting, but "software fix"		
      Right2.set(speed);
      RightFlipped.set(-speed);
    }
  
    // Getting raw position value from the right encoder
    public double getRightEncoder() {
      return rightEncoder.getRaw();
    }
  
    public double getRightVelocity() {
      return rightEncoder.getRate();
    }
  
    public double getLeftVelocity() {
      return -leftEncoder.getRate();
    }
  
    // Getting raw position value from the left encoder
    public double getLeftEncoder() {
      return -leftEncoder.getRaw();
    }
  
    // Getting the average encoder position from both encoders
    public double getAverageEncoderPosition() {
      return (getLeftEncoder() + getRightEncoder()) * 0.5;
    }
  
    // Getting the position from both encoders in feet
    public double getPosition() {
      return getAverageEncoderPosition() / OperatorConstants.encoderTicksPerFoot;
      //return ((getRightEncoder() + getLeftEncoder() * 0.5)) / encoderTicksPerFoot; // [feet]
    }
  
    // Getting the angle in radians from the spartan board
    public double getHeading() {
      heading = (gyro.getAngle() * (Math.PI / 180));
      return heading; // [radians]
    }
  
    // Method to set the the linear and angular speed of the robot
    public void setTargets(double percentV, double percentOmega) {
      leftSpeed = percentV - percentOmega;
      rightSpeed = percentV + percentOmega;
      
      Right1.set(rightSpeed);
      RightFlipped.set(-rightSpeed);
      //leftMaster.set(leftSpeed);
      //leftSlave1.set(leftSpeed);
    }
  
    // Method to reset the encoder values
    public void resetEncoder() {
      leftEncoder.reset();
      rightEncoder.reset();
    }
  
    // Method to reset the spartan board gyro values
    public void resetGyro() {
      gyro.reset(); 
    }
    
    // Method to shift the drive to low gear
    public void shiftToLowGear() {
      //solenoid.set(true);
    }
    
    // Method to shift the drive to high gear
    public void shiftToHighGear() {
      //solenoid.set(false);
    }
    
    // Method to initialize 
    public void init(){
      if(gyro == null){ 
        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
      }
      gyro.calibrate();
      // resetEncoder();
    }
    
    public double getAbsoluteMaxVelocity() {
      return OperatorConstants.absoluteMaxVelocity;
    }
    
    public double getAbsoluteMaxAcceleration() {
      return OperatorConstants.absoluteMaxAcceleration;
    }
    
    public double getAbsoluteMaxOmega() {
      return OperatorConstants.absoluteMaxOmega;
    }
    
    public double getAbsoluteMaxAlpha() {
      return OperatorConstants.absoluteMaxAlpha;
    }
    
    public double getCurrentVoltage() {
      currentVoltage = RobotController.getInputVoltage();
      return currentVoltage;
    }
  
    public void SetBrake() {
      Right1.setIdleMode(IdleMode.kBrake);
      Right2.setIdleMode(IdleMode.kBrake);
      RightFlipped.setIdleMode(IdleMode.kBrake);
      Left1.setIdleMode(IdleMode.kBrake);
      Left2.setIdleMode(IdleMode.kBrake);
      LeftFlipped.setIdleMode(IdleMode.kBrake);
    }
  
    public void SetCoast() {
      Right1.setIdleMode(IdleMode.kCoast);
      Right2.setIdleMode(IdleMode.kCoast);
      RightFlipped.setIdleMode(IdleMode.kCoast);
      Left1.setIdleMode(IdleMode.kCoast);
      Left2.setIdleMode(IdleMode.kCoast);
      LeftFlipped.setIdleMode(IdleMode.kCoast);
    }


}
