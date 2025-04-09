package Modes;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMode implements ClockMode {
    private Timer timer;
    private int remainingTime;

    public TimerMode(int seconds) {
        this.remainingTime = seconds;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    System.out.printf("\rTimer: %02d:%02d", remainingTime / 60, remainingTime % 60);
                    remainingTime--;
                } else {
                    System.out.println("\nTime's up!");
                    stop();
                }
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void reset() {
        stop();
        System.out.println("\nTimer Reset.");
    }
}