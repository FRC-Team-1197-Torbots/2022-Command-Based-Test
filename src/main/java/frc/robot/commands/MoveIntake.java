package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class MoveIntake extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private final Intake intakeSystem;
    private PIDController intakePID;

    private double target;
    private boolean onTarget;
    private double rollerin = -0.8f;
    private double rollerout = 0.5f;

    private double speed;

    public intakeState intakeMode = intakeState.UP;

    public enum intakeState{
        UP, DOWN, OFF;
    }

    public MoveIntake(Intake intakeSystem){
        this.intakeSystem = intakeSystem;
        intakePID = new PIDController(IntakeConstants.IntakekP, IntakeConstants.IntakekI, IntakeConstants.IntakekD);

        addRequirements(intakeSystem);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotContainer.player1.getAButton()){
            switchState(intakeState.DOWN);
            speed = intakePID.calculate(target - intakeSystem.getEncoderPos());
            intakeSystem.setIntakeSpeed(speed);
        }
        else{
            switchState(intakeState.UP);
            speed = intakePID.calculate(target - intakeSystem.getEncoderPos());
            intakeSystem.setIntakeSpeed(speed);
        }
    }

    public void switchState(intakeState state){
        intakeMode = state;
        switch(intakeMode) {
            case UP:
                intakeSystem.setRollerSpeed(rollerin / 3);
                target = IntakeConstants.UP_TARGET;
            break;

            case DOWN:
                target = IntakeConstants.DOWN_TARGET;
                if (onTarget)
                    intakeSystem.setRollerSpeed(rollerin);
            break;

            case OFF:
                intakeSystem.setRollerSpeed(0);
                target = IntakeConstants.UP_TARGET;

        }

    }
    
}
