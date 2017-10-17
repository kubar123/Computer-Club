package gui;


import dbConnection.BinaryDriver;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jakub Rybicki
 */
public class mainMenu extends JFrame implements ActionListener{
	//using an icon, adding it to JLabel to show top header IMG
    private static ImageIcon image = new ImageIcon("computerclub.jpg");
    private static JPanel pnlButtons=new JPanel(new GridLayout(2,2));
    private static JLabel backgroundImg = new JLabel(image);//adding icon to label
    
    private static JButton btnAddMember=new JButton("Add new member");
    private static JButton btnDeviceReport=new JButton("Devices report");
    
    private static JButton btnSearchMember=new JButton("Search Member");
    private static JButton btnDetails=new JButton("Member report");
    private static JButton btnExit=new JButton("Exit");

    public mainMenu() {
        super("Main Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        this.setSize(600, 450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height 
                                                / 2 - this.getSize().height / 2);
        this.add(backgroundImg,BorderLayout.NORTH);
        
        pnlButtons.add(btnAddMember);
        pnlButtons.add(btnDetails);
        pnlButtons.add(btnDeviceReport);
        pnlButtons.add(btnExit);
        
        this.add(pnlButtons,BorderLayout.CENTER);
        btnAddMember.addActionListener(this);
        btnDetails.addActionListener(this);
        btnExit.addActionListener(this);
        btnSearchMember.addActionListener(this);
        btnDeviceReport.addActionListener(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        //add log to file
        BinaryDriver a=new BinaryDriver();
        a.writeLog(new Date()+",");// log is current date and time
        new mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAddMember){
            new EditMenu(this);
            this.setVisible(false);
        }else if(e.getSource()==btnDetails){
            new ViewMember(this);
            this.setVisible(false);
            
        }else if(e.getSource()==btnDeviceReport){
            new deviceView(this);
            this.setVisible(false);
        }else if(e.getSource()==btnExit)
            System.exit(0);
    }
    // UNNEEDED - DEL?
    public void setWindowVisible(boolean setState){
        this.setVisible(setState);
    }
}
