package clinica.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class AppointmensSecretary {

	private JFrame frmViewAppointments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmensSecretary window = new AppointmensSecretary();
					window.frmViewAppointments.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppointmensSecretary() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewAppointments = new JFrame();
		frmViewAppointments.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Desktop\\Icons\\icons8-notification-16.png"));
		frmViewAppointments.setTitle("View Appointments");
		frmViewAppointments.setBounds(100, 100, 851, 742);
		frmViewAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewAppointments.getContentPane().setLayout(null);
	}

}
