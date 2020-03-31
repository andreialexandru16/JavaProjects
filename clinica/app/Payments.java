package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import clinica.app.Exceptii.ExceptieDoarNumar;
import clinica.app.Exceptii.ExceptieNumarCuZero;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.MatteBorder;

import com.gui.ExcelExport;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
public class Payments  {

	JFrame frmPayments;
	public JFrame jframeParents;
	private JTable table;
	private JTextField txtSuma;
	JComboBox cmbVar = new JComboBox();
	private JTextField txtNume;
	JDateChooser dateChooser2 = new JDateChooser();
	JDateChooser dateChooser1 = new JDateChooser();
	JCheckBox chckbxFilterByDate = new JCheckBox("Filter by date");
	private JTextField txtRaport;
	public String xlsFileName;
	private JCheckBox chckbxNewCheckBoxFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payments window = new Payments();
					window.frmPayments.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Payments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPayments = new JFrame();
		frmPayments.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-card-payment-16.png"));
		frmPayments.setTitle("PAYMENTS");
		frmPayments.setBounds(100, 100, 1075, 715);
		frmPayments.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		frmPayments.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setFocusable(false);
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.setForeground(SystemColor.menu);
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PAYMENTS INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 13, 1033, 642);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusTraversalPolicyProvider(true);
		scrollPane.setBackground(SystemColor.windowBorder);
		scrollPane.setBounds(12, 255, 1009, 374);
		panel_1.add(scrollPane);

		dateChooser2.setDateFormatString("yyyy-MM-dd");

		table = new JTable();
		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("Show Payments\r\n");
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFocusable(false);
		btnNewButton.setHideActionText(true);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean ok=false;
				if(ok==false) {
				if (!(txtNume.getText().isEmpty()) && !(txtSuma.getText().isEmpty())&& !(chckbxFilterByDate.isSelected())) {
					getPaymentsFilterNameAndAmount();
					ok=true;
				}
				if (txtNume.getText().isEmpty() && !(chckbxFilterByDate.isSelected())) {
					getPaymentsFilterAmount();
					ok=true;
					
				}
				if (txtSuma.getText().isEmpty() && !(chckbxFilterByDate.isSelected())) {
					getPaymentsFilterManufactureName();
					ok=true;
					
				}
				if (txtNume.getText().isEmpty() && txtSuma.getText().isEmpty() && !(chckbxFilterByDate.isSelected())) {
					getPayments();
					ok=true;
				}
				if (txtNume.getText().isEmpty() && txtSuma.getText().isEmpty() && chckbxFilterByDate.isSelected()) {
					getPaymentsFilterDate();
					ok=true;
				}
				
				dateChooser1.setCalendar(null);
				dateChooser2.setCalendar(null);
				chckbxFilterByDate.setSelected(false);
				txtNume.setText("");
				txtSuma.setText("");
			}
				if(ok==true) {
					JOptionPane.showMessageDialog(null, "Search was a succes!");
				}else {
					JOptionPane.showMessageDialog(null, "Please be careful how you manage filters!Only amount and name can be use in the same time.");
				}
			}
		});
		btnNewButton.setBounds(373, 23, 138, 38);
		panel_1.add(btnNewButton);
		cmbVar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbVar.setFocusable(false);
		cmbVar.setBackground(Color.WHITE);

		cmbVar.setModel(new DefaultComboBoxModel(new String[] { "=", ">", "<" }));
		cmbVar.setSelectedIndex(0);
		cmbVar.setBounds(139, 86, 150, 22);
		panel_1.add(cmbVar);
		JLabel lblValidation = new JLabel("");
		txtSuma = new JTextField();
		txtSuma.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtSuma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String suma = txtSuma.getText();
					lblValidation.setText("");
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
					lblValidation.setText("Invalid number");
				} catch (ExceptieDoarNumar doarNumar) {
					lblValidation.setText("This is not a number");
				}
			}
		});
		txtSuma.setBounds(139, 32, 150, 22);
		panel_1.add(txtSuma);
		txtSuma.setColumns(10);

		txtNume = new JTextField();
		txtNume.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtNume.setBounds(238, 214, 162, 28);
		panel_1.add(txtNume);
		txtNume.setColumns(10);

		lblValidation.setForeground(Color.RED);
		lblValidation.setBounds(139, 57, 138, 16);
		panel_1.add(lblValidation);

		dateChooser1.setDateFormatString("yyyy-MM-dd");
		dateChooser1.setBounds(12, 146, 123, 22);
		panel_1.add(dateChooser1);

		JLabel lblNewLabel_2 = new JLabel("TO\r\n");
		lblNewLabel_2.setBounds(139, 152, 17, 16);
		panel_1.add(lblNewLabel_2);

		dateChooser2.setBounds(157, 146, 132, 22);
		panel_1.add(dateChooser2);

		JButton btnNewButton_1 = new JButton("Create payments report");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBoxFile.isSelected()) {
					generateExcelFile();
					chckbxNewCheckBoxFile.setSelected(false);
				}else {
				generateTxtFile();
				}

			}

			public void generateExcelFile() {
				xlsFileName=txtRaport.getText();
				File file = new File(xlsFileName + ".xls");
				try {
					new ExcelExport().exportTable(table, file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(new File(xlsFileName + ".xls"));
				} catch (Exception any) {
					JOptionPane.showMessageDialog(null, "Error creating xls file!");

				}
			}

			public void generateTxtFile() {
				String raport=txtRaport.getText();
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
		btnNewButton_1.setBounds(750, 204, 190, 38);
		panel_1.add(btnNewButton_1);
		chckbxFilterByDate.setFont(new Font("Tahoma", Font.BOLD, 15));

		chckbxFilterByDate.setBounds(8, 119, 132, 25);
		panel_1.add(chckbxFilterByDate);

		JLabel lblNewLabel = new JLabel("Filter by amount");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 34, 134, 16);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Filter by manufacturer name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(8, 210, 218, 32);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("                                Report about peymants(.xls,.txt)\r\n\r\n   ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(582, 22, 439, 51);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Introduce the report name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(645, 89, 182, 28);
		panel_1.add(lblNewLabel_4);
		
		txtRaport = new JTextField();
		txtRaport.setBounds(839, 92, 123, 22);
		panel_1.add(txtRaport);
		txtRaport.setColumns(10);
		
		chckbxNewCheckBoxFile = new JCheckBox("Excel report\r\n");
		chckbxNewCheckBoxFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBoxFile.setBounds(645, 143, 190, 25);
		panel_1.add(chckbxNewCheckBoxFile);
		
		JLabel lblNewLabel_3_1 = new JLabel("                                      (By default it is a text file)\r\n\r\n   ");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(592, 44, 439, 51);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_5 = new JLabel(".txt/.xls");
		lblNewLabel_5.setBounds(965, 96, 56, 16);
		panel_1.add(lblNewLabel_5);
	}

	public void getPaymentsFilterDate() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String dataIceput = dformat.format(dateChooser1.getDate());
		String dataSfarsit = dformat.format(dateChooser2.getDate());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Order name");
		model.addColumn("Amount(RON)");
		model.addColumn("Manufacturer's name");
		model.addColumn("Payment Date");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psData = connection
					.prepareStatement("SELECT plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata FROM dbclinica.plati WHERE data_plata BETWEEN '" + dataIceput
							+ "' AND '" + dataSfarsit + "';");
			ResultSet resultSet = psData.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[4];
				row[0] = resultSet.getString("denumire_plata");
				row[1] = resultSet.getFloat("suma");
				row[2] = resultSet.getString("denumire_producator");
				row[3] = resultSet.getDate("data_plata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}

	public void getPaymentsFilterNameAndAmount() {
		String numeQuery = txtNume.getText();
		String sumaQuery = txtSuma.getText();
		if (cmbVar.getSelectedIndex() == 0) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psFilt = connection.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where denumire_producator='"
						+ numeQuery + "'and suma='" + sumaQuery + "'");
				ResultSet rsFilt = psFilt.executeQuery();
				while (rsFilt.next()) {
					Object[] row = new Object[4];
					row[0] = rsFilt.getString("denumire_plata");
					row[1] = rsFilt.getFloat("suma");
					row[2] = rsFilt.getString("denumire_producator");
					row[3] = rsFilt.getDate("data_plata");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbVar.getSelectedIndex() == 1) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psFilt2 = connection
						.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where denumire_producator='" + numeQuery + "'and suma>'"
								+ sumaQuery + "'");
				ResultSet rsFilt2 = psFilt2.executeQuery();
				while (rsFilt2.next()) {
					Object[] row = new Object[4];
					row[0] = rsFilt2.getString("denumire_plata");
					row[1] = rsFilt2.getFloat("suma");
					row[2] = rsFilt2.getString("denumire_producator");
					row[3] = rsFilt2.getDate("data_plata");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		} else if (cmbVar.getSelectedIndex() == 2) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement psFilt3 = connection
						.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where denumire_producator='" + numeQuery + "'and suma<'"
								+ sumaQuery + "'");
				ResultSet rsFilt3 = psFilt3.executeQuery();
				while (rsFilt3.next()) {
					Object[] row = new Object[4];
					row[0] = rsFilt3.getString("denumire_plata");
					row[1] = rsFilt3.getFloat("suma");
					row[2] = rsFilt3.getString("denumire_producator");
					row[3] = rsFilt3.getDate("data_plata");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		}
	}

	public void getPaymentsFilterManufactureName() {
		String numeQuery = txtNume.getText();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Order name");
		model.addColumn("Amount(RON)");
		model.addColumn("Manufacturer's name");
		model.addColumn("Payment Date");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psName = connection
					.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where denumire_producator='" + numeQuery + "'");
			ResultSet rsName = psName.executeQuery();
			while (rsName.next()) {
				Object[] row = new Object[4];
				row[0] = rsName.getString("denumire_plata");
				row[1] = rsName.getFloat("suma");
				row[2] = rsName.getString("denumire_producator");
				row[3] = rsName.getDate("data_plata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}

	public void getPaymentsFilterAmount() {
		String sumaQuery = txtSuma.getText();
		if (cmbVar.getSelectedIndex() == 0) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps = connection
						.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where suma='" + sumaQuery + "'");
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Object[] row = new Object[4];
					row[0] = resultSet.getString("denumire_plata");
					row[1] = resultSet.getFloat("suma");
					row[2] = resultSet.getString("denumire_producator");
					row[3] = resultSet.getDate("data_plata");
					model.addRow(row);

				}
				table.setModel(model);
				connection.close();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}
		} else if (cmbVar.getSelectedIndex() == 1) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps2 = connection
						.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where suma>'" + sumaQuery + "'");
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Object[] row = new Object[4];
					row[0] = rs2.getString("denumire_plata");
					row[1] = rs2.getFloat("suma");
					row[2] = rs2.getString("denumire_producator");
					row[3] = rs2.getDate("data_plata");
					model.addRow(row);
				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		} else if (cmbVar.getSelectedIndex() == 2) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Order name");
			model.addColumn("Amount(RON)");
			model.addColumn("Manufacturer's name");
			model.addColumn("Payment Date");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
						"root");
				PreparedStatement ps3 = connection
						.prepareStatement("Select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati Where suma<'" + sumaQuery + "'");
				ResultSet rs3 = ps3.executeQuery();
				while (rs3.next()) {
					Object[] row = new Object[4];
					row[0] = rs3.getString("denumire_plata");
					row[1] = rs3.getFloat("suma");
					row[2] = rs3.getString("denumire_producator");
					row[3] = rs3.getDate("data_plata");
					model.addRow(row);

				}
				table.setModel(model);
				connection.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error database connection");
			}

		}
		

	}

	public void getPayments() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Order name");
		model.addColumn("Amount(RON)");
		model.addColumn("Manufacturer's name");
		model.addColumn("Payment Date");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select plati.denumire_plata,plati.suma,plati.denumire_producator,plati.data_plata from plati;");
			while (rs.next()) {
				Object[] row = new Object[4];
				row[0] = rs.getString("denumire_plata");
				row[1] = rs.getFloat("suma");
				row[2] = rs.getString("denumire_producator");
				row[3] = rs.getDate("data_plata");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}
}
