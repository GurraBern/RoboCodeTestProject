import Mocks.AdvancedRobotMock;
import group9.framework.movement.TrueSurfing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robocode.Bullet;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.geom.Point2D;


public class TrueSurfingTest {
    AdvancedRobotMock robot;
    TrueSurfing sut;

    @BeforeEach
    public void setup() {
        robot = new AdvancedRobotMock();
        sut = new TrueSurfing(robot);


    }

    @AfterEach
    public  void teardown(){
        TrueSurfing._oppEnergy = 100;
    }

    @Test
    void onHitByBullet_ShouldEliminateEnemyWaveFromEnemyWaves(){

        //Arrange
        Bullet bullet = new Bullet(1,400,300,2,"owner", "victim", true, 0);
        HitByBulletEvent bulletEvent = new HitByBulletEvent(1, bullet);

        ScannedRobotEvent robotEvent = new ScannedRobotEvent();
        sut.onScannedRobot(robotEvent);

        TrueSurfing.EnemyWave wave = new TrueSurfing.EnemyWave();
        wave.fireLocation = new Point2D.Double(1.0, 2.0);
        wave.distanceTraveled = 500;
        wave.bulletVelocity = 14;
        sut._enemyWaves.add(wave);

        //Act
        sut.onHitByBullet(bulletEvent);

        //Assert
        Assertions.assertEquals(0, sut._enemyWaves.size());
    }

    @Test
    void onHitByBullet_ShouldNotEliminateEnemyWaveFromEnemyWaves_WhenWaveValuesAreMissing(){

        //Arrange
        Bullet bullet = new Bullet(1,400,300,2,"owner", "victim", true, 0);
        HitByBulletEvent bulletEvent = new HitByBulletEvent(1, bullet);

        ScannedRobotEvent robotEvent = new ScannedRobotEvent();
        sut.onScannedRobot(robotEvent);

        TrueSurfing.EnemyWave wave = new TrueSurfing.EnemyWave();
        wave.fireLocation = new Point2D.Double(1.0, 2.0);
        sut._enemyWaves.add(wave);

        //Act
        sut.onHitByBullet(bulletEvent);

        //Assert
        Assertions.assertEquals(1, sut._enemyWaves.size());
    }

    @Test
    void onScannedRobot_ShouldAddToEnemyWaves(){

        //Arrange
        sut._surfDirections.add(2);
        sut._surfDirections.add(5);

        sut._surfAbsBearings.add(1.0);
        sut._surfAbsBearings.add(2.0);
        sut._surfAbsBearings.add(3.0);
        sut._enemyLocation = new Point2D.Double(1.0, 2.0);
        //String name, double energy, double bearing, double distance, double heading, double velocity
        ScannedRobotEvent robotEvent = new ScannedRobotEvent("someRobot", 97, 0, 100, 0, 10);

        //Act
        sut.onScannedRobot(robotEvent);

        //Assert
        Assertions.assertEquals(1, sut._enemyWaves.size());
    }

    @Test
    void onScannedRobot_ShouldNotAddToEnemyWaves_WhenEnergyIsTooLow(){

        //Arrange
        sut._surfDirections.add(2);
        sut._surfDirections.add(5);

        sut._surfAbsBearings.add(1.0);
        sut._surfAbsBearings.add(2.0);
        sut._surfAbsBearings.add(3.0);
        sut._enemyLocation = new Point2D.Double(1.0, 2.0);
        //String name, double energy, double bearing, double distance, double heading, double velocity
        ScannedRobotEvent robotEvent = new ScannedRobotEvent("someRobot",50, 0, 100, 0, 10);

        //Act
        sut.onScannedRobot(robotEvent);

        //Assert
        Assertions.assertEquals(0, sut._enemyWaves.size());
    }

    @Test
    void setBackAsFront_ShouldTurnNegative90DegreesWhenAngleIsSetTo90(){

        //Arrange
        robot.headingRadians = 0;

        //Act
        sut.setBackAsFront(robot, -Math.PI/2);

        //Assert
        var headingRadians = robot.getHeadingRadians();
        Assertions.assertEquals(-Math.PI/2, headingRadians);
    }

    @Test
    void setBackAsFront_ShouldTurnNegative180DegreesWhenAngleIsSetTo180() {

        //Arrange
        robot.headingRadians = 0;

        //Act
        sut.setBackAsFront(robot, Math.PI);

        //Assert
        var headingRadians = robot.getHeadingRadians();
        Assertions.assertEquals(Math.PI, headingRadians);
    }

    @Test
    void updateWaves_ShouldRemoveEnemyWave_WhenEnoughDistance(){
        robot.setTime(100);
        sut._myLocation = new Point2D.Double(10,10);

        TrueSurfing.EnemyWave wave = new TrueSurfing.EnemyWave();
        wave.fireLocation = new Point2D.Double(1.0, 2.0);
        wave.distanceTraveled = 500;
        wave.bulletVelocity = 14;
        sut._enemyWaves.add(wave);

        sut.updateWaves();
    }

    @Test
    void testtt(){

        //Arrange
        robot.headingRadians = 0;

        //Act
        sut.setBackAsFront(robot, Math.PI);

        //Assert
        var headingRadians = robot.getHeadingRadians();
        //Assertions.assertEquals(-Math.PI/2, headingRadians);
    }
}
