// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      Statement.java
// =============================================================================
// Description:
// This file is the implementation of the Statement class.
// =============================================================================

package com.ProjectJulia.Statements;

import  com.ProjectJulia.ExceptionPack.ExecuterException;
import  com.ProjectJulia.Parser.Identifier;
//import com.ProjectJulia.Scanner.Token;
import com.ProjectJulia.Scanner.Tokenizer;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class Statement extends Tokenizer {
    
    // =========================================================================
    // Instance variables
    // @param type_def define the statement type number
    // @param stm_type gives the statement type name
    // @iterator is a partciular instance variable used for loop definitions
    // =========================================================================
    
    private int type_def;
    private String stm_type;
    private Identifier iterator;
    
    // =========================================================================
    // Constant defined for type def
    // Will be used to define the statement type name
    // =========================================================================
    
    private final int if_statement = RSVP_IF_N.getKey();
    private final int else_statement = RSVP_ELSE_N.getKey();
    private final int for_statement = RSVP_FOR_N.getKey();
    private final int while_statement = RSVP_WHIL_N.getKey();
    private final int print_statement = RSVP_PRIN_N.getKey();
    private final int do_statement = RSVP_DO_N.getKey();
    private final int function_statement = RSVP_FUNC_N.getKey();

    //private final int repeat_statement = Tokenizer.RSVP_REPE_N;
    private final int assign_statement = ASSIGNMENT_OPERATOR_N.getKey();
    
    // =========================================================================
    // _CONSTRUCTOR_
    // @param int state
    // =========================================================================
    
    public Statement(int state){
        this.type_def = state;
        try{
           this.setStatementType(); 
        }catch(ExecuterException ee){
            System.out.print(ee.getMessage());
        }
        
    }
    
    // =========================================================================
    // void setStatementType
    // defines the statement name
    // =========================================================================
    
    private void setStatementType(){
        //switch (this.type_def) {
        if (this.type_def == if_statement) {
        		this.stm_type = "IF_STATEMENT";
        }
        else if (this.type_def == for_statement) {
                this.stm_type = "FOR_STATEMENT";
        }
        else if (this.type_def == while_statement) {
                this.stm_type = "WHILE_STATEMENT";
        }
        else if (this.type_def == print_statement) {
                this.stm_type = "DISPLAY_STATEMENT";
        }
        else if (this.type_def == do_statement) {
                this.stm_type = "DO_STATEMENT";
        }
        else if (this.type_def == function_statement) {
                this.stm_type = "FUNCTION_STATEMENT";
        }
        else if (this.type_def == assign_statement) {
            this.stm_type = "ASSIGN_STATEMENT";
        }
        else if (this.type_def == else_statement) {
            this.stm_type = "ELSE_STATEMENT";
        }
        else {
        	this.stm_type = "EMPTY_STATEMENT";
        }
        /*
        case assign_statement:
            this.stm_type = "ASSIGNMENT_STATEMENT";
            break;
            */
    }
    
    // =========================================================================
    // void setIterator
    // assign the Identifier object to the iterator for loop control
    // The identifier used is taken form the Identifier table
    // =========================================================================
    
    public void setIterator(Identifier id){
        this.iterator = id;
    }
    
    // =========================================================================
    // Identifier getIterator
    // This function returns the identifier used for iteration
    // This iterator is the same as the one found in the Identifier Table
    // =========================================================================
    
    public Identifier getIterator(){
        return this.iterator;
    }
    
    // =========================================================================
    // int getIteratorValue
    // retrieves the integer value of the iterator of the loop
    // =========================================================================
    
    public int getIteratorValue(){
        return this.iterator.getValue();
    }
    
    // =========================================================================
    // void update_iterator
    // Permits to modify the value assigned to the iterator
    // =========================================================================
    
    public void update_iterator(int new_value){
        this.iterator.setValue(new_value);
    }

    // =========================================================================
    // int getStatementCode
    // provides the statement code of the instance
    // =========================================================================
    
    public int getStatementCode(){
        return this.type_def;
    }
    
    // =========================================================================
    // String getStatementType
    // gives back the statement type of the statement used
    // =========================================================================
    
    public String getStatementType(){
        return this.stm_type;
    }
    
    // =========================================================================
    // void execute
    // This is a function that will be overridden by the childs of this class
    // It defines the execution of a statement
    // =========================================================================

    public void execute(){}
     
}
