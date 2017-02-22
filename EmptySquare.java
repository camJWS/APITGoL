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
	public EmptySquare(Grid g) {
		super(g);
	}
	
	public EmptySquare(int x, int y){
		super(x,y);
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


}
