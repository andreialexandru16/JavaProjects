package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import clinica.app.Exceptii.ExcceptieStatus;
import clinica.app.Exceptii.ExceptieNumePrenume;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Appointments {

	JFrame frmAppointments;
	private JTextField textName;
	private JTextField texSurname;
	private JTable table;
	JLabel lblAlertSurname = new JLabel("");
	JLabel lblAlertName = new JLabel("");
	private JTextField textStatus;
	private JTextField txtNumeP;
	private JTextField txtSurnameP;
	private JTextField textID;
	public JFrame parentFrmAPP;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointments window = new Appointments();
					window.frmAppointments.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Appointments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppointments = new JFrame();
		frmAppointments.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		frmAppointments.setTitle("Appointments");
		frmAppointments.setBounds(100, 100, 1074, 712);
		frmAppointments.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAppointments.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Doctor's appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 1032, 323);
		frmAppointments.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 25, 1008, 285);
		panel.add(scrollPane);
		txtNumeP = new JTextField();
		txtNumeP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				JOptionPane.showMessageDialog(null, "You can't change the patient's name");
				txtNumeP.setFocusable(false);
			}
		});
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectStatusRow=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				String status=model.getValueAt(selectStatusRow, 7).toString();
				if(status.equals("YES")) {
					JOptionPane.showMessageDialog(null,"You can t change an appointment's which is already done");
				}else {
				textStatus.setText(model.getValueAt(selectStatusRow, 7).toString());
				txtNumeP.setText(model.getValueAt(selectStatusRow,3).toString());
				txtSurnameP.setText(model.getValueAt(selectStatusRow,2).toString());
				textID.setText(model.getValueAt(selectStatusRow,5).toString());
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblPleaseIntroduceYour = new JLabel("Please introduce your name");
		lblPleaseIntroduceYour.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour.setBounds(12, 382, 207, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour);
		
		textName = new JTextField();
		textName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String prenume=textName.getText();
					lblAlertName.setText("");
				for(int i=0;i<prenume.length();i++) {
					char c=prenume.charAt(i);
					if(Character.isAlphabetic(c)==false) {
						throw new ExceptieNumePrenume();
					}
				}
				
				}catch(ExceptieNumePrenume exceptieNumePrenume) {
					lblAlertName.setText("Invalid name!");
				}
			}
		});
		textName.setBounds(225, 380, 167, 30);
		frmAppointments.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblPleaseIntroduceYour_2 = new JLabel("Please introduce your surname");
		lblPleaseIntroduceYour_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour_2.setBounds(12, 438, 231, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour_2);
		
		texSurname = new JTextField();
		texSurname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String nume=texSurname.getText();
					lblAlertSurname.setText("");
				for(int i=0;i<nume.length();i++) {
					char c=nume.charAt(i);
					if(Character.isAlphabetic(c)==false) {
						throw new ExceptieNumePrenume();
					}
				}
				
				}catch(ExceptieNumePrenume exceptieNumePrenume) {
					lblAlertSurname.setText("Invalid Surname!");
				}
				
				
			}
		});
		texSurname.setColumns(10);
		texSurname.setBounds(248, 433, 167, 30);
		frmAppointments.getContentPane().add(texSurname);
		
		JButton btnAppointments = new JButton("Show Appointments\r\n");
		btnAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getAppointments();
			}
		});
		btnAppointments.setHideActionText(true);
		btnAppointments.setForeground(Color.DARK_GRAY);
		btnAppointments.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAppointments.setFocusable(false);
		btnAppointments.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAppointments.setBackground(Color.WHITE);
		btnAppointments.setBounds(125, 492, 155, 38);
		frmAppointments.getContentPane().add(btnAppointments);
		
		
		lblAlertName.setForeground(Color.RED);
		lblAlertName.setBounds(225, 409, 167, 16);
		frmAppointments.getContentPane().add(lblAlertName);
		
		
		lblAlertSurname.setForeground(Color.RED);
		lblAlertSurname.setBounds(248, 463, 167, 16);
		frmAppointments.getContentPane().add(lblAlertSurname);
		
		JLabel lblPleaseIntroduceYour_2_1 = new JLabel("Status Appointment");
		lblPleaseIntroduceYour_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour_2_1.setBounds(602, 514, 167, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour_2_1);
		
		textStatus = new JTextField();
		textStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				textStatus.setText("");
			}
		});
		
		textStatus.setColumns(10);
		textStatus.setBounds(771, 512, 167, 30);
		frmAppointments.getContentPane().add(textStatus);
		
		JButton btnChangeStatus = new JButton("Change Status");
		btnChangeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectStatusRow=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				boolean ok=true;
				if(selectStatusRow>=0) {
					try {
						String status=textStatus.getText();
						if(!(textStatus.getText().matches("YES"))){
							throw new ExcceptieStatus();
						}
						model.setValueAt(textStatus.getText(),selectStatusRow,7);
						changeStatus();
						textStatus.setText("");
						textID.setText("");
						txtNumeP.setText("");
						txtSurnameP.setText("");
					}catch(ExcceptieStatus excceptieStatus) {
						JOptionPane.showMessageDialog(null, "To change status you must type YES");
						textStatus.setText("");
					}
					
				}else if(selectStatusRow<0) {
					JOptionPane.showMessageDialog(null,"You must choose an appointment in order to change it");
				}
			}
		});
		btnChangeStatus.setHideActionText(true);
		btnChangeStatus.setForeground(Color.DARK_GRAY);
		btnChangeStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChangeStatus.setFocusable(false);
		btnChangeStatus.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnChangeStatus.setBackground(Color.WHITE);
		btnChangeStatus.setBounds(702, 601, 155, 38);
		frmAppointments.getContentPane().add(btnChangeStatus);
		
		JLabel lblPleaseIntroduceYour_2_1_1 = new JLabel("Patient's Name");
		lblPleaseIntroduceYour_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour_2_1_1.setBounds(602, 433, 127, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour_2_1_1);
		
		
		txtNumeP.setColumns(10);
		txtNumeP.setBounds(771, 431, 167, 30);
		frmAppointments.getContentPane().add(txtNumeP);
		
		JLabel lblPleaseIntroduceYour_2_1_1_1 = new JLabel("Patient's Surname");
		lblPleaseIntroduceYour_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour_2_1_1_1.setBounds(602, 476, 142, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour_2_1_1_1);
		
		txtSurnameP = new JTextField();
		txtSurnameP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JOptionPane.showMessageDialog(null, "You can't change the patient's surname");
				txtSurnameP.setFocusable(false);
			}
		});
		txtSurnameP.setColumns(10);
		txtSurnameP.setBounds(771, 474, 167, 30);
		frmAppointments.getContentPane().add(txtSurnameP);
		
		JLabel lblPleaseIntroduceYour_2_1_1_1_1 = new JLabel("ID Appointment");
		lblPleaseIntroduceYour_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceYour_2_1_1_1_1.setBounds(602, 392, 142, 25);
		frmAppointments.getContentPane().add(lblPleaseIntroduceYour_2_1_1_1_1);
		
		textID = new JTextField();
		textID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JOptionPane.showMessageDialog(null, "You can't change the appointment's id");
				textID.setFocusable(false);
			}
		});
		textID.setColumns(10);
		textID.setBounds(771, 384, 167, 30);
		frmAppointments.getContentPane().add(textID);
	}
	
	public void changeStatus() {
		String idPrg=textID.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psSt=connection.prepareStatement("update programari\r\n" + 
					"Set programare_efectuata='1'\r\n" + 
					"where programari.id_programare='" + idPrg +"'");
			int resultSet=psSt.executeUpdate();
			if(resultSet>0) {
				JOptionPane.showMessageDialog(null, "Status has been changed!");
			}else {
				JOptionPane.showMessageDialog(null, "Status hasn't been changed!");
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		
	}
	public void getAppointments() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Doctor's Surname");
		model.addColumn("Doctor's Name");
		model.addColumn("Patient's Surname");
		model.addColumn("Patient's Name");
		model.addColumn("Patient's Age");
		model.addColumn("Appointment ID");
		model.addColumn("Appointment Date");
		model.addColumn("Appointment Done");
		String numeDoctor=texSurname.getText();
		String prenumeDoctor=textName.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			
			PreparedStatement psApoit=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,pacienti.nume,pacienti.prenume,pacienti.varsta,programari.id_programare,programari.data_programare,if(programari.programare_efectuata,'YES','NO')programare_efectuata\r\n" + 
					"					From programari\r\n" + 
					"					JOIN angajati on angajati.angajat_id=programari.angajat_id \r\n" + 
					"					JOIN pacienti on pacienti.pacient_id=programari.pacient_id\r\n" + "where angajati.nume='" + numeDoctor +"'and angajati.prenume='" + prenumeDoctor + "'");
			ResultSet resultSet=psApoit.executeQuery();
			
			while(resultSet.next()) {
				Object[] row=new Object[8];
				row[0]=resultSet.getString("nume");
				row[1]=resultSet.getString("prenume");
				row[2]=resultSet.getString("pacienti.nume");
				row[3]=resultSet.getString("pacienti.prenume");
				row[4]=resultSet.getInt("varsta");
				row[5]=resultSet.getInt("id_programare");
				row[6]=resultSet.getDate("data_programare");
				row[7]=resultSet.getString("programare_efectuata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
			
			
			
		
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		
		
	}
}
