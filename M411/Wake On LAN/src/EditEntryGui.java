import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditEntryGui {

    public void editEntry(String key, String value){
        JFrame guiFrame = new JFrame();
        String oldKey = key;
        String oldValue = value;

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("New Workstation");
        guiFrame.setMinimumSize(new Dimension(325, 240));
        guiFrame.setLocationRelativeTo(null);

        JTextField namePerson = new JTextField(oldKey);
        JTextField macPerson = new JTextField(oldValue);

        JLabel infoText = new JLabel("");
        JPanel infoPane = new JPanel();
        infoPane.add(infoText);

        JButton changePersonButton = new JButton("Change");
        JButton deleteValueButton = new JButton("Delete");
        JButton previousMenuButton = new JButton(" Back ");

        Object[] fields = {"Name", namePerson, "Mac Address \n(Form: AA:AA:AA:AA:AA:AA)", macPerson};
        Object[] buttons = {previousMenuButton, changePersonButton, deleteValueButton};
        JOptionPane textEntry = new JOptionPane( fields, JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);

        changePersonButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {
                String newKey = namePerson.getText();
                String newValue = macPerson.getText();
                DeviceList person = new DeviceList();

                if (Device.isMacValid(newValue) == true){
                    if (person.safePerson(newKey, newValue) == true){
                        if (person.deletePerson(oldKey, oldValue) == true) {
                            guiFrame.setVisible(false);
                            GUI test = new GUI();
                            test.mainGui();
                        }
                        else {
                            infoText.setText("Error While Safing");
                            new JLabelCleaner(1600,infoText).startCountdownFromNow();
                        }
                    }
                    else {
                        if (!java.util.Objects.equals(oldValue, newValue)) {
                            if (person.deletePerson(oldKey, oldValue)) {
                                if (person.safePerson(newKey, newValue)) {
                                    guiFrame.setVisible(false);
                                    GUI test = new GUI();
                                    test.mainGui();
                                }
                            }
                        } else {
                            infoText.setText("Workstation Already Defined");
                            new JLabelCleaner(1600,infoText).startCountdownFromNow();
                        }
                    }
                }
                else {
                    infoText.setText("Invalid MAC");
                    new JLabelCleaner(1600,infoText).startCountdownFromNow();
                }
            }
        });

        deleteValueButton.addActionListener(event -> {
            DeviceList person = new DeviceList();
            if (!person.deletePerson(oldKey, oldValue)) {
                return;
            }
            guiFrame.setVisible(false);
            GUI test = new GUI();
            test.mainGui();
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
