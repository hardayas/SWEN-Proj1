import org.newdawn.slick.Input;

public class CrackedWall extends WallType {
	public CrackedWall(float x, float y) {
		super("res/cracked.png", x, y);
	}
	
	@Override
	public void update(Input input, int delta, World world) {
		
		Sprite testSprite = world.getSpriteOfType(getX(), getY());
		
		if(testSprite != null && Tnt.class.isAssignableFrom(testSprite.getClass())) {
			//prints if the collision is happening
			world.destroySprite(this);
		}
		
	}
}