package Modes;

import java.time.LocalTime;


public class WatchMode {
    public void showTime() {
        while (true) {
            LocalTime now = LocalTime.now();
            System.out.printf("\rCurrent Time: %02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
