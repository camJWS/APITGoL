import java.util.Random;
/**
 * Subclass that extends Species to 
 * be a particular form of species
 * @author Cameron Shanks
 */
public class SpeciesB extends Species {
	private final int MAXLIFESPAN = 5;
	/**
	 * Constructor inherits from Species,
	 * but sets the fitness to the species
	 * specific value, the boolean type to
	 * false to represent species B, and the 
	 * boolean empty to false to denote that
	 * this object is not an emptySquare object
	 * @param x coordinate
	 * @param y coordinate
	 * @param g the current grid
	 */
	public SpeciesB(int x, int y, Grid g) {
		super(x,y,g);
		type = false;
		empty = false;
		speciesSymbol = "B";
		fitness = 0.4;
		alive = true;
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
		speciesSymbol = "B";
	}

	public double getFitness(){
		return fitness;
	}

}
