/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Target extends Sprite {
	public Target(float x, float y) {
		super("res/Target.png", x, y);
	}
	
	private boolean activated = false;
	
	@Override
	public void update(Input input, int delta, World world) {
		activated = (world.allTargetActivated());
		world.setNextLevel(activated);
		
	}
	
}
