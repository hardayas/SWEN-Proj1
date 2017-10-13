/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Explosion extends Sprite {

	public Explosion(float x, float y) {
		super("res/explosion.png", x, y);
	}
	private final float LIMIT = 0.4f;
	private float time;
	
	public void update(Input input, int delta, World world) throws ClassNotFoundException {
		time += delta/1000f;
		if(time > LIMIT) {
			world.destroySprite(this);
		}
		
	}

}
