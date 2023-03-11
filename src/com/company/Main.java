/* Author: Shailendra Singh
Requirements: To Develop a tic-tac-toe game and have options for Human vs Human
and Human vs CPU game modes*/

package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        displayMenu();
        System.out.println("The board mark placements are as shown numbered from 1-9");
        displayBoard();
        int choice = scanner.nextInt();
        while (true) {
            if (choice == 1) {
                playHumanVsCpu();
                break;
            } else if (choice == 2) {
                playHumanVsHuman();
                break;
            } else if (choice == 3) {
                playHumanVsCpu_hard();
                break;
            } else if (choice == 4) {
                System.out.println("Quitting");
                break;
            } else {
                System.out.println("Cant recognise command, please enter a valid command");
                displayMenu();
                choice = scanner.nextInt();
            }
        }
        System.out.println("End----------------");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Enter choice for game mode");
        System.out.println("1: Human vs CPU");
        System.out.println("2: Human vs Human");
        System.out.println("3: Human vs CPU (Nightmare)");
        System.out.println("4: To quit program");

    }

    //driving function for Human vs Cpu(Ez mode)
    private static void playHumanVsCpu() {
        System.out.println("Playing vs Cpu");
        char choice = validChoice();
        PlayCPUEZ Game = new PlayCPUEZ(choice);
        Game.play();
    }

    //driving function for Human vs Human
    private static void playHumanVsHuman() {
        System.out.println("PLaying vs Human");
        char choice = validChoice();
        PlayHuman Game = new PlayHuman(choice);
        Game.play();
    }

    //driving function for Human vs Cpu(Hard mode)
    private static void playHumanVsCpu_hard() {
        System.out.println("Welcome to Hard mode");
        char choice = validChoice();
        PlayCPUHard Game = new PlayCPUHard(choice);
        Game.play();
    }


    //a function that displays mark placement info for this game
    private static void displayBoard() {
        char[][] board = new char[][]{{'1', '|', '2', '|', '3' }, {'-', '|', '-', '|', '-' }, {'4', '|', '5', '|', '6' },
                {'-', '|', '-', '|', '-' }, {'7', '|', '8', '|', '9' }};
        for (char[] c_arr : board) {
            for (char c : c_arr) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    //a function for ensuring that a valid choice for mark is entered
    private static char validChoice() {
        System.out.println("Choose your preferred mark 'X' or 'O' (Type X or O)");
        char choice;
        choice = scanner.next().charAt(0);
        while (choice != 'X' && choice != 'O') {
            System.out.println("Please Enter a valid mark");
            choice = scanner.next().charAt(0);
        }
        return choice;
    }
}
