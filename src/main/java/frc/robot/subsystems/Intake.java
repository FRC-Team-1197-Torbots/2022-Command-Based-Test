package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.TORDerivative;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase{
    private CANSparkMax intake;
    private CANSparkMax roller;
    private Encoder intakeEncoder;

    private double rollerin = -0.8f; 
    private double rollerout = 0.5f;

    private double pidDerivativeResult = 0;
    private double pidIntegral = 0;
    private final double kP = 0.001;//0.00075;
    private final double kI = 0;
    private final double kD = 0;//0.0000062;

    public boolean ONTARGET;
    private TORDerivative derivative;

    private double target;

    public enum moveIntake{
        UP, DOWN, OFF;
    }

    public moveIntake intakeState = moveIntake.UP;

    public Intake() {
        intake = new CANSparkMax(4, MotorType.kBrushless);
        roller = new CANSparkMax(11, MotorType.kBrushless);
        
        
        // intakeEncoder = intake.getEncoder(Type.kHallSensor, 42);
        intakeEncoder = new Encoder(14,15, false, Encoder.EncodingType.k4X);
        intakeEncoder.reset();

        derivative = new TORDerivative(OperatorConstants.TIME_INTERVAL);
        derivative.resetValue(0);      
        
        target = OperatorConstants.UP_TARGET;
        //this.controller = drivercontroller;
        ONTARGET = false;
    }

    public double PID(){
        double speed = 0;
        double error = target - intakeEncoder.get();

        pidDerivativeResult = derivative.estimate(error);
        pidIntegral += error;

        if (Math.abs(error) <= 20)
        {
            ONTARGET = true;
        }
        
        if(error < 20) { //magic number we need to calculate
            pidIntegral = 0;
        }

        if(pidIntegral * kI > 0.5) {
            pidIntegral = 0.5 / kI;
        } else if(pidIntegral * kI < -0.5) {
            pidIntegral = -0.5 / kI;
        }

        speed = (error * kP) + (pidIntegral * kI) + (pidDerivativeResult * kD);
        return speed;
    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Intake position", intakeEncoder.get());
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
        updateSmartDashboard();
        double speed = PID();
        intake.set(speed);

        switch(intakeState) {
            case UP:
                roller.set(rollerin / 3);
                target = OperatorConstants.UP_TARGET;
            break;

            case DOWN:
                target = OperatorConstants.DOWN_TARGET;
                if (ONTARGET)
                    roller.set(rollerin);
            break;

            case OFF:
                roller.set(0);
                target = OperatorConstants.UP_TARGET;

        }
      }
    
    
}
