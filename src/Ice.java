import org.newdawn.slick.Input;

public class Ice extends Pushable {
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
	}
	
	private final float oneSecond = 1;
	int moveDirection = Sprite.DIR_NONE;
	float time;
	
	@Override
	public void update(Input input, int delta, World world) {
		time += delta/1000f; 
		
		if(world.isPlayerMoved()) {
			addToHistory(getX(), getY());
		}
		if(world.undoFlag) {
			undo();
		}
		else {
			if(time>oneSecond) {
				moveToDest(moveDirection, world);
				time = 0;
			}
		}
	}
	
	@Override
	public void push(int pushedDirection, World world) {
		moveDirection = pushedDirection;
	}
}