import org.newdawn.slick.Input;

public class Explosion extends Sprite {

	public Explosion(float x, float y) {
		super("res/explosion.png", x, y);
		// TODO Auto-generated constructor stub
	}
	float time;
	public void update(Input input, int delta, World world) throws ClassNotFoundException {
		time += delta/1000f;
		if(time > 0.4) {
			world.destroySprite(this);
		}
		
	}

}
