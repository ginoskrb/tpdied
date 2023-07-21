package gui.orden; //GenerarOrden

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class GenerarOrden extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarOrden frame = new GenerarOrden();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GenerarOrden() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        MaskFormatter dateFormatter = null;
		try {
			dateFormatter = new MaskFormatter("##/##/####");
			dateFormatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFormattedTextField fechaTextField = new JFormattedTextField(dateFormatter);
		fechaTextField.setBounds(80, 30, 103, 20);
	    fechaTextField.setFocusLostBehavior(JFormattedTextField.COMMIT); 
	    fechaTextField.setValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		contentPane.add(fechaTextField);
		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFecha.setBounds(10, 30, 60, 17);
		contentPane.add(lblFecha);
		
		
	}
}
