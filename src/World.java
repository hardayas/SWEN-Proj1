
import java.util.ArrayList;

import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	public static final float EPSILON = .1f;
	
	private boolean restart = false;
	private boolean nextLevel = false; 
	private boolean undoFlag = false;
	private int totalMoves = 0;
	private boolean playerMoved = false;
	
	private  ArrayList<Sprite> sprites;
	private ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
	private ArrayList<Sprite> toAdd = new ArrayList<Sprite>();
	private Explosion explosion;
	
	//all getters and setters
	public ArrayList<Sprite> getToRemove() {
		return toRemove;
	}
	public ArrayList<Sprite> getToAdd() {
		return toAdd;
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
	
	//all methods
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
	
	public void render(Graphics g) {
		for (Iterator<Sprite> sprite = sprites.iterator(); sprite.hasNext(); ) {
			if (sprite != null) {
				sprite.next().render(g);
			}
		}
		g.drawString("Player Moves = " + totalMoves, 100, 100);
	}
	
	public void createSprite(Sprite sprite) {
		toAdd.add(sprite);
	}
	
	public void destroySprite(Sprite sprite) {
		toRemove.add(sprite);
	}
	
	//it checks if wall or pushable at the location x y
	public boolean isBlocked(float x, float y) {
		
		for (Sprite sprite : sprites) {
			
			//replace here with epsilon
			boolean block = Math.abs(sprite.getX()-x) < EPSILON && Math.abs(sprite.getY()-y) < EPSILON 
					&& Pushable.class.isAssignableFrom(sprite.getClass());
			
			boolean wall = Math.abs(sprite.getX()-x) < EPSILON && Math.abs(sprite.getY()-y) < EPSILON
					&& WallType.class.isAssignableFrom(sprite.getClass());
				
			if( block || wall) {return true;}		
		}	
		return false;
	}
	
	//returns the sprite of type on x, y of class pushable else return null
	public Sprite getSpriteOfType(float x , float y) {	
		
		for (Sprite sprite : sprites) {
			
			//skip if not pushable or wall
			if(!Pushable.class.isAssignableFrom(sprite.getClass()) 
					&& !WallType.class.isAssignableFrom(sprite.getClass())) { continue; }
			
			if (Math.abs(sprite.getX() - x) < EPSILON && Math.abs(sprite.getY() - y) < EPSILON ) {
				return sprite;
			}
		}
		return null;
		
	}
	
	// returns Sprite of class name if found else return null
	public Sprite getSpriteOfType(String name) throws ClassNotFoundException {
		
		for(Sprite sprite: sprites) {
			if(Class.forName(name).isAssignableFrom(sprite.getClass())) {
				return sprite;
			}
		}
		return null;
	}
	
	//whether or not all targets are activated yet
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

	// this will restart level if player makes contact with enemy
	public void restart(Sprite enemy) throws ClassNotFoundException {
		Player player = (Player) getSpriteOfType("Player");
		
		if(Math.abs(player.getX() - enemy.getX()) < EPSILON && 
				Math.abs(player.getY() - enemy.getY()) < EPSILON) {
			setRestart(true);
			
		}
	}
	
}
