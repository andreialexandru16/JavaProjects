package phonebook.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;

public class IntroBook {

	private JFrame frmPhoneBook;
	public JLabel lblNewLabel1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		IntroBook book = new IntroBook();
		book.frmPhoneBook.setVisible(true);
		book.frmPhoneBook.setLocationRelativeTo(null);
		try {
			for (int i = 0; i < 101; i++) {
				Thread.sleep(30);
				book.lblNewLabel1.setText(Integer.toString(i));
			}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MeniuBook window = new MeniuBook();
						window.frmPhoneBook.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			book.frmPhoneBook.setVisible(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public IntroBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhoneBook = new JFrame();
		frmPhoneBook.setUndecorated(true);
		frmPhoneBook.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmPhoneBook.getContentPane().setBackground(new Color(153, 204, 255));
		frmPhoneBook.setBounds(100, 100, 730, 438);
		frmPhoneBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhoneBook.getContentPane().setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(101);
		progressBar.setBorder(null);
		progressBar.setForeground(Color.WHITE);
		progressBar.setBackground(new Color(153, 204, 255));
		progressBar.setBounds(0, 0, 730, 37);
		frmPhoneBook.getContentPane().add(progressBar);

		lblNewLabel1 = new JLabel("");
		lblNewLabel1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(0, 46, 712, 71);
		frmPhoneBook.getContentPane().add(lblNewLabel1);

		JLabel lblNewLabel2 = new JLabel("Welcome to the PhooneBook");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel2.setBounds(0, 171, 712, 71);
		frmPhoneBook.getContentPane().add(lblNewLabel2);

		JLabel lblNewLabel = new JLabel("Design by:Marin Alexandru");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(543, 302, 187, 26);
		frmPhoneBook.getContentPane().add(lblNewLabel);

	}
}
