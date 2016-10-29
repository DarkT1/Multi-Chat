package Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class textMessage extends JPanel{
	
	public textMessage(String text, Image prof)
	{
		this.setBackground(Color.GRAY);
		this.setLayout(null);
        this.setPreferredSize(new Dimension(580,100));
   
        JLabel profilePic = new JLabel("");
        profilePic.setBounds(12, 13, 70, 70);
        profilePic.setIcon(new ImageIcon(prof));
        add(profilePic);
        
        JEditorPane textArea = new JEditorPane();
        textArea.setBounds(94, 13, 480, 90);
        textArea.setText(text);
        if(textArea.getPreferredSize().getHeight()>90)
        {
        	textArea.setSize(textArea.getPreferredSize());
        }
        if(textArea.getHeight()>this.getHeight())
        {
        	this.setPreferredSize(new Dimension(this.getWidth(),textArea.getHeight()+13+13));
        }
        add(textArea);
        this.validate();
        
	}
}
