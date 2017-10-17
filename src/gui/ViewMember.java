/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbConnection.databaseDriver;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class ViewMember extends JFrame implements ActionListener{
    private Container con=this.getContentPane();
    private static JButton btnBack=new JButton("Back");
    private static JButton btnEdit=new JButton("Edit");
    private static JButton btnDelete=new JButton("Delete");
    private static JButton btnAddDevice=new JButton("Add device");
    
    


    private JPanel pnlInfo=new JPanel();
    private JTable table=new JTable();
    private JScrollPane scrollPane=new JScrollPane(table);
    private mainMenu menu;
    private String selectedId;
    private static JPanel pnlButtons=new JPanel(new GridLayout(2,2));
    private tableModel tableModel=new tableModel();

    
    
    public ViewMember(mainMenu menu){
        super("Members");
        this.menu=menu;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height 
                                                / 2 - this.getSize().height / 2);
        table.setModel(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
           public void valueChanged(ListSelectionEvent event){
               //checking for which row a user clicks on. Getting first element (memberID)
               String selection=table.getValueAt(table.getSelectedRow(),0).toString();
               selectedId=selection;
           }
        });
        con.setLayout(new BorderLayout());
        con.add(scrollPane, BorderLayout.CENTER);
        con.add(pnlButtons,BorderLayout.SOUTH);

        pnlButtons.add(btnBack);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnAddDevice);
        this.setSize(600,200);
        this.setVisible(true);
        
        btnBack.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEdit.addActionListener(this);
        btnAddDevice.addActionListener(this);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            menu.setVisible(true);
            this.dispose();
        }else if(e.getSource()==btnEdit){
            if(selectedId==null){
                JOptionPane.showMessageDialog(null, "Please select something");
                return;
            }
            int intSelection=Integer.parseInt(selectedId);
            new EditMenu(tableModel.getspecificMember(intSelection));
        }
        else if(e.getSource()==btnDelete){
            databaseDriver.deleteMember(selectedId);
            if(selectedId==null){
                JOptionPane.showMessageDialog(null, "Please select something");
                return;
            }
            //-- removed, breaks selection event, and does not work
            //tableModel.fireTableDataChanged(); 
        }else if(e.getSource()==btnAddDevice){
            if(selectedId==null){
                JOptionPane.showMessageDialog(null, "Please select something");
                return;
            }
            new newDevice(Integer.parseInt(selectedId));
        }
    }
    
}
