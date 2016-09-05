import java.awt.*;
import javax.swing.*;
import java.lang.*;

class GUI {
    private static String inputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/inputfile.txt";
    private static String outputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/outputfile.txt";

    static void mainGui(){
        JFrame guiFrame = new JFrame();

        /*TODO
         * Some kind of feedback concerning the sorting progress
         */
        guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        guiFrame.setTitle("Sorting algorithms");
        guiFrame.setMinimumSize(new Dimension(500, 150));
        guiFrame.setMaximumSize(new Dimension(600, 300));
        guiFrame.setLocationRelativeTo(null);

        JButton radixSortButton = new JButton("Radix Sort LSD");
        JButton quickSort = new JButton("Quicksort");
        JButton bubblesortButton = new JButton("Bubblesort");
        JButton readmeButton = new JButton("Readme");

        JButton[] buttons = new JButton[4];
        buttons[0] = radixSortButton;
        buttons[1] = bubblesortButton;
        buttons[2] = readmeButton;
        buttons[3] = quickSort;

        JPanel buttonPane = new JPanel();
        buttonPane.add(radixSortButton);
        buttonPane.add(quickSort);
        buttonPane.add(bubblesortButton);
        buttonPane.add(readmeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel infoText = new JLabel("Choose your algorithm");
        JPanel infoPane = new JPanel();
        infoPane.add(infoText);

        //Radix Sort LSD button press
        radixSortButton.addActionListener(event -> {
            Thread t = new Thread(() -> Main.radixSortLSD(buttons, infoText));
            t.start();
        });

        //Quick Sort Button Press
        quickSort.addActionListener(event -> {
            for (JButton button : buttons)
                button.setEnabled(false);
            String userInput = Main.readFile(inputFilePath);
            int[] arr = new int[userInput.length()];

            for (int i = 0; i < userInput.length(); i++) {
                arr[i] = (int)userInput.charAt(i);
            }

            long startTime = System.currentTimeMillis();
            Thread t = new Thread(() -> Main.quickSort(arr, 0, arr.length - 1));
            t.start();
            long estimatedTime = System.currentTimeMillis() - startTime;

            for (JButton button : buttons)
                button.setEnabled(true);

            Main.writeFile(outputFilePath, arr);
            Main.labelTime(estimatedTime, infoText);
        });

        //Bubblesort button press
        bubblesortButton.addActionListener(event -> {
            Thread t = new Thread(() -> Main.bubbleSort(buttons, infoText));
            t.start();
        });

        //Readme button press
        readmeButton.addActionListener(event -> readmeGUI(guiFrame));

        guiFrame.add(buttonPane, BorderLayout.SOUTH);
        guiFrame.add(infoPane, BorderLayout.NORTH);
        guiFrame.setVisible(true);
    }

    private static void readmeGUI(JFrame guiFrame){
        guiFrame.setVisible(false);

        JFrame readmeFrame = new JFrame();
        readmeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        readmeFrame.setTitle("Readme");
        readmeFrame.setMinimumSize(new Dimension(300, 120));
        readmeFrame.setMaximumSize(new Dimension(300, 120));
        readmeFrame.setLocationRelativeTo(null);

        JButton backButton = new JButton("Back");
        backButton.setSize(30, 40);

        JPanel buttonPane = new JPanel();
        buttonPane.add(backButton);

        JLabel readmeText = new JLabel("<html><div style='text-align: center;'>" +
                "Parse File: /inputfile.txt " +
                "<br> Output File: /outputfile.txt " +
                "<br> No Whitespace in input allowed</html>");
        JPanel readmePane = new JPanel();
        readmePane.add(readmeText);

        backButton.addActionListener(event -> {
            readmeFrame.setVisible(false);
            mainGui();
        });

        readmeFrame.add(readmePane, BorderLayout.CENTER);
        readmeFrame.add(buttonPane, BorderLayout.SOUTH);
        readmeFrame.setVisible(true);
    }
}
