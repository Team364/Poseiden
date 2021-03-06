package com.team364.frc2019.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.team364.frc2019.RobotMap;
import com.team364.frc2019.misc.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DigitalInput;

public class Trident extends Subsystem {

  private static Trident Instance = null;
  private VictorSPX intake;
  private VictorSPX intakeSlave;
  private DoubleSolenoid cl;
  private DoubleSolenoid le;
  public Piston claw;
  public Piston lever;
  public DigitalInput infrared;

  public Trident() {
    intake = new VictorSPX(RobotMap.rightClaw);

    intakeSlave = new VictorSPX(RobotMap.leftClaw);
    intakeSlave.follow(intake);

    infrared = new DigitalInput(RobotMap.intakeLimitSwitch);

    cl = new DoubleSolenoid(RobotMap.intakePort1, RobotMap.intakePort2);
    le = new DoubleSolenoid(RobotMap.leverPort1, RobotMap.leverPort2);
    claw = new Piston(cl, "Claw");
    lever = new Piston(le, "Lever");

    intake.configPeakOutputForward(RobotMap.intakePeakOutputForward);
    intake.configPeakOutputReverse(RobotMap.intakePeakOutputReverse);
    intake.configNominalOutputReverse(RobotMap.intakeNominalOutputReverse);
    intake.configNominalOutputForward(RobotMap.intakeNominalOutputForward);

    intake.setNeutralMode(NeutralMode.Coast);
    intakeSlave.setNeutralMode(NeutralMode.Coast);
  }

  public synchronized static Trident getInstance() {
    if (Instance == null) {
      Instance = new Trident();
    }
    return Instance;
  }

  public void runIntake(double power) {
    intake.set(ControlMode.PercentOutput, power);
  }

  public void stopIntake() {
    runIntake(0);
  }

  public double getRawOutput() {
    return intake.getMotorOutputPercent();
  }

  public boolean tridentInactive() {
    return ((intake.getSelectedSensorVelocity() == 0) && lever.noCommand() && claw.noCommand());
  }

  @Override
  public void initDefaultCommand() {
  }
}
