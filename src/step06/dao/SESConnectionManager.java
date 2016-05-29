package step06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SESConnectionManager {

	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String user = "hr";
	private static String password = "hr";
	
	private static SESConnectionManager cm = new SESConnectionManager();
	
	private SESConnectionManager(){}
	
	public static SESConnectionManager getInstance(){
		return cm;
	}
			
	static{
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection con = null;
		try {
			con= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
}
