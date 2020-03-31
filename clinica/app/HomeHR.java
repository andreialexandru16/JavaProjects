package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeHR {

	JFrame frmHomeHr;
public JFrame parentHR;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeHR window = new HomeHR();
					window.frmHomeHr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeHR() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeHr = new JFrame();
		frmHomeHr.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-change-employee-female-16.png"));
		frmHomeHr.setTitle("Home HR");
		frmHomeHr.setBounds(100, 100, 873, 709);
		frmHomeHr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmHomeHr.setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sing out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHomeHr.setVisible(false);
				parentHR.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-sign-out-16.png"));
		mnHome.add(mntmNewMenuItem_1);
		
		JMenu mnEmployees = new JMenu("Employees");
		menuBar.add(mnEmployees);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View Employees");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewEmployees window = new ViewEmployees();
							window.frmViewEmployees.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-gender-neutral-employee-group-16.png"));
		mnEmployees.add(mntmNewMenuItem);
	}

}
