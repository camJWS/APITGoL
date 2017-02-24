/**
 * Subclass that extends Species to 
 * assign an empty element of the grid 
 * to contain an object of type EmptySquare
 * to denote no creature exists in provided coordinates
 * Thread is never run.
 * @author Cameron Shanks
 */
public class EmptySquare extends Species {
	/**
	 * Constructor sets the empty boolean to true
	 * to flag that this is an EmptySquare object 
	 * and sets the display symbol to -
	 * otherwise the object attributes are null
	 * @param x coordinate
	 * @param y coordinate
	 * @param g current grid
	 */
	public EmptySquare(int x, int y, Grid g) {
		super(x,y,g);
		empty = true;
		speciesSymbol = "-";
		alive = false;
	}
	
	public void setLifespan(){
		return;
	}
	
	public void setSpeciesSymbol() {
		speciesSymbol = "-";
	}
	
	public double getFitness(){
		return 0.0;
	}


}
