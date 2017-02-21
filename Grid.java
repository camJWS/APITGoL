import java.util.Random;

public class Grid {
	private String[][] gridMatrix;
	private int rows;
	private int cols;
	private int[][] posA, posB; 
	private int dimension;
	private SpeciesA[] listA;
	private SpeciesB[] listB;
	
	public Grid(int r, int c){
		rows = r;
		cols = c;
		dimension = r;
		gridMatrix = new String[rows][cols]; 
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				gridMatrix[i][j] = "-";
			}
		}
		posA = new int[5][2];
		posB = new int[5][2];
		
		listA = new SpeciesA[5];
		listB = new SpeciesB[5];
		
		if(this.gridInit(rows)){
			this.gridInit(rows);
			this.printGrid();
			for (int i = 0; i < 5; i++){
				listA[i] = new SpeciesA(posA[i][0], posA[i][1]);
				listB[i] = new SpeciesB(posB[i][0], posB[i][1]);
				listA[i].start();
				listB[i].start();
				while (listA[i].isAlive()){
					gridMatrix[posA[i][0]][posA[i][1]] = "A";
					
				}
				this.printGrid();
					
				while(listB[i].isAlive()){
					gridMatrix[posB[i][0]][posB[i][1]] = "B";
				}
				this.printGrid();
				
				this.printGrid();
				
				gridMatrix[posA[i][0]][posA[i][1]] = "-";
				
				gridMatrix[posB[i][0]][posB[i][1]] = "-";
				
				this.printGrid();
			}
			
		}
		else{
			System.out.println("Duplicated coords. Stop.");
//			this.gridInit(rows);
//			this.printGrid();
//			for (int i = 0; i < 5; i++){
//				listA[i] = new SpeciesA(posA[i][0], posA[i][1]);
//				listB[i] = new SpeciesB(posB[i][0], posB[i][1]);
//				listA[i].start();
//				try {
//					listA[i].wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				gridMatrix[posA[i][0]][posA[i][1]] = "-";
//				listB[i].start();
//				try {
//					listB[i].wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				gridMatrix[posB[i][0]][posB[i][1]] = "-";
//			}
//			this.printGrid();
		}
			
	}
	
	public boolean gridInit(int dimension){
		
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++){
			String creature = "a"+i;
			int xcoordA = rand.nextInt(dimension);
			int ycoordA = rand.nextInt(dimension);
			
			//Thread creature = new SpeciesA(xcoordA, ycoordA);
			
			posA[i][0] = xcoordA;
			posA[i][1] = ycoordA;
			
			gridMatrix[xcoordA][ycoordA] = "A";
			int xcoordB = rand.nextInt(dimension);
			int ycoordB = rand.nextInt(dimension);
			gridMatrix[xcoordB][ycoordB] = "B";
			
			posB[i][0] = xcoordB;
			posB[i][1] = ycoordB;
		}
		return (this.checkCoords(posA) && this.checkCoords(posB));
	}
	
	public void printGrid(){
		for (int i = 0; i < rows; i++){
			System.out.print("\n");
			for (int j = 0; j < cols; j++){
				System.out.print(gridMatrix[i][j] + " ");
			}
		}
		//System.out.print(gridMatrix);
	}
	
	public void setBirth(int x, int y, String creature){
		gridMatrix[x][y] = creature;
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
