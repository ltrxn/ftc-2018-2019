package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.HardwareJimHalpert.INCHES_TO_TICKS;

@Autonomous(name = "position near crater")
public class AutoCrater extends LinearOpMode {
    HardwareJimHalpert robot = new HardwareJimHalpert();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = 0.3;
    static final double TURN_90 = 4;
    static final double TURN_45 = 2;

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

        encoderDrive(DRIVE_SPEED, 4.5);
        turnForwardLeft(TURN_90);
        encoderDrive(DRIVE_SPEED, 20);
//        turnForwardLeft(TURN_90);
        turn(90);
        encoderDrive(DRIVE_SPEED, 24);
        turnForwardRight(TURN_45);
        encoderDrive(DRIVE_SPEED, 10);
        // only if you want to place team marker & park (unlikely) turnBackwardsRight(TURN_90);
        turnForwardRight(TURN_90);
        encoderDrive(DRIVE_SPEED, 40);

        //drop the team marker
        robot.dropTeamMarker();
        while (runtime.seconds() < 2 && opModeIsActive()) {
            telemetry.addData("dropping team marker", "%2f elapsed out of %2f", runtime.seconds(), 2);
            telemetry.update();
        }
        robot.riseTeamMarker();

    }

    public void encoderDrive(double speed, double distance) {
        int target;
        target = robot.position() + (int) (distance * INCHES_TO_TICKS);
        robot.rightBack.setTargetPosition(target);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive(Math.abs(speed));
        robot.leftDrive(Math.abs(speed));
        while (robot.rightBack.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1", "Running to %5d", target);
            telemetry.addData("Path2", "Running at %5d", robot.position());
            telemetry.update();
        }
        robot.zeroPower();
        robot.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void turnForwardLeft(double time) {
        robot.rightDrive(DRIVE_SPEED);
        runtime.reset();
        while (runtime.seconds() < time && opModeIsActive()) {
            telemetry.addData("turnForwardLeft", "%2f elapsed out of %2f", runtime.seconds(), time);
            telemetry.update();
        }
    }

    public void turnForwardRight(double time) {
        robot.leftDrive(DRIVE_SPEED);
        runtime.reset();
        while (runtime.seconds() < time && opModeIsActive()) {
            telemetry.addData("turnForwardRight", "%2f elapsed out of %2f", runtime.seconds(), time);
            telemetry.update();
        }
    }

    public void turnBackwardsLeft(double time) {
        robot.rightDrive(-DRIVE_SPEED);
        runtime.reset();
        while (runtime.seconds() < time && opModeIsActive()) {
            telemetry.addData("turnBackwardsLeft", "%2f elapsed out of %2f", runtime.seconds(), time);
            telemetry.update();
        }
    }

    public void turnBackwardsRight(double time) {
        robot.leftDrive(-DRIVE_SPEED);
        runtime.reset();
        while (runtime.seconds() < time && opModeIsActive()) {
            telemetry.addData("turnBackwardsRight", "%2f elapsed out of %2f", runtime.seconds(), time);
            telemetry.update();
        }
    }

    public void turn(int target) {
        int currentAngle = robot.getAngleDegree(); //robot's current angel
        double turnSpeed = 1; //how fast to turn
        int turnTarget = target + currentAngle; //set the target
        turnTarget %= 360; //make sure you don't go >360

        while (Math.abs(robot.getAngleDegree() - turnTarget) > 3) {

            if (robot.getAngleDegree() > target) { //if you are to the right of the target, rotate left
                robot.leftDrive(0);
                robot.rightDrive(turnSpeed);
            }
            if (robot.getAngleDegree() < target) { //if you are to the left of the target, rotate right
                robot.leftDrive(turnSpeed);
                robot.rightDrive(0);
            }
            sleep(50);
            telemetry.addData("current position", robot.getAngleDegree());
            telemetry.addData("target position", turnTarget);
            telemetry.update();
        }
        robot.leftDrive(0); //stop driving once target is reached
        robot.rightDrive(0);
    }

}
