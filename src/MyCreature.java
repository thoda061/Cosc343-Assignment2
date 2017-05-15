import cosc343.assig2.Creature;
import java.util.Random;

/**
* The MyCreate extends the cosc343 assignment 2 Creature.  Here you implement
* creatures chromosome and the agent function that maps creature percepts to
* actions.  
*
* @author  
* @version 1.0
* @since   2017-04-05 
*/
public class MyCreature extends Creature {

  // Random number generator
  Random rand = new Random();
  
  float[] chromosome;
  int searchDirection;

  /* Empty constructor - might be a good idea here to put the code that 
   initialises the chromosome to some random state   
  
   Input: numPercept - number of percepts that creature will be receiving
          numAction - number of action output vector that creature will need
                      to produce on every turn
  */
  public MyCreature(int numPercepts, int numActions) {
	  chromosome = new float[48];
	  
	  //Iteration 3
	  for(int i = 0; i < 48; i++) {
		  chromosome[i] = rand.nextFloat();
	  }
	  
	  //Iteration 2
	  /*for(int i = 0; i < 49; i++) {
		  chromosome[i] = rand.nextFloat();
	  }*/
	  /*
	  //Iteration 1
	  chromosome = new float[20];
	  for(int i = 0; i < 20; i++) {
		  chromosome[i] = rand.nextFloat();
	  }*/
	  float flip = rand.nextFloat();
	  if(flip < 0.5) {
		  searchDirection = rand.nextInt(4);
	  } else {
		  searchDirection = rand.nextInt(4) + 5;
	  }
  }
  
  /* This function must be overridden by MyCreature, because it implements
     the AgentFunction which controls creature behavoiur.  This behaviour
     should be governed by a model (that you need to come up with) that is
     parameterise by the chromosome.  
  
     Input: percepts - an array of percepts
            numPercepts - the size of the array of percepts depend on the percept
                          chosen
            numExpectedAction - this number tells you what the expected size
                                of the returned array of percepts should bes
     Returns: an array of actions 
  */
  @Override
  public float[] AgentFunction(int[] percepts, int numPercepts, int numExpectedActions) {
      
      // This is where your chromosome gives rise to the model that maps
      // percepts to actions.  This function governs your creature's behaviour.
      // You need to figure out what model you want to use, and how you're going
      // to encode its parameters in a chromosome.
      
      // At the moment, the actions are chosen completely at random, ignoring
      // the percepts.  You need to replace this code.
		
      float actions[] = new float[numExpectedActions];
		for(float i: actions) {
			i = 0.0f;
		}
		
		//Iteration 3
		boolean goAwayMonster;
		boolean nearMonster = false;
		if(chromosome[0] > chromosome[1]) {
			goAwayMonster = true;
		} else {
			goAwayMonster = false;
		}
		
		for(int i = 0; i < 9; i++) {
			if(percepts[i] == 1) {
				nearMonster = true;
			}
		}
		
		if(nearMonster) {
			for(int i = 0; i < 9; i++) {
				if(percepts[i] == 1) {
					if(goAwayMonster) {
						actions[8-i] = chromosome[10-i];
					} else {
						actions[i] = chromosome[i+2];
					}
				}
			}
		}
		
		boolean goAwayCreature;
		boolean nearCreature = false;
		if(chromosome[11] > chromosome[12]) {
			goAwayCreature = true;
		} else {
			goAwayCreature = false;
		}
		
		for(int i = 9; i < 18; i++) {
			if(percepts[i] == 1) {
				nearCreature = true;
			}
		}
		
		if(nearCreature) {
			for(int i = 9; i < 18; i++) {
				if(percepts[i] == 1) {
					if(goAwayCreature) {
						if(actions[8-(i-9)] < chromosome[26-i]){
							actions[8-(i-9)] = chromosome[26-i];
						}
					} else {
						if(actions[i-9] < chromosome[i+4]){
							actions[i-9] = chromosome[i+4];
						}
					}
				}
			}
		}
		
		boolean goAwayGreenFood;
		boolean nearGreenFood = false;
		if(chromosome[22] > chromosome[23]) {
			goAwayGreenFood = true;
		} else {
			goAwayGreenFood = false;
		}
		
		for(int i = 18; i < 27; i++) {
			if(percepts[i] == 1) {
				nearGreenFood = true;
			}
		}
		
		if(nearGreenFood) {
			for(int i = 18; i < 27; i++) {
				if(percepts[i] == 1) {
					if(goAwayGreenFood) {
						if(actions[8-(i-18)] < chromosome[50-i]){
							actions[8-(i-18)] = chromosome[50-i];
						}
					} else {
						if(actions[i-18] < chromosome[i+6]){
							actions[i-18] = chromosome[i+6];
						}
					}
					if(i == 22) {
						if(actions[9] < chromosome[46]) {
							actions[9] = chromosome[46];
						}
					}
				}
			}
		}
		
		boolean goAwayRedFood;
		boolean nearRedFood = false;
		if(chromosome[33] > chromosome[34]) {
			goAwayRedFood = true;
		} else {
			goAwayRedFood = false;
		}
		
		for(int i = 18; i < 27; i++) {
			if(percepts[i] == 2) {
				nearRedFood = true;
			}
		}
		
		if(nearRedFood) {
			for(int i = 18; i < 27; i++) {
				if(percepts[i] == 2) {
					if(goAwayRedFood) {
						if(actions[8-(i-18)] < chromosome[61-i]){
							actions[8-(i-18)] = chromosome[61-i];
						}
					} else {
						if(actions[i-18] < chromosome[i+17]){
							actions[i-18] = chromosome[i+17];
						}
					}
					if(i == 22) {
						if(actions[9] < chromosome[47]) {
							actions[9] = chromosome[47];
						}
					}
				}
			}
		}
		
		if(chromosome[44] > chromosome[45]) {
			actions[10] = 0.000001f;
		}else if(actions[searchDirection] != 0) {
			actions[searchDirection] = 0.000001f;
		}
		
		
		//Iteration 2
		/*if(percepts[0] != 0 || percepts[1] != 0) {
			for(int i = 0; i < 9; i++) {
				if(chromosome[i] > actions[i]) {
					actions[i] = chromosome[i];
				}
			}
		}
		
		if(percepts[2] != 0 || percepts[3] != 0) {
			for(int i = 0; i < 9; i++) {
				if(chromosome[i+9] > actions[i]) {
					actions[i] = chromosome[i+9];
				}
			}
		}
		
		if(percepts[4] != 0 || percepts[5] != 0) {
			for(int i = 0; i < 9; i++) {
				if(chromosome[i+18] > actions[i]) {
					actions[i] = chromosome[i+18];
				}
			}
		}
		
		if(percepts[6] != 0) {
			for(int i = 0; i < 10; i++) {
				if(chromosome[i+27] > actions[i]) {
					actions[i] = chromosome[i+27];
				}
			}
		}
		
		if(percepts[7] != 0) {
			for(int i = 0; i < 10; i++) {
				if(chromosome[i+37] > actions[i]) {
					actions[i] = chromosome[i+37];
				}
			}
		}
		
		if(chromosome[47] > chromosome[48]) {
			actions[10] = 0.000001f;
		}else if(actions[searchDirection] != 0) {
			actions[searchDirection] = 0.000001f;
		}*/
		
		//Iteration 1
		/*
		//Determins whether to run away or go to monster
		if(percepts[0] != 0 || percepts[1] != 0) {
			String posistion = String.valueOf(percepts[0]) + String.valueOf(percepts[1]);
			float goTowards = (chromosome[0] + chromosome[1])/2;
			float goAway = (chromosome[2] + chromosome[3])/2;
			switch(posistion){
					case"0-1" :
						if(goTowards > goAway && goTowards > actions[3]) {
							actions[3] = goTowards;
						} else if (goAway > goTowards && goAway > actions[5]) {
							actions[5] = goAway;
						}
						break;
					case"01" :
						if(goTowards > goAway && goTowards > actions[5]) {
							actions[5] = goTowards;
						} else if (goAway > goTowards && goAway > actions[3]) {
							actions[3] = goAway;
						}
						break;
					case"-10" :
						if(goTowards > goAway && goTowards > actions[1]) {
							actions[1] = goTowards;
						} else if (goAway > goTowards && goAway > actions[7]) {
							actions[7] = goAway;
						}
						break;
					case"10" :
						if(goTowards > goAway && goTowards > actions[7]) {
							actions[7] = goTowards;
						} else if (goAway > goTowards && goAway > actions[1]) {
							actions[1] = goAway;
						}
						break;
					case"-1-1" :
						if(goTowards > goAway && goTowards > actions[0]) {
							actions[0] = goTowards;
						} else if (goAway > goTowards && goAway > actions[8]) {
							actions[8] = goAway;
						}
						break;
					case"1-1" :
						if(goTowards > goAway && goTowards > actions[6]) {
							actions[6] = goTowards;
						} else if (goAway > goTowards && goAway > actions[2]) {
							actions[2] = goAway;
						}
						break;
					case"-11" :
						if(goTowards > goAway && goTowards > actions[2]) {
							actions[2] = goTowards;
						} else if (goAway > goTowards && goAway > actions[6]) {
							actions[6] = goAway;
						}
						break;
					case"11" :
						if(goTowards > goAway && goTowards > actions[8]) {
							actions[8] = goTowards;
						} else if (goAway > goTowards && goAway > actions[0]) {
							actions[0] = goAway;
						}
						break;
			}
		}
		
		//Determins whether to run away or go to creature
		if(percepts[2] != 0 || percepts[3] != 0) {
			String posistion = String.valueOf(percepts[2]) + String.valueOf(percepts[3]);
			float goTowards = (chromosome[4] + chromosome[5])/2;
			float goAway = (chromosome[6] + chromosome[7])/2;
			switch(posistion){
					case"0-1" :
						if(goTowards > goAway && goTowards > actions[3]) {
							actions[3] = goTowards;
						} else if (goAway > goTowards && goAway > actions[5]) {
							actions[5] = goAway;
						}
						break;
					case"01" :
						if(goTowards > goAway && goTowards > actions[5]) {
							actions[5] = goTowards;
						} else if (goAway > goTowards && goAway > actions[3]) {
							actions[3] = goAway;
						}
						break;
					case"-10" :
						if(goTowards > goAway && goTowards > actions[1]) {
							actions[1] = goTowards;
						} else if (goAway > goTowards && goAway > actions[7]) {
							actions[7] = goAway;
						}
						break;
					case"10" :
						if(goTowards > goAway && goTowards > actions[7]) {
							actions[7] = goTowards;
						} else if (goAway > goTowards && goAway > actions[1]) {
							actions[1] = goAway;
						}
						break;
					case"-1-1" :
						if(goTowards > goAway && goTowards > actions[0]) {
							actions[0] = goTowards;
						} else if (goAway > goTowards && goAway > actions[8]) {
							actions[8] = goAway;
						}
						break;
					case"1-1" :
						if(goTowards > goAway && goTowards > actions[6]) {
							actions[6] = goTowards;
						} else if (goAway > goTowards && goAway > actions[2]) {
							actions[2] = goAway;
						}
						break;
					case"-11" :
						if(goTowards > goAway && goTowards > actions[2]) {
							actions[2] = goTowards;
						} else if (goAway > goTowards && goAway > actions[6]) {
							actions[6] = goAway;
						}
						break;
					case"11" :
						if(goTowards > goAway && goTowards > actions[8]) {
							actions[8] = goTowards;
						} else if (goAway > goTowards && goAway > actions[0]) {
							actions[0] = goAway;
						}
						break;
			}
		}
		
		//Determins whether to run away or go to food
		if(percepts[4] != 0 || percepts[5] != 0) {
			String posistion = String.valueOf(percepts[4]) + String.valueOf(percepts[5]);
			float goTowards = (chromosome[8] + chromosome[9])/2;
			float goAway = (chromosome[10] + chromosome[11])/2;
			switch(posistion){
					case"0-1" :
						if(goTowards > goAway && goTowards > actions[3]) {
							actions[3] = goTowards;
						} else if (goAway > goTowards && goAway > actions[5]) {
							actions[5] = goAway;
						}
						break;
					case"01" :
						if(goTowards > goAway && goTowards > actions[5]) {
							actions[5] = goTowards;
						} else if (goAway > goTowards && goAway > actions[3]) {
							actions[3] = goAway;
						}
						break;
					case"-10" :
						if(goTowards > goAway && goTowards > actions[1]) {
							actions[1] = goTowards;
						} else if (goAway > goTowards && goAway > actions[7]) {
							actions[7] = goAway;
						}
						break;
					case"10" :
						if(goTowards > goAway && goTowards > actions[7]) {
							actions[7] = goTowards;
						} else if (goAway > goTowards && goAway > actions[1]) {
							actions[1] = goAway;
						}
						break;
					case"-1-1" :
						if(goTowards > goAway && goTowards > actions[0]) {
							actions[0] = goTowards;
						} else if (goAway > goTowards && goAway > actions[8]) {
							actions[8] = goAway;
						}
						break;
					case"1-1" :
						if(goTowards > goAway && goTowards > actions[6]) {
							actions[6] = goTowards;
						} else if (goAway > goTowards && goAway > actions[2]) {
							actions[2] = goAway;
						}
						break;
					case"-11" :
						if(goTowards > goAway && goTowards > actions[2]) {
							actions[2] = goTowards;
						} else if (goAway > goTowards && goAway > actions[6]) {
							actions[6] = goAway;
						}
						break;
					case"11" :
						if(goTowards > goAway && goTowards > actions[8]) {
							actions[8] = goTowards;
						} else if (goAway > goTowards && goAway > actions[0]) {
							actions[0] = goAway;
						}
						break;
			}
		}
		
		if(percepts[6] == 1) {
			actions[9] = (chromosome[12] + chromosome[13])/2;
		}
		
		if(percepts[7] == 1) {
			actions[9] = (chromosome[14] + chromosome[15])/2;
		}
		
		//If all other action are 0, this ensures a random move.
		if((chromosome[16] +  chromosome[17])/2 > (chromosome[18] + chromosome[19])/2) {
			actions[10] = 0.000001f;
		}else if(actions[searchDirection] != 0) {
			actions[searchDirection] = 0.000001f;
		}
		*/
		
		return actions;
  }
  
}