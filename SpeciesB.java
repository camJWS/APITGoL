import java.util.Random;
/**
 * 
 */

/**
 * @author CamDev
 *
 */
public class SpeciesB extends Species {
	private final int MAXLIFESPAN = 5;
//	private final double fitness = 0.4;
	/**
	 * 
	 * @param dimension
	 */
	public SpeciesB(int x, int y, Grid g) {
		super(x,y,g);
		type = false;
		empty = false;
		speciesSymbol = "B";
		fitness = 0.4;
		//System.out.println("Fitness: B" + fitness);
	}

//	public double setFitness(){
//		return 0.0;
//	}
	
	public void setLifespan(){
		Random rand = new Random();
		
		lifespan = rand.nextInt(MAXLIFESPAN);
	}
	@Override
	public void setSpeciesSymbol() {
		speciesSymbol = "B";
	}
	
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
//			//	System.out.println("this.getFitness =" + this.getFitness());
//			//	System.out.println("that.getFitness = " + grid.getElement(xtemp, ytemp).getFitness());
//				if (grid.getElement(xtemp, ytemp).isEmpty()){
//					if (Math.random() <= this.getFitness()){
//						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//					//	System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
//					}
//				}
//				else/* if (!grid.getGrid()[xtemp][ytemp].isEmpty())*/{
//					//double otherFitness = grid.getElement(xtemp, ytemp).getFitness();
//					if (Math.random() <= (this.getFitness() - grid.getElement(xtemp, ytemp).getFitness())) {
//						grid.createNewCreature(xtemp, ytemp, this.getSpeciesSymbol());
//					//	System.out.println("Testing: "/* + grid.getElement(xtemp, ytemp).getSpeciesSymbol()*/);
//					}
//				}
////				else
////					System.out.println("error");
//			}
//		}
//		
//	}
	public double getFitness(){
		return fitness;
	}

}
