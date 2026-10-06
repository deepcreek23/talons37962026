package org.firstinspires.ftc.teamcode.opModes.autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.packages.mecanumDrive;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class blehue extends OpMode {

    mecanumDrive drive = new mecanumDrive();

    private Follower follower;
    private Timer pathTimer, opModeTimer;

    private final Pose startPose = new Pose(
            60,
            9,
            Math.toRadians(90)
    );

    public enum PathState {
        START,
        DONE
    }

    PathState pathState;

    public void buildPaths() {

        // Build paths here

    }

    public void statePathUpdate() {

        switch (pathState) {

            case START:

                // Start first path here

                setPathState(PathState.DONE);
                break;

            case DONE:

                // Autonomous finished

                break;
        }
    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.reset();
    }

    @Override
    public void init() {

        drive.init(hardwareMap);

        pathTimer = new Timer();
        opModeTimer = new Timer();

        opModeTimer.reset();

        follower = Constants.create(hardwareMap);

        assert follower != null;
        follower.setPose(startPose);

        pathState = PathState.START;

        buildPaths();
    }

    @Override
    public void start() {

        opModeTimer.reset();

        setPathState(PathState.START);
    }

    @Override
    public void loop() {

        follower.update();

        if (!follower.isBusy()) {
            statePathUpdate();
        }

        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", follower.pose().heading());
        telemetry.addData("Path Time", pathTimer.seconds());

        telemetry.update();
    }
}