// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      FunctionList.java
// =============================================================================
// Description:
// This file is the implementation of the FunctionList class.
// =============================================================================

package com.ProjectJulia.Statements;

import java.util.LinkedList;

import com.ProjectJulia.Parser.Identifier;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class FunctionList extends StatementList {
	
	// =========================================================================
    // Instance variable
    // Defines a list of statements
    // =========================================================================
    
    private LinkedList<FunctionStatement> fstatements = new LinkedList<FunctionStatement>();
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Does not takes any value as the list starts empty
    // =========================================================================
    
    public FunctionList(){}
    
 // =========================================================================
    // void addStatement
    // Enables to add a Statement object to the list
    // =========================================================================
    
    public void addStatement(FunctionStatement stm){
        this.fstatements.add(stm);
    }
	
	// =========================================================================
    // FunctionStatement searchStatement
    // @param FunctionStatement new_id
    // looks if an identifier is present in the list of functions
    // =========================================================================
    
    public FunctionStatement searchStatement(FunctionStatement new_id){
        int ref = this.fstatements.indexOf(new_id);
        if(ref!=(-1))
            return fstatements.get(ref);
        else return null;
    }
    
    // =========================================================================
    // FunctionStatement searchStatement
    // @param Identifier new_id
    // looks if an identifier is present in the list of functions
    // =========================================================================
    
    public FunctionStatement searchStatement(Identifier new_id){
    	FunctionStatement found = null;
        for(FunctionStatement fs: this.fstatements){
            if(fs.getIdentifier() == null ? new_id == null : fs.getIdentifier().getName().equals(new_id.getName()))
                return fs;
        }
        return found;
    }
    

}
