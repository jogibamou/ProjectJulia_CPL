package com.ProjectJulia.ExceptionPack;

//=============================================================================
//Exception class designed for the scanner
//=============================================================================


public class ParserException extends RuntimeException{
	public ParserException(String msg) {
        super(msg);
    }
}
