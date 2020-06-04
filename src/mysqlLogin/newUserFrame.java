package mysqlLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class newUserFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private sqlFucctions sql = new sqlFucctions();
	
	public newUserFrame(boolean logsucc) {
		Font font = new Font("Verdana", Font.PLAIN, 16);
		JPanel panel = new JPanel();
		JLabel name = new JLabel("ΟΝΟΜΑ");
		name.setFont(font);
		JTextField namefield = new JTextField(40);
		namefield.setFont(font);
		JLabel eponimo = new JLabel("ΕΠΩΝΥΜΟ");
		eponimo.setFont(font);
		JTextField eponimofield = new JTextField(40);
		eponimofield.setFont(font);
		JLabel username = new JLabel("ΟΝΟΜΑ ΧΡΗΣΤΗ");
		username.setFont(font);
		JTextField usernamefield = new JTextField(40);
		usernamefield.setFont(font);
		JLabel password = new JLabel("ΚΩΔΙΚΟΣ");
		password.setFont(font);
		JPasswordField passfield = new JPasswordField(40);	
		passfield.setEchoChar('*');
		passfield.setFont(font);
		JLabel repeatpassword = new JLabel("ΕΠΑΛΗΘΕΥΣΗ ΚΩΔΙΚΟΥ");
		repeatpassword.setFont(font);
		JPasswordField passfield2 = new JPasswordField(40);
		passfield2.setEchoChar('*');
		passfield2.setFont(font);
		JLabel email = new JLabel("ΔΙΕΥΘΗΝΣΗ EMAIL");
		email.setFont(font);
		JTextField emailfield = new JTextField(40);
		emailfield.setFont(font);
		JLabel privilage = new JLabel("ΔΙΚΑΙΩΜΑΤΑ ΠΡΟΣΒΑΣΗΣ ΧΡΗΣΤΗ ΣΤΟ ΠΡΟΓΡΑΜΜΑ");
		privilage.setFont(font);
		String[] dik = new String[] {"client", "admin"};
		JComboBox<String> privilagechoises = new JComboBox<>(dik);
		JButton btnok = new JButton("ΑΠΟΘΗΚΕΥΣΗ");
		btnok.setFont(font);
		JButton btnclear = new JButton("ΚΑΘΑΡΙΣΜΟΣ ΠΕΔΙΩΝ");
		btnclear.setFont(font);
		privilagechoises.setForeground(Color.BLACK);
		privilagechoises.setFont(font);
			
		panel.add(name);
		panel.add(namefield);
		panel.add(eponimo);
		panel.add(eponimofield);
		panel.add(username);
		panel.add(usernamefield);
		panel.add(password);
		panel.add(passfield);
		panel.add(repeatpassword);
		panel.add(passfield2);
		panel.add(email);
		panel.add(emailfield);
		panel.add(privilage);
		panel.add(privilagechoises);
		panel.add(btnok);
		panel.add(btnclear);
		
		btnok.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if((namefield.getText().equals("")) || (eponimofield.getText().equals("")) || (usernamefield.getText().equals("")) || (passfield.getText().equals("")) || (passfield2.getText().equals("")) || (emailfield.getText().equals(""))) {
					JOptionPane.showMessageDialog(panel, "ΔΕΝ ΜΠΟΡΕΙ ΝΑ ΕΙΝΙΑ ΚΕΝΑ ΚΑΝΕΝΑ ΑΠΟ ΤΑ ΠΕΔΙΑ",
							"ΣΦΑΛΜΑ", JOptionPane.INFORMATION_MESSAGE);
				}else if (!passfield.getText().equals(passfield2.getText())) {
					JOptionPane.showMessageDialog(panel, "Ο ΚΩΔΙΚΟΣ ΠΡΟΣΒΑΣΗΣ ΔΕΝ ΣΥΜΠΙΠΤΕΙ ΜΕ ΤΗΝ ΕΠΑΛΗΘΕΥΣΗ ΤΟΥ",
							"ΣΦΑΛΜΑ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String onoma = namefield.getText();
					String epon = eponimofield.getText();
					String user = usernamefield.getText();
					String pass = passfield.getText();
					String email = emailfield.getText();
					String d = String.valueOf(privilagechoises.getSelectedItem());
					int i = sql.WritetoDB(onoma, epon, user, pass, email, d);
					if (i == 0) {
						JOptionPane.showMessageDialog(panel, "Υπαρχεί ήδη εγγραφή με αυτό το Επώνυμο.",
								"ΠΛΗΡΟΦΟΡΙΕΣ", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(panel, "Επιτυχής Αποθήκευση στην Βάση Δεδομένων!!!",
								"ΠΛΗΡΟΦΟΡΙΕΣ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
    	});
		
		btnclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				namefield.setText("");
				eponimofield.setText("");
				usernamefield.setText("");
				passfield.setText("");
				passfield2.setText("");
				emailfield.setText("");	
			}
    	});
		
		this.setContentPane(panel);   
		this.setVisible(true);
		this.setSize(680, 550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("ΕΙΣΑΓΩΓΗ ΝΕΟΥ ΧΡΗΣΤΗ");
	}
}
