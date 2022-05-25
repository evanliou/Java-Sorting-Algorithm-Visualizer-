import javax.swing.*;
import java.awt.*;

public class BubbleSort {

    static int currIndex = 0;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (!Screen.running) return;
                Screen.sleep((int) (8000000 * Screen.speed));
                currIndex = j + 1;
                panel.repaint();
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        double timeTakenInSeconds = Math.round(Screen.stopStopwatch()*1000.0)/1000.0;
        Screen.timeTakenLabel.setText("Time Taken: " + Double.toString(timeTakenInSeconds) + " seconds");

        currIndex = 0;
        Screen.sorted = true;
        Screen.finalSweepOn = true;
        for (int i = 0; i < Screen.arr.length; i++) {
            if (!Screen.running) break;
            panel.repaint();
            Screen.sleep(10000000);
            Screen.sweepNum++;
        }
        Screen.sleep(100000000);
        Screen.sweepNum = 0;
        Screen.finalSweepOn = false;
        Screen.running = false;
        panel.repaint();
    }

    public static void drawRedBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(200+BubbleSort.currIndex*(Screen.barWidth+1), 541 - Screen.arr[BubbleSort.currIndex], (Screen.barWidth), Screen.arr[BubbleSort.currIndex] + 20);
        g.setColor(Color.WHITE);
    }

}
