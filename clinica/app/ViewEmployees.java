package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ViewEmployees {

	JFrame frmViewEmployees;
	private JTable table;
	private JComboBox cmbDepartamente;
	private JCheckBox chckbxDep;
	private JComboBox cmbJob;
	private JCheckBox chckbxFilterByEmployeesJOB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ViewEmployees() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewEmployees = new JFrame();
		frmViewEmployees.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-gender-neutral-employee-group-16.png"));
		frmViewEmployees.setTitle("View Employees");
		frmViewEmployees.setBounds(100, 100, 862, 693);
		frmViewEmployees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmViewEmployees.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Employees Table", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 820, 307);
		frmViewEmployees.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 25, 796, 269);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnShowEmployees = new JButton("Show Employees\r\n");
		btnShowEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxDep.isSelected() == true && chckbxFilterByEmployeesJOB.isSelected()==false) {
					getEmployeesByDepartment();
					chckbxDep.setSelected(false);
				} else if(chckbxFilterByEmployeesJOB.isSelected()==true && chckbxDep.isSelected()==false) {
					getEmployeesByJob();
					chckbxFilterByEmployeesJOB.setSelected(false);
				}else if(chckbxDep.isSelected()==false && chckbxFilterByEmployeesJOB.isSelected()==false) {
				getAllEmployees();
				}else if(chckbxDep.isSelected()==true && chckbxFilterByEmployeesJOB.isSelected()==true) {
					JOptionPane.showMessageDialog(null, "You must choose only one filter!");
					chckbxDep.setSelected(false);
					chckbxFilterByEmployeesJOB.setSelected(false);
				}
			}
		});
		btnShowEmployees.setHideActionText(true);
		btnShowEmployees.setForeground(Color.DARK_GRAY);
		btnShowEmployees.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowEmployees.setFocusable(false);
		btnShowEmployees.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowEmployees.setBackground(Color.WHITE);
		btnShowEmployees.setBounds(329, 561, 138, 56);
		frmViewEmployees.getContentPane().add(btnShowEmployees);

		chckbxDep = new JCheckBox("Filter by department's name");
		chckbxDep.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxDep.setBounds(12, 341, 255, 25);
		frmViewEmployees.getContentPane().add(chckbxDep);

		cmbDepartamente = new JComboBox();
		cmbDepartamente.setModel(new DefaultComboBoxModel(new String[] { "Pediatrie", "Stomatologie", "Oftalmologie",
				"Contabilitate", "Resurse Umane", "Secretariat" }));
		cmbDepartamente.setSelectedIndex(0);
		cmbDepartamente.setBounds(12, 374, 202, 22);
		frmViewEmployees.getContentPane().add(cmbDepartamente);

		chckbxFilterByEmployeesJOB = new JCheckBox("Filter by Employee's Job");
		chckbxFilterByEmployeesJOB.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxFilterByEmployeesJOB.setBounds(537, 341, 234, 25);
		frmViewEmployees.getContentPane().add(chckbxFilterByEmployeesJOB);

		cmbJob = new JComboBox();
		cmbJob
				.setModel(new DefaultComboBoxModel(new String[] { "Doctor", "Contabil", "HR", "Secretar/Secretara" }));
		cmbJob.setSelectedIndex(0);
		cmbJob.setBounds(537, 374, 202, 22);
		frmViewEmployees.getContentPane().add(cmbJob);
	}

	public void getEmployeesByJob() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's Name");
		model.addColumn("Employee's CNP");
		model.addColumn("Employee's Job");
		model.addColumn("Employee's Phone Number");
		model.addColumn("Employee's Salary(RON)");
		if(cmbJob.getSelectedIndex()==0) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement=connection.prepareStatement("SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n" + 
						"from angajati\r\n" + 
						"JOIN joburi on angajati.job_id=joburi.job_id\r\n" + 
						"where joburi.job_id=1 or joburi.job_id=2 or joburi.job_id=3");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("nume");
				row[1] = resultSet.getString("prenume");
				row[2] = resultSet.getString("cnp");
				row[3] = resultSet.getString("job_denumire");
				row[4] = resultSet.getInt("telefon");
				row[5] = resultSet.getInt("salariu");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}
		
	}

	public void getAllEmployees() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's Name");
		model.addColumn("Employee's CNP");
		model.addColumn("Employee's Job");
		model.addColumn("Employee's Phone Number");
		model.addColumn("Employee's Salary(RON)");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
							+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("nume");
				row[1] = resultSet.getString("prenume");
				row[2] = resultSet.getString("cnp");
				row[3] = resultSet.getString("job_denumire");
				row[4] = resultSet.getInt("telefon");
				row[5] = resultSet.getInt("salariu");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public void getEmployeesByDepartment() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's Name");
		model.addColumn("Employee's CNP");
		model.addColumn("Employee's Job");
		model.addColumn("Employee's Phone Number");
		model.addColumn("Employee's Salary(RON)");
		if (cmbDepartamente.getSelectedIndex() == 0) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=1;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbDepartamente.getSelectedIndex() == 1) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=2;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		} else if (cmbDepartamente.getSelectedIndex() == 2) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=3;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbDepartamente.getSelectedIndex() == 3) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=4;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbDepartamente.getSelectedIndex() == 4) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=5;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbDepartamente.getSelectedIndex() == 5) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT angajati.nume,angajati.prenume,angajati.cnp,angajati.telefon,angajati.salariu,joburi.job_denumire\r\n"
								+ "from angajati\r\n" + "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
								+ "where joburi.job_id=6;");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[6];
					row[0] = resultSet.getString("nume");
					row[1] = resultSet.getString("prenume");
					row[2] = resultSet.getString("cnp");
					row[3] = resultSet.getString("job_denumire");
					row[4] = resultSet.getInt("telefon");
					row[5] = resultSet.getInt("salariu");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}
	}
}
