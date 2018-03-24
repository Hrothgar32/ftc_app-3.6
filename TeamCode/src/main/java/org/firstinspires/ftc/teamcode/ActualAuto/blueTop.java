package org.firstinspires.ftc.teamcode.ActualAuto;



import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by Vsbi on 19/3/2018 (RoboCorp RO084)
 * This is the autonomous code used by RoboCorp RO084 during the 2017-2018 season of FTC
 * Copyright (c) 2018 RoboCorp
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the followin   g conditions:
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

@Autonomous(name = "blue top", group = "final")
public class blueTop extends LinearOpMode{

    public ColorSensor armSensor = null;
    private RobotInit robot;
    private String vuMark;


    public void runOpMode(){
        robot = new RobotInit();
        armSensor = hardwareMap.get(ColorSensor.class, "armSensor");
        waitForStart();

        robot.init(hardwareMap, true);
        robot.armServo.setPosition(1);
        robot.sleep(2000);

        telemetry.addLine()
                .addData("red:", armSensor.red())
                .addData("blue", armSensor.blue());
        telemetry.update();


        String first, second;

        if(armSensor.red() >= armSensor.blue()){
            first = "Backward";
            second = "Forward";
        }
        else{
            first = "Forward";
            second = "Backward";
        }
        robot.sleep(1800);
        telemetry.addLine()
                .addData("first", first)
                .addData("second", second);
        telemetry.update();
        //knocking the jewel off
        robot.setEncoderBlocks((float)0.2, first);
        robot.armServo.setPosition(0.3);
        robot.sleep(1500);
        robot.setEncoderBlocks((float)0.2, second);


    }
}
