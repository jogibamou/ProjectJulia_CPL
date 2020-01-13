// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3rd Deliverable
// File:      StatementList.java
// =============================================================================
// Description:
// This file is the implementation of the StatementList class.
// =============================================================================

package com.ProjectJulia.Statements;

import java.util.LinkedList;

import com.ProjectJulia.Parser.Identifier;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class StatementList {
    
    // =========================================================================
    // Instance variable
    // Defines a list of statements
    // =========================================================================
    
    private LinkedList<Statement> statements = new LinkedList<Statement>();
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Does not takes any value as the list starts empty
    // =========================================================================
    
    public StatementList(){}
    
    // =========================================================================
    // void addStatement
    // Enables to add a Statement object to the list
    // =========================================================================
    
    public void addStatement(Statement stm){
        this.statements.add(stm);
    }
    
    // =========================================================================
    // void removeStatement
    // Enables to remove a statement from the statement list
    // =========================================================================
    
    public void removeStatement(Statement stm){
        this.statements.remove(stm);
    }
    
    // =========================================================================
    // LinkedList<Statement> getStatements
    // returns the list of statements in the statement list
    // =========================================================================
    
    public LinkedList<Statement> getStatements(){
        return this.statements;
    }
    
    
}
