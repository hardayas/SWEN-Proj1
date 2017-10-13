
import java.awt.Graphics;
import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Player extends Movable  {
	
	public Player(float x, float y) {
		super("res/player.png", x, y);
		
	}
	private int playerCount = 0;
	
	@Override
	public void update(Input input, int delta, World world) {
		
		float lastX = getX(), lastY = getY();
		
		int dir = DIR_NONE;
		Sprite testSprite;
		
		if (input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_J)) {
			playerCount++;
			dir = DIR_LEFT;
			testSprite = world.getSpriteOfType(getX()-App.TILE_SIZE, getY());
			
			//check if moving position is pushable
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				
				((Pushable)testSprite).push(dir, world);
			
			}
		}	
		else if (input.isKeyPressed(Input.KEY_RIGHT) || input.isKeyPressed(Input.KEY_L)) {
			
			dir = DIR_RIGHT;
			playerCount++;
			testSprite = world.getSpriteOfType(getX()+App.TILE_SIZE, getY());
			
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				((Pushable)testSprite).push(dir, world);
			
			}
			
		}
		else if (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_I)) {
			dir = DIR_UP;
			playerCount++;
			testSprite = world.getSpriteOfType(getX(), getY()-App.TILE_SIZE);
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				
				((Pushable)testSprite).push(dir, world);
			
			}
			
		}
		else if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_K)) {
			dir = DIR_DOWN;
			playerCount++;
			testSprite = world.getSpriteOfType(getX(), getY()+App.TILE_SIZE);
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				
				((Pushable)testSprite).push(dir, world);
			
			}
			
		}
		
		//this moves player
		if(moveToDest(dir, world)) {
			world.setPlayerMoved(true);
			addToHistory(lastX, lastY);
		}else {
			world.setPlayerMoved(false);
		}
			
		
		//depending on the input add to history
		
		
		//undo
		if(input.isKeyPressed(Input.KEY_U)) {
			undo();
		}
		
		if(input.isKeyPressed(Input.KEY_R)) {
			world.restart = true;
		}
		
		//player count
		//if(playerCount>5) {world.nextLevel = true; playerCount = 0;}
		
	}
	
	public void onMove(int dir, float testX, float testY) {
		
	}
	

	

	
	
}
