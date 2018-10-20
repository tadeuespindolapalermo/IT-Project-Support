package br.com.tadeudeveloper.help.projects.array;

/**
 * (en-us)
 * <p>
 * Utility class responsible for processing operations and manipulation in data structure of type Array
 * <p>
 * (pt-br)
 * <p>
 * Classe utilitária responsável por processar operações e manipulação em estrutura de dados do tipo Array 
 * 
 * @author Tadeu Espíndola Palermo 
 * @since v1.0
 * <p>
 * Date: 19/10/2018
 */
public class ArrayProcessingOperations {	
	
	/**
	 * (en-us)<p>
	 * 
	 * Method used for creating array of integer type elements.
	 * Enter in the parameter only the array elements.
	 * This method returns the array entered in the method parameter.
	 * 
	 * Simple example of use:<p>
	 * <blockquote><pre>
	 * createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89);
	 * </pre></blockquote>
	 * 
	 * (pt-br)<p>
	 * Método utilizado para a criação de array de elementos do tipo inteiro.
	 * Informe no parâmetro apenas os elementos do array.
	 * Este método retorna o array informado no parâmetro do método.
	 * 
	 * Exemplo simples de uso:<p>
	 * <blockquote><pre>
	 * createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89); 	
	 * </pre></blockquote><p>
	 * 
	 * @param array Array elements
	 * @author Tadeu Espíndola Palermo                
	 * @return Array of elements entered in the method parameter
	 * @since v1.0
	 * <p>
	 * Date: 19/10/2018
	 */
	public int[] createArrayOfIntegers(int... array) {       	
    	return array;    	
    }  	
    
    /**
     * (en-us)<p>
	 * Method used to get array length.
	 * Enter in the parameter only the element array.
	 * This method returns the length of the array entered in the method parameter.
	 * <p>
	 * Simple example of use:
	 * <p><blockquote><pre>
	 * ArrayProcessingOperations processing = new ArrayProcessingOperations();
	 * int[] array = ap.createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89);	
	 * processing.getArrayLength(array);
	 * </pre></blockquote>
	 * (pt-br)<p>
	 * Método utilizado para obter o comprimento do array.
	 * Informe no parâmetro apenas o array de elementos.
	 * Este método retorna o comprimento do array informado no parâmetro do método.
	 * <p>
	 * Exemplo simples de uso:
	 * <p><blockquote><pre>
	 * ArrayProcessingOperations processing = new ArrayProcessingOperations();
	 * int[] array = ap.createArrayOfIntegers(15, 7, 41, 602, 600, 945, 28, 34, 88, 89);	
	 * processing.getArrayLength(array);	
	 * </pre></blockquote>
	 * 
	 * @param array Element array        
	 * @author Tadeu Espíndola Palermo	
	 * @return Length of array entered in method parameter
	 * @since v1.0
	 * <p>
	 * Date: 19/10/2018
	 */
    public int getArrayLength(int... array) {         	    	
    	return array.length;     	
    }   
   
}
