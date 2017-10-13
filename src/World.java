
import java.util.ArrayList;

import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	boolean restart = false;
	boolean nextLevel = false; //influenced by player and target
	boolean closedDoor = true;
	boolean undoFlag = false;
	
	private  ArrayList<Sprite> sprites;
	ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
	ArrayList<Sprite> toAdd = new ArrayList<Sprite>();
	HistoryStack history = new HistoryStack();
	Explosion explosion;
		
	
	private boolean playerMoved = false;
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	public World(int level) {
		this.sprites = Loader.loadSprites("res/levels/"+level+".lvl");
	}
	
	public void update(Input input, int delta) throws ClassNotFoundException {
		for (Iterator<Sprite> sprite = sprites.iterator(); sprite.hasNext(); ) {
			if (sprite != null) {
				sprite.next().update(input,delta, this);
			}
		}
		
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
	}
	
	public void createSprite(Sprite sprite) {
		toAdd.add(sprite);
	}
	
	public void destroySprite(Sprite sprite) {
		toRemove.add(sprite);
	}
	
	public boolean isBlocked(float x, float y) {
		//right now it checks wall and pushables at the location x y
		for (Sprite sprite : sprites) {
			
			//replace here with epsilon
			boolean block = Math.abs(sprite.getX()-x) < 1 && Math.abs(sprite.getY()-y) < 1 
					&& Pushable.class.isAssignableFrom(sprite.getClass());
			
			boolean wall = Math.abs(sprite.getX()-x) < 1 && Math.abs(sprite.getY()-y) < 1
					&& WallType.class.isAssignableFrom(sprite.getClass());
				
			if( block || wall) {return true;}
				
		}
			
		
		return false;
	}
	
	//returns the sprite of type on x, y of class pushable else return null
	public Sprite getSpriteOfType(float x , float y) {	
		Sprite s = null;
		for (Sprite sprite : sprites) {
			//skip if not pushable or wall
			if(!Pushable.class.isAssignableFrom(sprite.getClass()) && !WallType.class.isAssignableFrom(sprite.getClass())) {continue;}
			
			if (Math.abs(sprite.getX() - x) < 5 && Math.abs(sprite.getY() - y) < 5 
					) {
				//System.out.println(sprite);
				return sprite;
			}
		}
		return s;
		
	}
	
	public void updateMovableHistory(float x, float y) {
		
		//history.push(x, y, this);
		System.out.println(history.getLastX());
	}
	
	public void undoHistory() {
		
		//System.out.println(history.size());
		history.pop();
		/*ArrayList<Sprite> prevSprites = history.getStack();*/
		/*for(Sprite s: history.getStack()) {
			System.out.println(s.getType() + " " + s.getX());
		}*/
		
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
	

	
	public boolean allTargetActivated() {
		//go through all sprites
		for (Sprite sprite : sprites) {
			
			
			
			if(sprite.getType().contains("Target")) {
				
				Sprite s = getSpriteOfType(sprite.getX(), sprite.getY());
				if(s == null) {return false;}
			}
		}
		return true;
	}

	public boolean isPlayerMoved() {
		return playerMoved;
	}

	public void setPlayerMoved(boolean playerMoved) {
		this.playerMoved = playerMoved;
	}
	
	//creates a list copy
	public ArrayList<Sprite> copy(ArrayList<Sprite> sprites) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		for(Sprite current : sprites) {
			copy.add(current);
		}
		return copy;
		
	}
	
	
}
