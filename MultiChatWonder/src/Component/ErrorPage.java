package Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Main.Window;
import Main.Main;
public class ErrorPage extends JPanel{
	
	public String ErrorText = "Error";
	public ErrorPage()
	{
		this.setBounds(100, 75, 800, 500);	
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setVisible(false);
	
		JPanel Container = new JPanel();
		Container.setBounds(0, 0, 800, 500);
		Container.setBackground(Color.BLACK);
		Container.setLayout(null);
		this.add(Container);
		
		JLabel lblError = new JLabel("Error");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 99));
		lblError.setForeground(Color.CYAN);
		lblError.setBounds(-11, 0, 788, 200);
		Container.add(lblError);
		
		JLabel lblPleaseClickThe = new JLabel("Please Click The Button Below To Fix The Problem");
		lblPleaseClickThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseClickThe.setForeground(Color.CYAN);
		lblPleaseClickThe.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPleaseClickThe.setBounds(-11, 105, 788, 286);
		Container.add(lblPleaseClickThe);
		
		JButton btnNewButton = new JButton("Restart");
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(231, 281, 308, 120);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Main.w.setVisible(false);
				Main.w.dispose();
				setVisible(false);
			    Main.main(null);
				
			}
		});
		Container.add(btnNewButton);
	}
	
}
