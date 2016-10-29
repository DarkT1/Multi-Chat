package Network;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Main.Main;

public class server implements Runnable{
 
	ArrayList<clientMaintenance> clients = new ArrayList <clientMaintenance>();
	DefaultListModel<String> model = new DefaultListModel<String>();
	ArrayList<String> users = new ArrayList<String>();
	ServerSocket serverSocket;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	int port = Main.w.getVariables().getPort();
	String address = Main.w.getVariables().getServerIP();
	String username = Main.w.getVariables().getUsername();
	
	
	
	int y =0; /// Creates ID 
	
	volatile boolean shutdown = false;
	
	public server()
	{
		log("Server>/Initialized");
		log("Waiting for Client to Connect");
		Main.w.getServer().getUserList().setModel(model);
	}
	public void run() 
	{    
	    if(!shutdown)
	    {
					try {
						
							serverSocket = new ServerSocket(port);
						
						while(!shutdown)
							{
								socket = serverSocket.accept();	
								clients.add(new clientMaintenance(socket, y));
								clients.get(clients.size()-1).start();
			                    y++;
			               
							}
		
					} catch (IOException e) {
						
						try {
							socket.close();
							shutdown();
							errorControl();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							shutdown();
							errorControl();
						}
						errorControl();
					}finally{
						
							    try{
							        if (socket!=null){
								        socket.close();
								        shutdown();
								       errorControl();
							        }
				
							        }
							    catch(IOException ie)
							    {
							    	shutdown();
							    	errorControl();
							    }
					}
	    }
	}
	public void log(String s)	
	{
		Main.w.getServer().log(s);
	}
	public void sendMessage(String s)
	{
		for(int x =0; x< clients.size();x++)
		{
			clients.get(x).getPrintWriter().println("(ADMIN) " +username+ ": "+s);
		}
	}
	public int getClientSize()
	{
		return clients.size();
	}
	public ArrayList<clientMaintenance> getClients()
	{
		return clients;
	}
	public void errorControl()
	{
		Main.w.getStartFrame().setVisible(false);
		Main.w.getErrorPage().setVisible(true);
	}
	public void shutDownAClient(String username)
	{
	     for(int x =0; x< clients.size();x++)
	     {
	    	 if(username.equals(clients.get(x).getUsername()))
	    	 {
	    		 try {
					clients.get(x).getSocketConnection().close();
					clients.get(x).shutdown();
				} catch (IOException e) {
					shutdown();
					errorControl();
				}
	    		 removeFromUserList(username);
	    		 clients.remove(x);
	    		
	    	 }
	     }
	}
	public void addToUserList(String username)
	{
		users.add(username);
		model.addElement(username);
	}
	public void removeFromUserList(String username)
	{
		for(int x =0; x< users.size();x++)
		{
			if(users.get(x).equals(username))
			{
				users.remove(x);
			}
		}
		for(int x =0; x<model.size(); x++)
		{
			if(model.getElementAt(x).equals(username))
			{
				model.remove(x);
			}
		}
	}
	public DefaultListModel<String> getModel()
	{
		return model;
	}
	public ArrayList<String> getUserArrayList()
	{
		return users;
	}
	public void shutdown()
	{
		shutdown = true;
	}
}
class clientMaintenance extends Thread
{
	private Socket socket;
	private  PrintWriter out;
	private BufferedReader in;
	private int port = Main.w.getVariables().getPort();
	private String address;
	private String username;
	private int clientID;
	
	volatile boolean shutdown = false;
	
	public clientMaintenance(Socket socket, int clientID)
	{
		this.socket = socket;
		this.clientID = clientID;
		
	}
	public void run()
	{
		if(!shutdown)
		{
				 try {
					 in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
					 out = new PrintWriter(socket.getOutputStream(), true);
					 username = in.readLine(); //Must be in this order. Username then address is sent when client connected
					 address = in.readLine();
					 username = userNameTest(username);
					 try{
							Thread.sleep(10);
						}
						catch(InterruptedException e)
						{
							socket.close();
							Main.w.getServer().getServerThread().shutDownAClient(username);
							shutdown();
						}
					 log(username + " has connected from " + address); //logging
					 Main.w.getServer().getServerThread().addToUserList(username);
				 }
				 catch(IOException e)
				 {
					 try {
						socket.close();
						Main.w.getServer().getServerThread().shutDownAClient(username);
						shutdown();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						Main.w.getServer().getServerThread().shutDownAClient(username);
						shutdown();
						errorControl();
					}
					 
				 }
		}
				 
		 while(!shutdown)
		 {
			try {
				
				String s = in.readLine(); //receive message from client
				log(s);//stores in server
				sendMessageToOtherClients(s);//sends to other clients
				try{
					Thread.sleep(10);
				}
				catch(InterruptedException e)
				{
					socket.close();
					Main.w.getServer().getServerThread().shutDownAClient(username);
					shutdown();
				}
			} catch (IOException e) {
				try {
					socket.close();
					Main.w.getServer().getServerThread().shutDownAClient(username);
					shutdown();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Main.w.getServer().getServerThread().shutDownAClient(username);
					shutdown();
					errorControl();
				}
				
			}
		 }
		 

		 
	}
	public void sendMessageToOtherClients(String s)
	{
		int client_Size = Main.w.getServer().getServerThread().getClientSize();
	    ArrayList<clientMaintenance> clients = Main.w.getServer().getServerThread().getClients();
	    
		for(int x =0; x< client_Size; x++)
		{
		
			if(clients.get(x).getID() != clientID)
				clients.get(x).getPrintWriter().println(s);
		}
	
	}
	
	public int getID()
	{
		return clientID;
	}
	public String getUsername()
	{
		return username;
	}
    public String userNameTest(String s)
    {
    	int count = 1; 
    	s.trim(); 
    	for(int x =0; x< Main.w.getServer().getServerThread().getUserArrayList().size();x++)
    	{
	    	if(Main.w.getServer().getServerThread().getUserArrayList().get(x).equals(s))
	    	{
	    		count = count+1;
	    	}
    	}
    	if(count == 1)
    	{
    		return s;
    	}	
    	else
    	{
    	     return s + " "+"("+ count +")";
    	}
    	
    }
	public void log(String s)	
	{
		Main.w.getServer().log(s);
	}
	public Socket getSocketConnection()
	{
		return socket;
	}
	public PrintWriter getPrintWriter()
	{
		return out;
	}
	public void errorControl()
	{
		Main.w.getStartFrame().setVisible(false);
		Main.w.getErrorPage().setVisible(true);
		Main.w.getServer().setVisible(false);
	}
	public void shutdown()
	{
		shutdown = true;
	}
	

}