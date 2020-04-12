package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import clinica.app.Exceptii.ExceptieDoarNumar;
import clinica.app.Exceptii.ExceptieLispaCNP;
import clinica.app.Exceptii.ExceptieNumarCuZero;
import clinica.app.Exceptii.ExceptieNumePrenume;
import clinica.app.Exceptii.ExceptieSalariu;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEmployee {

	JFrame frmAddEmployee;
	private JTextField textSurname;
	private JTextField textFirstname;
	private JTextField textCNP;
	private JTextField textSalariu;
	private JComboBox combJobTitle;
	private JTextField textPhoneNumber;
	private JLabel lblAlertSurname;
	private JLabel lblAlertFirstName;
	private JLabel lblAlertCNP;
	private JLabel lblAlertSalary;
	private JLabel lblAlertPhoenNumber;
	private JComboBox comboDep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee window = new AddEmployee();
					window.frmAddEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddEmployee = new JFrame();
		frmAddEmployee.getContentPane().setBackground(Color.WHITE);
		frmAddEmployee.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-add-16.png"));
		frmAddEmployee.setTitle("Add Employee");
		frmAddEmployee.setBounds(100, 100, 852, 693);
		frmAddEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddEmployee.getContentPane().setLayout(null);

		JLabel lblDa = new JLabel("");
		lblDa.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\AddEmployee.png"));
		lblDa.setBounds(12, 13, 255, 241);
		frmAddEmployee.getContentPane().add(lblDa);

		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonalInformation.setFont(new Font("Arial", Font.BOLD, 24));
		lblPersonalInformation.setBounds(279, 31, 543, 62);
		frmAddEmployee.getContentPane().add(lblPersonalInformation);

		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(279, 101, 82, 30);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1);

		textSurname = new JTextField();
		textSurname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String surname = textSurname.getText();
					lblAlertSurname.setText("");
					for (int i = 0; i < surname.length(); i++) {
						char c = surname.charAt(i);
						if (Character.isAlphabetic(c) == false) {
							throw new ExceptieNumePrenume();
						}
					}
				} catch (ExceptieNumePrenume exceptieNumePrenume) {
					lblAlertSurname.setText("Invalid Surname!");

				}

			}
		});
		textSurname.setColumns(10);
		textSurname.setBounds(393, 98, 429, 38);
		frmAddEmployee.getContentPane().add(textSurname);

		JLabel lblNewLabel_1_1_1 = new JLabel("FirstName");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(279, 155, 82, 30);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1);

		textFirstname = new JTextField();
		textFirstname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String firstName = textFirstname.getText();
					lblAlertFirstName.setText("");
					for (int i = 0; i < firstName.length(); i++) {
						char c = firstName.charAt(i);
						if (Character.isAlphabetic(c) == false) {
							throw new ExceptieNumePrenume();
						}
					}

				} catch (ExceptieNumePrenume exceptieNumePrenume) {
					lblAlertFirstName.setText("Invalid FirstName!");
				}

			}
		});
		textFirstname.setColumns(10);
		textFirstname.setBounds(393, 149, 429, 38);
		frmAddEmployee.getContentPane().add(textFirstname);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("CNP\r\n");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(279, 213, 82, 30);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1_1);

		textCNP = new JTextField();
		textCNP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String cnp = textCNP.getText();
					lblAlertCNP.setText("");
					if (cnp.length() == 0) {
						throw new ExceptieLispaCNP();
					}
				} catch (ExceptieLispaCNP cnp) {
					lblAlertCNP.setText("Invalid CNP!");
				}
			}
		});
		textCNP.setColumns(10);
		textCNP.setBounds(393, 205, 429, 38);
		frmAddEmployee.getContentPane().add(textCNP);

		JLabel lblJobInformation = new JLabel("Job Information");
		lblJobInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblJobInformation.setFont(new Font("Arial", Font.BOLD, 24));
		lblJobInformation.setBounds(279, 267, 543, 62);
		frmAddEmployee.getContentPane().add(lblJobInformation);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Job Title\r\n");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1.setBounds(279, 342, 93, 35);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1_1_1);

		combJobTitle = new JComboBox();
		combJobTitle.setBackground(Color.WHITE);
		combJobTitle.setFocusable(false);
		combJobTitle.setFont(new Font("Arial", Font.BOLD, 16));
		combJobTitle.setModel(new DefaultComboBoxModel(new String[] { "Pediatrician", "Dentist", "Ophthalmologist",
				"Accountant", "Human resources specialist", "Secretary" }));
		combJobTitle.setSelectedIndex(0);
		combJobTitle.setBounds(393, 339, 429, 38);
		frmAddEmployee.getContentPane().add(combJobTitle);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Salary");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(279, 412, 93, 35);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1_1_1_1);

		textSalariu = new JTextField();
		textSalariu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String salariu = textSalariu.getText();
					lblAlertSalary.setText("");
					for (int i = 0; i < salariu.length(); i++) {
						char c = salariu.charAt(i);
						if (salariu.charAt(0) == '0') {
							throw new ExceptieSalariu();
						}
						if (Character.isDigit(c) == false) {
							throw new ExceptieSalariu();
						}

					}
				} catch (ExceptieSalariu exceptieSalariu) {
					lblAlertSalary.setText("Invalid Salary!");
				}
			}
		});
		textSalariu.setColumns(10);
		textSalariu.setBounds(393, 409, 429, 38);
		frmAddEmployee.getContentPane().add(textSalariu);

		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Job Department\r\n");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(240, 477, 132, 35);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1_1_1_2);

		comboDep = new JComboBox();
		comboDep.setBackground(Color.WHITE);
		comboDep.setFocusable(false);
		comboDep.setModel(new DefaultComboBoxModel(
				new String[] { "Pediatrics", "Dentistry", "Ophthalmology", "Accountancy", "HR", "Secretariat" }));
		comboDep.setSelectedIndex(0);
		comboDep.setFont(new Font("Arial", Font.BOLD, 16));
		comboDep.setBounds(393, 474, 429, 38);
		frmAddEmployee.getContentPane().add(comboDep);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Phone Number");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(250, 539, 122, 35);
		frmAddEmployee.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);

		textPhoneNumber = new JTextField();
		textPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String numarTelefon = textPhoneNumber.getText();
					lblAlertPhoenNumber.setText("");
					for (int i = 0; i < numarTelefon.length(); i++) {
						char c = numarTelefon.charAt(i);
						if (Character.isDigit(c) == false) {
							throw new ExceptieDoarNumar();
						}

					}
				} catch (ExceptieDoarNumar doarNumar) {
					lblAlertPhoenNumber.setText("Invalid number!");
				}
			}
		});
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(393, 538, 429, 38);
		frmAddEmployee.getContentPane().add(textPhoneNumber);

		lblAlertSurname = new JLabel("");
		lblAlertSurname.setForeground(Color.RED);
		lblAlertSurname.setBounds(279, 125, 102, 16);
		frmAddEmployee.getContentPane().add(lblAlertSurname);

		lblAlertFirstName = new JLabel("");
		lblAlertFirstName.setForeground(Color.RED);
		lblAlertFirstName.setBounds(279, 180, 102, 16);
		frmAddEmployee.getContentPane().add(lblAlertFirstName);

		lblAlertCNP = new JLabel("");
		lblAlertCNP.setForeground(Color.RED);
		lblAlertCNP.setBounds(279, 238, 102, 16);
		frmAddEmployee.getContentPane().add(lblAlertCNP);

		lblAlertSalary = new JLabel("");
		lblAlertSalary.setForeground(Color.RED);
		lblAlertSalary.setBounds(279, 437, 102, 16);
		frmAddEmployee.getContentPane().add(lblAlertSalary);

		lblAlertPhoenNumber = new JLabel("");
		lblAlertPhoenNumber.setForeground(Color.RED);
		lblAlertPhoenNumber.setBounds(248, 567, 102, 16);
		frmAddEmployee.getContentPane().add(lblAlertPhoenNumber);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textCNP.getText().isEmpty() || textFirstname.getText().isEmpty()
						|| textPhoneNumber.getText().isEmpty() || textSalariu.getText().isEmpty()
						|| textSurname.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				} else {
					saveEmployee();
				}
			}
		});
		btnSave.setHideActionText(true);
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setFocusable(false);
		btnSave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(42, 567, 155, 53);
		frmAddEmployee.getContentPane().add(btnSave);
	}

	public void saveEmployee() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			String surname = textSurname.getText();
			String firstname = textFirstname.getText();
			String cnp = textCNP.getText();
			String numarTel = textPhoneNumber.getText();
			String salary = textSalariu.getText();
			int job = chooseJob();
			int dep = chooseDep();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO `dbclinica`.`angajati` (`nume`, `prenume`, `telefon`, `job_id`, `salariu`, `departament_id`, `CNP`) VALUES ('"
							+ surname + "','" + firstname + "','" + numarTel + "' , '" + job + "', '" + salary + "', '"
							+ dep + "', '" + cnp + "');");
			int resultSet = preparedStatement.executeUpdate();
			if (resultSet > 0) {
				JOptionPane.showMessageDialog(null, "Employee has been added!");
				int nr = 0;
				nr = numarAngajati(dep);
				increaseEmployee(nr, dep);
			} else {
				JOptionPane.showMessageDialog(null, "Employee hasn't been added!");
			}
			textCNP.setText("");
			textFirstname.setText("");
			textPhoneNumber.setText("");
			textSalariu.setText("");
			textSurname.setText("");
			combJobTitle.setSelectedIndex(0);
			comboDep.setSelectedIndex(0);
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");

		}
	}

	public int numarAngajati(int idDep) {
		int numar = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("Select numar_angajati from departamente where departament_id='" + idDep + "'");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				numar = resultSet.getInt("numar_angajati");
			}
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		return numar;
	}

	public void increaseEmployee(int numarAngajati, int idDep) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			numarAngajati++;
			PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `dbclinica`.`departamente` SET `numar_angajati` ='"+ numarAngajati+"' WHERE (`departament_id` = '" +idDep+"')");
			int resultSet=preparedStatement.executeUpdate();
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public int chooseDep() {
		int dep = 0;
		switch (comboDep.getSelectedIndex()) {
		case 0:
			dep = 1;
			break;
		case 1:
			dep = 2;
			break;
		case 2:
			dep = 3;
			break;
		case 3:
			dep = 4;
			break;
		case 4:
			dep = 5;
			break;
		case 5:
			dep = 6;
			break;
		}
		return dep;
	}

	public int chooseJob() {
		int job = 0;
		switch (combJobTitle.getSelectedIndex()) {
		case 0:
			job = 1;
			break;
		case 1:
			job = 2;
			break;
		case 2:
			job = 3;
			break;
		case 3:
			job = 4;
			break;
		case 4:
			job = 5;
			break;
		case 5:
			job = 6;
			break;
		}
		return job;
	}

}
