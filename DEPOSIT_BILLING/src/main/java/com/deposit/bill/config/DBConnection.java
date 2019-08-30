package com.deposit.bill.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	@SuppressWarnings("unused")
	public static Connection getDBConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			// Creating the connection
			String url = "jdbc:mysql://localhost:3306/sys?sslMode=DISABLED";
			String user = "anirudh";
			String pass = "Mumbai@123";
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			return con;

		} catch (Exception ex) {
			System.err.println(ex);
			return null;
		}
	}
}
