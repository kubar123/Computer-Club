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
 * @author Jakub Rybicki
 */
public class deviceView extends JFrame implements ActionListener{
    
    private Container con=this.getContentPane();
    private static JButton btnBack=new JButton("Back");
    private static JButton btnEdit=new JButton("Edit");
    private static JButton btnDelete=new JButton("Delete");


    private JPanel pnlInfo=new JPanel();
    private JTable table=new JTable();
    private JScrollPane scrollPane=new JScrollPane(table);
    private mainMenu menu;
    private String selectedId;
    private static JPanel pnlButtons=new JPanel(new GridLayout(1,3));
    private TableModelDevice tableModel=new TableModelDevice();
    
    public deviceView(mainMenu menu){
        super("Devices");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menu=menu;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height 
                                        / 2 - this.getSize().height / 2);
        table.setModel(tableModel);
        // listens for click of item - which ID to edit/delete
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event){
               //get 1 (2nd) column from the table and assign to selectedID
               String selection=table.getValueAt(table.getSelectedRow(),1).toString();
               selectedId=selection;
            }
        });
        con.setLayout(new BorderLayout());
        con.add(scrollPane, BorderLayout.CENTER);
        con.add(pnlButtons,BorderLayout.SOUTH);

        pnlButtons.add(btnBack);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDelete);
        
        this.setSize(400,200);
        this.setVisible(true);
        //ensure that action event does not fire 2x - might fire 2x (making 1 populated
        // and 1 blank JFrame)
        if (btnBack.getActionListeners().length<1){
            btnBack.addActionListener(this);
            btnDelete.addActionListener(this);
            btnEdit.addActionListener(this);
        }
        this.setVisible(true);
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
            //getting one Device from the already created arrayList in tableModel
            new newDevice(tableModel.getspecificDevice(intSelection));
        }else if(e.getSource()==btnDelete){
            if(selectedId==null){
                JOptionPane.showMessageDialog(null, "Please select something");
                return;
            }
            databaseDriver.deleteDevice(selectedId);
            tableModel=new TableModelDevice();
            table.setModel(tableModel);
            // nothing works.... ....
            //resizing the window does repaint the window
            
            this.validate();
            this.repaint();
            this.revalidate();
            table.validate();
            table.repaint();
            table.revalidate();
            con.validate();
            con.repaint();
            con.revalidate();
            scrollPane.revalidate();
            scrollPane.repaint();
            scrollPane.validate();
            pnlInfo.revalidate();
            pnlInfo.validate();
            pnlInfo.repaint();
            tableModel.fireTableDataChanged(); 

        }
    }
    
}
