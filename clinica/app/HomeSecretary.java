package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeSecretary {
     JFrame parentFrame;
	JFrame frmHomeSecretary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeSecretary window = new HomeSecretary();
					window.frmHomeSecretary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeSecretary() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeSecretary = new JFrame();
		frmHomeSecretary.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-secretary-woman-16.png"));
		frmHomeSecretary.setTitle("Home Secretary");
		frmHomeSecretary.setBounds(100, 100, 808, 669);
		frmHomeSecretary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeSecretary.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 790, 26);
		frmHomeSecretary.getContentPane().add(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		JMenuItem mntmSingOut = new JMenuItem("Sing out");
		mntmSingOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHomeSecretary.setVisible(false);
				parentFrame.setVisible(true);
			}
		});
		mntmSingOut.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-sign-out-16.png"));
		mnHome.add(mntmSingOut);
		
		JMenu mnAppointments = new JMenu("Appointments");
		menuBar.add(mnAppointments);
		
		JMenuItem mntmViewAppointments = new JMenuItem("View Appointments");
		mntmViewAppointments.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		mnAppointments.add(mntmViewAppointments);
	}
}
