import java.util.Scanner;  
import java.io.File;
import java.io.IOException;
public class reader{
    public String[] readD(String file, String loc){
	File story = new File(file);
	String dialogue = "";
	Boolean stop = false;
	String[]ans = new String[4];
	try {
	Scanner sc = new Scanner(story);

        while(sc.hasNextLine()){
	    if (sc.nextLine().equals(loc)){
		while(sc.hasNext())
		    if (sc.next().equals("#")){
			while (!stop){		   
			    String line = sc.nextLine();
			    if (line.equals("#")) {
				stop = true;
			    }else{
			    dialogue += line + "\n";
			}
		    }
		}
	    }
	}
	//	String destination = readNext(sc);

	Scanner scan = new Scanner(story);
	String next = "";
	stop = false;
	while (scan.hasNextLine()){
	    if (scan.nextLine().equals(loc)){
		while (scan.hasNext()){
		    if (scan.nextLine().equals("%")){
			while (!stop){
			    String line = scan.nextLine();
			    if (line.equals("%")){
				stop = true;
			    }else{
				next += line;
			    }
			}
			
		    }
		}
	    }
	}

	Scanner scanpic = new Scanner(story);
	String nextimg = "";
	stop = false;
	while (scanpic.hasNextLine()){
	    if (scanpic.nextLine().equals(loc)){
		while (scanpic.hasNext()){
		    if (scanpic.nextLine().equals("@")){
			while (!stop){
			    String line = scanpic.nextLine();
			    if (line.equals("@")){
				stop = true;
			    }else{
				nextimg += line;
			    }
			}
			
		    }
		}
	    }
	}

	Scanner scanpts = new Scanner(story);
	String points = "";
	stop = false;

        while(scanpts.hasNextLine()){
	    if (scanpts.nextLine().equals(loc)){
		while(scanpts.hasNext())
		    if (scanpts.next().equals("+")){
			while (!stop){		   
			    String line = scanpts.nextLine();
			    if (line.equals("+")) {
				stop = true;
			    }else{
			    points += line;
			}
		    }
		}
	    }
	}

	ans[0] = dialogue;
	ans[1] = next;
	ans[2] = nextimg;
	ans[3] = points;
	}catch(Exception e){
	    e.printStackTrace();
	}

	return ans;
	//return dialogue;
    }
    public String[] readC(String file, String loc){
	File story = new File(file);
	String dialogue = "";
	Boolean stop = false;
	String[]ans = new String[3];
	try {
	Scanner sc = new Scanner(story);

        while(sc.hasNextLine()){
	    if (sc.nextLine().equals(loc)){
		while(sc.hasNext())
		    if (sc.next().equals("$")){
			while (!stop){		   
			    String line = sc.nextLine();
			    if (line.equals("$")) {
				stop = true;
			    }else{
				dialogue += line + "\n";
			    }
			}
		    }
	    }
	}
	Scanner scan = new Scanner(story);
	String next = "";
	stop = false;
	while (scan.hasNextLine()){
	    if (scan.nextLine().equals(loc)){
		while (scan.hasNext()){
		    if (scan.nextLine().equals("%")){
			while (!stop){
			    String line = scan.nextLine();
			    if (line.equals("%")){
				stop = true;
			    }else{
				next += line;
			    }
			}
			
		    }
		}
	    }
	}

	Scanner scanpic = new Scanner(story);
	String nextimg = "";
	stop = false;
	while (scanpic.hasNextLine()){
	    if (scanpic.nextLine().equals(loc)){
		while (scanpic.hasNext()){
		    if (scanpic.nextLine().equals("@")){
			while (!stop){
			    String line = scanpic.nextLine();
			    if (line.equals("@")){
				stop = true;
			    }else{
				nextimg += line;
			    }
			}
			
		    }
		}
	    }
	}

	ans[0] = dialogue;
	ans[1] = next;
	ans[2] = nextimg;
	}catch(Exception e){
	    e.printStackTrace();
	}
	return ans;
    }

    public String readNext(Scanner sc){
	
	String next = "";
	boolean stop = false;
	while (sc.hasNextLine()){
	    if (sc.nextLine().equals("%")){
		while (!stop){
		    String line = sc.nextLine();
		    if (line.equals("%")){
			stop = true;
		    }else{
			next += line;
		    }
		}
		
	    }
	}
	return next;
    }

    public boolean isDialogue (String file, String loc){
	File story = new File(file);
	Boolean ans = false;
	try {
	    Scanner sc = new Scanner(story);

	    while(sc.hasNextLine()){
		if (sc.nextLine().equals(loc)){
		    while(sc.hasNext()){
			String line = sc.next();
			if (line.equals("#")){
			    return true;
			}
			else if (line.equals("$")){
			    return false;
			}
		    }
		}
	    }
	}
	catch (Exception e){
	    e.printStackTrace();
	}
	return false;
    }
    public static void main(String[]args){
        reader a = new reader();
	
	if (a.isDialogue("story.txt","S1D1B0")){
	    System.out.println(a.readC("story.txt","S1D3B0")[0]);
	    System.out.println(a.readC("story.txt","S1D3B0")[1]);
	    System.out.println(a.readC("story.txt","S1D3B0")[2]);
	}
	
	System.out.println(a.readC("story.txt","S1D7B0")[0]);
	System.out.println(a.readC("story.txt","S1D7B0")[1]);	
	
    }
	
}
