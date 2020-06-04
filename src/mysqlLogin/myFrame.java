package mysqlLogin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class myFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private String dikaiomata;
	private String succ;
	private boolean logsucc=false;
	
	sqlFucctions sql = new sqlFucctions();
	
	// gui 
	private JPanel panel;
	private JLabel username;
	private JLabel password;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnok;
	private JButton btnclear;
	
	public myFrame() {
		Font font = new Font("Verdana", Font.PLAIN, 16);
		panel = new JPanel();
		username = new JLabel("ΟΝΟΜΑ ΧΡΗΣΤΗ");
		password = new JLabel("ΚΩΔΙΚΟΣ ΠΡΟΣΒΑΣΗΣ");
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		btnok = new JButton("ΣΥΝΔΕΣΗ");
		btnclear = new JButton("ΚΑΘΑΡΙΣΜΟΣ ΠΕΔΙΩΝ");
		
		panel.add(username);
		username.setFont(font);
		panel.add(usernameField);
		usernameField.setFont(font);
		panel.add(password);
		password.setFont(font);
		panel.add(passwordField);
		passwordField.setFont(font);
		passwordField.setEchoChar('*');
		panel.add(btnok);
		btnok.setFont(font);
		panel.add(btnclear);
		btnclear.setFont(font);
		
		ButtonListener listener = new ButtonListener();
		btnok.addActionListener(listener);
		
		this.setContentPane(panel);   
		this.setVisible(true);
		this.setSize(450, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ΦΟΡΜΑ ΣΥΝΔΕΣΗΣ - LOG IN");		
	}
	
	class ButtonListener implements ActionListener{
		//@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnok)){
				String givenUsername = usernameField.getText();
				String givenPassword = String.valueOf(passwordField.getPassword());
				//κενα τα πεδια ονομα χρηστη κια κωδικου προσβασης
				if((givenUsername.equals("")) || (givenPassword.equals(""))) {
					JOptionPane.showMessageDialog(panel, "ΔΕΝ ΜΠΟΡΕΙ ΝΑ ΕΙΝΙΑ ΚΕΝΑ ΤΑ ΠΕΔΙΑ ΟΝΟΜΑ ΧΡΗΣΤΗ ΚΑΙ ΚΩΔΙΚΟΥ ΠΡΟΣΒΑΣΗΣ",
							"ΣΦΑΛΜΑ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					succ = sql.connect(givenUsername, givenPassword);
					if (succ.equals("admin")) {
						dikaiomata = "ΔΙΑΧΕΙΡΗΣΤΗ";
						JOptionPane.showMessageDialog(panel, "ΚΑΛΩΣΟΡΙΣΑΤΕ " + dikaiomata + " " + givenUsername,
								"ΚΑΛΩΣΟΡΙΣΑΤΕ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false); //αορατο
						dispose(); //Καταστροφη του JFrame
						logsucc = true;
						new adminFrame(logsucc);
						
					}else if (succ.equals("client")) {
						dikaiomata = "client";
						JOptionPane.showMessageDialog(panel, "ΚΑΛΩΣΟΡΙΣΑΤΕ " + " " + givenUsername,
								"ΚΑΛΩΣΟΡΙΣΑΤΕ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false); //αορατο
						dispose(); //Καταστροφη του JFrame
					}else if (succ.equals("-1")) {
						JOptionPane.showMessageDialog(panel, "ΛΑΘΟΣ ΟΝΟΜΑ ΧΡΗΣΤΗ Ή ΚΩΔΙΚΟΥ ΠΡΟΣΒΑΣΗΣ\n ΠΑΡΑΚΑΛΩ ΞΑΝΑΔΟΚΙΜΑΣΤΕ Ή ΕΠΙΚΟΙΝΩΝΗΣΤΕ ΜΕ ΤΟΝ ΔΙΑΧΕΙΡΗΣΤΗ",
								"ΣΦΑΛΜΑ", JOptionPane.INFORMATION_MESSAGE);
					}else if (succ.equals("-2")) {
						JOptionPane.showMessageDialog(panel, "Ο ΛΟΓΑΡΙΑΣΜΟΣ ΕΙΝΑΙ ΚΛΕΙΔΩΜΕΝΟΣ ΕΠΙΚΟΙΝΩΝΗΣΤΕ ΜΕ ΤΟΝ ΔΙΑΧΕΙΡΗΣΤΗ",
								"ΣΦΑΛΜΑ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}