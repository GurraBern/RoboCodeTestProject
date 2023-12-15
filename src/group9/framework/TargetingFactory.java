package group9.framework;

import group9.framework.targeting.GuessFactorTargeting;
import group9.framework.targeting.ITarget;
import group9.framework.targeting.NoTargeting;
import robocode.AdvancedRobot;

public class TargetingFactory extends Factory<ITarget> {
    public ITarget createProduct(AdvancedRobot robot){

        ConfigurationManager config = ConfigurationManager.getInstance();
        ITarget targeting;
        if (config.getProperty("GuessFactorTargeting")){
            return new GuessFactorTargeting(robot);
        }
        return new NoTargeting();

    }
}
