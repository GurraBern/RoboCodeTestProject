package group9.framework.targeting;

import robocode.ScannedRobotEvent;

public class NoTargeting implements ITarget{
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        //We decided to leave this method empty as NoTargeting is a feature but it doesn't do anything.
    }
}
