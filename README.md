This program is written purely in JAVA and as such no external dependencies or third part libraries are used.
Suggested IDE to run this program: Intellij Idea

Written By: Shailendra Singh
email: shailensingh27@gmail.com

This file contains instructions on how to run the code.

If running through Intellij idea
1. Clone the repo locally
2. In Intellij choose File->New->Project from existing sources.
3. Navigate to local directory and choose the root.
4. Follow through the options with default options.

If using any other ide

1. Simply clone the repo locally.
2. Open a ide of your choice and create a new project
3. Create a folder structure as i have used for the project i.e src.com.company
4. Copy the Java files(7 java files) from src.com.company folder into the src folder of you new project and run from main
5. There might be some package name changes required depending on where you paste the files
6. All files belong to a same package


About The Program:

The program implements the classic TIC TAC TOE game.
The program is written in Java and contains 3 modes 
1) Human vs CPU
2) Human vs CPU(Hard)
3) Human vs Human

Mode 1 and Mode 3 implementaion are explained in the code and is recommended to understand from there.
Mode 2 uses a MiniMax Artificial Intelligence algorithm to generate optimal moves for cpu.

A basic info about folder structure:
There are 7 java files and are listed below with few remarks.

1)Main.java : main driving file.Please start running your code from here
2)Board.java : java file containing board class
3)PlayCPU.java : java file containing abstract class
4)PlayCPUEZ.java: java file containing Human Vs Cpu (easy mode) logic with explanatory comments
5)PlayHuman.java: java file contain Human vs Human gameplay logic
6)PlayCPUHard.java : java file containing Human vs Cpu(Hard mode) logic 
7)BoardAI.java: file containing logic used to generate optimal moves for CPU.


Minimax Algorithm:
Sources used for Minimax:
1) Wikipedia: https://en.wikipedia.org/wiki/Minimax
2) Youtube : https://www.youtube.com/watch?v=l-hh51ncgDI&t=293s
	     by: Sebastian Lague
Above two are recommended source for learning about minimax algorithm.


MiniMax Implementation:
Minimax is a backtracking algorithm. Minimax algorithm is used in this project to generate optimum moves for CPU.
In this algorithm among the two players one is Maximizer and one is Minimizer.
Maximizer is the player for whom you want to generate optimum moves considering that their opponent plays optimally too.
In our case CPU is maximizer and human player minimizer.

the core implementation with pseudocode as applied in this project is as follows:

the algorithm starts with a findbestmove function which receives a board state(lets call it the root state)
From here on now the function calculates and assigns a value to every move possible from this 
root state


func findbestmove(board){
	best_val=0;
	for move in board{
		//calculate val for move
		current_move_score = minimax(, ,)
		if current_move_score is better than best_val
			best_val=current_move_score
			bestmove = move
	}
	return best_move;
}

the current_move_score is calculated by the minimax algorithm which is a recursive algorithm.

int minimax(char board, int depth, boolean isMaximizer){
	
	//recursion teminating condition which checks if either 
	//player wins or there are no moves left to play

	if(isMaximizer){ //this means if its the maximizers turn and it always
			// tries to maximize the value it can obtain from its next move
		best_val = -inf
		for each move in board:
		{ 
		best_val = max(best_val,minimax(board,depth+1,!isMaximizer)
		}
	}
	else 
	{	//this means its the minimizers turn and it always
		// tries to minimizethe value it can obtain from its next move
		best_val = inf
		for each move in board:
		{
		best_val = min(best_val,minimax(board,depth+1,!isMaximizer)
		}
  	} 
	
	return best_val
}


These are just pseudocode for the main functions. For more details please follow in code implementation for better clarity.




