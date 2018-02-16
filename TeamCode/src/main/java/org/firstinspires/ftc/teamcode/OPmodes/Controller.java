package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.RobotInit;


/** Created by Rodnan 2/16/2018.
 *
 *
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
    private DcMotor lift = null;
    private DcMotor armMotor = null;
    private double liftPower = 0;

    @Override
    public void runOpMode() {


        lift = hardwareMap.get(DcMotor.class, "lift");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        robot = new RobotInit();
        robot.init(hardwareMap, false);

        waitForStart();

        armMotor.setPower(0);
        lift.setPower(0);

        while (opModeIsActive()) {


            /**     Drive control    */

            if (gamepad1.left_stick_y >= 0.51 || gamepad1.left_stick_y <= -0.51) {
                robot.backLeftDrive.setPower(gamepad1.left_stick_y/2);
                robot.backRightDrive.setPower(gamepad1.left_stick_y/2);
                robot.frontLeftDrive.setPower(-gamepad1.left_stick_y/2);
                robot.frontRightDrive.setPower(-gamepad1.left_stick_y/2);
            }

            else {
                if (gamepad1.left_stick_x >= 0.52 || gamepad1.left_stick_x <= -0.52) {
                    robot.backLeftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.backRightDrive.setPower(-gamepad1.left_stick_x/2);
                    robot.frontLeftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.frontRightDrive.setPower(-gamepad1.left_stick_x/2);
                }
                else{
                    robot.backLeftDrive.setPower(0);
                    robot.backRightDrive.setPower(0);
                    robot.frontLeftDrive.setPower(0);
                    robot.frontRightDrive.setPower(0);
                }
            }


            /**     Front arm control   */


            if (gamepad1.left_bumper) {
                armMotor.setPower(-0.3);
            } else
                armMotor.setPower(0);

            if (gamepad1.right_bumper) {
                armMotor.setPower(0.3);

            } else
                armMotor.setPower(0);

            if (gamepad1.right_trigger != 0) {
                liftPower = 0.35;
                lift.setPower(liftPower);
            }

            if (gamepad1.left_trigger != 0) {
                liftPower = -0.1;
                lift.setPower(liftPower);
            }

            if (gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0)
                lift.setPower(0);

            if (gamepad1.x) {
                liftPower = 0;
                lift.setPower(0);
            }

            telemetry.addLine()
                     .addData("Status", "Initialized")
                     .addData("armMotor power:", armMotor.getPower())
                     .addData("lift power", lift.getPower())
                     .addData("frontLeftDrive position: ", robot.frontLeftDrive.getCurrentPosition())
                     .addData("frontRightDrive position: ", robot.frontRightDrive
                             .getCurrentPosition())
                     .addData("backLeftDrive position: ", robot.backLeftDrive.getCurrentPosition())
                     .addData("backRightDrive position: ", robot.backRightDrive
                             .getCurrentPosition());
            telemetry.update();
        }
    }
}





