package group9.framework;

public class SpinningRadar implements Radar{
    @Override
    public void Spin(SPLRobot robot) {
        robot.turnRadarRightRadians(Double.POSITIVE_INFINITY);
    }
}
