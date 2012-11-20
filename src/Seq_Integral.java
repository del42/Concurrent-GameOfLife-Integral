
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author delgersurenbold
 */
public class Seq_Integral {


    /* 
     * Bold, Delgersuren 
     * CSC720
     * Professor Murhpy
     * febraury 14, 2012
     * Description: This program is works as a sequential  integral operation
     * in C programming language
     */
    private static final int num_rects = 100000;
    private static long startTime;
    private static long endTime;
    private static long totalTime;
    private static float areaOfFunction = 0;
    private static float totalSum = 0;
    private static float midValue = 0;
    private static float height = 0;
    private static float width = 0;
    private static int degree;

//this method is pushing the item in stack
    static void Integral() {
        width = (float) (1.0 / num_rects);
        int i;
        for (i = 0; i < num_rects; i++) {
            midValue = (float) ((i + 0.5) * width);
            height = (float) (4.0 / (1.0 + midValue * midValue));
            totalSum += height;

        }
        areaOfFunction = totalSum * width;
        System.out.println("Area of the function is " + areaOfFunction);

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the sequential Integrsl");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of thread = ");
        degree = input.nextInt();
        System.out.println();
        //recording the thread start time
        startTime = System.currentTimeMillis();

        Integral();

        System.out.println("Game is terminated...");
        //recording the end time of the program
        endTime = System.currentTimeMillis();
        //calculating the total time spent for this program to execute
        totalTime = endTime - startTime;
        System.out.println("...Sequential Game of Life ended with " + totalTime + " milliseconds");

    }
}