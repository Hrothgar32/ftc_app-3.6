package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;

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



@Autonomous(name = "Red bottom corner", group = "final")
public class RedBottom extends LinearOpMode{

    private RobotInit robot;
    private String vuMark = "nothing";

    public void runOpMode(){
        robot = new RobotInit();
         waitForStart();
        robot.init(hardwareMap, true);
        robot.armMotor.setPower(0.2);

    //lifting up the cube
        robot.lift.setPower(0.4);
        robot.sleep(500);
        robot.lift.setPower(0);

        robot.armServo.setPosition(1);
        robot.sleep(3000);
        robot.lift.setPower(0);
    String first, second;
        telemetry.addData("szin: ", robot.auto.driver());
        telemetry.update();
        if(robot.auto.driver() == 1){
        first = "Forward";
        second = "Backward";
    }
        else{
        first = "Backward";
        second = "Forward";
    }
    //knocking the jewel off
        robot.setEncoderBlocks((float)0.2, first);
        robot.armServo.setPosition(0.3);
        robot.sleep(1000);
        robot.setEncoderBlocks((float)0.25, second);
    //reading the VuMark
        robot.setEncoderBlocks((float)0.35, "Forward");

         vuMark = robot.vufModul.identifyVuMark();
        telemetry.addData("vumark", vuMark);
        telemetry.update();

        robot.setEncoderBlocks((float)1, "Backward");
        robot.turnClcw(90, 2, 0.10, 0.20, telemetry, 3);
        robot.setEncoderBlocks((float)0.5, "Forward");
        robot.turn(90, 2, 0.10, 0.20, telemetry, 3);
        robot.auto.vuMovement(robot, vuMark, telemetry);
    }
}
