package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Abstract class for Vs CPU modes
//containing basic common functionalities and member variables
public abstract class PlayCPU {
    protected Board board;
    protected char humanMark;                     //mark (X or O chosen by human player)
    protected char cpuMark;                       //mark assigned to cpu
    protected List<Integer> human_positions;     //an arraylist to record human player moves
    protected List<Integer> cpu_positions;         //an arraylist to record cpu moves
    protected static List<List<Integer>> winning_conditions;  // a arraylist to record all winning conditions

    static{
        //listing all the possible winning conditions
        List<Integer> top_row = Arrays.asList(1,2,3);   //top row winning condition
        List<Integer> mid_row = Arrays.asList(4,5,6);   // and likewise for others
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

    public PlayCPU(char humanMark){
        if(humanMark=='X'){
            this.humanMark = 'X';
            this.cpuMark = 'O';
        }else if(humanMark == 'O'){
            this.humanMark = 'O';
            this.cpuMark = 'X';
        }

        board = new Board();
        human_positions = new ArrayList<>();
        cpu_positions = new ArrayList<>();
    }


}
