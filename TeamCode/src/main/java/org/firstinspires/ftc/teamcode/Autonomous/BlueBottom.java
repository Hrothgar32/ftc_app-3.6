package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by zolda on 2018-03-20.
 */
@Autonomous(name = "Blue Bottom Corner", group = "final")
public class BlueBottom extends LinearOpMode {

    private RobotInit robot;
    private String vuMark;

    public void runOpMode() throws InterruptedException {
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
        robot.armServo.setPosition(0.8);
        robot.sleep(200);
        String f, s;
        if(robot.auto.driver() == 1) {
            f = "Forward";
            s = "Backward";
        }
        else{
            f = "Backward";
            s = "Forward";
        }
        robot.setEncoderBlocks((float)0.3, f);
        vuMark = robot.vufModul.identifyVuMark();
        robot.armServo.setPosition(0.3);
        robot.sleep(500);
        robot.setEncoderBlocks((float)0.3, s);
        robot.setEncoderBlocks((float)0.8, "Forward");

        robot.turn(90, 1, 0.10, 0.50, 3);
        robot.setEncoderBlocks((float)0.5, "Forward" );
        robot.gyro.Reset();
        robot.turn(90, 1, 0.10, 0.50, 3);
        robot.gyro.Reset();
        robot.turn(90, 1, 0.10, 0.50, 3);
        robot.setEncoderBlocks((float)0.5, "Forward" );
        robot.gyro.Reset();
        robot.turn(90, 1, 0.10, 0.50, 3);
        robot.auto.vuMovement(robot, vuMark);
    }
}
