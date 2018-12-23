package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class OpModeBase extends LinearOpMode {

    public static DcMotor motorRF;
    public static DcMotor motorRB;
    public static DcMotor motorLF;
    public static DcMotor motorLB;

    public static DcMotor lift;
    public static Servo sweeper;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    public void hardwareMap() {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorLB = hardwareMap.dcMotor.get("motorLB");
        lift = hardwareMap.dcMotor.get("lift");
        sweeper = hardwareMap.servo.get("sweeper");

        telemetry.addData("Status", "Hardware Mapped");
        telemetry.update();
    }

    public void initSensors() {
        telemetry.addData("Sensors", "Initializing...");
        telemetry.update();

        telemetry.addData("Sensors", "Initialized");
        telemetry.update();
    }

    public void initServos() {
        sweeper.setPosition(0);
    }

    public void setMotors(double left, double right) {
        if (!opModeIsActive())
            return;

        motorRF.setPower(-left);
        motorRB.setPower(-left);
        motorLF.setPower(right);
        motorLB.setPower(right);
    }

    public void stopMotors() {
        if (!opModeIsActive())
            return;

        motorRF.setPower(0);
        motorRB.setPower(0);
        motorLF.setPower(0);
        motorLB.setPower(0);
    }

    public void resetEncoders() {
        motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorRF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int inToTick(int inches) {
        return (int) (inches * COUNTS_PER_INCH);
    }
    public void encoderMove(double power, double left, double right) {

    }
}
