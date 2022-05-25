import javax.swing.*;
import java.awt.*;

public class SelectionSort {

    static int currIndex = 1;
    static int leastIndex = -1;
    static int replacingIndex = 0;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        int least;
        for (int i = 0; i < arr.length - 1; i++) {
            least = i;
            replacingIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (!Screen.running) return;
                Screen.sleep((int) (10000000*Screen.speed));
                currIndex = j;
                leastIndex = least;
                panel.repaint();
                if (arr[j] < arr[least]) {
                    least = j;
                }
            }
            if (!Screen.running) return;
            int temp = arr[i];
            arr[i] = arr[least];
            arr[least] = temp;
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
        g.fillRect(200+SelectionSort.currIndex*(Screen.barWidth+1), 541 - Screen.arr[SelectionSort.currIndex], (Screen.barWidth), Screen.arr[SelectionSort.currIndex] + 20);
        g.setColor(Color.WHITE);
    }

    public static void drawGreenBar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(200+SelectionSort.leastIndex*(Screen.barWidth+1), 541 - Screen.arr[SelectionSort.leastIndex], (Screen.barWidth), Screen.arr[SelectionSort.leastIndex] + 20);
        g.setColor(Color.WHITE);
    }

    public static void drawBlueBar(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(200+SelectionSort.replacingIndex*(Screen.barWidth+1), 541 - Screen.arr[SelectionSort.replacingIndex], (Screen.barWidth), Screen.arr[SelectionSort.replacingIndex] + 20);
        g.setColor(Color.WHITE);
    }

}
