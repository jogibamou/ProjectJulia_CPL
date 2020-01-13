// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      ParseError.java
// =============================================================================
// Description:
// This file is the implementation of the ParseError class.
// =============================================================================


package com.ProjectJulia.Parser;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class ParseError extends Exception {

    private String errorMessage;

    ParseError(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return String.format("Parsing Error %s", errorMessage);
    }


}
