/**
 * Project 2 SWEN20003: Object Oriented Software Development 2017
 * by Hardaya Singh
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    
    private World world;
    private int level = 0;
    private final int TOTAL_LEVELS = 6;
    
    public App()
    {    	
        super("Shadow Blocks");
    }
    
    
    
    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
    	world = new World(level);
    		
    }
    
    /** Updates the level and also restarts same level.
     * @param level Level that needs modification.
     */
    public void modifyLevel(int level) {
    		if (level < TOTAL_LEVELS) {
    			world = new World(level);
    			
    		}
    		
    }
    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException 
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        
        // Alter world for next update if needed
        if(world.getToRemove() != null) world.getSprites().removeAll(world.getToRemove());
        if(world.getToAdd() != null) world.getSprites().addAll(world.getToAdd());
        
        
        try {
			world.update(input, delta);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        
        if (world.isRestart()) {
        		modifyLevel(level);
        }
        
        if(world.isNextLevel()) {
        		modifyLevel(++level);
		}
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {   
            gc.exit();     
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
    	world.render(g);
    }
    
    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}