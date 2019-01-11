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
    }
}
