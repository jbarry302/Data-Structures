/**
 * @(#)BinarySearch.java
 *
 *
 * @author github.com/jbarry302
 * @version 1.00 2022/10/3
 */


public class BinarySearch {

    public static void main (String[] args) {
		int[] arr = {1,2,3,4,5};
		
		System.out.println (search(arr, 3));
	
	}
	
	public static int search(int[] arr, int val) {
		int high = arr.length-1;
		int low = 0;
		
		while (low < high) {
			int mid = (int)((low + high) / 2);
			
			if (arr[mid] == val) {
				return mid;
			} else if (arr[mid] > val) {
				high = mid-1;
			} else {
				low = mid+1;
			}
		}
		return -1;
	}
    
}