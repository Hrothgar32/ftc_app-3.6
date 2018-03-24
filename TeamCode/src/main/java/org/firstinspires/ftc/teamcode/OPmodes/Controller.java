package org.firstinspires.ftc.teamcode.OPmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.RobotInit;

import static java.lang.Math.abs;


/** Created by Rodnan 2/14/2018.
 *
 * This code is made to control the robot with a controller. It has been edited several times,
 * this is the final, most optimal code on the date of 2/16/2018.
 *
 * This code moves the main arm, the lift, the relic arm and the wheels of the robot.
 *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@TeleOp(name="Controller", group="Linear Opmode")
public class Controller extends LinearOpMode {

    private RobotInit robot = null;
    private DcMotor lift = null;
    private DcMotor armMotor = null;
    private double liftPower = 0;

    @Override
    public void  runOpMode() {


        lift = hardwareMap.get(DcMotor.class, "lift");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        robot = new RobotInit();
        robot.init(hardwareMap, false);

        waitForStart();
        robot.armServo.setPosition(0.3);
        armMotor.setPower(0);
        lift.setPower(0);
        double rCS = 0.17;
        robot.relClawServo.setPosition(rCS);


        while (opModeIsActive()) {



            /**---------------------------------Drive control-------------------------------------*/

            if (gamepad1.left_stick_y >= 0.51 || gamepad1.left_stick_y <= -0.51) {

                if (gamepad1.left_stick_y <= 0.8 && gamepad1.left_stick_y>= -0.8){
                    robot.backLeftDrive.setPower(0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.backRightDrive.setPower(0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.frontLeftDrive.setPower(-0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.frontRightDrive.setPower(-0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }

                if(gamepad1.left_stick_y > 0.8 || gamepad1.left_stick_y < -0.8){
                    robot.backLeftDrive.setPower(0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.backRightDrive.setPower(0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.frontLeftDrive.setPower(-0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.frontRightDrive.setPower(-0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }
            }

            else {

                if (gamepad1.left_stick_x >= 0.52 || gamepad1.left_stick_x <= -0.52) {

                    /*/robot.backLeftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.backRightDrive.setPower(-gamepad1.left_stick_x/2);
                    robot.frontLeftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.frontRightDrive.setPower(-gamepad1.left_stick_x/2);/*/

                    if (gamepad1.left_stick_x <= 0.8 && gamepad1.left_stick_x >= -0.8){
                        robot.backLeftDrive.setPower(0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.backRightDrive.setPower(-0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.frontLeftDrive.setPower(0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.frontRightDrive.setPower(-0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                    if (gamepad1.left_stick_x > 0.8 || gamepad1.left_stick_x < -0.8){
                        robot.backLeftDrive.setPower(0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.backRightDrive.setPower(-0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.frontLeftDrive.setPower(0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.frontRightDrive.setPower(-0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                }
                else{
                    robot.backLeftDrive.setPower(0);
                    robot.backRightDrive.setPower(0);
                    robot.frontLeftDrive.setPower(0);
                    robot.frontRightDrive.setPower(0);
                }
            }


            /**------------------------Front arm control-----------------------------*/


            if (gamepad1.left_bumper) {
                armMotor.setPower(-0.3);
            } else
                armMotor.setPower(0);

            if (gamepad1.right_bumper) {
                armMotor.setPower(0.4);

            } else
                armMotor.setPower(0);

            /**-----------------------------Lift control----------------------------*/

            if (gamepad1.right_trigger != 0) {
                liftPower = 0.6;
                lift.setPower(-liftPower);
            }

            if (gamepad1.left_trigger != 0) {
                liftPower = -0.1;
                lift.setPower(-liftPower);
            }


            if (gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0)
                lift.setPower(0);

            if (gamepad1.x) {
                liftPower = 0;
                lift.setPower(0);
            }



            /**----------------------------RelicArm Control--------------------------*/


            if(gamepad2.dpad_up){
                rCS += 0.025;
                robot.relClawServo.setPosition(rCS);
            }


            if(gamepad2.dpad_down && (rCS - 0.025) > 0.17) {
                rCS -= 0.025;
                robot.relClawServo.setPosition(rCS);
            }

            if(gamepad2.right_trigger != 0)
                robot.relServo.setPosition(1);

            if(gamepad2.left_trigger != 0)
                robot.relServo.setPosition(0.58);

            if(gamepad2.x){
                robot.relServo.setPosition(0.66);
            }


            if(gamepad2.right_stick_y > 0.5)
                robot.relicMotor.setPower(0.8);

            if(gamepad2.right_stick_y < -0.5)
                robot.relicMotor.setPower(-0.3);

            else if (gamepad2.right_stick_y < 0.5 && gamepad2.right_stick_y > - 0.5)
                robot.relicMotor.setPower(0);




            /**-------------------------------Telemetry----------------------------*/
            telemetry.addLine()
                     .addData("Status", "Initialized")
                     .addData("armMotor power:", armMotor.getPower())
                     .addData("lift power", lift.getPower())
                     .addData("frontLeftDrive position: ", robot.frontLeftDrive.getCurrentPosition())
                     .addData("frontRightDrive position: ", robot.frontRightDrive
                             .getCurrentPosition())
                     .addData("backLeftDrive position: ", robot.backLeftDrive.getCurrentPosition())
                     .addData("backRightDrive position: ", robot.backRightDrive
                             .getCurrentPosition());
            telemetry.update();
        }
    }
}





