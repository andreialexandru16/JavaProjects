package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeDoctor {

	JFrame frmHomedoctor;
	public JFrame parentHomeDoctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeDoctor window = new HomeDoctor();
					window.frmHomedoctor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeDoctor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomedoctor = new JFrame();
		frmHomedoctor.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-home-16.png"));
		frmHomedoctor.setTitle("Home Doctor");
		frmHomedoctor.setBounds(100, 100, 838, 581);
		frmHomedoctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmHomedoctor.setJMenuBar(menuBar);
		
		JMenu mnSingOut = new JMenu("Home\r\n");
		menuBar.add(mnSingOut);
		
		JMenuItem mntmSingOut = new JMenuItem("Sing out");
		mntmSingOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHomedoctor.setVisible(false);
				parentHomeDoctor.setVisible(true);
				
			}
		});
		mntmSingOut.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-sign-out-16.png"));
		mnSingOut.add(mntmSingOut);
		
		JMenu mnPacien = new JMenu("My account\r\n");
		menuBar.add(mnPacien);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\r\nAppointments");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Appointments window = new Appointments();
							window.frmAppointments.setVisible(true);
							window.parentFrmAPP=frmHomedoctor;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		mnPacien.add(mntmNewMenuItem);
	}

}
