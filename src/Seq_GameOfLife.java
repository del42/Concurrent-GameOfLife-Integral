import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author delgersurenbold
 */
public class Seq_GameOfLife {


    /* 
     * Bold, Delgersuren 
     * CSC720
     * Professor Murhpy
     * febraury 14, 2012
     * Description: This program is works as a sequential Game of Life operation
     * in Java programming language
     */
    private static int rowSize;
    private static int columnSize;
    private static int[][] currentGrid;
    private static int[][] nextGrid;
    private static int[][] emptyGrid;
    private static final int ALIVE = 1;
    private static final int DEAD = 0;
    private static long startTime;
    private static long endTime;
    private static long totalTime;

    static void NextLife() {
        int counter;
        int terminator = 0;

        for (int i = 1; i < rowSize - 1; i++) {
            for (int j = 1; j < columnSize - 1; j++) {
                counter = 0;
                if (currentGrid[i - 1][j - 1] == ALIVE) {
                    counter++; //north west 
                }
                if (currentGrid[i - 1][j] == ALIVE) {
                    counter++; //north 
                }
                if (currentGrid[i - 1][j + 1] == ALIVE) {
                    counter++; //north east 
                }
                if (currentGrid[i][j - 1] == ALIVE) {
                    counter++; //west 
                }
                if (currentGrid[i][j + 1] == ALIVE) {
                    counter++; //east 
                }
                if (currentGrid[i + 1][j - 1] == ALIVE) {
                    counter++; //south west 
                }
                if (currentGrid[i + 1][j] == ALIVE) {
                    counter++; //south
                }
                if (currentGrid[i + 1][j + 1] == ALIVE) {
                    counter++; //south east
                }
                if (counter <= 1 || counter > 4) {
                    nextGrid[i][j] = DEAD;
                } else if (currentGrid[i][j] == ALIVE && (counter == 2 || counter == 3)) {
                    nextGrid[i][j] = ALIVE;
                } else if (currentGrid[i][j] == DEAD && counter == 3) {
                    nextGrid[i][j] = ALIVE;
                } else {
                    nextGrid[i][j] = ALIVE;
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("Welcome to the sequential Game of life...\nPlease enter row and column of the grid ==> ");
        Scanner input = new Scanner(System.in);
        rowSize = input.nextInt();
        columnSize = input.nextInt();
        currentGrid = new int[rowSize][columnSize];
        nextGrid = new int[rowSize][columnSize];
        emptyGrid = new int[rowSize][columnSize];


        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                currentGrid[i][j] = 0;
                nextGrid[i][j] = 0;
                emptyGrid[i][j] = 0;
                System.out.print(currentGrid[i][j] + " ");

            }
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("pick a coordinate for living cell (Ex: 2 3 )==> ");
            int seedR = input.nextInt();
            int seedC = input.nextInt();
            currentGrid[seedR][seedC] = 1;
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(currentGrid[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println("GAME OF LIFE has began...");
        //recording the thread start time
        startTime = System.currentTimeMillis();

        for (int i = 0; i < 200; i++) {
            NextLife();
            for (int n = 0; n < rowSize; n++) {
                for (int m = 0; m < columnSize; m++) {
                    System.out.print(nextGrid[n][m] + " ");
                }
                System.out.println("");
            }
            System.out.println();
            currentGrid = nextGrid;
            nextGrid = emptyGrid;
        }
        System.out.println("Game is terminated...");
        //recording the end time of the program
        endTime = System.currentTimeMillis();
        //calculating the total time spent for this program to execute
        totalTime = endTime - startTime;
        System.out.println("...Sequential Game of Life ended with " + totalTime + " milliseconds");


    }
}
