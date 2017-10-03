
import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Player extends Sprite  {
	public float r = getX();
	
	public Player(float x, float y) {
		super("res/player.png", x, y);
	}
	
	public float playerX() {
		return r;
	}
	
	private int c =1;
	

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	
	@Override
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {
		int dir = DIR_NONE;
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
		
			dirP = dir = DIR_LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dirP = dir = DIR_RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dirP = dir = DIR_UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dirP = dir = DIR_DOWN;
		}
		
		// Move to our destination
		moveToDest(dir);
		
		
	}
	
}
