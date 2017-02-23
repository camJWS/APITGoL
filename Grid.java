import java.util.ArrayList;
import java.util.Random;

public class Grid extends Thread {
	private Species[][] gridMatrix;
	private int rows;
	private int cols;
	private int[][] posA, posB; 
	private int dimension;
	private SpeciesA[] arrayA;
	private SpeciesB[] arrayB;
	private ArrayList<SpeciesA> listA;
	private ArrayList<SpeciesB> listB;
	
	public Grid(int r, int c){
		rows = r;
		cols = c;
		dimension = r;
		
		listA = new ArrayList<SpeciesA>();
		listB = new ArrayList<SpeciesB>();
		
		gridMatrix = new Species[rows][cols]; 
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				gridMatrix[i][j] = new EmptySquare(i,j,this);
			}
		}
	}
	
	public void run(){
		for (int i = 0; i < 100; i++){
			this.printGrid();
			try {
				this.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
	
	public void setGrid(Species[][] g){
		gridMatrix = g;
	}
	
	public void printGrid(){
		for (int i = 0; i < rows; i++){
			System.out.print("\n");
			for (int j = 0; j < cols; j++){
				System.out.print(gridMatrix[i][j].getSpeciesSymbol() + " ");
			}
		}
		System.out.print("\n");
	}
	
//	public void setBirth(int x, int y, String creature){
//		gridMatrix[x][y] = creature;
//	}
	
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
	
	public Species[][] getGrid(){
		return gridMatrix;
	}
	
	public void setElement(int x, int y, Species s){
		gridMatrix[x][y] = s;
	}
	
	public Species getElement(int x, int y){
		return gridMatrix[x][y];
	}
	
	public int getGridDimension(){
		return dimension;
		
	}
	
	public void createNewCreature(int x, int y, String type){
		if (type.equals("A")){
			//System.out.println("This is type A");
			Species t = new SpeciesA(x,y,this);
			this.setElement(x, y, t);
			t.start();
//			listA.add(new SpeciesA(x,y,this));
			return;
		}
		else if (type.equals("B")){
			//System.out.println("This is type B");
			//listB.add(new SpeciesB(x,y,this));
			Species t = new SpeciesB(x,y,this);
			this.setElement(x, y, t);
			t.start();
			return;		
		}	
//		else{
//			return new EmptySquare(x,y,this);
//		}
			
	}
	
	public void startGame(int numInitCreatures){
		for (int i = 0; i < numInitCreatures; i++){
			listA.add(new SpeciesA(this.getinitialCoords()[0], this.getinitialCoords()[1], this));
			listB.add(new SpeciesB(this.getinitialCoords()[0], this.getinitialCoords()[1], this));
			listA.get(i).start();
			listB.get(i).start();
		}
		
	}
	
	public int[] getinitialCoords(){
		Random rand = new Random();
		int[] coords = new int[2];
		int xcoord = rand.nextInt(dimension);
		int ycoord = rand.nextInt(dimension);
		coords[0] = xcoord;
		coords[1] = ycoord;
		return coords;
	}

	
}
