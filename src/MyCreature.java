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

  /* Empty constructor - might be a good idea here to put the code that 
   initialises the chromosome to some random state   
  
   Input: numPercept - number of percepts that creature will be receiving
          numAction - number of action output vector that creature will need
                      to produce on every turn
  */
  public MyCreature(int numPercepts, int numActions) {
	  chromosome = new float[16];
	  for(int i = 0; i < 16; i++) {
		  chromosome[i] = rand.nextFloat();
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
		actions[10] = 0.000001f;
		
		return actions;
  }
  
}