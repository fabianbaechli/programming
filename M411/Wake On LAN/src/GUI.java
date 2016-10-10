import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {

    public void mainGui(){
        JFrame guiFrame = new JFrame();
        DeviceList personList = new DeviceList();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Wake On LAN Fabian Bächli 2016");
        guiFrame.setMinimumSize(new Dimension(450, 150));

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        //Options for the JComboBox
        String[] pOptions = personList.getNames();
        Quicksort.quickSort(pOptions,0,pOptions.length - 1);

        //The first JPanel contains a JLabel and JCombobox
        final JPanel comboPanel = new JPanel();

        JLabel comboLbl = new JLabel("Workstations: ");
        JComboBox<String> persons = new JComboBox<>(pOptions);
        comboPanel.add(comboLbl);
        comboPanel.add(persons);
        guiFrame.add(comboPanel, BorderLayout.NORTH);

        JButton sendPackageButton = new JButton(" Send Package ");
        JButton newWorkstationButton = new JButton("New Workstation");
        JButton editWorkstationButton = new JButton("  Edit Entry  ");

        sendPackageButton.setSize(30, 40);
        newWorkstationButton.setSize(30, 40);
        editWorkstationButton.setSize(30, 40);

        JPanel buttonPane = new JPanel();
        buttonPane.add(editWorkstationButton);
        buttonPane.add(newWorkstationButton);
        buttonPane.add(sendPackageButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel infoText = new JLabel("");
        JPanel infoPane = new JPanel();
        infoPane.add(infoText);
        guiFrame.add(infoPane, BorderLayout.CENTER);

        sendPackageButton.addActionListener(event -> {
            //When the sendPackageButton is pressed
            //A String with the Workstation which is selected
            //A byte with the value of the MAC Address from the HashMap with the said String as an id
            //Finally the byte is sent

            String selection = String.valueOf(persons.getSelectedItem());
            Package sendPackage = new Package();
            byte[] pByte = personList.getMacAddress(selection);
            if (!sendPackage.sendWakeOnLanPackage(pByte)) {
                infoText.setText("Package Failed To Send, consult your IT Admin");
                new JLabelCleaner(1600,infoText).startCountdownFromNow();
            } else {
                infoText.setText("Package Sent ( ͡° ͜ʖ ͡°)");
                new JLabelCleaner(1600,infoText).startCountdownFromNow();
            }
        });

        newWorkstationButton.addActionListener(event -> {
            guiFrame.setVisible(false);
            NewEntryGUI setNew = new NewEntryGUI();
            setNew.newEntry();

        });

        editWorkstationButton.addActionListener(event -> {
            guiFrame.setVisible(false);
            String key = String.valueOf(persons.getSelectedItem());
            String value = personList.getMacAddressString(key);

            EditEntryGui edit = new EditEntryGui();
            edit.editEntry(key, value);
        });

        guiFrame.add(comboPanel, BorderLayout.NORTH);
        guiFrame.add(infoPane, BorderLayout.CENTER);
        guiFrame.add(buttonPane, BorderLayout.SOUTH);
        guiFrame.setVisible(true);
    }
}
