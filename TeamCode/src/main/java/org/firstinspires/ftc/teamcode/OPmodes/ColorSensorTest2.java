package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
/**
 * Copyright (c) RoboCorp 2018
 *
 * Created by Vsbi on 2/14/2018.
 *
 *
 * The sole purpose of this code is testing therefor IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
  */
@TeleOp(name = "ColorSensorTest2", group = "test")
public class ColorSensorTest2 extends LinearOpMode{
    ColorSensor color_sensor;
    boolean ison = false;
    @Override
    public void runOpMode(){
        color_sensor = hardwareMap.colorSensor.get("test");
        color_sensor.enableLed(false);


        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.x) {
                ison = !ison;
                color_sensor.enableLed(ison);
            }
            telemetry.addLine()
                    .addData("red:", color_sensor.red())
                    .addData("green: ", color_sensor.green())
                    .addData("blue: ", color_sensor.blue())
                    .addData("alpha: ", color_sensor.alpha());
            telemetry.update();
        }
    }

}
