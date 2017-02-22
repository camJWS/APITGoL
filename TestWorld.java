import java.util.ArrayList;

public class TestWorld{
	
	private static Grid grid;
	
	public static void main(String[] args){
		final int initialA = 5;
		final int initialB = 5;
		int rows = 20;
		int cols = rows;
		ArrayList<SpeciesA> listA = new ArrayList<SpeciesA>();
		ArrayList<SpeciesB> listB = new ArrayList<SpeciesB>();
		
		

		grid = new Grid(rows, cols);
		grid.start();
		listA.add(new SpeciesA(grid));
		listB.add(new SpeciesB(grid));
		listA.get(0).start();
		listB.get(0).start();
	}
}
