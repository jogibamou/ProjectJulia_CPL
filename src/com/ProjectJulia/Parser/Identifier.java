// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      Identifier.java
// =============================================================================
// Description:
// This file is the implementation of the Identifier class.
// =============================================================================


package com.ProjectJulia.Parser;

import java.util.Objects;

/**
*
* @author Giovanni Bamou Nana #000855399
*/

public class Identifier {

	// =========================================================================
    // Instance variables
    // idName defines the name of the identifier
    // expr defines the expression assigned to the identifer
    // =========================================================================
    
    private String idName;
    private int type_id = 1;
    private int value = 0;
    
    // =========================================================================
    // Constant variables
    // define the datatype of the identifier
    // =========================================================================
    
    public final int INT_TYPE = 1;
    public final int INT_ARRAY_TYPE = 2;
    public final int SHORT_TYPE = 3;
    public final int MVOID_TYPE = 4;
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Helps defining an instance of the class Identifier
    // =========================================================================
    
    public Identifier(String name){
        this.idName = name;
    }
    
    // =========================================================================
    // String getName
    // returns the name of the identifier
    // =========================================================================
    
    public String getName(){
        return idName;
    }
    
    // =========================================================================
    // int getType
    // return the type code of the identtifier
    // =========================================================================
    
    public int getType(){
        return type_id;
    }
    
    // =========================================================================
    // void setType
    // @param int type
    // Assign a type code to the identifier
    // =========================================================================
    
    public void setType(int type){
        this.type_id = type;
    }
    
    // =========================================================================
    // void setValue
    // @param int value
    // assign a value to the identifier
    // =========================================================================
    
    public void setValue(int value){
        this.value = value;
    }
    
    // =========================================================================
    // int getValue
    // returns the value contained in the identifier
    // =========================================================================
    
    public int getValue(){
        return this.value;
    }
    
    // =========================================================================
    // static int evaluate
    // launch the execution of the identifier expression
    // =========================================================================
    
    public static int evaluate(){
        return 0;
    }
    

    // =========================================================================
    // COMPARISON FUNCTIONS
    // the comparison will be based on the name of the indentifier
    // Two identifiers cannot have the same name
    // =========================================================================
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Identifier other = (Identifier) obj;
        if (!Objects.equals(this.idName, other.idName)) {
            return false;
        }
        return true;
    }
    
    
}
