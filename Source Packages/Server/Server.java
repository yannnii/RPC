
package Server;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

public class Server {
	
	public Server() throws SQLException {
		Connect();
	}
	private Connection connection;
	private void Connect() throws SQLException {
		String db = "jdbc:mysql://localhost/rpc";
		String user = "root";
		String pass = "root";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = (Connection) DriverManager.getConnection(db, user, pass);
			 if (connection != null) {
				 System.out.println("Success connection");
			 }
		} catch (SQLException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public String getData() {
		String data = "";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM biodata");
			while (rs.next()) {
				data += rs.getInt(1)+"."+rs.getString(2)+"."+rs.getString(3)+"."+rs.getString(4)+"-";
			}
		} catch (SQLException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return data;
	}
	public String insertData(String name, String age, String position) {
		String data = "";
		String sql = "INSERT INTO biodata VALUES (null, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, age);
			ps.setString(3, position);
			int num = ps.executeUpdate();
			if (num>0) {
				data = "Success";
			}
			else {
				data = "Error";
			}
		} catch (SQLException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return data;
	}
}
