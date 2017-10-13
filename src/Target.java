import org.newdawn.slick.Input;

public class Target extends Sprite {
	public Target(float x, float y) {
		super("res/Target.png", x, y);
	}
	
	private boolean activated = false;
	
	@Override
	public void update(Input input, int delta, World world) {
		activated = (world.allTargetActivated());
		world.nextLevel = activated;
		
		if(activated) System.out.println("all target activated");
	}

	public boolean isActivated() {
		return activated;
	}
	
}
