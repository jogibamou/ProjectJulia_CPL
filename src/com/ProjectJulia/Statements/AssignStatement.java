// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      AssignStatement.java
// =============================================================================
// Description:
// This file is the implementation of the AssignStatement class.
// =============================================================================

package com.ProjectJulia.Statements;

import com.ProjectJulia.Executer.ArithmeticExpression;
import com.ProjectJulia.Parser.Identifier;
import com.ProjectJulia.Scanner.Tokenizer;
import javafx.util.Pair;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class AssignStatement extends Statement {
	
	// =========================================================================
    // Instance variables
    // @param BooleanExpression be represent the expression to be evaluated 
    // @param StatementList sl represents the different statements interior
    // =========================================================================
    
	ArithmeticExpression ae;
	Identifier id;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // @param BooleanExpression be
    // =========================================================================
    
    public AssignStatement(Identifier new_id, ArithmeticExpression new_ae){
        super(ASSIGNMENT_OPERATOR_N.getKey());
        this.ae = new_ae;
        this.id = new_id;
    }
    
    // =========================================================================
    // int getResult
    // provides the result of the arithmethic expression
    // =========================================================================
    
    public int getResult(){
        return ArithmeticExpression.eval(ae);
    }
    
    // =========================================================================
    // void execute
    // OVERRIDING FUNCTION
    // Verifies the condition then either execute or not the statement list
    // =========================================================================
    
    @Override
    public void execute(){
    	id.setValue(ArithmeticExpression.eval(ae));
    }
        
    
}
