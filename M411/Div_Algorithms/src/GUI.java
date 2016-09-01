import java.awt.*;
import javax.swing.*;
class GUI {
    static void mainGui(){
        JFrame guiFrame = new JFrame();

        /*TODO
         * Some kind of feedback concerning the sorting progress
         */
        guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        guiFrame.setTitle("Sorting algorithms");
        guiFrame.setMinimumSize(new Dimension(450, 150));
        guiFrame.setMaximumSize(new Dimension(600, 300));
        guiFrame.setLocationRelativeTo(null);

        JButton radixSortButton = new JButton(" Radix Sort LSD");
        JButton bubblesortButton = new JButton("Bubblesort");
        JButton readmeButton = new JButton("Readme");
        radixSortButton.setSize(30, 40);
        bubblesortButton.setSize(30, 40);
        readmeButton.setSize(30,40);
        JButton[] buttons = new JButton[3];
        buttons[0] = radixSortButton;
        buttons[1] = bubblesortButton;
        buttons[2] = readmeButton;

        JPanel buttonPane = new JPanel();
        buttonPane.add(radixSortButton);
        buttonPane.add(bubblesortButton);
        buttonPane.add(readmeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel infoText = new JLabel("Choose your algorithm");
        JPanel infoPane = new JPanel();
        infoPane.add(infoText);

        //Radix Sort LSD button press
        radixSortButton.addActionListener(event -> {
            infoPane.setVisible(false);
            Main.radixSortLSD(buttons, infoText);

        });

        //Bubblesort button press
        bubblesortButton.addActionListener(event -> {
            Main.bubbleSort(buttons, infoText);
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
