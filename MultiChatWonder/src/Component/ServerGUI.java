package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Main.Main;
import Network.server;

public class ServerGUI extends JPanel{

	public JTextArea ServerInfo;
	public JTextArea textArea;
	JTextField kickField;
	Thread t;
	server server;
	private JPanel MessageBoard;
	private JPanel Messages;
	private JList userList; 
	JScrollPane scrollPane;
	
	public ServerGUI()
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
						server.sendMessage(userInput.getText().trim());
						userInput.setText(" ");
					}
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
			
				
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
					server.sendMessage(userInput.getText().trim());
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
		
		JLabel lblChat = new JLabel("Server Log");
		lblChat.setOpaque(true);
		lblChat.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setForeground(Color.WHITE);
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChat.setBackground(SystemColor.activeCaption);
		lblChat.setBounds(10, 66, 605, 21);
		this.add(lblChat);
		
        
		userList = new JList();
		userList.setFont(new Font("Tahoma", Font.BOLD, 16));
		userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
	    
		   
		userList.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
			
				
				  JList list = (JList) e.getSource();
		          DefaultListModel data = (DefaultListModel) list.getModel();
		        
		          int[]  selected= list.getSelectedIndices();
		          
		          kickField.setText("");
		          
		          for(int x =0; x< selected.length; x++)
		          {
		        	  kickField.setText(kickField.getText()+data.getElementAt(selected[x])+ ", ");
		          }

			}

		});   
		   
		JScrollPane userListScroller = new JScrollPane(userList);
		userListScroller.setBounds(10, 183, 196, 404);
		
		JLabel userTitle = new JLabel("Users");
		userTitle.setOpaque(true);
		userTitle.setBackground(SystemColor.activeCaption);
		userTitle.setForeground(Color.WHITE);
		userTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		userTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		userTitle.setHorizontalAlignment(SwingConstants.CENTER);
		userListScroller.setColumnHeaderView(userTitle);
		panel.add(userListScroller);
		
		kickField = new JTextField();
		kickField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kickField.setBounds(10, 153, 220, 22);
		panel.add(kickField);
		kickField.setColumns(10);
		kickField.setEditable(false);
		
		JButton btnKick = new JButton("Kick");
		btnKick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  while(userList.getSelectedIndex() != -1)
                  {
               	     getServerThread().shutDownAClient((String)(userList.getSelectedValue()));
               	     kickField.setText("");
                  }
			}
		});
		btnKick.setBackground(SystemColor.activeCaption);
		btnKick.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKick.setForeground(Color.WHITE);
		btnKick.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKick.setBounds(240, 151, 97, 25);
		panel.add(btnKick);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Help h = new Help();
			}
		});
		helpButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		helpButton.setForeground(Color.WHITE);
		helpButton.setBackground(SystemColor.activeCaption);
		helpButton.setBounds(240, 183, 97, 25);
		panel.add(helpButton);
		
	}
    public JList getUserList(){
    	return userList;
    }
	public void initializeServer()
	{
		server = new server();
		t = new Thread(server);
		t.start();
	}
	public server getServerThread()
	{
		return server;
	}
	public void setServerInfo(String s)
	{
		ServerInfo.setText(s);
	}
	public void log(String s)
	{
		Messages.add(new textMessage(s,Main.w.getVariables().getImage(Main.w.getVariables().getImageIndex())));
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		ServerGUI.this.validate();
	}
}
