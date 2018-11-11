package com.github.tadeuespindolapalermo.itprojectsupport.examples;

import java.util.Date;

import com.github.tadeuespindolapalermo.itprojectsupport.array.ArrayProcessingOperations;

public class ExamplesArrayProcessingOperations {

	/**
	 * Array values: "A", "B", 140, 5.4, "Text", 2L, 3F, new Date()
	 * 
	 * Example to get the length of the Object array
	 */
	public void processingArrayObject() {
		ArrayProcessingOperations<Object> processingArrayObject = new ArrayProcessingOperations<>();
		Object[] arrayObject = processingArrayObject.createArray("A", "B", 140, 5.4, "Text", 2L, 3F, new Date());
		int arrayObjectLength = processingArrayObject.getArrayLength(arrayObject);
		System.out.println("Length of array of Object: " + arrayObjectLength + "\n");
	}

	/**
	 * Array values: "A", "B", "C"
	 *
	 * Example to get the length of the String array
	 */
	public void processingArrayString() {
		ArrayProcessingOperations<String> processingArrayString = new ArrayProcessingOperations<>();
		String[] arrayString = processingArrayString.createArray("A", "B", "C");
		int arrayStringLength = processingArrayString.getArrayLength(arrayString);
		System.out.println("Length of array of String: " + arrayStringLength + "\n");
	}

	/**
	 * Array values: 15, 7, 41, 602, 600, 945, 28, 34, 88, 89
	 *
	 * Example to obtain the length of the array of Integer type;
	 * 
	 * Example to obtain the sum of all elements of the array of Integer type;
	 *
	 * Example to obtain multiplication of all array elements of Integer type;
	 * 
	 * Example to obtain the subtraction of all array elements of the Integer type;
	 * 
	 * Example to obtain the division of all elements of the array of Integer type;
	 * 
	 */
	public void processingArrayInteger() {
		ArrayProcessingOperations<Integer> processingArrayInteger = new ArrayProcessingOperations<>();
		Integer[] arrayInteger = processingArrayInteger.createArray(15, 7, 41, 602, 600, 945, 28, 34, 88, 89);
		int arrayIntegerLength = processingArrayInteger.getArrayLength(arrayInteger);
		Integer arrayIntegerSum = processingArrayInteger.sumArrayElements(arrayInteger);
		Integer arrayIntegerMultiply = processingArrayInteger.multiplyArrayElements(arrayInteger);
		Integer arrayIntegerSubtract = processingArrayInteger.subtractArrayElements(arrayInteger);
		Integer arrayIntegerDivision = processingArrayInteger.divisionArrayElements(arrayInteger);
		System.out.println("Length of array of Integer: " + arrayIntegerLength);
		System.out.println("Sum of all elements of the Integer array: " + arrayIntegerSum);
		System.out.println("Multiply of all elements of the Integer array: " + arrayIntegerMultiply);
		System.out.println("Subtract of all elements of the Integer array: " + arrayIntegerSubtract);
		System.out.println("Division of all elements of the Integer array: " + arrayIntegerDivision + "\n");
	}

	/**
	 * Array values: 15.5, 7.1, 41.5, 602.8, 600.9, 945.2, 28.3, 34.0, 88.4, 89.6
	 * 
	 * Example to obtain the length of the array of Double type;
	 * 
	 * Example to obtain the sum of all elements of the array of Double type;
	 * 
	 * Example to obtain multiplication of all array elements of Double type;
	 * 
	 * Example to obtain the subtraction of all array elements of the Double type;
	 * 
	 * Example to obtain the division of all elements of the array of Double type;
	 * 
	 * *
	 */
	public void processingArrayDouble() {
		ArrayProcessingOperations<Double> processingArrayDouble = new ArrayProcessingOperations<>();
		Double[] arrayDouble = processingArrayDouble.createArray(15.5, 7.1, 41.5, 602.8, 600.9, 945.2, 28.3, 34.0, 88.4,
				89.6);
		int arrayDoubleLength = processingArrayDouble.getArrayLength(arrayDouble);
		Double arrayDoubleSum = processingArrayDouble.sumArrayElements(arrayDouble);
		Double arrayDoubleMultiply = processingArrayDouble.multiplyArrayElements(arrayDouble);
		Double arrayDoubleSubtract = processingArrayDouble.subtractArrayElements(arrayDouble);
		Double arrayDoubleDivision = processingArrayDouble.divisionArrayElements(arrayDouble);
		System.out.println("Length of array of Double: " + arrayDoubleLength);
		System.out.println("Sum of all elements of the Double array: " + arrayDoubleSum);
		System.out.println("Multiply of all elements of the Double array: " + arrayDoubleMultiply);
		System.out.println("Subtract of all elements of the Double array: " + arrayDoubleSubtract);
		System.out.println("Division of all elements of the Double array: " + arrayDoubleDivision + "\n");
	}

	/**
	 * Array values: 15.9f, 7.8f, 41.3f, 602.1f, 600.2f, 945.5f, 28.7f, 34.4f,
	 * 8888.0f, 89.5f
	 * 
	 * Example to obtain the length of the array of Float type;
	 * 
	 * Example to obtain the sum of all elements of the array of Float type;
	 * 
	 * Example to obtain multiplication of all array elements of Float type;
	 *
	 * Example to obtain the subtraction of all array elements of the Float type;
	 * 
	 * Example to obtain the division of all elements of the array of Float type;
	 * 
	 */
	public void processingArrayFloat() {
		ArrayProcessingOperations<Float> processingArrayFloat = new ArrayProcessingOperations<>();
		Float[] arrayFloat = processingArrayFloat.createArray(15.9f, 7.8f, 41.3f, 602.1f, 600.2f, 945.5f, 28.7f, 34.4f,
				8888.0f, 89.5f);
		int arrayFloatLength = processingArrayFloat.getArrayLength(arrayFloat);
		Float arrayFloatSum = processingArrayFloat.sumArrayElements(arrayFloat);
		Float arrayFloatMultiply = processingArrayFloat.multiplyArrayElements(arrayFloat);
		Float arrayFloatSubtract = processingArrayFloat.subtractArrayElements(arrayFloat);
		Float arrayFloatDivision = processingArrayFloat.divisionArrayElements(arrayFloat);
		System.out.println("Length of array of Float: " + arrayFloatLength);
		System.out.println("Sum of all elements of the Float array: " + arrayFloatSum);
		System.out.println("Multiply of all elements of the Float array: " + arrayFloatMultiply);
		System.out.println("Subtract of all elements of the Float array: " + arrayFloatSubtract);
		System.out.println("Division of all elements of the Float array: " + arrayFloatDivision + "\n");
	}

	/**
	 * Array values: 15L, 7L, 41L, 602L, 600L, 945L, 28L, 34L, 8888L, 89L
	 * 
	 * Example to obtain the length of the array of Long type;
	 * 
	 * Example to obtain the sum of all elements of the array of Long type;
	 * 
	 * Example to obtain multiplication of all array elements of Long type;
	 *
	 * Example to obtain the subtraction of all array elements of the Long type;
	 *
	 * Example to obtain the division of all elements of the array of Long type;
	 * 
	 */
	public void processingArrayLong() {
		ArrayProcessingOperations<Long> processingArrayLong = new ArrayProcessingOperations<>();
		Long[] arrayLong = processingArrayLong.createArray(15L, 7L, 41L, 602L, 600L, 945L, 28L, 34L, 8888L, 89L);
		int arrayLongLength = processingArrayLong.getArrayLength(arrayLong);
		Long arrayLongSum = processingArrayLong.sumArrayElements(arrayLong);
		Long arrayLongMultiply = processingArrayLong.multiplyArrayElements(arrayLong);
		Long arrayLongSubtract = processingArrayLong.subtractArrayElements(arrayLong);
		Long arrayLongDivision = processingArrayLong.divisionArrayElements(arrayLong);
		System.out.println("Length of array of Long: " + arrayLongLength);
		System.out.println("Sum of all elements of the Long array: " + arrayLongSum);
		System.out.println("Multiply of all elements of the Long array: " + arrayLongMultiply);
		System.out.println("Subtract of all elements of the Long array: " + arrayLongSubtract);
		System.out.println("Division of all elements of the Long array: " + arrayLongDivision + "\n");
	}

}