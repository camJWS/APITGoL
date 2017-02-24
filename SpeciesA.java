import java.util.Random;
/**
 * @author Cameron Shanks
 * Subclass that extends Species to 
 * be a particular form of species
 */
public class SpeciesA extends Species {
	private final int MAXLIFESPAN = 10;
	/**
	 * Constructor inherits from Species,
	 * but sets the fitness to the species
	 * specific value, the boolean type to
	 * true to represent species A, and the 
	 * boolean empty to false to denote that
	 * this object is not an emptySquare object
	 * @param x coordinate
	 * @param y coordinate
	 * @param g the current grid
	 */
	public SpeciesA(int x, int y, Grid g) {
		super(x,y,g);
		type = true;
		empty = false;
		speciesSymbol = "A";
		fitness = 0.8;
	}
	/**
	 * Randomly sets the creatures lifespan
	 * based on its MAXLIFETIME
	 */
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(MAXLIFESPAN);
	}
	/**
	 * Defines the String label for the 
	 * species to display in the 
	 * Sysout printout
	 */
	public void setSpeciesSymbol() {
		speciesSymbol = "A";
	}

	public double getFitness(){
		return fitness;
	}

}
