package JdbcConnection;

import java.sql.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * purpose:jdbc connection from eclipse to mssql server.
 */

public class jdbcConnection {
	private Connection con;
	private static Statement st;
	private static ResultSet rs;

	@BeforeClass
	void setup() throws Exception {

		// To register or load jdbc driver.
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		/**
		 * URL to establish the connection. ServerName:ATMECSINDT-083\\SQLEXPRESS.
		 * Database Name:Student Integratedsecurity:true which means it takes windows
		 * account credential.
		 */
		String connectionUrl = "jdbc:sqlserver://ATMECSINDT-083\\SQLEXPRESS;database=Student;integratedSecurity=true;";

		@SuppressWarnings("unused")
		// To establish the connection.
		Connection con = DriverManager.getConnection(connectionUrl);
		System.out.println("Connected to the Database...");
	}
	
    /**
     * Get student information from student database.
     */
	@Test
	void getStudentData() throws SQLException {

		st = con.createStatement();
		rs = st.executeQuery("select * from Student");

		while (rs.next()) {
			int id = rs.getInt("Student_ID");
			String name = rs.getString("Student_Name");
			String dept = rs.getString("Student_Dept");

			System.out.println(id + " " + name + " " + dept);
		}
		rs.close();
	}
	/**
	 *Close the database
	 */

	@AfterTest
	void closeDatabase() throws Exception {
		if (con != null) {
			System.out.println("closing the database...");
			con.close();
		}
	}

}
