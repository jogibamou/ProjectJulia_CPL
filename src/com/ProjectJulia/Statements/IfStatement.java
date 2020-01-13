// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      IfStatement.java
// =============================================================================
// Description:
// This file is the implementation of the IfStatement class.
// =============================================================================

package com.ProjectJulia.Statements;

import com.ProjectJulia.Executer.BooleanExpression;
import com.ProjectJulia.Scanner.Tokenizer;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class IfStatement extends Statement{
    
    // =========================================================================
    // Instance variables
    // @param BooleanExpression be represent the expression to be evaluated 
    // @param StatementList sl represents the different statements interior
    // =========================================================================
    
    BooleanExpression be;
    StatementList sl;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // @param BooleanExpression be
    // =========================================================================
    
    public IfStatement(BooleanExpression be){
        super(RSVP_IF_N.getKey());
        this.be = be;
    }
    
    // =========================================================================
    // boolean getResult
    // provides the result of the boolean expression
    // =========================================================================
    
    public boolean getResult(){
        return be.evaluate();
    }
    
    // =========================================================================
    // void execute
    // OVERRIDING FUNCTION
    // Verifies the condition then either execute or not the statement list
    // =========================================================================
    
    @Override
    public void execute(){
        //if(this.getResult()){
            for(Statement st: sl.getStatements()){
                st.execute();
            }
        //}
    }
    
    // =========================================================================
    // void setStatementList
    // Set the list of statement included in the IF control
    // =========================================================================
    
    public void setStatementList(StatementList stl){
        this.sl = stl;
    }
    
    
}
