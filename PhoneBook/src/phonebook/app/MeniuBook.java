package phonebook.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.PreparableStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MeniuBook {

	JFrame frmPhoneBook;
	private JTextField textField;
	private JTable table;
	private JButton btnAddContact;
	private JButton btnShowFavContacts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniuBook window = new MeniuBook();
					window.frmPhoneBook.setVisible(true);
					window.frmPhoneBook.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MeniuBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int selectContactRow = table.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String numar = model.getValueAt(selectContactRow, 2).toString();
					int type = getContactFavorNot(numar);
					int ok = 0;
					Object stringArray[] = { "Favorite", "Edit","Delete" };
					int resultAlegere = JOptionPane.showOptionDialog(null,
							"What do you want to do with this contact?", " Contact Option",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);
					if (resultAlegere == JOptionPane.YES_OPTION) {
						int id = getIDContactForChanging(numar);
						updateFavoriteContact(id);
					} else if (resultAlegere == JOptionPane.NO_OPTION) {
						int idNo = getIDContactForChanging(numar);
						Contact contact = createContactForEdit(idNo);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									EditContact window = new EditContact();
									window.frmEditContact.setVisible(true);
									window.setContact(contact);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}else if(resultAlegere==JOptionPane.CANCEL_OPTION) {
						int idNo = getIDContactForChanging(numar);
						deleteContact(idNo);
						model.removeRow(selectContactRow);
						
					}

				} catch (Exception EX) {
					JOptionPane.showMessageDialog(null, "Eror table edit");
				}
			}
		});
		frmPhoneBook = new JFrame();
		frmPhoneBook.getContentPane().setBackground(Color.WHITE);
		frmPhoneBook.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frmPhoneBook.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-phone-book-30.png"));
		frmPhoneBook.setTitle("Phone Book");
		frmPhoneBook.setBounds(100, 100, 685, 627);
		frmPhoneBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhoneBook.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				boolean ok = false;
				for (int i = 0; i < textField.getText().length(); i++) {
					char c = textField.getText().charAt(i);
					if (Character.isAlphabetic(c) == true) {
						ok = true;
					}

				}
				if (ok == false) {
					pressKeyNumber();
				} else {
					getContactByNameContact();
				}

			}

			public void getContactByNameContact() {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Nume");
				model.addColumn("Prenume");
				model.addColumn("Telefon");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook",
							"root", "root");
					String[] text = textField.getText().split(" ");
					String nume = text[0];
					PreparedStatement preparedStatement = connection
							.prepareStatement("SELECT nume,prenume,numar_telefon\r\n" + "from contacte\r\n"
									+ "where nume like '%" + nume + "%'");
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						Object[] row = new Object[3];
						row[0] = resultSet.getString("nume");
						row[1] = resultSet.getString("prenume");
						row[2] = resultSet.getString("numar_telefon");
						model.addRow(row);
					}
					table.setModel(model);
					connection.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Database Error!");
				}

			}

			public void pressKeyNumber() {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Nume");
				model.addColumn("Prenume");
				model.addColumn("Telefon");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook",
							"root", "root");
					PreparedStatement prepStatement = connection.prepareStatement("	SELECT nume,prenume,numar_telefon "
							+ "from contacte where numar_telefon LIKE '%" + textField.getText() + "%'");
					ResultSet resultSet = prepStatement.executeQuery();
					while (resultSet.next()) {
						Object[] row = new Object[3];
						row[0] = resultSet.getString("nume");
						row[1] = resultSet.getString("prenume");
						row[2] = resultSet.getString("numar_telefon");
						model.addRow(row);
						;
					}
					table.setModel(model);
					connection.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Database Error!");
				}
			}
		});
		textField.setBounds(39, 0, 628, 41);
		frmPhoneBook.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Asus\\Desktop\\Icons\\icons8-search-45.png"));
		lblNewLabel.setBounds(0, 0, 39, 41);
		frmPhoneBook.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 46, 645, 461);
		frmPhoneBook.getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		JButton btnShowContacts = new JButton("Show Contacts");
		btnShowContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContactByPatternNumber();
			}

			public void getContactByPatternNumber() {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Nume");
				model.addColumn("Prenume");
				model.addColumn("Telefon");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook",
							"root", "root");
					PreparedStatement prepStatement = connection
							.prepareStatement("	SELECT nume,prenume,numar_telefon\r\n" + "    from contacte\r\n");

					ResultSet resultSet = prepStatement.executeQuery();
					while (resultSet.next()) {
						Object[] row = new Object[3];
						row[0] = resultSet.getString("nume");
						row[1] = resultSet.getString("prenume");
						row[2] = resultSet.getString("numar_telefon");
						model.addRow(row);

					}
					table.setModel(model);
					connection.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Database Error!");
				}
			}
		});
		btnShowContacts.setHideActionText(true);
		btnShowContacts.setForeground(Color.DARK_GRAY);
		btnShowContacts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShowContacts.setFocusable(false);
		btnShowContacts.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowContacts.setBackground(Color.WHITE);
		btnShowContacts.setBounds(20, 516, 125, 51);
		frmPhoneBook.getContentPane().add(btnShowContacts);

		btnAddContact = new JButton("Add Contact\r\n");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		btnAddContact.setHideActionText(true);
		btnAddContact.setForeground(Color.DARK_GRAY);
		btnAddContact.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddContact.setFocusable(false);
		btnAddContact.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAddContact.setBackground(Color.WHITE);
		btnAddContact.setBounds(157, 516, 125, 51);
		frmPhoneBook.getContentPane().add(btnAddContact);

		btnShowFavContacts = new JButton(" Favorite Contacts");
		btnShowFavContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFavoriteContacts();

			}
		});
		btnShowFavContacts.setHideActionText(true);
		btnShowFavContacts.setForeground(Color.DARK_GRAY);
		btnShowFavContacts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShowFavContacts.setFocusable(false);
		btnShowFavContacts.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowFavContacts.setBackground(Color.WHITE);
		btnShowFavContacts.setBounds(294, 516, 125, 51);
		frmPhoneBook.getContentPane().add(btnShowFavContacts);
	}
	
	
	public void deleteContact(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement=connection.prepareStatement("DELETE from contacte where id_contact='" + id + "'");
			int resultSet=preparedStatement.executeUpdate();
			if(resultSet==0) {
				JOptionPane.showMessageDialog(null, "Deleted Error!");
			}else {
				JOptionPane.showMessageDialog(null, "The contact is deleted!");
			}
			connection.close();
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}
	}

	public void updateFavoriteContact(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("update contacte \r\n" + "Set favorite=1\r\n" + "where id_contact='" + id + "'");
			int resultSet = preparedStatement.executeUpdate();
			if (resultSet == 0) {
				JOptionPane.showMessageDialog(null, "The contact is not favorite now!");
			} else if (resultSet != 0) {
				JOptionPane.showMessageDialog(null, "The contact is  favorite now!");
			}
			connection.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}

	}

	public int getIDContactForChanging(String numar) {
		int id = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"Select id_contact \r\n" + "from contacte \r\n" + "where numar_telefon='" + numar + "'");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id_contact");
			}
			connection.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}
		return id;
	}

	public int getContactFavorNot(String numar) {
		int type = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select favorite \r\n" + "from contacte \r\n" + "where numar_telefon='" + numar + "'");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				type = resultSet.getInt("favorite");
			}
			connection.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}
		return type;
	}

	public Contact createContactForEdit(int id) {
		Contact contact = new Contact(0,null, null, null, null, null, null, 0, 0, null);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM contacte\r\n" + "where id_contact='" + id + "'");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				contact.setId(resultSet.getInt("id_contact"));
				contact.setSurname(resultSet.getString("nume"));
				contact.setFirstName(resultSet.getString("prenume"));
				contact.setGen(resultSet.getString("gen"));
				contact.setDate(resultSet.getDate("zi_de_nastere"));
				contact.setAdresa(resultSet.getString("adresa"));
				contact.setTelefon(resultSet.getString("numar_telefon"));
				contact.setTipNumar(resultSet.getInt("tip_id"));
				contact.setFavorite(resultSet.getInt("favorite"));
				contact.setNotes(resultSet.getString("notite"));
			}
			connection.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}
		return contact;
	}

	public void getFavoriteContacts() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Telefon");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbphonebook", "root",
					"root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("Select nume,prenume,numar_telefon from contacte " + "where favorite=1");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[3];
				row[0] = resultSet.getString("nume");
				row[1] = resultSet.getString("prenume");
				row[2] = resultSet.getString("numar_telefon");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Database Error!");
		}

	}
}
