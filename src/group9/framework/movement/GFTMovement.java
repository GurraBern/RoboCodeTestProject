package group9.framework.movement;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import group9.framework.util.CalcUtils;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GFTMovement implements IMoveable{
    private static final double BATTLE_FIELD_WIDTH = 800;
    private static final double BATTLE_FIELD_HEIGHT = 600;
    private static final double WALL_MARGIN = 18;
    private static final double MAX_TRIES = 125;
    private static final double REVERSE_TUNER = 0.421075;
    private static final double DEFAULT_EVASION = 1.2;
    private static final double WALL_BOUNCE_TUNER = 0.699484;

    private AdvancedRobot robot;
    private Rectangle2D fieldRectangle = new Rectangle2D.Double(WALL_MARGIN, WALL_MARGIN,
            BATTLE_FIELD_WIDTH - WALL_MARGIN * 2, BATTLE_FIELD_HEIGHT - WALL_MARGIN * 2);
    private double enemyFirePower = 3;
    private double direction = 0.4;

    public GFTMovement(AdvancedRobot _robot) {
        this.robot = _robot;
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyAbsoluteBearing = robot.getHeadingRadians() + e.getBearingRadians();
        double enemyDistance = e.getDistance();
        Point2D robotLocation = new Point2D.Double(robot.getX(), robot.getY());
        Point2D enemyLocation = CalcUtils.project(robotLocation, enemyAbsoluteBearing, enemyDistance);
        Point2D robotDestination;
        double tries = 0;
        while (!fieldRectangle.contains(robotDestination = CalcUtils.project(enemyLocation, enemyAbsoluteBearing + Math.PI + direction,
                enemyDistance * (DEFAULT_EVASION - tries / 100.0))) && tries < MAX_TRIES) {
            tries++;
        }
        if ((Math.random() < (CalcUtils.bulletVelocity(enemyFirePower) / REVERSE_TUNER) / enemyDistance ||
                tries > (enemyDistance / CalcUtils.bulletVelocity(enemyFirePower) / WALL_BOUNCE_TUNER))) {
            direction = -direction;
        }
        // Jamougha's cool way
        double angle = CalcUtils.absoluteBearing(robotLocation, robotDestination) - robot.getHeadingRadians();
        robot.setAhead(Math.cos(angle) * 100);
        robot.setTurnRightRadians(Math.tan(angle));
    }
}