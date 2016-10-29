package Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Main.Main;


public class client implements Runnable
{
	
	Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	int port = Main.w.getVariables().getPort();
	private String address = Main.w.getVariables().getServerIP();
	private String username = Main.w.getVariables().getUsername();
	
	volatile boolean shutdown = false;
	
	public client()
	{		
		log("Client initialized");
		
	}
	public void run() {
		if(!shutdown)
		{
					try {
						
						socket = new Socket(address, port);	   
					} catch (UnknownHostException e) {
						e.printStackTrace();
						closeS();
						shutdown();
						errorControl();
					} catch (IOException e) {
						e.printStackTrace();
						closeS();
						shutdown();
						errorControl();
						
					}catch(Exception e)
					{
			
						e.printStackTrace();
						closeS();
						shutdown();
						errorControl();
					}
					
					 try {
						 in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
						 out = new PrintWriter(socket.getOutputStream(), true);
						 try{
								Thread.sleep(10);
							}
							catch(InterruptedException e)
							{
								socket.close();
								Main.w.getServer().getServerThread().shutDownAClient(username);
								shutdown();
							}
						 out.println(username);
						 out.println(address);
						} catch (IOException e) {
							closeS();
							shutdown();
							errorControl();
						}catch(Exception e)
						{
							closeS();
							shutdown();
							errorControl();
						}
	}
		 while(!shutdown)
		 {
			 try {
				String s = in.readLine();
				log(s);
				try{
					Thread.sleep(10);
				}
				catch(InterruptedException e)
				{
					closeS();
					shutdown();
					errorControl();
				}
				if(s.equals(null))
				{
					closeS();
					shutdown();
					errorControl();
				}
			} catch (IOException e) {
				closeS();
				shutdown();
				errorControl();
			}catch(Exception e)
				{
				closeS();
				shutdown();
				errorControl();
			}
	
		 }
		 
		 
	     
	}
	public void log(String s)	
	{
		Main.w.getChat().log(s);
	}
	public void sendMessage(String s)
	{
		log(username+ ": "+s);
	    out.println(username+": " + s);
	}
	public void closeS()
	{
		
		if(socket!=null)
			try {
				socket.close();
				shutdown();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorControl();
				shutdown();
			}
	}
	public void shutdown()
	{
		shutdown = true;
	}
	public void errorControl()
	{
		
		Main.w.getStartFrame().setVisible(false);
		Main.w.getChat().setVisible(false);
		Main.w.getErrorPage().setVisible(true);
	}
}
	
	
