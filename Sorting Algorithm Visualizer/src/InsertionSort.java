import javax.swing.*;
import java.awt.*;

public class InsertionSort {

    static int currIndex;
    static int greatestIndex;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            greatestIndex = i;
            while (j > 0 && arr[j] < arr[j-1]) {
                if (!Screen.running) return;
                Screen.sleep((int) (50000000*Screen.speed));
                currIndex = j - 1;
                panel.repaint();
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        double timeTakenInSeconds = Math.round(Screen.stopStopwatch()*1000.0)/1000.0;
        Screen.timeTakenLabel.setText("Time Taken: " + Double.toString(timeTakenInSeconds) + " seconds");

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
        g.fillRect(200+InsertionSort.currIndex*(Screen.barWidth+1), 541 - Screen.arr[InsertionSort.currIndex], (Screen.barWidth), Screen.arr[InsertionSort.currIndex] + 20);
        g.setColor(Color.WHITE);
    }

    public static void drawGreenBar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(200+InsertionSort.greatestIndex*(Screen.barWidth+1), 541 - Screen.arr[InsertionSort.greatestIndex], (Screen.barWidth), Screen.arr[InsertionSort.greatestIndex] + 20);
        g.setColor(Color.WHITE);
    }

}
