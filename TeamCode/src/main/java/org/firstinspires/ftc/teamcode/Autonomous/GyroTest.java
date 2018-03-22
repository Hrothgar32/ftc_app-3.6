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

@Autonomous(name = "Gyro Test", group = "Linear Opmode")
public class GyroTest extends LinearOpMode {

    private RobotInit robot = null;

    @Override
    public void runOpMode() {
        robot = new RobotInit();
        robot.init(hardwareMap, true);
      //  Gyroscope GyroData = new Gyroscope(hardwareMap);

        telemetry.addData("Angle", robot.gyro.getAngle());
        robot.sleep(5000);
        telemetry.update();
        robot.turn(90,5, 0.15, 0.1, telemetry, 3 );
        robot.sleep(5000);
        telemetry.addLine("finished");
        telemetry.update();
        robot.turn(-90, 5, 0.15, 0.1,telemetry, 3);
        }
    }

