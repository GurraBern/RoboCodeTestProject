package Mocks;

import robocode.*;

import robocode.ScannedRobotEvent;

public class AdvancedRobotMock extends AdvancedRobot {
	
	final double arenaWidth = 800;
	final double arenaHeight = 600; 
	
	double radarAngle = 0;
	double gunAngle = 0;
	long time = 0;
	public double headingRadians = 0;
	double x = arenaWidth / 2;
	double y = arenaHeight / 2;
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public void run() {
		
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void setAhead(double distance) {
		distance *= 0.1;
		y += Math.sin(getHeadingRadians() + Math.PI/2) * distance;
		x -= Math.cos(getHeadingRadians() + Math.PI/2) * distance;
	}
	
	@Override
	public void setBack(double distance) {
		distance *= 0.1;
		y -= Math.sin(getHeadingRadians() + Math.PI/2) * distance;
		x += Math.cos(getHeadingRadians() + Math.PI/2) * distance;
	}
	
	@Override
	public double getEnergy() {
		return 3.0;
	}
	
	@Override
	public double getHeadingRadians() {
		return headingRadians;
	}
	
	@Override
	public double getBattleFieldHeight() {
		return arenaHeight;
	}
	
	@Override
	public double getBattleFieldWidth() {
		return arenaWidth;
	}
	
	@Override
	public double getRadarHeadingRadians() {
		return radarAngle;
	}
	
	@Override
	public void setTurnRightRadians(double radians)  {
		headingRadians += radians;
	}

	@Override
	public void setTurnLeftRadians(double radians)  {
		headingRadians -= radians;
	}

	
	@Override
	public void setTurnRadarRightRadians(double d) {
		radarAngle += d;
	}
	
	@Override
	public void setTurnGunRightRadians(double d) {
		gunAngle += d;
	}
	
	@Override
	public double getGunHeadingRadians() {
		return gunAngle;
	}
	
	@Override
	public void fire(double d) {
		
	}
	
	@Override
	public double getVelocity() {
		return 3.0;
	}
	
}
