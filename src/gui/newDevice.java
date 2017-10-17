/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbConnection.databaseDriver;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import members.Device;

/**
 *
 * @author Jakub Rybicki
 */
public class newDevice extends JFrame implements ActionListener {

    private mainMenu menu;
    private boolean isNewRecord = false; // are we adding a new record, or updating old one?
    private static int memberID;

    private static JLabel lblCpu = new JLabel("CPU");
    private static JTextField txfCpu = new JTextField(8);
    private static JLabel lblDedicatedGpu = new JLabel("GPU ");
    private static JCheckBox chkDedicatedGpu = new JCheckBox("Dedicated GPU?");
    private static JTextField txfGpu = new JTextField(8);
    private static JButton btnSubmit = new JButton("Submit");
    private static JButton btnBack = new JButton("Back");
    private static JPanel pnlForm = new JPanel(new FlowLayout());

    newDevice(Device device) {
        super("Edit device");
        isNewRecord = false; // updating old record
        basicFormCompletion();
        //set text to field
        txfCpu.setText(device.getCpu());
        if (device.isDedicatedGpu()) {
            chkDedicatedGpu.setSelected(true);
        } else {
            txfGpu.setVisible(false);
            lblDedicatedGpu.setVisible(false);
        }
        txfGpu.setText(device.getGpu());
        txfCpu.setText(device.getCpu());
        // ----- end of set text -------

        this.add(pnlForm, BorderLayout.CENTER);
        this.setVisible(true);
        btnBack.setVisible(false);//no back needed

    }

    newDevice(int memberID) {
        super("Add new device");
        this.memberID = memberID;
        isNewRecord = true;
        this.menu = menu;

        txfGpu.setVisible(chkDedicatedGpu.isSelected());
        lblDedicatedGpu.setVisible(chkDedicatedGpu.isSelected());
        basicFormCompletion();

        this.add(pnlForm, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void basicFormCompletion() {
        this.setLayout(new BorderLayout());
        this.setSize(150, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
            dim.height / 2 - this.getSize().height / 2);

        pnlForm.add(lblCpu);
        pnlForm.add(txfCpu);
        pnlForm.add(chkDedicatedGpu);
        pnlForm.add(txfGpu);
        pnlForm.add(lblDedicatedGpu);
        pnlForm.add(txfGpu);

        pnlForm.add(btnSubmit);
        pnlForm.add(btnBack);

        btnSubmit.addActionListener(this);
        chkDedicatedGpu.addActionListener(this);

    }

    public Device makeDevice() {
        Device a = new Device(memberID, txfCpu.getText(),
            chkDedicatedGpu.isSelected(), txfGpu.getText());
        return a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //show, if checkbox is checked
        lblDedicatedGpu.setVisible(chkDedicatedGpu.isSelected());
        txfGpu.setVisible(chkDedicatedGpu.isSelected());
        
        if (e.getSource() == btnSubmit) {
            if(!isAllValidated())
                return;
            if (isNewRecord) {
                databaseDriver.insertDevice(memberID, makeDevice());
            } else {
                databaseDriver.updateDevice(memberID, makeDevice());
            }

            this.dispose();
        }
    }

    public boolean isAllValidated() {
        //only required field is CPU, GPU is optional.
        if (txfCpu.getText().length() < 2) {
            JOptionPane.showMessageDialog(null, "Error, CPU must be at least 2 characters");
            return false;
        }
        return true;
    }
}
