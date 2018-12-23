package org.firstinspires.ftc.teamcode;

public class BlueBottom extends OpModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMap();
        initServos();
        initSensors();

        waitForStart();

    }
}
