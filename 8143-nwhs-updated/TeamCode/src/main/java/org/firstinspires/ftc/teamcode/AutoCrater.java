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

    static final double DRIVE_SPEED = 1;
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

        encoderDrive(DRIVE_SPEED, 14);
        turn(-90);
        encoderDrive(DRIVE_SPEED, 40);
        turnNeg45();
        encoderDrive(DRIVE_SPEED, 40);

        //drop the team marker
        robot.dropTeamMarker();
        runtime.reset();
        while (runtime.seconds() < 2 && opModeIsActive()) {
            telemetry.addData("team marker arm will rise in ", runtime.seconds());
            telemetry.update();
        }
        robot.riseTeamMarker();
        while (runtime.seconds() < 4 && opModeIsActive()) {
            telemetry.addData("GOOD LUCK", runtime.seconds());
            telemetry.update();
        }

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

    public void turn(int target) {
        int currentAngle = robot.getAngleDegree(); //robot's current angel
        int turnTarget = target + currentAngle; //set the target

        if (turnTarget > 179) {
            turnTarget %= 180; //make sure you don't go >180
            turnTarget = -180 + turnTarget;
            while ((Math.abs(robot.getAngleDegree() - turnTarget) > 3) && opModeIsActive()) {
                robot.leftDrive(1);
                robot.rightDrive(0);
                telemetry.addData("current position", robot.getAngleDegree());
                telemetry.addData("target position", turnTarget);
                telemetry.addData("difference between current and target: ", Math.abs(robot.getAngleDegree() - turnTarget));
                telemetry.addData("is current greater? ", robot.getAngleDegree() > target);
                telemetry.update();
            }

        } else if (turnTarget < -179) {
            turnTarget %= 180; //make sure you don't go <-180
            turnTarget = 180 + turnTarget;
            while ((Math.abs(robot.getAngleDegree() - turnTarget) > 3) && opModeIsActive()) {

                robot.leftDrive(0);
                robot.rightDrive(1);
                telemetry.addData("current position", robot.getAngleDegree());
                telemetry.addData("target position", turnTarget);
                telemetry.addData("difference between current and target: ", Math.abs(robot.getAngleDegree() - turnTarget));
                telemetry.addData("is current greater? ", robot.getAngleDegree() > target);
                telemetry.update();
            }
        } else {

            while ((Math.abs(robot.getAngleDegree() - turnTarget) > 3) && opModeIsActive()) {
                if (robot.getAngleDegree() > target) { //if you are to the right of the target, rotate left
                    robot.leftDrive(0);
                    robot.rightDrive(1);
                }
                if (robot.getAngleDegree() < target) { //if you are to the left of the target, rotate right
                    robot.leftDrive(1);
                    robot.rightDrive(0);
                }
                telemetry.addData("current position", robot.getAngleDegree());
                telemetry.addData("target position", turnTarget);
                telemetry.addData("difference between current and target: ", Math.abs(robot.getAngleDegree() - turnTarget));
                telemetry.addData("is current greater? ", robot.getAngleDegree() > target);
                telemetry.addData("is -70>-130: ", -70 > -130);
                telemetry.update();
            }
        }
        robot.leftDrive(0); //stop driving once target is reached
        robot.rightDrive(0);
    }

    public void turnNeg45() {
        int currentAngle = robot.getAngleDegree(); //robot's current angel
        int turnTarget = currentAngle - 45; //set the target
        if (turnTarget < -179) {
            turnTarget %= 180; //make sure you don't go <-180
            turnTarget = 180 + turnTarget;
        }
        while ((Math.abs(robot.getAngleDegree() - turnTarget) > 3) && opModeIsActive()) {
            robot.leftDrive(0);
            robot.rightDrive(1);
        }
        robot.zeroPower();
    }
}
