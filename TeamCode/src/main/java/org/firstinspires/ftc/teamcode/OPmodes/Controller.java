package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.DriveMode;
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

@TeleOp(name="Controller", group="Linear Opmode")
public class Controller extends LinearOpMode {

    private RobotInit robot = null;




    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot = new RobotInit();
        robot.init(hardwareMap, false);

        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.right_stick_y <=0.5&&gamepad1.right_stick_y >=-0.5)

            {
                robot.backLeftDrive.setPower(gamepad1.right_stick_y / 2);
                robot.backRightDrive.setPower(gamepad1.right_stick_y / 2);
                robot.frontLeftDrive.setPower(gamepad1.right_stick_y / -2);
                robot.frontRightDrive.setPower(gamepad1.right_stick_y / -2);
            }
            else

            {
                robot.backLeftDrive.setPower(gamepad1.right_stick_y);
                robot.backRightDrive.setPower(gamepad1.right_stick_y);
                robot.frontLeftDrive.setPower(gamepad1.right_stick_y);
                robot.frontRightDrive.setPower(gamepad1.right_stick_y);
            }

            if(gamepad1.right_stick_x <=0.5&&gamepad1.right_stick_x >=-0.5)

            {
                robot.backLeftDrive.setPower(gamepad1.right_stick_x / 2);
                robot.backRightDrive.setPower(-gamepad1.right_stick_x / 2);
                robot.frontLeftDrive.setPower(-gamepad1.right_stick_x / 2);
                robot.frontRightDrive.setPower(gamepad1.right_stick_x / 2);
            }
            else

            {
                robot.backLeftDrive.setPower(gamepad1.right_stick_x);
                robot.backRightDrive.setPower(-gamepad1.right_stick_x);
                robot.frontLeftDrive.setPower(gamepad1.right_stick_x);
                robot.frontRightDrive.setPower(-gamepad1.right_stick_x);
            }
        }
    }

}
    /*/
    private void tankDriveMode(){
      forwardDriveMode();
      turnDriveMode();
    }

    private void driveTelemetry(){
        telemetry.addData("frontLeftDrive pos:", robot.frontLeftDrive.getCurrentPosition());
        telemetry.addData("frontRightDrive pos: ", robot.frontLeftDrive.getCurrentPosition());
        telemetry.addData("backLeftDrive pos: ",robot.backLeftDrive.getCurrentPosition());
        telemetry.addData("backRightDrive pos: ", robot.backRightDrive.getCurrentPosition());
    }

    private void forwardDriveMode(){

        if (gamepad1.right_stick_y <= 0.5 && gamepad1.right_stick_y >= -0.5){
            robot.backLeftDrive.setPower(gamepad1.right_stick_y/2);
            robot.backRightDrive.setPower(gamepad1.right_stick_y/2);
            robot.frontLeftDrive.setPower(gamepad1.right_stick_y/2);
            robot.frontRightDrive.setPower(gamepad1.right_stick_y/2);
        }
        else{
            robot.backLeftDrive.setPower(gamepad1.right_stick_y);
            robot.backRightDrive.setPower(gamepad1.right_stick_y);
            robot.frontLeftDrive.setPower(gamepad1.right_stick_y);
            robot.frontRightDrive.setPower(gamepad1.right_stick_y);
        }

    }

    private void turnDriveMode(){
        if (gamepad1.right_stick_x <= 0.5 && gamepad1.right_stick_x >= -0.5){
            robot.backLeftDrive.setPower(gamepad1.right_stick_x/2);
            robot.backRightDrive.setPower(-gamepad1.right_stick_x/2);
            robot.frontLeftDrive.setPower(-gamepad1.right_stick_x/2);
            robot.frontRightDrive.setPower(gamepad1.right_stick_x/2);
        }
        else{
            robot.backLeftDrive.setPower(gamepad1.right_stick_x);
            robot.backRightDrive.setPower(-gamepad1.right_stick_x);
            robot.frontLeftDrive.setPower(gamepad1.right_stick_x);
            robot.frontRightDrive.setPower(-gamepad1.right_stick_x);
        }
    }

    private void driveSelectedMode(){
        switch (robot.driveMode){
            case TANKDRIVE:
                tankDriveMode();
                break;
            case TURN_ONLY:
                turnDriveMode();
                break;
            case FORWARD_ONLY:
                forwardDriveMode();
                break;
        }
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot = new RobotInit();
        robot.init(hardwareMap,false);

        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.x){
                robot.driveMode = DriveMode.TANKDRIVE;
                telemetry.addData("Drivemode changed:", " TANKDRIVE");
            }
            if(gamepad1.a){
                robot.driveMode = DriveMode.FORWARD_ONLY;
                telemetry.addData("Drivemode changed:", " FORWARD_ONLY");
            }
            if(gamepad1.b){
                robot.driveMode = DriveMode.TURN_ONLY;
                telemetry.addData("Drivemode changed: ", " TURN_ONLY");
            }
            driveSelectedMode();
            driveTelemetry();
            telemetry.update();
        }
    }
    /*/

