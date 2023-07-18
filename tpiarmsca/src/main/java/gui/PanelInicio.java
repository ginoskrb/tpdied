package gui;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelInicio() {
		setBackground(new Color(255, 255, 255));
		setBounds(309,98,955,583);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(276, 121, 389, 336);
		add(lblNewLabel);
	}
}
