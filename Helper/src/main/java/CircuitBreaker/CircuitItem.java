package CircuitBreaker;

import java.util.Timer;
import java.util.TimerTask;

public class CircuitItem {
    private static final int LIFE_TIME_OF_CIRCUIT_ITEM = 10;

    private static final int CHECK_PERIOD = 1;

    private final Timer timer;

    private int remainingLifeTime;

    private boolean isOpened;

    public CircuitItem() {
        this.timer = new Timer();
        this.remainingLifeTime = LIFE_TIME_OF_CIRCUIT_ITEM;
    }

    public void open() {
        this.isOpened = true;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callback();
            }
        }, 0, CHECK_PERIOD * 1000);
    }

    private void cancel() {
        this.timer.cancel();
    }

    private void callback() {
        this.remainingLifeTime -= CHECK_PERIOD;
        if (this.isTimedOut())
            this.cancel();
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    public boolean isTimedOut() {
        return this.remainingLifeTime <= 0;
    }
}
