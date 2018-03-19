package org.firstinspires.ftc.teamcode.libs;


import android.graphics.Color;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.HardwareMap;


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
    private boolean readJewelColor(){
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



}