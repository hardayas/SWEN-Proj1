import org.newdawn.slick.Input;

public class Switch extends Sprite {
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
	}
	private float xDoor, yDoor;
	public boolean doorClosed = true;
	
	@Override
	public void update(Input input, int delta, World world) throws ClassNotFoundException  {
		
		Sprite testSprite = world.getSpriteOfType(getX(), getY());
		
		Door door= (Door) world.getSpriteOfType("Door");
		if(door != null) {
			xDoor = door.getX(); yDoor = door.getY(); 
		}
		
		if(testSprite != null) {
			
			//world.destroySprite(door);
			
			doorClosed = false;
			
		} else {
			if(door == null) {
				world.createSprite(new Door(xDoor, yDoor));
			}
			doorClosed = true;
		}
	}
}