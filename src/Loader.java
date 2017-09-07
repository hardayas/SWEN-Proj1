import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.SlickException;

import java.io.FileReader;


public class Loader {
	
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 * @throws SlickException
	 * @throws NumberFormatException
	 */
	public static Sprite[] loadSprites(String filename) throws NumberFormatException, SlickException {
		String file = filename;
		String line ;
		String splitter = ",";
		BufferedReader br = null;

		int i = 0;
		Sprite [] spriteArray = new Sprite [129];
		try {

            br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
            	   
                String[] buffer = line.split(splitter); //storing input in a buffer to then put into sprite array by parsing
                
                spriteArray[i] = new Sprite("res/"+buffer[0]+".png", Float.parseFloat(buffer[1]), Float.parseFloat(buffer[2]));
                i++;
            }
            //System.out.println(i);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return spriteArray;
	}
	
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile
	public static boolean restrictedArea(float x, float y) throws NumberFormatException, SlickException {
		int i = 0;
		float ep = (float) 1;
		Sprite[] array = Loader.loadSprites("res/levels/0.lvl");
		
		while(i <= 128) {
			//debug
			//System.out.println("dx= " + Math.abs(array[i].getX() - x)+ " dy= "+ Math.abs(array[i].getY() - y)+ " ix= "+ array[i].getX()+" x = "+x);
			
			//in order to compare floats set ep = 1, increment x or y a lil bit to see if the place you are moving into is legal. 
			if(array[i].getType().equals("res/wall.png") && Math.abs(array[i].getX() - x) < ep && Math.abs(array[i].getY() - y) < ep) {
		
				return true; //if collision with wall then return true
			}
			
			i++;
		}
		

		// Default to not blocked
		return false;
	}

}
