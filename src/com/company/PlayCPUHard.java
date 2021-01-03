package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//main class governing Human vs CPU(Nightmare) mode
//please refer PlayCPUEZ class before reading further.
public class PlayCPUHard extends PlayCPU{

    public  PlayCPUHard(char humanMark){
        super(humanMark);
    }
    //play function containing logic for conducting the game
    public void play (){
        board.printBoard();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Enter a placement 1-9 to place your mark");
            int human_pos = scanner.nextInt();

            //basic validations
            while (human_pos<1 || human_pos>9 || human_positions.contains(human_pos) || cpu_positions.contains(human_pos)){
                System.out.println("Already occupied position or invalid input(should be between 1-9), please put your mark elsewhere");
                human_pos = scanner.nextInt();
            }
            human_positions.add(human_pos);
            placeMark(human_pos,"Human");
            boolean endGame = checkWinningCondition();
            if(endGame) {
                System.out.println("The game concludes!!!");
                break;
            }

            //CPU plays optimal moves in this mode
            //Refer to BoardAI to know about the details of algorithm used to generate these optimum moves
            BoardAI Optimal_Pos = new BoardAI(board,humanMark,cpuMark);
            int cpu_pos = Optimal_Pos.getPos();
            cpu_positions.add(cpu_pos);
            placeMark(cpu_pos,"CPU");
            System.out.println("CPU played:" + cpu_pos);
            board.printBoard();
            endGame = checkWinningCondition();
            if(endGame) {
                System.out.println("The game concludes!!!");
                break;
            }
        }
        scanner.close();
    }

    //a helper function to that places mark on the board for both
    //users(human and cpu)
    private void placeMark(int pos, String user){
        char mark;

        if(user.equalsIgnoreCase("human")){
            mark = this.humanMark;
        }else{
            mark = this.cpuMark;
        }

        switch(pos){
            case 1:
                board.getBoard()[0][0] = mark;
                break;
            case 2:
                board.getBoard()[0][2] = mark;
                break;
            case 3:
                board.getBoard()[0][4] = mark;
                break;
            case 4:
                board.getBoard()[2][0] = mark;
                break;
            case 5:
                board.getBoard()[2][2] = mark;
                break;
            case 6:
                board.getBoard()[2][4] = mark;
                break;
            case 7:
                board.getBoard()[4][0] = mark;
                break;
            case 8:
                board.getBoard()[4][2] = mark;
                break;
            case 9:
                board.getBoard()[4][4] = mark;
                break;
        }

    }

    //a helper function which checks for terminating conditions
    //with the help of 3 arraylists
    private boolean checkWinningCondition() {
        for (List<Integer> l : winning_conditions) {
            if (human_positions.containsAll(l))
            {
                System.out.println("You win!!, We have a winner");
                return true;
            }
            else if (cpu_positions.containsAll(l))
            {
                System.out.println("CPU wins(sad face) but we have a winner");
                return true;
            }
        }

        if(human_positions.size()+cpu_positions.size() == 9){  //condition for tie
            System.out.println("A Tie!!");
            return true;
        }
        return false;
    }
}
