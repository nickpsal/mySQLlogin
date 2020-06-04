package mysqlLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlLogin {
	
	passwordEncrypt encrypt = new passwordEncrypt();
	public String connect(String givenUsername, String givenPassword) {
		String hashedPassword;
		String succ = "-1";
		String priv;
		//URL ΣΥΝΔΕΣΗΣ
		String url = "jdbc:mysql://localhost:3306/demosql?serverTimezone=UTC&characterEncoding=UTF-8";
		//ΟΝΟΜΑ ΧΡΗΣΤΗ ΚΑΙ ΚΩΔΙΚΟΣ ΠΡΟΣΑΒΑΣΗΣ
		String user = "root";
		String pass = "";
		//ΕΡΩΤΗΜΑ
		hashedPassword = encrypt.encrypt(givenPassword);
		String query = "Select * from users Where username='" + givenUsername + "' and password='"+ hashedPassword +"'";
		try {
			// 1. Σύνδεση με την βάση δεδομένων
			Connection myConn = DriverManager.getConnection(url, user, pass);
			// 2. Δημιουρηία statement
			Statement myStmt = myConn.createStatement();
			// 3. Εκτελεση Qyuery
			ResultSet myRs = myStmt.executeQuery(query);
			
			if (myRs.next())
		    {
				priv = myRs.getString("priv");
				if (priv.equals("admin")) {
					succ = "admin";
				}else {
					succ = "client";
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
}
