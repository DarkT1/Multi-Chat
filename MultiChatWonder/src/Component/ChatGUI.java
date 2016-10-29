package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Main.Main;

import Network.client;
public class ChatGUI extends JPanel{

	public JTextArea ServerInfo;
	public JTextArea textArea;
	public Thread t;
	static client client;
	private JPanel MessageBoard;
	private JPanel Messages;
	JScrollPane scrollPane;
	public ChatGUI()
	{
		this.setDoubleBuffered(true);
		JTextField userInput;
		this.setVisible(false);
		this.setBounds(0, 0, 984, 764);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		 scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 87, 605, 579);
		this.add(scrollPane);
	
		MessageBoard = new JPanel();
		scrollPane.setViewportView(MessageBoard);
		MessageBoard.setLayout(new BorderLayout());
		
		Messages= new JPanel();
		MessageBoard.add(Messages, BorderLayout.PAGE_START);
		Messages.setLayout(new BoxLayout(Messages,BoxLayout.Y_AXIS));
		Messages.setAlignmentY(LEFT_ALIGNMENT);
		
		
		
		userInput = new JTextField();
		userInput.setBounds(10, 11, 810, 44);
		this.add(userInput);
		userInput.setColumns(10);
		userInput.requestFocus();
		userInput.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(userInput.getText().length() != 0)
					{
						client.sendMessage(userInput.getText().trim());
						userInput.setText(" ");
					}
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}			
				
	   });
		
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(SystemColor.activeCaption);
		btnSend.setBounds(830, 11, 144, 44);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userInput.getText().length() != 0)
				{
					client.sendMessage(userInput.getText().trim());
					userInput.setText(" ");
				}
			}
		});
		this.add(btnSend);
		
		JPanel panel = new JPanel();
		panel.setBounds(625, 66, 349, 600);
		this.add(panel);
		panel.setLayout(null);
		
		ServerInfo = new JTextArea();
		ServerInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ServerInfo.setEditable(false);
		ServerInfo.setBounds(0, 21, 349, 119);
		panel.add(ServerInfo);
		
		JLabel lblServerInformation = new JLabel("Server Information");
		lblServerInformation.setOpaque(true);
		lblServerInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServerInformation.setHorizontalTextPosition(SwingConstants.CENTER);
		lblServerInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerInformation.setForeground(Color.WHITE);
		lblServerInformation.setBackground(SystemColor.activeCaption);
		lblServerInformation.setBounds(0, 0, 349, 21);
		panel.add(lblServerInformation);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setOpaque(true);
		lblChat.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setForeground(Color.WHITE);
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChat.setBackground(SystemColor.activeCaption);
		lblChat.setBounds(10, 66, 605, 21);
		this.add(lblChat);

		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Help h = new Help();
			}
		});
		helpButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		helpButton.setForeground(Color.WHITE);
		helpButton.setBackground(SystemColor.activeCaption);
		helpButton.setBounds(240, 151, 97, 25);
		panel.add(helpButton);
		
	}
	public void setServerInfo(String s)
	{
		ServerInfo.setText(s);
	}
	public client getclientThread()
	{
		return client;
	}
	public void connect()
	{
		client = new client();
		t = new Thread(client);
		t.start();
	}
    
	public void log(String s)
	{
		Messages.add(new textMessage(s,Main.w.getVariables().getImage(Main.w.getVariables().getImageIndex())));
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		ChatGUI.this.validate();
	}
	
}
