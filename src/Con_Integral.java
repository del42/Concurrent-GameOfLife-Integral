
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author delgersurenbold
 */
public class Con_Integral extends Thread {
    /* 
     * Bold, Delgersuren 
     * CSC720
     * Professor Murhpy
     * febraury 14, 2012
     * Description: This program is works as a concurent integral operation
     * in Java programming language
     */

    private static final double num_rects = 100000;
    private static long startTime;
    private static long endTime;
    private static long totalTime;
    private static double areaOfFunction = 0.0;
    private static double totalSum = 0.0;
    private static double midValue = 0.0;
    private static double height = 0.0;
    private static double width = 0.0;
    private static int degree;
    private static double tID;

    public static class Lock {

        private boolean isCritical = false;

        public synchronized void lock()
                throws InterruptedException {
            while (isCritical) {
                wait();
            }
            isCritical = true;
        }

        public synchronized void unlock() {
            isCritical = false;
            notify();
        }
    }

//this method is integration of Py
    static void Integral(double tid, Lock l) throws InterruptedException {
        double threadID = tid + 1;

        width = (double) (1.0 / num_rects);
        int i;

        for (i = 0; i < (num_rects / degree); i++) {
            l.lock();
            midValue = (i + 0.5) * width + (threadID - 1) * (1.0 / degree);
            height = (double) 4.0 / (double) (1.0 + (double) (midValue * midValue));
            totalSum += height;
            l.unlock();
        }
        areaOfFunction = totalSum * width;
        System.out.println("Area of the function is " + areaOfFunction);
        //l.unlock();

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the concurrent Integrsl");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of thread = ");
        degree = input.nextInt();
        //recording the thread start time
        startTime = System.currentTimeMillis();
        // instantiating the threads
        Thread[] thread = new Thread[degree];
        final Lock lock = new Lock();

        for (int i = 0; i < 1; i++) {
            // preparing each thread 
            for (int j = 0; j < thread.length; j++) {
                final double tId = j;
                thread[j] = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Integral(tId, lock);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Con_Integral.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            }

            // starting each thread
            for (int n = 0; n < thread.length; n++) {
                thread[n].start();
            }

            // waiting for each thread to exit
            for (int m = 0; m < thread.length; m++) {
                try {
                    thread[m].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                }
            }

        }

        System.out.println("Game is terminated...");
        //recording the end time of the program
        endTime = System.currentTimeMillis();
        //calculating the total time spent for this program to execute
        totalTime = endTime - startTime;
        System.out.println("...Sequential Game of Life ended with " + totalTime + " milliseconds");

    }
}