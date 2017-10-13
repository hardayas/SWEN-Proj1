import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Skeleton extends Movable {
	public Skeleton(float x, float y) {
		super("res/skeleton.png", x, y);
	}
	
	int dir = Sprite.DIR_UP;
	
	public void update(Input input, int delta, World world) {
		
		
			
		if(dir == Sprite.DIR_UP) {
			if(!world.isBlocked(getX(), getY() - App.TILE_SIZE)) {
				setY(getY() - App.TILE_SIZE*delta/1000f);
			} else {
				dir = Sprite.DIR_DOWN;
			}
		}
			
		if(dir == Sprite.DIR_DOWN) {
			if(!world.isBlocked(getX(), getY() + App.TILE_SIZE)) {
				setY(getY() + App.TILE_SIZE*delta/1000f);
			} else {
				dir = Sprite.DIR_UP;
			}
		}
			
		

		
	}
}
