// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      BooleanExpression.java
// =============================================================================
// Description:
// This file is the implementation of the BooleanExpression class.
// =============================================================================

package com.ProjectJulia.Executer;

import com.ProjectJulia.Scanner.Tokenizer;

/**
*
* @author Giovanni Bamou Nana #000855399
*/

public class BooleanExpression extends Tokenizer{
	private ArithmeticExpression left;
    private ArithmeticExpression right;
    private int op;
    
    public BooleanExpression(ArithmeticExpression l, int op, ArithmeticExpression r){
        this.left = l;
        this.right = r;
        this.op = op;
    }
    
    public ArithmeticExpression getLeft(){
        return this.left;
    }
    
    public ArithmeticExpression getRight(){
        return this.right;
    }
    
    public int getOperator(){
        return this.op;
    }
    
    public boolean evaluate(){
        int left_value = ArithmeticExpression.eval(this.left);
        int right_value = ArithmeticExpression.eval(this.right);
        boolean result = false;
        
        
        if (this.getOperator() == EQ_OPERATOR_N.getKey()) {
                result = (left_value == right_value);
        }
        else if (this.getOperator() == GE_OPERATOR_N.getKey()) {
                result = (left_value >= right_value);
        }
        else if (this.getOperator() == GT_OPERATOR_N.getKey()) {
                result = (left_value > right_value);
        }
        else if (this.getOperator() == LE_OPERATOR_N.getKey()) {
                result = (left_value <= right_value);
        }
        else if (this.getOperator() == LT_OPERATOR_N.getKey()) {
                result = (left_value < right_value);
        }
        else if (this.getOperator() == NE_OPERATOR_N.getKey()) {
                result = (left_value != right_value); //We have integers and it is the only valid representations
        }
        
        return result;
        
    }
}
