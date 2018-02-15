package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by Vsbi on 2/14/2018.
 *
 * * The sole purpose of this code is testing therefor IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Autonomous(name = "Encoder test", group = "test")
public class EncoderTest extends LinearOpMode {
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;
    public DcMotor backLeftDrive = null;
    private double power = 0.4;
    public DcMotor backRightDrive = null;

    @Override
    public void runOpMode() {

        telemetry.addLine("1");


        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addLine()
                .addData("backLeft position", backLeftDrive.getCurrentPosition())
                .addData("backRight position", backRightDrive.getCurrentPosition())
                .addData("frontLeft position", frontLeftDrive.getCurrentPosition())
                .addData("frontRight position", frontRightDrive.getCurrentPosition());
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("2");
        backLeftDrive.setTargetPosition(1440);
        backRightDrive.setTargetPosition(1440);
        frontLeftDrive.setTargetPosition(1440);
        frontRightDrive.setTargetPosition(1440);
        frontRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addLine("3");
        waitForStart();
        while (opModeIsActive()) {
            backLeftDrive.setTargetPosition(1440);
            backRightDrive.setTargetPosition(1440);
            frontLeftDrive.setTargetPosition(1440);
            frontRightDrive.setTargetPosition(1440);
            telemetry.addLine()
                    .addData("backLeft position", backLeftDrive.getCurrentPosition())
                    .addData("backRight position   }\n", backRightDrive.getCurrentPosition())
                    .addData("frontLeft position", frontLeftDrive.getCurrentPosition())
                    .addData("frontRight position", frontRightDrive.getCurrentPosition());
        }
    }
}
