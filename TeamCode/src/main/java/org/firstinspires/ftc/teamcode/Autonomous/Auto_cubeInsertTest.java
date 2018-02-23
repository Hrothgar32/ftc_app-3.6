package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by Vsbi on 2/22/2018.
 *
 * * * The sole purpose of this code is testing therefor IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */



@Autonomous(name = "CubeTest", group = "test")
public class Auto_cubeInsertTest extends LinearOpMode {
    private double power = 0.2;
    private RobotInit robot = null;

    @Override
    public void runOpMode(){
        robot = new RobotInit();
        robot.init(hardwareMap, true);
        waitForStart();
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

        robot.armMotor.setPower(0.3);
        robot.sleep(50);
        robot.lift.setPower(0.4);

        robot.sleep(50);
        robot.lift.setPower(0);

        robot.setMotorPower(0.3, "Straight");


        robot.sleep(2000);
        robot.armMotor.setPower(0);

        robot.backLeftDrive.setTargetPosition(0);
        robot.backRightDrive.setTargetPosition(0);
        robot.frontLeftDrive.setTargetPosition(0);
        robot.frontRightDrive.setTargetPosition(0);


        telemetry.addLine("Done");
    }
}