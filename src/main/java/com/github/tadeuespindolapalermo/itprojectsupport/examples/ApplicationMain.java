package com.github.tadeuespindolapalermo.itprojectsupport.examples;

import com.github.tadeuespindolapalermo.itprojectsupport.array.ArrayProcessingOperations;

public class ApplicationMain {

	public static void main(String[] args) {			
		
		ArrayProcessingOperations processing = new ArrayProcessingOperations();
		int[] array = processing.createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89); 	
		processing.getArrayLength(array);		
    } 
	
}