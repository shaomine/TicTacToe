package com.company;

import java.util.*;

public class PlayHuman {
    private Board board;
    private char mark_player1;                   //mark for Player1
    private char mark_player2;                   //mark for Player1
    private List<Integer> player1_moves;            //Array List that records player1 moves
    private List<Integer> player2_moves;            //Array List that records player1 moves
    private static List<List<Integer>> winning_conditions;

    static{
        //listing all the possible winning conditions
        List<Integer> top_row = Arrays.asList(1,2,3);
        List<Integer> mid_row = Arrays.asList(4,5,6);
        List<Integer> bot_row = Arrays.asList(7,8,9);
        List<Integer> left_col = Arrays.asList(1,4,7);
        List<Integer> mid_col = Arrays.asList(2,5,8);
        List<Integer> right_col = Arrays.asList(3,6,9);
        List<Integer> diagonal1 = Arrays.asList(1,5,9);
        List<Integer> diagonal2 = Arrays.asList(3,5,7);

        //adding the individual winning conditions to a list
        winning_conditions = new ArrayList<>();
        winning_conditions.add(top_row);
        winning_conditions.add(mid_row);
        winning_conditions.add(bot_row);
        winning_conditions.add(left_col);
        winning_conditions.add(mid_col);
        winning_conditions.add(right_col);
        winning_conditions.add(diagonal1);
        winning_conditions.add(diagonal2);

    }

    public PlayHuman(char mark){
        if(mark =='X'){
            this.mark_player1 = 'X';
            this.mark_player2 = 'O';
        }else if(mark == 'O'){
            this.mark_player1 = 'O';
            this.mark_player2 = 'X';
        }
        board = new Board();
        player1_moves = new ArrayList<>();
        player2_moves = new ArrayList<>();
    }
    //function containing logic for conducting the game
    public void play(){
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter a placement 1-9 to place your mark Player1");
            int player1_pos = scanner.nextInt();

            //basic validations for P1
            while (player1_pos<1 || player1_pos>9 || player1_moves.contains(player1_pos) || player2_moves.contains(player1_pos)){
                System.out.println("Already occupied position or invalid input, please put your mark elsewhere Player1");
                player1_pos = scanner.nextInt();
            }
            //adding the move the player1 arraylist
            player1_moves.add(player1_pos);
            placeMark(player1_pos,"player1");
            board.printBoard();

            //checking terminating condtions
            boolean endGame = checkWinningCondition();
            if(endGame) {
                System.out.println("The game concludes!!!");
                break;
            }

            System.out.println("Enter a placement 1-9 to place your mark Player2");
            int player2_pos = scanner.nextInt();

            //basic vlidation for P2
            while (player2_pos<1 || player2_pos>9 || player1_moves.contains(player2_pos) || player2_moves.contains(player2_pos)){
                System.out.println("Player2 needs to enter another position ");
                player2_pos = scanner.nextInt();
            }
            //adding the position to player2 array
            player2_moves.add(player2_pos);
            placeMark(player2_pos,"Player2");

            board.printBoard();
            endGame = checkWinningCondition();
            if(endGame) {
                System.out.println("The game concludes!!!");
                break;
            }

        }
        scanner.close();
    }

    private void placeMark(int pos, String user){
        char mark;

        if(user.equalsIgnoreCase("player1")){
            mark = this.mark_player1;
        }else{
            mark = this.mark_player2;
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
    // 1.winning_conditions : contains all terminating conditions
    //2.player1_moves : arraylist that records human player1 moves
    //3.player2_moves : arraylist that records human player2 moves
    private boolean checkWinningCondition() {
        for (List<Integer> l : winning_conditions) {
            if (player1_moves.containsAll(l))
            {
                System.out.println("Player 1  wins!!, We have a winner");
                return true;
            }
            else if (player2_moves.containsAll(l))
            {
                System.out.println("Player 2 wins!!, We have a winner");
                return true;
            }
        }

        if(player1_moves.size() + player2_moves.size() == 9){
            System.out.println("A Tie!!");
            return true;
        }
        return false;
    }
}

