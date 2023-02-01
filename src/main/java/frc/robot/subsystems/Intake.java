package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase{
    private final CANSparkMax intake;
    private final CANSparkMax roller;

    private Encoder intakeEncoder;

    public Intake(){
        intake = new CANSparkMax(IntakeConstants.IntakeID, MotorType.kBrushless);
        roller = new CANSparkMax(IntakeConstants.RollerID, MotorType.kBrushless);

        intakeEncoder = new Encoder(14,15, false, Encoder.EncodingType.k4X);
        intakeEncoder.reset();
    }

    public double getEncoderPos(){
        return intakeEncoder.get();
    }

    public void setRollerSpeed(double speed){
        roller.set(speed);
    }

    public void setIntakeSpeed(double speed){
        intake.set(speed);
    }
    
}
