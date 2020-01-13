// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      WhileStatement.java
// =============================================================================
// Description:
// This file is the implementation of the WhileStatement class.
// =============================================================================

package com.ProjectJulia.Statements;

import com.ProjectJulia.Executer.BooleanExpression;
import com.ProjectJulia.Scanner.Tokenizer;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class WhileStatement extends Statement {
    
    // =========================================================================
    // Instance variables
    // @param BooleanExpression be defines the condition to meet
    // @param StatementList sl is the list of statements to execute
    // =========================================================================
    
    private BooleanExpression be;
    private StatementList sl;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // takes a Boolean Expression and a Statement List
    // =========================================================================
    
    public WhileStatement(BooleanExpression boe, StatementList sl){
        super(RSVP_WHIL_N.getKey());
        this.be = boe;
        this.sl = sl;
    }
    
    // =========================================================================
    // _CONSTRUCTOR_
    // takes a Boolean Expression
    // =========================================================================
    
    public WhileStatement(BooleanExpression boe){
        super(RSVP_WHIL_N.getKey());
        this.be = boe;
    }
    
    // =========================================================================
    // void execute
    // OVERRIDING FUNCTION
    // Proceeds with the execution of the loop while the condition is met
    // =========================================================================
    
    @Override
    public void execute(){
        while(this.be.evaluate()){
            for(Statement st: sl.getStatements()){
                st.execute();
            }
        }
    }
    
    // =========================================================================
    // void setStatementList
    // Set the list of statement included in the WHILE control
    // =========================================================================
    
    public void setStatementList(StatementList stl){
        this.sl = stl;
    }
    
    public BooleanExpression getBooleanExpression(){
        return this.be;
    }

    public StatementList getStatementList(){
        return this.sl;
    }
    
}
