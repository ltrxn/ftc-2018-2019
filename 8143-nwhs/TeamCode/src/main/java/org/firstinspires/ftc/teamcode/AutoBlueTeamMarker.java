package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import ftc.vision.FrameGrabber;
import ftc.vision.Result;
import ftc.vision.ruckus.MineralColorResult;

import static org.firstinspires.ftc.teamcode.HardwareJimHalpert.INCHES_TO_TICKS;

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
        robot.motorDirection();
        robot.resetEncoders();

        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();

//        //drop off from lander
//        robot.lift.setPower(DRIVE_SPEED);
//        robot.lift.setPower(DRIVE_SPEED);
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
//            telemetry.addData("Lift Drop", "Leg 1: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//        robot.lift.setPower(0);

        encoderDrive(.3, 10);

        //drive forwards
        robot.leftDrive(DRIVE_SPEED);
        robot.rightDrive(DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            telemetry.addData("Drive forward", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.zeroPower();


        //Scan for mineral
       Result mineralPosition = FtcRobotControllerActivity.processFrame();

//        MineralColorResult.MineralColor leftColor = mineralPosition.get
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
        robot.zeroPower();
    }

    public void encoderDrive(double speed, double distance) {
        int target;
        target = robot.position() + (int)(distance*INCHES_TO_TICKS);
        robot.rightBack.setTargetPosition(target);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive(Math.abs(speed));
        robot.leftDrive(Math.abs(speed));
        while (robot.rightBack.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %5d", target);
            telemetry.addData("Path2",  "Running at %5d", robot.position());
            telemetry.update();
        }
        robot.zeroPower();
        robot.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
}
