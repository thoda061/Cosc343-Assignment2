import cosc343.assig2.World;
import cosc343.assig2.Creature;
import java.util.*;

/**
* The MyWorld extends the cosc343 assignment 2 World.  Here you can set 
* some variables that control the simulations and override functions that
* generate populations of creatures that the World requires for its
* simulations.
*
* @author  
* @version 1.0
* @since   2017-04-05 
*/
public class MyWorld extends World {
 
  /* Here you can specify the number of turns in each simulation
   * and the number of generations that the genetic algorithm will 
   * execute.
  */
  private final int _numTurns = 100;
  private final int _numGenerations = 500;
  

  
  /* Constructor.  
   
     Input: griSize - the size of the world
            windowWidth - the width (in pixels) of the visualisation window
            windowHeight - the height (in pixels) of the visualisation window
            repeatableMode - if set to true, every simulation in each
                             generation will start from the same state
            perceptFormat - format of the percepts to use: choice of 1, 2, or 3
  */
  public MyWorld(int gridSize, int windowWidth, int windowHeight, boolean repeatableMode, int perceptFormat) {   
      // Initialise the parent class - don't remove this
      super(gridSize, windowWidth,  windowHeight, repeatableMode, perceptFormat);

      // Set the number of turns and generations
      this.setNumTurns(_numTurns);
      this.setNumGenerations(_numGenerations);
      
      
  }
 
  /* The main function for the MyWorld application

  */
  public static void main(String[] args) {
     // Here you can specify the grid size, window size and whether torun
     // in repeatable mode or not
     int gridSize = 24;
     int windowWidth =  1600;
     int windowHeight = 900;
     boolean repeatableMode = true;
     
      /* Here you can specify percept format to use - there are three to
         chose from: 1, 2, 3.  Refer to the Assignment2 instructions for
         explanation of the three percept formats.
      */
     int perceptFormat = 1;     
     
     // Instantiate MyWorld object.  The rest of the application is driven
     // from the window that will be displayed.
     MyWorld sim = new MyWorld(gridSize, windowWidth, windowHeight, repeatableMode, perceptFormat);
  }
  

  /* The MyWorld class must override this function, which is
     used to fetch a population of creatures at the beginning of the
     first simulation.  This is the place where you need to  generate
     a set of creatures with random behaviours.
  
     Input: numCreatures - this variable will tell you how many creatures
                           the world is expecting
                            
     Returns: An array of MyCreature objects - the World will expect numCreatures
              elements in that array     
  */  
  @Override
  public MyCreature[] firstGeneration(int numCreatures) {

    int numPercepts = this.expectedNumberofPercepts();
    int numActions = this.expectedNumberofActions();
      
    // This is just an example code.  You may replace this code with
    // your own that initialises an array of size numCreatures and creates
    // a population of your creatures
    MyCreature[] population = new MyCreature[numCreatures];
    for(int i=0;i<numCreatures;i++) {
        population[i] = new MyCreature(numPercepts, numActions);     
    }
    return population;
  }
  
  /* The MyWorld class must override this function, which is
     used to fetch the next generation of the creatures.  This World will
     proivde you with the old_generation of creatures, from which you can
     extract information relating to how they did in the previous simulation...
     and use them as parents for the new generation.
  
     Input: old_population_btc - the generation of old creatures before type casting. 
                              The World doesn't know about MyCreature type, only
                              its parent type Creature, so you will have to
                              typecast to MyCreatures.  These creatures 
                              have been simulated over and their state
                              can be queried to compute their fitness
            numCreatures - the number of elements in the old_population_btc
                           array
                        
                            
  Returns: An array of MyCreature objects - the World will expect numCreatures
           elements in that array.  This is the new population that will be
           use for the next simulation.  
  */  
@Override
public MyCreature[] nextGeneration(Creature[] old_population_btc, int numCreatures) {
    // Typcast old_population of Creatures to array of MyCreatures
   MyCreature[] old_population = (MyCreature[]) old_population_btc;
     // Create a new array for the new population
   MyCreature[] new_population = new MyCreature[numCreatures];
	  
   float[] fitness = new float[numCreatures];
     
     // Here is how you can get information about old creatures and how
     // well they did in the simulation
   float avgLifeTime=0f;
   int nSurvivors = 0;
   float avgEnegryLeft=0f;
   for(MyCreature creature : old_population) {
        // The energy of the creature.  This is zero if creature starved to
        // death, non-negative oterhwise.  If this number is zero, but the 
        // creature is dead, then this number gives the enrgy of the creature
        // at the time of death.
      avgEnegryLeft += creature.getEnergy();

        // This querry can tell you if the creature died during simulation
        // or not.  
      boolean dead = creature.isDead();
        
      if(dead) {
           // If the creature died during simulation, you can determine
           // its time of death (in turns)
         int timeOfDeath = creature.timeOfDeath();
         avgLifeTime += (float) timeOfDeath;
      } else {
         nSurvivors += 1;
         avgLifeTime += (float) _numTurns;
      }
   }
	
    //Fitness function
   for(int i = 0; i < numCreatures; i++) {
		MyCreature c = old_population[i];
		float multiplier;
		if(c.isDead()) {
			multiplier = (((float)c.getEnergy()+1)/100);
		} else {
		 multiplier = 1 + (((float)c.getEnergy()+1)/100);
		}
		if(c.isDead()) {
			fitness[i] = (float)c.timeOfDeath() * multiplier;
		} else {
			fitness[i] = (float)_numTurns * multiplier;
		}
	}
     
   float avgFit = 0f;
   for(int i =0; i < fitness.length; i++) {
      avgFit += fitness[i];
   }
     
     // Right now the information is used to print some stats...but you should
     // use this information to access creatures fitness.  It's up to you how
     // you define your fitness function.  You should add a print out or
     // some visual display of average fitness over generations.
   avgLifeTime /= (float) numCreatures;
   avgFit /= (float) numCreatures; 
   avgEnegryLeft /= numCreatures;
   System.out.println("Simulation stats:");
   System.out.println("  Survivors    : " + nSurvivors + " out of " + numCreatures);
   System.out.println("  Avg life time: " + avgLifeTime + " turns");
   System.out.println("  Avg enegry eft: " + avgEnegryLeft);
   System.out.println("  Avg fitness: " + avgFit);
	  
     // Having some way of measuring the fitness, you should implement a proper
     // parent selection method here and create a set of new creatures.  You need
     // to create numCreatures of the new creatures.  If you'd like to have
     // some elitism, you can use old creatures in the next generation.  This
     // example code uses all the creatures from the old generation in the
     // new generation.
     
   Object[][] fitPlusCreature = new Object[numCreatures][2];
     
   for(int i=0;i<numCreatures; i++) {
      fitPlusCreature[i][0] = old_population[i];
      fitPlusCreature[i][1] = fitness[i];
   }
     
     
   fitPlusCreature = selectionSort(fitPlusCreature, numCreatures, false);
     
   for(int i =0; i<fitPlusCreature.length; i++) {
      System.out.println(fitPlusCreature[i][1]);
   }
	
	for(int i = 0; i <fitPlusCreature.length; i++) {
		float[] chromo = ((MyCreature)fitPlusCreature[i][0]).chromosome;
		System.out.println(chromo[0] + "," + chromo[1] + "," + chromo[2] + "," + chromo[3] + "," +
				  chromo[4] + "," + chromo[5] + "," + chromo[6] + "," + chromo[7]  + "," + chromo[8]
					 + "," + chromo[9]  + "," + chromo[10] + "," + chromo[11] + "," + chromo[12]
					 + "," + chromo[13] + "," + chromo[14] + "," + chromo[15]);
	}
     
     
   for(int i =0; i<5; i++) {
      new_population[i] = (MyCreature)fitPlusCreature[i][0];
   }
     
   float fitTotal = 0;
	for(int i = 0; i < fitPlusCreature.length; i++) {
		fitTotal += (float)fitPlusCreature[i][1];
	}
	  
	fitPlusCreature = selectionSort(fitPlusCreature, numCreatures, true);
	
   for(int i=5;i<numCreatures; i++) {
		Random rand = new Random();
		
		float selector = rand.nextFloat();
		int parentPos1 = -1;
		int parentPos2 = -1;
		int currentPos = 0;
		float currentFitSum = 0;
		
		while(parentPos1 == -1) {
			currentFitSum += ((float)fitPlusCreature[currentPos][1])/fitTotal;
			if(currentFitSum > selector) {
				parentPos1 = currentPos;
			} else {
				currentPos++;
			}
		}
		
		do{
			currentPos = 0;
			currentFitSum = 0;
			selector = rand.nextFloat();
			
			if(parentPos1 == parentPos2){
				parentPos2 = -1;
			}

			while(parentPos2 == -1) {
				currentFitSum += ((float)fitPlusCreature[currentPos][1])/fitTotal;
				if(currentFitSum > selector) {
					parentPos2 = currentPos;
				} else {
					currentPos++;
				}
			}
		} while(parentPos2 == parentPos1);
		
		MyCreature child = new MyCreature(this.expectedNumberofPercepts(), this.expectedNumberofActions());

      /*for(int j = 0; j < child.chromosome.length/2; j++) {
         child.chromosome[j] = ((MyCreature)fitPlusCreature[parentPos1][0]).chromosome[j];
      }
      for(int j = child.chromosome.length/2; j < child.chromosome.length; j++) {
         child.chromosome[j] = ((MyCreature)fitPlusCreature[parentPos2][0]).chromosome[j];
      }*/
		
		for(int j = 0; j < child.chromosome.length; j++){
			float flip = rand.nextFloat();
			if(flip < 0.5) {
				child.chromosome[j] = ((MyCreature)fitPlusCreature[parentPos1][0]).chromosome[j];
			} else {
				child.chromosome[j] = ((MyCreature)fitPlusCreature[parentPos2][0]).chromosome[j];
			}
		}
		  
      new_population[i] = child;
		
		
		/*Random rand = new Random();
		int subsetStart = rand.nextInt(numCreatures-2);
		float parent1 = 0;
		float parent2 = 0;
		int parent1Pos = 0;
		int parent2Pos = 0;

		//Determin two fittest creatures from subset of 10
		for(int j = subsetStart; j < subsetStart+2; j++) {
					if((float)fitPlusCreature[j][1] > parent1) {
						 parent1 = (float)fitPlusCreature[j][1];
						 parent1Pos = j;
					} else if ((float)fitPlusCreature[j][1] > parent2) {
						parent2 = (float)fitPlusCreature[j][1];
						parent2Pos = j;
					}
		}


		MyCreature child = new MyCreature(this.expectedNumberofPercepts(), this.expectedNumberofActions());

        for(int j = 0; j < child.chromosome.length/2; j++) {
            child.chromosome[j] = ((MyCreature)fitPlusCreature[parent1Pos][0]).chromosome[j];
        }
        for(int j = child.chromosome.length/2; j < child.chromosome.length; j++) {
            child.chromosome[j] = ((MyCreature)fitPlusCreature[parent2Pos][0]).chromosome[j];
        }
		  
        new_population[i] = child;*/
     }
     
     
     // Return new population of cratures.
     return new_population;
  }
  
  //Sorts population of creature based on thier fitness
  public Object[][] selectionSort(Object[][] fitPlusCreature, int numCreatures, boolean ascending) {
      float small;
      int pSmall;
      Object[] pVal;
      for(int p = 0; p < (numCreatures - 1); p++) {
          small = (float)fitPlusCreature[p][1];
          pSmall = p;
          for(int i = p; i < numCreatures; i++) {
				 if(ascending) {
					if((float)fitPlusCreature[i][1] < small) {
						 small = (float)fitPlusCreature[i][1];
						 pSmall = i;
					}
				 } else {
					 if((float)fitPlusCreature[i][1] > small) {
						 small = (float)fitPlusCreature[i][1];
						 pSmall = i;
					}
				 }
          }
          pVal = fitPlusCreature[p];
          fitPlusCreature[p] = fitPlusCreature[pSmall];
          fitPlusCreature[pSmall] = pVal;
      }
      return fitPlusCreature;
  }
  
}