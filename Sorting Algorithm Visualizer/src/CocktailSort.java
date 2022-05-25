import javax.swing.*;
import java.awt.*;

public class CocktailSort {

    static int currIndex = 0;

    public static void animate(int arr[], JPanel panel) {
        Screen.startStopwatch();

        boolean swapped = true;
        int start = 0;
        int end = arr.length;

        while (swapped == true) {
            if (!Screen.running) return;
            panel.repaint();
            swapped = false;

            for (int i = start; i < end - 1; ++i)
            {
                if (arr[i] > arr[i + 1]) {
                    if (!Screen.running) return;
                    Screen.sleep((int) (8000000 * Screen.speed));
                    currIndex = i + 1;
                    panel.repaint();
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false)
                break;

            swapped = false;

            end = end - 1;

            for (int i = end - 1; i >= start; i--)
            {
                if (arr[i] > arr[i + 1])
                {
                    if (!Screen.running) return;
                    Screen.sleep((int) (8000000 * Screen.speed));
                    currIndex = i;
                    panel.repaint();
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }

            start = start + 1;
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
        g.fillRect(200+CocktailSort.currIndex*(Screen.barWidth+1), 541 - Screen.arr[CocktailSort.currIndex], (Screen.barWidth), Screen.arr[CocktailSort.currIndex] + 20);
        g.setColor(Color.WHITE);
    }
}
