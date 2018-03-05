package org.firstinspires.ftc.teamcode.libs;



//imports for gyroscope
import com.qualcomm.hardware.bosch.BNO055IMU;
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


//imports for Vufoira
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import static java.lang.Math.abs;

/**
 * Created by Vsbi on 2/12/2018. (RoboCorp RO084)
 *
 * Copyright (c) 2018 RoboCorp
        *  * Permission is hereby granted, free of charge, to any person obtaining a copy
        * of this software and associated documentation files (the "Software"), to deal
        * in the Software without restriction, including without limitation the rights
        * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        * copies of the Software, and to permit persons to whom the Software is
        * furnished to do so, subject to the followin   g conditions:
        *
        * The above copyright notice and this permission notice shall be included in all
        * copies or substantial portions of the Software.
        *
        * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
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

    public DcMotor armMotor = null;
    public DcMotor lift = null;
    private double motorPower;
    HardwareMap hwMap = null;


    public RoboVuforia vufModul = null;
    public Gyroscope gyro = null;



    public void init(HardwareMap ahwMap, boolean isAuto) {
        hwMap = ahwMap;
        frontLeftDrive = hwMap.dcMotor.get("frontLeftDrive");
        frontRightDrive = hwMap.dcMotor.get("frontRightDrive");
        backLeftDrive = hwMap.dcMotor.get("backLeftDrive");
        backRightDrive = hwMap.dcMotor.get("backRightDrive");
        armMotor = hwMap.dcMotor.get("armMotor");
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
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armServo.setPosition(0);
        if (isAuto) {
            vufModul = new RoboVuforia();
            gyro = new Gyroscope(hwMap);
        }
    }

    public void setEncoderBlocks(int numOfBlocks, String direction){
        final int tetrix = 2750;
        final int newrest = 2136;

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
                frontLeftDrive.setTargetPosition(tetrix*numOfBlocks);
                frontRightDrive.setTargetPosition(tetrix*numOfBlocks);
                backLeftDrive.setTargetPosition(-newrest*numOfBlocks);
                backRightDrive.setTargetPosition(-newrest*numOfBlocks);
                setMotorPower(0.5,"Straight");
                break;
            case "Backward":
                frontLeftDrive.setTargetPosition(-tetrix*numOfBlocks);
                frontRightDrive.setTargetPosition(-tetrix*numOfBlocks);
                backRightDrive.setTargetPosition(newrest*numOfBlocks);
                backLeftDrive.setTargetPosition(newrest*numOfBlocks);
                setMotorPower(-0.5,"Straight");
                break;
            case "Right":
                frontRightDrive.setTargetPosition(tetrix*numOfBlocks);
                frontLeftDrive.setTargetPosition(-tetrix*numOfBlocks);
                backLeftDrive.setTargetPosition(-newrest*numOfBlocks);
                backRightDrive.setTargetPosition(newrest*numOfBlocks);
                setMotorPower(0.5,"Rotation");
                break;
            case"Left":
                frontRightDrive.setTargetPosition(-tetrix*numOfBlocks);
                frontLeftDrive.setTargetPosition(tetrix*numOfBlocks);
                backLeftDrive.setTargetPosition(newrest*numOfBlocks);
                backRightDrive.setTargetPosition(-newrest*numOfBlocks);
                setMotorPower(-0.5,"Rotation");
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
                frontLeftDrive.setPower(power);
                backRightDrive.setPower(power);
                backLeftDrive.setPower(power);
        }
    }

    private void stopMotors(){
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
            while(true) {
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
            }
        }
    }

    /*
    * @param target the angle to reach
    * @param range the acceptable  fail range of the angle it's around 1 or 2 degrees
    * @param minSpeed the minimal speed to give to the motors
    * @param addSpeed the speed to give to speed up the turning process
    * @param steps the number of times you want to check if the angle is in range
     */


    public void turn(double target, double range, double minSpeed, double addSpeed, int steps) {
        int count = 0;
        double current = gyro.getAngle();
        double delta = (target - current + 360.0) % 360.0;
        if (delta > 180.0)
            delta -= 360.0;
        while(count != steps) {
            if (Math.abs(delta) > range) {
                double gyroMod = delta / 45.0;
                if (Math.abs(gyroMod) > 1.0)
                    gyroMod = Math.signum(gyroMod);
                this.setMotorPower(minSpeed * Math.signum(gyroMod) + addSpeed * gyroMod, "Rotation");
            } else {
                count++;
                this.stopMotors();
            }
        }
    }
}