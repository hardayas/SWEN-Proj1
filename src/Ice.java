/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Ice extends Pushable {
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
	}
	
	private final float LIMIT = .25f;
	private int moveDirection = Sprite.DIR_NONE;
	private float time;
	
	@Override
	public void update(Input input, int delta, World world) {
		time += delta/1000f; 
		
		if(world.isPlayerMoved()) {
			addToHistory(getX(), getY());
		}
		if(world.isUndoFlag()) {
			undo();
		}
		else {
			if(time > LIMIT) {
				moveToDest(moveDirection, world);
				time = 0;
			}
		}
	}
	
	@Override
	public void push(int pushedDirection, World world) {
		moveDirection = pushedDirection;
	}
}