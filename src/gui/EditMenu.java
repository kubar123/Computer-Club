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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import members.Gamer;
import members.GraphicDesigner;
import members.Member;

/**
 *
 * @author Jakub Rybicki
 */
public class EditMenu extends JFrame implements ActionListener {

    private mainMenu previousMenu;
    private boolean newMember = false;
    public static JRadioButton radGamer = new JRadioButton("Gamer");
    public static JRadioButton radDesigner = new JRadioButton("Designer");
    public static ButtonGroup grpRadGamer = new ButtonGroup();
    public static JRadioButton radMale = new JRadioButton("Male");
    public static JRadioButton radFemale = new JRadioButton("Female");
    public static ButtonGroup grpGender = new ButtonGroup();
    private static JPanel pnlForm = new JPanel(new FlowLayout());

    private static JLabel lblFee = new JLabel();
    private static JLabel lblSeperator = new JLabel();
    public static JLabel lblFName = new JLabel("First Name");
    public static JTextField txfFName = new JTextField(8);
    public static JLabel lblLName = new JLabel("Last Name");
    public static JTextField txfLName = new JTextField(8);
    public static JLabel lblAge = new JLabel("               Age");
    public static JTextField txfAge = new JTextField(8);
    public static JLabel lblStreetNo = new JLabel("        Street #");
    public static JTextField txfStreetNo = new JTextField(8);
    public static JLabel lblStreetName = new JLabel("Street Name");
    public static JTextField txfStreetName = new JTextField(8);
    public static JLabel lblSuburb = new JLabel("Suburb");
    public static JTextField txfSuburb = new JTextField(8);
    public static JLabel lblPostCode = new JLabel("     Post Code");
    public static JTextField txfPostCode = new JTextField(8);

    public static JLabel lblFavGame = new JLabel("Favourite game");
    public static JTextField txfFavGame = new JTextField(8);
    public static JLabel lblFavGenre = new JLabel("Favourite Genre");
    public static JTextField txfFavGenre = new JTextField(8);
    public static JLabel lblFavSoftware = new JLabel("Favorite Software");
    public static JTextField txfFavSoftware = new JTextField(8);

    public static JButton btnSubmit = new JButton("Submit");
    public static JButton btnBack = new JButton("Back");
    public static JLabel lblFeeSeperator = new JLabel("     ");

    private boolean isNewAddition=false;

    public static String[] stateString = {
        "VIC",
        "NSW",
        "QLD",
        "WA",
        "TAS",
        "SA",
        "NT"
    };

    public static JComboBox cboState = new JComboBox(stateString);

    public EditMenu(Member member) {
        super("Add new details");
        newMember=false;
        beginingThis();//add layouts, and items to JFrame
        // ------ set text values -------
        txfFName.setText(member.getFirstName());
        txfLName.setText(member.getLastName());
        txfAge.setText("" + member.getAge());
        txfStreetNo.setText("" + member.getStreetNo());
        txfStreetName.setText(member.getStreetName());
        txfSuburb.setText(member.getSuburb());
        if (member.isGamer()) {
            radGamer.setSelected(true);
        } else {
            radDesigner.setSelected(true);
        }
        if (member.isMale()) {
            radMale.setSelected(true);
        } else {
            radFemale.setSelected(true);
        }
        btnBack.setVisible(false);
        
        //------- end set text values -----

    }

    public EditMenu(mainMenu menu) {
        super("Add new details");
        newMember = true; // we are adding a new member - falses if editing
        previousMenu = menu;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        beginingThis();
    }

    public void beginingThis() {
        this.setLayout(new BorderLayout());

        this.setSize(220, 450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
            dim.height / 2 - this.getSize().height / 2);
        //set default selected options
        addItemsToMenu();
        //pressing enter counts as submit
        this.getRootPane().setDefaultButton(btnSubmit);
        radGamer.addActionListener(this);
        radDesigner.addActionListener(this);
        //default set on gamer, setVisible to appropriate things
        setGamer(true);
        radGamer.setSelected(true);
        radMale.setSelected(true);

        this.add(pnlForm, BorderLayout.CENTER);
        btnBack.addActionListener(this);
        btnSubmit.addActionListener(this);

        this.setVisible(true);
    }

    public void setGamer(boolean isGamer) {
        if (isGamer) {
            lblFee.setText("$140.00");
        } else {
            lblFee.setText("$350.00");
        }
        lblFavGame.setVisible(isGamer);
        txfFavGame.setVisible(isGamer);
        lblFavGenre.setVisible(isGamer);
        txfFavGenre.setVisible(isGamer);
        lblFavSoftware.setVisible(!isGamer);
        txfFavSoftware.setVisible(!isGamer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //update links when something changes
        setGamer(radGamer.isSelected());
        
        if (e.getSource() == btnBack) {
            previousMenu.setVisible(true);
            this.setVisible(false);
        }
       
        if (e.getSource() == btnSubmit) {
            if(!isAllDataValid())// checking for valid data
                return;
            
            if(newMember){
                if (radGamer.isSelected()) {
                        Gamer a = makeMember();//inserting a member (gamer)
                        databaseDriver.insertMember(makeMember());
                    } else {// inserting a member (GraphicDesigner)
                        GraphicDesigner a = makeMember(false);
                        databaseDriver.insertMember(a);
                }
            }else{
                if (radGamer.isSelected()) {
                    Gamer a = makeMember();
                    databaseDriver.updateMember(makeMember());
                } else {
                    GraphicDesigner a = makeMember(false);
                    databaseDriver.updatemember(a);
                }
            }
        }
            
    }

    public Gamer makeMember() {
        return getObject(radGamer.isSelected());
    }
// unneeded boolean; if boolean exists (any value), we are making a graphicDesigner
    public GraphicDesigner makeMember(boolean t) {
        return getObject();
    }

    public Gamer getObject(boolean isGamer) {
        Gamer member = new Gamer();
        member = (Gamer) setMemberInfo(member);
        member.setFavGame(txfFavGame.getText());
        member.setFavGenre(txfFavGenre.getText());
        return member;
    }

    public GraphicDesigner getObject() {
        GraphicDesigner member = new GraphicDesigner();
        member = (GraphicDesigner) setMemberInfo(member);
        member.setFavouriteSoftware(txfFavSoftware.getText());
        System.out.println(member.toString());
        return member;
    }

    public void addItemsToMenu() {
        grpRadGamer.add(radGamer);
        grpRadGamer.add(radDesigner);
        grpGender.add(radFemale);
        grpGender.add(radMale);

        pnlForm.add(radGamer);
        pnlForm.add(radDesigner);

        pnlForm.add(lblFName);
        pnlForm.add(txfFName);
        pnlForm.add(lblLName);
        pnlForm.add(txfLName);

        pnlForm.add(radMale);
        pnlForm.add(radFemale);

        pnlForm.add(lblSeperator);

        pnlForm.add(lblAge);
        pnlForm.add(txfAge);
        pnlForm.add(lblStreetNo);
        pnlForm.add(txfStreetNo);
        pnlForm.add(lblStreetName);
        pnlForm.add(txfStreetName);
        pnlForm.add(lblSuburb);
        pnlForm.add(txfSuburb);
        pnlForm.add(cboState);

        pnlForm.add(lblFavGame);
        pnlForm.add(txfFavGame);
        pnlForm.add(lblFavGenre);
        pnlForm.add(txfFavGenre);
        pnlForm.add(lblFavSoftware);
        pnlForm.add(txfFavSoftware);

        pnlForm.add(btnSubmit);
        pnlForm.add(btnBack);
        pnlForm.add(lblFeeSeperator);
        pnlForm.add(lblFee);

    }

    public Member setMemberInfo(Member member) {
        member.setFirstName(txfFName.getText());
        member.setLastName(txfLName.getText());
        member.setMale(radMale.isSelected());
        member.setAge((short) Integer.parseInt(txfAge.getText()));
        member.setStreetNo(txfStreetNo.getText());
        member.setStreetName(txfStreetName.getText());
        member.setSuburb(txfSuburb.getText());
        member.setState(cboState.getSelectedItem().toString());
        return member;
    }
// ------------------------------------ validation -----------------------------------------
    public boolean isAllDataValid() {
        if (txfFName.getText().length() < 2 | txfFName.getText() == "") {
            JOptionPane.showMessageDialog(null, "Error, name must be atleast 2 characters");
            return false;
        } else if (txfLName.getText().length() < 2 | txfLName.getText() == "") {
            JOptionPane.showMessageDialog(null, "Error, name must be atleast 2 characters");
            return false;
        } else if (txfStreetNo.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Error, Street number is required");
            return false;
        } else if (txfStreetName.getText().length()<2) {
            JOptionPane.showMessageDialog(null, "Error, Street name is required");
            return false;
        } else if (txfSuburb.getText().length() <2) {
            JOptionPane.showMessageDialog(null, "Error, Suburb is required");
            return false;
        }

        try {
            int age =Integer.parseInt(txfAge.getText());
            if(age<0 | age > 150){
                JOptionPane.showMessageDialog(null, "Error, Age must be between 0 and 150");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error, Age must be a valid number");
            return false;
        }
        try {
            Integer.parseInt(txfStreetNo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error, Street number must be a valid number");
            return false;
        }
        return true;
    }
    // ------------------------------------end of validation -------------------------------------

}
