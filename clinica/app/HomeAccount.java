package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeAccount {

	JFrame frmHomeAccountant;
	public JFrame parentHomeAcount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAccount window = new HomeAccount();
					window.frmHomeAccountant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeAccountant = new JFrame();
		frmHomeAccountant.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-accounting-16.png"));
		frmHomeAccountant.setTitle("Home Accountant");
		frmHomeAccountant.setBounds(100, 100, 932, 630);
		frmHomeAccountant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmHomeAccountant.setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sing out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHomeAccountant.setVisible(false);
				parentHomeAcount.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-sign-out-16.png"));
		mnHome.add(mntmNewMenuItem_1);
		
		JMenu mnPayments = new JMenu("Payments");
		mnPayments.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-card-payment-16.png"));
		menuBar.add(mnPayments);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Payment");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddPayments window = new AddPayments();
							window.frmAddPayment.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-add-property-16.png"));
		mnPayments.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("View Payments");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewPayments window = new ViewPayments();
							window.frmViewPayments.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-view-16.png"));
		mnPayments.add(mntmNewMenuItem_2);
	}

}
