import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Species extends Thread {
	
	protected int lifespan;
	protected int[] coords;
	protected int xcoord, ycoord;
	protected int[][] xyPos;
	protected Grid grid;
	protected Species[][] gridTemp;
	protected final int GRIDSIZE;
	protected String speciesSymbol;
	protected boolean empty;
	protected boolean type;
	protected ReentrantLock gridLock;	
	protected double fitness;
	protected final int BIRTHRANGE = 3; // Number of neighbouring squares creatures ca
	
	public Species(int x, int y, Grid g) {
		grid = g;
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
		GRIDSIZE = grid.getGRIDSIZE();
		grid.setElement(xcoord, ycoord, this);
		//dimension = grid.getGridDimension();
		gridLock = new ReentrantLock();
	}
	
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
	
	abstract void setLifespan();
	
	public int[] getNewCoords(){
		return coords;
	}
	
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
//					Species neighbour = grid.getElement(xtemp, ytemp);
//					if (neighbour.isEmpty()){
//						neighbour.interrupt();
//						if (Math.random() <= this.getFitness()){
//							grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//							this.interrupt();
//						}
//					}
//					else {
//						neighbour.interrupt();
//						if (Math.random() <= (this.getFitness() - neighbour.getFitness())) {
//							grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//							this.interrupt();
//						}
//					}
				//}
				}
				
				else{ // is Not WRAPAROUND
					if (xtemp < 0 || xtemp >= GRIDSIZE || ytemp < 0 || ytemp >= GRIDSIZE){
						continue;
					}
					else{
						this.birth(xtemp, ytemp);
						
					}
				
				
				//int[][] xyTemp = this.searchWrapAround();
				
			//	for (int i = 0; i < BIRTHRANGE*BIRTHRANGE; i++){
//					Species neighbour = grid.getElement(xtemp, ytemp);
//					if (neighbour.isEmpty()){
//						neighbour.interrupt();
//						if (Math.random() <= this.getFitness()){
//							grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//							this.interrupt();
//						}
//					}
//					else {
//						neighbour.interrupt();
//						if (Math.random() <= (this.getFitness() - neighbour.getFitness())) {
//							grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//							this.interrupt();
//						}
					}
				}
			
				
			}
		}
		
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
		
	//}
//	}
	
//	public int[][] searchWrapAround(){
//		int [][] xyTemp = new int[BIRTHRANGE*BIRTHRANGE][grid.getNumDimensions()];
//		for (int i = 0; i < BIRTHRANGE; i++){
//			for (int j = 0; j < BIRTHRANGE; j++){
//				xyTemp[i+j][0] = (coords[0]-1+j);
//				xyTemp[i+j][1] = (coords[1]-1+i);
//				if (xyTemp[i+j][0] < 0){
//					xyTemp[i+j][0] = dimension-1;
//				}
//				else if (xyTemp[i+j][0] >= dimension){
//					xyTemp[i+j][0] = 0;
//				}
//				if (xyTemp[i+j][1] < 0){
//					xyTemp[i+j][1] = dimension-1;
//				}
//				else if (xyTemp[i+j][1] >= dimension){
//					xyTemp[i+j][1] = 0;
//				}
//				
//			}
//			System.out.println(xyTemp[i][0] + " " + xyTemp[i][1]);
//		}
//		return xyTemp;
//	}
	
	public void born(){
		
	}
	
	
	public EmptySquare die(){
		return new EmptySquare(coords[0],coords[1],grid);	
	}
	
	public void kill(){
		
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
	
	abstract double getFitness();
	
	public boolean isEmpty(){
		return empty;
	}
}
