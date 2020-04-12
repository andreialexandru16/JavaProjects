package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import clinica.app.Clase.Calculator;
import clinica.app.Exceptii.ExceptieLipsaNume;
import clinica.app.Exceptii.ExceptieLipsaPrenume;
import clinica.app.Exceptii.ExceptieNumePrenume;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorsAppointments {

	JFrame frmDoctorsAppointments;
	private JTable table;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textIncomeMonth;
	JDateChooser dateStart = new JDateChooser();
	JDateChooser dateFinal = new JDateChooser();
	public JFrame frmParent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorsAppointments window = new DoctorsAppointments();
					window.frmDoctorsAppointments.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DoctorsAppointments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctorsAppointments = new JFrame();
		frmDoctorsAppointments.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		frmDoctorsAppointments.setTitle("Doctor's Appointments");
		frmDoctorsAppointments.setBounds(100, 100, 1090, 745);
		frmDoctorsAppointments.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDoctorsAppointments.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 13, 1051, 649);
		frmDoctorsAppointments.getContentPane().add(panel);
		panel.setLayout(null);
		JDateChooser dateFinalIncome = new JDateChooser();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 1027, 220);
		panel.add(scrollPane);
		JCheckBox chckbxFilterByDate = new JCheckBox("Filter by date");
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectStatusRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String status = model.getValueAt(selectStatusRow, 3).toString();
				int venitAngajatLunar = Integer.parseInt(model.getValueAt(selectStatusRow, 2).toString());
				boolean okei = false;
				int venituriTotaleLunar = Integer.parseInt(textIncomeMonth.getText());
				Calculator calculator = new Calculator(venituriTotaleLunar, venitAngajatLunar);
				int numarAngajati=0;
				try {
					if (status.equals("YES")) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica",
								"root", "root");
						PreparedStatement preparedStatement=connection.prepareStatement("SELECT sum(departamente.numar_angajati)\r\n" + 
								"from departamente\r\n" + 
								"where departament_id=1 OR departament_id=2 OR departament_id=3");
						ResultSet resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							 numarAngajati=resultSet.getInt(1);
						}
						connection.close();
						okei = calculator.decideBonus(numarAngajati);
						if(okei==true) {
							JOptionPane.showMessageDialog(null, "This doctor takes bonus this month!");
						}else {
							JOptionPane.showMessageDialog(null, "This doctor does not take bonus this month!");
						}
						
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error database connection");
				}
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		textIncomeMonth = new JTextField();
		textIncomeMonth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JOptionPane.showMessageDialog(null, "You can't change the calculated income!");
				textIncomeMonth.setFocusable(false);

			}
		});
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Filters for Appointments and Bonus Decision", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(12, 245, 1027, 391);
		panel.add(panel_1);
		panel_1.setLayout(null);
		JLabel lblSurname = new JLabel("");
		chckbxFilterByDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxFilterByDate.setBounds(718, 29, 132, 25);
		panel_1.add(chckbxFilterByDate);
		JLabel lblName = new JLabel("");
		dateStart.setDateFormatString("yyyy-MM-dd");
		dateStart.setBounds(718, 56, 132, 22);
		panel_1.add(dateStart);

		JLabel lblNewLabel_2 = new JLabel("TO\r\n");
		lblNewLabel_2.setBounds(859, 62, 23, 16);
		panel_1.add(lblNewLabel_2);

		dateFinal.setDateFormatString("yyyy-MM-dd");
		dateFinal.setBounds(883, 56, 132, 22);
		panel_1.add(dateFinal);
		JDateChooser dateStartIncome = new JDateChooser();
		JLabel lblPleaseIntroduceThe = new JLabel("Dates to calculate the income for month");
		lblPleaseIntroduceThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe.setBounds(8, 26, 310, 31);
		panel_1.add(lblPleaseIntroduceThe);

		textIncomeMonth.setColumns(10);
		textIncomeMonth.setBounds(127, 98, 94, 30);
		panel_1.add(textIncomeMonth);

		textName = new JTextField();
		textName.setBounds(848, 98, 167, 30);
		panel_1.add(textName);
		textName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String prenume = textName.getText();
					lblName.setText("");
					for (int i = 0; i < prenume.length(); i++) {
						char c = prenume.charAt(i);
						if (Character.isAlphabetic(c) == false) {
							throw new ExceptieNumePrenume();
						}
					}

				} catch (ExceptieNumePrenume exceptieNumePrenume) {
					lblName.setText("Invalid name!");
				}
			}
		});

		textName.setColumns(10);
		JLabel lblFilterByDoctors = new JLabel("Filter by Doctor's Name");
		lblFilterByDoctors.setBounds(652, 97, 184, 31);
		panel_1.add(lblFilterByDoctors);
		lblFilterByDoctors.setFont(new Font("Tahoma", Font.BOLD, 15));

		textSurname = new JTextField();
		textSurname.setBounds(848, 142, 167, 30);
		panel_1.add(textSurname);
		textSurname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String prenume = textSurname.getText();
					lblSurname.setText("");
					for (int i = 0; i < prenume.length(); i++) {
						char c = prenume.charAt(i);
						if (Character.isAlphabetic(c) == false) {
							throw new ExceptieNumePrenume();
						}
					}

				} catch (ExceptieNumePrenume exceptieNumePrenume) {
					lblSurname.setText("Invalid Surname!");
				}
			}
		});
		textSurname.setColumns(10);

		JLabel lblFilterByDoctors_2 = new JLabel("Filter by Doctor's Surname");
		lblFilterByDoctors_2.setBounds(637, 141, 199, 31);
		panel_1.add(lblFilterByDoctors_2);
		lblFilterByDoctors_2.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnAppointmentsD = new JButton("Show Filtered Appointments \r\n");
		btnAppointmentsD.setBounds(798, 185, 217, 57);
		panel_1.add(btnAppointmentsD);
		btnAppointmentsD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = false;
				if (textName.getText().isEmpty() && textSurname.getText().isEmpty()
						&& !(chckbxFilterByDate.isSelected())) {
					getAllAppointmentsWithoutFilter();
					ok = true;
				}
				if (!(textName.getText().isEmpty())
						|| !(textSurname.getText().isEmpty()) && !(chckbxFilterByDate.isSelected())) {
					getAppointmentsFilterNameSurnameDoctors();
					ok = true;
				}
				if (textName.getText().isEmpty() && textSurname.getText().isEmpty()
						&& chckbxFilterByDate.isSelected()) {
					getAppointmentsByDateFilter();
					ok = true;
				}
				if (!(textName.getText().isEmpty()) && !(textSurname.getText().isEmpty())
						&& chckbxFilterByDate.isSelected()) {
					getAppointmentsByDateAndNameAndSurname();
					ok = true;
				}
				dateStart.setCalendar(null);
				dateFinal.setCalendar(null);
				textName.setText("");
				textSurname.setText("");
				chckbxFilterByDate.setSelected(false);
				if (ok == true) {
					JOptionPane.showMessageDialog(null, "Search was a succes!");
				} else {
					JOptionPane.showMessageDialog(null, "Please be careful how you manage filters!");
				}

			}
		});
		btnAppointmentsD.setHideActionText(true);
		btnAppointmentsD.setForeground(Color.DARK_GRAY);
		btnAppointmentsD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAppointmentsD.setFocusable(false);
		btnAppointmentsD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAppointmentsD.setBackground(Color.WHITE);

		JButton btnShowCostsFor = new JButton("Calculate the income for specified dates");
		btnShowCostsFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
				String dataIceput = dformat.format(dateStartIncome.getDate());
				String dataSfarsit = dformat.format(dateFinalIncome.getDate());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
							"root");
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(programari.cost) \r\n"
							+ "FROM programari\r\n" + "WHERE data_programare BETWEEN '" + dataIceput + "' AND'"
							+ dataSfarsit + "' AND programare_efectuata=1;");
					int incomeMonth = 0;
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						incomeMonth = resultSet.getInt(1);
					}
					textIncomeMonth.setText(Integer.toString(incomeMonth));
					connection.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error database connection");
				}

			}
		});
		btnShowCostsFor.setHideActionText(true);
		btnShowCostsFor.setForeground(Color.DARK_GRAY);
		btnShowCostsFor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowCostsFor.setFocusable(false);
		btnShowCostsFor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowCostsFor.setBackground(Color.WHITE);
		btnShowCostsFor.setBounds(8, 141, 310, 57);
		panel_1.add(btnShowCostsFor);

		dateStartIncome.setDateFormatString("yyyy-MM-dd");
		dateStartIncome.setBounds(8, 56, 132, 22);
		panel_1.add(dateStartIncome);

		JLabel lblNewLabel_2_1 = new JLabel("TO\r\n");
		lblNewLabel_2_1.setBounds(142, 62, 23, 16);
		panel_1.add(lblNewLabel_2_1);

		dateFinalIncome.setDateFormatString("yyyy-MM-dd");
		dateFinalIncome.setBounds(162, 56, 132, 22);
		panel_1.add(dateFinalIncome);

		lblName.setBounds(848, 125, 167, 16);
		panel_1.add(lblName);

		lblName.setForeground(Color.RED);

		lblSurname.setBounds(848, 169, 167, 16);
		panel_1.add(lblSurname);

		lblSurname.setForeground(Color.RED);

		JLabel lblTheIncomeIs = new JLabel("The income is:");
		lblTheIncomeIs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTheIncomeIs.setBounds(18, 97, 106, 31);
		panel_1.add(lblTheIncomeIs);

		JButton btnCalculateTheIncome = new JButton("Calculate the income for every doctor/month");
		btnCalculateTheIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
				String dataIceput = dformat.format(dateStartIncome.getDate());
				String dataSfarsit = dformat.format(dateFinalIncome.getDate());
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Doctor's Name");
				model.addColumn("Doctor's Surname");
				model.addColumn("Total income(RON/Month)");
				model.addColumn("Take Bonus");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
							"root");
					PreparedStatement preparableStatement = connection.prepareStatement(
							"SELECT angajati.nume,angajati.prenume,sum(programari.cost)TotalPerDoctor,if(joburi.bonus,'YES','NO')bonus\r\n"
									+ "FROM programari\r\n"
									+ "JOIN angajati on programari.angajat_id=angajati.angajat_id \r\n"
									+ "JOIN joburi on angajati.job_id=joburi.job_id\r\n"
									+ "where programare_efectuata=1 And data_programare BETWEEN '" + dataIceput
									+ "' AND'" + dataSfarsit + "'\r\n" + "Group by \r\n"
									+ "angajati.nume,angajati.prenume\r\n" + "Order by \r\n" + "TotalPerDoctor DESC");
					ResultSet resultSet = preparableStatement.executeQuery();

					while (resultSet.next()) {
						Object[] row = new Object[4];
						row[0] = resultSet.getString("angajati.nume");
						row[1] = resultSet.getString("angajati.prenume");
						row[2] = resultSet.getInt("TotalPerDoctor");
						row[3] = resultSet.getString("bonus");
						model.addRow(row);
					}
					table.setModel(model);
					connection.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error database connection");
				}
			}
		});
		btnCalculateTheIncome.setHideActionText(true);
		btnCalculateTheIncome.setForeground(Color.DARK_GRAY);
		btnCalculateTheIncome.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCalculateTheIncome.setFocusable(false);
		btnCalculateTheIncome.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCalculateTheIncome.setBackground(Color.WHITE);
		btnCalculateTheIncome.setBounds(8, 213, 343, 57);
		panel_1.add(btnCalculateTheIncome);

		JLabel lblNewLabel = new JLabel("RON/Month");
		lblNewLabel.setBounds(223, 98, 71, 30);
		panel_1.add(lblNewLabel);
	}

	public void getAppointmentsByDateAndNameAndSurname() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String dataIceput = dformat.format(dateStart.getDate());
		String dataSfarsit = dformat.format(dateFinal.getDate());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Doctor's Name");
		model.addColumn("Doctor's Surname");
		model.addColumn("Appointments'Date");
		model.addColumn("Appointment's Details");
		model.addColumn("Cost(RON");
		model.addColumn("Appointment Done");
		try {
			String prenume = textName.getText();
			String nume = textSurname.getText();
			if (prenume.length() == 0) {
				throw new ExceptieLipsaPrenume();
			}
			if (nume.length() == 0) {
				throw new ExceptieLipsaNume();
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,programari.data_programare,programari.detalii_servici,programari.cost,if(programari.programare_efectuata,'YES','NO')programare_efectuata\r\n"
							+ "FROM programari\r\n" + "JOIN angajati on programari.angajat_id=angajati.angajat_id \r\n"
							+ "WHERE data_programare BETWEEN '" + dataIceput + "' AND'" + dataSfarsit + "' And nume='"
							+ nume + "' And prenume='" + prenume + "'");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("angajati.nume");
				row[1] = resultSet.getString("angajati.prenume");
				row[2] = resultSet.getDate("data_programare");
				row[3] = resultSet.getString("detalii_servici");
				row[4] = resultSet.getInt("cost");
				row[5] = resultSet.getString("programare_efectuata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (ExceptieLipsaNume exceptieLipsaNume) {
			JOptionPane.showMessageDialog(null, "You must complete both filters,name and surname!");
		} catch (ExceptieLipsaPrenume exceptieLipsaPrenume) {
			JOptionPane.showMessageDialog(null, "You must complete both filters,name and surname!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}

	public void getAppointmentsByDateFilter() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String dataIceput = dformat.format(dateStart.getDate());
		String dataSfarsit = dformat.format(dateFinal.getDate());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Doctor's Name");
		model.addColumn("Doctor's Surname");
		model.addColumn("Appointments'Date");
		model.addColumn("Appointment's Details");
		model.addColumn("Cost(RON");
		model.addColumn("Appointment Done");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,programari.data_programare,programari.detalii_servici,programari.cost,if(programari.programare_efectuata,'YES','NO')programare_efectuata\r\n"
							+ "FROM programari\r\n" + "JOIN angajati on programari.angajat_id=angajati.angajat_id \r\n"
							+ "WHERE data_programare BETWEEN '" + dataIceput + "'And'" + dataSfarsit + "'");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("angajati.nume");
				row[1] = resultSet.getString("angajati.prenume");
				row[2] = resultSet.getDate("data_programare");
				row[3] = resultSet.getString("detalii_servici");
				row[4] = resultSet.getInt("cost");
				row[5] = resultSet.getString("programare_efectuata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public void getAllAppointmentsWithoutFilter() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Doctor's Name");
		model.addColumn("Doctor's Surname");
		model.addColumn("Appointments'Date");
		model.addColumn("Appointment's Details");
		model.addColumn("Cost(RON");
		model.addColumn("Appointment Done");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement pst = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,programari.data_programare,programari.detalii_servici,programari.cost,if(programari.programare_efectuata,'YES','NO')programare_efectuata\r\n"
							+ "from programari\r\n" + "JOIN angajati on programari.angajat_id=angajati.angajat_id");

			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("angajati.nume");
				row[1] = resultSet.getString("angajati.prenume");
				row[2] = resultSet.getDate("data_programare");
				row[3] = resultSet.getString("detalii_servici");
				row[4] = resultSet.getInt("cost");
				row[5] = resultSet.getString("programare_efectuata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public void getAppointmentsFilterNameSurnameDoctors() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Doctor's Name");
		model.addColumn("Doctor's Surname");
		model.addColumn("Appointments'Date");
		model.addColumn("Appointment's Details");
		model.addColumn("Cost(RON");
		model.addColumn("Appointment Done");
		try {
			String prenume = textName.getText();
			String nume = textSurname.getText();
			if (prenume.length() == 0) {
				throw new ExceptieLipsaPrenume();
			}
			if (nume.length() == 0) {
				throw new ExceptieLipsaNume();
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT angajati.nume,angajati.prenume,programari.data_programare,programari.detalii_servici,programari.cost,if(programari.programare_efectuata,'YES','NO')programare_efectuata\r\n"
							+ "from programari\r\n" + "JOIN angajati on programari.angajat_id=angajati.angajat_id\r\n"
							+ "where angajati.nume='" + nume + "'and angajati.prenume='" + prenume + "'");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[6];
				row[0] = resultSet.getString("angajati.nume");
				row[1] = resultSet.getString("angajati.prenume");
				row[2] = resultSet.getDate("data_programare");
				row[3] = resultSet.getString("detalii_servici");
				row[4] = resultSet.getInt("cost");
				row[5] = resultSet.getString("programare_efectuata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (ExceptieLipsaNume exceptieLipsaNume) {
			JOptionPane.showMessageDialog(null, "You must complete both filters,name and surname!");
		} catch (ExceptieLipsaPrenume exceptieLipsaPrenume) {
			JOptionPane.showMessageDialog(null, "You must complete both filters,name and surname!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}
}
