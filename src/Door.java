/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Door extends WallType {
	public Door(float x, float y) {
		super("res/door.png", x, y);
	}
	
	
	@Override
	public void update(Input input, int delta, World world) throws ClassNotFoundException {
		
		Switch  test = (Switch)world.getSpriteOfType("Switch"); 
		if(!test.doorClosed) {
			world.destroySprite(this);
		}
	}
	
}