/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import members.Device;
import dbConnection.databaseDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jakub Rybicki
 */
public class TableModelDevice extends AbstractTableModel{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    public ArrayList<Device> devices = new ArrayList<>();
    private String[] columns = {
        "Member ID", "Device ID", "CPU", "Dedicated GPU?", "GPU"
    };
    
    public Device getspecificDevice(int id){
        for (Device device : devices) {
            if(device.getDeviceID()==id)
                return device;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return devices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    
    public Device getRow(int row){
        return devices.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Device device=null;
        try{
            Device device = devices.get(rowIndex);
            switch (columnIndex) {
            case 0:
                return device.getMemberId();
            case 1:
                return device.getDeviceID();
            case 2:
                return device.getCpu();
            case 3:
                return device.isDedicatedGpu();
            case 4:
                return device.getGpu();
            }
        }catch(IndexOutOfBoundsException | NullPointerException e){
            System.out.println("everything is ok - exception is expected");
        }
        return null;
    }

    public TableModelDevice() {
        makeTable();
    }

    public void makeTable() {
        
            ResultSet r = databaseDriver.getInfoFromDBDevice();
            devices.clear();
            try {
                while (r.next()) {
                    devices.add(new Device(r.getInt("deviceID"),
                        r.getInt("memberID"),r.getString("cpu"),
                        r.getBoolean("dedicatedGPU"),r.getString("GPU")));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(tableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

