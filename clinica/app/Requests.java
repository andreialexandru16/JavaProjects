package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Requests {

	JFrame frmRequests;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Requests window = new Requests();
					window.frmRequests.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Requests() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRequests = new JFrame();
		frmRequests.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-message-exchange-16.png"));
		frmRequests.setTitle("Requests");
		frmRequests.setBounds(100, 100, 755, 640);
		frmRequests.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRequests.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(12, 28, 713, 452);
		frmRequests.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectStatusRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String cnp = model.getValueAt(selectStatusRow, 3).toString();
				int result = JOptionPane.showConfirmDialog(null, "Accept request?");
				if (result == JOptionPane.YES_OPTION) {
					int id=getDepartmentID(cnp);
					int nrEmployee=NumberEmployeeDepartment(id);
					boolean verificare=RezilereContract(cnp);
					if(verificare==true) {
					YesNOOptionRequest(cnp);
					UpdateNumberEmployee(id, nrEmployee);
					model.removeRow(selectStatusRow);
					}
				}else if(result==JOptionPane.NO_OPTION) {
					YesNOOptionRequest(cnp);
					model.removeRow(selectStatusRow);
					
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton btnShowRequests = new JButton("Show Requests");
		btnShowRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRequests();
			}
		});
		btnShowRequests.setHideActionText(true);
		btnShowRequests.setForeground(Color.DARK_GRAY);
		btnShowRequests.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowRequests.setFocusable(false);
		btnShowRequests.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowRequests.setBackground(Color.WHITE);
		btnShowRequests.setBounds(285, 527, 155, 53);
		frmRequests.getContentPane().add(btnShowRequests);
	}

	public void showRequests() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Request");
		model.addColumn("Employee's Surname");
		model.addColumn("Employee's FirstName");
		model.addColumn("CNP");
		model.addColumn("Remark");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement("Select * from cerererezilierecontract");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object[] row = new Object[5];
				row[0] = resultSet.getInt("id_mesaj");
				row[1] = resultSet.getString("nume_angajat");
				row[2] = resultSet.getString("prenume_angajat");
				row[3] = resultSet.getString("cnp");
				row[4] = resultSet.getString("mesaj");
				model.addRow(row);
			}
			table.setModel(model);
			connection.close();
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}
	
	public boolean RezilereContract(String cnp) {
		boolean ok=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `dbclinica`.`angajati` WHERE (`cnp` = '" + cnp +"')");
			int resultSet=preparedStatement.executeUpdate();
			if(resultSet>0) {
				JOptionPane.showMessageDialog(null, "The contract is terminated!");
				ok=true;
			}else {
				JOptionPane.showMessageDialog(null, "An error has been appeared!Check if the CNP has been sent with the correct form!");
			}
			connection.close();
		}catch(Exception ex) {
			
		}
		return ok;
	}
	
	public void UpdateNumberEmployee(int idDep,int numarAngajati) {
		int numarAngajatiNou=numarAngajati;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			numarAngajatiNou--;
			PreparedStatement preparedStatement=connection.prepareStatement("Update departamente Set numar_angajati='" + numarAngajatiNou +"' where departament_id='"+ idDep +"'");
			int resultSet=preparedStatement.executeUpdate();
			connection.close();
		}catch(Exception exception){
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
	}
	
	public int NumberEmployeeDepartment(int id) {
		int nrEmployee=-1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT numar_angajati\r\n" + 
					" FROM dbclinica.departamente\r\n" + 
					" where departament_id='" + id +"'");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				nrEmployee=resultSet.getInt("numar_angajati");
			}
			connection.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		return nrEmployee;
	}
	
	
	public int getDepartmentID(String cnp) {
		int id=-1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT departament_id \r\n" + 
					"FROM dbclinica.angajati\r\n" + 
					"where CNP= '" + cnp +"'");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				id=resultSet.getInt("departament_id");
			}
			connection.close();
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		return id;
	}
	
	
	public void YesNOOptionRequest(String cnp) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclinica", "root",
					"root");
			PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `dbclinica`.`cerererezilierecontract` WHERE (`cnp` = '" + cnp +"')");
			int resultSet=preparedStatement.executeUpdate();
			if(resultSet>0) {
				JOptionPane.showMessageDialog(null, "Request response has been processed with success!");
			}else {
				JOptionPane.showMessageDialog(null, "Request rejected!");
			}
			connection.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error database connection");
		}
		
	}
	
}
