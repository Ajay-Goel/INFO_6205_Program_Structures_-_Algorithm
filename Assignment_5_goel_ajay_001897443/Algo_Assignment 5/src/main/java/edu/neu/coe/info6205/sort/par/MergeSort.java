/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.sort.par;

/**
 *
 * @author ajaygoel
 */
public class MergeSort {
    public MergeSort(){};
    
    public static void sort(int[] array,int lo,int high)
    {
        int[] aux = new int[array.length];
        sort(array,aux,lo,high);
    }
    public static void sort(int[]array,int[]aux,int lo, int high)
    {
        if(lo>=high) return;
        
        int mid = lo +(high-lo)/2;
        sort(array,aux,lo,mid);
        sort(array,aux,mid+1,high);
        merge(array,aux,lo,mid,high);
    }
    
    public static void merge(int[]array,int[]aux,int lo,int mid, int high)
    {
        
        for(int i =0; i <array.length;i++)
        {
            aux[i]=array[i];
        }
        
        int i =lo, j=mid+1;
        for(int k=lo;k<=high;k++)
        {
            if(i>mid) array[k]=aux[j++];
            else if(j>high) array[k]=aux[i++];
            else if(less(aux[i],aux[j])) array[k]=aux[i++];
            else array[k]=aux[j++];
        }
    }
    
    public static boolean less(int one,int second)
    {
        return one<second;
    }
}
