package group9.framework;

import group9.framework.movement.IMoveable;


import group9.framework.targeting.ITarget;
import robocode.*;


import java.awt.*;


public class SPLRobot extends AdvancedRobot{

    private Gun gun;
    private ITarget targeting;
    private IMoveable movement;

    public static double lateralDirection;
	public static double lastEnemyVelocity;

    private TargetingFactory tf;
    private MovementFactory mf;
    private Radar radar;

    public SPLRobot(){
        this.gun = new Gun();
        this.tf = new TargetingFactory();
        this.mf = new MovementFactory();
        this.targeting = tf.createProduct(this);
        this.radar = new SpinningRadar();

    }

    public void run() {
        // #if BlueBlackYellow
        setColors(Color.BLUE, Color.BLACK, Color.YELLOW);
        // #endif

        lateralDirection = 1;
        lastEnemyVelocity = 0;
        
        this.movement = mf.createProduct(this);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        do {
            radar.Spin(this);
        } while (true);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        movement.onScannedRobot(e);
        targeting.onScannedRobot(e);
    }

    public Gun getGun(){
        return gun;
    }
}
