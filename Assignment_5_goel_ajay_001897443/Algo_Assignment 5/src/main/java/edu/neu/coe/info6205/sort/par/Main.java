package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
        Scanner scan = new Scanner(System.in);
        System.out.println("PLease enter the value of cutoff:");
        ParSort.cutoff = scan.nextInt();
        System.out.println("PLease enter the number of elements you want to sort in the array:");
        int numbers = scan.nextInt();
        Random random = new Random(0L);
        int[] array = new int[numbers];
        int[] test_array= new int[numbers];
       
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000);
           // test_array[i]=random.nextInt(10000);
       
        }
        
         for (int i = 0; i < array.length; i++) {
            test_array[i]=random.nextInt(10000);
       
        }
        double t1 = (double) System.currentTimeMillis();
        ParSort.sort(array, 0, array.length-1);
        double t2 = (double) System.currentTimeMillis();
        MergeSort.sort(test_array,0,test_array.length-1);
        double t3 = (double) System.currentTimeMillis();
        System.out.println("Sorting by Multi - threading :"+(t2-t1));
        System.out.println("Number of threads created :"+ ParSort.threads);
        System.out.println("Number of cores on the current system :"+Runtime.getRuntime().availableProcessors());
        System.out.println("Sorting by single thread :"+(t3-t2));
//        for (int i : array) {
//            System.out.println(i + " ");
//        }
        if (array[0] == 11) {
            System.out.println("Success!");
        }
    }
}
