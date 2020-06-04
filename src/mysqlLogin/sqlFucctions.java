package mysqlLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlFucctions {
	private String user = "root";
	private String pass = "";
	passwordEncrypt encrypt = new passwordEncrypt();
	private String url = "jdbc:mysql://localhost:3306/demosql?serverTimezone=UTC&characterEncoding=UTF-8";

	public String connect(String givenUsername, String givenPassword) {
		
		String hashedPassword;
		String succ = "-1";
		String priv;
		//ΕΡΩΤΗΜΑ
		hashedPassword = encrypt.encrypt(givenPassword);
		String query = "Select * from users Where username='" + givenUsername + "' and password='"+ hashedPassword +"'";
		try {
			// 1. Σύνδεση με την βάση δεδομένων
			Connection myConn = DriverManager.getConnection(url, user, pass);
			// 2. Δημιουργία statement
			Statement myStmt = myConn.createStatement();
			// 3. Εκτελεση Qyuery
			ResultSet myRs = myStmt.executeQuery(query);
			if (myRs.next())
		    {	
				int blocked = myRs.getInt("blocked");
				if (blocked == 1) {
					succ = "-2";
				}else {
					priv = myRs.getString("priv");
					if (priv.equals("admin")) {
						succ = "admin";
					}else {
						succ = "client";
					}
				}
				
		    }else {
		    	succ = "-1";
		    }
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return succ;
	}
	
	public int WritetoDB(String name, String epon, String username, String Password, String email, String d) {

		String hashedPassword;
		//ΕΡΩΤΗΜΑ
		hashedPassword = encrypt.encrypt(Password);
		String query = "INSERT INTO USERS (id, onoma,eponimo,username, password , email, priv, blocked) VALUES (DEFAULT,?,?,?,?,?,?,0)";
		try {
			// 1. Σύνδεση με την βάση δεδομένων
			Connection myConn = DriverManager.getConnection(url, user, pass);
			// 2. Δημιουρηία statement
			PreparedStatement stmt=myConn.prepareStatement(query);  
			
			stmt.setString(1, name);
			stmt.setString(2, epon);
			stmt.setString(3, username);
			stmt.setString(4, hashedPassword);
			stmt.setString(5, email);
			stmt.setString(6, d);
			
			int i = stmt.executeUpdate();
			return i;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return 0;
	}
}
