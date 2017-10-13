import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Tnt extends Pushable {
	public Tnt(float x, float y) {
		super("res/tnt.png", x, y);
	}
	
	private boolean explosion = false;
	
	public boolean isExplosion() {
		return explosion;
	}
	public void setExplosion(boolean explosion) {
		this.explosion = explosion;
	}
	
	
	
	//using polymorphism here since tnt can run into cracked wall
	@Override
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
		
		Sprite testSprite = world.getSpriteOfType(x + delta_x, y + delta_y);
		// Make sure the position isn't occupied!
		if (testSprite != null && CrackedWall.class.isAssignableFrom(testSprite.getClass())) {
			
			setX(x + delta_x); setY(y + delta_y);
			
			//delete tnt here and then add history
			setExplosion(true);
			world.destroySprite(this);
			
			if(delta_x != 0 || delta_y != 0) {
				ArrayList<Sprite> s1 = null;
				//world.updateMovableHistory(s1);
				//need to work here
				
			}
			setX(x + delta_x); setY(y + delta_y);
			
		}
		//otherwise move normally
		if (!world.isBlocked(x + delta_x, y + delta_y) ) {
			
			
			if(delta_x != 0 || delta_y != 0) {
				ArrayList<Sprite> s1 = null;
				
				//world.updateMovableHistory(s1);
				//need to work here
				
			}
			setX(x + delta_x); setY(y + delta_y);
		}
		return result;
	}
}