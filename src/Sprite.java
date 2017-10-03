
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class Sprite {
	// Used to decide what direction an object is moving
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	public static int dirP = DIR_NONE;
	
	public static int getDirP() {
		return dirP;
	}

	public void setDirP(int dirP) {
		this.dirP = dirP;
	}

	private Image image = null;
	private float x;
	private float y;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		snapToGrid();
		this.type = image_src;
	}
	
	//Getters
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
			
	// Setters
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}
	
	// Forces this sprite to align to the grid
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}
	
	public void moveToDest(int dir) {
		float speed = 32;
		// Translate the direction to an x and y displacement
		float delta_x = 0,
			  delta_y = 0;
		switch (dir) {
			case DIR_LEFT:
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		// Make sure the position isn't occupied!
		if (!Loader.isBlocked(x + delta_x, y + delta_y, "wall") ) {
			x += delta_x;
			y += delta_y;
		}
	}

	public void update(Input input, int delta, ArrayList<Sprite> sprites) {
	}
}
