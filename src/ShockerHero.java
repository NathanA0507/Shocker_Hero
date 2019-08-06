import java.awt.image.BufferedImage; 
import java.awt.*;

import arduino.*;

public class ShockerHero
{
	public static void main(String[] args)
	{
		Arduino a = new Arduino("COM4");
		
		BufferedImage img;
		boolean isBlack = true;
		boolean wasBlack = true;
		a.openConnection();
		try { 
            Robot robot = new Robot();
            //Really not the best implementation but I only had a day so pls give me a break lol
            //keeps running until the program is manually terminated
			while(true){
				img = robot.createScreenCapture(new Rectangle(1205, 680, 10, 10));			
				
				
				Color c1 = new Color(img.getRGB(1,1));
				
				isBlack = isBlack(c1);
				if (isBlack && !wasBlack) {
					a.serialWrite("1"); //Turns the taser on
					Thread.sleep(50); //Has a slight delay so the taser stays on for a decent amount of time
					a.serialWrite("2"); //Turns it off
				}
				wasBlack = isBlack; //wasBlack is imporant because it prevents the user from constantly getting tased if they are done with it.
				
			}
        } catch(Exception e) { 
        	System.out.println("there's an exception somewhere");
        } 	
	}	
	
	//Checks if the color in the space is black
	public static boolean isBlack(Color c1)
	{
		if (c1.getGreen() < 20 && c1.getBlue() < 20 && c1.getRed() < 20)
			return true;
		else
			return false;
	}
}