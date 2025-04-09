package GUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

// Digital Clock Application with GUI
public class DigitalClockGUI extends JFrame {
    private JLabel watchLabel, stopwatchLabel, timerLabel;
    private JButton startStopwatch, stopStopwatch, resetStopwatch;
    private JButton startTimer, stopTimer, resetTimer;
    private JTextField timerInput;

    private boolean runningStopwatch = false;
    private long stopwatchStartTime, stopwatchElapsedTime = 0;
    private Timer stopwatchTimer, countdownTimer;
    private int countdownTime = 0;

    public DigitalClockGUI() {
        setTitle("Digital Clock Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Watch", createWatchPanel());
        tabbedPane.addTab("Stopwatch", createStopwatchPanel());
        tabbedPane.addTab("Timer", createTimerPanel());
        add(tabbedPane, BorderLayout.CENTER);

        // Start updating the watch time
        startWatchUpdater();
    }

    // Watch Mode Panel
    private JPanel createWatchPanel() {
        JPanel panel = new JPanel();
        watchLabel = new JLabel("Current Time: --:--:--", SwingConstants.CENTER);
        watchLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(watchLabel);
        return panel;
    }

    // Stopwatch Mode Panel
    private JPanel createStopwatchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        stopwatchLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        stopwatchLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(stopwatchLabel);

        JPanel buttonPanel = new JPanel();
        startStopwatch = new JButton("START");
        stopStopwatch = new JButton("STOP");
        resetStopwatch = new JButton("RESET");

        startStopwatch.addActionListener(e -> startStopwatch());
        stopStopwatch.addActionListener(e -> stopStopwatch());
        resetStopwatch.addActionListener(e -> resetStopwatch());

        buttonPanel.add(startStopwatch);
        buttonPanel.add(stopStopwatch);
        buttonPanel.add(resetStopwatch);
        panel.add(buttonPanel);

        return panel;
    }

    // Timer Mode Panel
    private JPanel createTimerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        timerLabel = new JLabel("00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(timerLabel);

        timerInput = new JTextField(14);
        panel.add(timerInput);

        JPanel buttonPanel = new JPanel();
        startTimer = new JButton("START");
        stopTimer = new JButton("STOP");
        resetTimer = new JButton("RESET");

        startTimer.addActionListener(e -> startTimer());
        stopTimer.addActionListener(e -> stopTimer());
        resetTimer.addActionListener(e -> resetTimer());

        buttonPanel.add(startTimer);
        buttonPanel.add(stopTimer);
        buttonPanel.add(resetTimer);
        panel.add(buttonPanel);

        return panel;
    }

    // Update the watch time every second
    private void startWatchUpdater() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();
                watchLabel.setText(String.format("Current Time: %02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()));
            }
        }, 0, 1000);
    }

    // Stopwatch Functions
    private void startStopwatch() {
        if (!runningStopwatch) {
            runningStopwatch = true;
            stopwatchStartTime = System.currentTimeMillis() - stopwatchElapsedTime;

            stopwatchTimer = new Timer();
            stopwatchTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    stopwatchElapsedTime = System.currentTimeMillis() - stopwatchStartTime;
                    long seconds = (stopwatchElapsedTime / 1000) % 60;
                    long minutes = (stopwatchElapsedTime / 60000) % 60;
                    long milliseconds = (stopwatchElapsedTime % 1000) / 10;
                    stopwatchLabel.setText(String.format("%02d:%02d:%02d", minutes, seconds, milliseconds));
                }
            }, 0, 10);
        }
    }

    private void stopStopwatch() {
        runningStopwatch = false;
        if (stopwatchTimer != null) {
            stopwatchTimer.cancel();
        }
    }

    private void resetStopwatch() {
        runningStopwatch = false;
        stopwatchElapsedTime = 0;
        stopwatchLabel.setText("00:00:00");
        if (stopwatchTimer != null) {
            stopwatchTimer.cancel();
        }
    }

    // Timer Functions
    private void startTimer() {
        try {
            countdownTime = Integer.parseInt(timerInput.getText());
            if (countdownTime <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid countdown time in seconds.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (countdownTimer != null) {
            countdownTimer.cancel();
        }

        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (countdownTime > 0) {
                    timerLabel.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
                    countdownTime--;
                } else {
                    timerLabel.setText("Time's Up!");
                    countdownTimer.cancel();
                }
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (countdownTimer != null) {
            countdownTimer.cancel();
        }
    }

    private void resetTimer() {
        stopTimer();
        timerLabel.setText("00:00");
        timerInput.setText("");
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitalClockGUI app = new DigitalClockGUI();
            app.setVisible(true);
        });
    }
}
