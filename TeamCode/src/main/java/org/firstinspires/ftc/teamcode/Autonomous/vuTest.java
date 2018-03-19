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
        robot.init(hardwareMap, true,telemetry);
        vuMark = robot.vufModul.identifyVuMark();
        telemetry.addLine(vuMark);
        telemetry.update();
        while(opModeIsActive()){
            sleep(5000);
            vuMark = robot.vufModul.identifyVuMark();
            telemetry.addLine(vuMark);
            telemetry.update();
            sleep(5000);
        }
    }

}
