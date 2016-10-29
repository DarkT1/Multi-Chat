package Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class Help extends JFrame{
	private JTextField textField;
	private JEditorPane licensePane;
	private HTMLEditorKit kit;
	private URL url;
	private URL styleSheetURL;
	private URL clientURL;
	private URL clientSheetURL;
	private URL serverURL;
	private URL serverSheetURL;
	private URL versionURL;
	private URL versionSheetURL;
	private StyleSheet styleSheet;
	public Help()
	{
		this.setVisible(true);
		this.setSize(980,695);
		this.setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		
		kit = new HTMLEditorKit();
		styleSheet = new StyleSheet();
		
		///////////////////////////////////////////////////////////////////////
		url = getClass().getResource("/licensing.htm");
		styleSheetURL = getClass().getResource("/licensingStyleSheet.css");
		clientURL=getClass().getResource("/clientHTML.htm");
		clientSheetURL=getClass().getResource("/clientStyle.css");
		serverURL=getClass().getResource("/serverHTML.htm");
		serverSheetURL=getClass().getResource("/serverStyle.css");
		versionURL=getClass().getResource("/versionHTML.htm");
		versionSheetURL=getClass().getResource("/versionStyle.css");
		//////////////////////////////////////////////////////////////////////////
		
		
		
		/////////////////Default page//////////////////////////////////
		styleSheet.importStyleSheet(styleSheetURL);
		kit.setStyleSheet(styleSheet);
		//////////////////////////////////////////////////
		
		
		
		
		JPanel Container = new JPanel();
		Container.setBackground(Color.BLACK);
		Container.setBounds(0, 0, 984, 668);
		this.add(Container);
		Container.setLayout(null);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setOpaque(true);
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setForeground(Color.CYAN);
		lblHelp.setBackground(Color.DARK_GRAY);
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 53));
		lblHelp.setBounds(0, 0, 964, 72);
		Container.add(lblHelp);
		
		JButton licensing = new JButton("Licensing");
		licensing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				styleSheet.importStyleSheet(styleSheetURL);
				kit.setStyleSheet(styleSheet);
				try {
					licensePane.setPage(url);
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		licensing.setForeground(Color.CYAN);
		licensing.setBackground(Color.GRAY);
		licensing.setFont(new Font("Tahoma", Font.BOLD, 18));
		licensing.setBounds(12, 85, 176, 25);
		Container.add(licensing);
								
								JButton clientBTN = new JButton("Client");
								clientBTN.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										styleSheet.importStyleSheet(clientSheetURL);
										kit.setStyleSheet(styleSheet);
										
										try {
											licensePane.setPage(clientURL);
											licensePane.validate();
										} catch (MalformedURLException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
									}
								});
								clientBTN.setForeground(Color.CYAN);
								clientBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
								clientBTN.setBackground(Color.GRAY);
								clientBTN.setBounds(12, 123, 176, 25);
								Container.add(clientBTN);
								
								JButton Server = new JButton("Server");
								Server.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										styleSheet.importStyleSheet(serverSheetURL);
										kit.setStyleSheet(styleSheet);
										
										try {
											licensePane.setPage(serverURL);
											licensePane.validate();
										} catch (MalformedURLException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
									}
								});
								Server.setForeground(Color.CYAN);
								Server.setFont(new Font("Tahoma", Font.BOLD, 18));
								Server.setBackground(Color.GRAY);
								Server.setBounds(12, 161, 176, 25);
								Container.add(Server);
								
								
								JScrollPane Textpane = new JScrollPane();
								Textpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
								Textpane.setBounds(233, 85, 719, 557);
								
								 licensePane = new JEditorPane();
								 licensePane.setDoubleBuffered(true);
								 licensePane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								licensePane.setBounds(233, 85, 719, 557);
								Textpane.setViewportView(licensePane);
								licensePane.setContentType("text/html");
								
										try {
											licensePane.setPage(url);
										} catch (MalformedURLException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
								licensePane.setEditorKit(kit);
								licensePane.setEditable(false);
								licensePane.addHyperlinkListener(new HyperlinkListener() {
								    public void hyperlinkUpdate(HyperlinkEvent e) {
								        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								            if(Desktop.isDesktopSupported()) {
								                try {
								                    Desktop.getDesktop().browse(e.getURL().toURI());
								                }
								                catch (IOException | URISyntaxException e1) {
								                    e1.printStackTrace();
								                }
								            }
								        }
								    }
								}
								);
								
							   
							   
								
								
								
								Container.add(Textpane);
								
								JButton btnVersion = new JButton("Version");
								btnVersion.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										styleSheet.importStyleSheet(versionSheetURL);
										kit.setStyleSheet(styleSheet);
										
										try {
											licensePane.setPage(versionURL);
											licensePane.validate();
										} catch (MalformedURLException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
									}
								});
								btnVersion.setForeground(Color.CYAN);
								btnVersion.setFont(new Font("Tahoma", Font.BOLD, 18));
								btnVersion.setBackground(Color.GRAY);
								btnVersion.setBounds(12, 199, 176, 25);
								Container.add(btnVersion);
								
			               
	}
	public void initLicense()
	{
		
	}
}
