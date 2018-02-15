import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


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

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor armMotor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        backLeftDrive  = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
     //   armMotor = hardwareMap.get(DcMotor.class,"armMotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses Sfront)
        while (opModeIsActive()) {
            backLeftDrive.setPower(gamepad1.right_stick_y);
            backRightDrive.setPower(gamepad1.right_stick_y);
            frontLeftDrive.setPower(gamepad1.right_stick_y);
            frontRightDrive.setPower(gamepad1.right_stick_y);

            //telemetry.addData("Power",gamepad1.right_stick_y );
            //telemetry.update();
            if(gamepad1.right_stick_x > 0){
                backRightDrive.setPower(gamepad1.right_stick_x);
                backLeftDrive.setPower(gamepad1.right_stick_x);
                frontLeftDrive.setPower(gamepad1.right_stick_x);
                frontRightDrive.setPower(gamepad1.right_stick_x);

            }
            if(gamepad1.right_stick_x < 0){
                backRightDrive.setPower(gamepad1.right_stick_x);
                backLeftDrive.setPower(gamepad1.right_stick_x);
                frontLeftDrive.setPower(gamepad1.right_stick_x);
                frontRightDrive.setPower(gamepad1.right_stick_x);

            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
