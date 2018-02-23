package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.libs.Gyroscope;
import org.firstinspires.ftc.teamcode.libs.RobotInit;

/**
 * Created by Rodnan on 2/22/2018.
 *
 *  * The sole purpose of this code is testing therefor IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Autonomous(name = "Gyro Test", group = "Linera Opmode")
public class GyroTest extends LinearOpMode {

    private RobotInit robot;

    @Override
    public void runOpMode() throws InterruptedException {

        Gyroscope GyroData = new Gyroscope(hardwareMap);
        float TurnAngle = GyroData.getAngle();

        telemetry.addData("Angle", GyroData.getAngle());

        waitForStart();


        while (opModeIsActive()){
            telemetry.addData("Angle", GyroData.getAngle());
            TurnAngle = GyroData.getAngle();
            telemetry.update();
           /* while (TurnAngle != 90){

                robot.setMotorPower(1,"Rotation");
            }*/

        }
    }
}
