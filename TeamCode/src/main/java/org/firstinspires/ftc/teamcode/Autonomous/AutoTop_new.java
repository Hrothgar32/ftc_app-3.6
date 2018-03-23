package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.RobotInit;
/**
 * Created by Vsbi on 3/23/2018.
 */

@Autonomous(name = "Red top final", group = "test")
public class AutoTop_new extends LinearOpMode{
    private RobotInit robot;
    private String vuMark;

    static final double newrest = 2750;
    static final double tetrix = 2136;

    static final double driveSpeed = 0.5;
    static final double turnSpeed = 0.4;

    public void runOpMode(){
        robot = new RobotInit();
        while(!isStarted()){
            telemetry.addData("angle", robot.gyro.getAngle());
            telemetry.update();
        }
        waitForStart();
        robot.init(hardwareMap, true);
        robot.armMotor.setPower(0.20);
        robot.armServo.setPosition(0.80);
        robot.lift.setPower(0.40);
        robot.sleep(1500);

        int sig = -1;
        if(robot.auto.readJewelColor() == true){
            sig = 1;
        }
        drive(0.2 * sig , driveSpeed);
        robot.armServo.setPosition(0.3);
        robot.sleep(1000);
        drive(-sig * 0.2, driveSpeed);
        drive(0.2, driveSpeed);
        vuMark = robot.vufModul.identifyVuMark();
        drive(-0.3, driveSpeed);
        drive(-1, driveSpeed);
        turn(90, 2, 0.10, 0.20, 3);
        vuMovement(vuMark);
        robot.armMotor.setPower(0);
        drive(-0.2, driveSpeed);
        robot.armMotor.setPower(0.2);
        drive(0.2, driveSpeed);
    }


    public void drive(double distance, double speed){
        int tetrixtarget, newresttarget;
        if(opModeIsActive()){
            newresttarget = (int)(distance * newrest);
            tetrixtarget = (int)(distance * tetrix);

            robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.frontLeftDrive.setTargetPosition(tetrixtarget);
            robot.frontRightDrive.setTargetPosition(tetrixtarget);
            robot.backLeftDrive.setTargetPosition(newresttarget);
            robot.backRightDrive.setTargetPosition(newresttarget);

            robot.frontLeftDrive.setPower(driveSpeed);
            robot.frontRightDrive.setPower(driveSpeed);
            robot.backLeftDrive.setPower(driveSpeed);
            robot.backRightDrive.setPower(driveSpeed);

            while(opModeIsActive() && robot.backRightDrive.isBusy() && robot.frontLeftDrive.isBusy()){
                telemetry.addLine("Moving");
                telemetry.update();

            }
            robot.frontLeftDrive.setPower(0);
            robot.frontRightDrive.setPower(0);
            robot.backLeftDrive.setPower(0);
            robot.backRightDrive.setPower(0);
        }

    }
    public void turn(int target, int range, double minspeed, double addSpeed, int steps){
        if(opModeIsActive()) {
            if (target < 0)
                this.turn2(Math.abs(target), range, minspeed, addSpeed, steps);
            int current = robot.gyro.getAngle();
            int count = 0;
            while(count != steps && opModeIsActive()){
                if(!isok(current, target, range)){
                    current = robot.gyro.getAngle();
                    telemetry.addData("angle:", current);
                    int mod = target - current;
                    mod /= 30;
                    telemetry.addData("mod: ", mod);
                    telemetry.update();
                    robot.setMotorPower(minspeed +addSpeed * Math.abs(mod), "Rotation");
                }
                else
                    count++;
              robot.stopMotors();
              robot.gyro.reset();
            }
        }

    }

    public void turn2(int target, int range, double minspeed, double addSpeed, int steps){
        if(opModeIsActive()) {
            int current = robot.gyro.getAngle();
            int count = 0;
            while(count != steps && opModeIsActive()){
                if(!isok(current, target, range)){
                    current = robot.gyro.getAngle();
                    telemetry.addData("angle:", current);
                    int mod = target - current;
                    mod /= 30;
                    telemetry.addData("mod: ", mod);
                    telemetry.update();
                    robot.setMotorPowerCtrClcw(minspeed +addSpeed * Math.abs(mod), "Rotation");
                }
                else
                    count++;
                robot.stopMotors();
                robot.gyro.reset();
            }
        }

    }


    public boolean isok(int a, int b, int c){
        return ((a >= b - c && a <= b + c));
}

    public void vuMovement(String vuMark){
        if(opModeIsActive()) {
            if (vuMark == "left") {
                turn(90, 1, 0.10, 0.30, 3);
                robot.sleep(1000);
                drive(0.5, driveSpeed);
                turn(-90, 1, 0.10, 0.30, 3);
                drive(0.8, driveSpeed);
                robot.sleep(1000);
            }
            if (vuMark == "center") {
                drive(0.8, driveSpeed);
                robot.sleep(1000);
            }
            if (vuMark == "right") {
                turn(-90, 1, 0.10, 0.30, 3);
                robot.sleep(1000);
                drive(0.5, driveSpeed);
                turn(90, 1, 0.10, 0.30, 3);
                drive(0.8, driveSpeed);
                robot.sleep(1000);
            }
        }
    }

}
