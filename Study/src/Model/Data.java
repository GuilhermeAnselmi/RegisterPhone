package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
	private static String connection_DB = "jdbc:sqlserver://den1.mssql8.gear.host:1433;databaseName=invictustest;user=invictustest;password=test@123";
	
	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(connection_DB); Statement stmt = con.createStatement();) {
			String SQL = "select * from test";
	        ResultSet rs = stmt.executeQuery(SQL);
	        
	        rs.next();
	        System.out.println(rs.getString("name"));
	        
	        //int rs = stmt.executeUpdate("insert into test([name]) values('Luciano')");
	        //System.out.println(rs);
	        
	        //System.out.println(rs.getRow());
	        
	        //while (rs.next()) {
	        //    System.out.println(rs.getString("name"));
	        //}
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static String[] Read(String query) {
		String[] value = new String[2];
		
		try {
			Connection conn = DriverManager.getConnection(connection_DB);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	
	        rs.next();
	        value[0] = rs.getString("name");
	        value[1] = rs.getString("phone");
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	        value[0] = "";
	    }
		return value;
	}
	
	public static int Write(String query) {
		int rs;
		
		try {
			Connection conn = DriverManager.getConnection(connection_DB);
			Statement stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			rs = 0;
		}
		
		return rs;
	}
}
