import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesA extends Thread implements Species {
	private final double maxLifespan = 10.0;
	private final double fitness = 0.8;
	private double lifespan;
	private int[] coords;
	private int xcoord, ycoord;
	
	/**
	 * 
	 */
	public SpeciesA(int x, int y) {
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
	}
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesA(int dimension) {
		Random rand = new Random();
		xcoord = rand.nextInt(dimension);
		ycoord = rand.nextInt(dimension);
	}
	
	@Override
	public void run() {
		this.born();
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
		// TODO Auto-generated method stub
		
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
