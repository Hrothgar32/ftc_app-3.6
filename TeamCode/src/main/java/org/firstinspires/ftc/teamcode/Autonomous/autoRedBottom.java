package org.firstinspires.ftc.teamcode.Autonomous;

 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
 import com.qualcomm.robotcore.eventloop.opmode.Disabled;
 import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.robot.Robot;

        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import org.firstinspires.ftc.teamcode.libs.RobotInit;
/**
 * Created by Vsbi on 3/23/2018.
 */

@Autonomous(name = "Red bottom", group = "final")
@Disabled
public class autoRedBottom extends LinearOpMode {
    private RobotInit robot;
    private String vuMark;

    static final double newrest = 2750;
    static final double tetrix = 2136;

    static final double driveSpeed = 0.5;
    static final double turnSpeed = 0.4;

    public void runOpMode(){
        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
        robot.armMotor.setPower(0.3);
        robot.armServo.setPosition(1);
        //robot.lift.setPower(0.40);
        robot.sleep(500);

        int sig = 0;
            if(robot.auto.readJewelColor() == true){
                sig = 1;
            }
            else {
                sig = -1;
            }
        drive(0.2 * sig , driveSpeed);
        robot.sleep(1000);
        robot.armServo.setPosition(0.3);
        drive(-sig * 0.2, driveSpeed);
        drive(0.2, driveSpeed);
        vuMark = robot.vufModul.identifyVuMark();
        drive(-0.3, driveSpeed);
        drive(-0.7, driveSpeed);
        turn(90, 2, 0.10, 0.20, 3);
        drive(0.5, driveSpeed);
        turn(-90, 2, 0.10, 0.20, 3);
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
            robot.backLeftDrive.setTargetPosition(-newresttarget);
            robot.backRightDrive.setTargetPosition(-newresttarget);

            robot.setMotorPower(0.4, "Forward");

            while(opModeIsActive() && Math.abs(robot.backRightDrive.getCurrentPosition()) <
                    Math.abs(newresttarget) -
                    20){
                telemetry.addLine("Moving");
                telemetry.update();
                if(!isStopRequested())
                    break;
            }
            telemetry.addLine("done");
            telemetry.update();
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
            if (vuMark.equals("left")) {
                turn(90, 1, 0.10, 0.30, 3);

                drive(0.5, driveSpeed);
                turn(-90, 1, 0.10, 0.30, 3);
                drive(0.8, driveSpeed);

            }
            if (vuMark.equals("center")) {
                drive(0.8, driveSpeed);
            }
            if (vuMark.equals("right")) {
                turn(-90, 1, 0.10, 0.30, 3);
                drive(0.5, driveSpeed);
                turn(90, 1, 0.10, 0.30, 3);
                drive(0.8, driveSpeed);
            }
        }
    }

}
