import java.util.Random;

public class Grid extends Thread {
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
	}
	
	public void run(){
		for (int i = 0; i < 21; i++){
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
	
	public void setGrid(String[][] g){
		gridMatrix = g;
	}
	
	public void printGrid(){
		for (int i = 0; i < rows; i++){
			System.out.print("\n");
			for (int j = 0; j < cols; j++){
				System.out.print(gridMatrix[i][j] + " ");
			}
		}
		System.out.print("\n");
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
	
	public String[][] getGrid(){
		return gridMatrix;
	}
	
	public int getGridDimension(){
		return dimension;
		
	}

	
}
