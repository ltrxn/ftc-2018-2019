package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This OpMode tests each motor and servo
 */
@TeleOp(name= "test different parts", group = "test")
public class DriveTestParts extends LinearOpMode {
    //motors
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public DcMotor lift;
    public DcMotor harvesterLift;
    //servos
    public Servo liftHook;
    public Servo harvesterIntake;
    //variables
    private double testingPower = 0.3;
    private double armPosition = 0.5;
    private double armDelta = 0.05;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        lift = hardwareMap.dcMotor.get("lift");
        harvesterLift = hardwareMap.dcMotor.get("harvesterLift");

        liftHook = hardwareMap.servo.get("liftHook");
        harvesterIntake = hardwareMap.servo.get("harvesterIntake");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            //to drive robot
            if(gamepad1.a) { //does left back
                leftFront.setPower(testingPower);
            } else {
                leftFront.setPower(0);
            }
            if(gamepad1.b) { //does left front
                rightFront.setPower(testingPower);
            } else {
                rightFront.setPower(0);
            }
            if(gamepad1.x) { //right front
                leftBack.setPower(testingPower);
            } else {
                leftBack.setPower(0);
            }
            if(gamepad1.y) { //corect
                rightBack.setPower(testingPower);
            } else {
                rightBack.setPower(0);
            }
            //move harvesterLift up
            if(gamepad1.dpad_up) {
                harvesterLift.setPower(testingPower);
            } else {
                harvesterLift.setPower(0);
            }
            //move harvesterLift down
            if(gamepad1.dpad_down) {
                harvesterLift.setPower(-testingPower);
            } else {
                harvesterLift.setPower(0);
            }
            //move lift up
            if(gamepad1.dpad_right) {
                lift.setPower(testingPower);
            } else {
                lift.setPower(0);
            }
            //move lift down
            if(gamepad1.dpad_left) {
                lift.setPower(-testingPower);
            } else {
                lift.setPower(0);
            }
            if (gamepad1.right_bumper) {
            // if the right_bumper is pushed, start the servo
                armPosition += armDelta;
            }
            if (gamepad1.left_bumper) {
            // if the left_bumper is pushed, stop cont. servo
                armPosition = 0.5;
            }
            harvesterIntake.setPosition(armPosition);
            telemetry.addLine("left joystick | ")
                    .addData("x", gamepad1.left_stick_x)
                    .addData("y", gamepad1.left_stick_y);
            telemetry.addLine("right joystick | ")
                    .addData("x", gamepad1.right_stick_x)
                    .addData("y", gamepad1.right_stick_y);
            telemetry.update();
        }
    }

}

