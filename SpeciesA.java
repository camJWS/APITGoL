import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesA extends Species {
	private final int maxLifespan = 10;
	private final double fitness = 0.8;
	/**
	 * 
	 */
	public SpeciesA(int x, int y) {
		super(x,y);
	}
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesA() {
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
