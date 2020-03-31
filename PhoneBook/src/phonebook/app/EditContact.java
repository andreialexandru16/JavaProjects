package phonebook.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import phonebook.app.Exceptii.ExceptieCampuriNecompletate;
import phonebook.app.Exceptii.ExceptieDouaCampuriNecompletateFirstName;
import phonebook.app.Exceptii.ExceptieDouaNecompletatSurname;
import phonebook.app.Exceptii.ExceptieDouaNecompletatePhonenNumber;
import phonebook.app.Exceptii.ExceptieFirstName;
import phonebook.app.Exceptii.ExceptieNumar;
import phonebook.app.Exceptii.ExceptieNumarSave;
import phonebook.app.Exceptii.ExceptieSurname;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class EditContact {

	JFrame frmEditContact;
	private JTextField textNOTES;
	private JTextField textPhoneNumber;
	private JTextField textAdress;
	private JTextField textFirstName;
	private JTextField textSurname;
	private JDateChooser dateBirth;
	private JComboBox comboTypeNumber;
	private JComboBox comboType;
	private JCheckBox chckFavorite;
	Contact contactt;
	private JLabel lblAlertSurname;
	private JLabel lblAlertFirstName;
	private JLabel lblAlertPhoneNR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContact window = new EditContact();
					window.frmEditContact.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditContact() {
		initialize();
	}

	public void setContact(Contact contact) {
		this.contactt = contact;
		if (this.contactt.getAdresa() == null) {
			textAdress.setText("");
		} else {
			textAdress.setText(this.contactt.getAdresa());
		}
		if(this.contactt.getDate()==null) {
			dateBirth.setDate(this.contactt.getDate());
		}else {
			dateBirth.setDate(this.contactt.getDate());
		}
		if (this.contactt.getGen() == null) {
			comboType.setSelectedIndex(0);
		} else {
			if (this.contactt.getGen().matches("M")) {
				comboType.setSelectedIndex(0);
			} else if (this.contactt.getGen().matches("F")) {
				comboType.setSelectedIndex(1);
			}
		}
		if (this.contactt.getSurname() == null) {
			textSurname.setText("");
		} else {
			textSurname.setText(this.contactt.getSurname());
		}
		if (this.contactt.getFirstName() == null) {
			textFirstName.setText("");
		} else {
			textFirstName.setText(this.contactt.getFirstName());
		}
		if (this.contactt.getTelefon() == null) {
			textPhoneNumber.setText("");
		} else {
			textPhoneNumber.setText(this.contactt.getTelefon());
		}
		if (this.contactt.getTipNumar() < 1) {
			comboTypeNumber.setSelectedIndex(1);
		} else {
			if (this.contactt.getTipNumar() == 1) {
				comboTypeNumber.setSelectedIndex(0);
			} else if (this.contactt.getTipNumar() == 2) {
				comboTypeNumber.setSelectedIndex(1);
			}
		}
		if (this.contactt.getNotes() == null) {
			textNOTES.setText("");
		} else {
			textNOTES.setText(this.contactt.getNotes());
		}
		if (this.contactt.getFavorite() == 0) {
			chckFavorite.setSelected(false);
		} else {
			chckFavorite.setSelected(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditContact = new JFrame();
		frmEditContact.getContentPane().setBackground(Color.WHITE);
		frmEditContact.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-add-phone-30.png"));
		frmEditContact.setTitle("Edit Contact");
		frmEditContact.setBounds(100, 100, 876, 743);
		frmEditContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditContact.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\download.png"));
		lblNewLabel.setBounds(12, 13, 278, 241);
		frmEditContact.getContentPane().add(lblNewLabel);

		JLabel lblPersonalContact = new JLabel("Personal Contact");
		lblPersonalContact.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonalContact.setFont(new Font("Arial", Font.BOLD, 24));
		lblPersonalContact.setBounds(257, 26, 564, 71);
		frmEditContact.getContentPane().add(lblPersonalContact);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(257, 94, 82, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(257, 147, 82, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("FirstName");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(257, 201, 82, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date of Birth");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(257, 253, 95, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblContactDetails = new JLabel("Contact Details");
		lblContactDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactDetails.setFont(new Font("Arial", Font.BOLD, 24));
		lblContactDetails.setBounds(257, 285, 564, 71);
		frmEditContact.getContentPane().add(lblContactDetails);

		JLabel lblNewLabel_1_1_2 = new JLabel("Adress");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(257, 354, 82, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Phone Number");
		lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_1.setBounds(257, 402, 123, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Phone Type");
		lblNewLabel_1_1_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_2.setBounds(257, 457, 95, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_2_2);

		JLabel lblOther = new JLabel("Other\r\n");
		lblOther.setHorizontalAlignment(SwingConstants.LEFT);
		lblOther.setFont(new Font("Arial", Font.BOLD, 24));
		lblOther.setBounds(257, 499, 564, 50);
		frmEditContact.getContentPane().add(lblOther);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Notes");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_2_1_1.setBounds(257, 580, 123, 30);
		frmEditContact.getContentPane().add(lblNewLabel_1_1_2_1_1);

		textNOTES = new JTextField();
		textNOTES.setFont(new Font("Arial", Font.BOLD, 15));
		textNOTES.setColumns(10);
		textNOTES.setBounds(463, 564, 363, 71);
		frmEditContact.getContentPane().add(textNOTES);

		textPhoneNumber = new JTextField();
		textPhoneNumber.setFont(new Font("Arial", Font.BOLD, 15));
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(417, 394, 429, 38);
		frmEditContact.getContentPane().add(textPhoneNumber);

		textAdress = new JTextField();
		textAdress.setFont(new Font("Arial", Font.BOLD, 15));
		textAdress.setColumns(10);
		textAdress.setBounds(417, 346, 429, 38);
		frmEditContact.getContentPane().add(textAdress);

		dateBirth = new JDateChooser();
		dateBirth.setFont(new Font("Arial", Font.BOLD, 15));
		dateBirth.setDateFormatString("yyyy-MM-dd");
		dateBirth.setBounds(417, 246, 429, 37);
		frmEditContact.getContentPane().add(dateBirth);

		textFirstName = new JTextField();
		textFirstName.setFont(new Font("Arial", Font.BOLD, 15));
		textFirstName.setColumns(10);
		textFirstName.setBounds(417, 193, 429, 38);
		frmEditContact.getContentPane().add(textFirstName);

		textSurname = new JTextField();
		textSurname.setFont(new Font("Arial", Font.BOLD, 15));
		textSurname.setColumns(10);
		textSurname.setBounds(417, 139, 429, 38);
		frmEditContact.getContentPane().add(textSurname);

		comboType = new JComboBox();
		comboType.setBackground(Color.WHITE);
		comboType.setFocusable(false);
		comboType.setFont(new Font("Arial", Font.BOLD, 15));
		comboType.setModel(new DefaultComboBoxModel(new String[] { "Mr", "Mrs" }));
		comboType.setSelectedIndex(0);
		comboType.setBounds(417, 99, 429, 33);
		frmEditContact.getContentPane().add(comboType);

		comboTypeNumber = new JComboBox();
		comboTypeNumber.setModel(new DefaultComboBoxModel(new String[] { "Personal", "Work" }));
		comboTypeNumber.setSelectedIndex(0);
		comboTypeNumber.setFont(new Font("Arial", Font.BOLD, 15));
		comboTypeNumber.setFocusable(false);
		comboTypeNumber.setBackground(Color.WHITE);
		comboTypeNumber.setBounds(417, 462, 429, 33);
		frmEditContact.getContentPane().add(comboTypeNumber);

		chckFavorite = new JCheckBox("Favorite number?\r\n");
		chckFavorite.setFont(new Font("Arial", Font.BOLD, 15));
		chckFavorite.setFocusable(false);
		chckFavorite.setBackground(Color.WHITE);
		chckFavorite.setBounds(12, 296, 213, 25);
		frmEditContact.getContentPane().add(chckFavorite);
		
		JButton btnSaveEdit = new JButton("Save Edit");
		btnSaveEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblAlertFirstName.setText("");
					lblAlertPhoneNR.setText("");
					lblAlertSurname.setText("");
				if(textSurname.getText().isEmpty() && !(textFirstName.getText().isEmpty()) && !(textPhoneNumber.getText().isEmpty())) {
					throw new ExceptieSurname();
				}
				if(textFirstName.getText().isEmpty() && !(textSurname.getText().isEmpty()) && !(textPhoneNumber.getText().isEmpty())) {
					throw new ExceptieFirstName();
				}
				if(textPhoneNumber.getText().isEmpty() && !(textSurname.getText().isEmpty()) && !(textFirstName.getText().isEmpty())) {
					throw new ExceptieNumar();
				}
				if(textSurname.getText().isEmpty() && textFirstName.getText().isEmpty() && textPhoneNumber.getText().isEmpty()) { 
					throw new ExceptieCampuriNecompletate();
				}
				if(textSurname.getText().isEmpty() && textFirstName.getText().isEmpty() && !(textPhoneNumber.getText().isEmpty())) {
					throw new ExceptieDouaNecompletatePhonenNumber();
				}
				if(textSurname.getText().isEmpty() && !(textFirstName.getText().isEmpty()) && textPhoneNumber.getText().isEmpty()) {
					throw new  ExceptieDouaCampuriNecompletateFirstName();
				}
				if(!(textSurname.getText().isEmpty()) && textFirstName.getText().isEmpty() && textPhoneNumber.getText().isEmpty()) {
					throw new  ExceptieDouaNecompletatSurname();
				}
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook",
						"root", "root");
				String gen="";
				int titleIndex=comboType.getSelectedIndex();
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
				String adresa=textAdress.getText();
				String telefon=textPhoneNumber.getText();
				for(int i=0;i<telefon.length();i++) {
					char c=telefon.charAt(i);
					if(Character.isDigit(c)==false) {
						throw new ExceptieNumarSave();
					}
				}
				int type=comboTypeNumber.getSelectedIndex();
				int insertTYPE=0;
				if(type==0) {
					 insertTYPE=2;
				}else if(type==1) {
					insertTYPE=1;
				}
				String notes=textNOTES.getText();
				PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `dbphonebook`.`contacte` SET `nume` = '" + nume +"', `prenume` ='" + prenume +"', `gen` = '" + gen +"', `adresa` = '" + adresa +"', `notite` = '" + notes +"', `numar_telefon` = '" + telefon + "', `tip_id` = '" + insertTYPE + "' , `favorite` = '" + fav +"' WHERE (`id_contact` = '" + contactt.getId() + "');\r\n" + 
						"");
				int resultSet=preparedStatement.executeUpdate();
				if(resultSet>0) {
					JOptionPane.showMessageDialog(null, "Edit was saved!");
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
				textPhoneNumber.setText("");
				textAdress.setText("");
				textFirstName.setText("");
				textNOTES.setText("");
				textSurname.setText("");
				chckFavorite.setSelected(false);
			
				
			}
		});
		btnSaveEdit.setHideActionText(true);
		btnSaveEdit.setForeground(Color.DARK_GRAY);
		btnSaveEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSaveEdit.setFocusable(false);
		btnSaveEdit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSaveEdit.setBackground(Color.WHITE);
		btnSaveEdit.setBounds(12, 584, 138, 56);
		frmEditContact.getContentPane().add(btnSaveEdit);
		
		lblAlertSurname = new JLabel("");
		lblAlertSurname.setBackground(Color.RED);
		lblAlertSurname.setBounds(257, 172, 95, 16);
		frmEditContact.getContentPane().add(lblAlertSurname);
		
		lblAlertFirstName = new JLabel("");
		lblAlertFirstName.setBackground(Color.RED);
		lblAlertFirstName.setBounds(257, 224, 107, 16);
		frmEditContact.getContentPane().add(lblAlertFirstName);
		
		lblAlertPhoneNR = new JLabel("");
		lblAlertPhoneNR.setBackground(Color.RED);
		lblAlertPhoneNR.setBounds(257, 428, 107, 16);
		frmEditContact.getContentPane().add(lblAlertPhoneNR);
	}
}
