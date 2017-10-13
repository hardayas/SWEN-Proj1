/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */

import org.newdawn.slick.Input;

/**
 * Player Class handles input from the user and moves in four directions 
 * every update. When making a move if the position being
 * moved into is pushable then it pushes it.
 * If the player has been successful in making the move then it will be 
 * history for undoing. It also triggers all the undoing as this is where
 * the user input is interpreted.
 */
public class Player extends Movable  {
	
	public Player(float x, float y) {
		super("res/player.png", x, y);
		
	}
	
    /** Update the game state for a frame. 
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     * @param world Current level of the game
     */
	@Override
	public void update(Input input, int delta, World world) {
		
		world.setUndoFlag(false);
		float lastX = getX(), lastY = getY();
		
		int dir = DIR_NONE;
		Sprite testSprite;
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
			
			//check if moving position is pushable with testSprite
			testSprite = world.getSpriteOfType(getX()-App.TILE_SIZE, getY());
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {	
				
				((Pushable)testSprite).push(dir, world);
			
			}
		}	
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
			
			testSprite = world.getSpriteOfType(getX()+App.TILE_SIZE, getY());
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				((Pushable)testSprite).push(dir, world);
			
			}
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
			
			testSprite = world.getSpriteOfType(getX(), getY()-App.TILE_SIZE);
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				((Pushable)testSprite).push(dir, world);
			}
			
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
			
			testSprite = world.getSpriteOfType(getX(), getY()+App.TILE_SIZE);
			if(testSprite != null && Pushable.class.isAssignableFrom(testSprite.getClass())) {
				((Pushable)testSprite).push(dir, world);
			}
		}
		
		//this moves player and adds to history
		if(moveToDest(dir, world)) {
			world.setPlayerMoved(true);
			addToHistory(lastX, lastY);
			world.setTotalMoves(world.getTotalMoves() + 1);
		} else {
			world.setPlayerMoved(false);
		}
			
		//undo
		if(input.isKeyPressed(Input.KEY_Z)) {
			world.setTotalMoves(world.getTotalMoves() - 1);
			world.setUndoFlag(true); 
			undo();
		}
		
		//restart
		if(input.isKeyPressed(Input.KEY_R)) {
			world.setRestart(true);
		}
		
	}

}
