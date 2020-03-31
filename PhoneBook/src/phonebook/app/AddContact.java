package phonebook.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import phonebook.app.Exceptii.ExceptieCampuriNecompletate;
import phonebook.app.Exceptii.ExceptieDouaCampuriNecompletateFirstName;
import phonebook.app.Exceptii.ExceptieDouaNecompletatSurname;
import phonebook.app.Exceptii.ExceptieDouaNecompletatePhonenNumber;
import phonebook.app.Exceptii.ExceptieFirstName;
import phonebook.app.Exceptii.ExceptieNumar;
import phonebook.app.Exceptii.ExceptieNumarSave;
import phonebook.app.Exceptii.ExceptieSurname;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class AddContact {

	JFrame frmAddContact;
	private JTextField textSurname;
	private JTextField textFirstName;
	private JTextField textAdresa;
	private JTextField txtPhoneNumber;
	private JTextField textNotes;
	private JLabel lblAlertPhoneNR;
	private JLabel lblAlertSurname;
	private JLabel lblAlertFirstName;
	private JCheckBox chckFavorite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact window = new AddContact();
					window.frmAddContact.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddContact() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddContact = new JFrame();
		frmAddContact.setTitle("Add Contact");
		frmAddContact.setBounds(100, 100, 896, 720);
		frmAddContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddContact.getContentPane().setLayout(null);
		
		JLabel lblPersonalContact = new JLabel("Personal Contact");
		lblPersonalContact.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonalContact.setFont(new Font("Arial", Font.BOLD, 24));
		lblPersonalContact.setBounds(302, 30, 564, 71);
		frmAddContact.getContentPane().add(lblPersonalContact);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\Experiencia-Usuario.png"));
		lblNewLabel.setBounds(12, 13, 278, 241);
		frmAddContact.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(302, 102, 82, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboTILTE = new JComboBox();
		comboTILTE.setFont(new Font("Arial", Font.BOLD, 15));
		comboTILTE.setModel(new DefaultComboBoxModel(new String[] {"Mr", "Mrs"}));
		comboTILTE.setSelectedIndex(0);
		comboTILTE.setBackground(Color.WHITE);
		comboTILTE.setFocusable(false);
		comboTILTE.setBounds(437, 102, 429, 33);
		frmAddContact.getContentPane().add(comboTILTE);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(302, 151, 82, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1);
		
		textSurname = new JTextField();
		textSurname.setBounds(437, 148, 429, 38);
		frmAddContact.getContentPane().add(textSurname);
		textSurname.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("FirstName");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(302, 209, 82, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_1);
		
		textFirstName = new JTextField();
		textFirstName.setColumns(10);
		textFirstName.setBounds(437, 199, 429, 38);
		frmAddContact.getContentPane().add(textFirstName);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date of Birth");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(302, 265, 95, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JDateChooser dateBirth = new JDateChooser();
		dateBirth.setDateFormatString("yyyy-MM-dd");
		dateBirth.setBounds(437, 258, 429, 37);
		frmAddContact.getContentPane().add(dateBirth);
		
		JLabel lblContactDetails = new JLabel("Contact Details");
		lblContactDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactDetails.setFont(new Font("Arial", Font.BOLD, 24));
		lblContactDetails.setBounds(302, 308, 564, 71);
		frmAddContact.getContentPane().add(lblContactDetails);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Adress");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(302, 380, 82, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_2);
		
		textAdresa = new JTextField();
		textAdresa.setColumns(10);
		textAdresa.setBounds(437, 372, 429, 38);
		frmAddContact.getContentPane().add(textAdresa);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Phone Number");
		lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_1.setBounds(302, 435, 123, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_2_1);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String numar=txtPhoneNumber.getText();
					lblAlertPhoneNR.setText("");
					for(int i=0;i<numar.length();i++) {
						char c=numar.charAt(i);
						if(Character.isDigit(c)==false) {
							throw new ExceptieNumar();
						}
					}
				}catch(ExceptieNumar exceptieNumar) {
					lblAlertPhoneNR.setText("Invalid Number!");
				}
			}
		});
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(437, 432, 429, 38);
		frmAddContact.getContentPane().add(txtPhoneNumber);
		
		JLabel lblOther = new JLabel("Other\r\n");
		lblOther.setHorizontalAlignment(SwingConstants.LEFT);
		lblOther.setFont(new Font("Arial", Font.BOLD, 24));
		lblOther.setBounds(302, 540, 564, 50);
		frmAddContact.getContentPane().add(lblOther);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Notes");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_1_1.setBounds(302, 603, 123, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Phone Type");
		lblNewLabel_1_1_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_2.setBounds(302, 486, 95, 30);
		frmAddContact.getContentPane().add(lblNewLabel_1_1_2_2);
		
		JComboBox comboBoxPhoenType = new JComboBox();
		comboBoxPhoenType.setFont(new Font("Arial", Font.BOLD, 15));
		comboBoxPhoenType.setModel(new DefaultComboBoxModel(new String[] {"Personal", "Work"}));
		comboBoxPhoenType.setSelectedIndex(0);
		comboBoxPhoenType.setFocusable(false);
		comboBoxPhoenType.setBackground(Color.WHITE);
		comboBoxPhoenType.setBounds(437, 483, 429, 33);
		frmAddContact.getContentPane().add(comboBoxPhoenType);
		
		textNotes = new JTextField();
		textNotes.setBounds(453, 589, 363, 71);
		frmAddContact.getContentPane().add(textNotes);
		textNotes.setColumns(10);
		
		lblAlertPhoneNR = new JLabel("");
		lblAlertPhoneNR.setForeground(Color.RED);
		lblAlertPhoneNR.setBackground(Color.RED);
		lblAlertPhoneNR.setBounds(283, 461, 147, 17);
		frmAddContact.getContentPane().add(lblAlertPhoneNR);
		
		JButton btnSaveContact = new JButton("Save");
		btnSaveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveContact(comboTILTE, dateBirth, comboBoxPhoenType);
			}

			public void saveContact(JComboBox comboTILTE, JDateChooser dateBirth, JComboBox comboBoxPhoenType) {
				try {
					lblAlertFirstName.setText("");
					lblAlertPhoneNR.setText("");
					lblAlertSurname.setText("");
				if(textSurname.getText().isEmpty() && !(textFirstName.getText().isEmpty()) && !(txtPhoneNumber.getText().isEmpty())) {
					throw new ExceptieSurname();
				}
				if(textFirstName.getText().isEmpty() && !(textSurname.getText().isEmpty()) && !(txtPhoneNumber.getText().isEmpty())) {
					throw new ExceptieFirstName();
				}
				if(txtPhoneNumber.getText().isEmpty() && !(textSurname.getText().isEmpty()) && !(textFirstName.getText().isEmpty())) {
					throw new ExceptieNumar();
				}
				if(textSurname.getText().isEmpty() && textFirstName.getText().isEmpty() && txtPhoneNumber.getText().isEmpty()) { 
					throw new ExceptieCampuriNecompletate();
				}
				if(textSurname.getText().isEmpty() && textFirstName.getText().isEmpty() && !(txtPhoneNumber.getText().isEmpty())) {
					throw new ExceptieDouaNecompletatePhonenNumber();
				}
				if(textSurname.getText().isEmpty() && !(textFirstName.getText().isEmpty()) && txtPhoneNumber.getText().isEmpty()) {
					throw new  ExceptieDouaCampuriNecompletateFirstName();
				}
				if(!(textSurname.getText().isEmpty()) && textFirstName.getText().isEmpty() && txtPhoneNumber.getText().isEmpty()) {
					throw new  ExceptieDouaNecompletatSurname();
				}
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook",
						"root", "root");
				String gen="";
				int titleIndex=comboTILTE.getSelectedIndex();
				if(titleIndex==0) {
					gen="M";
				}else if(titleIndex==1) {
					gen="F";
				}
				int fav=0;
				if(chckFavorite.isSelected()==true) {
					fav=1;
				}else if(chckFavorite.isSelected()==false) {
					fav=0;
				}
				String nume=textSurname.getText();
				String prenume=textFirstName.getText();
				SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
				String data=dformat.format(dateBirth.getDate());
				String adresa=textAdresa.getText();
				String telefon=txtPhoneNumber.getText();
				for(int i=0;i<telefon.length();i++) {
					char c=telefon.charAt(i);
					if(Character.isDigit(c)==false) {
						throw new ExceptieNumarSave();
					}
				}
				int type=comboBoxPhoenType.getSelectedIndex();
				int insertTYPE=0;
				if(type==0) {
					 insertTYPE=2;
				}else if(type==1) {
					insertTYPE=1;
				}
				String notes=textNotes.getText();
				PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `dbphonebook`.`contacte` (`nume`, `prenume`, `gen`, `adresa`, `zi_de_nastere`, `notite`, `numar_telefon`, `tip_id`, `favorite`) VALUES ('" + nume + "', '" + prenume +"', '" + gen +"', '" + adresa +"', '" + data +"', '" +  notes +"', '" + telefon +"', '" + insertTYPE +"', '" + fav +"');");
				int resultSet=preparedStatement.executeUpdate();
				if(resultSet>0) {
					JOptionPane.showMessageDialog(null, "Save was a succes!");
				}
				}catch(ExceptieNumarSave exceptieNumarSave) {
					JOptionPane.showMessageDialog(null, "Number is not correct!");
					
				}catch(ExceptieSurname exceptieSurname) {
					lblAlertSurname.setText("Complete Surname!");
				}catch(ExceptieFirstName exceptieFirstName) {
					lblAlertFirstName.setText("Complete FirstName!");
				}catch(ExceptieNumar exceptieNumar) {
					lblAlertPhoneNR.setText("Complete PhoneNumber!");
				}catch(ExceptieCampuriNecompletate campuriNecompletate) {
					lblAlertSurname.setText("Complete Surname!");
					lblAlertFirstName.setText("Complete FirstName!");
					lblAlertPhoneNR.setText("Complete PhoneNumber!");
				}catch(ExceptieDouaNecompletatePhonenNumber douaNecompletatePhonenNumber) {
					lblAlertSurname.setText("Complete Surname!");
					lblAlertFirstName.setText("Complete FirstName!");
				}catch(ExceptieDouaCampuriNecompletateFirstName campuriNecompletateFirstName) {
					lblAlertSurname.setText("Complete Surname!");
					lblAlertPhoneNR.setText("Complete PhoneNumber!");
				}catch(ExceptieDouaNecompletatSurname douaNecompletatSurname) {
					lblAlertFirstName.setText("Complete FirstName!");
					lblAlertPhoneNR.setText("Complete PhoneNumber!");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Database Error!");
				}
				txtPhoneNumber.setText("");
				textAdresa.setText("");
				textFirstName.setText("");
				textNotes.setText("");
				textSurname.setText("");
				chckFavorite.setSelected(false);
			}
		});
		btnSaveContact.setHideActionText(true);
		btnSaveContact.setForeground(Color.DARK_GRAY);
		btnSaveContact.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSaveContact.setFocusable(false);
		btnSaveContact.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSaveContact.setBackground(Color.WHITE);
		btnSaveContact.setBounds(47, 589, 138, 56);
		frmAddContact.getContentPane().add(btnSaveContact);
		
		lblAlertFirstName = new JLabel("");
		lblAlertFirstName.setForeground(Color.RED);
		lblAlertFirstName.setBackground(Color.RED);
		lblAlertFirstName.setBounds(302, 231, 128, 17);
		frmAddContact.getContentPane().add(lblAlertFirstName);
		
		lblAlertSurname = new JLabel("");
		lblAlertSurname.setForeground(Color.RED);
		lblAlertSurname.setBackground(Color.RED);
		lblAlertSurname.setBounds(302, 176, 123, 17);
		frmAddContact.getContentPane().add(lblAlertSurname);
		
		chckFavorite = new JCheckBox("Favorite number?\r\n");
		chckFavorite.setFont(new Font("Arial", Font.BOLD, 15));
		chckFavorite.setBounds(12, 296, 213, 25);
		frmAddContact.getContentPane().add(chckFavorite);
	}
}
