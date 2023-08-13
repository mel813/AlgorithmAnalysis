/**
*  @author: Melissa Froh
*  Date: 05/24/2023
*  Description: Project 1 CMSC 451 6381
*  
*  This program provides the methods to be used for benchmarking the sorting operations. 
*/
package BenchMark;

public abstract class AbstractSort {
	//Initialize class variables
	protected int operationCount = 0;
    protected long startTime;
    protected long endTime;
    
    //Define abstract sort method
	public abstract void sort(int[] arr);

	protected void startSort() {
		/**Start the timer for measuring the time taken to sort in nanoseconds.**/
		startTime = System.nanoTime();
	}
	
	protected void endSort() {
		/**Stop the timer for measuring the time taken to sort in nanoseconds.**/
		endTime = System.nanoTime();
	}
	
	protected void incrementCount() {
		/**Increment the operation count.**/
		operationCount++;
	}
	
	public int getCount() {
		/**Return the operation count.**/
		return operationCount;
		
	}
	
	public long getTime() {
		/**Return the total elapsed time in nanoseconds.**/
		return endTime - startTime;
		
	}
}
