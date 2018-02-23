package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Vsbi on 2/13/2018.
 */

//@TeleOp(name = "IntakeTest", group = "Test")
public class IntakeTest extends LinearOpMode {
    private DcMotor lift = null;
    private DcMotor armMotor = null;
    private double liftPower = 0;
    @Override
    public void runOpMode(){
        lift = hardwareMap.get(DcMotor.class, "lift");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        waitForStart();
        armMotor.setPower(0);
        lift.setPower(0);
        while(opModeIsActive()){
            if(gamepad2.left_bumper){
                if(armMotor.getPower() == 0)
                armMotor.setPower(-0.1);
                else
                    armMotor.setPower(0);
            }
            if(gamepad2.right_bumper){
                if(armMotor.getPower() == 0)
                armMotor.setPower(0.15);
                else
                    armMotor.setPower(0);
            }
            if(gamepad2.right_stick_y != 0){
                liftPower += gamepad2.right_stick_y / 1000;
                if(liftPower > 0.40)
                    liftPower = 0.4;
                if(liftPower < -0.10)
                    liftPower = -0.10;
                lift.setPower(liftPower);
            }
            if(gamepad2.x){
                liftPower = 0;
                    lift.setPower(0);
            }
            telemetry.addLine()
                    .addData("armMotor power:", armMotor.getPower())
                    .addData("lift power", lift.getPower());
            telemetry.update();
        }
    }
}
