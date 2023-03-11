package com.company;
//This class governs how the cpu choses its optimum  move
//the core logic is minimax algorithm which plays through every possible move
//and chooses the best move

public class BoardAI {
    private Board board;
    private char humanMark;
    private char cpuMark;

    public BoardAI(Board board, char humanMark, char cpuMark) {
        this.board = board;
        this.humanMark = humanMark;
        this.cpuMark = cpuMark;
    }

    //this function return a value b/w 1-9 based on the feedback from
    //findbestmove() function
    public int getPos() {
        int[] pos = findbestmove();
        if (pos[0] == 0 && pos[1] == 0) return 1;
        else if (pos[0] == 0 && pos[1] == 2) return 2;
        else if (pos[0] == 0 && pos[1] == 4) return 3;
        else if (pos[0] == 2 && pos[1] == 0) return 4;
        else if (pos[0] == 2 && pos[1] == 2) return 5;
        else if (pos[0] == 2 && pos[1] == 4) return 6;
        else if (pos[0] == 4 && pos[1] == 0) return 7;
        else if (pos[0] == 4 && pos[1] == 2) return 8;
        else if (pos[0] == 4 && pos[1] == 4) return 9;

        return -1;
    }

    //starting point of the minimax algorithm
    private int[] findbestmove() {
        //since the minimax algorithm requires a minimiser and a maximizer
        //maximizer as the name suggests tries to maximize the score and minimizer
        //does vice-versa
        //here since we want CPU to choose optimum moves we choose CPU as the maximizer
        //and human player as minimizer

        int best_val = Integer.MIN_VALUE;
        int mov_val;
        int best_row = 0;
        int best_col = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == ' ') {        //find a empty place

                    board.getBoard()[i][j] = cpuMark; //play the move

                    //now its human player turn and he/she is minimizer
                    mov_val = minimax(board.getBoard(), 0, false);

                    //undo the move
                    board.getBoard()[i][j] = ' ';

                    //track the best move for this turn based on score(best_val)
                    if (mov_val > best_val) {
                        best_val = mov_val;
                        best_row = i;
                        best_col = j;
                    }
                }
            }
        }

        return new int[]{best_row, best_col};
    }


    //core minimax algorithm implemented here
    //minimax receives three function parameters
    //1. char[][] board
    //2. int depth : a parameter which shows how far in the recursion tree we are
    //3. boolean isMaximUser : true if its the maximizer's turn to play
    //                         false if its the minimizer's turn
    //it is a recursive algorithm which plays ahead every move possible from
    //a given state and returns a score for the current state.
    private int minimax(char[][] board, int depth, boolean isMaximUser) {

        //calculation of the score of current board is done in static_evaluation
        //basically a score is assigned on the basis of board positions.
        int board_score = static_evaluation(board);

        //maximizer is winning then return its score i.e 100
        if (board_score == 100) {
            //System.out.println("CPU wins");
            return board_score;
        }

        //minimizer is winning then return -100
        if (board_score == -100) {
            //System.out.println("Human wins");
            return board_score;
        }

        //no more left to play
        if (!isMovesLeft(board)) {
            //System.out.println("no moves left");
            return 0;
        }

        //if its the maximizers turn
        if (isMaximUser) {
            int best_val = Integer.MIN_VALUE;

            for (int i = 0; i < 5; i++) {                //find a empty place in board
                for (int j = 0; j < 5; j++) {            //to play move
                    if (board[i][j] == ' ') {        //empty place
                        board[i][j] = cpuMark;   //play the move

                        //maximizer tries to maximize
                        best_val = Math.max(best_val, minimax(board, depth + 1, !isMaximUser));

                        //undo then move
                        board[i][j] = ' ';
                    }
                }
            }
            return best_val;
        } else {      //its minimizers turn
            int best_val = Integer.MAX_VALUE;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == ' ') {        //empty place
                        board[i][j] = humanMark;  //play the move

                        //minimizer tries to minimize
                        best_val = Math.min(best_val, minimax(board, depth + 1, !isMaximUser));

                        //undo move
                        board[i][j] = ' ';
                    }
                }
            }

            return best_val;
        }

    }

    //score calculation for the given board
    //assigns a value of 100 if the maximizer is winning in the given board(maximizer is CPU)
    //assigns a value of -100 if the minimizer is winning
    //else a score of 0 is returned
    private int static_evaluation(char[][] board) {
        //checking for row
        for (int row = 0; row < 5; row += 2) {
            if ((board[row][0] == board[row][2]) && (board[row][2] == board[row][4])) {
                if (board[row][0] == cpuMark) {
                    return 100;
                } else if (board[row][0] == humanMark) {
                    return -100;
                }
            }
        }

        //for columns
        for (int col = 0; col < 5; col += 2) {
            if ((board[0][col] == board[2][col]) && (board[2][col] == board[4][col])) {
                if (board[0][col] == cpuMark) {
                    return 100;
                } else if (board[0][col] == humanMark) {
                    return -100;
                }
            }
        }

        //for diagonals
        //diagonal1
        if (board[0][0] == board[2][2] && board[2][2] == board[4][4]) {
            if (board[0][0] == cpuMark) {
                return 100;
            } else if (board[0][0] == humanMark) {
                return -100;
            }
        }

        //diagonal 2
        if (board[0][4] == board[2][2] && board[2][2] == board[4][0]) {
            if (board[0][4] == cpuMark) {
                return 100;
            } else if (board[0][4] == humanMark) {
                return -100;
            }
        }

        return 0;
    }

    //a func to check whether there are more any more moves possible for either player
    private boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == ' ') {
                    //System.out.println("Empty place exists");//empty place
                    return true;
                }
            }
        }
        return false;
    }
}
