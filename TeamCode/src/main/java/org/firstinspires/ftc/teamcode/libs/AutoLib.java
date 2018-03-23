package org.firstinspires.ftc.teamcode.libs;


import android.graphics.Color;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by Vsbi on 3/19/2018.
 *
 *
 *
 *
 *
 */


public class AutoLib {
    public NormalizedColorSensor armSensor = null;
    public void init(HardwareMap ahwMap){
        armSensor = ahwMap.get(NormalizedColorSensor.class, "armSensor");

    }
    public boolean readJewelColor(){
        float[] hsvValues = new float[3];
        NormalizedRGBA colors = armSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red /= max;
        colors.green /= max;
        colors.blue /= max;
        int color = colors.toColor();
        return (Color.red(color) < Color.blue(color));

    }

    private int direction(boolean way) {return  (way == true) ? 1 : -1;}


    public int driver(){
        return  direction(readJewelColor());
    }

    public void vuMovement(RobotInit robot, String vuMark, Telemetry telemetry){
        if(vuMark == "left") {
            robot.turn(90, 1, 0.10, 0.30, telemetry, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.5, "Forward");
            robot.sleep(3000);
            robot.turn(-90, 1, 0.10, 0.30, telemetry, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }
        if(vuMark == "center"){
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }
        if(vuMark == "right"){
            robot.turn(-90, 1, 0.10, 0.30, telemetry, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.5, "Forward");
            robot.sleep(3000);
            robot.turn(90, 1, 0.10, 0.30, telemetry, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }

    }

}
