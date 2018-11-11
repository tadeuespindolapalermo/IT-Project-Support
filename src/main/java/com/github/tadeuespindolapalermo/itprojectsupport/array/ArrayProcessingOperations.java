package com.github.tadeuespindolapalermo.itprojectsupport.array;

import java.util.ArrayList;
import java.util.List;

import com.github.tadeuespindolapalermo.itprojectsupport.utilities.Validators;

@SuppressWarnings("unchecked")
public class ArrayProcessingOperations <T> {
	
	// Attributes used only for multiplication and division operations
	private Long operationLong;
	private Float operationFloat;
	private Double operationDouble;
	private Integer operationInteger;		
	
	public ArrayProcessingOperations () {
		operationLong = 1L;
		operationFloat = 1f;
		operationDouble = 1.0;
		operationInteger = 1;		
	}	
	
	public T[] createArray(T... array) {       	
    	return array;    	
    } 	
   
    public int getArrayLength(T... array) {         	    	
    	return array.length;     	
    }          
    
    private List<Object> checkTypeAllowed(T... array) {
    	List<Object> listArray = new ArrayList<>();
    	if(array[0] instanceof Integer || array[0] instanceof Long || array[0] instanceof Double    			
    			|| array[0] instanceof Float || array[0] instanceof Short 
    			|| array[0] instanceof Byte) {
    		listArray.add(array);    		
    	}
    	return listArray;
    }    
   
    private int checkTypeInformed(T... array) {
    	List<Object> listArray = new ArrayList<>();
    	int numberType = 0;
    	if(array[0] instanceof Integer || array[0] instanceof Long || array[0] instanceof Double    			
    			|| array[0] instanceof Float) {
    		listArray.add(array);  
    		if(array[0] instanceof Integer) {
    			numberType = 1;
    		} else if(array[0] instanceof Long) {
    			numberType = 2;
    		} else if(array[0] instanceof Double) {
    			numberType = 3;
    		} else if(array[0] instanceof Float) {
    			numberType = 4;
    		} 
    		return numberType;
    	}
    	return 0;
    }    
   
    private T convertValue(int valueComparator, String valueConversion) {
    	Number valueConverter = null;
    	if(valueComparator == 1) {
    		valueConverter = Integer.parseInt(valueConversion);    		
    		return (T) valueConverter;
    	} else if(valueComparator == 2) {
    		valueConverter = Long.parseLong(valueConversion);
    		return (T) valueConverter;
    	} else if(valueComparator == 3) {
    		valueConverter = Double.parseDouble(valueConversion);
    		return (T) valueConverter;
    	} else if(valueComparator == 4) {
    		valueConverter = Float.parseFloat(valueConversion);
    		return (T) valueConverter;
    	} 
    	return null;
    }        
    
	private T operateArrayElements(String operation, T... array) {        	
    	if(Validators.collectionValid(checkTypeAllowed(array))) {
    		T outputOperation = null; T returnOfOperation = null; int i;        
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
    		returnOfOperation = processOperation(typeArray, type, outputOperationDouble, outputOperationInteger, outputOperationFloat, outputOperationLong, listArray, operation);
    		if(!(typeArray.equals("Double") || typeArray.equals("Integer") || typeArray.equals("Float") || typeArray.equals("Long"))) {
    			System.out.println("The type informed is not applicable to the sum functionality of the elements of an array!");
    		} 
    		return returnOfOperation;   		
    	}
    	return null;			    	
    } 		
	
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
					if(cont == 1) {
						operationDouble -= item * -1;
					} else {
						operationDouble -= item;
					}
				} else if (operation.equals("*")) {
					this.operationDouble *= item;					
				}  else if (operation.equals("/")) {
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
					if(cont == 1) {
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
					if(cont == 1) {
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
					if(cont == 1) {
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
		
		if(type.equals("Double")) {
			
			if(operation.equals("*") || operation.equals("/")) {
				return (T) this.operationDouble;
			} else {
				return (T) operationDouble;
			}
			
		} else if(type.equals("Integer")) {
			
			if(operation.equals("*") || operation.equals("/")) {
				return (T) this.operationInteger;
			} else {
				return (T) operationInteger;
			}
			
		} else if(type.equals("Float")) {
			
			if(operation.equals("*") || operation.equals("/")) {
				return (T) this.operationFloat;
			} else {
				return (T) operationFloat;
			}
			
		} else if(type.equals("Long")) {
			
			if(operation.equals("*") || operation.equals("/")) {
				return (T) this.operationLong;
			} else {
				return (T) operationLong;
			}
		} 
		return null;
    }           
   
	public T sumArrayElements(T... array) {		
		String operation = "+";
		return operateArrayElements(operation, array);
	}		
	
	public T subtractArrayElements(T... array) {
		String operation = "-";
		return operateArrayElements(operation, array);
	}	
	
	public T multiplyArrayElements(T... array) {
		String operation = "*";
		return operateArrayElements(operation, array);
	}	
	
	public T divisionArrayElements(T... array) {
		String operation = "/";
		return operateArrayElements(operation, array);
	}	
   
}