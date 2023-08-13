/**
 *  @author: Melissa Froh
 *  Date: 05/24/2023
 *  Description: Project 1 CMSC 451 6381
 *  
 *  This program produces a report in the form of a JTable to display analysis of the data obtained in the benchmark sort program.
 *  Provides values for data set size, average operation count, coefficient of variance for operation and time counts, and average time
 *  elapsed for each data set. Allows user to choose the file to analyze.
 */
package BenchMark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Report {

	public static void main(String[] args) {
		/** Main method of the program to create JTable and GUI components. Allow user to select file to be 
		 * 
		 * @params datasetSizes[] a integer array containing the number of elements for a single run of the sorting algorithm
		 * 		   criticalCounts[] a long array containing the 40 critical counts for a single run of the sorting algorithm
		 * 		   times[] a long array containing the 40 time values for a single run of the sorting algorithm
		 * 		   outputFile the file path where the information will be written to
		 * @exception IOException, catches exceptions for the file writer 
		 * 
		 */
		//Create the JFrame
		JFrame frame = new JFrame("Report Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[][] data = new String[12][5];
		String[] columnNames = {"Size", "AvgCount", "Coef Count", "AvgTime", "CoefTime"};
		// Create the JTable
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		JTable table = new JTable(model);

		// Create the JScrollPane and add the table to it
		JScrollPane scrollPane = new JScrollPane(table);

		// Create the JPanel to hold the components
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);

		// Add the panel to the frame
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		// Select the input file using JFileChooser
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));

		int result = fc.showOpenDialog(null); 
		if (result == JFileChooser.APPROVE_OPTION) {
			// Read the input file and populate the table
			String selectedFile = fc.getSelectedFile().getAbsolutePath();
			populateData(selectedFile, model);
		}
	}	
	private static void populateData(String selectedFile, DefaultTableModel model) {
		/** Read information from selected file to populate rows in the table. Add row values to the table.
		 * 
		 * @params selectedFile, the file to be read
		 * 		   model, the table model to append
		 * @exception IOException, catches exceptions for the buffered reader
		 * 
		 */
		//initialize variables
		int[] datasetSizes = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000, 1200000};
		String line;
		int i = 0;
		double coeffCount;
		double countMean;
		double timeMean;
		double coeffTime;

		//allows for formatting output to two decimal places
		DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));

		BufferedReader br;
		try {
			//initialize buffered reader
			br = new BufferedReader( new FileReader(selectedFile));

			while((line = br.readLine()) != null){ //read in lines from file
				String[] values = line.split(" ");
				long[] counts = new long[40];
				long[]times = new long[40];
				for (int j = 0; j < values.length; j++) {
					if(j % 2 == 0) { //populate with operation counts
						counts[j/2] = Long.parseLong(values[j]);
					}
					else { //populate with time counts
						times[j/2] = Long.parseLong(values[j]);
					}

				}
				countMean = calculateMean(counts); //calculate operation count mean
				coeffCount = (stdDev(counts, countMean) / countMean) * 100; //calculate coefficient of variance as percent
				timeMean = calculateMean(times); //calculate time mean
				coeffTime =(stdDev(times, timeMean) / timeMean) * 100; //calculate coefficient of variance as percent
				String[] rowData = {String.valueOf(datasetSizes[i]), String.valueOf(decimalFormat.format(countMean)), String.valueOf(decimalFormat.format(coeffCount) + "%"),
						String.valueOf(decimalFormat.format(timeMean)), String.valueOf(decimalFormat.format(coeffTime ) + " %")}; //add values for row of tableS
				model.insertRow(i, rowData); //add row to table
				i++; //increment data set size and row of table
			}

		}
		catch (IOException e1) {
			String message = "Issue with processing the file";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}


	}		
	public static double calculateMean(long[] values) {
		/**Calculate mean of the set of values.
		 * 
		 * @param values[] a set of long values from the output file
		 * @returns sum / length, the mean of the data set
		 */
		double sum = 0; 
		int length = values.length; //get number of items in set

		for(double num : values) {
			sum += num; //calculate total sum
		}
		return sum / length; //divide sum by length
	}

	public static double stdDev(long[] values, double mean){
		/**Calculate standard deviation for the set of values.
		 * 
		 * @param values[] a set of long values from the output file
		 * @returns Math.sqrt(standardDeviation/length), the standard deviation of the set
		 */
		double standardDeviation = 0.0;
		int length = values.length; //get number of items in set

		for(double num: values) {
			standardDeviation += Math.pow(num - mean, 2);
		}


		return Math.sqrt(standardDeviation/length);
	}

}
