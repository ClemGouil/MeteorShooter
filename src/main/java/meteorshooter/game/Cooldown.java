package meteorshooter.game;

public class Cooldown {
    
    private double delay;
    private double timeLeft;

    public Cooldown(double delay){
        this.delay = delay;
        this.timeLeft = delay;
    }

    /**
     * @param delta the time interval (in ms) between the last and current update
     * @return true whenever the cooldown reaches zero, false otherwise
     */
    public boolean update(float delta){
        this.timeLeft -= delta;
        if(this.timeLeft < 0){
            this.timeLeft = delay;
            return true;
        }
        return false;
    }

}
