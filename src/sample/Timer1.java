package sample;

        import java.util.Timer;
        import java.util.TimerTask;

/**
 * Created by BLiu on 1/27/2016.
 */
public class Timer1 {

    int seconds = 2;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            seconds--;
            System.out.println(seconds);
            if (seconds == 0) {
                timer.cancel();
            }
        }
    };

    public void start() {
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop() {
        timer.cancel();
    }
}
