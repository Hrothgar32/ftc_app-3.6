package org.firstinspires.ftc.teamcode.Autonomous;


import org.firstinspires.ftc.teamcode.libs.RobotInit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
    @Disabled
    public class EncoderTest extends LinearOpMode {

        private double power = 0.5;
        private RobotInit robot = null;

    @Override
    public void runOpMode() {
        telemetry.addLine(  "1");
        robot = new RobotInit();
        robot.init(hardwareMap, true);

        telemetry.addLine()
                .addData("backLeft position", robot.backLeftDrive.getCurrentPosition())
                .addData("backRight position", robot.backRightDrive.getCurrentPosition())
                .addData("frontLeft position", robot.frontLeftDrive.getCurrentPosition())
                .addData("frontRight position", robot.frontRightDrive.getCurrentPosition());
        telemetry.update();

        //reseting encoders
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        robot.backLeftDrive.setTargetPosition(-2136);
        robot.backRightDrive.setTargetPosition(-2136);
        robot.frontLeftDrive.setTargetPosition(2750);
        robot.frontRightDrive.setTargetPosition(2750);

        robot.setMotorPower(power, "Straight");
        while (robot.frontRightDrive.getCurrentPosition() < 2700) {
            telemetry.addLine()
                    .addData("backLeft position", robot.backLeftDrive.getCurrentPosition())
                    .addData("backRight position", robot.backRightDrive.getCurrentPosition())
                    .addData("frontLeft position", robot.frontLeftDrive.getCurrentPosition())
                    .addData("frontRight position", robot.frontRightDrive.getCurrentPosition());
            telemetry.update();
        }
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftDrive.setTargetPosition(-1120);
        robot.backRightDrive.setTargetPosition(1120);
        robot.frontLeftDrive.setTargetPosition(-1440);
        robot.frontRightDrive.setTargetPosition(1440);
    }


}


