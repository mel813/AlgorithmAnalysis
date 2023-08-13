package BenchMark;
/**
 * @author: Melissa Froh
 *  Date: 05/22/2023
 *  Description: Project 1 CMSC 451
 *  
 * 
 * Defines custom exception and displays error message to user when thrown.
 */
public class InvalidSort extends Exception{
	InvalidSort(String errorMessage) {
		super(errorMessage);
	}
}

