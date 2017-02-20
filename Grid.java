import java.util.Random;

public class Grid {
	private String[][] gridMatrix;
	private int rows;
	private int cols;
	private int[][] posA, posB; 
	private int dimension;
	
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
		
		if(this.gridInit(rows))
			this.printGrid();
		else
			this.gridInit(rows);
	}
	
	public boolean gridInit(int dimension){
		
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++){
			
			int xcoordA = rand.nextInt(dimension);
			int ycoordA = rand.nextInt(dimension);
			
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
