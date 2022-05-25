import javax.swing.*;
import java.awt.*;

public class GnomeSort {

    static int pointerIndex;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        int index = 0;

        while (index < arr.length) {
            if (!Screen.running) return;
            Screen.sleep((int) (40000000*Screen.speed));
            panel.repaint();
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index-1]) {
                index++;
                pointerIndex = index;
            }
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index-1];
                arr[index-1] = temp;
                index--;
                pointerIndex = index;
            }
        }
        pointerIndex = 0;

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
        g.fillRect(200+GnomeSort.pointerIndex*(Screen.barWidth+1), 541 - Screen.arr[GnomeSort.pointerIndex], (Screen.barWidth), Screen.arr[GnomeSort.pointerIndex] + 20);
        g.setColor(Color.WHITE);
    }

}
