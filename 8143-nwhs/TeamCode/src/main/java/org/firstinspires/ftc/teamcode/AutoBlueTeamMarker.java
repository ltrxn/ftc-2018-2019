package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import ftc.vision.FrameGrabber;
import ftc.vision.Result;
import ftc.vision.ruckus.MineralColorResult;

@Autonomous(name = "Blue Team Marker")
public class AutoBlueTeamMarker extends LinearOpMode {
    HardwareJimHalpert robot = new HardwareJimHalpert();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = 0.3;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialization: ", "In Progress");

        telemetry.update();
        robot.init(hardwareMap);
        robot.resetEncoders();

        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();

        //drop off from lander
        robot.lift.setPower(DRIVE_SPEED);
        robot.lift.setPower(DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Lift Drop", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.lift.setPower(0);

        //drive forwards
        robot.leftDrive(-DRIVE_SPEED);
        robot.rightDrive(-DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            telemetry.addData("Drive forward", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.zeroPower();


        //Scan for mineral
        Result<MineralColorResult> mineralPosition = FtcRobotControllerActivity.processFrame();
        telemetry.addData("Result", mineralPosition.toString());
        telemetry.update();

        //drive forward
        robot.leftDrive(DRIVE_SPEED);
        robot.rightDrive(DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3)) {
            telemetry.addData("Drive forward", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.zeroPower();        //
    }
}
