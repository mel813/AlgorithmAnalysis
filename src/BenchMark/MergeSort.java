/**
*  
* @author: Melissa Froh
*  Date: 05/24/2023
*  Description: Project 1 CMSC 451 6381
*  
*  This program is an implementation of the merge sort operation. The source code was obtained from Geeks for Geeks for the
*  merge sort methods. Increment count and abstract method sort were implemented to perform benchmarking operations for operation count
*  and measuring total time taken. checkSort method was added to verify output is sorted and throw custom exception if not properly sorted.
*/

package BenchMark;


public class MergeSort extends AbstractSort{
	@Override
	public void sort(int[] arr) {
		/**Implementation of abstract method sort to begin the sorting operation.
		 * Implements the start sort and end sort operations from the AbstractSort class
		 * to start and end the timer.
		 * 
		 * @param arr[] an array of integers to be sorted by the program
		 */
		startSort(); //start timer
		sort(arr, 0, arr.length - 1); //call to merge sort operation
		endSort(); ///end timer
	}
	
	
	void merge(int arr[], int l, int m, int r) {
		/**Perform merge operation after to merge the two sorted arrays 
		 * back together and maintain sorted order.
		 * 
		 * @param arr[] a sub array of already sorted integers
		 * 		  l the left index
		 * 		  m the mid point in the array
		 * 		  r the right index
		 */
		
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
        	incrementCount();
            L[i] = arr[l + i];  
            
           
        }
        for (int j = 0; j < n2; ++j) {
        	incrementCount();
            R[j] = arr[m + 1 + j];          
        }
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
            	incrementCount();
                arr[k] = L[i];
                i++;
                
            }
            else {
            	incrementCount();
                arr[k] = R[j];
                j++;
                
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
        	incrementCount();
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
        	incrementCount();
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using merge()
    public void sort(int arr[], int l, int r){
    	/**Perform the sort operations by recursively dividing the arrays in half and sorting
    	 * each half. Calls the merge operation to merge the sorted arrays back together.
		 * 
		 * @param arr[] an array of integers top sort
		 * 		  l the left index
		 * 		  r the right index
		 */
    	
        if (l < r) {
        	incrementCount();
        	
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    
    public void checkSort(int arr[]) throws InvalidSort {
    	/**Check the array to ensure that the final array is in sorted
    	 * order. If a value is found to be larger than the next element
    	 * an exception is thrown.
    	 * 
    	 * @param arr[] an integer array
    	 * @exceptions InvalidSort a custom exception thrown if the array is not properly sorted
		 */
    	for(int i = 0; i < arr.length - 1; i++) {
    		if (arr[i] > arr[i+1]){
    			String message = "Elements are not in sorted order.";
	    		throw new InvalidSort(message); 
    		}
    	}
    }

	
		
 

}
