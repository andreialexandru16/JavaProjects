package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.Font;

import com.sun.crypto.provider.RSACipher;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewPayments {

	JFrame frmViewPayments;
	private JTable table;
	JDateChooser dateInceput = new JDateChooser();
	JDateChooser dateSfarsit = new JDateChooser();
	private JTextField textNamePay;
	private JTextField textAmount;
	private JTextField textManName;
	private JTextField textUniqCode;
	private JTextField textCompanyAdres;
	private JTextField textIban;
	JDateChooser datePayment = new JDateChooser();
	private JTextField textID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ViewPayments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewPayments = new JFrame();
		frmViewPayments.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-view-16.png"));
		frmViewPayments.setTitle("View Payments");
		frmViewPayments.setBounds(100, 100, 917, 749);
		frmViewPayments.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmViewPayments.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Payments Table",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 875, 343);
		frmViewPayments.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 25, 851, 305);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectStatusRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				textID.setText(model.getValueAt(selectStatusRow, 0).toString());
				textNamePay.setText(model.getValueAt(selectStatusRow, 1).toString());
				textAmount.setText(model.getValueAt(selectStatusRow, 2).toString());
				textManName.setText(model.getValueAt(selectStatusRow, 4).toString());
				textUniqCode.setText(model.getValueAt(selectStatusRow, 5).toString());
				textCompanyAdres.setText(model.getValueAt(selectStatusRow, 6).toString());
				textIban.setText(model.getValueAt(selectStatusRow, 7).toString());
				String data = (model.getValueAt(selectStatusRow, 3).toString());
				try {
					Date dataC = new SimpleDateFormat("yyyy-MM-dd").parse(data);
					datePayment.setDate(dataC);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manage Payments",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 369, 875, 320);
		frmViewPayments.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JCheckBox chckbxFilterByDate = new JCheckBox("Filter by date");
		chckbxFilterByDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxFilterByDate.setBounds(8, 25, 132, 25);
		panel_1.add(chckbxFilterByDate);

		dateInceput.setDateFormatString("yyyy-MM-dd");
		dateInceput.setBounds(8, 62, 123, 22);
		panel_1.add(dateInceput);

		JLabel lblNewLabel_2 = new JLabel("TO\r\n");
		lblNewLabel_2.setBounds(131, 68, 17, 16);
		panel_1.add(lblNewLabel_2);

		dateSfarsit.setDateFormatString("yyyy-MM-dd");
		dateSfarsit.setBounds(150, 62, 132, 22);
		panel_1.add(dateSfarsit);

		JButton btnViewPayments = new JButton("Show Payments\r\n");
		btnViewPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxFilterByDate.isSelected()==false) {
					viewAllPayments();	
				}else if(chckbxFilterByDate.isSelected()) {
					viewPaymentsByDate();
					chckbxFilterByDate.setSelected(false);
					dateInceput.setCalendar(null);
					dateSfarsit.setCalendar(null);
				}
				
				
			}
		});
		btnViewPayments.setHideActionText(true);
		btnViewPayments.setForeground(Color.DARK_GRAY);
		btnViewPayments.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewPayments.setFocusable(false);
		btnViewPayments.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnViewPayments.setBackground(Color.WHITE);
		btnViewPayments.setBounds(8, 156, 138, 56);
		panel_1.add(btnViewPayments);

		JLabel lblPleaseIntroduceThe = new JLabel("Payment Name");
		lblPleaseIntroduceThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe.setBounds(354, 25, 127, 25);
		panel_1.add(lblPleaseIntroduceThe);

		textNamePay = new JTextField();
		textNamePay.setColumns(10);
		textNamePay.setBounds(472, 17, 159, 33);
		panel_1.add(textNamePay);

		JLabel lblPleaseIntroduceThe_2 = new JLabel("Amount(RON)");
		lblPleaseIntroduceThe_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2.setBounds(354, 70, 110, 25);
		panel_1.add(lblPleaseIntroduceThe_2);

		textAmount = new JTextField();
		textAmount.setColumns(10);
		textAmount.setBounds(472, 62, 159, 33);
		panel_1.add(textAmount);

		JLabel lblPleaseIntroduceThe_2_1 = new JLabel("Payment Date");
		lblPleaseIntroduceThe_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1.setBounds(354, 108, 110, 25);
		panel_1.add(lblPleaseIntroduceThe_2_1);

		datePayment.setDateFormatString("yyyy-MM-dd");
		datePayment.setBounds(472, 111, 159, 22);
		panel_1.add(datePayment);

		JLabel lblPleaseIntroduceThe_2_1_1 = new JLabel(" Manufacturer's Name");
		lblPleaseIntroduceThe_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_1.setBounds(317, 156, 164, 25);
		panel_1.add(lblPleaseIntroduceThe_2_1_1);

		textManName = new JTextField();
		textManName.setColumns(10);
		textManName.setBounds(482, 148, 222, 33);
		panel_1.add(textManName);

		JLabel lblPleaseIntroduceThe_2_1_2 = new JLabel("Unique Registration Code");
		lblPleaseIntroduceThe_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_2.setBounds(275, 196, 206, 25);
		panel_1.add(lblPleaseIntroduceThe_2_1_2);

		textUniqCode = new JTextField();
		textUniqCode.setColumns(10);
		textUniqCode.setBounds(472, 193, 198, 33);
		panel_1.add(textUniqCode);

		JLabel lblPleaseIntroduceThe_2_1_3 = new JLabel(" Company  Address");
		lblPleaseIntroduceThe_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_3.setBounds(297, 234, 152, 25);
		panel_1.add(lblPleaseIntroduceThe_2_1_3);

		textCompanyAdres = new JTextField();
		textCompanyAdres.setColumns(10);
		textCompanyAdres.setBounds(457, 231, 250, 33);
		panel_1.add(textCompanyAdres);

		JLabel lblPleaseIntroduceThe_2_1_4 = new JLabel("IBAN");
		lblPleaseIntroduceThe_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_4.setBounds(377, 277, 68, 25);
		panel_1.add(lblPleaseIntroduceThe_2_1_4);

		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(457, 272, 206, 33);
		panel_1.add(textIban);

		JLabel lblId = new JLabel("ID\r\n");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(712, 25, 25, 25);
		panel_1.add(lblId);

		textID = new JTextField();
		textID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JOptionPane.showMessageDialog(null, "You can't change the payment id");
				textID.setFocusable(false);
			}
		});
		textID.setColumns(10);
		textID.setBounds(734, 22, 129, 33);
		panel_1.add(textID);
		
		JButton btnModifyPayment = new JButton("\r\nModify Payment");
		btnModifyPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectStatusRow=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				boolean ok=true;
				if(selectStatusRow>=0) {
					model.setValueAt(textID.getText(),selectStatusRow,0);
					model.setValueAt(textNamePay.getText(),selectStatusRow,1);
					model.setValueAt(textAmount.getText(),selectStatusRow,2);
					model.setValueAt(textManName.getText(),selectStatusRow,4);
					model.setValueAt(textUniqCode.getText(),selectStatusRow,5);
					model.setValueAt(textCompanyAdres.getText(),selectStatusRow,6);
					model.setValueAt(textIban.getText(),selectStatusRow,7);
					SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
					String dataIceput = dformat.format(datePayment.getDate());
					model.setValueAt(dataIceput,selectStatusRow,3);
				changePayment();
				 
				}else if(selectStatusRow<0) {
					JOptionPane.showMessageDialog(null,"You must choose a payment in order to change it");
				}
			}

			public void changePayment() {
				String idPrg=textID.getText();
				String numePlata=textNamePay.getText();
				String suma=textAmount.getText();
				SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
				String data = dformat.format(datePayment.getDate());
				String denumireProd=textManName.getText();
				String CUI=textUniqCode.getText();
				String AdresaFirma=textCompanyAdres.getText();
				String CodIban=textIban.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
							"root");
					PreparedStatement psSt=connection.prepareStatement("UPDATE plati\r\n" + 
							"Set denumire_plata='" +numePlata +"',\r\n" + 
							"suma='" + suma +"',\r\n" + 
							"data_plata='" + data +"',\r\n" + 
							"denumire_producator='" + denumireProd +"',\r\n" + 
							"CUI_firma='" + CUI +"',\r\n" + 
							"adresa_firma='" + AdresaFirma +"',\r\n" + 
							"cod_iban='" + CodIban +"'\r\n" + 
							"where plata_id='" + idPrg +"'");
					int resultSet=psSt.executeUpdate();
					textID.setText("");
					textManName.setText("");
					textNamePay.setText("");
					textUniqCode.setText("");
					textIban.setText("");
					textCompanyAdres.setText("");
					textAmount.setText("");
					datePayment.setCalendar(null);
					if(resultSet>0) {
						JOptionPane.showMessageDialog(null, "Payment has been changed!");
					}else {
						JOptionPane.showMessageDialog(null, "Payment hasn't been changed!");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error database connection");
				}
			}
		});
		btnModifyPayment.setHideActionText(true);
		btnModifyPayment.setForeground(Color.DARK_GRAY);
		btnModifyPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModifyPayment.setFocusable(false);
		btnModifyPayment.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModifyPayment.setBackground(Color.WHITE);
		btnModifyPayment.setBounds(8, 246, 138, 56);
		panel_1.add(btnModifyPayment);
	}

	public void viewPaymentsByDate() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String dataIceput = dformat.format(dateInceput.getDate());
		String dataSfarsit = dformat.format(dateSfarsit.getDate());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Payment");
		model.addColumn("Payment Name");
		model.addColumn("Amount(RON)");
		model.addColumn("Payment Date");
		model.addColumn("Manufacturer's Name");
		model.addColumn("Unique Registration Code");
		model.addColumn("Company  Address");
		model.addColumn("IBAN");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psData = connection.prepareStatement(
					"SELECT plati.plata_id,plati.denumire_plata,plati.suma,plati.data_plata,plati.denumire_producator,plati.CUI_firma,plati.adresa_firma,plati.cod_iban FROM dbclinica.plati WHERE data_plata BETWEEN '"
							+ dataIceput + "' AND '" + dataSfarsit + "';");
			ResultSet resultSet = psData.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[8];
				row[0] = resultSet.getInt("plata_id");
				row[1] = resultSet.getString("denumire_plata");
				row[2] = resultSet.getFloat("suma");
				row[3] = resultSet.getDate("data_plata");
				row[4] = resultSet.getString("denumire_producator");
				row[5] = resultSet.getString("CUI_firma");
				row[6] = resultSet.getString("adresa_firma");
				row[7] = resultSet.getString("cod_iban");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}

	public void viewAllPayments() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Payment");
		model.addColumn("Payment Name");
		model.addColumn("Amount(RON)");
		model.addColumn("Payment Date");
		model.addColumn("Manufacturer's Name");
		model.addColumn("Unique Registration Code");
		model.addColumn("Company  Address");
		model.addColumn("IBAN");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement psData = connection.prepareStatement(
					"SELECT plati.plata_id,plati.denumire_plata,plati.suma,plati.data_plata,plati.denumire_producator,plati.CUI_firma,plati.adresa_firma,plati.cod_iban FROM dbclinica.plati");
			ResultSet resultSet = psData.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[8];
				row[0] = resultSet.getInt("plata_id");
				row[1] = resultSet.getString("denumire_plata");
				row[2] = resultSet.getFloat("suma");
				row[3] = resultSet.getDate("data_plata");
				row[4] = resultSet.getString("denumire_producator");
				row[5] = resultSet.getString("CUI_firma");
				row[6] = resultSet.getString("adresa_firma");
				row[7] = resultSet.getString("cod_iban");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}
}
