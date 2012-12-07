package math.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Calculator 
{
	private static final String INPUT_FILE_PATH = "c:\\input.txt";
	
	private static final String OUTPUT_FILE_PATH = "c:\\output.txt";
	
	private static final String ERROR_BRACKET_WAS_NOT_CLOSED ="Bracket ( was not closed in expression! ";
	
	private static String calculationStatus = "";
	
	private static final String ERROR_PARSING_DOUBLE = "Operand is not Double type!";

	private static final String ERROR_WRONG_NUMBER_OF_OPERATIONS = "Wrong Number of math operations";

	private static final String ERROR_LETTER_FOUND = "Letters are not allowed";

	private static final String ERROR_UNKNOWN_OPERATION = "Unknown operation";

	private static final String ERROR_BRACKETS_ARE_PUT_UNCORRECT = "Bracket ) is not opened";
	
	private static final String ERROR_EMPTY_EXPRESSION = "Expression could not be empty!";
    
	public static String getCalculationStatus() {
		return calculationStatus;
	}
    
    //return 1 if symbol is digit, 2 - if operation, 3 - if "(", 4 - if ")", 5 - fload delimiter . or , , 6- unknown    
    public int symbolType(char ch) throws Exception{
        if (ch >= '0' && ch <= '9'){
            return 1;
        }else if ( ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^'){
            return 2;
        }else if (ch == '(' ){
            return 3;
        }else if( ch == ')'){
            return 4;
        }else if (ch == '.' || ch == ',' ){
            return 5;
        } else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
        	calculationStatus = ERROR_LETTER_FOUND + " "+ ch;
        	throw new Exception();
        }else{
        	calculationStatus = ERROR_UNKNOWN_OPERATION + " "+ ch;
        	throw new Exception();
        }
    }
    public String calculateExpression(String s) throws Exception{
		s = s.replaceAll(" ", "");
		if (s.length() == 0){
        	calculationStatus = ERROR_EMPTY_EXPRESSION;
        	throw new Exception();
		}
        boolean flag = true;
        String inputString = s;
        
        //add missed * near brackets
        s = addMissedOperations(s);
        
        isBracketsCorrect(s);
        //use recursive, if ( is found call itself
        while (flag){
            int start = -1;
            int end = -1;
            for (int i = 0; i< s.length(); i++){
                if (symbolType(s.charAt(i)) == 3){
                    int isClosed = 1;
                    int j = i+1;
                    start = i;
                    while (isClosed > 0 && j <s.length()){
                        if (symbolType(s.charAt(j)) == 3) isClosed ++;
                        if (symbolType(s.charAt(j)) == 4) isClosed --;
                        j++;
                    }
                    // ( is not closed
                    if (isClosed > 0) {
                    	calculationStatus = ERROR_BRACKET_WAS_NOT_CLOSED;
                    	throw new Exception();
                    }
                    end = j-1;
                    break;
                }
            }
            if (start  < 0){
                flag = false;
            }else{
            	//recursive call if () was found
                String s1 = s.substring(0,start) + calculateExpression(s.substring(start+1, end));
                String s2 = "";
                if (end+1 < s.length()){
                    s2 = s.substring(end+1);
                }   
                s =s1+s2;
            }    
        }    
        
        List <String>operands = new ArrayList();
        List <String>operations = new ArrayList();
        flag = true;
        //use recursive, if ( is found call itself
        while (flag){
            int start = -1;
            int end = -1;
            for (int i = 0; i< s.length(); i++){
                if (symbolType(s.charAt(i)) == 2 && i!=0){
                    operands.add(s.substring(0,i));
                    operations.add(s.substring(i, i+1));
                    start = i;
                    break;
                }
            }
            if (start  < 0){
                flag = false;
                operands.add(s.substring(0));
            }else{
                s = s.substring(start+1);
            }    
        }
        
        if ( (operands.size() - operations.size()) != 1){
        	calculationStatus = ERROR_WRONG_NUMBER_OF_OPERATIONS + " " + inputString; 
        	throw new Exception();
        }
        if (operands.size() == 1){
        	return operands.get(0);
        }
        
        List <String>operandsSum = new ArrayList();
        List <String>operationsSum = new ArrayList();
        for (int index =0; index < operations.size(); index++){
        	String op = operations.get(index);
            if (op.charAt(0)== '*' || op.charAt(0)== '/' || op.charAt(0)== '^'){
           		doPriorityMathOperation(operands, index, op);
            }else{
            	operandsSum.add(operands.get(index));
            	operationsSum.add(op);
            }
        	if (index == operations.size()-1){
            	operandsSum.add(operands.get(index+1));
        	}
        }
        //value will contain result
        Double value = Double.parseDouble(operandsSum.get(0));
        for (int index =0; index<operationsSum.size(); index++){
        	String op = operationsSum.get(index);
        	try{
	            if (op.charAt(0)== '+'){
	                value += Double.parseDouble(operandsSum.get(index+1));
	            }else if(op.charAt(0)== '-'){
	                value -= Double.parseDouble(operandsSum.get(index+1));
	            }
        	}catch(NumberFormatException ex){
    			calculationStatus = ERROR_PARSING_DOUBLE;
    			throw new Exception();
        	}
        }
        return value.toString();
    }
    
    private void isBracketsCorrect(String s) throws Exception{
    	int isClosed = 0;
    	for (int index = 0; index<s.length(); index++){
            if (symbolType(s.charAt(index)) == 3) isClosed ++;
            if (symbolType(s.charAt(index)) == 4) isClosed --;
            if(isClosed < 0){
            	calculationStatus = ERROR_BRACKETS_ARE_PUT_UNCORRECT;
            	throw new Exception();
        		
        	}
        }
        if (isClosed > 0) {
        	calculationStatus = ERROR_BRACKET_WAS_NOT_CLOSED;
        	throw new Exception();
        }
    }	

	//will replace substrings '4(' or ')3' by '4*(' and ')*3' 
    private String addMissedOperations(String s) throws Exception{
    	String result = s;
    	for (int index =0; index< s.length()-1; index++){
    		int thisSymbolType = symbolType(s.charAt(index));
    		int nextSymbolType = symbolType(s.charAt(index+1));
    		if ((thisSymbolType ==1 && nextSymbolType ==3) || (thisSymbolType ==4 && nextSymbolType ==1) || (thisSymbolType ==4 && nextSymbolType ==3)){
    			String oldSubstring = "" + s.charAt(index)+ s.charAt(index+1);
    			String newSubstring = "" + s.charAt(index)+ "*"+s.charAt(index+1);
    			result = result.replace(oldSubstring, newSubstring);
    		}
    	}
		return result;
	}

	//this function do high priority operations like '*', '/', '^' 
    private void doPriorityMathOperation(List<String> operands, int index, String op)throws Exception{
		try{
	    	if (op.charAt(0)== '*'){
	            Double d = Double.parseDouble(operands.get(index))* Double.parseDouble(operands.get(index+1));
	            replaceOperands(operands, index, d);
	        }else if(op.charAt(0)== '/'){
	            Double d = Double.parseDouble(operands.get(index))/ Double.parseDouble(operands.get(index+1));
	            replaceOperands(operands, index, d);
	        }else if (op.charAt(0)== '^'){
	            Double d = Math.pow(Double.parseDouble(operands.get(index)), Double.parseDouble(operands.get(index+1)));
	            replaceOperands(operands, index, d);
	        }
		}catch (NumberFormatException ne){
			calculationStatus = ERROR_PARSING_DOUBLE;
			throw new Exception();
		}
    }
    
	private void replaceOperands(List<String> operands, int index, Double d) {
		operands.remove(index);
		operands.remove(index);
		operands.add(index, d.toString());
		operands.add(index, d.toString());
	}
}
