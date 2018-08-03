
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ajaygoel
 */
public class CalculateHash {

    public static int Sum_one = 0;
    public static int Sum_two = 0;

    public static int[] Arr;
    public static int[]Arr_count;

    public static int hash(Integer i, int size) {
        return (i.hashCode() & 0x7fffffff) % size;
    }

    //function to calculate the c1 and b1 values
    public static void result(int[] myArray, int c1, int b0) {
        Random ran = new Random();
        int r_int;
        int Value;
        int Count = 0;
        boolean collision = false;
        int Size = myArray.length;
        //take random numbers from 1 to 100 and fill the array
        int min = 1;
        
        int max = 10000;
        for (int i = 0; i < max; i++) {
            r_int = ran.nextInt(max - min) + min;
            //ran.setSeed((max - min) + min);
            //r_int=ran.nextInt();
            Value = hash(r_int, myArray.length);
            Arr_count[Value]++;
            if (Size == 0) {
                b0 = Count;
                Sum_one += b0;
                break;
            } else 
            {
                if (myArray[Value] == 0) 
                {
                    myArray[Value] = r_int;
                    Count++;
                    Size--;
                } else if (myArray[Value] != 0) 
                {
                    if (collision == false) 
                    {
                        c1 = Count;
                        Sum_two += c1;
                        collision = true;
                    } else 
                    {
                        myArray[Value] = r_int;
                        Count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        String in = scan.nextLine();

        int Size = Integer.parseInt(in);

        for (int i = 0; i < 100; i++) {
            Arr = new int[Size];
            Arr_count= new int[Size];
            int birth = 0;
            int coupon = 0;
            result(Arr, birth, coupon);
        }

        System.out.println("Average Birthday by code = " + Sum_two / 100);
        System.out.println("Average Birthday by formula = " + Math.sqrt(Math.PI * Size / 2));
        System.out.println("Average Coupon by code = " + Sum_one / 100);
        System.out.println("Average Coupon by formula = " + (Size * Math.log(Size)));
        
        System.out.println(Arrays.toString(Arr_count));
    }

}

