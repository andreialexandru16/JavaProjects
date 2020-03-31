package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import clinica.app.Exceptii.ExceptieDoarNumar;
import clinica.app.Exceptii.ExceptieNumarCuZero;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddPayments {

	JFrame frmAddPayment;
	private JTextField txtNamePayment;
	private JTextField txtAmount;
	private JTextField txtManFacName;
	private JLabel lblPleaseIntroduceThe_2_1_2;
	private JTextField txtCUI;
	private JLabel lblPleaseIntroduceThe_2_1_3;
	private JTextField txtAdress;
	private JLabel lblPleaseIntroduceThe_2_1_4;
	private JTextField txtIban;
	private JButton btnPaymentAdd;
	JDateChooser datePayment = new JDateChooser();
	private JLabel lblAlertAmountPP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPayments window = new AddPayments();
					window.frmAddPayment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddPayments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddPayment = new JFrame();
		frmAddPayment.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-add-property-16.png"));
		frmAddPayment.setTitle("Add Payment");
		frmAddPayment.setBounds(100, 100, 854, 715);
		frmAddPayment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddPayment.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 800, 642);
		frmAddPayment.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblPleaseIntroduceThe = new JLabel("Payment Name");
		lblPleaseIntroduceThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe.setBounds(12, 42, 127, 25);
		panel.add(lblPleaseIntroduceThe);

		txtNamePayment = new JTextField();
		txtNamePayment.setBounds(134, 39, 159, 33);
		panel.add(txtNamePayment);
		txtNamePayment.setColumns(10);

		JLabel lblPleaseIntroduceThe_2 = new JLabel("Amount(RON)");
		lblPleaseIntroduceThe_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2.setBounds(12, 156, 110, 25);
		panel.add(lblPleaseIntroduceThe_2);

		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String suma = txtAmount.getText();
					lblAlertAmountPP.setText("");
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
					lblAlertAmountPP.setText("Invalid number");
				} catch (ExceptieDoarNumar doarNumar) {
					lblAlertAmountPP.setText("This is not a number");
				}

			}
		});
		txtAmount.setColumns(10);
		txtAmount.setBounds(134, 153, 159, 33);
		panel.add(txtAmount);

		JLabel lblPleaseIntroduceThe_2_1 = new JLabel("Payment Date");
		lblPleaseIntroduceThe_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1.setBounds(12, 261, 110, 25);
		panel.add(lblPleaseIntroduceThe_2_1);

		JLabel lblPleaseIntroduceThe_2_1_1 = new JLabel(" Manufacturer's Name");
		lblPleaseIntroduceThe_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_1.setBounds(374, 42, 164, 25);
		panel.add(lblPleaseIntroduceThe_2_1_1);

		txtManFacName = new JTextField();
		txtManFacName.setColumns(10);
		txtManFacName.setBounds(550, 39, 222, 33);
		panel.add(txtManFacName);

		lblPleaseIntroduceThe_2_1_2 = new JLabel("Unique Registration Code");
		lblPleaseIntroduceThe_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_2.setBounds(374, 261, 206, 25);
		panel.add(lblPleaseIntroduceThe_2_1_2);

		txtCUI = new JTextField();
		txtCUI.setColumns(10);
		txtCUI.setBounds(572, 258, 216, 33);
		panel.add(txtCUI);

		lblPleaseIntroduceThe_2_1_3 = new JLabel(" Company  Address");
		lblPleaseIntroduceThe_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_3.setBounds(374, 156, 152, 25);
		panel.add(lblPleaseIntroduceThe_2_1_3);

		txtAdress = new JTextField();
		txtAdress.setColumns(10);
		txtAdress.setBounds(538, 150, 250, 38);
		panel.add(txtAdress);

		lblPleaseIntroduceThe_2_1_4 = new JLabel("IBAN");
		lblPleaseIntroduceThe_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseIntroduceThe_2_1_4.setBounds(12, 388, 110, 25);
		panel.add(lblPleaseIntroduceThe_2_1_4);

		txtIban = new JTextField();
		txtIban.setColumns(10);
		txtIban.setBounds(130, 385, 206, 33);
		panel.add(txtIban);

		btnPaymentAdd = new JButton("Register Payment");
		btnPaymentAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int payment = JOptionPane.showConfirmDialog(null, "Are you sure you want to register this payment?");
				if (payment == 0) {
					insertPayment();
				} else {
					JOptionPane.showMessageDialog(null, "Thank you!");
				}

			}
		});
		btnPaymentAdd.setHideActionText(true);
		btnPaymentAdd.setForeground(Color.DARK_GRAY);
		btnPaymentAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPaymentAdd.setFocusable(false);
		btnPaymentAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnPaymentAdd.setBackground(Color.WHITE);
		btnPaymentAdd.setBounds(284, 569, 192, 60);
		panel.add(btnPaymentAdd);

		datePayment.setDateFormatString("yyyy-MM-dd");
		datePayment.setBounds(134, 264, 159, 22);
		panel.add(datePayment);

		lblAlertAmountPP = new JLabel("");
		lblAlertAmountPP.setForeground(Color.RED);
		lblAlertAmountPP.setBounds(131, 119, 128, 16);
		panel.add(lblAlertAmountPP);
	}

	public void insertPayment() {
		String nume = txtNamePayment.getText();
		String suma = txtAmount.getText();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String dataPlata = dformat.format(datePayment.getDate());
		String denumireProducator = txtManFacName.getText();
		String CUI = txtCUI.getText();
		String AdresaFirma = txtAdress.getText();
		String Iban = txtIban.getText();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO `dbclinica`.`plati` (`denumire_plata`, `suma`, `data_plata`, `denumire_producator`, `CUI_firma`, `adresa_firma`, `cod_iban`) VALUES ('"
							+ nume + "', '" + suma + "', '" + dataPlata + "', '" + denumireProducator + "', '" + CUI
							+ "', '" + AdresaFirma + "', '" + Iban + "');");
			int resultSet = preparedStatement.executeUpdate();
			if (resultSet > 0) {
				JOptionPane.showMessageDialog(null, "The payment has been registered!");
			} else {
				JOptionPane.showMessageDialog(null, "The payment has not been registered!");
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}

	}
}
