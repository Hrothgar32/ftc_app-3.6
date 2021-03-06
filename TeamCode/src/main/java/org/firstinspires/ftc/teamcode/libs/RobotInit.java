package org.firstinspires.ftc.teamcode.libs;



//imports for gyroscope
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


import org.firstinspires.ftc.teamcode.libs.Gyroscope;


//imports for other hardware
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.libs.AutoLib;
import org.firstinspires.ftc.teamcode.libs.Gyroscope;


//imports for Vufoira
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Vsbi on 2/12/2018. (RoboCorp RO084)
 *
 * Copyright (c) 2018 RoboCorp
        *  * Permission is hereby granted, free of charge, to any person obtaining a copy
        * of this software and associated documentation files (the "Software"), to deal
        * in the Software without restriction, including without limitation the rights
        * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        * copies of the Software, and to permit persons to whom the Software is
        * furnished to do so, subject to the following conditions:
        *
        * The above copyright notice and this permission notice shall be included in all
        * copies or substantial portions of the Software.
        *
        * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
        * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        * SOFTWARE.
        */


public class RobotInit{
    private Gyroscope GyroData = null;
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;
    public DcMotor backLeftDrive = null;
    public DcMotor backRightDrive = null;
    public NormalizedColorSensor armSensor = null;
    public Servo armServo = null;

    public DcMotor relicMotor;
    public Servo relServo = null;
    public Servo relClawServo = null;

    public DcMotor armMotor = null;
    public DcMotor lift = null;
    public DcMotor jewelMotor = null;
    private double motorPower;
    HardwareMap hwMap = null;


    Telemetry telemetry = null;

    public RoboVuforia vufModul = null;
    public Gyroscope gyro = null;
    public AutoLib auto = null;


    public void init(HardwareMap ahwMap, boolean isAuto) {
        hwMap = ahwMap;
        frontLeftDrive = hwMap.dcMotor.get("frontLeftDrive");
        frontRightDrive = hwMap.dcMotor.get("frontRightDrive");
        backLeftDrive = hwMap.dcMotor.get("backLeftDrive");
        backRightDrive = hwMap.dcMotor.get("backRightDrive");
        armMotor = hwMap.dcMotor.get("armMotor");
        relicMotor = hwMap.dcMotor.get("relicMotor");
        relServo = hwMap.servo.get("relServo");
        relClawServo = hwMap.servo.get("relClawServo");
        armSensor = hwMap.get(NormalizedColorSensor.class, "armSensor");
        armServo = hwMap.servo.get("armServo");
        lift = hwMap.dcMotor.get("lift");

        frontRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);


        if (isAuto) {
            vufModul = new RoboVuforia();
            gyro = new Gyroscope();
            auto = new AutoLib();
            gyro.init(hwMap);
            auto.init(hwMap);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void setEncoderBlocks(float a, String direction){
        final int tetrix = 2750;
        final int newrest = 2136;
        int numBlocksTetrix = Math.round(tetrix * a);
        int numBlocksNewrest = Math.round(newrest * a);

        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        switch(direction){
            case "Forward":
                frontLeftDrive.setTargetPosition(numBlocksTetrix);
                frontRightDrive.setTargetPosition(numBlocksTetrix);
                backLeftDrive.setTargetPosition(-numBlocksNewrest);
                backRightDrive.setTargetPosition(-numBlocksNewrest);
                setMotorPower(0.3,"Straight");
                while (frontRightDrive.getCurrentPosition() < 2750*a) {
                }
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            case "Backward":
                frontLeftDrive.setTargetPosition(-numBlocksTetrix);
                frontRightDrive.setTargetPosition(-numBlocksTetrix);
                backRightDrive.setTargetPosition(numBlocksNewrest);
                backLeftDrive.setTargetPosition(numBlocksNewrest);
                setMotorPower(0.3,"Straight");
                while (frontRightDrive.getCurrentPosition() > -2750*a) {
                }
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
        }
    }

    public void setMotorPower(double power, String direction){

        switch (direction){

            case "Straight":
                frontRightDrive.setPower(power);
                frontLeftDrive.setPower(power);
                backRightDrive.setPower(power);
                backLeftDrive.setPower(power);
            case "Rotation":
                frontRightDrive.setPower(power);
                frontLeftDrive.setPower(-power);
                backRightDrive.setPower(power);
                backLeftDrive.setPower(-power);
        }
    }

    public void setMotorPowerCtrClcw(double power, String direction){

        switch (direction){

            case "Straight":
                frontRightDrive.setPower(power);
                frontLeftDrive.setPower(power);
                backRightDrive.setPower(power);
                backLeftDrive.setPower(power);
            case "Rotation":
                frontRightDrive.setPower(-power);
                frontLeftDrive.setPower(power);
                backRightDrive.setPower(-power);
                backLeftDrive.setPower(power);
        }
    }


    public void stopMotors(){
        frontRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
    }


    public void sleep(int milsec){
        try {
            Thread.sleep(milsec);
        } catch (InterruptedException ex) {

        }
    }


    public class RoboVuforia {
        public static final String TAG = "Vuforia VuMark Sample";
        private VuforiaLocalizer vuforia = null;
        private VuforiaTrackables relicTrackables = null;
        private VuforiaTrackable relicTemplate = null;
        private int steps = 0;
        public RoboVuforia() {
            int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            String vufo = "AX6TvX3/////AAAAme1lQHFynkbdlSTlhf4eut5N0mWxBDe/ufwa7KQerU+hD7vx8RPtAsWGmx3pBM3cJkLf3qQedXpj2j9nYGnDx6hA1mpKJlzIxAN1jkC4hmMkNZEHrnai5jf0cIWLmCPMUuxF6QCgCgUwZAVYTfkrZa7k/ocgcM1P4CfMfFbzI/qxsNqc2xhd1sGWlH9bKQoacSLKzzE0mrlxwKMYAimju4Pez73i68R2tFXh4GQnnHfMjJk+8qWeDNn03y0obTD2S0IeaYPsNf8TUASkTyCVLYbzI0WyRWvy0Tq5TMN3GE6wrnGt9PnsXji3HuhX6pBKikOXYjMzgrVIyO/L8+UAexRZfzbq/T83GI5LjEMx/ro3";
            parameters.vuforiaLicenseKey = vufo;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
            relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
            relicTemplate = relicTrackables.get(0);
            relicTemplate.setName("relicVuMarkTemplate");
            relicTrackables.activate();
        }
        public String identifyVuMark(){

                RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    return "center";
                }
                else if(vuMark == RelicRecoveryVuMark.LEFT){
                    return "left";
                }
                else if(vuMark == RelicRecoveryVuMark.RIGHT){
                    return "right";
                }
            return "center";
        }
    }

    /*
    * @param target the angle to reach
    * @param range the acceptable  fail range of the angle it's around 1 or 2 degrees
    * @param minSpeed the minimal speed to give to the motors
    * @param addSpeed the speed to give to speed up the turning process
    * @param steps the number of times you want to check if the angle is in range
     */

    public void turn(int target, int range, double minSpeed, double addSpeed, Telemetry
            telemetry, int steps) {
        if (target < 0) {
                this.turnClcw(Math.abs(target), range, minSpeed, addSpeed, telemetry, 3);
        } else {
            int current = gyro.getAngle();
            telemetry.addData("fasz", current);
            telemetry.update();
            int count = 0;
            while (count != steps) {
                if (!isok(current, target, range)) {
                    current = gyro.getAngle();
                    telemetry.addData("szog", current);

                    int mod = target - current;
                    mod /= 30;
                    telemetry.addData("mod", mod);
                    telemetry.update();
                    this.setMotorPower(minSpeed + addSpeed * Math.abs(mod), "Rotation");
                } else
                    count++;

            }
            this.stopMotors();
            gyro.reset();
        }
    }
    public void turnClcw(int target, int range, double minSpeed, double addSpeed, Telemetry
            telemetry, int steps) {
        int current = gyro.getAngle();
        int count = 0;
        while (count != steps) {
            if (!isok(current, target, range)) {
                current = gyro.getAngle();
                telemetry.addData("szog", current);
                int mod = target - current;
                mod /= 30;
                telemetry.addData("mod", mod);
                telemetry.update();
                this.setMotorPowerCtrClcw(minSpeed + addSpeed * Math.abs(mod), "Rotation");
            } else
                count++;
        }
        this.stopMotors();
        gyro.reset();
    }
    private boolean isok(int a, int b, int c){
        return ((a >= b - c && a <= b + c));
    }
}