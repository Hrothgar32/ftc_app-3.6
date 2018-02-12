package org.firstinspires.ftc.teamcode.libs;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

/**
 * Created by Vsbi on 2/12/2018. (RoboCorp RO084)
 *
 * Copyright (c) 2018 RoboCorp
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/*


import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;


import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;


/*
 *This class implements a color sensor, it provides implementation of the abstract methods in autonomous modes
 *
public class FtcColorSensor {
    public enum DataType{
        COLOR_NUMBER,
        RED,
        GREEN,
        BLUE,
        ALPHA,
        HUE,
        SATURATION,
        VALUE
    }
    public ColorSensor sensor;
    private int argbData = 0;
    private int[] rgbaData = new int[4];
    private float hsvValues[] = {0.0f, 0.0f, 0.0f};


    /*
     * Constructor
     *
     * @param hardwareMap specifies the global hardwaremap
     * @param instanceName specifes the name of the instance
     *
     *
    public ftcColorSensor(HardwareMap hardwareMap, String instanceName){
        sensor = hardwareMap.get(ColorSensor.class, instanceName);
    }

    @Override
    public SensorData<Double> getRawData(int index, DataType dataType){
        sensorData<Double> data = null;
        NormalizedRGBA normalizedColors = ((NormalizedColorSensor)sensor).getNormalizedColors();
        rgbaData[0] = (int)(normalizedColors.red * 100000.0);
        rgbaData[1] = (int)(normalizedColors..green * 100000.0);
    }
}
*/
