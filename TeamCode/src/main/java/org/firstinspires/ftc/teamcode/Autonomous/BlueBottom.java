package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by zolda on 2018-03-20.
 */
@Autonomous(name = "Blue Bottom Corner", group = "final")
@Disabled
public class BlueBottom extends LinearOpMode {

    private RobotInit robot;
    private String vuMark;

    public void runOpMode() {
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
        robot.setEncoderBlocks((float)0.35, "Backward");

        vuMark = robot.vufModul.identifyVuMark();
        telemetry.addData("vumark", vuMark);
        telemetry.update();

        robot.setEncoderBlocks((float)1.5, "Forward");
        robot.turn(90, 2, 0.10, 0.20, telemetry, 3);
        robot.auto.vuMovement(robot, vuMark, telemetry);
    }
}
