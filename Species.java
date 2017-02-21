import java.util.Random;

public abstract class Species extends Thread {
	
	protected int lifespan;
	protected int[] coords;
	protected int xcoord, ycoord;
	protected int[][] xyPos;
	protected Grid grid;
	protected String[][] gridTemp;
	protected int dimension;
	protected String speciesSymbol;
	//protected Lock lock;
	
	public Species(Grid g) {
		grid = g;
		gridTemp = grid.getGrid();
		coords = new int[2];
		dimension = grid.getGridDimension();
	}
	
	public synchronized void run(){
		this.setSpeciesSymbol();
		coords = this.initialise();
		gridTemp[coords[0]][coords[1]] = this.getSpeciesSymbol();
		grid.setGrid(gridTemp);
	//	grid.printGrid();
		this.setLifespan();
		try {
			Thread.sleep(1000*this.getLifeSpan());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gridTemp[coords[0]][coords[1]] = "-";
		grid.setGrid(gridTemp);
	//	grid.printGrid();
		return;
		
	}
	
	public void run(int x, int y){
		this.setLifespan();
		this.initialise();
		try {
			this.lifetime();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.die();
		return;
		
	}
	
	//abstract double setFitness();
	abstract void setLifespan();
	
	public String[][] getNewGrid(){
		return grid.getGrid();
	}
	
	public int[] getNewCoords(){
		return coords;
	}
	
	public void reproduce(){
		
	}
	public void search(){
		
	}
	public void born(){
		
	}
	
	
	public void die(){
		
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
}
