package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Defines hardware for Jim Halpert configuration
 */
public class HardwareJimHalpert {

    //public members
    public DcMotor leftFront = null;
    public DcMotor rightFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;
    public DcMotor lift = null;
    public DcMotor harvesterLift = null;
    public DcMotor lights = null;
    public Servo liftHook = null;
    public Servo harvesterIntake = null;
    public Servo teamDropper = null;
    public Servo craterRaker = null;
    public BNO055IMU imu = null;
    //private members
    HardwareMap hardwareMap = null;
    public boolean harvesterIsOn = false;
    public final static int INCHES_TO_TICKS = 72; //5600 for 2 mat length

    //constructor
    public HardwareJimHalpert() {

    }

    //initialization
    public void init(HardwareMap hwMap) {
        //assign hardware map
        hardwareMap = hwMap;

        // define and initialize Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        lift = hardwareMap.get(DcMotor.class, "lift");
        harvesterLift = hardwareMap.get(DcMotor.class, "harvesterLift");
        lights = hardwareMap.get(DcMotor.class, "lights");

        // define and initialize servos
        liftHook = hardwareMap.get(Servo.class, "liftHook");
        harvesterIntake = hardwareMap.get(Servo.class, "harvesterIntake");
        teamDropper = hardwareMap.get(Servo.class, "markerDropper");
        craterRaker = hardwareMap.get(Servo.class, "craterRaker");


        //reverse left side
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        //IMU GYRO
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.useExternalCrystal = true;
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


        hookOff();
        harvesterIntake.setPosition(.5);
        craterRakerUp();
        zeroPower();
    }

    public void motorDirection() {
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
    }

    public void zeroPower() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    public void leftDrive(double power) {
        leftFront.setPower(power);
        leftBack.setPower(power);
    }

    public void rightDrive(double power) {
        rightFront.setPower(power);
        rightBack.setPower(power);
    }

    public void harvesterOn() {
        harvesterIntake.setPosition(.9);
        harvesterIsOn = true;
    }

    public void harvesterReverse() {
        harvesterIntake.setPosition(.1);
        harvesterIsOn = true;
    }

    public void harvesterOff() {
        harvesterIntake.setPosition(.5);
        harvesterIsOn = false;
    }

    public void setMotorMode(DcMotor.RunMode runMode) {
        leftFront.setMode(runMode);
        leftBack.setMode(runMode);
        rightFront.setMode(runMode);
        rightBack.setMode(runMode);
    }

    public void hookOn() {
        liftHook.setPosition(.2);
    }

    public void hookOff() {
        liftHook.setPosition(1);
    }

    public void resetEncoders() {
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int position() {
        return rightBack.getCurrentPosition();
    }

    public void dropTeamMarker() {
        teamDropper.setPosition(.5);
    }


    public void riseTeamMarker() {
        teamDropper.setPosition(1);
    }

    public void craterRakerUp() {
        craterRaker.setPosition(.28);
    }

    public void craterRakerMid() {
        craterRaker.setPosition(.48);
    }

    public void craterRakerDown() {
        craterRaker.setPosition(.78);
    }

    public int getAngleDegree() {
        Orientation angles = null;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return (int) -angles.firstAngle;
    }


}

