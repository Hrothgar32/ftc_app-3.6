package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
        robot.init(hardwareMap,false);

        waitForStart();
        while (opModeIsActive()) {
            robot.backLeftDrive.setPower(gamepad1.right_stick_y);
            robot.backRightDrive.setPower(gamepad1.right_stick_y);
            robot.frontLeftDrive.setPower(gamepad1.right_stick_y);
            robot.frontRightDrive.setPower(gamepad1.right_stick_y);

            if(gamepad1.right_stick_x > 0){
                robot.backRightDrive.setPower(gamepad1.right_stick_x);
                robot.backLeftDrive.setPower(gamepad1.right_stick_x);
                robot.frontLeftDrive.setPower(gamepad1.right_stick_x);
                robot.frontRightDrive.setPower(gamepad1.right_stick_x);
            }
            if(gamepad1.right_stick_x < 0){
                robot.backRightDrive.setPower(gamepad1.right_stick_x);
                robot.backLeftDrive.setPower(gamepad1.right_stick_x);
                robot.frontLeftDrive.setPower(gamepad1.right_stick_x);
                robot.frontRightDrive.setPower(gamepad1.right_stick_x);

            }
        }
    }
}
