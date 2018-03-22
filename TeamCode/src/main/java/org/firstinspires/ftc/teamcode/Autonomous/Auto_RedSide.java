package org.firstinspires.ftc.teamcode.Autonomous;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.libs.RobotInit;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */




@Autonomous(name="Auto_Red_Side", group="Linear Opmode")
//@Disabled
public class Auto_RedSide extends LinearOpMode {

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
        return (Color.red(color) < Color.blue(color));
    }

    private int direction(boolean way){
        return  (way == true) ? 1 : -1;
    }


    @Override
    public void runOpMode() {
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
/*
        robot.armServo.setPosition(1);
        telemetry.update();


        int way = direction(readJewelColor());

        robot.setMotorPower(0.7  * way, "Straight");
        robot.sleep(200);
        robot.armServo.setPosition(0.2);
        robot.sleep(150);
        robot.setMotorPower(0, "Straight");

*/
    }
}