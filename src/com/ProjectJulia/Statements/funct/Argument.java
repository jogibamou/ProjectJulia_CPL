// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      Argument.java
// =============================================================================
// Description:
// This file is the implementation of the Argument class.
// =============================================================================

package com.ProjectJulia.Statements.funct;

import com.ProjectJulia.Parser.Identifier;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class Argument {
	// =========================================================================
    // Instance variable
    // @param String argument_value
    // Used as reference to the display of elements
    // =========================================================================
    
    //private String argument_value;
    private Identifier argument_name = null;
    private String argument_value;
    private int arg_type;
    
    public final int IDENTIFIER_TYPE = 1;
    public final int OTHER_TYPE = 2;
    
    
    // =========================================================================
    // _CONSTRUCTOR_
    // takes an Identifier
    // =========================================================================
    
    //public Argument(Identifier id){
    public Argument(Identifier id){

    	if (id != null) {
    		//this.argument_value = Integer.toString(id.getValue());
    		this.argument_name = id;
    		this.arg_type = IDENTIFIER_TYPE;
    	}
    }
    
    // =========================================================================
    // _CONSTRUCTOR_
    // takes an int
    // =========================================================================
    
    public Argument(int value){
        this.argument_value = Integer.toString(value);
		this.arg_type = OTHER_TYPE;

    }
    
    // =========================================================================
    // _CONSTRUCTOR_
    // takes a String
    // =========================================================================
    
    public Argument(String content){
        this.argument_value = content;
		this.arg_type = OTHER_TYPE;

    }
    
    // =========================================================================
    // static void appendArg
    // @param Argument arg is the argument to which we attach element
    // @param String content is the element added
    // append a value to the original Argument arg
    // =========================================================================
    
    public static void appendArg(Argument arg, String content){
        arg.setValueArg(arg.getValueArg()+" "+content);
    }
    
    // =========================================================================
    // String getValueArg
    // Returns the value of the argument
    // =========================================================================
    
    public String getValueArg(){
    	if (this.arg_type == OTHER_TYPE) {
            return this.argument_value;
    	}
    	else {
    		return Integer.toString(this.argument_name.getValue());
    	}

    }
    
    // =========================================================================
    // void setValueArg
    // takes a String value
    // enables to modify or assign a value to an argument
    // =========================================================================
    
    public void setValueArg(String value){
        this.argument_value = value;
		this.arg_type = OTHER_TYPE;
    }
    
    public void setValueArg(Identifier value){
        this.argument_name = value;
		this.arg_type = IDENTIFIER_TYPE;
    }
    
}
