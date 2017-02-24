import java.util.ArrayList;
/**
 * Main class to run the simulation
 * @author Cameron Shanks
 */
public class TestWorld{
	
	private static Grid grid;
	
	public static void main(String[] args){
		final int NUMINITIAL = 4;
		final int GRIDSIZE = 20;
		// Use wrapAround = true to have wraparound edges...
		final boolean WRAPAROUND = true;
		// Uncomment wrapAround = false to use hard edges...
//		boolean wrapAround = false;
		
		grid = new Grid(GRIDSIZE, NUMINITIAL, WRAPAROUND);
		grid.start();
		grid.startGame();
	}
}
