package br.com.tadeudeveloper.help.projects.examples;

import br.com.tadeudeveloper.help.projects.array.ArrayProcessingOperations;

public class ApplicationMain {

	public static void main(String[] args) {			
		
		ArrayProcessingOperations processing = new ArrayProcessingOperations();
		int[] array = processing.createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89); 	
		processing.getArrayLength(array);		
    } 
	
}