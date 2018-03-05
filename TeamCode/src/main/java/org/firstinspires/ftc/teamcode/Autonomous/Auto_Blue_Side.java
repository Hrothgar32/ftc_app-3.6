package org.firstinspires.ftc.teamcode.Autonomous;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.libs.RobotInit;


/**
 *
 */
@Autonomous(name="Auto_Blue_Side", group="Linear Opmode")

public class Auto_Blue_Side extends LinearOpMode {

    private RobotInit robot;
    String niceVuMark;


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

    private int direction(boolean way) {
        return (way == true) ? -1 : 1;
    }

    @Override
    public void runOpMode() {
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);

        robot.armServo.setPosition(1);
        telemetry.update();


        int way = direction(readJewelColor());

        robot.setMotorPower(0.7  * way, "Straight");
        robot.sleep(200);
        robot.armServo.setPosition(0.2);
        robot.sleep(150);
        robot.setMotorPower(0, "Straight");

    }
}
