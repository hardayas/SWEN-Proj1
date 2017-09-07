

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//need to get rid of magic numbers

public class World {	
	int x = 20,y =20;
	
	static float SPEED = .5f;
	
	private Player pl;
	private Sprite[] sprites;
	World() throws NumberFormatException, SlickException {
		sprites = Loader.loadSprites("res/levels/0.lvl");
		pl = new Player(sprites[128].getType(),sprites[128].getX(),sprites[128].getY());
		
	}
	

	public void setP(Sprite s) {
		this.sprites[125] = s;
	}
		
	public void update(Input input, int delta) throws NumberFormatException, SlickException {
		pl.update(input, delta);
	}
	
	public void render(Graphics g) throws SlickException {
		for(int i=0;i<128;i++) {
			sprites[i].render(g);
		}
		pl.render(g);
	}
}
