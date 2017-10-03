import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Input;

public class Stone extends Sprite {
	public Stone(float x, float y) {
		super("res/stone.png", x, y);
	}
	
	/*int collisionFromDirection(int x, int y) {
		x -= Loader.getOffset_x()+1; //epsilon error
		x /= App.TILE_SIZE;
		y -= Loader.getOffset_y();
		y /= App.TILE_SIZE;
		
		// Rounding is important here
		x = Math.round(x);
		y = Math.round(y);
		
		// Do bounds checking!
		if (s.equals("res/stone.png") {
			//System.out.println(x + "    " + y);
			return 1);
		}
		// Default to blocked
		return 0;
	}*/
	
	@Override
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {
		/*int i = 0;
		ArrayList<Sprite> sprites1 = (ArrayList<Sprite>) sprites.clone();
		for(Sprite s: sprites) {
			if(s.getType().equals("res/player.png")) {
				i = sprites.indexOf(s); break;
			}
		}
		float x = sprites1.get(i).getX();
		float y = sprites1.get(i).getY();
		for(Sprite s:sprites1) {
			if(s.getType().equals("res/stone.png") && Math.abs(x - s.getX()) < 1 && Math.abs(y - s.getY()) < 1) {
				sprites.remove(s);
			}
		}
		if(player.push()) {
			move;
		}*/
		
		for(Sprite sp : Loader.getList()) {
			int x1 = Math.round(sp.getX());
			int x2 = Math.round(this.getX());
			int y1 = Math.round(sp.getY());
			int y2 = Math.round(this.getY());
			
			boolean t = sp.getType().equals("res/player.png") && x1 == x2 && y1 == y2;
			
			if( t) {
				
				
				moveToDest(dirP);
				System.out.println("yea");

			}
			
		}
		
	}

}
