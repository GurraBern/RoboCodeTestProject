package group9.framework;

import group9.framework.targeting.ITarget;

import robocode.*;

public class Robot extends AdvancedRobot{

    private Gun gun;
    private ITarget targeting;
    private Movement movement;

    public static double lateralDirection;
	public static double lastEnemyVelocity;

    public Robot(Gun gun, ITarget targeting, Movement movement){
        this.gun = gun;
        this.targeting = targeting;
        this.movement = movement;
    }

}
