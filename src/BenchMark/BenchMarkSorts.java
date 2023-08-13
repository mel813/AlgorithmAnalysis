/**@author: Melissa Froh
*  Date: 05/24/2023
*  Description: Project 1 CMSC 451 6381
*  
*  This program utilizes the MergeSort and HeapSort classes to sort 40 random data sets of 12 different sizes and produces information about the
*  time and operation count to benchmark these operations. The program checks that the array is properly sorted after calling the sort method. 
*  The results for the sorting algorithms time in nanoseconds and operation count are written to a file. 
*/

package BenchMark;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

public class BenchMarkSorts {

	public static void writeResultsToFile(int[] datasetSizes, long[][] criticalCounts, long[][] times, String outputFile) {
		/** Use information from sorting algorithms to write output to file. Each line represents the 40 runs for 
		 * that data sets' size.
		 * 
		 * @params datasetSizes[] an integer array containing the number of elements for a single run of the sorting algorithm
		 * 		   criticalCounts[] a long array containing the 40 critical counts for a single run of the sorting algorithm
		 * 		   times[] a long array containing the 40 time values for a single run of the sorting algorithm
		 * 		   outputFile the file path where the information will be written to
		 * @exception IOException, catches exceptions for the file writer 
		 * 
		 */
		try (FileWriter fileWriter = new FileWriter(outputFile)){
			// Iterate over the dataset sizes
			for (int i = 0; i < datasetSizes.length; i++) {

				// Iterate over the 40 runs for each dataset size
				for (int j = 0; j < 40; j++) {
					fileWriter.write(criticalCounts[i][j] + " " + times[i][j] + " ");

				}
				fileWriter.write("\n"); //add new line
			}
		} 
		catch (IOException e) {
			String message = "File not found.";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		/** The main method of the program. Instantiates merge sort and heap sort classes and runs
		 * sorting operations on randomly generated sets of data.
		 * 
		 * @exception InvalidSort, catches custom exception invalid sort exception if the data is not properly sorted
		 * 
		 */
		final String MERGE_SORT_OUTPUT_FILE = System.getProperty("user.home") + "\\Desktop\\mergeSortresults.txt";
		final String HEAP_SORT_OUTPUT_FILE =  System.getProperty("user.home") + "\\Desktop\\heapSortresults.txt";
		int[] datasetSizes = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000, 1200000};
		long[][] mergeSortCriticalCounts = new long[datasetSizes.length][40];
		long[][] mergeSortTimes = new long[datasetSizes.length][40];
		long[][] heapSortCriticalCounts = new long[datasetSizes.length][40];
		long[][] heapSortTimes = new long[datasetSizes.length][40];
		int warmUpIterations = 1000;

		Random random = new Random();
		
		// Warm-up JVM
		for (int i = 0; i < warmUpIterations; i++) {
			int[] data = generateRandomData(1000000, random);
			BenchMark.MergeSort mergeSort = new MergeSort();
			mergeSort.sort(data);
			BenchMark.HeapSort heapSort = new HeapSort();
			heapSort.sort(data);
		}
		
		//Iterate over each data set to sort arrays of different sizes
		for (int i = 0; i < datasetSizes.length; i++) {
			int datasetSize = datasetSizes[i];

			for (int j = 0; j < 40; j++) { //Create data for 40 runs
				int[] data = generateRandomData(datasetSize, random); //create random data set
				BenchMark.MergeSort mergeSort = new MergeSort();
				mergeSort.sort(data); //sort the data with merge sort
				try { //check that data is sorted
					mergeSort.checkSort(data);
				} catch (InvalidSort is) {					
					JOptionPane.showMessageDialog(null, is.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				mergeSortCriticalCounts[i][j] = mergeSort.getCount(); //get operation count
				mergeSortTimes[i][j] = mergeSort.getTime(); //get time taken to sort

				BenchMark.HeapSort heapSort = new HeapSort();
				heapSort.sort(data); //sort the same data with heapsort
				try {
					heapSort.checkSort(data); //check that data is sorted
				} catch (InvalidSort is) {					
					JOptionPane.showMessageDialog(null, is.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				heapSortCriticalCounts[i][j] = heapSort.getCount();
				heapSortTimes[i][j] = heapSort.getTime();

			}
		}

		//Write merge sort results to merge sort file path
		writeResultsToFile(datasetSizes, mergeSortCriticalCounts, mergeSortTimes, MERGE_SORT_OUTPUT_FILE);

	    // Write heap sort results to heap sort file path
	    writeResultsToFile(datasetSizes, heapSortCriticalCounts, heapSortTimes, HEAP_SORT_OUTPUT_FILE);
	}
	

	public static int[] generateRandomData(int size, Random random) {
		/** Uses Java random to generate a random data set of integer types. The size is determined by the
		 * size of the data set being used in the main method.
		 * 
		 * @param size, the size of the data set to be generated
		 * 		  random, the instance of Java Random
		 * @returns data[] a randomly generate array of integers
		 * @exception IOException, catches exceptions for the file writer 
		 * 
		 */
		int[] data = new int[size];
		//populate array with integers
		for (int i = 0; i < size; i++) {
			data[i] = random.nextInt();
		}
		return data;
	}
	


}
