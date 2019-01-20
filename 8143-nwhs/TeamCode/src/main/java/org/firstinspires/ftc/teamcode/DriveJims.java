package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Jimmies Drive", group = "official")

public class DriveJims extends LinearOpMode {
    HardwareJimHalpert robot = new HardwareJimHalpert();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialization: ", "In Progress");

        telemetry.update();
        robot.init(hardwareMap);
        robot.resetEncoders();

        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                robot.rightFront.setPower(.2);
            }
            //tank drive [gamepad 1:left and right joystick]
            robot.leftDrive(gamepad1.left_stick_y);
            robot.rightDrive(gamepad1.right_stick_y);

            //move harvesterLift up [gamepad 2:right trigger]
            //ove harvesterLift up [gamepad 2:left trigger]
            if (gamepad2.right_trigger > 0) {
                robot.harvesterLift.setPower(gamepad2.right_trigger / 2);
            } else if (gamepad2.left_trigger > 0) {
                robot.harvesterLift.setPower(-gamepad2.left_trigger / 2);
            } else {
                robot.harvesterLift.setPower(0.);
            }

            //moves lift up [gamepad 1 or 2:dpad up]
            if (gamepad2.dpad_up) {
                robot.lift.setPower(.9);
            } else if (gamepad1.dpad_up) {
                robot.lift.setPower(.9);
                //moves lift down [gamepad 1 or 2:dpad down]
            } else if (gamepad2.dpad_down) {
                robot.lift.setPower(-.9);
            } else if (gamepad1.dpad_down) {
                robot.lift.setPower(-.9);
            } else {
                robot.lift.setPower(0);
            }

            //harvester intake [gamepad 2:a]
            //harvester outtake [gamepad 2:b]
            //harvester off [gamepad 2:x]
            if (gamepad2.a) {
                robot.harvesterOn();
            } else if (gamepad2.x) {
                robot.harvesterOff();
            } else if (gamepad2.b) {
                robot.harvesterReverse();
            }

            //hook to vertical position [gamepad2:dpad right]
            if (gamepad2.dpad_right) {
                robot.hookOn();
            } else if (gamepad1.dpad_right) {
                robot.hookOn();
            }

            //hook to horizontal position/unhook [gamepad2:dpad left]
            if (gamepad2.dpad_left) {
                robot.hookOff();
            } else if (gamepad1.dpad_left) {
                robot.hookOff();
            }

            //control lights [gamepad1:triggers]
            robot.lights.setPower(gamepad1.right_trigger / 2);

            //drop team marker [gamepad2.right_bumper]
            if (gamepad2.right_bumper) {
                robot.dropTeamMarker();
            }
            //up team marker [gamepad2.left_bumper]
            if (gamepad2.left_bumper) {
                robot.riseTeamMarker();
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
            telemetry.addData("liftHook Position |", robot.liftHook.getPosition());
            telemetry.update();
        }
    }
}
