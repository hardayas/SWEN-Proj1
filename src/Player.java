import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {
	Player(String typeS, float tempX, float tempY) throws SlickException {
		super(typeS, tempX, tempY);
		// TODO Auto-generated constructor stub
	}

	public void update(Input input, int delta) throws NumberFormatException, SlickException {
			
			if (input.isKeyPressed(Input.KEY_RIGHT)) {
				float x = getX();
				System.out.println(x);
				boolean b = Loader.restrictedArea((float) (x+.01), (float) (getY()));
				if(b) {
					setX(x);
				}
				else {
					setX(x+=1);
				}
				
			}
			if (input.isKeyPressed(Input.KEY_LEFT)) {
				float x;
				x = getX();
				boolean b = Loader.restrictedArea((float) (x-.01), (float) (getY()));
				if(b) {
					setX(x);
				}
				else {
					setX(x-=1);
				}
				
			}
			if (input.isKeyPressed(Input.KEY_UP)) {
				float y = getY();
				boolean b = Loader.restrictedArea((float) (getX()), (float) (y-0.01));
				if(b) {
					setY(y);
				}
				else {
					setY(y-=1);
				}
			
			}
	
			if (input.isKeyPressed(Input.KEY_DOWN)) {

				float y = getY();
				boolean b = Loader.restrictedArea((float) (getX()), (float) (y+0.01));
				if(b) {
					setY(y);
				}
				else {
					setY(y+=1);
				}
			
			}
		}	

}
