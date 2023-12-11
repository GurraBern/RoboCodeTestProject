package group9.framework;

public class Gun {
    private double FIRE_POWER = 1.0;


    public Gun(double p){
        FIRE_POWER = p;
    }

    public double getFirePower(){
        return FIRE_POWER;
    }

    public void setFirePower(double p){
        FIRE_POWER = p;
    }
}
