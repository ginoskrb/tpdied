package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class panelInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelInicio() {
		setBackground(new Color(255, 255, 255));
		setBounds(309,98,955,583);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Franco\\Desktop\\SH9q-yOc_400x400.jpg"));
		lblNewLabel.setBounds(276, 121, 389, 336);
		add(lblNewLabel);
	}
}
