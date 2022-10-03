/**
 * @(#)BubbleSort.java
 *
 *
 * @author github.com/jbarry302
 * @version 1.00 2022/10/3
 */

import java.util.*;

public class BubbleSort {

    public static void main (String[] args) {
    	
    	int[] arr = new int[10];
    	
    	for(int i = 0; i < arr.length; i++) {
    		arr[i] = new Random().nextInt(100) + 1;
    	}
    	
    	System.out.println("initial arr: " + Arrays.toString(arr));
    	
    	sort(arr);
    	
    	System.out.println ("sorted: " + Arrays.toString(arr));
	
	
	}
    
    
    public static void sort(int[] arr) {
    	for(int i = 0; i < arr.length; i++) {
    		for(int j = 0; j < arr.length - i - 1; j++) {
    			if(arr[j] > arr[j+1]) {
    				int temp = arr[j+1];
    				arr[j+1] = arr[j];
    				arr[j] = temp;
    			}
    		}
    	}
    }
    
}