
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	private  ArrayList<Sprite> sprites;
	

	public World() {
		this.sprites = Loader.loadSprites("res/levels/0.lvl");
	}
	
	public void update(Input input, int delta) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.update(input, delta, sprites);
			}
		}
	}
	
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void removeSprites(int index) {
		sprites.remove(index);
	}
	
	public String whatSprite(float x, float y) {
		x -= Loader.getOffset_x()+1; //epsilon error
		x /= App.TILE_SIZE;
		y -= Loader.getOffset_y();
		y /= App.TILE_SIZE;
		
		// Rounding is important here
		x = Math.round(x);
		y = Math.round(y);
		
		String type = "null";
		for(Sprite s: sprites) {
			if( (int)x == (int)s.getX() && (int)y == (int)s.getY()) {
				type = s.getType();
				if(type.equals("res/floor.png")) {
					continue;
				}
				else {
					break;
				}
			}
		}
		return type;
	}
}
