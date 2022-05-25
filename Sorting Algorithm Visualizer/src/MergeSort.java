import javax.swing.*;
import java.awt.*;

public class MergeSort {

    static int midIndex;
    static int rightIndex;
    static int leftIndex;

    public static void animate(int[] arr, JPanel panel) {
        Screen.startStopwatch();

        int n = arr.length;
        int curr_size;
        int left_start;

        for (curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size) {
            for (left_start = 0; left_start < n-1; left_start += 2*curr_size) {
                if (!Screen.running) return;
                Screen.sleep((int) (100000000*Screen.speed*(curr_size/4)));
                panel.repaint();
                int mid = Math.min(left_start + curr_size - 1, n-1);
                int right_end = Math.min(left_start + 2*curr_size - 1, n-1);

                midIndex = mid;
                rightIndex = right_end;
                leftIndex = left_start;

                merge(arr, left_start, mid, right_end);
            }
        }
        midIndex = 0;
        rightIndex = 0;
        leftIndex = 0;
        Screen.sorted = true;
        panel.repaint();
        Screen.sleep(300000000);

        double timeTakenInSeconds = Math.round(Screen.stopStopwatch()*1000.0)/1000.0;
        Screen.timeTakenLabel.setText("Time Taken: " + Double.toString(timeTakenInSeconds) + " seconds");

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
        g.fillRect(200+MergeSort.rightIndex*(Screen.barWidth+1), 541 - Screen.arr[MergeSort.rightIndex], (Screen.barWidth), Screen.arr[MergeSort.rightIndex] + 20);
        g.fillRect(200+MergeSort.leftIndex*(Screen.barWidth+1), 541 - Screen.arr[MergeSort.leftIndex], (Screen.barWidth), Screen.arr[MergeSort.leftIndex] + 20);
        g.setColor(Color.WHITE);
    }

    public static void merge(int arr[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (i = 0; i < n1; i++){
            L[i] = arr[l + i];
        }

        for (j = 0; j < n2; j++) {
            R[j] = arr[m + 1+ j];
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

}
