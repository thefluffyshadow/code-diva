package tests;/*
Program: GAOneBits
Course: Bio-inspired Computing, Spring 2015
Author: J. Gurka (version 1)

Description:
A genetic algorithm (GA) program to illustrate the 
basic operations of GAs.  This program attempts to 
produce "bit strings" of all ones, from an initial
population of random bit strings.

Versions.
Version 1: Basic operations, parameters hard-coded, 
text output only.

Input: None.

Output: Tracking data of population progress 
(overall fitness & individual chromosomes).

Future work:
1. User-input values for GA parameters such as 
population size (and possibly command-line 
parameters?).
2. Visual display of fitness progress.

*/

import java.util.Random;  

public class GAOneBits {

	// GA parameters
	private final int POPULATION_SIZE,
				         INDIVIDUAL_SIZE,	   // one individual / ... 
                                          // ... one member of the population
				         GENERATION_MAX;	   // forced halting independent of fitness
	private final double MUTATION_RATE,    // chance for one individual to have a mutation
				            GOOD_PARENT_RATE, // what percentage of time a good parent is chosen rather than a random one
				            FITNESS_GOAL;     // system termination criteria
	
	private int[][] population, newPopulation;
	private double[] fitness, newFitness;	// one value per individual
	private double averageFitness;			// entire population
	private int generation, 					// counter
					crossoverPoint;            // division point of parents for copying "bits" to new child
	
	private Random rand;	// initial population, crossover point, parent selection

	public GAOneBits () {

		printMethod("constructor");
      	
      // hard-coded GA parameters
		POPULATION_SIZE = 25;  
		GENERATION_MAX = 200;
		MUTATION_RATE = 0.03;
		GOOD_PARENT_RATE = 0.8;
		INDIVIDUAL_SIZE = 20;
		FITNESS_GOAL = 0.95;	   	
		
		generation = 0;
		rand = new Random();
		
		population = new int[POPULATION_SIZE][INDIVIDUAL_SIZE];
		newPopulation = new int[POPULATION_SIZE][INDIVIDUAL_SIZE];
		fitness = new double[POPULATION_SIZE];
		newFitness = new double[POPULATION_SIZE];
		
	}  // default constructor

	public void outputGAParameters () {
		System.out.println ("Parameters:" +
					 	"\n   population size: "  + POPULATION_SIZE + 
						"\n   individual size: "  + INDIVIDUAL_SIZE + 
						"\n   generation count: " + GENERATION_MAX + 
						"\n   mutation rate: "    + MUTATION_RATE +
						"\n   good parent rate: " + GOOD_PARENT_RATE +
						"\n   fitness level: "    + FITNESS_GOAL +  
						"\n" 
							    );
	}  // outputProgramOverview
	
	public void createInitialPopulation () {
		
		printMethod("createInitialPopulation");
		
		// fill population with random ones and zeros
		for (int i = 0; i < POPULATION_SIZE; i++) {
			for (int j = 0; j < INDIVIDUAL_SIZE; j++) {
				population[i][j] = rand.nextInt(2);  // zero or one		
			}  // one individual in the population
		}  // entire population
		
		// calculate initial fitness
		for (int i = 0; i < POPULATION_SIZE; i++) {
			fitness[i] = calculateFitness(population[i]);
		}  
		double totalFitness = 0.0;
		for (int i = 0; i < POPULATION_SIZE; i++)
			totalFitness += fitness[i];
		averageFitness = totalFitness / POPULATION_SIZE;
		
		printPopulation(population, "Initial Population");
	
	}  // createInitialPopulation

	public int selectParent() {
		printMethod("selectParent");
		// select parent by mostly choosing above-average parents
		int parent;
		double totalFitness = 0, averageFitness;
		boolean found = false;
		
      // use fitness to select parent?
		if (rand.nextInt(POPULATION_SIZE) <= 
          POPULATION_SIZE * GOOD_PARENT_RATE) {
			// select by fitness - choose above-average parent
			// System.out.println("selecting above-average parent");
			for (int i = 0; i < POPULATION_SIZE; i++) 
				totalFitness += fitness[i];
			averageFitness = (double) totalFitness / POPULATION_SIZE;
			// find random above-average parent
			do {
				parent = rand.nextInt(POPULATION_SIZE);
				if (fitness[parent] >= averageFitness)
					found = true;
			} while (!found);
		}
		  else {  //  choose random parent, ignore fitness
			// System.out.println("selecting random parent");
		 	parent = rand.nextInt(POPULATION_SIZE); 
		}	  
		
		return parent;
	
	}  // select parent

	public double calculateFitness (int[] individual) {
		printMethod("calculateFitness");
		// fitness function: count one bits
		int fitness = 0;
		for (int i = 0; i < INDIVIDUAL_SIZE; i++)
			fitness += individual[i];
		return fitness;
	}

	public void runGA () {
		printMethod("runGA");
		generation = 1;
		while (generation < GENERATION_MAX && 
		       averageFitness < FITNESS_GOAL * INDIVIDUAL_SIZE) {	
			createNextGeneration();
			printPopulation(population, "Generation " + generation);
			generation++;
		}
		printPopulation(population, "Last population");
		if (generation == GENERATION_MAX)
			System.out.println("\n>> Stopped due to maximum generations reached, "
			                    + "generation: " + generation + ".\n");
		if (averageFitness >= FITNESS_GOAL * INDIVIDUAL_SIZE)
			System.out.println("\n>> Stopped due to fitness goal reached, " +	
			                   "generation: " + generation + ".\n");
      outputGAParameters();


	}  // runGA

	private void createNextGeneration() {
		printMethod("createNextGeneration");
		int[] parent1 , parent2, 
			   child = new int[INDIVIDUAL_SIZE]; 
		
		for (int i = 0; i < POPULATION_SIZE; i++) {

			parent1 = population[selectParent()];
			parent2 = population[selectParent()];

			crossoverPoint = rand.nextInt(INDIVIDUAL_SIZE);
			
			// create child by copying parent #1 up to crossover point,
			// then parent #2 from there to the end
			for (int j = 0; j < crossoverPoint; j++)
				child[j] = parent1[j];
			for (int j = crossoverPoint; j < INDIVIDUAL_SIZE; j++)
				child[j] = parent2[j];
			
			// output parents & initial child
			System.out.println("-----\nInitial new individual");
			System.out.print("   Parent 1: ");
			printIndividual(parent1); 
			System.out.print("\n   Parent 2: ");
			printIndividual(parent2);
			System.out.println("\n   crossover point: " + crossoverPoint);  
			System.out.print("   Child:    ");
			printIndividual(child);
			System.out.println();
         			
			// possibly alter child using mutation
			if (rand.nextDouble() <= MUTATION_RATE) {
				// flip one bit
				int position = rand.nextInt(INDIVIDUAL_SIZE);
            System.out.println("mutating, position = " + position);
				child[position] = child[position]==0 ? 1 : 0;
			   // output mutated child
			   System.out.println("After mutation");
			   System.out.print("   Child:    ");
			   printIndividual(child);
			   System.out.println();
			}
            else {
               System.out.println("No mutation");  
         }
					
			// save child
			for (int j = 0; j < INDIVIDUAL_SIZE; j++)
				newPopulation[i][j] = child[j];
			newFitness[i] = calculateFitness(child);	
			
		}  // create all new children
		
		// update fitness using new generation		
		population = newPopulation; 
		fitness = newFitness;
		double totalFitness = 0.0;
		averageFitness = 0.0;
		for (int i = 0; i < POPULATION_SIZE; i++)
			totalFitness += fitness[i];
		averageFitness = totalFitness / POPULATION_SIZE;
		
	}  // createNextGeneration
	
	private void printIndividual(int[] individual) {
		for (int i = 0; i < INDIVIDUAL_SIZE-1; i++)
			System.out.print(individual[i] + " ");
		System.out.print(individual[INDIVIDUAL_SIZE-1]);
	}  // print individual

	private void printMethod(String name) {
      // used to follow program progress, 
      // by printing method names as they are called
		System.out.println("... method call: " + name);
	}  // printMethod

	private void printPopulation (int[][] pop, String popName) {
		// prints population and fitness of each individual
		System.out.println("\n" + popName +
		                   ", average fitness = " + averageFitness);
		
		for (int i = 0; i < POPULATION_SIZE; i++) {
		   System.out.print("   ");
		   for (int j = 0; j < INDIVIDUAL_SIZE; j++)
				System.out.print(pop[i][j] + "  ");
			System.out.println("  --  " + fitness[i]);
		} 
	}  // printPopulation

	private double calculateAverageFitness() {
      printMethod("calculateAverageFitness");
		double total = 0;
		for (int i = 0; i < POPULATION_SIZE; i++)
			total += fitness[i];
		return total / POPULATION_SIZE;
	}  // calculateAverageFitness


	public static void main (String args[]) {
      System.out.println ("GAOneBits starting");
		GAOneBits gaBits = new GAOneBits();
		gaBits.outputGAParameters();
		gaBits.createInitialPopulation();
		gaBits.runGA();
		//gaBits.reportResults();
	}

}
