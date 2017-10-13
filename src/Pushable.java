/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public abstract class Pushable extends Movable {
	public Pushable(String image_src, float x, float y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void push(int dir, World world) {
			if(moveToDest(dir, world)) {
				
			}	
		
	}
	
	@Override
	public void update(Input input, int delta, World world) {
		if(world.isPlayerMoved()) {
			addToHistory(getX(), getY());
		}
		if(world.isUndoFlag()) {
			undo();
		}
	}
	
	public boolean active() {
		return true;
	}
	
	@Override
	//undoes by popping first because stack is stored differently
	public void undo() {
		if(!getHistory().isEmpty()) {
			getHistory().pop();
			if(!getHistory().isEmpty()) {
				setX(getHistory().getLastX());
				setY(getHistory().getLastY());
			}
		}
	}
	
}
