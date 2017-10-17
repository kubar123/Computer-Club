/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbConnection.databaseDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import members.Member;

/**
 *
 * @author Jakub Rybicki
 */
public class tableModel extends AbstractTableModel {

    public ArrayList<Member> members = new ArrayList<>();
    private String[] columns = {
        "ID", "First Name", "Last Name", "Male?", "Age", "Street #", "Street name",
        "suburb", "state"
    //, "favourite Game", "favourite genre","favourite software"
    };

    public Member getspecificMember(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return members.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    public Member getRow(int row) {
        return members.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member place = members.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return place.getMemberId();
            case 1:
                return place.getFirstName();
            case 2:
                return place.getLastName();
            case 3:
                return place.isMale();
            case 4:
                return place.getAge();
            case 5:
                return place.getStreetNo();
            case 6:
                return place.getStreetName();
            case 7:
                return place.getSuburb();
            case 8:
                return place.getState();
        }
        return null;
    }

    public tableModel() {
        makeTable();
    }

    public void makeTable() {

        ResultSet r = databaseDriver.getInfoFromDB();
        members.clear(); // reset members list

        try {
            while (r.next()) {
                //adding items to arraylist to populate JTable
                members.add(new Member(140.0, r.getInt("memberId"), r.getString("firstName"),
                    r.getString("lastName"), r.getBoolean("isMale"),
                    r.getShort("age"), r.getString("streetNo"),
                    r.getString("streetName"), r.getString("suburb"),
                    r.getString("state"), r.getBoolean("isGamer")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}