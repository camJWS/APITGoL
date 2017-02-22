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
	 * @param dimension
	 */
	public SpeciesB(Grid g) {
		super(g);
	}

	public SpeciesB(int x, int y){
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
		speciesSymbol = "B";
	}

}
