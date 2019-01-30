package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HardwareJimHalpert;

public abstract class OpModeBase extends LinearOpMode {

    HardwareJimHalpert robot = new HardwareJimHalpert();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    public void hardwareMap() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hardware Mapped");
        telemetry.update();
    }

    public void setMotors(double left, double right) {
        if (!opModeIsActive())
            return;
        robot.rightDrive(right);
        robot.leftDrive(left);
    }

    public void stopMotors() {
        if (!opModeIsActive())
            return;
        robot.zeroPower();
    }

    public void resetEncoders() {
        robot.resetEncoders();
    }

    public int inToTick(int inches) {
        return (int) (inches * COUNTS_PER_INCH);
    }
    public void encoderMove(double power, double left, double right) {

    }
}
