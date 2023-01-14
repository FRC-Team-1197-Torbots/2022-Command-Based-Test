package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.SPI;

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

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });

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
}
