import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesB extends Species {
	private final int maxLifespan = 5;
	private final double fitness = 0.4;
	/**
	 * 
	 */
	public SpeciesB(int x, int y) {
		super(x,y);
	}
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesB() {
		super();
	}
	
//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(maxLifespan);
	}

}
