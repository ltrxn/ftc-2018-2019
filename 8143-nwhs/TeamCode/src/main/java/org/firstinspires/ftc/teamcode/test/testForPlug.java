package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This OpMode tests each motor and servo
 */
@TeleOp(name= "Test for Plug", group = "TestCodes")
public class testForPlug extends LinearOpMode {
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


        waitForStart();

        while (opModeIsActive()) {
            //to drive robot
            while(gamepad1.a) {
                leftFront.setPower(testingPower);
            }
            while(gamepad1.b) {
                rightFront.setPower(testingPower);
            }
            while(gamepad1.x) {
                leftBack.setPower(testingPower);
            }
            while(gamepad1.y) {
                rightBack.setPower(testingPower);
            }
            //move harvesterLift up
            while(gamepad1.dpad_up) {
                harvesterLift.setPower(testingPower);
            }
            //move harvesterLift down
            while(gamepad1.dpad_down) {
                harvesterLift.setPower(-testingPower);
            }
            //move lift up
            while(gamepad1.dpad_right) {
                lift.setPower(testingPower);
            }
            //move lift down
            while(gamepad1.dpad_left) {
                lift.setPower(-testingPower);
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

