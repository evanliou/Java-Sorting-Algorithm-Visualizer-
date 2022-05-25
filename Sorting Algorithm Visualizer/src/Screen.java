import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Screen extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    static final int barWidth = 7;
    static final double speedModifier = 10.0;
    static final int arraySize = 100;

    static int[] arr = new int[arraySize];

    static double speed = 1.0;

    static int sweepNum = 0;
    static long startTime = 0;

    static boolean running = false;
    static boolean sorted = false;

    static boolean selectionSortOn = true;
    static boolean bubbleSortOn = false;
    static boolean insertionSortOn = false;
    static boolean mergeSortOn = false;
    static boolean cocktailSortOn = false;
    static boolean gnomeSortOn = false;
    static boolean bozoSortOn = false;
    static boolean finalSweepOn = false;

    private JFrame frame;
    private JButton start;
    private JButton reset;
    private JButton stop;
    private JButton selectionSortButton;
    private JButton bubbleSortButton;
    private JButton insertionSortButton;
    private JButton mergeSortButton;
    private JButton gnomeSortButton;
    private JButton cocktailSortButton;
    private JButton bozoSortButton;
    private JSlider speedSlider;
    public static JLabel timeTakenLabel;

    public Screen() {
        frame = new JFrame();
        frame.setTitle("Sorting Algorithm Visualizer");
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);

        setLayout(null);
        setBackground(Color.BLACK);

        start = new JButton("Start");
        start.setBounds(400, 5, 80, 25);
        start.addActionListener(this);

        reset = new JButton("Reset");
        reset.setBounds(550, 5, 80, 25);
        reset.addActionListener(this);

        stop = new JButton("Stop");
        stop.setBounds(700, 5, 80, 25);
        stop.addActionListener(this);

        selectionSortButton = new JButton("Selection Sort");
        selectionSortButton.setBounds(20,  50,  130,  25);
        selectionSortButton.addActionListener(this);
        selectionSortButton.setEnabled(false);

        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.setBounds(20,  90,  130,  25);
        bubbleSortButton.addActionListener(this);

        insertionSortButton = new JButton("Insertion Sort");
        insertionSortButton.setBounds(20,  130,  130,  25);
        insertionSortButton.addActionListener(this);

        cocktailSortButton = new JButton("Cocktail Sort");
        cocktailSortButton.setBounds(20,  170,  130,  25);
        cocktailSortButton.addActionListener(this);

        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.setBounds(20,  210,  130,  25);
        mergeSortButton.addActionListener(this);

        gnomeSortButton = new JButton("Gnome Sort");
        gnomeSortButton.setBounds(20,  250,  130,  25);
        gnomeSortButton.addActionListener(this);

        bozoSortButton = new JButton("Bozo Sort");
        bozoSortButton.setBounds(20,  290,  130,  25);
        bozoSortButton.addActionListener(this);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("Fast"));
        labelTable.put(6, new JLabel("Default"));
        labelTable.put(11, new JLabel("Slow"));

        speedSlider = new JSlider(1, 11);
        speedSlider.setBounds(850, 5, 250, 50);
        speedSlider.setSnapToTicks(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintTrack(false);
        speedSlider.setInverted(true);
        speedSlider.setLabelTable(labelTable);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speed = speedSlider.getValue()/speedModifier;
            }
        });

        timeTakenLabel = new JLabel();
        timeTakenLabel.setBounds(460,  25,  400,  100);
        timeTakenLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        timeTakenLabel.setForeground(Color.WHITE);

        add(start);
        add(reset);
        add(stop);
        add(selectionSortButton);
        add(bubbleSortButton);
        add(insertionSortButton);
        add(cocktailSortButton);
        add(mergeSortButton);
        add(gnomeSortButton);
        add(bozoSortButton);
        add(speedSlider);
        add(timeTakenLabel);

        frame.setVisible(true);

        resetArr();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        drawBars(g);

        if (running && !sorted) {

            if (selectionSortOn) {
                SelectionSort.drawRedBar(g);
                SelectionSort.drawGreenBar(g);
                SelectionSort.drawBlueBar(g);
            }

            if (bubbleSortOn) {
                BubbleSort.drawRedBar(g);
            }

            if (insertionSortOn) {
                InsertionSort.drawRedBar(g);
                InsertionSort.drawGreenBar(g);
            }

            if (cocktailSortOn) {
                CocktailSort.drawRedBar(g);
            }

            if (mergeSortOn) {
                MergeSort.drawRedBar(g);
            }

            if (gnomeSortOn) {
                GnomeSort.drawRedBar(g);
            }

            if (bozoSortOn) {
                BozoSort.drawRedBar(g);
            }
        }

        if (finalSweepOn) {
            finalSweep(g);
        }

    }

    public void resetArr() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 400) + 1;
        }
    }

    public void drawBars(Graphics g) {
        for (int i = 0; i < arr.length; i++) {
            g.fillRect(200+i*(Screen.barWidth+1), 541 - arr[i], (Screen.barWidth), arr[i] + 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Object action = e.getSource();

                if (action.equals(start) && !running && !sorted) {
                    running = true;
                    animateCurrentSort();
                    return;
                }


                if (action.equals(reset)) {
                    timeTakenLabel.setText("");
                    running = false;
                    sorted = false;
                    resetArr();
                    repaint();
                    return;
                }

                if (action.equals(stop) && running) {
                    running = false;
                    return;
                }

                if (action.equals(selectionSortButton) && !running) {
                    disableCurrentSort();
                    selectionSortOn = true;
                    selectionSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(bubbleSortButton) && !running) {
                    disableCurrentSort();
                    bubbleSortOn = true;
                    bubbleSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(insertionSortButton) && !running) {
                    disableCurrentSort();
                    insertionSortOn = true;
                    insertionSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(cocktailSortButton) && !running) {
                    disableCurrentSort();
                    cocktailSortOn = true;
                    cocktailSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(mergeSortButton) && !running) {
                    disableCurrentSort();
                    mergeSortOn = true;
                    mergeSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(gnomeSortButton) && !running) {
                    disableCurrentSort();
                    gnomeSortOn = true;
                    gnomeSortButton.setEnabled(false);
                    return;
                }

                if (action.equals(bozoSortButton) && !running) {
                    disableCurrentSort();
                    bozoSortOn = true;
                    bozoSortButton.setEnabled(false);
                    return;
                }
            }
        });
        thread.start();
    }

    public void animateCurrentSort() {
        if (selectionSortOn) SelectionSort.animate(arr, this);
        if (bubbleSortOn) BubbleSort.animate(arr, this);
        if (insertionSortOn) InsertionSort.animate(arr, this);
        if (cocktailSortOn) CocktailSort.animate(arr, this);
        if (mergeSortOn) MergeSort.animate(arr, this);
        if (gnomeSortOn) GnomeSort.animate(arr, this);
        if (bozoSortOn) BozoSort.animate(arr, this);
    }

    public void disableCurrentSort() {
        if (selectionSortOn) {
            selectionSortOn = false;
            selectionSortButton.setEnabled(true);
            return;
        }

        if (bubbleSortOn) {
            bubbleSortOn = false;
            bubbleSortButton.setEnabled(true);
            return;
        }

        if (insertionSortOn) {
            insertionSortOn = false;
            insertionSortButton.setEnabled(true);
            return;
        }

        if (cocktailSortOn) {
            cocktailSortOn = false;
            cocktailSortButton.setEnabled(true);
            return;
        }

        if (mergeSortOn) {
            mergeSortOn = false;
            mergeSortButton.setEnabled(true);
            return;
        }

        if (gnomeSortOn) {
            gnomeSortOn = false;
            gnomeSortButton.setEnabled(true);
            return;
        }

        bozoSortOn = false;
        bozoSortButton.setEnabled(true);

    }

    public static void finalSweep(Graphics g) {
        if (!Screen.running) return;
        for (int i = 0; i <= sweepNum; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(200+i*(barWidth+1), 541 - Screen.arr[i], barWidth, Screen.arr[i] + 20);
            g.setColor(Color.WHITE);
        }
    }

    public static void sleep(long nanoseconds) {
        long timeElapsed;
        final long startTime = System.nanoTime();
        do {
            timeElapsed = System.nanoTime() - startTime;
        } while (timeElapsed < nanoseconds);
    }

    public static void startStopwatch() {
        startTime = System.nanoTime();
    }

    public static double stopStopwatch() {
        long temp = startTime;
        startTime = 0;
        return ((System.nanoTime() - temp) / 1000000000.0);
    }

}