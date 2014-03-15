import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class VN implements KeyListener{
	private JPanel c;
	private JFrame frame;
	private JButton button;
	private int Height;
	private int Width;
	
	private String next;
	private boolean useKey; //boolean to decide whether or not activate KeyListener 
	private int scene;
	boolean progress;
	private JTextArea textBox;
	public painting panel;

	//Screen size variables necessary for image scaling capabilities for multiple resolution options

	//this is the divisor that will decide where the picture is on the screen default is 1/6.22 away from the right edge
	public String Dir = "images/Characters/";
	public String BGDir = "images/Backgrounds/MainScreen.png";

	private Girl char1,char2,char3;

	public VN(){gui();}
   
	public void gui(){
		frame = new JFrame("GalGame");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double w = screenSize.getWidth();
		double h = screenSize.getHeight();
		if ((h/w)< .7){
			h -= 32;
			w = h*1.429;
		} else if (h/w > .7){
			h = (w*.7)-32;
		}
		Height = (int)h;
		Width = (int)w;
		
		frame.setSize((int)w,(int)h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		c = new JPanel();
		panel = new painting();

		c.setLayout(null);
		panel.setLayout(null);
		panel.setBounds(0,0,Width-10,(int)(Height*.754));
		
		char1 = new Girl(3,2,1,2);
		char2 = new Girl(4,3,1,1);
		char3 = new Girl(3,4,3,3);

		next = "S1D1B0";
		scene = 1;

		textBox = new JTextArea();
			button = new JButton();
		ImageIcon menu = new ImageIcon(BGDir);
		panel.setBG(menu);
		panel.repaint();

		textBox.setBounds(0,(int)(Height*3/4),(int)(Width*.95),(int)(Height));
		textBox.setEditable(false);
		textBox.setRows(8);
		textBox.setLineWrap(true);
		textBox.setWrapStyleWord(true);

		button.setBounds((int)(Width*.95),(int)(Height*3/4),(int)(Width),(int)(Height));
		button.addKeyListener(new KeyListener(){
				public void keyReleased (KeyEvent e){
				if (useKey){
					//int id = e.getID();
					int keycode = e.getKeyCode();
					reader r = new reader();
					int chosen = 0; 
					int endChoice = 0;
					if (keycode == KeyEvent.VK_1){
					//look at findNthInstance below
					//basically, it goes through a string 
					//and returns the index of the nth time 
					//the specfied character appears in the string
					chosen = findNthInstance(next,"S",1); //finds first S since this is if the player picked choice 1
					endChoice = findNthInstance(next,"S",2); //finds second S (end of choice 1)
					//ImageIcon image1 = new ImageIcon(Dir);
					//ImageIcon image2 = new ImageIcon(BGDir);
					//panel.setChar(image1);
					//panel.setBG(image2);

					//c.repaint();
					}
					else if (keycode == KeyEvent.VK_2){
					chosen = findNthInstance(next,"S",2);
					endChoice = findNthInstance(next,"S",3);
					//ImageIcon image1 = new ImageIcon(Dir);
					//ImageIcon image2 = new ImageIcon(BGDir);
					//panel.setChar(image1);
					//panel.setBG(image2);
					
					//c.repaint();
					}
					else if (keycode == KeyEvent.VK_3){
					chosen = findNthInstance(next,"S",3);
					endChoice = findNthInstance(next,"S",4);
					//ImageIcon image1 = new ImageIcon(Dir);
					//ImageIcon image2 = new ImageIcon(BGDir);
					//panel.setChar(image1);
					//panel.setBG(image2);
					
					//c.repaint();
					}
					else if (keycode == KeyEvent.VK_4){
					chosen = findNthInstance(next,"S",4);
					endChoice = next.length();//since it's just the last choice
					//ImageIcon image1 = new ImageIcon(Dir);
					//ImageIcon image2 = new ImageIcon(BGDir);
					//panel.setChar(image1);
					//panel.setBG(image2);
					
					//c.repaint();
					}
					else {
					;
					}
				
					next = next.substring(chosen,endChoice); //substring between the two S's
					String[] info = r.readD("story.txt",next);
					next = info[1].replace("*",""); 
					// ^^ When writing out choices in the story.txt, I started each pointer to the next scene with a *
					//This was to differentiate from pointers from the actual title
					//So S1D3B0 (the actual scene) is different from *S1D3B0, which is where S1D2B0 points to
					//Thus, I have to remove the * now
					//Hopefully, that makes sense
					Dir = info[2].substring(findNthInstance(info[2],"~",1),findNthInstance(info[2],"~",2)).replace("~","");
					BGDir = info[2].substring(findNthInstance(info[2],"~",2),info[2].length()).replace("~","");
					ImageIcon image1 = new ImageIcon(Dir);
					ImageIcon image2 = new ImageIcon(BGDir);
					panel.setChar(image1);
					panel.setBG(image2);
					c.repaint();
					textBox.setText(info[0]);
					
					useKey = false;
				}
				}
			public void keyPressed (KeyEvent e){
				
			}
			public void keyTyped (KeyEvent e){
				
			}
			
			});
		button.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e){
				reader r = new reader();
				
				// textBox.setText(r.readD("story.txt","S"+scene+"D"+dialogue+"B"+branch));
				if (!useKey) {
				if (r.isDialogue("story.txt",next)){
					String[] info = r.readD("story.txt",next);
					next = info[1].replace("*","");
					Dir = info[2].substring(findNthInstance(info[2],"~",1),findNthInstance(info[2],"~",2)).replace("~","");
					BGDir = info[2].substring(findNthInstance(info[2],"~",2),info[2].length()).replace("~","");
					if (!info[3].equals("")){
						String[] splitpoints = info[3].split(";");
					for (int x = 0; x < splitpoints.length; x++){
						changePoints(splitpoints[x]);
					}
					}
					textBox.setText(info[0]);
					if (sceneChange(next)) {}
					ImageIcon image1 = new ImageIcon(Dir);
					ImageIcon image2 = new ImageIcon(BGDir);
					panel.setChar(image1);
					panel.setBG(image2);
					c.repaint();

				}
				else {
					String[] info = r.readC("story.txt",next);
					useKey = true;
					next = info[1].replace("*","");
					Dir = info[2].substring(findNthInstance(info[2],"~",1),findNthInstance(info[2],"~",2)).replace("~","");
					BGDir = info[2].substring(findNthInstance(info[2],"~",2),info[2].length()).replace("~","");
					textBox.setText(info[0]);

					ImageIcon image1 = new ImageIcon(Dir);
					ImageIcon image2 = new ImageIcon(BGDir);
					panel.setChar(image1);
					panel.setBG(image2);

					c.repaint();
				}
				}

			}
			}
			);
		c.add(textBox);
		c.add(button);
		
		frame.add(panel);
		frame.add(c);
		

		frame.setVisible(true);
	}

	public int findNthInstance (String str, String ch, int n){
	int index = 0;
	int counter = 0;
	while (index < str.length()){
		if (String.valueOf(str.charAt(index)).equals(ch)){
		counter ++;
		}
		if (counter == n){
		break;
		}
		index++;
	}
	return index;
	}

	public void changePoints (String str){
	String[] tally = str.split(",");
	if (tally[0].equals("1")){
		char1.addRespect(Integer.parseInt(tally[1]));
		char1.addAffection(Integer.parseInt(tally[2]));
		char1.addFamiliarity(Integer.parseInt(tally[3]));
		char1.addTrust(Integer.parseInt(tally[4]));
	}
	else if (tally[0].equals("2")){
		char2.addRespect(Integer.parseInt(tally[1]));
		char2.addAffection(Integer.parseInt(tally[2]));
		char2.addFamiliarity(Integer.parseInt(tally[3]));
		char2.addTrust(Integer.parseInt(tally[4]));
	}
	else if (tally[0].equals("3")){
		char3.addRespect(Integer.parseInt(tally[1]));
		char3.addAffection(Integer.parseInt(tally[2]));
		char3.addFamiliarity(Integer.parseInt(tally[3]));
		char3.addTrust(Integer.parseInt(tally[4]));
	}
	}

	public boolean sceneChange (String loc){
	if (scene < loc.charAt(1) - '0'){
		scene++;
		return true;
	}
	else {
		return false;
	}
	}

	public void keyReleased (KeyEvent e) {}
	public void keyPressed (KeyEvent e) {}
	public void keyTyped (KeyEvent e) {}

	public static void main(String[]args){new VN();}
}
