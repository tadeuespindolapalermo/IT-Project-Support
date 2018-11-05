package com.github.tadeuespindolapalermo.itprojectsupport.array;

import java.util.ArrayList;
import java.util.List;

import com.github.tadeuespindolapalermo.itprojectsupport.utilities.Validators;

/**
 * (en-us)
 * <p>
 * Generic class responsible for processing operations and manipulation in data
 * structure of type Array
 * <p>
 * (pt-br)
 * <p>
 * Classe genérica responsável por processar operações e manipulação em
 * estrutura de dados do tipo Array
 * 
 * @param <T> - Generic type of data
 * @author Tadeu Espíndola Palermo
 * @since v1.0
 *        <p>
 *        Date Create: 19/10/2018
 *        <p>
 *        Date Last Update: 04/11/2018
 */
@SuppressWarnings("unchecked")
public class ArrayProcessingOperations<T> {

	// Attributes used only for multiplication and division operations
	private Long operationLong;
	private Float operationFloat;
	private Double operationDouble;
	private Integer operationInteger;

	public ArrayProcessingOperations() {
		operationLong = 1L;
		operationFloat = 1f;
		operationDouble = 1.0;
		operationInteger = 1;
	}

	/**
	 * (en-us)
	 * <p>
	 * 
	 * Method used for creating array of elements. Enter in the parameter only the
	 * array elements. This method returns the array entered in the method
	 * parameter.
	 * 
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * Object[] arrayObject = processingObject.createArray("A", "B", 140, 5.4, "Text", 2L, 3F, new Date());
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * (pt-br)
	 * <p>
	 * Método utilizado para a criação de array de elementos. Informe no parâmetro
	 * apenas os elementos do array. Este método retorna o array informado no
	 * parâmetro do método.
	 * 
	 * @param array - Array elements
	 * @author Tadeu Espíndola Palermo
	 * @return Array of elements entered in the method parameter
	 * @since v1.0
	 *        <p>
	 *        Date Create: 19/10/2018
	 *        <p>
	 *        Date Last Update: 29/10/2018
	 */
	public T[] createArray(T... array) {
		return array;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to get array length. Enter in the parameter only the element
	 * array. This method returns the length of the array entered in the method
	 * parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Object> processingObject = new ArrayProcessingOperations<>();
	 * Object[] arrayObject = processingObject.createArray("A", "B", 140, 5.4, "Text", 2L, 3F, new Date());
	 * int arrayObjectLength = processingObject.getArrayLength(arrayObject);
	 * System.out.println(arrayObjectLength);
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para obter o comprimento do array. Informe no parâmetro
	 * apenas o array de elementos. Este método retorna o comprimento do array
	 * informado no parâmetro do método. *
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return Length of array entered in method parameter
	 * @since v1.0
	 *        <p>
	 *        Date Create: 19/10/2018
	 *        <p>
	 *        Date Last Update: 29/10/2018
	 */
	public int getArrayLength(T... array) {
		return array.length;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to check if the type of the array entered in the parameter is of
	 * any allowed type: Integer, Long, Double or Float. Enter in the parameter only
	 * the element array. This method returns a list containing the array entered in
	 * the method parameter, if the type of the array entered is of any allowed
	 * type: Integer, Long, Double or Float. Usability - used only within the class:
	 * public class ArrayProcessingOperations <T>, as requirement of functionality
	 * pertaining to the method: public T makeSumOfElements (T ... array)
	 * <p>
	 * (pt-br)
	 * <p>
	 * Método utilizado para verificar se o tipo do array informado no parâmetro é
	 * de algum tipo permitido: Integer, Long, Double ou Float. Informe no parâmetro
	 * apenas o array de elementos. Este método retorna uma lista contendo o array
	 * informado no parâmetro do método, caso o tipo do array informado seja de
	 * algum tipo permitido: Integer, Long, Double ou Float. Usabilidade - utilizado
	 * apenas dentro da classe: public class ArrayProcessingOperations <T>, como
	 * requisito da funcionalidade pertinente ao método: public T
	 * sumArrayElements(T... array)
	 * <p>
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return List containing the array entered in the method parameter, if the
	 *         type of the array entered is of any allowed type: Integer, Long,
	 *         Double or Float.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 26/10/2018
	 *        <p>
	 *        Date Last Update: 29/10/2018
	 */
	private List<Object> checkTypeAllowed(T... array) {
		List<Object> listArray = new ArrayList<>();
		if (array[0] instanceof Integer || array[0] instanceof Long || array[0] instanceof Double
				|| array[0] instanceof Float || array[0] instanceof Short || array[0] instanceof Byte) {
			listArray.add(array);
		}
		return listArray;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to check if the type of the array entered in the parameter is of
	 * any allowed type: Integer, Long, Double or Float. Enter in the parameter only
	 * the element array. This method returns an integer according to the check of
	 * the type of array entered by parameter: Integer-1; Long-2; Double-3; Float-4;
	 * If the type of the array entered by parameter is different from the Integer,
	 * Long, Double, or Float types, the method returns the value 0. Usability -
	 * used only within the class: public class ArrayProcessingOperations <T>, as
	 * requirement of functionality pertaining to the method: public T
	 * makeSumOfElements (T ... array)
	 * <p>
	 * (pt-br)
	 * <p>
	 * Método utilizado para verificar se o tipo do array informado no parâmetro é
	 * de algum tipo permitido: Integer, Long, Double ou Float. Informe no parâmetro
	 * apenas o array de elementos. Este método retorna um número inteiro de acordo
	 * com a verificação do tipo do array informado por parâmetro: Integer-1;
	 * Long-2; Double-3; Float-4; Caso o tipo do array informado por parâmetro seja
	 * diferente dos tipos Integer, Long, Double ou Float, o método retorna o valor
	 * 0. Usabilidade - utilizado apenas dentro da classe: public class
	 * ArrayProcessingOperations <T>, como requisito da funcionalidade pertinente ao
	 * método: public T sumArrayElements(T... array)
	 * <p>
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return Integer according to the check of the type of array entered by
	 *         parameter: Integer-1; Long-2; Double-3; Float-4; If the type of the
	 *         array entered by parameter is different from the Integer, Long,
	 *         Double, or Float types, the method returns the value 0.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 26/10/2018
	 *        <p>
	 *        Date Last Update: 29/10/2018
	 */
	private int checkTypeInformed(T... array) {
		List<Object> listArray = new ArrayList<>();
		int numberType = 0;
		if (array[0] instanceof Integer || array[0] instanceof Long || array[0] instanceof Double
				|| array[0] instanceof Float) {
			listArray.add(array);
			if (array[0] instanceof Integer) {
				numberType = 1;
			} else if (array[0] instanceof Long) {
				numberType = 2;
			} else if (array[0] instanceof Double) {
				numberType = 3;
			} else if (array[0] instanceof Float) {
				numberType = 4;
			}
			return numberType;
		}
		return 0;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to convert a numeric value of type String based on a comparison
	 * value of integer type, both informed by parameter: 1-Integer; 2-Long;
	 * 3-Double; 4-Float. Enter as the first parameter the value of the compare
	 * integer type and as the second parameter the value of type String to be
	 * converted. This method returns the conversion of the value of the type String
	 * entered by parameter, converted to the generic type T. If the integer value
	 * of the comparison reported by parameter is different from 1, 2, 3 or 4, the
	 * method returns null. Usability - used only within the class: public class
	 * ArrayProcessingOperations <T>, as requirement of functionality pertaining to
	 * the method: public T makeSumOfElements (T ... array)
	 * <p>
	 * (pt-br)
	 * <p>
	 * Método utilizado para converter um valor numérico do tipo String com base num
	 * valor de comparação do tipo inteiro, ambos informados por parâmetro:
	 * 1-Integer; 2-Long; 3-Double; 4-Float. Informe como primeiro parâmetro o valor
	 * do tipo inteiro de comparação e como segundo parâmetro o valor do tipo String
	 * a ser convertido. Este método retorna a conversão do valor do tipo String
	 * informado por parâmetro, convertido para o tipo genérico T. Caso o valor
	 * inteiro de comparação informado por parâmetro seja diferente de 1, 2, 3 ou 4,
	 * o método retorna null. Usabilidade - utilizado apenas dentro da classe:
	 * public class ArrayProcessingOperations <T>, como requisito da funcionalidade
	 * pertinente ao método: public T sumArrayElements(T... array)
	 * <p>
	 * 
	 * @param valueComparator - Value to be shared
	 * @param valueConversion - Value to be converted
	 * @author Tadeu Espíndola Palermo
	 * @return Conversion of the value of the type String entered by parameter,
	 *         converted to the generic type T. If the integer value of the
	 *         comparison reported by parameter is different from 1, 2, 3 or 4, the
	 *         method returns null.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 26/10/2018
	 *        <p>
	 *        Date Last Update: 29/10/2018
	 */
	private T convertValue(int valueComparator, String valueConversion) {
		Number valueConverter = null;
		if (valueComparator == 1) {
			valueConverter = Integer.parseInt(valueConversion);
			return (T) valueConverter;
		} else if (valueComparator == 2) {
			valueConverter = Long.parseLong(valueConversion);
			return (T) valueConverter;
		} else if (valueComparator == 3) {
			valueConverter = Double.parseDouble(valueConversion);
			return (T) valueConverter;
		} else if (valueComparator == 4) {
			valueConverter = Float.parseFloat(valueConversion);
			return (T) valueConverter;
		}
		return null;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to perform the sum of all the elements of the array informed by
	 * parameter. Enter in the parameter only the element array. This method returns
	 * the sum of all elements of the array entered by parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Float> processingArrayFloat = new ArrayProcessingOperations<>();
	 * Float[] arrayFloat = processingArrayFloat.createArray(15.9f, 7.8f, 41.3f, 602.1f, 600.2f, 945.5f, 28.7f, 34.4f,
	 * 		8888.0f, 89.5f);
	 * Float arrayFloatSum = processingArrayFloat.sumArrayElements(arrayFloat);
	 * System.out.println("Sum of all elements of the Float array: " + arrayFloatSum);
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para realizar a soma de todos os elementos do array
	 * informado por parâmetro. Informe no parâmetro apenas o array de elementos.
	 * Este método retorna a soma de todos os elementos do array informado por
	 * parâmetro.
	 * <p>
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return Sum of all elements of the array entered by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 26/10/2018
	 *        <p>
	 *        Date Last Update: 31/10/2018
	 */
	private T operateArrayElements(String operation, T... array) {
		if (Validators.collectionValid(checkTypeAllowed(array))) {
			T outputOperation = null;
			T returnOfOperation = null;
			int i;
			Double outputOperationDouble = (Double) outputOperation;
			Integer outputOperationInteger = (Integer) outputOperation;
			Float outputOperationFloat = (Float) outputOperation;
			Long outputOperationLong = (Long) outputOperation;
			List<T> listArray = new ArrayList<>();
			String type = "";
			String typeArray = "";
			for (i = 0; i < array.length; i++) {
				String valueElementPosition = array[i].toString();
				T converterValue = convertValue(checkTypeInformed(array), valueElementPosition);
				listArray.add(converterValue);
				typeArray = converterValue.getClass().getSimpleName();
			}
			returnOfOperation = processOperation(typeArray, type, outputOperationDouble, outputOperationInteger,
					outputOperationFloat, outputOperationLong, listArray, operation);
			if (!(typeArray.equals("Double") || typeArray.equals("Integer") || typeArray.equals("Float")
					|| typeArray.equals("Long"))) {
				System.out.println(
						"The type informed is not applicable to the sum functionality of the elements of an array!");
			}
			return returnOfOperation;
		}
		return null;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to obtain the result of the operation of the elements of a list
	 * informed by parameter. This method return the result of the operation of the
	 * elements of a list informed by parameter. Usability - used only within the
	 * class: public class ArrayProcessingOperations <T>, as requirement of
	 * functionality pertaining to the method: public T makeSumOfElements (T ...
	 * array)
	 * <p>
	 * (pt-br)
	 * <p>
	 * Método utilizado para obter o resultado da operação dos elementos de uma
	 * lista informada por parâmetro. Este método retorna o resultado da operação
	 * dos elementos de uma lista informada por parâmetro. Usabilidade - utilizado
	 * apenas dentro da classe: public class ArrayProcessingOperations <T>, como
	 * requisito da funcionalidade pertinente ao método: public T
	 * sumArrayElements(T... array)
	 * <p>
	 * 
	 * @param typeArray        - Array type related to sum
	 * @param type             - Type the array to be used in the comparison that
	 *                         decides to return the sum.
	 * @param operationDouble  - Double countable variable that will store the sum
	 *                         to be returned.
	 * @param operationInteger - Integer countable variable that will store the sum
	 *                         to be returned.
	 * @param operationFloat   - Float countable variable that will store the sum to
	 *                         be returned.
	 * @param operationLong    - Long countable variable that will store the sum to
	 *                         be returned.
	 * @param listArray        - List that contains the elements that will be added
	 *                         together.
	 * @param operation        - Type of operation to be performed: addition(+),
	 *                         subtraction(-), multiplication(*) or division(/).
	 * @author Tadeu Espíndola Palermo
	 * @return Sum of the elements of a list informed by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 26/10/2018
	 *        <p>
	 *        Date Last Update: 30/10/2018
	 */
	@SuppressWarnings("rawtypes")
	private T processOperation(String typeArray, String type, Double operationDouble, Integer operationInteger,
			Float operationFloat, Long operationLong, List<T> listArray, String operation) {

		if (typeArray.equals("Double")) {
			type = "Double";
			operationDouble = 0.0;
			int cont = 0;
			List<Double> listDouble = new ArrayList(listArray);
			for (Double item : listDouble) {
				cont++;
				if (operation.equals("+")) {
					operationDouble += item;
				} else if (operation.equals("-")) {
					if (cont == 1) {
						operationDouble -= item * -1;
					} else {
						operationDouble -= item;
					}
				} else if (operation.equals("*")) {
					this.operationDouble *= item;
				} else if (operation.equals("/")) {
					if (cont == 1) {
						this.operationDouble = (Double) listArray.get(0);
					} else {
						this.operationDouble /= item;
					}
				}
			}
		}

		if (typeArray.equals("Integer")) {
			type = "Integer";
			operationInteger = 0;
			int cont = 0;
			List<Integer> listInteger = new ArrayList(listArray);
			for (Integer item : listInteger) {
				cont++;
				if (operation.equals("+")) {
					operationInteger += item;
				} else if (operation.equals("-")) {
					if (cont == 1) {
						operationInteger -= item * -1;
					} else {
						operationInteger -= item;
					}
				} else if (operation.equals("*")) {
					this.operationInteger *= item;
				} else if (operation.equals("/")) {
					if (cont == 1) {
						this.operationInteger = (Integer) listArray.get(0);
					} else {
						this.operationInteger /= item;
					}
				}
			}
		}

		if (typeArray.equals("Float")) {
			type = "Float";
			operationFloat = 0f;
			int cont = 0;
			List<Float> listFloat = new ArrayList(listArray);
			for (Float item : listFloat) {
				cont++;
				if (operation.equals("+")) {
					operationFloat += item;
				} else if (operation.equals("-")) {
					if (cont == 1) {
						operationFloat -= item * -1;
					} else {
						operationFloat -= item;
					}
				} else if (operation.equals("*")) {
					this.operationFloat *= item;
				} else if (operation.equals("/")) {
					if (cont == 1) {
						this.operationFloat = (Float) listArray.get(0);
					} else {
						this.operationFloat /= item;
					}
				}
			}
		}

		if (typeArray.equals("Long")) {
			type = "Long";
			operationLong = 0L;
			int cont = 0;
			List<Long> listLong = new ArrayList(listArray);
			for (Long item : listLong) {
				cont++;
				if (operation.equals("+")) {
					operationLong += item;
				} else if (operation.equals("-")) {
					if (cont == 1) {
						operationLong -= item * -1;
					} else {
						operationLong -= item;
					}
				} else if (operation.equals("*")) {
					this.operationLong *= item;
				} else if (operation.equals("/")) {
					if (cont == 1) {
						this.operationLong = (Long) listArray.get(0);
					} else {
						this.operationLong /= item;
					}
				}
			}
		}

		if (type.equals("Double")) {

			if (operation.equals("*") || operation.equals("/")) {
				return (T) this.operationDouble;
			} else {
				return (T) operationDouble;
			}

		} else if (type.equals("Integer")) {

			if (operation.equals("*") || operation.equals("/")) {
				return (T) this.operationInteger;
			} else {
				return (T) operationInteger;
			}

		} else if (type.equals("Float")) {

			if (operation.equals("*") || operation.equals("/")) {
				return (T) this.operationFloat;
			} else {
				return (T) operationFloat;
			}

		} else if (type.equals("Long")) {

			if (operation.equals("*") || operation.equals("/")) {
				return (T) this.operationLong;
			} else {
				return (T) operationLong;
			}
		}
		return null;
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to obtain the sum of all elements of the array entered by
	 * parameter. Enter in the parameter only the element array. This method returns
	 * the sum of all elements of the array entered by parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Long> processingArrayLong = new ArrayProcessingOperations<>();
	 * Long[] arrayLong = processingArrayLong.createArray(15L, 7L, 41L, 602L, 600L, 945L, 28L, 34L, 8888L, 89L);
	 * Long arrayLongSum = processingArrayLong.sumArrayElements(arrayLong);
	 * System.out.println("Sum of all elements of the Long array: " + arrayLongSum);
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para ober a soma de todos os elementos do array informado
	 * por parâmetro. Informe no parâmetro apenas o array de elementos. Este método
	 * retorna a soma de todos os elementos do array informado por parâmetro.
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return sum of all elements of the array entered by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 29/10/2018
	 *        <p>
	 *        Date Last Update: 04/11/2018
	 */
	public T sumArrayElements(T... array) {
		String operation = "+";
		return operateArrayElements(operation, array);
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to obtain the subtraction of all elements of the array entered by
	 * parameter. Enter in the parameter only the element array. This method returns
	 * the subtraction of all elements of the array entered by parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Float> processingArrayFloat = new ArrayProcessingOperations<>();
	 * Float[] arrayFloat = processingArrayFloat.createArray(15.9f, 7.8f, 41.3f, 602.1f, 600.2f, 945.5f, 28.7f, 34.4f,
	 * 		8888.0f, 89.5f);
	 * Float arrayFloatSubtract = processingArrayFloat.subtractArrayElements(arrayFloat);
	 * System.out.println("Subtract of all elements of the Float array: " + arrayFloatSubtract);
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para ober a subtração de todos os elementos do array
	 * informado por parâmetro. Informe no parâmetro apenas o array de elementos.
	 * Este método retorna a subtração de todos os elementos do array informado por
	 * parâmetro.
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return subtraction of all elements of the array entered by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 30/10/2018
	 *        <p>
	 *        Date Last Update: 04/11/2018
	 */
	public T subtractArrayElements(T... array) {
		String operation = "-";
		return operateArrayElements(operation, array);
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to obtain the multiplication of all elements of the array entered
	 * by parameter. Enter in the parameter only the element array. This method
	 * returns the multiplication of all elements of the array entered by parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Double> processingArrayDouble = new ArrayProcessingOperations<>();
	 * Double[] arrayDouble = processingArrayDouble.createArray(15.5, 7.1, 41.5, 602.8, 600.9, 945.2, 28.3, 34.0, 88.4,
	 * 		89.6);
	 * Double arrayDoubleMultiply = processingArrayDouble.multiplyArrayElements(arrayDouble);
	 * System.out.println("Multiply of all elements of the Double array: " + arrayDoubleMultiply);
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para ober a multiplicação de todos os elementos do array
	 * informado por parâmetro. Informe no parâmetro apenas o array de elementos.
	 * Este método retorna a multiplicação de todos os elementos do array informado
	 * por parâmetro.
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return multiplication of all elements of the array entered by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 30/10/2018
	 *        <p>
	 *        Date Last Update: 04/11/2018
	 */
	public T multiplyArrayElements(T... array) {
		String operation = "*";
		return operateArrayElements(operation, array);
	}

	/**
	 * (en-us)
	 * <p>
	 * Method used to obtain the division of all elements of the array entered by
	 * parameter. Enter in the parameter only the element array. This method returns
	 * the division of all elements of the array entered by parameter.
	 * <p>
	 * Simple example of use:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * ArrayProcessingOperations<Integer> processingArrayInteger = new ArrayProcessingOperations<>();
	 * Integer[] arrayInteger = processingArrayInteger.createArray(15, 7, 41, 602, 600, 945, 28, 34, 88, 89);
	 * Integer arrayIntegerDivision = processingArrayInteger.divisionArrayElements(arrayInteger);
	 * System.out.println("Division of all elements of the Integer array: " + arrayIntegerDivision + "\n");
	 * </pre>
	 * 
	 * </blockquote> (pt-br)
	 * <p>
	 * Método utilizado para ober a divisão de todos os elementos do array informado
	 * por parâmetro. Informe no parâmetro apenas o array de elementos. Este método
	 * retorna a divisão de todos os elementos do array informado por parâmetro.
	 * 
	 * @param array - Element array
	 * @author Tadeu Espíndola Palermo
	 * @return division of all elements of the array entered by parameter.
	 * @since v1.1
	 *        <p>
	 *        Date Create: 30/10/2018
	 *        <p>
	 *        Date Last Update: 04/11/2018
	 */
	public T divisionArrayElements(T... array) {
		String operation = "/";
		return operateArrayElements(operation, array);
	}

}