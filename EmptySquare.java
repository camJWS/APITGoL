import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class EmptySquare extends Species {
	/**
	 * 
	 * @param dimension
	 */
	public EmptySquare(int x, int y, Grid g) {
		super(x,y,g);
		//type = true;
		empty = true;
		speciesSymbol = "-";
	}
	
//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		return;
	}
	@Override
	public void setSpeciesSymbol() {
		speciesSymbol = "-";
	}

	
	public double getFitness(){
		return 0.0;
	}


}
