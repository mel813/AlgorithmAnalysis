/**
*  
* @author: Melissa Froh
*  Date: 05/24/2023
*  Description: Project 1 CMSC 451 6381
*  
*  This program is an implementation of the heap sort operation. The source code was obtained from Geeks for Geeks for the
*  heap sort method. Increment count and abstract method sort were implemented to perform benchmarking operations for operation count
*  and measuring total time taken. checkSort method was added to verify output is sorted and throw custom exception if not properly sorted.
*/

package BenchMark;

public class HeapSort extends AbstractSort{
		
	@Override
	public void sort(int[] arr) {
		/**Implementation of abstract method sort to begin the sorting operation.
		 * Implements the start sort and end sort operations from the AbstractSort class
		 * to start and end the timer.
		 * 
		 * @param arr[] an array of integers to be sorted by the program
		 */
		startSort(); //starts timer
		
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--) {
			incrementCount();
			heapify(arr, n, i);
			
		}
		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			incrementCount();

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}

		endSort();//stops timer
	}
	 
	void heapify(int arr[], int n, int i){
		/**Sort the input by rearranging the elements of an array
		 * to conform to heap property. 
		 * 
		 * @param arr[] the integer array to be sorted
		 * 		  n the length of the array
		 * 		  i the element to be sorted
		 */
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest]) {
			incrementCount();
			largest = l;
		}
		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest]) {
			incrementCount();
			largest = r;
		}
		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			incrementCount();

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
			
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
