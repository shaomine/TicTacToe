package com.company;

import java.util.*;

// main class that governs Human Vs Cpu (Ez mode)
public class PlayCPUEZ extends PlayCPU {

    public PlayCPUEZ(char humanMark) {
        super(humanMark);
    }

    public void play() {        //play function containing logic for conducting the game
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        while (true) {
            System.out.println("Enter a placement 1-9 to place your mark");
            int human_pos = scanner.nextInt();

            //basic validation checks on human player input
            while (human_pos < 1 || human_pos > 9 || human_positions.contains(human_pos) || cpu_positions.contains(human_pos)) {
                System.out.println("Already occupied position or invalid input(should be between 1-9), please put your mark elsewhere");
                human_pos = scanner.nextInt();
            }

            //adding the move to the arraylist that keeps records of human moves
            human_positions.add(human_pos);

            //placing the mark on board
            placeMark(human_pos, "Human");

            //checking for game terminating condition
            boolean endGame = checkWinningCondition();
            if (endGame) {
                System.out.println("The game concludes!!!");
                break;
            }

            //getting cpu move using a randomly generated move
            int cpu_pos = rand.nextInt(9) + 1;

            //if the move is already used then regenerating a move until a valid move is
            //found
            while (human_positions.contains(cpu_pos) || cpu_positions.contains(cpu_pos)) {
                //System.out.println("CPU needs to change mark");
                cpu_pos = rand.nextInt(9) + 1;
            }

            //adding the mark to the arraylist used for tracking cpu moves
            cpu_positions.add(cpu_pos);
            placeMark(cpu_pos, "CPU");
            System.out.println("CPU played:" + cpu_pos);
            board.printBoard();
            //checking for game terminating condition
            endGame = checkWinningCondition();
            if (endGame) {
                System.out.println("The game concludes!!!");
                break;
            }
        }
        scanner.close();
    }

    //a helper function to that places mark on the board for both
    //users(human and cpu)
    private void placeMark(int pos, String user) {
        char mark;

        if (user.equalsIgnoreCase("human")) {
            mark = this.humanMark;
        } else {
            mark = this.cpuMark;
        }

        switch (pos) {
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
    // 1.winning_conditions : contains all terminating conditions
    //2.human_positions : arraylist that records human player moves
    //3.cpu_positions : arraylist that records cpu positions
    private boolean checkWinningCondition() {
        for (List<Integer> l : winning_conditions) {  //using containsAll function from collection framework
            if (human_positions.containsAll(l)) {
                System.out.println("You win!!, We have a winner");
                return true;
            } else if (cpu_positions.containsAll(l)) {
                System.out.println("CPU wins(sad face) but we have a winner");
                return true;
            }
        }
        if (human_positions.size() + cpu_positions.size() == 9) {       //condition for tie
            System.out.println("A Tie!!");
            return true;
        }
        return false;
    }
}
