package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive.ArcadeDrive.SIDE;
import frc.robot.subsystems.TORDerivative;

public class PIDSubsystem extends SubsystemBase{

    private double currentError = 0;
    private TORDerivative pidDerivative;
    private double pidDerivativeResult;
    private double pidIntegral = 0;
    private double LeftPIDSum = 0, RightPIDSum = 0;
    private double dt = 0.005;

    private double kP, kI, kD;
    public PIDSubsystem(){
        kP = OperatorConstants.velocitykP;
        kI = OperatorConstants.velocitykI;
        kD = OperatorConstants.velocitykD;

        pidDerivative = new TORDerivative(dt);
    }

    public double PID(double currentSpeed, double targetSpeed, SIDE side) {
        
        currentError = targetSpeed - currentSpeed;

        // SmartDashboard.putNumber("currentError:", currentError);
        pidDerivativeResult = pidDerivative.estimate(currentError);
        pidIntegral += currentError;

        if(currentError < 20) {
        pidIntegral = 0;
        }

        if(pidIntegral * kI > 0.5) {
        pidIntegral = 0.5 / kI;
        } else if(pidIntegral * kI < -0.5) {
        pidIntegral = -0.5 / kI;
        }

        if(side == SIDE.LEFT) {
            LeftPIDSum = ((currentError * kP) +
            (pidIntegral * kI) +
            (pidDerivativeResult * kD)); //+ FeedForward;
    
            return LeftPIDSum;
        } else if(side == SIDE.RIGHT) {
            RightPIDSum = ((currentError * kP) +
            (pidIntegral * kI) +
            (pidDerivativeResult * kD)); //+ FeedForward;
    
            return RightPIDSum;
        } else {
            return 0;            
        }


   }

    /* 
    private final double velocitykP = 0.0000125;// velocity stuff probably not needed at all and should keep 0
    private final double velocitykI = 0.0;
    private final double velocitykD = 0.0000008;
*/
    
}
