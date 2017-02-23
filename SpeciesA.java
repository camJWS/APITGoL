import java.util.Random;

/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesA extends Species {
	private final int maxLifespan = 10;
	private final double fitness = 0.8;
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesA(int x, int y, Grid g) {
		super(x,y,g);
		type = true;
		empty = false;
		speciesSymbol = "A";
		//System.out.println("Fitness A: " + fitness);
	}
	
//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(maxLifespan);
	}
	@Override
	public void setSpeciesSymbol() {
		speciesSymbol = "A";
	}
	
	public void reproduce(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				
				//System.out.println(Math.random());
				int xtemp = coords[0]-1+j;
				int ytemp = coords[1]-1+i;
				if (xtemp < 0){
					xtemp = dimension-1;
				}
				else if (xtemp >= dimension){
					xtemp = 0;
				}
				if (ytemp < 0){
					ytemp = dimension-1;
				}
				else if (ytemp >= dimension){
					ytemp = 0;
				}
			//	System.out.println("this.getFitness =" + this.getFitness());
			//	System.out.println("that.getFitness = " + grid.getElement(xtemp, ytemp).getFitness());
				if (grid.getElement(xtemp, ytemp).isEmpty()){
					grid.getElement(xtemp, ytemp).interrupt();
					if (Math.random() <= this.getFitness()){
						
						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
						this.interrupt();
			//			System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
					}
				}
				else/* if (!grid.getGrid()[xtemp][ytemp].isEmpty())*/{
					grid.getElement(xtemp, ytemp).interrupt();
					//double otherFitness = grid.getElement(xtemp, ytemp).getFitness();
					if (Math.random() <= (this.getFitness() - grid.getElement(xtemp, ytemp).getFitness())) {
						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
						this.interrupt();
				//		System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
					}
				}
//				else
//					System.out.println("error");
			}
		}
		
	}

	public double getFitness(){
		return fitness;
	}

}
