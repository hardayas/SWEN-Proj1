
import org.newdawn.slick.Input;

public class Player extends Movable  {
	
	public Player(float x, float y) {
		super("res/player.png", x, y);
		
	}
	
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
