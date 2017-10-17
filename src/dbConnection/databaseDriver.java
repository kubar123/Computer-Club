package dbConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import members.Device;
import members.Gamer;
import members.GraphicDesigner;

/**
 *
 * @author Jakub Rybicki
 */
public class databaseDriver {

    static Connection conDB;
    static Statement stmt;
    static ResultSet r;
    static PreparedStatement pstmt;
    public static ResultSet connect(String username) {
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            //use prepared statement - prevent SQL injection
            pstmt = conDB.prepareStatement("SELECT * FROM members where memberID = ?");
            pstmt.setString(1, username);

            r = pstmt.executeQuery();

            return r;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("A critical error has occurred");
            //LoginScreen.setEndInfo("Critical error has occurred");
        }
        return null;
    }
    public static void updateDevice(int memberID,Device device){
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            if(device.isDedicatedGpu()){
                pstmt = conDB.prepareStatement("update device set cpu=?,dedicatedGPU=?,GPU=? where deviceID=?");
                pstmt.setString(1, device.getCpu());
                pstmt.setString(2, "1");
                pstmt.setString(3, device.getGpu());
                pstmt.setString(4, device.getDeviceID()+"");
            }else{
                pstmt = conDB.prepareStatement("update device set cpu=?,dedicatedGPU=0 where deviceID=?");
                pstmt.setString(1, device.getCpu());
                pstmt.setString(2, device.getDeviceID()+"");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void updateMember(Gamer memberInfo){
        try {
            String maleNo="0";
            if(memberInfo.isMale())
                maleNo="1";
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("update members set `firstName`=?, `lastName`=?,"
                + " `isMale`=?, `age`=?, `streetNo`=?, `streetName`=?, `suburb`=?, `state`=?,"
                + " `fee`=?, `isGamer`=?, `favouriteGame`=?, `favouriteGenre`=? where memberID=?;");
            pstmt.setString(1, memberInfo.getMemberId() + "");
            pstmt.setString(2, memberInfo.getFirstName());
            pstmt.setString(3, memberInfo.getLastName());
            pstmt.setString(4, maleNo);
            pstmt.setString(5, memberInfo.getAge() + "");
            pstmt.setString(6, memberInfo.getStreetNo() + "");
            pstmt.setString(7, memberInfo.getStreetName());
            pstmt.setString(8, memberInfo.getSuburb());
            pstmt.setString(9, memberInfo.getState());
            pstmt.setString(10, memberInfo.getFee() + "");
            pstmt.setString(11, "1");
            pstmt.setString(12, memberInfo.getFavGame());
            pstmt.setString(13, memberInfo.getFavGenre());
            pstmt.setString(14, memberInfo.getMemberId()+"");
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void insertMember(Gamer memberInfo) {
        try {
            
            String maleNo="0";
            if(memberInfo.isMale())
                maleNo="1";
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("insert into members (`memberID`, `firstName`, `lastName`,"
                + " `isMale`, `age`, `streetNo`, `streetName`, `suburb`, `state`,"
                + " `fee`, `isGamer`, `favouriteGame`, `favouriteGenre`,"
                + " `favouriteSoftware`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            pstmt.setString(1, memberInfo.getMemberId() + "");
            pstmt.setString(2, memberInfo.getFirstName());
            pstmt.setString(3, memberInfo.getLastName());
            pstmt.setString(4, maleNo);
            pstmt.setString(5, memberInfo.getAge() + "");
            pstmt.setString(6, memberInfo.getStreetNo() + "");
            pstmt.setString(7, memberInfo.getStreetName());
            pstmt.setString(8, memberInfo.getSuburb());
            pstmt.setString(9, memberInfo.getState());
            pstmt.setString(10, memberInfo.getFee() + "");
            pstmt.setString(11, "1");
            pstmt.setString(12, memberInfo.getFavGame());
            pstmt.setString(13, memberInfo.getFavGenre());
            pstmt.setString(14, "");
             pstmt.executeUpdate();
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteDevice(String idtoDelete){
        System.out.println("Try to delete: "+idtoDelete);
        try{
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt=conDB.prepareStatement("DELETE FROM device where deviceID = ?");
            pstmt.setString(1, idtoDelete);
            System.out.println(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteMember(String idToDelete){
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt=conDB.prepareStatement("delete from members where memberID = ?;");
            pstmt.setString(1, idToDelete);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void insertDevice(int memberID, Device device){
        try {
             Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            if(device.isDedicatedGpu()){
                pstmt = conDB.prepareStatement("insert into device (`memberID`,"
                    + "`cpu`, `dedicatedGPU`,`GPU`) values(?,?,1,?);");
                pstmt.setString(1, memberID+"");
                pstmt.setString(2, device.getCpu());
                pstmt.setString(3, device.getGpu());
                System.out.println(pstmt);
                pstmt.executeUpdate();
            }else{           
                pstmt = conDB.prepareStatement("insert into device (`memberID`,"
                    + "`cpu`, `dedicatedGPU`) VALUES(?,?,?)");
                pstmt.setString(1, memberID+"");
                pstmt.setString(2, device.getCpu());
                pstmt.setString(3, "0");
                pstmt.executeUpdate();

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updatemember(GraphicDesigner memberInfo){
        String maleNo="0";
        if(memberInfo.isMale())
            maleNo="1";
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("update members (`firstName`=?, `lastName`=?,"
                + " `isMale`=?, `age`=?, `streetNo`=?, `streetName`=?, `suburb`=?, `state`=?,"
                + " `fee`=?, `isGamer`=0,"
                + " where memberID=?;");
            pstmt.setString(1, memberInfo.getFirstName());
            pstmt.setString(2, memberInfo.getLastName());
            pstmt.setString(3, maleNo);
            pstmt.setString(4, memberInfo.getAge() + "");
            pstmt.setString(5, memberInfo.getStreetNo() + "");
            pstmt.setString(6, memberInfo.getStreetName());
            pstmt.setString(7, memberInfo.getSuburb());
            pstmt.setString(8, memberInfo.getState());
            pstmt.setString(9, memberInfo.getFee() + "");
            pstmt.setString(10, memberInfo.getFavouriteSoftware());
            pstmt.setString(11, memberInfo.getMemberId()+"");
            
            pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertMember(GraphicDesigner memberInfo) {
        String maleNo="0";
        if(memberInfo.isMale())
            maleNo="1";
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
                ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("insert into members (`memberID`, `firstName`, `lastName`,"
                + " `isMale`, `age`, `streetNo`, `streetName`, `suburb`, `state`,"
                + " `fee`, `isGamer`, `favouriteGame`, `favouriteGenre`,"
                + " `favouriteSoftware`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            pstmt.setString(1, memberInfo.getMemberId() + "");
            pstmt.setString(2, memberInfo.getFirstName());
            pstmt.setString(3, memberInfo.getLastName());
            pstmt.setString(4, maleNo);
            pstmt.setString(5, memberInfo.getAge() + "");
            pstmt.setString(6, memberInfo.getStreetNo() + "");
            pstmt.setString(7, memberInfo.getStreetName());
            pstmt.setString(8, memberInfo.getSuburb());
            pstmt.setString(9, memberInfo.getState());
            pstmt.setString(10, memberInfo.getFee() + "");
            pstmt.setString(11, "0");
            pstmt.setString(12, "");
            pstmt.setString(13, "");
            pstmt.setString(14, memberInfo.getFavouriteSoftware());
            pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static ResultSet getInfoFromDB() {
        try {
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
            ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("Select * from members");
            //stmt.setString(1, "*");
            r=pstmt.executeQuery();
//            r.next();
//            System.out.println(r.getString("firstName"));
            
            return r;

            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR:::::::::::::::::::");
        }
        return null;
    }
    
    public static ResultSet getInfoFromDBDevice(){
        try{
            Class.forName(ConnectionDetails.getDriver());
            conDB = DriverManager.getConnection(ConnectionDetails.getURL(),
            ConnectionDetails.getUsername(), ConnectionDetails.getPassword());
            pstmt = conDB.prepareStatement("Select * from device");
            r=pstmt.executeQuery();
            return r;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(databaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
