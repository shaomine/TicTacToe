package com.company;

//Defining a board class with a 2d char board and few helper functions
public class Board {
    private char[][] board;

    public Board() {
        board = new char[][]{{' ', '|', ' ', '|', ' '}, {'-', '|', '-', '|', '-'}, {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'}, {' ', '|', ' ', '|', ' '}};
    }

    public char[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (char[] c_arr : board) {
            for (char c : c_arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}