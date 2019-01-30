package org.firstinspires.ftc.teamcode.auto;

import ftcrobotcontroller.FieldPositonData;

public class AutoOpMode extends OpModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        FieldPositonData.FieldPosition position = FieldPositonData.fieldPostion;
        hardwareMap();
        waitForStart();
        switch(position) {
            case RED_TOP:
                break;
            case RED_BOTTOM:
                break;
            case BLUE_TOP:
                break;
            case BLUE_BOTTOM:
                BlueBottom.runBlueBottom();
                break;
        }
    }
}
