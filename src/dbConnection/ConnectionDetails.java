package dbConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author kubar
 */
public class ConnectionDetails {
	private static final String username="root";
	private static final String password="root";
	//What database are we interacrtingwith.
	private static final String driver="com.mysql.jdbc.Driver";
	//url of the database
	private static final String url="jdbc:mysql://localhost:3306/computerclub?autoReconnect=true";
	
	public static String getUsername(){
		return username;
	}
	public static String getPassword(){
		return password;
	}
	public static String getDriver(){
		return driver;
	}
	public static String getURL(){
		return url;
	}
	
}
