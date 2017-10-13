/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * World class controls the interaction among
 * various types of sprites and keeps track of everything.
 */
public class World {
	
	public static final float EPSILON = .1f;
	
	private boolean restart = false;
	private boolean nextLevel = false; 
	private boolean undoFlag = false;
	private boolean playerMoved = false;
	private int totalMoves = 0;
	
	private  ArrayList<Sprite> sprites;
	private ArrayList<Sprite> spritesToRemove = new ArrayList<Sprite>();
	private ArrayList<Sprite> spritesToAdd = new ArrayList<Sprite>();
	
	/** All getters and setter for the private attributes
	 *  listed above.
     */
	public ArrayList<Sprite> getToRemove() {
		return spritesToRemove;
	}
	public ArrayList<Sprite> getToAdd() {
		return spritesToAdd;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	public World(int level) {
		this.sprites = Loader.loadSprites("res/levels/"+level+".lvl");
	}
	
	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean isNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(boolean nextLevel) {
		this.nextLevel = nextLevel;
	}

	public boolean isUndoFlag() {
		return undoFlag;
	}

	public void setUndoFlag(boolean undoFlag) {
		this.undoFlag = undoFlag;
	}
	
	public int getTotalMoves() {
		return totalMoves;
	}

	public void setTotalMoves(int totalMoves) {
		this.totalMoves = totalMoves;
	}
	
	public boolean isPlayerMoved() {
		return playerMoved;
	}

	public void setPlayerMoved(boolean playerMoved) {
		this.playerMoved = playerMoved;
	}
	
	/** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) throws ClassNotFoundException {
		for (Iterator<Sprite> sprite = sprites.iterator(); sprite.hasNext(); ) {
			if (sprite != null) {
				sprite.next().update(input,delta, this);
			}
		}
		
		// this adds explosion effect
		Tnt tnt = (Tnt)getSpriteOfType("Tnt");
		if(tnt != null && tnt.isExplosion()) {
			Explosion explosion = new Explosion(tnt.getX(), tnt.getY());
			sprites.add(explosion);
		}
	}
	
	/** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) {
		for (Iterator<Sprite> sprite = sprites.iterator(); sprite.hasNext(); ) {
			if (sprite != null) {
				sprite.next().render(g);
			}
		}
		g.drawString("Player Moves = " + totalMoves, 100, 100);
	}
	
	/** CreateSprite will add a sprite to ArrayList spritesToAdd which
	 * will then add those sprites to Arraylist sprites in next update. 
     * @param sprite Sprites to be added.
     */
	public void createSprite(Sprite sprite) {
		spritesToAdd.add(sprite);
	}
	
	/** CreateSprite will add a sprite to ArrayList spritesToRemove which
	 * will then remove those sprites from Arraylist sprites in next update. 
     * @param sprite Sprites to be removed.
     */
	public void destroySprite(Sprite sprite) {
		spritesToRemove.add(sprite);
	}
	
	/** isBlocked checks if there is wall or pushable at the location x, y. 
     * @param x X coordinate.
     * @param x Y coordinate.
     * @return boolean
     */
	public boolean isBlocked(float x, float y) {
		
		for (Sprite sprite : sprites) {
			
			//replace here with epsilon
			boolean block = Math.abs(sprite.getX()-x) < EPSILON
						    && Math.abs(sprite.getY()-y) < EPSILON 
							&& Pushable.class.isAssignableFrom(sprite.getClass());
			
			boolean wall = Math.abs(sprite.getX()-x) < EPSILON 
						   && Math.abs(sprite.getY()-y) < EPSILON
					       && WallType.class.isAssignableFrom(sprite.getClass());
				
			if( block || wall) {
				return true;
			}		
		}	
		return false;
	}
	
	/** Given x, y this function gives the sprite at that location.
	 * It only considers sprites of type wall and pushable. 
     * @param x X coordinate.
     * @param x Y coordinate.
     * @return sprite Sprite at that x, y.
     */
	public Sprite getSpriteOfType(float x , float y) {	
		
		for (Sprite sprite : sprites) {
			
			//skip if not pushable or wall
			if(!Pushable.class.isAssignableFrom(sprite.getClass()) 
				&& !WallType.class.isAssignableFrom(sprite.getClass())) {
				
				continue; 
			}
			
			if (Math.abs(sprite.getX() - x) < EPSILON 
				&& Math.abs(sprite.getY() - y) < EPSILON ) {
				
				return sprite;
			}
		}
		return null;
		
	}
	
	/** Going through sprites in the world it will return
	 * the Sprite of class name or null if not found. 
     * @param name Class name to be tested.
     * @return sprite Sprite of type name.
     * @return null If the sprite is no found.
     */
	public Sprite getSpriteOfType(String name) throws ClassNotFoundException {
		
		for(Sprite sprite: sprites) {
			if(Class.forName(name).isAssignableFrom(sprite.getClass())) {
				return sprite;
			}
		}
		return null;
	}
	
	/** 
	 * Checks if every target is occupied with a movable 
     * @return boolean
     */
	public boolean allTargetActivated() {
		for (Sprite sprite : sprites) {
			if(sprite.getType().contains("Target")) {
				
				//checking if there is a block at each target location
				Sprite block = getSpriteOfType(sprite.getX(), sprite.getY());
				if(block == null) {
					return false;
					}
			}
		}
		return true;
	}
	
	/** When player makes contact with enemy unit
	 * restart flag will be set to true. 
     * @param enemy Sprite against which player is to be tested.
     */
	// this will restart level if player makes contact with enemy
	public void restart(Sprite enemy) throws ClassNotFoundException {
		Player player = (Player) getSpriteOfType("Player");
		
		if(Math.abs(player.getX() - enemy.getX()) < EPSILON && 
				Math.abs(player.getY() - enemy.getY()) < EPSILON) {
			setRestart(true);
			
		}
	}
	
}
