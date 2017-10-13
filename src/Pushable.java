
public abstract class Pushable extends Movable {
	public Pushable(String image_src, float x, float y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void push(int dir, World world) {
			moveToDest(dir, world);
		
	}
	
	public boolean active() {
		return true;
	}
	
	
	
}
