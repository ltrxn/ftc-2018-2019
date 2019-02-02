package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "for testing")
public class AutoTesting extends LinearOpMode {
    HardwareJimHalpert robot = new HardwareJimHalpert();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialization: ", "In Progress");

        telemetry.update();
        robot.init(hardwareMap);
        robot.motorDirection();
        robot.resetEncoders();

        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();

//        turn(90);
    }
}
