package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.ComponentOrientation;

public class RemoveEmployee {

	JFrame frmRemoveEmployee;
	private JTextField textSurname;
	private JTextField textFirstName;
	private JTextField textCNP;
	private JTextField textMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEmployee window = new RemoveEmployee();
					window.frmRemoveEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RemoveEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveEmployee = new JFrame();
		frmRemoveEmployee.getContentPane().setBackground(Color.WHITE);
		frmRemoveEmployee.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-remove-16.png"));
		frmRemoveEmployee.setTitle("Remove Employee");
		frmRemoveEmployee.setBounds(100, 100, 835, 726);
		frmRemoveEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRemoveEmployee.getContentPane().setLayout(null);
		
		JLabel lblEmployeeInformation = new JLabel("Employee Information");
		lblEmployeeInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployeeInformation.setFont(new Font("Arial", Font.BOLD, 24));
		lblEmployeeInformation.setBounds(274, 13, 543, 62);
		frmRemoveEmployee.getContentPane().add(lblEmployeeInformation);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(274, 109, 82, 30);
		frmRemoveEmployee.getContentPane().add(lblNewLabel_1_1);
		
		textSurname = new JTextField();
		textSurname.setColumns(10);
		textSurname.setBounds(376, 106, 429, 38);
		frmRemoveEmployee.getContentPane().add(textSurname);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("FirstName");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(274, 180, 82, 30);
		frmRemoveEmployee.getContentPane().add(lblNewLabel_1_1_1);
		
		textFirstName = new JTextField();
		textFirstName.setColumns(10);
		textFirstName.setBounds(376, 177, 429, 38);
		frmRemoveEmployee.getContentPane().add(textFirstName);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CNP\r\n");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(274, 246, 82, 30);
		frmRemoveEmployee.getContentPane().add(lblNewLabel_1_1_1_1);
		
		textCNP = new JTextField();
		textCNP.setColumns(10);
		textCNP.setBounds(376, 243, 429, 38);
		frmRemoveEmployee.getContentPane().add(textCNP);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\administrator_delete_resize.png"));
		lblNewLabel.setBounds(12, 13, 250, 260);
		frmRemoveEmployee.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Remarks");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1.setBounds(274, 322, 82, 30);
		frmRemoveEmployee.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		textMessage = new JTextField();
		textMessage.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textMessage.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textMessage.setColumns(10);
		textMessage.setBounds(376, 321, 429, 228);
		frmRemoveEmployee.getContentPane().add(textMessage);
		
		JButton btnSendRequest = new JButton("Sent Request");
		btnSendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textCNP.getText().isEmpty() || textFirstName.getText().isEmpty() || textMessage.getText().isEmpty() || textSurname.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				}else {
				sendRequest();
				}
				
			}
		});
		btnSendRequest.setHideActionText(true);
		btnSendRequest.setForeground(Color.DARK_GRAY);
		btnSendRequest.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSendRequest.setFocusable(false);
		btnSendRequest.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSendRequest.setBackground(Color.WHITE);
		btnSendRequest.setBounds(321, 602, 155, 53);
		frmRemoveEmployee.getContentPane().add(btnSendRequest);
	}
	public void sendRequest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			String nume=textSurname.getText();
			String prenume=textFirstName.getText();
			String cnp=textCNP.getText();
			String mesaj=textMessage.getText();
			PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `dbclinica`.`cerererezilierecontract` (`nume_angajat`, `prenume_angajat`, `cnp`, `mesaj`) VALUES ('" + nume +"', '" + prenume+"', '" +cnp +"', '" + mesaj +"')");
			int resultSet=preparedStatement.executeUpdate();
			if(resultSet>0) {
				JOptionPane.showMessageDialog(null, "The request has been sent!");
			}else {
				JOptionPane.showMessageDialog(null, "The request hasn't been sent!");
			}
			textCNP.setText("");
			textFirstName.setText("");
			textMessage.setText("");
			textSurname.setText("");
			connection.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

}
