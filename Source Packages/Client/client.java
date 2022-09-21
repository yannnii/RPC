package Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class client {
	Vector <String> vector = null;
	XmlRpcClient client = null;
	String serverUrl;
	
	public client() throws MalformedURLException{
		serverUrl = "https://localhost:8080/RPC2";
		client = new XmlRpcClient(serverUrl);
		vector = new Vector<String>();
	}
	
	void showData() {
		String ar = "";
		try {
			ar = (String) client.execute("serverRPC.getData", vector);
		} catch (XmlRpcException ex) {
			Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
		}
		StringTokenizer st = new StringTokenizer (ar, "-");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
	
	void insertData() {
		String ar = "";
		try {
			vector.add("Test1");
			vector.add("Test2");
			vector.add("Test3");
			ar = (String) client.execute("serverRPC.insertData", vector);
			vector = new Vector<>();
		} catch (XmlRpcException ex) {
			Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(ar);
		
	}
	public static void main(String[] args) throws MalformedURLException {
		client c = new client();
		c.showData();
	}
}
