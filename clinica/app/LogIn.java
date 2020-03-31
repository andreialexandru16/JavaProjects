package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

public class LogIn {

	private JFrame frmLogIn;
	private JTextField txtUser;
	private JButton btnlogin;
	private JPasswordField txtpassword;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblSelectUserType;
	JComboBox cmbType = new JComboBox();
	JProgressBar prgBar = new JProgressBar();
	private Timer time;
	int cont;
	public static final int one_seconds =1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frmLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public LogIn() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogIn = new JFrame();
		frmLogIn.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-login-64.png"));
		frmLogIn.setType(Type.POPUP);
		frmLogIn.setTitle("Login");
		frmLogIn.setBounds(100, 100, 702, 558);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		final JPasswordField field = new JPasswordField(20);
		panel.setForeground(Color.GRAY);
		frmLogIn.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtUser = new JTextField();
		txtUser.setBounds(10, 38, 212, 32);
		panel.add(txtUser);
		txtUser.setColumns(10);

		btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logIn();

			}

			class TimerListener implements ActionListener {

				@Override
				public void actionPerformed(ActionEvent e) {
					cont++;
					prgBar.setValue(cont);
					if (cont == 100) {
						time.stop();
					}

				}

			}

			private void logIn() {
				String User = txtUser.getText();
				String Password = txtpassword.getText();
				try {
					if (txtUser.getText().isEmpty() || txtpassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "User or password field is empty");
					}
					prgBar.setVisible(true);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
							"root");
					PreparedStatement ps;
					String type = String.valueOf(cmbType.getSelectedItem());
					ps = connection.prepareStatement("Select * from login WHERE user='" + User + "'and parola='"
							+ Password + "'and usertype='" + type + "'");
					ResultSet resultSet = ps.executeQuery();
					if (resultSet.next()) {
						cont = 0;
						prgBar.setValue(0);
						prgBar.setStringPainted(true);
						time = new Timer(one_seconds, new TimerListener());
						time.start();
						JOptionPane.showMessageDialog(null, "Login Successesfully!");
						if (cmbType.getSelectedIndex() == 0) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										HomeManager window = new HomeManager();
										window.frameHomeManager.setVisible(true);
										window.parentframe = frmLogIn;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							frmLogIn.setVisible(false);

						} else if(cmbType.getSelectedIndex()==1) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										HomeDoctor window = new HomeDoctor();
										window.frmHomedoctor.setVisible(true);
										window.parentHomeDoctor=frmLogIn;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							frmLogIn.setVisible(false);
						}else if(cmbType.getSelectedIndex()==2) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										HomeAccount window = new HomeAccount();
										window.frmHomeAccountant.setVisible(true);
										window.parentHomeAcount=frmLogIn;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							frmLogIn.setVisible(false);
						}else if(cmbType.getSelectedIndex()==3) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										HomeHR window = new HomeHR();
										window.frmHomeHr.setVisible(true);
										window.parentHR=frmLogIn;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							frmLogIn.setVisible(false);
						}
							
						
					}else {
						JOptionPane.showMessageDialog(null, "Login failed!Be careful at username and password and don t forget to select the corect department");
					}
					connection.close();
				} catch (Exception exception) {
					JOptionPane.showConfirmDialog(null, "Database conncetion error");
				}finally {
					txtUser.setText("");
					txtpassword.setText("");
					prgBar.setVisible(false);
					prgBar.setValue(0);
				}

			}
		});
		btnlogin.setBounds(79, 305, 109, 32);
		panel.add(btnlogin);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(10, 105, 212, 32);
		panel.add(txtpassword);

		JCheckBox chkPassShow = new JCheckBox("Show Password");
		chkPassShow.setBackground(Color.WHITE);
		chkPassShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkPassShow.isSelected()) {
					txtpassword.setEchoChar((char) 0);
				} else {
					txtpassword.setEchoChar('*');
				}
			}
		});
		chkPassShow.setBounds(10, 146, 127, 25);
		panel.add(chkPassShow);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(272, 54, 244, 283);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel show_image = new JLabel("");
		show_image.setForeground(Color.WHITE);
		show_image.setBackground(Color.WHITE);
		show_image.setIcon(new ImageIcon(LogIn.class.getResource("/images.png")));
		show_image.setBounds(12, 0, 232, 270);
		panel_1.add(show_image);

		lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 13, 105, 32);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 83, 114, 32);
		panel.add(lblNewLabel_1);

		lblSelectUserType = new JLabel("Select user type:");
		lblSelectUserType.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 13));
		lblSelectUserType.setBounds(10, 180, 145, 25);
		panel.add(lblSelectUserType);

		cmbType.setBackground(Color.WHITE);
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Doctor", "Accountant", "HR", "Secretar"}));
		cmbType.setSelectedIndex(0);
		cmbType.setBounds(149, 180, 114, 24);
		panel.add(cmbType);
		prgBar.setStringPainted(true);
		prgBar.setVisible(false);
		prgBar.setBounds(10, 235, 261, 32);
		panel.add(prgBar);

	}
}
