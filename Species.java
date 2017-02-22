import java.util.Random;

public abstract class Species extends Thread {
	
	protected int lifespan;
	protected int[] coords;
	protected int xcoord, ycoord;
	protected int[][] xyPos;
	protected Grid grid;
	protected Species[][] gridTemp;
	protected int dimension;
	protected String speciesSymbol;
	protected boolean empty;
	//protected Lock lock;
	
	public Species(Grid g) {
		grid = g;
		coords = new int[2];
		dimension = grid.getGridDimension();
		gridTemp = new Species[dimension][dimension];
		for (int i = 0; i < dimension; i++){
			for (int j = 0; j < dimension; j++){
				gridTemp[i][j] = grid.getGrid()[i][j];
			}
		}
		
		
		empty = false;
	}
	
	public Species(int x, int y){
		xcoord = x;
		ycoord = y;
		speciesSymbol = "-";
		empty = true;
		coords = new int[2];
		
	}
	
	public synchronized void run(){
		this.setSpeciesSymbol();
		this.setLifespan();
		coords = this.initialise();
		grid.setElement(coords[0], coords[1], this);
	//	grid.setGrid(gridTemp);
	//	grid.printGrid();

		//System.out.println(this.getSpeciesSymbol() + " Lifetim = " + this.getLifeSpan());
		try {
			sleep(1000*this.getLifeSpan());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		grid.setElement(coords[0], coords[1], this.die());
	//	grid.setGrid(gridTemp);
	//	grid.printGrid();
		return;
	}
	
	//abstract double setFitness();
	abstract void setLifespan();
	
//	public String[][] getNewGrid(){
//		return grid.getGrid();
//	}
	
	public int[] getNewCoords(){
		return coords;
	}
	
	public void reproduce(double fitness){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (gridTemp[coords[0]-1+j][coords[1]-1+i].equals("-")){
					if (Math.random() <= fitness){
						// Code to reproduce
					}
				}
				else if (gridTemp[coords[0]-1+j][coords[1]-1+i].isEmpty()){
					
				}
			}
			
		}
		
	}
	public void search(){
		
	}
	public void born(){
		
	}
	
	
	public EmptySquare die(){
		return new EmptySquare(coords[0],coords[1]);
		
	}
	
	public void kill(){
		
	}
	public int[] initialise(){
		
		Random rand = new Random();
		
		//for (int i = 0; i < 1; i++){
		//	String creature = "a"+i;

			int xcoord = rand.nextInt(dimension);
			int ycoord = rand.nextInt(dimension);
			coords[0] = xcoord;
			coords[1] = ycoord;
			//Thread creature = new SpeciesA(xcoordA, ycoordA);
			
//			xyPos[i][0] = xcoord;
//			xyPos[i][1] = ycoord;
			
			//grid.getGrid()[xcoord][ycoord] = this.getSpeciesSymbol();
		//}
		return coords;
	}
	
	abstract void setSpeciesSymbol();
	
	public String getSpeciesSymbol(){
		return speciesSymbol;
	}
	
	public void lifetime() throws InterruptedException{
		this.sleep(lifespan*1000);
	}
	
	public int getLifeSpan(){
		return lifespan;
	}
	
	public int[] getCoords(){
		int[] coords = {xcoord,ycoord};
		return coords;
	}
	
	private boolean checkCoords(int[][] input){
		for (int i = 0; i < 5; i++){
			for  (int j = 0; j < 5 && j != i; j++)
				if (input[i][0] == input[j][0] && input[i][1] == input[j][1]){
					return false;
				}
				else
					continue;
		}
		return true;
	}
	
	public boolean isEmpty(){
		return empty;
	}
}
