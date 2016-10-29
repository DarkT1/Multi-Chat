package Main;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Component.ChatGUI;
import Component.ErrorPage;
import Component.Help;
import Component.ServerGUI;
import Component.initializingChatUI;
import GlobalVars.vars;

public class Window extends JFrame{

	//Frame Var;
	private static int screenWidth= 1000;
	private static int screenHeight = 700;
	
	
	//Game Var;
	vars variables;
	//Components
	initializingChatUI startFrame;
	ChatGUI chat;
	ErrorPage ePage;
	ServerGUI gui1;
	
	
	public Window()
	{

		//Game Var;
		 variables = new vars();
		//Components
		 ePage = new ErrorPage();
		startFrame = new initializingChatUI();
		chat = new ChatGUI();
		gui1 = new ServerGUI();
		initialize();
		
	}
	void initialize()
	{

		this.setSize(screenWidth,screenHeight);
		this.setAlwaysOnTop(true);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(ePage);
		this.add(startFrame);
		this.add(chat);
		this.add(gui1);
		this.getContentPane().setBackground(Color.BLACK);
	
	}

	public initializingChatUI getStartFrame()
	{
		return startFrame;
	}
	
	public ErrorPage getErrorPage()
	{
		return ePage;
	}
	public vars getVariables()
	{
		return variables;
	}
	public ChatGUI getChat()
	{
		return chat;
	}
	public void setChat(ChatGUI s)
	{
		chat = s;
	}
	
	public ServerGUI getServer()
	{
		return gui1;
	}
	public void setServer(ServerGUI s)
	{
		gui1 = s;
	}
 }

