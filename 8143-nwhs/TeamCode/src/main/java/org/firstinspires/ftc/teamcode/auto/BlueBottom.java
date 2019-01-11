package org.firstinspires.ftc.teamcode.auto;

public class BlueBottom extends OpModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMap();
        initServos();
        initSensors();

        waitForStart();
        runBlueBottom();
    }

    public static void runBlueBottom() {
    }
}
