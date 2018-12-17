package com.chan.chanandbori;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://35.189.158.41:3306/chanwithboridb?useSSL=false";
	private static final String USER = "chanwithbori";
	private static final String PASSWORD = "1234";

	@Test
	public void testMySQLConnection() throws Exception {
		
		Class.forName(DRIVER); //Returns the Class object associated with the class or interface with the given string name. 
		
		try ( Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){
			
			System.out.println(con);	//com.mysql.jdbc.JDBC4Connection@63e31ee

		} catch ( Exception e ) {
			
			e.printStackTrace();
		}

	}

}
