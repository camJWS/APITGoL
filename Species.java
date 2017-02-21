import java.util.Random;

public abstract class Species extends Thread {
	
	protected int lifespan;
	protected int[] coords;
	protected int xcoord, ycoord;
	protected int[][] posA, posB; 
	//protected Lock lock;
	
	public Species(int x, int y) {
		xcoord = x;
		ycoord = y;
		coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
	}
	
	public Species() {
//		Random rand = new Random();
//		xcoord = rand.nextInt(dimension);
//		ycoord = rand.nextInt(dimension);
	}
	
	public void run(){
		this.setLifespan();
		try {
			this.lifetime();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.die();
		return;
		
	}
	
	public void run(int x, int y){
		this.setLifespan();
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
	public void initialise(){
		
	}
	
	public void lifetime() throws InterruptedException{
		this.sleep(lifespan*1000);
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
