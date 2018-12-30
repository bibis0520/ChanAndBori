package com.chan.chanandbori;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConnectionTest {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@35.200.69.237:1521:XE";
	private static final String USER = "CHAN";
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
