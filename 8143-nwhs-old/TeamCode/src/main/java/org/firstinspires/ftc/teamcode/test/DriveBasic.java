package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name= "basic driving", group = "test")
public class DriveBasic extends LinearOpMode {
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public DcMotor lift;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        lift = hardwareMap.dcMotor.get("lift");

        waitForStart();

        while (opModeIsActive()) {
            //to drive robot
            leftFront.setPower(-gamepad1.left_stick_y);
            rightFront.setPower(-gamepad1.right_stick_y);
            leftBack.setPower(-gamepad1.left_stick_y);
            rightBack.setPower(-gamepad1.right_stick_y);

        }
    }

}

