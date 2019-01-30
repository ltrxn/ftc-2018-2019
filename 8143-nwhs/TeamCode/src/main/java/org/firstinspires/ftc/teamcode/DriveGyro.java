package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "testing gyro", group = "official")
@Disabled
public class DriveGyro extends LinearOpMode {
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
            telemetry.addData("current angle:", robot.getAngleDegree());
            telemetry.update();
        }
    }
}