package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "test gyro values")
@Disabled
public class AutoTesting extends LinearOpMode{
    HardwareJimHalpert robot = new HardwareJimHalpert();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialization: ", "In Progress");

        telemetry.update();
        robot.init(hardwareMap);
        telemetry.addData("Initialization: ", "Ready");
        telemetry.update();

        waitForStart();
        turn(90);
        turn(-135);
        while(opModeIsActive()) {
            telemetry.addData("current position", robot.getAngleDegree());
            telemetry.update();
        }
    }
    public void turn(int target) {
        int currentAngle = robot.getAngleDegree(); //robot's current angel
        int turnTarget = target+currentAngle; //set the target

        if(turnTarget>179) {
            turnTarget%=180; //make sure you don't go >180
            turnTarget = -180+turnTarget; //
        } else if (turnTarget<-179) {
            turnTarget%=180; //make sure you don't go <-180
            turnTarget = 180+turnTarget; //
        }

        while((Math.abs(robot.getAngleDegree()-turnTarget)>3)&&opModeIsActive()) {

            if (robot.getAngleDegree()>target) { //if you are to the right of the target, rotate left
                robot.leftDrive(0);
                robot.rightDrive(0);
            }
            if (robot.getAngleDegree()<target) { //if you are to the left of the target, rotate right
                robot.leftDrive(0);
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
