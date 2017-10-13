
	import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
	private static String[][] types;
	
	private static int world_width;
	
	public static int getWorld_width() {
		return world_width;
	}

	private static int world_height;
	public static int getWorld_height() {
		return world_height;
	}

	private static int offset_x;
	private static int offset_y;
	private static ArrayList<Sprite> list;
	
	
	public static ArrayList<Sprite> getList() {
		return list;
	}

	public static int getOffset_y() {
		return offset_y;
	}

	public static int getOffset_x() {
		return offset_x;
	}


	
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	
	
	private static Sprite createSprite(String name, float x, float y) {
		switch (name) {
			case "wall":
				return new Wall(x, y);
			case "floor":
				return new Floor(x, y);
			case "stone":
				return new Stone(x, y);
			case "target":
				return new Target(x, y);
			case "player":
				return new Player(x, y);
			case "cracked":
				return new CrackedWall(x, y);
			case "door":
				return new Door(x, y);
			case "ice":
				return new Ice(x, y);
			case "mage":
				return new Mage(x, y);
			case "skeleton":
				return new Skeleton(x, y);
			case "tnt":
				return new Tnt(x, y);
				
			case "switch":
				return new Switch(x, y);
			case "rogue":
				return new Rogue(x, y);
		}
		return null;
	}
	
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile
	public static boolean isBlocked(float x, float y, String type) {
		x -= offset_x+1; //epsilon error
		x /= App.TILE_SIZE;
		y -= offset_y;
		y /= App.TILE_SIZE;
		
		// Rounding is important here
		x = Math.round(x);
		y = Math.round(y);
		
		// Do bounds checking!
		if (x >= 0 && x < world_width && y >= 0 && y < world_height) {
			//System.out.println(x + "    " + y);
			return types[(int)x][(int)y].equals(type) || types[(int)x][(int)y].equals("stone");
		}
		// Default to blocked
		return true;
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		
		list = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			// Create the array of object types
			types = new String[world_width][world_height];
			
			// Calculate the top left of the tiles so that the level is
			// centered
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
				
				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				types[(int)x][(int)y] = name;
				//System.out.println(x + " " + y +" " + types[(int)x][(int)y]);
								
				// Adjust for the grid
				x = offset_x + x * App.TILE_SIZE;
				y = offset_y + y * App.TILE_SIZE;
				
				// Create the sprite
				list.add(createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
