/**
 * Original code:
 * Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
 *
 * Modifications:
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

/**
 * Height-weighted Quick Union with Path Compression
 */
public class HWQUPC {

    private final int[] parent;   // parent[i] = parent of i
    private final int[] height;   // height[i] = height of subtree rooted at i
    private int count;  // number of components
    private boolean pathCompression = false;
    private static final Random random = new Random();

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public HWQUPC(int n) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], height[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site
     * {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site
     * {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        if (!pathCompression) {
            return root;
        }
        HWQUPC.doPathCompression(p, root, parent);
        return root;
        // ... end of TODO
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the
     * same component; {@code false} otherwise
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
     * {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the the component
     * containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
     * {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int j = find(p);
        int i = find(q);
        if (i == j) {
            return;
        }
        HWQUPC.mergeComponents(i, j, height, parent);
        count--;
    }

    /**
     * Used only by testing code
     *
     * @param pathCompression
     */
    public void setPathCompression(boolean pathCompression) {
        this.pathCompression = pathCompression;
    }

    /**
     * Used only by testing code
     *
     * @param i the component
     * @return the parent of the component
     */
    public int getParent(int i) {
        return parent[i];
    }

    static void mergeComponents(int i, int j, int[] height, int[] parent) {
        // TODO make shorter root point to taller one
        //throw new RuntimeException("not implemented");

        if (height[i] > height[j]) {
            parent[j] = i;
            height[i] += height[j];
        } else {
            parent[i] = j;
            height[j] += height[i];
        }
    }

    static void doPathCompression(int p, int root, int[] parent) {
        // TODO update parent if appropriate
        //throw new RuntimeException("not implemented");

        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
    }

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        System.out.println("PLease enter the value of n :");
        int n = 10;//scan.nextInt();

        for (int j = 1; j < 500; j = j + 20) {
            int avg = 0;
            for (int i = 0; i < j;i++) {

                HWQUPC hw = new HWQUPC(n);
                int ran = 0;
                while (hw.count() != 1) {
                    int first = random.nextInt(n);
                    int second = random.nextInt(n);

                    ran++;

                    if (hw.connected(first, second)) {
                        continue;
                    }
                    {
                        hw.union(first, second);
                    }

                }
                //System.out.println("Random number generated : " + ran + " times");
                avg += ran;
            }
            
            System.out.println("Loop for "+j+" and n = "+n+" average is "+ avg /j);
            n+=10;
        }

    }

}
