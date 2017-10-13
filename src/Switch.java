/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */
import org.newdawn.slick.Input;

public class Switch extends Sprite {
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
	}
	private float xDoor, yDoor;
	private boolean doorClosed = true;
	
	public boolean isDoorClosed() {
		return doorClosed;
	}

	public void setDoorClosed(boolean doorClosed) {
		this.doorClosed = doorClosed;
	}
	
	@Override
	public void update(Input input, int delta, World world) throws ClassNotFoundException  {
		
		Sprite testSprite = world.getSpriteOfType(getX(), getY());
		
		Door door= (Door) world.getSpriteOfType("Door");
		if(door != null) {
			xDoor = door.getX(); yDoor = door.getY(); 
		}
		
		if(testSprite != null) {
			
			//world.destroySprite(door);
			
			setDoorClosed(false);
			
		} else {
			if(door == null) {
				world.createSprite(new Door(xDoor, yDoor));
			}
			setDoorClosed(true);
		}
	}
	
}