package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Defines hardware for Jim Halpert configuration
 */
public class HardwareJimHalpert {

    //public members
    public DcMotor  leftFront       = null;
    public DcMotor  rightFront      = null;
    public DcMotor  leftBack        = null;
    public DcMotor  rightBack       = null;
    public DcMotor  lift            = null;
    public DcMotor  harvesterLift   = null;

    public Servo    liftHook        = null;
    public Servo    harvesterIntake = null;

    //private members
    HardwareMap hardwareMap               =  null;
    public boolean harvesterIsOn = false;

    //constructor
    public HardwareJimHalpert(){

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
        // define and initialize servos
        liftHook = hardwareMap.get(Servo.class, "liftHook");
        harvesterIntake = hardwareMap.get(Servo.class, "harvesterIntake");

        //reverse left side
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        liftHook.setPosition(.04);
        harvesterIntake.setPosition(.5);
        zeroPower();
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
        harvesterIsOn =true;
    }

    public void harvesterReverse() {
        harvesterIntake.setPosition(.1);
        harvesterIsOn = true;
    }

    public void harvesterOff() {
        harvesterIntake.setPosition(.5);
        harvesterIsOn =false;
    }
    public void setMotorMode(DcMotor.RunMode runMode) {
        leftFront.setMode(runMode);
        leftBack.setMode(runMode);
        rightFront.setMode(runMode);
        rightBack.setMode(runMode);
    }

    public void resetEncoders() {
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}
