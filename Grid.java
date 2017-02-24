import java.util.ArrayList;
import java.util.Random;
/**
 * Grid initialises the grid, and runs
 * the grid visualisation on its own Thread  
 * @author Cameron Shanks
 *
 */
public class Grid extends Thread {
	private Species[][] speciesGrid;			// Grid of species objects
	private int[][] posList; 					// List of 2D coordinates for initial positions
	private final int GRIDSIZE;					// The size of the square grid
	private final int NUMDIMENSIONS = 2;		// Currently set to only work in 2D
	private final int NUMINITIAL;				// Number of initial creatures per species
	private ArrayList<SpeciesA> creatureListA;	// List containing all Species A creatures
	private ArrayList<SpeciesB> creatureListB;	// List containing all Species B creatures
	private final boolean WRAPAROUND;			// Boolean to switch between wrap around/fixed border grid mode
	private final int MAXRUNTIME = 1000;		// The maximum time the programme will run for in s
	/**
	 * Grid Constructor sets up lists to contain creatures and 
	 * the grid to which they will be assigned elements.
	 * Initialises each square to contain an 'EmptySquare' 
	 * object
	 * @param d sqrt of number of elements in grid
	 * @param n number of initial creatures per species
	 * @param w boolean wraparound switch
	 */
	public Grid(int d, int n, boolean w){
		GRIDSIZE = d;
		NUMINITIAL = n;
		creatureListA = new ArrayList<SpeciesA>();
		creatureListB = new ArrayList<SpeciesB>();
		WRAPAROUND = w;
		speciesGrid = new Species[GRIDSIZE][GRIDSIZE]; 
		for (int i = 0; i < GRIDSIZE; i++){
			for (int j = 0; j < GRIDSIZE; j++){
				speciesGrid[i][j] = new EmptySquare(i,j,this);
			}
		}
	}
	/**
	 * Run method prints the grid 1000 times, printing
	 * once every half second
	 * Uses MAXRUNTIME to define how long the program will
	 * print for until terminating 
	 */
	public void run(){
		for (int i = 0; i < 2*MAXRUNTIME; i++){
			this.printGrid();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	public Species[][] getSpeciesGrid() {
		return speciesGrid;
	}

	public void setSpeciesGrid(Species[][] speciesGrid) {
		this.speciesGrid = speciesGrid;
	}

	public int[][] getPosList() {
		return posList;
	}

	public void setPosList(int[][] posList) {
		this.posList = posList;
	}

	public ArrayList<SpeciesA> getCreatureListA() {
		return creatureListA;
	}

	public void setCreatureListA(ArrayList<SpeciesA> creatureListA) {
		this.creatureListA = creatureListA;
	}

	public ArrayList<SpeciesB> getCreatureListB() {
		return creatureListB;
	}

	public void setCreatureListB(ArrayList<SpeciesB> creatureListB) {
		this.creatureListB = creatureListB;
	}

	public int getGRIDSIZE() {
		return GRIDSIZE;
	}

	public int getNUMDIMENSIONS() {
		return NUMDIMENSIONS;
	}

	public int getNUMINITIAL() {
		return NUMINITIAL;
	}
	
	/**
	 * Method to update the Species grid
	 * with the latest version
	 * @param g the new grid
	 */
	public void setGrid(Species[][] g){
		speciesGrid = g;
	}
	/**
	 * Reads the current Grid and prints each
	 * element's Species object's symbol to the
	 * System out
	 */
	public void printGrid(){
		for (int i = 0; i < GRIDSIZE; i++){
			System.out.print("\n");
			for (int j = 0; j < GRIDSIZE; j++){
				System.out.print(speciesGrid[i][j].getSpeciesSymbol() + " ");
			}
		}
		System.out.print("\n");
	}
	/**
	 * Checks whether the initial randomly chosen
	 * coordinates contain any duplicates
	 * @param input list of initial coordinates 
	 * @return True if coordinates ok, false otherwise
	 */
	private boolean checkCoords(int[][] input){
		for (int i = 0; i < NUMINITIAL; i++){
			for  (int j = 0; j < NUMINITIAL && j != i; j++){
				if (input[i][0] == input[j][0] && input[i][1] == input[j][1]){
					return false;
				}
				else
					continue;
			}
		}
		return true;
	}
	
	public Species[][] getGrid(){
		return speciesGrid;
	}
	
	public void setElement(int x, int y, Species s){
		speciesGrid[x][y] = s;
	}
	
	public Species getElement(int x, int y){
		return speciesGrid[x][y];
	}
	
	public int getGridDimension(){
		return GRIDSIZE;
		
	}
	/**
	 * Creates a new creature of type A or B, sets the 
	 * corresponding grid element with the new creature 
	 * and starts the thread 
	 * @param x x position
	 * @param y y position
	 * @param type String 'A' or 'B' to determine type 
	 * of creature
	 */
	public void createNewCreature(int x, int y, String type){
		Species t = null;
		if (type.equals("A")){
			t = new SpeciesA(x,y,this);
		}
		else if (type.equals("B")){
			t = new SpeciesB(x,y,this);
		}
		this.setElement(x, y, t);
		t.start();
		return;				
	}
	/**
	 * Starts the game by calling the getInitialCoords method
	 * to provide random coordinates, checks for dupes
	 * and then creates requested number of creature
	 * threads and starts them running
	 */
	public void startGame(){
		posList = new int[2*NUMINITIAL][NUMDIMENSIONS];
		int[] xyPosA = new int[NUMDIMENSIONS];
		int[] xyPosB = new int[NUMDIMENSIONS];
		
		for (int i = 0; i < NUMINITIAL; i++){
			xyPosA = getInitialCoords();
			xyPosB = getInitialCoords();
			
			posList[i][0] = xyPosA[0];
			posList[i][1] = xyPosA[1];
			posList[i+NUMINITIAL][0] = xyPosB[0];
			posList[i+NUMINITIAL][1] = xyPosB[1];
		}
			
		if (this.checkCoords(posList)){
			for (int i = 0; i < NUMINITIAL; i++){
				creatureListA.add(new SpeciesA(posList[i][0], posList[i][1], this));
				creatureListA.get(i).start();
				creatureListB.add(new SpeciesB(posList[i+NUMINITIAL][0], posList[i+NUMINITIAL][1], this));
				creatureListB.get(i).start();
			}
		}
		else{
			System.err.println("DUPLICATE Coordinates...");
			this.startGame();
			return;
		}
		
		
	}
	/**
	 * Generates random set of 2D coordinates
	 * @return coords integer tuple containing cart coords
	 */
	public int[] getInitialCoords(){
		Random rand = new Random();
		int[] coords = new int[NUMDIMENSIONS];
		int xcoord = rand.nextInt(GRIDSIZE-1);
		int ycoord = rand.nextInt(GRIDSIZE-1);
		coords[0] = xcoord;
		coords[1] = ycoord;
		return coords;
	}
	/**
	 * Checks whether the wraparound mode has been selected
	 * via the boolean
	 * @return boolean WRAPAROUND
	 * returns true if wraparound selected, otherwise false 
	 */
	public boolean isWrapAround(){
		return WRAPAROUND;
	}

	
}
