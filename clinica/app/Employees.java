package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import clinica.app.Exceptii.ExceptieDoarNumar;
import clinica.app.Exceptii.ExceptieNumarCuZero;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JCheckBox;

public class Employees {

	JFrame frmEmployees;
	private JTable table;
	private JTextField textSalary;
	JComboBox cmbSalVar = new JComboBox();
	private JTextField textSalaryAvanced;
	private JTextField textSalaryAdvanced2;
	private JTextField textRaportEmployees;
	JLabel lblAlert = new JLabel("");
	JLabel lblAlertStartSalary = new JLabel("");
	JLabel lblAlertSalary2 = new JLabel("\r\n");
	JComboBox cmbDepartamente = new JComboBox();
	JCheckBox chckbxDep = new JCheckBox("Filter by department's name");
	public JFrame parentEmployees;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employees window = new Employees();
					window.frmEmployees.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employees() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployees = new JFrame();
		frmEmployees.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-gender-neutral-employee-group-16.png"));
		frmEmployees.setTitle("EMPLOYEES");
		frmEmployees.setBounds(100, 100, 1298, 768);
		frmEmployees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frmEmployees.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Clinic's Employees", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 1256, 702);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 27, 1232, 257);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		JButton btnShowEmployees = new JButton("Show Employees");
		btnShowEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean ok=false;
				if(ok==false) {
                   if(textSalary.getText().isEmpty() && textSalaryAvanced.getText().isEmpty() && textSalaryAdvanced2.getText().isEmpty()&& !(chckbxDep.isSelected())) {
                	   getEmployees();
                	   ok=true;
                   }
                   if(textSalaryAvanced.getText().isEmpty() && textSalaryAdvanced2.getText().isEmpty() && !(chckbxDep.isSelected()) && !(textSalary.getText().isEmpty())) {
                	   getEmployeesbySalary();
                	   ok=true;
                   }
                   if(textSalary.getText().isEmpty() && !(textSalaryAvanced.getText().isEmpty()) && !(textSalaryAdvanced2.getText().isEmpty()) && !(chckbxDep.isSelected())) {
                	   getEmployeesBySalaryAdvancedFilter();
                	   ok=true;
                   }
                   if(chckbxDep.isSelected() && textSalary.getText().isEmpty() && textSalaryAvanced.getText().isEmpty() && textSalaryAdvanced2.getText().isEmpty()) {
                		getEmployeesByDepartments();
                		   ok=true;
                   }
				
				textSalary.setText("");
				textSalaryAvanced.setText("");
				textSalaryAdvanced2.setText("");
				chckbxDep.setSelected(false);
				}
				if(ok==true) {
					JOptionPane.showMessageDialog(null, "Search was a succes!");
				}else {
					JOptionPane.showMessageDialog(null, "Please be careful how you manage filters!You can't select multiple filters.");
				}
			
			}

		});
		btnShowEmployees.setHideActionText(true);
		btnShowEmployees.setForeground(Color.DARK_GRAY);
		btnShowEmployees.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShowEmployees.setFocusable(false);
		btnShowEmployees.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowEmployees.setBackground(Color.WHITE);
		btnShowEmployees.setBounds(12, 306, 125, 51);
		panel_1.add(btnShowEmployees);

		JLabel lblFilterBySalary = new JLabel("Filter by salary");
		lblFilterBySalary.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFilterBySalary.setBounds(12, 385, 125, 31);
		panel_1.add(lblFilterBySalary);

		textSalary = new JTextField();
		textSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String suma = textSalary.getText();
					lblAlert.setText("");
					char c2 = suma.charAt(0);
					String var = "";
					var += c2;
					if (var.equals("0")) {
						throw new ExceptieNumarCuZero();
					}
					for (int i = 0; i < suma.length(); i++) {
						char c = suma.charAt(i);
						if (Character.isDigit(c) == false) {
							throw new ExceptieDoarNumar();
						}

					}
				} catch (ExceptieNumarCuZero cuZero) {
					lblAlert.setText("Invalid number");
				} catch (ExceptieDoarNumar doarNumar) {
					lblAlert.setText("This is not a number");
				}
			}

		});
		textSalary.setColumns(10);
		textSalary.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textSalary.setBounds(144, 386, 150, 31);
		panel_1.add(textSalary);

		cmbSalVar.setModel(new DefaultComboBoxModel(new String[] { "=", ">", "<" }));
		cmbSalVar.setSelectedIndex(0);
		cmbSalVar.setFocusable(false);
		cmbSalVar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbSalVar.setBackground(Color.WHITE);
		cmbSalVar.setBounds(333, 390, 150, 22);
		panel_1.add(cmbSalVar);

		JLabel lblAdvancedFilterBy = new JLabel("Advanced filter by salary\r\n");
		lblAdvancedFilterBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdvancedFilterBy.setBounds(12, 429, 202, 31);
		panel_1.add(lblAdvancedFilterBy);

		JLabel lblBetween = new JLabel("BETWEEN");
		lblBetween.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBetween.setBounds(221, 430, 73, 31);
		panel_1.add(lblBetween);

		textSalaryAvanced = new JTextField();
		textSalaryAvanced.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String suma = textSalaryAvanced.getText();
					lblAlertStartSalary.setText("");
					char c2 = suma.charAt(0);
					String var = "";
					var += c2;
					if (var.equals("0")) {
						throw new ExceptieNumarCuZero();
					}
					for (int i = 0; i < suma.length(); i++) {
						char c = suma.charAt(i);
						if (Character.isDigit(c) == false) {
							throw new ExceptieDoarNumar();
						}

					}
				} catch (ExceptieNumarCuZero cuZero) {
					lblAlertStartSalary.setText("Invalid number");
				} catch (ExceptieDoarNumar doarNumar) {
					lblAlertStartSalary.setText("This is not a number");
				}
			}
			
		});
		textSalaryAvanced.setColumns(10);
		textSalaryAvanced.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textSalaryAvanced.setBounds(306, 430, 150, 31);
		panel_1.add(textSalaryAvanced);

		JLabel lblAnd = new JLabel("AND\r\n");
		lblAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnd.setBounds(466, 429, 34, 31);
		panel_1.add(lblAnd);

		textSalaryAdvanced2 = new JTextField();
		textSalaryAdvanced2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String suma = textSalaryAdvanced2.getText();
					lblAlertSalary2.setText("");
					char c2 = suma.charAt(0);
					String var = "";
					var += c2;
					if (var.equals("0")) {
						throw new ExceptieNumarCuZero();
					}
					for (int i = 0; i < suma.length(); i++) {
						char c = suma.charAt(i);
						if (Character.isDigit(c) == false) {
							throw new ExceptieDoarNumar();
						}

					}
				} catch (ExceptieNumarCuZero cuZero) {
					lblAlertSalary2.setText("Invalid number");
				} catch (ExceptieDoarNumar doarNumar) {
					lblAlertSalary2.setText("This is not a number");
				}
			}
				
			
		});
		textSalaryAdvanced2.setColumns(10);
		textSalaryAdvanced2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textSalaryAdvanced2.setBounds(500, 430, 150, 31);
		panel_1.add(textSalaryAdvanced2);

		JLabel lblNewLabel_3 = new JLabel("Report about employees(.txt)");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(1000, 350, 244, 38);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Introduce the report name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(800, 401, 182, 28);
		panel_1.add(lblNewLabel_4);

		textRaportEmployees = new JTextField();
		textRaportEmployees.setColumns(10);
		textRaportEmployees.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textRaportEmployees.setBounds(981, 401, 263, 31);
		panel_1.add(textRaportEmployees);

		JButton btnGenerateReport = new JButton("Generate report\r\n");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String raport=textRaportEmployees.getText();
				try {
					File file = new File("C:\\Users\\Asus\\Desktop\\" +  raport + ".txt");
					if (!file.exists()) {
						file.createNewFile();

					}
					FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fileWriter);
					for (int i = 0; i < table.getRowCount(); i++) {
						for (int j = 0; j < table.getColumnCount(); j++) {
							bw.write(table.getModel().getValueAt(i, j).toString());
							bw.write(",");
						}
						bw.write("\n_______________________________________\n");
					}
					bw.close();
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "Report was create!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error file.txt");
				}

			}
		});
		btnGenerateReport.setHideActionText(true);
		btnGenerateReport.setForeground(Color.DARK_GRAY);
		btnGenerateReport.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGenerateReport.setFocusable(false);
		btnGenerateReport.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnGenerateReport.setBackground(Color.WHITE);
		btnGenerateReport.setBounds(1031, 479, 125, 51);
		panel_1.add(btnGenerateReport);
		lblAlert.setForeground(Color.RED);

		lblAlert.setBounds(139, 416, 128, 16);
		panel_1.add(lblAlert);
		
		
		lblAlertStartSalary.setForeground(Color.RED);
		lblAlertStartSalary.setBounds(306, 460, 131, 16);
		panel_1.add(lblAlertStartSalary);
		
		
		lblAlertSalary2.setForeground(Color.RED);
		lblAlertSalary2.setBounds(500, 460, 150, 16);
		panel_1.add(lblAlertSalary2);
		
		
		cmbDepartamente.setModel(new DefaultComboBoxModel(new String[] {"Pediatrie", "Stomatologie", "Oftalmologie", "Contabilitate", "Resurse Umane", "Secretariat"}));
		cmbDepartamente.setSelectedIndex(0);
		cmbDepartamente.setBounds(263, 481, 202, 22);
		panel_1.add(cmbDepartamente);
		
		
		chckbxDep.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxDep.setBounds(12, 479, 255, 25);
		panel_1.add(chckbxDep);
	}
	
    public void getEmployeesByDepartments() {
		if(cmbDepartamente.getSelectedIndex()==0) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psD1=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Pediatrie';");
				ResultSet resultSet=psD1.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}else if(cmbDepartamente.getSelectedIndex()==1) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psD2=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Stomatologie'");
				ResultSet resultSet=psD2.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}else if(cmbDepartamente.getSelectedIndex()==2) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psD2=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Oftalmologie'");
				ResultSet resultSet=psD2.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}else if(cmbDepartamente.getSelectedIndex()==3) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psD3=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Contabilitate'");
				ResultSet resultSet=psD3.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
				
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}else if(cmbDepartamente.getSelectedIndex()==4) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psD4=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Resurse Umane'");
				ResultSet resultSet=psD4.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();		
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
			
		}else if(cmbDepartamente.getSelectedIndex()==5) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n" + 
						"										from angajati\r\n" + 
						"										JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n" + 
						"										where departamente.denumire_departament='Secretariat'");
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}
		
    }
	public void getEmployeesBySalaryAdvancedFilter() {
		String salaryStart = textSalaryAvanced.getText();
		String salaryFinal = textSalaryAdvanced2.getText();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's Name");
		model.addColumn("Phone Number");
		model.addColumn("Job title");
		model.addColumn("Salary(RON)");
		model.addColumn("Maxim Salary");
		model.addColumn("Take Bonus(RON/4Months)");
		model.addColumn("Departament's name");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psAdvanced = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n"
							+ "										from angajati\r\n"
							+ "										JOIN joburi on angajati.job_id=joburi.job_id\r\n"
							+ "										JOIN departamente on angajati.departament_id=departamente.departament_id\r\n"
							+ "										where salariu>='" + salaryStart + "' AND salariu<'"
							+ salaryFinal + "'");
			ResultSet resultSet = psAdvanced.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[8];
				row[0] = resultSet.getString("nume");
				row[1] = resultSet.getString("prenume");
				row[2] = resultSet.getInt("telefon");
				row[3] = resultSet.getString("job_denumire");
				row[4] = resultSet.getInt("salariu");
				row[5] = resultSet.getInt("maxim_salary");
				row[6] = resultSet.getString("bonus");
				row[7] = resultSet.getString("denumire_departament");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public void getEmployeesbySalary() {
		String suma = textSalary.getText();
		if (cmbSalVar.getSelectedIndex() == 0) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n"
								+ "					from angajati\r\n"
								+ "					JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "					JOIN departamente on angajati.departament_id=departamente.departament_id\r\n"
								+ "					where salariu='" + suma + "';\r\n" + "");
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		} else if (cmbSalVar.getSelectedIndex() == 1) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps2 = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n"
								+ "					from angajati\r\n"
								+ "					JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "					JOIN departamente on angajati.departament_id=departamente.departament_id\r\n"
								+ "					where salariu>'" + suma + "'");
				ResultSet resultSet = ps2.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		} else if (cmbSalVar.getSelectedIndex() == 2) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Employee's Surname");
			model.addColumn("Employee's Name");
			model.addColumn("Phone Number");
			model.addColumn("Job title");
			model.addColumn("Salary(RON)");
			model.addColumn("Maxim Salary");
			model.addColumn("Take Bonus(RON/4Months)");
			model.addColumn("Departament's name");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n"
								+ "					from angajati\r\n"
								+ "					JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "					JOIN departamente on angajati.departament_id=departamente.departament_id\r\n"
								+ "					where salariu<'" + suma + "'");
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[8];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getInt("telefon");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("salariu");
					row[5] = resultSet.getInt("maxim_salary");
					row[6] = resultSet.getString("bonus");
					row[7] = resultSet.getString("denumire_departament");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}

	}

	public void getEmployees() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's Name");
		model.addColumn("Phone Number");
		model.addColumn("Job title");
		model.addColumn("Salary(RON)");
		model.addColumn("Maxim Salary");
		model.addColumn("Take Bonus(RON/4Months)");
		model.addColumn("Departament's name");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement ps = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,angajati.telefon,angajati.salariu,joburi.job_denumire,joburi.maxim_salary,if(joburi.bonus,'YES','NO') bonus,departamente.denumire_departament\r\n"
							+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
							+ "JOIN departamente on angajati.departament_id=departamente.departament_id;");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[8];
				row[0] = resultSet.getString("nume");
				row[1] = resultSet.getString("prenume");
				row[2] = resultSet.getInt("telefon");
				row[3] = resultSet.getString("job_denumire");
				row[4] = resultSet.getInt("salariu");
				row[5] = resultSet.getInt("maxim_salary");
				row[6] = resultSet.getString("bonus");
				row[7] = resultSet.getString("denumire_departament");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}
}
