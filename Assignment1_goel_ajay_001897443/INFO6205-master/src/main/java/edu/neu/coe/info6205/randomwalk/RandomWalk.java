/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;
import java.util.*;

public class RandomWalk {
    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    private void move(int dx, int dy) {
        // TODO you need to implement this
        x=x+dx;
        y=y+dy;
    }

    private void randomWalk(int m) {
        for (int i = 0; i < m; i++)
            randomMove();
    }

    private void randomMove() {
        // TODO you need to implement this
        //1 = North, 2 = South, 3 = East, 4 = West
        int direction = random.nextInt(4) + 1;
        if(direction==1)
        {
            move(0,1);
        }
        else if(direction==2)
        {
            move(0,-1);
        }
        else if(direction==3)
        {
            move(1,0);
        }
        else //(direction==4)
        {
            move(-1,0);
        }
    }

    public double distance() {
        double distance = Math.sqrt(Math.pow(x, 2)+Math.pow(y,2));
        return distance;
        //return 0; // TODO you need to implement this
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     * @param n the number of experiments to run
     * @param m the number of steps for each experiment
     * @return the mean distance
     */
    public static double randomWalkMulti(int n, int m) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++){
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance/n ;
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n =0;
        int m =100;

        for(int i=100; i <=1500;i=i+50)
        {
            n =i;
            double meanDistance = randomWalkMulti(m, n);
            System.out.println(n + " steps: " + meanDistance + " over "+ m + " experiments");
            //m=m+m/2;
        }

    }



}
