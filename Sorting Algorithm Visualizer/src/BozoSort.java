import javax.swing.*;
import java.awt.*;

public class BozoSort {

    static int currIndex1 = -1;
    static int currIndex2 = -1;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        while (!sorted(arr)) {
            if (!Screen.running) return;
            Screen.sleep((int) (40000000*Screen.speed));
            currIndex1 = (int) (Math.random() * arr.length);
            currIndex2 = (int) (Math.random() * arr.length);

            int temp = arr[currIndex1];
            arr[currIndex1] = arr[currIndex2];
            arr[currIndex2] = temp;
            panel.repaint();
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
        currIndex1 = -1;
        currIndex2 = -1;
    }

    private static boolean sorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    public static void drawRedBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(200+BozoSort.currIndex1*(Screen.barWidth+1), 541 - Screen.arr[BozoSort.currIndex1], (Screen.barWidth), Screen.arr[BozoSort.currIndex1] + 20);
        g.fillRect(200+BozoSort.currIndex2*(Screen.barWidth+1), 541 - Screen.arr[BozoSort.currIndex2], (Screen.barWidth), Screen.arr[BozoSort.currIndex2] + 20);
        g.setColor(Color.WHITE);
    }

}

