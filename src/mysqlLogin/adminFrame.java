package mysqlLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class adminFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public adminFrame(boolean logsucc) {
		JPanel panel = new JPanel();
		JMenuBar menu = new JMenuBar();
		
		this.setJMenuBar(menu);
		
		JMenu filemenu = new JMenu("File");
		JMenu Editmenu = new JMenu("Edit");
		
		JMenuItem f1 = new JMenuItem("Νέος Χρήστης");
		JMenuItem f2 = new JMenuItem("Προβολή Στοιχείων Χρήστη");
		JMenuItem f3 = new JMenuItem("Αλλαγή Στοιχείων Χρήστη");
		JMenuItem f4 = new JMenuItem("Πληροφοριες");
		JMenuItem f5 = new JMenuItem("Αποσύνδεση");
		JMenuItem f6 = new JMenuItem("Έξοδος");
		
		menu.add(filemenu);
		menu.add(Editmenu);
		
		filemenu.add(f1);
		filemenu.add(f2);
		filemenu.add(f3);
		filemenu.add(f4);
		filemenu.add(f5);
		filemenu.add(f6);
		
		f1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new newUserFrame(logsucc);
			}
    	});
		
		f6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //αορατο
				dispose(); //Καταστροφη του JFrame
			}
    	});
		if (logsucc) {
			this.setContentPane(panel);   
			this.setVisible(true);
			this.setSize(450, 450);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setTitle("ΠΑΡΑΘΥΡΟ ΔΙΑΧΕΙΡΗΣΤΗ");
		}
	}
}
