/*
 * Copyright (c) 2018. Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import java.util.Arrays;
import java.util.Random;

import java.util.function.Function;

public class Benchmark<T> {
    private static Random rand = new Random();
    private static Integer[] Sorted_Array = new Integer[100000];
    private static Integer[] Reverse_Array = new Integer[100000];
    private static Integer[] Partial_Sort_Array = new Integer[100000];
    private static Integer[] Random_Array = new Integer[100000];

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
        //return 0;  // TODO
        double initial_time=System.nanoTime();
        for(int i =0;i<n;i++)
        {
            populate_Array(Sorted_Array, Reverse_Array, Partial_Sort_Array, Random_Array);
             T a = t;
            f.apply(a);
        }
        f.apply(t);

        double final_Time = System.nanoTime() - initial_time;
        //double mili_Final_Time= final_Time/1000;
        //return (mili_Final_Time) / n;
        return final_Time;
    }

    private final Function<T, Void> f;

    public static void main(String[] args) {
        
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
//        Integer[] Sorted_Array = new Integer[100000];
//        Integer[] Reverse_Array = new Integer[100000];
//        Integer[] Partial_Sort_Array = new Integer[100000];
//        Integer[] Random_Array = new Integer[100000];

        //Assigning values to Sorted_Array
//        for (int i = 0; i < 100000; i++) {
//            Sorted_Array[i] = i; // TODO populate the array with real random data
//            Reverse_Array[i] = 100000 - i;
//            Random_Array[i] = rand.nextInt(100001);
//        }

        int n = 1000;
        for (int i = n; i <= 100000; i = 2*i) {
            populate_Array(Sorted_Array, Reverse_Array, Partial_Sort_Array, Random_Array);
            // TODO You need to apply doubling to n
            
            System.out.println("For Sorted_Array");
            benchmarkSort(Sorted_Array, i, "SelectionSort", new SelectionSort<>(), m);
            benchmarkSort(Sorted_Array, i, "InsertionSort", new InsertionSort<>(), m);

            System.out.println("For Reverse_Array");
            benchmarkSort(Reverse_Array, i, "SelectionSort", new SelectionSort<>(), m);
            benchmarkSort(Reverse_Array, i, "InsertionSort", new InsertionSort<>(), m);

            System.out.println("For Random_Array");
            benchmarkSort(Random_Array, i, "SelectionSort", new SelectionSort<>(), m);
            benchmarkSort(Random_Array, i, "InsertionSort", new InsertionSort<>(), m);

            System.out.println("For Partial_Sort_Array");
            benchmarkSort(Partial_Sort_Array, i, "SelectionSort", new SelectionSort<>(), m);
            benchmarkSort(Partial_Sort_Array, i, "InsertionSort", new InsertionSort<>(), m);
            
        }

    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        //System.out.println(name + ": " + x + " millisecs for n=" + n);
        System.out.println(name + " " + x + " " + n);
        
    }
    private static void populate_Array(Integer[]Sorted_Array,Integer[]Reverse_Array,Integer[]Partial_Sort_Array,Integer[]Random_Array)
    {
        for (int i = 0; i < 100000; i++) {
            Sorted_Array[i] = i; // TODO populate the array with real random data
            Reverse_Array[i] = 100000 - i;
            Random_Array[i] = rand.nextInt(100001);
        }
        for (int j=0; j<100000/2; j++)
        {
            Partial_Sort_Array[j]=j;
            Partial_Sort_Array[j+100000/2]=rand.nextInt(100000);
        }
    }
}
