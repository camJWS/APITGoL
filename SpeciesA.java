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
	 * @param dimension
	 */
	public SpeciesA(Grid g) {
		super(g);
	}
	
	public SpeciesA(int x, int y){
		super(x,y);
	}
	
//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(maxLifespan);
	}
	@Override
	public void setSpeciesSymbol() {
		speciesSymbol = "A";
	}


}
