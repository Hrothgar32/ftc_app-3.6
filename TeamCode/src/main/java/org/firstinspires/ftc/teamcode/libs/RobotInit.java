package org.firstinspires.ftc.teamcode.libs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

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
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;
    public DcMotor backLeftDrive = null;
    public DcMotor backRightDrive = null;
    public NormalizedColorSensor armSensor = null;
    public Servo armServo = null;
    public RoboVuforia vufModul = null;
    public DcMotor armMotor = null;
    public DcMotor lift = null;
    private double motorPower;
    public DriveMode driveMode = null;
    HardwareMap hwMap = null;


    public void setMotorPower(double power){
        motorPower = power;
        frontRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backRightDrive.setPower(power);
        backLeftDrive.setPower(power);
    }

    public void init(HardwareMap ahwMap, boolean isAuto){
        hwMap = ahwMap;
        driveMode = DriveMode.TANKDRIVE;
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
        if(isAuto)
            vufModul = new RoboVuforia();
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

}