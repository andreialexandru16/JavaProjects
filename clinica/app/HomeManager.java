package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class HomeManager {

	JFrame frameHomeManager;
	public JFrame parentframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeManager window = new HomeManager();
					window.frameHomeManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameHomeManager = new JFrame();
		frameHomeManager.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-home-16.png"));
		frameHomeManager.setBackground(new Color(0, 191, 255));
		frameHomeManager.getContentPane().setBackground(Color.WHITE);
		frameHomeManager.setTitle("Home Manager");
		frameHomeManager.setBounds(100, 100, 975, 648);
		frameHomeManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(224, 255, 255));
		frameHomeManager.setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		mnHome.setForeground(new Color(0, 0, 0));
		menuBar.add(mnHome);
		
		JMenuItem mntmSingOut = new JMenuItem("Sing out");
		mntmSingOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameHomeManager.setVisible(false);
				parentframe.setVisible(true);
			}
			
		});
		mntmSingOut.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-sign-out-16.png"));
		mnHome.add(mntmSingOut);
		
		JMenu mnNewMenu = new JMenu("Finance");
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Payments");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Payments window = new Payments();
							window.frmPayments.setVisible(true);
							window.jframeParents=frameHomeManager;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\r\nFiscal Value");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
							"root");
					PreparedStatement preparedStatement=connection.prepareStatement("SELECT SUM(programari.cost) \r\n" + 
							"FROM programari\r\n" + 
							"WHERE programare_efectuata=1");
					ResultSet resultSet=preparedStatement.executeQuery();
					int suma=0;
					while(resultSet.next()) {
						suma=resultSet.getInt(1);
					}
					JOptionPane.showMessageDialog(null, "Total income till now is:"+ " " + suma+" "+"RON.");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error database connection");
				}
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-profit-16.png"));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-card-payment-16.png"));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Bonus\r\n");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "The doctor who hits the target receive 15% of the subtraction between total income of the selected period and doctor's income in that period.\r\n" + 
						"If the substraction is zero the bonus will be a free lunch." );
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-gift-16.png"));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Human Resources");
		mnNewMenu_1.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Employees");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Employees window = new Employees();
							window.frmEmployees.setVisible(true);
							window.parentEmployees=frameHomeManager;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Doctor's Appointments");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DoctorsAppointments window = new DoctorsAppointments();
							window.frmDoctorsAppointments.setVisible(true);
							window.frmParent=frameHomeManager;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JMenuItem mntmRequests = new JMenuItem("Requests");
		mntmRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Requests window = new Requests();
							window.frmRequests.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
				
			}
		});
		mntmRequests.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-message-exchange-16.png"));
		mnNewMenu_1.add(mntmRequests);
		mntmNewMenuItem_3.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-gender-neutral-employee-group-16.png"));
		mnNewMenu_1.add(mntmNewMenuItem_1);
	}

}
