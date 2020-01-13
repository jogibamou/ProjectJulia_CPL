// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      ArithmeticExpression.java
// =============================================================================
// Description:
// This file is the implementation of the ArithmeticExpression class.
// =============================================================================

package com.ProjectJulia.Executer;

import com.ProjectJulia.Scanner.Tokenizer;
import com.ProjectJulia.Parser.Identifier;


/**
*
* @author Giovanni Bamou Nana #000855399
*/

public class ArithmeticExpression extends Tokenizer{

	private int token_code = LITERAL_INTEGER_N.getKey();
    //private String sequence = "0";
    private Identifier sequence = null;

    private ArithmeticExpression left = null;
    private ArithmeticExpression right = null;
    
    //public ArithmeticExpression(int code, String seq){

    public ArithmeticExpression(int code, Identifier seq){
        this.token_code = code;
        this.sequence = seq;
    }
    
    public ArithmeticExpression(){}
    
    public void setLeftNode(ArithmeticExpression new_left){
        this.left = new_left;
    }
    
    public void setRightNode(ArithmeticExpression new_right){
        this.right = new_right;
    }
    
//    public void setSequence(String seq){

    public void setSequence(Identifier seq){
        this.sequence = seq;
    }
    
    public void setTokenCode(int code){
        this.token_code = code;
    }

    //public String getSequence(){

    public Identifier getSequence(){
        return this.sequence;
    }
    
    public int getTokenCode(){
        return this.token_code;
    }
    
    public ArithmeticExpression getLeftNode(){
    	if(this.left == null) {
    		this.left = new ArithmeticExpression();
    		return this.left;
    	}
    	else {
    		return this.left;
    	}
    }
    
    public ArithmeticExpression getRightNode(){
    	if(this.left == null) {
    		this.right = new ArithmeticExpression();
    		return this.right;
    	}
    	else {
    		return this.right;
    	}
    }
    
    public static int eval(ArithmeticExpression ae){
        int value = 0;
        
        if (ae.getTokenCode() == IDENTIFIER_N.getKey()) {
                //value = Integer.parseInt(ae.getSequence());
                value = ae.getSequence().getValue();

        }
        else if (ae.getTokenCode() ==  LITERAL_INTEGER_N.getKey()) {
               // value = Integer.parseInt(ae.getSequence());
                value = ae.getSequence().getValue();

	    }
        else if (ae.getTokenCode() ==  ADD_OPERATOR_N.getKey()) {
                value = eval(ae.getLeftNode()) + eval (ae.getRightNode());
        }
        else if (ae.getTokenCode() ==  SUB_OPERATOR_N.getKey()) {
                value = eval(ae.getLeftNode()) - eval(ae.getRightNode());
        }
        else if (ae.getTokenCode() ==  MUL_OPERATOR_N.getKey()) {
                value = eval(ae.getLeftNode()) * eval(ae.getRightNode());
        }
        else if (ae.getTokenCode() ==  DIV_OPERATOR_N.getKey()) {
                value = eval(ae.getLeftNode()) / eval(ae.getRightNode());
        }
        
        return value;
    }
	
}
