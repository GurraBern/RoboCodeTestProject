package group9.framework;

import robocode.AdvancedRobot;

abstract class Factory<T> {
    public abstract T createProduct(AdvancedRobot robot);
}
