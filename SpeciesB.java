import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesB extends Thread implements Species {
	private final double maxLifespan = 5.0;
	private final double fitness = 0.4;
	private double lifespan;
	private int[] coords;
	private int xcoord, ycoord;
	
	/**
	 * 
	 */
	public SpeciesB(int x, int y) {
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
	}
	/**
	 * 
	 */
	public SpeciesB(int dimension) {
		Random rand = new Random();
		xcoord = rand.nextInt(dimension);
		ycoord = rand.nextInt(dimension);
	}
	
	@Override
	public void run() {
		
		
	}

	@Override
	public void reproduce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void born() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double setFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLifespan() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialise() {
		
	}
	
	private boolean checkCoords(int[][] input){
		for (int i = 0; i < 5; i++){
			for  (int j = 0; j < 5 && j != i; j++)
				if (input[i][0] == input[j][0] && input[i][1] == input[j][1]){
					return false;
				}
				else
					continue;
		}
		return true;
	}

}
