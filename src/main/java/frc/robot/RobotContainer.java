// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.Drive.ArcadeDrive;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_robotDrive = new Drivetrain();
  private static OperatorConstants constants;
  private ArcadeDrive arcadeDrive;

  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();

  static XboxController m_driverController = new XboxController(constants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   //m_autoChooser.addOption("4 ball", Auto_4Ball);

    initialize_Subsystems();
    //configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

 // private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    
  //}

  public void initialize_Subsystems(){
    m_robotDrive.setDefaultCommand(
          new ArcadeDrive(m_robotDrive, m_driverController::getLeftY, m_driverController::getLeftX));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /* 
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_autoChooser.getSelected();
  }
  */
}
