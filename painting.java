import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class painting extends JPanel{

    private Image image1;
    private Image image2;
    BufferedImage icon;


    public void setBG(ImageIcon i){
	image2 = i.getImage();
    }
    public void setChar(ImageIcon i) {
        image1 = i.getImage();
    }
    public void update(Graphics g){
	paintComponent(g);
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	//PAINTS THE BACKGROUND 
	g.drawImage(image2, 0, 0, getWidth(),(int)(getWidth()*.533), null); 
	//PAINTS THE SCALED CHARACTER 
	g.drawImage(image1, 
		    (int)(getWidth()/6.22), 
		    (int)(getHeight()*.246), 
		    (int)(getHeight()*.754*.33),  
		    (int)(getHeight()*.754), 
		    null); 
	repaint();
    }
}
