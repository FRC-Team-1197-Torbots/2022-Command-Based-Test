package frc.robot.commands.Drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PIDSubsystem;

public class ArcadeDrive extends CommandBase{
    private double leftOutput;
    private double rightOutput;

    private Drivetrain m_Drivetrain;
    private PIDSubsystem pid;
    private final DoubleSupplier m_throttle, m_steer;

    private double joystickX, joystickY, throttle, steer, previousThrottle, steerinit, throttleinit;

    public enum SIDE {LEFT, RIGHT};

    public ArcadeDrive(Drivetrain driveTrain, DoubleSupplier throttle, DoubleSupplier steer) {
        m_Drivetrain = driveTrain;
        m_throttle = throttle;
        m_steer = steer;

        pid = new PIDSubsystem();
    
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveTrain);
      }

        // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    steerinit = m_steer.getAsDouble();
    System.out.println("Steer:" + steerinit);
    throttleinit = m_throttle.getAsDouble();
    System.out.println("Throttle:" + throttleinit);
    joystickY = MathUtil.applyDeadband(m_throttle.getAsDouble(), 0.05);
    joystickX = MathUtil.applyDeadband(m_steer.getAsDouble(), 0.05);

    throttle = joystickY;
    steer = -joystickX;
    
    // joystickY = (Math.abs(m_throttle.getAsDouble()) > 0.05) ? m_throttle.getAsDouble() : 0;
    // joystickX = (Math.abs(m_turn.getAsDouble()) > 0.05) ? m_turn.getAsDouble() : 0;
    //System.out.println("throttle: " + throttle);
    double sign = Math.signum(throttle);
    throttle = sign * Math.pow(throttle, 2);
    
    //System.out.println(steer);
    sign = Math.signum(steer);
    steer = sign * Math.pow(steer, 2) * OperatorConstants.STEER_SCALAR;

    if(Math.abs(throttle) < 0.025f) {
        throttle = 0;
   }

   if(Math.abs(steer) < 0.025f) {
       steer = 0;
   }

   double rightspeed = 0, leftSpeed = 0;

   if (throttle > previousThrottle) {
       if (previousThrottle> 0)
            throttle = previousThrottle + OperatorConstants.POSRANGE_MAX_ACCEL;
        else{                
            throttle = previousThrottle + OperatorConstants.NEGRANGE_MAX_ACCEL;
            //System.out.println("Throttle " + throttle);
        }
            
    }

    if (throttle < previousThrottle && Math.abs(throttle - previousThrottle) > OperatorConstants.MAX_DECEL) {
        throttle = previousThrottle - OperatorConstants.MAX_DECEL;
    }

    if(throttle > 1) {
        throttle = 1;
    }

    if(throttle < -1) {
        throttle = -1;
    }


    previousThrottle = throttle;
    
    throttle = -throttle;

   if(throttle > 0) {
       if(steer > 0) {
           leftSpeed = throttle - steer;
           rightspeed = Math.max(throttle, steer);
       } else {
           leftSpeed = Math.max(throttle, -steer);
           rightspeed = throttle + steer;
       }
   } else {
       if(steer > 0) {
           leftSpeed = -Math.max(-throttle, steer);
           rightspeed = throttle + steer;
       } else {
           leftSpeed = throttle - steer;
           rightspeed = -Math.max(-throttle, -steer);
       }
   }

   double settingLeftSpeed = leftSpeed, settingRightSpeed = rightspeed;

   //System.out.println("Left speed: " + leftSpeed);
   //System.out.println("Right speed: " + rightspeed);


       leftOutput = pid.PID(m_Drivetrain.getLeftVelocity(), settingLeftSpeed * OperatorConstants.MAX_VELOCITY, SIDE.LEFT);
       rightOutput = pid.PID(m_Drivetrain.getRightVelocity(), settingRightSpeed * OperatorConstants.MAX_VELOCITY, SIDE.RIGHT);

      
        m_Drivetrain.setMotorSpeeds(leftOutput, rightOutput);


    //m_Drivetrain.setMotorSpeeds(throttle, steer);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
