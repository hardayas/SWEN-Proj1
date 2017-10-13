import java.util.ArrayList;


public class HistoryStack {
	
	public ArrayList<Float> xS;
	
	public float getxS() {
		return xS.get(xS.size()-1);
	}


	public ArrayList<Float> yS;
	

	public HistoryStack() {
		xS = new ArrayList<Float>();
		yS = new ArrayList<Float>();
	}
	
	

	public void push(float x, float y , World world) {
		xS.add(x); 
		yS.add(y);
	}
	
	public void pop() {
		xS.remove(xS.size() - 1);
		yS.remove(yS.size() - 1);
		
	}
	
	public int size() {
		return xS.size();
	}
}
