/**
 * Created by Fabian on 31/08/16.
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class GUI {
    public static void mainGui(){
        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        guiFrame.setTitle("Sorting algorithms");
        guiFrame.setMinimumSize(new Dimension(450, 150));
        guiFrame.setLocationRelativeTo(null);

        JButton radixSortButton = new JButton(" Radix Sort LSD");
        JButton bubblesortButton = new JButton("Bubblesort");
        radixSortButton.setSize(30, 40);
        bubblesortButton.setSize(30, 40);

        JPanel buttonPane = new JPanel();
        buttonPane.add(radixSortButton);
        buttonPane.add(bubblesortButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        radixSortButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {

            }
        });

        guiFrame.add(buttonPane, BorderLayout.SOUTH);
        guiFrame.setVisible(true);
    }
}
