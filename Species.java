import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Species extends Thread {
	
	protected int lifespan;					// Lifespan of the creature
	protected int[] coords;					// Array containing the creature's coordinates
	protected int xcoord, ycoord;			// the x and y coordinates
	protected int[][] xyPos;				// 2D array to hold xy coordinates of all potential squares for reproduction
	protected Grid grid;					// The grid object
//	protected Species[][] gridTemp;			//
	protected final int GRIDSIZE;			// The size of the square grid (side)
	protected String speciesSymbol;			// The String used to describe the creature in the printout of the grid
	protected boolean empty;				// boolean used to determine whether the object is a creature or an Empty Square
	protected boolean type;					//	
	protected ReentrantLock gridLock;		// Lock for the creature's thread
	protected double fitness;				// Fitness var, initialised in the subclass SpeciesA/SpeciesB
	protected final int BIRTHRANGE = 3; 	// Sqrt Number of neighbouring squares creatures can reproduce into
	/**
	 * Constructs the Creature initialsing its vital
	 * stats and the necessary Thread lock
	 * @param x coordinate
	 * @param y coordinate
	 * @param g current grid
	 */
	public Species(int x, int y, Grid g) {
		grid = g;
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
		GRIDSIZE = grid.getGRIDSIZE();
		gridLock = new ReentrantLock();
	}
	/**
	 * Run method sets the species symbol
	 * determines creature's lifetime
	 * Locks the thread to update the shared
	 * grid resource with its coordinates
	 * Sleeps for specified lifetime (unless interrupted
	 * by a murderer, then thread is ended)
	 * At the end of its life, creature's thread again 
	 * is locked to update the shared grid
	 * Its symbol in 'middle' square is removed and replaced
	 * with EmptySquare, in order for the reproduce method to 
	 * search the area and create new creatures
	 * Parent creature thread is ended 
	 */
	public void run(){
		try{
			this.setSpeciesSymbol();
			this.setLifespan();
			gridLock.lock();
			try{
				grid.setElement(xcoord, ycoord, this);
			}
			finally{
				gridLock.unlock();
			}
			sleep(1000*this.getLifeSpan());
			gridLock.lock();
			try{
				grid.setElement(coords[0], coords[1], this.die());
				this.reproduce();
			}
			finally{
				gridLock.unlock();
			}
			return;
		} catch (InterruptedException e) {
			return;
		}
	}
	/**
	 * Abstract Method to set Lifespan is defined in the 
	 * subclass SpeciesA/SpeciesB
	 */
	abstract void setLifespan();
	
	public int[] getNewCoords(){
		return coords;
	}
	/**
	 * Reproduce Method loops over the area surrounding 
	 * the parent and assigns the new coordinates to the 
	 * xtemp ytemp variables
	 * These x y coordinates are checked to see if the 
	 * creature is at an edge, and if so, they are
	 * either transformed to wrap the grid around, or 
	 * they ignore the squares that are off the edge, 
	 * depending on which edge mode is being used
	 * Using these coordinates, the birth() method is 
	 * called to create new creatures at that point on
	 * the grid
	 */
	public void reproduce(){
		for (int i = 0; i < BIRTHRANGE; i++){
			for (int j = 0; j < BIRTHRANGE; j++){
				int xtemp = coords[0]-1+j;
				int ytemp = coords[1]-1+i;
				if (grid.isWrapAround()){
					if (xtemp == -1){
						xtemp = GRIDSIZE-1;
					}
					else if (xtemp == -2){
						xtemp = GRIDSIZE -2;
					}
					else if (xtemp == -3){
						xtemp = GRIDSIZE -3;
					}
					else if (xtemp == GRIDSIZE){
						xtemp = 0;
					}
					else if (xtemp == GRIDSIZE+1){
						xtemp = 1;
					}
					else if (xtemp == GRIDSIZE+2){
						xtemp = 2;
					}
					if (ytemp == -1){
						ytemp = GRIDSIZE-1;
					}
					else if (ytemp == -2){
						ytemp = GRIDSIZE -2;
					}
					else if (ytemp == -3){
						ytemp = GRIDSIZE -3;
					}
					else if (ytemp == GRIDSIZE){
						ytemp = 0;
					}
					else if (ytemp == GRIDSIZE+1){
						ytemp = 1;
					}
					else if (ytemp == GRIDSIZE+2){
						ytemp = 2;
					}
					this.birth(xtemp, ytemp);
				}
				
				else{ // is Not WRAPAROUND
					if (xtemp < 0 || xtemp >= GRIDSIZE || ytemp < 0 || ytemp >= GRIDSIZE){
						continue;
					}
					else{
						this.birth(xtemp, ytemp);
						
					}

				}
			}
			
				
		}
	}
	/**
	 * Checks the neighbouring object in the 
	 * specified coordinares for whether it is
	 * a living creature or an empty square
	 * If empty or with a living creature, 
	 * the empty square object is 
	 * interrupted and a creature is created
	 * with a randomised probability dependent 
	 * on the Species and the previous occupier
	 * @param x coordinate of new creature
	 * @param y coordinate of new creature
	 */
	public void birth(int x, int y){
		Species neighbour = grid.getElement(x, y);
		if (neighbour.isEmpty()){
			neighbour.interrupt();
			if (Math.random() <= this.getFitness()){
				grid.createNewCreature(x, y, this.getSpeciesSymbol());
				this.interrupt();
			}
		}
		else {
			neighbour.interrupt();
			if (Math.random() <= (this.getFitness() - neighbour.getFitness())) {
				grid.createNewCreature(x, y, this.getSpeciesSymbol());
				this.interrupt();
			}
		}
	}
	/**
	 * Method to replace the parent creature in
	 * its coordinate location with an EmptySquare
	 * object, leaving the square ready to be potentially
	 * repopulated
	 * @return EmptySquare object
	 */
	public EmptySquare die(){
		return new EmptySquare(coords[0],coords[1],grid);	
	}
	
	abstract void setSpeciesSymbol();
	
	public String getSpeciesSymbol(){
		return speciesSymbol;
	}
	
//	public void lifetime() throws InterruptedException{
//		this.sleep(lifespan*1000);
//	}
	
	public int getLifeSpan(){
		return lifespan;
	}
	
	public int[] getCoords(){
		int[] coords = {xcoord,ycoord};
		return coords;
	}
	
	abstract double getFitness();
	/**
	 * Returns boolean signalling
	 * whether this object is a creature
	 * or an empty square object
	 * @return
	 */
	public boolean isEmpty(){
		return empty;
	}
}
