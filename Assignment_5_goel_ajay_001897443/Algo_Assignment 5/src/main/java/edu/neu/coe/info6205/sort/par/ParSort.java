package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;
    public static int threads = 0;
    

    public static void sort(int[] array, int from, int to) {
        int size = to - from + 1;
        if (size <= cutoff) {
            Arrays.sort(array, from, to+1);
        } else {
            int mid = from + (to - from) / 2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid);//null; // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, mid + 1, to);//null; // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TODO implement me
                        int i = 0, j = 0;
                        for (int k = 0; k < result.length; k++) {
                            if (j >= xs2.length) {
                                result[k] = xs1[i++];
                            } else if (i >= xs1.length) {
                                result[k] = xs2[j++];
                            } else if (xs1[i] <= xs2[j]) {
                                result[k] = xs1[i++];
                            } else {
                                result[k] = xs2[j++];
                            }
                        }
		                for (int k = 0; k < array.length; k++) {
		                    array[k] = result[k];
		                }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {

                // for (int k = 0; k < array.length; k++) {
//                     array[k] = result[k];
//                 }
				if (throwable == null) 
				{
                    parsort.complete(array);
                } else 
				{
                    parsort.completeExceptionally(throwable);
                }
            }); // TODO implement me
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    //threads=threads+ 1;
                    int[] result = new int[to - from + 1];
                    // TODO implement me
                    /////////////////////
                    int j = from;
                    for (int k = 0; k < result.length; k++) {
                        result[k] = array[j++];
                    }
                    sort(result, 0, to-from);
                    /////////////////////
                    return result;
                }
        );
    }
}
