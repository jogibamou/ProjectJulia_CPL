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

import com.ProjectJulia.Statements.StatementList;
import com.ProjectJulia.Statements.funct.Argument;
import com.ProjectJulia.Statements.funct.ArgumentsList;
import com.ProjectJulia.Parser.Identifier;


/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class FunctionStatement extends Statement {
	
	private Identifier id = null;
	private ArgumentsList al = null;
	private StatementList sl = null;
	
	public FunctionStatement(Identifier new_id, ArgumentsList new_al, StatementList new_sl){
		super(RSVP_FUNC_N.getKey());
		this.id = new_id;
		this.al = new_al;
		this.sl = new_sl;	
	}
	
	public FunctionStatement(Identifier new_id){
		super(RSVP_FUNC_N.getKey());
		this.id = new_id;
	}
	
	// =========================================================================
    // void setIdentifier
    // assign the Identifier object to the iterator of the function
    // The identifier used is taken form the Identifier table
    // =========================================================================
    
    public void setIdentifier(Identifier id){
        this.id = id;
    }
    
    // =========================================================================
    // void setIdentifier
    // assign the Identifier object to the iterator of the function
    // The identifier used is taken form the Identifier table
    // =========================================================================
    
    
    public void setArgumentsList(ArgumentsList al){
		this.al = al;
    }
    
    // =========================================================================
    // void setIdentifier
    // assign the Identifier object to the iterator of the function
    // The identifier used is taken form the Identifier table
    // =========================================================================
    
    
    public void setStatementList(StatementList sl){
		this.sl = sl;	
    }
    
    // =========================================================================
    // Identifier getIdentifier
    // This function returns the identifier of the function
    // This Identifier is the same as the one found in the Identifier Table
    // =========================================================================
    
    public Identifier getIdentifier(){
        return this.id;
    }
	
	
	//@Override
    public void execute(){
    	if (sl != null) {
    		for(Statement st: this.sl.getStatements()){
    			st.execute();
    		}  
    	}
    }	

}
