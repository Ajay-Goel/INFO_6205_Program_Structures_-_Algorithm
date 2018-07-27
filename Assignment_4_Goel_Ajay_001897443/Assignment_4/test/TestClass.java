
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ajaygoel
 */
public class TestClass {

    /**
     *
     * @author ajaygoel
     */
   public TestClass() {
    }
    ///TEST CASE FOR HASH
    @Test
    public void testHashFunction() {
        CalculateHash test1 = new CalculateHash();
        int hashValue = test1.hash(300, 40);
        assertEquals(20, hashValue,1);
    }
    ///TEST CASE FOR BIRTHDAY PROBLEM
    
    @Test
    public void Birthday_formula(){
    CalculateHash test2 = new CalculateHash();
    test2.Arr=new int[100];
    test2.Arr_count=new int[100];
    test2.result(test2.Arr, 0, 0);
    assertEquals(12.53314,(test2.Sum_two),5);
}
    
    ///TEST CASE FOR COUPON's COLLECTOR PROBLEM
    @Test
    public void Coupon_formula(){
    CalculateHash test3 = new CalculateHash();
    test3.Arr=new int[10];
    test3.Arr_count=new int[10];
    test3.result(test3.Arr, 0, 0);
    assertEquals(23.025,(test3.Sum_one/100),60);
}
    ///logic of counting balls in a bin is correct
    @Test
    public void count_balls(){
    CalculateHash test = new CalculateHash();
    test.Arr=new int[10];
    test.Arr_count=new int[10];
    test.result(test.Arr, 0, 0);
    assertEquals(2,test.Arr_count[7]);
}
    
}
