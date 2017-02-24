import java.util.ArrayList;
import java.util.Random;
/**
 * Grid initialises the grid, and runs
 * the grid visualisation on its own Thread  
 * @author Cameron Shanks
 *
 */
public class Grid extends Thread {
	private Species[][] speciesGrid;
	private int[][] posList; 
	private final int GRIDSIZE;
	private final int NUMDIMENSIONS = 2;
	private final int NUMINITIAL;
	private ArrayList<SpeciesA> creatureListA;
	private ArrayList<SpeciesB> creatureListB;
	private final boolean WRAPAROUND;
	
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
	
	public void run(){
		for (int i = 0; i < 1000; i++){
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

	public void setGrid(Species[][] g){
		speciesGrid = g;
	}
	
	public void printGrid(){
		for (int i = 0; i < GRIDSIZE; i++){
			System.out.print("\n");
			for (int j = 0; j < GRIDSIZE; j++){
				System.out.print(speciesGrid[i][j].getSpeciesSymbol() + " ");
			}
		}
		System.out.print("\n");
	}
	
	private boolean checkCoords(int[][] input){
		for (int i = 0; i < NUMINITIAL; i++){
			for  (int j = 0; j < NUMINITIAL && j != i; j++){
				//System.out.println("("+ input[i][0] + "," + input[i][1] + ")\n(" + input[j][0] + "," + input[j][1] + ")\n");
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
	
	public void startGame(){
		posList = new int[2*NUMINITIAL][NUMDIMENSIONS];
		int[] xyPosA = new int[NUMDIMENSIONS];
		int[] xyPosB = new int[NUMDIMENSIONS];
		//posB = new int[NUMINITIAL][NUMDIMENSIONS];
		for (int i = 0; i < NUMINITIAL; i++){
			xyPosA = getInitialCoords();
					//	System.out.println("A Coordinate " + i + " (" + xyPosA[0] + "," + xyPosA[1]+")");
			xyPosB = getInitialCoords();
					//	System.out.println("B Coordinate " + i + " (" + xyPosB[0] + "," + xyPosB[1]+")");
			
			posList[i][0] = xyPosA[0];
			posList[i][1] = xyPosA[1];
			posList[i+NUMINITIAL][0] = xyPosB[0];
			posList[i+NUMINITIAL][1] = xyPosB[1];
		}
			
		if (this.checkCoords(posList)){
			//System.out.println("coordinates good");
			for (int i = 0; i < NUMINITIAL; i++){
				creatureListA.add(new SpeciesA(posList[i][0], posList[i][1], this));
				creatureListA.get(i).start();
				creatureListB.add(new SpeciesB(posList[i+NUMINITIAL][0], posList[i+NUMINITIAL][1], this));
				creatureListB.get(i).start();
			//	System.out.println("A Fitness: " + creatureListA.get(i).getFitness() + ", coordinates (" + creatureListA.get(i).getCoords()[0] + "," + creatureListA.get(i).getCoords()[1] + ")" );
				
			//	System.out.println("B Fitness: " + creatureListB.get(i).getFitness() + ", coordinates (" + creatureListB.get(i).getCoords()[0] + "," + creatureListB.get(i).getCoords()[1] + ")" );
				//return;
			}
		}
		else{
			System.err.println("DUPLICATE Coordinates...");
			this.startGame();
			return;
		}
		
		
	}
	
	public int[] getInitialCoords(){
		Random rand = new Random();
		int[] coords = new int[NUMDIMENSIONS];
		int xcoord = rand.nextInt(GRIDSIZE-1);
		int ycoord = rand.nextInt(GRIDSIZE-1);
		coords[0] = xcoord;
		coords[1] = ycoord;
		return coords;
	}
	
	public boolean isWrapAround(){
		return WRAPAROUND;
	}

	
}
