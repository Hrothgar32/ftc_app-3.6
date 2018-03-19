package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.RobotInit;
/**
 * Created by Vsbi on 3/16/2018.
 */



@Autonomous(name = "Vu Test", group = "test")
public class vuTest extends LinearOpMode{
    private RobotInit robot;
    String vuMark;

    public void runOpMode(){
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
        vuMark = "left";
        if(vuMark == "left") {
            robot.turn(90, 1, 0.10, 0.30, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.5, "Forward");
            robot.sleep(3000);
            robot.turn(-90, 1, 0.10, 0.30, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }
        if(vuMark == "center"){
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }
        if(vuMark == "right"){
            robot.turn(-90, 1, 0.10, 0.30, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.5, "Forward");
            robot.sleep(3000);
            robot.turn(90, 1, 0.10, 0.30, 3);
            robot.sleep(3000);
            robot.setEncoderBlocks((float) 0.8, "Forward");
            robot.sleep(3000);
        }
    }
}


