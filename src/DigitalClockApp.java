import Modes.StopwatchMode;
import Modes.TimerMode;
import Modes.WatchMode;

import java.util.Scanner;

public class DigitalClockApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WatchMode watchMode = new WatchMode();
        StopwatchMode stopwatchMode = new StopwatchMode();
        TimerMode timerMode = null;

        while (true) {
            System.out.println("\nDigital Clock Application");
            System.out.println("1. Watch Mode (Current Time)");
            System.out.println("2. Stopwatch Mode");
            System.out.println("3. Timer Mode");
            System.out.println("4. Exit");
            System.out.print("Choose mode: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    watchMode.showTime();
                    break;
                case 2:
                    handleStopwatch(scanner, stopwatchMode);
                    break;
                case 3:
                    System.out.print("Enter countdown time in seconds: ");
                    int seconds = scanner.nextInt();
                    timerMode = new TimerMode(seconds);
                    handleTimer(scanner, timerMode);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleStopwatch(Scanner scanner, StopwatchMode stopwatchMode) {
        while (true) {
            System.out.println("\nStopwatch Controls: (1) Start (2) Stop (3) Reset (4) Back");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    stopwatchMode.start();
                    break;
                case 2:
                    stopwatchMode.stop();
                    break;
                case 3:
                    stopwatchMode.reset();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choices.");
            }
        }
    }

    private static void handleTimer(Scanner scanner, TimerMode timerMode) {
        while (true) {
            System.out.println("\nTimer Controls: (1) Start (2) Stop (3) Reset (4) Back");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    timerMode.start();
                    break;
                case 2:
                    timerMode.stop();
                    break;
                case 3:
                    timerMode.reset();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}