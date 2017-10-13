import java.util.ArrayList;


public class HistoryStack {
	
	private ArrayList<ArrayList<Sprite>> stack;
	

	public HistoryStack() {
		stack = new ArrayList<ArrayList<Sprite>>();
	}
	
	public ArrayList<Sprite> getStack() {
		return stack.get(0);
	}

	public void push(ArrayList<Sprite> s2, World world) {
		stack.add(s2); 
		for(ArrayList<Sprite> s1 : world.history.stack) {
			System.out.println(world.history.stack.size());	
			for(Sprite s: s1) {
				System.out.println(s.getType() + " " + s.getX());			}
		}
	}
	
	public void pop() {
		stack.remove(stack.size() - 1);
		
	}
	
	public int size() {
		return stack.size();
	}
}
