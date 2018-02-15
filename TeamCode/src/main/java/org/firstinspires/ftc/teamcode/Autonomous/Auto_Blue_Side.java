/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Autonomous;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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

@Autonomous(name="AutonomOP_Ball", group="Linear Opmode")
//@Disabled
public class Auto_Blue_Side extends LinearOpMode {

    private RobotInit robot;

    private boolean readJewelColor(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
        }
        float[] hsvValues = new float[3];
        NormalizedRGBA colors = robot.armSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red /= max;
        colors.green /= max;
        colors.blue /= max;
        int color = colors.toColor();
        if(Color.red(color) > Color.blue(color))
            return true;
        else
            return false;
    }

    @Override
    public void runOpMode() {
        robot = new RobotInit();
        robot.init(hardwareMap, true);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.armServo.setPosition(0.5);
        boolean forward = readJewelColor();
        if(forward) {
            robot.frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
            robot.backRightDrive.setDirection(DcMotor.Direction.REVERSE);
            robot.frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.setMotorPower(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                telemetry.addData("Error", "Nono");
            }
            robot.setMotorPower(0);
        }
        else{
            robot.frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
            robot.backRightDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
            robot.setMotorPower(1);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                telemetry.addData("Error","Nono");
            }
            robot.setMotorPower(0);
        }
        robot.vufModul.identifyVuMark();

        // run until the end of the match (driver presses STOP)
        while(opModeIsActive()) {

        }
    }
}