package org.firstinspires.ftc.teamcode.opModes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.packages.mecanumDrive;

@TeleOp
public class teleOPv1 extends OpMode {

    mecanumDrive drive = new mecanumDrive();
    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;
        drive.drive(forward, strafe, rotate);
    }
}
