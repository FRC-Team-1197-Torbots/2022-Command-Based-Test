package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.moveIntake;
import frc.robot.Constants.OperatorConstants;
//import com.revrobotics.CANSparkMax;

public class DropIntake extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake m_intake;
    private moveIntake intakeState;

    public DropIntake(Intake intake){
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize(){
        intakeState = moveIntake.UP;

    }
    @Override
    public void exectue(){
        double speed = m_intake.PID();
        
        //System.out.println(intakeState);
        //SmartDashboard.putNumber("Target value", target);
        //SmartDashboard.putNumber("Speed", speed);

    }
    @Override
    public void end(boolean interrupted){

    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
