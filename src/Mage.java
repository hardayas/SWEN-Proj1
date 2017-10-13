/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Mage extends Sprite {
	public Mage(float x, float y) {
		super("res/mage.png", x, y);
	}
	public void update(Input input, int delta, World world) throws ClassNotFoundException {
		
		//checks if this unit has made contact with the player and restart if yes 
		world.restart(this);
		
		if(world.isPlayerMoved()) {
			float x = world.getSpriteOfType("Player").getX() - getX() ;
			float y = world.getSpriteOfType("Player").getY() - getY();
			
			if(Math.abs(y) < Math.abs(x)) {
				if(!world.isBlocked(getX() + App.TILE_SIZE * Math.signum(x), getY())) {
					setX(getX() + App.TILE_SIZE * Math.signum(x));
				}
				
			} else {
				if(!world.isBlocked(getX(), getY() + App.TILE_SIZE * Math.signum(y))) {
					setY(getY() + App.TILE_SIZE * Math.signum(y));
				}
			}
		}
	}
}
