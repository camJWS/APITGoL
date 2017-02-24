import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesA extends Species {
	private final int MAXLIFESPAN = 10;
//	private final double fitness = 0.8;
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesA(int x, int y, Grid g) {
		super(x,y,g);
		type = true;
		empty = false;
		speciesSymbol = "A";
		fitness = 0.8;
		//System.out.println("Fitness A: " + fitness);
	}
	
//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(MAXLIFESPAN);
	}
	@Override
	public void setSpeciesSymbol() {
		speciesSymbol = "A";
	}

	public double getFitness(){
		return fitness;
	}

}
