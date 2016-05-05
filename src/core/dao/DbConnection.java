package core.dao;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


//import oracle.jdbc.driver.OracleDriver;

public class DbConnection {
	
	private static String dbSID     = "orcl";
    private static String hostName  = "192.168.1.13";
    private static String userName  = "test1";
    private static String userPwd   = "ericeric";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@"+ hostName + ":1521:" + dbSID;
		try {
			con = DriverManager.getConnection(url, userName, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(ResultSet rs, Statement st, Connection conn) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!= null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
