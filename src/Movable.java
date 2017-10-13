import java.util.ArrayList;

import org.newdawn.slick.Input;

public abstract class Movable extends Sprite {

	
	
	public Movable(String image_src, float x, float y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	public boolean hasHistory() {
		return true;
	}
	public void addToHistory() {
		
	}
	public void undo() {
		
	}
	public boolean moveToDest(int dir, World world) {
		boolean result = false;
		int speed = 32;
		float x = getX(), y = getY();
		// Translate the direction to an x and y displacement
		int delta_x = 0,
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
		if (!world.isBlocked(x + delta_x, y + delta_y) ) {
			
			
			if(delta_x != 0 || delta_y != 0) {
				result = true;
				ArrayList<Sprite> s1 = null;
				
				//world.updateMovableHistory(s1);
				//need to work here
				
			}
			setX(x + delta_x); setY(y + delta_y);
		}
		return result;
		
	}
	public void moveToDest(int dir, float x, float y) {

		
	}
	
	public void onMove(int dir, float testX, float testY) {
	}
	
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {
	}
	
}
