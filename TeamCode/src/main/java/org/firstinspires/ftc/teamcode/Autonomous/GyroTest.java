package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libs.Gyroscope;
import org.firstinspires.ftc.teamcode.libs.RobotInit;
import static java.lang.Math.abs;

/**
 * Created by Rodnan on 2/22/2018.
 *
 *  * The sole purpose of this code is testing therefor IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Autonomous(name = "Gyro Test", group = "Test")
public class GyroTest extends LinearOpMode {

    private RobotInit robot = null;

    @Override
    public void runOpMode() {
        robot = new RobotInit();
        robot.init(hardwareMap, false);
        Gyroscope GyroData = new Gyroscope(hardwareMap);
        float TurnAngle = GyroData.getAngle();

        telemetry.addData("Angle", GyroData.getAngle());
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Angle", GyroData.getAngle());
            TurnAngle = GyroData.getAngle();
            if (TurnAngle < 90) {
                TurnAngle = GyroData.getAngle();
                telemetry.update();
                robot.setMotorPower(-0.2, "Rotation");
            }
            if (TurnAngle >= 90) robot.setMotorPower(0, "Straight");
        }
    }
}
