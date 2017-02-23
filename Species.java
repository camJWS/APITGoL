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
	protected boolean type;
//	protected double fitness;
	//protected Lock lock;
	
	public Species(int x, int y, Grid g) {
		grid = g;
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
		//fitness = 0.0;
		//grid.setElement(xcoord, ycoord, this);
		
		dimension = grid.getGridDimension();
//		gridTemp = new Species[dimension][dimension];
//		for (int i = 0; i < dimension; i++){
//			for (int j = 0; j < dimension; j++){
//				gridTemp[i][j] = grid.getGrid()[i][j];
//			}
//		}
		
		
		//empty = false;
	}
	
	public synchronized void run(){
		try {
			this.setSpeciesSymbol();
			this.setLifespan();
			//coords = this.initialise();
		//	grid.setElement(coords[0], coords[1], this);
		//	grid.setGrid(gridTemp);
		//	grid.printGrid();
	
			//System.out.println(this.getSpeciesSymbol() + " Lifetim = " + this.getLifeSpan());
			
			sleep(1000*this.getLifeSpan());
			grid.setElement(coords[0], coords[1], this.die());
			this.reproduce();
			
		
		//	grid.setGrid(gridTemp);
		//	grid.printGrid();
			return;
		} catch (InterruptedException e) {
			//e.printStackTrace();
			this.interrupt();
			return;
		}
		//System.out.println("Fitness: " + fitness);
		

	}
	
	//abstract double setFitness();
	abstract void setLifespan();
	
//	public String[][] getNewGrid(){
//		return grid.getGrid();
//	}
	
	public int[] getNewCoords(){
		return coords;
	}
	
	abstract void reproduce();
//	public void reproduce(){
//		for (int i = 0; i < 3; i++){
//			for (int j = 0; j < 3; j++){
//				
//				//System.out.println(Math.random());
//				int xtemp = coords[0]-1+j;
//				int ytemp = coords[1]-1+i;
//				if (xtemp < 0){
//					xtemp = dimension-1;
//				}
//				else if (xtemp >= dimension){
//					xtemp = 0;
//				}
//				if (ytemp < 0){
//					ytemp = dimension-1;
//				}
//				else if (ytemp >= dimension){
//					ytemp = 0;
//				}
//				System.out.println("this.getFitness =" + this.getFitness());
//				System.out.println("that.getFitness = " + grid.getElement(xtemp, ytemp).getFitness());
//				if (grid.getElement(xtemp, ytemp).isEmpty()){
//					if (Math.random() <= this.getFitness()){
//						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//						System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
//					}
//				}
//				else/* if (!grid.getGrid()[xtemp][ytemp].isEmpty())*/{
//					//double otherFitness = grid.getElement(xtemp, ytemp).getFitness();
//					if (Math.random() <= (this.getFitness() - grid.getElement(xtemp, ytemp).getFitness())) {
//						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//						System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
//					}
//				}
////				else
////					System.out.println("error");
//			}
//		}
//		
//	}
	
	public void search(){
		
	}
	public void born(){
		
	}
	
	
	public EmptySquare die(){
		return new EmptySquare(coords[0],coords[1],grid);	
	}
	
	public void kill(){
		
	}
	
//	public int[] initialise(){
//		Random rand = new Random();
//		
//			int xcoord = rand.nextInt(dimension);
//			int ycoord = rand.nextInt(dimension);
//			coords[0] = xcoord;
//			coords[1] = ycoord;
//		return coords;
//	}
	
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
	abstract double getFitness();
//	public double getFitness(){
//		return fitness;
//	}
	
	public boolean isEmpty(){
		return empty;
	}
}
