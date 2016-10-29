package Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Main;

public class initializingChatUI extends JPanel{
	
	private String serverAddress;
	private int port;
	private String name;
	slideShow show;
	private ArrayList<Image> Images = new ArrayList<Image>();
	private JPanel cover;
	public initializingChatUI()
	{
		final JTextField UsernameClient;
		final JTextField PortNumberClient;
		final JTextField ServerAddressClient;
		final JTextField username;
		final JTextField portNum1;
		final JTextField InetAddressField;
		JButton joinServer;
		
		this.setBounds(100, 75, 800, 500);	
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		Image Logo = ((ImageIcon) new ImageIcon(getClass().getResource("/TitlePage.png"))).getImage();
		Image Logo2 = ((ImageIcon) new ImageIcon(getClass().getResource("/Credits.png"))).getImage();
		Images.add(Logo);
		Images.add(Logo2);
		final JPanel Server = new JPanel();
		Server.setVisible(false);
		
		cover = new JPanel();
		cover.setBackground(Color.BLACK);
		cover.setBounds(429, 196, 288, 99);
		add(cover);
		Server.setBounds(10, 320, 765, 140);
		Server.setEnabled(false);
		Server.setLayout(null);
		Server.setBackground(Color.BLACK);
		Server.setForeground(Color.BLACK);
		this.add(Server);
		
		final JPanel StartGame = new JPanel();
		StartGame.setBounds(10, 320, 765, 140);
		this.add(StartGame);
		StartGame.setBackground(Color.BLACK);
		StartGame.setLayout(null);
	
		
		JButton btnHosting = new JButton("Hosting");
		btnHosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartGame.setVisible(false);
				Server.setVisible(true);
			}
		});
		btnHosting.setForeground(Color.CYAN);
		btnHosting.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnHosting.setBackground(Color.DARK_GRAY);
		btnHosting.setBounds(484, 11, 242, 118);
		StartGame.add(btnHosting);
		

		username = new JTextField();
		username.setBounds(149, 21, 170, 23);
		username.setForeground(Color.BLACK);
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setColumns(10);
		Server.add(username);
		
		JLabel label = new JLabel("Username: ");
		label.setBounds(62, 21, 78, 17);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.CYAN);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		Server.add(label);
		
		JLabel label_1 = new JLabel("Port:");
		label_1.setBounds(79, 55, 78, 17);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.CYAN);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		Server.add(label_1);
		
		portNum1 = new JTextField();
		portNum1.setBounds(149, 55, 170, 23);
		portNum1.setForeground(Color.BLACK);
		portNum1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		portNum1.setColumns(10);
		Server.add(portNum1);
		
		JLabel label_2 = new JLabel("Server Address: ");
		label_2.setBounds(22, 89, 125, 17);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.CYAN);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		Server.add(label_2);
		
		InetAddressField = new JTextField();
		InetAddressField.setBounds(149, 87, 170, 23);
		InetAddressField.setForeground(Color.BLACK);
		InetAddressField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		InetAddressField.setColumns(10);
		
		try {
			InetAddressField.setText(""+
					InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Server.add(InetAddressField);
		
		joinServer = new JButton("Launch"); //creating server
		joinServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(portNum1.getText().length()==0 ||username.getText().length()==0 || Pattern.matches("^[0-9]+$",portNum1.getText()) == false||validIP(InetAddressField.getText())==false)
				{
					Main.w.getStartFrame().setVisible(false);
					Main.w.getErrorPage().setVisible(true);
				
				}
				else
				{
					try
					{
						Main.w.getVariables().setUsername(username.getText());
						try
						{
							Main.w.getVariables().setPort(Integer.parseInt(portNum1.getText()));
						}
						catch(NumberFormatException exception)
						{

							Main.w.getStartFrame().setVisible(false);
							Main.w.getErrorPage().setVisible(true);
						}
						Main.w.getVariables().setServerIP(InetAddressField.getText());
						Main.w.getStartFrame().setVisible(false);
						Main.w.getErrorPage().setVisible(false);
						
					
					    Main.w.getServer().setVisible(true);
					    Main.w.getServer().setServerInfo(Main.w.getVariables().ServerToString());
					    Main.w.getServer().initializeServer();
					}catch(Exception exception)
					{
					
						Main.w.getStartFrame().setVisible(false);
						Main.w.getErrorPage().setVisible(true);
						
					}
				
				}
				
			}
		});
		joinServer.setBounds(473, 34, 215, 68);
		joinServer.setForeground(Color.CYAN);
		joinServer.setFont(new Font("Tahoma", Font.BOLD, 14));
		joinServer.setBackground(Color.DARK_GRAY);
		Server.add(joinServer);
		
		JLabel logo = new JLabel("");
		logo.setBounds(0, 0, 800, 310);
		this.add(logo);
		show = new slideShow(Images,logo,cover);
		Thread t = new Thread(show);
		t.start();
		//////////////////////////////////////
		
		
		
		
		
		
		
		
		
		//////////////////////////////////////
		
		
		
		
		final JPanel AClient = new JPanel();
		AClient.setVisible(false);
		AClient.setBounds(10, 320, 765, 140);
		AClient.setEnabled(false);
		AClient.setBackground(Color.BLACK);
		AClient.setForeground(Color.BLACK);
		this.add(AClient);
		AClient.setLayout(null);
		
		UsernameClient = new JTextField();
		UsernameClient.setBounds(129, 25, 170, 23);
		AClient.add(UsernameClient);
		UsernameClient.setForeground(Color.BLACK);
		UsernameClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsernameClient.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(51, 28, 78, 17);
		AClient.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.CYAN);
		
		JLabel PortNumberLbl = new JLabel("Port:");
		PortNumberLbl.setBounds(68, 62, 78, 17);
		AClient.add(PortNumberLbl);
		PortNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
		PortNumberLbl.setForeground(Color.CYAN);
		PortNumberLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		PortNumberClient = new JTextField();
		PortNumberClient.setBounds(129, 59, 170, 23);
		AClient.add(PortNumberClient);
		PortNumberClient.setForeground(Color.BLACK);
		PortNumberClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PortNumberClient.setColumns(10);
		
		JLabel IP = new JLabel("Server Address: ");
		IP.setHorizontalAlignment(SwingConstants.CENTER);
		IP.setForeground(Color.CYAN);
		IP.setFont(new Font("Tahoma", Font.BOLD, 14));
		IP.setBounds(10, 96, 125, 17);
		AClient.add(IP);
		
		ServerAddressClient = new JTextField();
		ServerAddressClient.setForeground(Color.BLACK);
		ServerAddressClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ServerAddressClient.setColumns(10);
		ServerAddressClient.setBounds(129, 93, 170, 23);
		
		AClient.add(ServerAddressClient);
		
		
		
		JButton btnJoin = new JButton("Join"); //client Joining server
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(PortNumberClient.getText().length()==0 ||UsernameClient.getText().length()==0 ||ServerAddressClient.getText().length()==0||Pattern.matches("^[0-9]+$",PortNumberClient.getText()) == false||validIP(ServerAddressClient.getText())==false)
				{
					Main.w.getStartFrame().setVisible(false);
					Main.w.getErrorPage().setVisible(true);
					
				}
				else
				{
					try
					{
						Main.w.getVariables().setUsername(UsernameClient.getText());
						try
						{
							Main.w.getVariables().setPort(Integer.parseInt(PortNumberClient.getText()));
						}
						catch(NumberFormatException exception)
						{

							Main.w.getStartFrame().setVisible(false);
							Main.w.getErrorPage().setVisible(true);
						}
						Main.w.getVariables().setServerIP(ServerAddressClient.getText());
						Main.w.getStartFrame().setVisible(false);
						Main.w.getErrorPage().setVisible(false);
						
						
						Main.w.getChat().setVisible(true);
						Main.w.getChat().connect();
						Main.w.getChat().setServerInfo(Main.w.getVariables().ServerToString());
					}catch(Exception exception)
					{
					
						Main.w.getStartFrame().setVisible(false);
						Main.w.getErrorPage().setVisible(true);
					
					}
				
				}
				
			}
		});
		btnJoin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnJoin.setBackground(Color.DARK_GRAY);
		btnJoin.setForeground(Color.CYAN);
		btnJoin.setBounds(419, 36, 215, 68);
		AClient.add(btnJoin);
		
		
		JButton btnNewButton = new JButton("Join");
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartGame.setVisible(false);
				AClient.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(35, 11, 242, 118);
		StartGame.add(btnNewButton);
		
		JButton Help = new JButton("Help");
		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help h = new Help();
			}
		});
		Help.setForeground(Color.CYAN);
		Help.setFont(new Font("Tahoma", Font.BOLD, 25));
		Help.setBackground(Color.DARK_GRAY);
		Help.setBounds(313, 11, 136, 118);
		StartGame.add(Help);
	}
	
	public static boolean validIP (String ip) {
	    try {
	        if ( ip == null || ip.isEmpty() ) {
	            return false;
	        }

	        String[] parts = ip.split( "\\." );
	        if ( parts.length != 4 ) {
	            return false;
	        }

	        for ( String s : parts ) {
	            int i = Integer.parseInt( s );
	            if ( (i < 0) || (i > 255) ) {
	                return false;
	            }
	        }
	        if ( ip.endsWith(".") ) {
	            return false;
	        }

	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
}
class slideShow implements Runnable
{
	
	volatile boolean run;
	ArrayList<Image> list;
	JLabel label;
	JPanel cover;
	public slideShow(ArrayList<Image> list, JLabel label,JPanel cover)
	{
		run=true;
		this.list=list;
		this.label=label;
		this.cover=cover;
	}

	
	public void run() {
		
		while(run)
		{
			for(int x=0; x<list.size(); x++)
			{
				label.setIcon(new ImageIcon(list.get(x)));
				label.validate();
				if(x==1)
				{
					cover.setVisible(true);
				}
				else
				{
					cover.setVisible(false);
				}
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					run=false;
				}
			}
		}
	}
	public void shutdown()
	{
		run=false;
	}
}





