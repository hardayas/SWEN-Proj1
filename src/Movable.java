/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */

import java.util.ArrayList;
import org.newdawn.slick.Input;

/**
 * Movable is an abstract class to all the sprites in the 
 * game that can move.
 *
 */
public abstract class Movable extends Sprite {

	
	
	public Movable(String image_src, float x, float y) {
		super(image_src, x, y);
		
	}
	
	private HistoryStack history = new HistoryStack();
	
	public HistoryStack getHistory() {
		return history;
	}
	public void setHistory(HistoryStack history) {
		this.history = history;
	}
	public boolean hasHistory() {
		return true;
	}
	public void addToHistory(float x, float y) {
		history.push(x, y);
	}
	
	public void undo() {
		if(!history.isEmpty()) {
			setX(history.getLastX());
			setY(history.getLastY());
			history.pop();
		}
		
	}
	public boolean moveToDest(int dir, World world) {
		boolean result = false;
		int speed = App.TILE_SIZE;
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
			}
			setX(x + delta_x); setY(y + delta_y);
		}
		return result;
		
	}
	
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {
	}
	
}
