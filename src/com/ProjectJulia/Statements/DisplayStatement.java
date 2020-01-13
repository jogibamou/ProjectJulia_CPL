// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      DisplayStatement.java
// =============================================================================
// Description:
// This file is the implementation of the DisplayStatement class.
// =============================================================================

package com.ProjectJulia.Statements;

import com.ProjectJulia.Scanner.Tokenizer;
import com.ProjectJulia.Statements.funct.Argument;
import com.ProjectJulia.Statements.funct.ArgumentsList;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class DisplayStatement extends Statement{
    
    // =========================================================================
    // Instance variable
    // @param ArgumentList al
    // Will be used to list the different elements to print
    // =========================================================================
    
    private ArgumentsList al;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Takes an ArgumentList
    // =========================================================================
    
    public DisplayStatement(ArgumentsList al){
        super(RSVP_PRIN_N.getKey());
        this.al = al;
    }
    
    // =========================================================================
    // void execute
    // OVERRIDING FUNCTION
    // Display the arguments in the command line
    // =========================================================================
    
    public void execute(){
        System.out.println();
        for(Argument ar: this.al.getArgumentList()){
            System.out.print(ar.getValueArg()+" ");
        }
        System.out.println("\n");
    }
    
    
    
}
