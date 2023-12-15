package group9.framework;

import group9.framework.movement.GFTMovement;
import group9.framework.movement.IMoveable;
import group9.framework.movement.TrueSurfing;
import group9.framework.util.CalcUtils;
import robocode.AdvancedRobot;

public class MovementFactory extends Factory<IMoveable>{
    public IMoveable createProduct(AdvancedRobot robot){
        ConfigurationManager config = ConfigurationManager.getInstance();

        if (config.getProperty("TrueWaveSurfing")){
            return new TrueSurfing(robot);
        }
        return new GFTMovement(robot);
    }
}
