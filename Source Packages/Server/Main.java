package Server;

import java.sql.SQLException;

import org.apache.xmlrpc.WebServer;

public class Main {
	public static void main(String[] args) throws SQLException {
		WebServer ws = new WebServer (1999);
		ws.addHandler("serverRPC", new Server());
		ws.start();
		System.out.println("Server RPC Lebedeva Yana");
	}
}
