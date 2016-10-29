package GlobalVars;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class vars {
	
	int port;
	String ServerIP;
	String username;
	ArrayList<Image> pics;
	int profilePicIndex;
	public vars()
	{
		port = 0;
		ServerIP = "";
		username = "";
		profilePicIndex=0;
		pics = new ArrayList<Image>();
		scanAllPics();
	}
	public void scanAllPics()
	{
		Image img1 = ((ImageIcon) new ImageIcon(getClass().getResource("/defaultProf.png"))).getImage();
		pics.add(img1);
		
		
		for(int x = 0; x<pics.size();x++)
		{
			pics.get(x).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		}
		
	}
	
	
	
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String s)
	{
		username = s;
	}
	public int getPort()
	{
		return port;
	}
	public void setPort(int s)
	{
		port = s;
	}
	public String getServerIP()
	{
		return ServerIP;
	}
	public void setServerIP(String s)
	{
		ServerIP = s;
	}
	public Image getImage(int index)
	{
		return pics.get(index);
	}
	public int getImageIndex()
	{
		return profilePicIndex;
	}
	public String ServerToString()
	{
		String s = "";
		s+=" Username: " + username+"\n";
		s+=" Port: " + port+"\n";
		s+=" Server IP Address: " + ServerIP+"\n";
		try {
			s+=" My IP Address: " + InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
}
