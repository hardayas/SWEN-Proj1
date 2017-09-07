


import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
	private String type;
	
	Sprite(String typeS, float tempX, float tempY) throws SlickException {
		// TODO Auto-generated constructor stub
		
		type = typeS;
		x = tempX;
		y = tempY;
		image = new Image(typeS);
	}
	
	final float speed = 0.05f;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	private float x;
	private float y;
	private Image image;
	
	public void render(Graphics g) throws SlickException {
		image.draw(x*32, y*32);
			
	}
	
}
