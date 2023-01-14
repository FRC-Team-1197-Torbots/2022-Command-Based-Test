package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.En
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase{
    public Intake() {
        CANSparkMax intake = new CANSparkMax(4, MotorType.kBrushless);
        CANSparkMax roller = new CANSparkMax(11, MotorType.kBrushless);

        // intakeEncoder = intake.getEncoder(Type.kHallSensor, 42);
        Encoder intakeEncoder = new Encoder(14,15, false, Encoder.EncodingType.k4X);
        intakeEncoder.reset();

        //derivative = new TorDerivative(Robot.TIME_INTERVAL);
        //derivative.resetValue(0);      
        
        //target = UP_TARGET;
        //this.controller = drivercontroller;
        //ONTARGET = false;
    }  
    
    
}
