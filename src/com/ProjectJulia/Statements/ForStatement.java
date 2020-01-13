// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      ForStatement.java
// =============================================================================
// Description:
// This file is the implementation of the ForStatement class.
// =============================================================================

package com.ProjectJulia.Statements;

import com.ProjectJulia.Scanner.Tokenizer;
import javafx.util.Pair;


/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class ForStatement extends Statement{
	
	 // =========================================================================
    // Instance variables
    // @param int value_start gives the beginning point
    // @param int value_end gives the ending point
    // @param StatementList sl gives the list of statements ot be executed
    // =========================================================================
    //private static final Pair < Integer, String > RSVP_FOR_N = new Pair < > (5004, "RSVP_FOR_N");

    private int value_start;
    private int value_end;
    private StatementList sl;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Takes a statement list, a start point and end point
    // =========================================================================
    
    public ForStatement(StatementList sl,int start, int end){
        super(RSVP_FOR_N.getKey());
        this.sl = sl;
        this.value_start = start;
        this.value_end = end;
    }
    
    // =========================================================================
    // boolean reach
    // Verifies if the starting value is equal to the ending point
    // =========================================================================
    
    private boolean reach(){
        //return this.value_end == this.value_start;
        return this.value_end == this.getIteratorValue();

    }
    
    // =========================================================================
    // void execute
    // OVERRIDING FUNCTION
    // Proceeds with the execution of the loop until the condition is met
    // =========================================================================
    
    //@Override
    public void execute(){

        while(!reach()){
            for(Statement st: this.sl.getStatements()){
                st.execute();
            }
            this.update_iterator(this.getIteratorValue() + 1);
        }
    }
    
    
    
	
}
