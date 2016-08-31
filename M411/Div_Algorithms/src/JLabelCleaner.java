import javax.swing.*;

/**
 * <Message> gets shown for <waitMsSeconds> on <label>
 */

class JLabelCleaner {
    private JLabel label;
    private int waitMsSeconds;
    private String message;

    JLabelCleaner(int waitMsSeconds, JLabel label, String message) {
        this.label = label;
        this.waitMsSeconds = waitMsSeconds;
        this.message = message;
    }

    void startCountdownFromNow() {
        Timer timer = new Timer(waitMsSeconds, e -> label.setText(message));
        timer.setRepeats(false);
        timer.start();
    }
}