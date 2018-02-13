package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Vsbi on 2/13/2018.
 */

@TeleOp(name = "IntakeTest", group = "Linear Opmode")
public class IntakeTest extends LinearOpMode {
    private DcMotor intake = null;
    double power = 0;
    @Override
    public void runOpMode(){
        intake = hardwareMap.get(DcMotor.class, "intake");
        waitForStart();

        while(opModeIsActive()){
            while(gamepad1.right_trigger != 0 && power < 1)  {
                power += gamepad1.right_trigger / 100;
                telemetry.addData("power:", power);
                intake.setPower(power);
            }
            while(gamepad1.left_trigger != 0 && power > -1 ) {
                power -= gamepad1.left_trigger / 100;
                telemetry.addData("power:", power);
                intake.setPower(power);
            }

            intake.setPower(0);
            telemetry.addData("power:", power);
            telemetry.update();
        }
    }
}
