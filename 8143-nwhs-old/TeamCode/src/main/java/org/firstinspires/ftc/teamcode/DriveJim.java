package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Jimmy Drive", group = "official")
public class DriveJim extends LinearOpMode {
    HardwareJimHalpert robot = new HardwareJimHalpert();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialization: ", "In Progress");

        telemetry.update();
        robot.init(hardwareMap);
        robot.resetEncoders();
        robot.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            //tank drive
            robot.leftDrive(gamepad1.left_stick_y);
            robot.rightDrive(gamepad1.right_stick_y);

            //right trigger moves pully up
            //left trigger moves pully down.
            if (gamepad1.right_trigger>0) {
                robot.harvesterLift.setPower(gamepad1.right_trigger/2);
             }
             else if (gamepad1.left_trigger > 0) {
                robot.harvesterLift.setPower(-gamepad1.left_trigger/2);
            }
            else {
                robot.harvesterLift.setPower(0.);
            }

            //CHANGE TO if right is >0, then set power, and then ELSE IF left is >0 then set left power.

            //dpad moves lift up
            if(gamepad1.dpad_up) {
                robot.lift.setPower(-.9);
            } else {
                robot.lift.setPower(0);
            }

            //dpad moves lift down
            if(gamepad1.dpad_down) {
                robot.lift.setPower(.9);
            } else {
                robot.lift.setPower(0);
            }

            //pressing 'a' will turn on/off intake
            //pressing 'b' will turn on/off outtake
            if (gamepad1.a && robot.harvesterIsOn) {
                robot.harvesterOff();
            } else if (gamepad1.a && !robot.harvesterIsOn) {
                robot.harvesterOn();
            } else if (gamepad1.x && robot.harvesterIsOn) {
                robot.harvesterOff();
            } else if (gamepad1.x && !robot.harvesterIsOn) {
                robot.harvesterReverse();
            }
            //pressing y will bring hook to vertical position
            if (gamepad1.y) {
                robot.liftHook.setPosition(0.04);
            }

            //pressing b will unhook
            if (gamepad1.b) {
                robot.liftHook.setPosition(.3);
            }
            //telemetry
            telemetry.addLine("left joystick | ")
                    .addData("x", gamepad1.left_stick_x)
                    .addData("y", gamepad1.left_stick_y);
            telemetry.addLine("right joystick | ")
                    .addData("x", gamepad1.right_stick_x)
                    .addData("y", gamepad1.right_stick_y);
            telemetry.addLine("trigger | ")
                    .addData("left", gamepad1.left_trigger)
                    .addData("right", gamepad1.right_trigger);
            telemetry.addLine("encoders | ")
                    .addData("leftFront", robot.leftFront.getCurrentPosition())
                    .addData("leftBack", robot.leftBack.getCurrentPosition())
                    .addData("rightFront", robot.rightFront.getCurrentPosition())
                    .addData("rightBack", robot.rightBack.getCurrentPosition());
            telemetry.addData("lift power |", robot.lift.getPower());
            telemetry.addData("harvesterLift power |", robot.harvesterLift.getPower());
            telemetry.addData("harvesterIntake power |", robot.harvesterIntake.getPosition());
            telemetry.update();
        }
    }
}


