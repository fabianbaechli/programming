import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewEntryGUI {

    public void newEntry(){
        JFrame guiFrame = new JFrame();

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("New Workstation");
        guiFrame.setMinimumSize(new Dimension(325, 240));
        guiFrame.setLocationRelativeTo(null);

        JTextField namePerson = new JTextField();
        JTextField macPerson = new JTextField();

        JButton makePersonButton = new JButton("Create");
        JButton previousMenuButton = new JButton(" Back ");

        JLabel infoText = new JLabel("");
        JPanel infoPane = new JPanel();
        infoPane.add(infoText);

        Object[] fields = {"Name", namePerson, "Mac Address \n(Form: AA:AA:AA:AA:AA:AA)", macPerson};
        Object[] buttons = {previousMenuButton, makePersonButton};
        JOptionPane textEntry = new JOptionPane( fields, JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);

        makePersonButton.addActionListener(event -> {
            String key = namePerson.getText();
            String value = macPerson.getText();
            DeviceList person = new DeviceList();

            if (!Device.isMacValid(value)) {
                infoText.setText("Invalid MAC");
                new JLabelCleaner(1600,infoText).startCountdownFromNow();
            } else {
                if (person.safePerson(key, value)){
                    guiFrame.setVisible(false);
                    GUI test = new GUI();
                    test.mainGui();
                }
                else {
                    infoText.setText("Workstation Already Defined");
                    new JLabelCleaner(1600,infoText).startCountdownFromNow();
                }
            }
        });
        previousMenuButton.addActionListener(event -> {
            guiFrame.setVisible(false);
            GUI test = new GUI();
            test.mainGui();
        });

        guiFrame.add(textEntry, BorderLayout.SOUTH);
        guiFrame.add(infoPane, BorderLayout.NORTH);
        guiFrame.setVisible(true);
    }
}
