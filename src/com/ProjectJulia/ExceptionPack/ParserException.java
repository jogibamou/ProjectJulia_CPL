
// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      ParserException.java
// =============================================================================
// Description:
// This file is the implementation of the ParserException class.
// =============================================================================


package com.ProjectJulia.ExceptionPack;

/**
*
* @author Giovanni Bamou Nana #000855399
*/

//=============================================================================
//Exception class designed for the scanner
//=============================================================================
public class ParserException extends RuntimeException{
	public ParserException(String msg) {
        super(msg);
    }
}
