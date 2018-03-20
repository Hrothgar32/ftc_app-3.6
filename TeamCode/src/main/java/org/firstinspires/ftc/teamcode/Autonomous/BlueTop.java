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


@Autonomous(name = "Blue top corner Auto", group = "final")
public class BlueTop extends LinearOpMode{

    private RobotInit robot;
    private String vuMark;

    public void runOpMode(){
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
        robot.armServo.setPosition(0.8);
        robot.sleep(200);
        String f, s;
        if(robot.auto.driver() == 1){
            f = "Forward";
            s = "Backward";
        }
        else{
            f = "Forward";
            s = "Backward";
        }



    }
}
