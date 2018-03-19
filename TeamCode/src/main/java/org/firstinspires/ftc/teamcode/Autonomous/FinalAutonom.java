package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import android.graphics.Color;
import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by RoboCorp on 3/6/2018.
 */


/**             1. negyed               */

/** turn(90, 1, 0.10, 0.50, 3);
    setEncoderBlocks(0.5, "Forward");
    turn(90, 1, 0.10, 0.50, 3);
    setEncoderBlocks(12, "Forward");
 */


/**             2. negyed               */

/** turn(90, 1, 0.10, 0.50, 3);
    setEncoderBlocks(0.5, "Forward");
    turn(90, 1, 0.10, 0.50, 3);
 */


/**             3. negyed               */

/** setEncoderBlocks(0.5, "Forward");
    turn(-90, 1, 0.10, 0.50, 3);
 */


/**             4. negyed               */

/** setEncoderBlocks(1.5, "Backward");
    turn(-90, 1, 0.10, 0.50, 3);
 */

/*
@Autonomous (name = "FinalAutonom", group = "Final")
public class FinalAutonom extends LinearOpMode{

    private RobotInit robot;

    private boolean readJewelColor(){
        robot.sleep(1000);
        float[] hsvValues = new float[3];
        NormalizedRGBA colors = robot.armSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red /= max;
        colors.green /= max;
        colors.blue /= max;
        int color = colors.toColor();
        telemetry.addData("Blue color: ", Color.blue(color));
        telemetry.addData("Red color: ", Color.red(color));
        telemetry.update();
        robot.sleep(5000);
        return (Color.red(color) > Color.blue(color));
    }

    private int GetPosition(boolean isUpper, int turnWay){

        if(readJewelColor());
    }


    @Override
    public void runOpMode(){
        robot = new RobotInit();
        int turnWay;
        boolean isUpper;

        GetPosition(isUpper, turnWay);

        waitForStart();

        robot.init(hardwareMap, true);
    }
}
*/