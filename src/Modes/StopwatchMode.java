package Modes;

public class StopwatchMode implements ClockMode {
    private boolean running = false;
    private long startTime, elapsedTime = 0;

    public void start() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis() - elapsedTime;
            new Thread(() -> {
                while (running) {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    System.out.printf("\rStopwatch: %02d:%02d:%02d", (elapsedTime / 1000) / 60, (elapsedTime / 1000) % 60, (elapsedTime % 1000) / 10);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();
        }
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        running = false;
        elapsedTime = 0;
        System.out.println("\nStopwatch Reset.");
    }
}
