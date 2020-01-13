// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      IdentifierTable.java
// =============================================================================
// Description:
// This file is the implementation of the IdentifierTable class.
// =============================================================================

package com.ProjectJulia.Parser;

import java.util.LinkedList;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class IdentifierTable {
    
    // =========================================================================
    // Instance variable
    // @param LinkedList<Identifier> identifierTable
    // List of all the identifiers used within the code
    // =========================================================================
    
    private LinkedList<Identifier> identifierTable = new LinkedList<Identifier>();
    
    // =========================================================================
    // _CONSTRUCTOR_
    // Empty constructor to define an empty list
    // =========================================================================
    
    public IdentifierTable(){}
    
    // =========================================================================
    // void addIdentifier
    // @param Identifier new_id
    // Adds a new Identifier to the identifier table
    // =========================================================================
    
    public void addIdentifier(Identifier new_id){
        int ref = this.identifierTable.indexOf(new_id);
        if(ref==(-1))
            this.identifierTable.add(new_id);
        else
            this.identifierTable.get(ref).setValue(new_id.getValue());
    }
    
    // =========================================================================
    // void setIdentifierValue
    // @param Identifier new_id is the identifier reasearched in the table
    // @param int value is the value to be assigned to the identifier
    // =========================================================================
    
    public void setIdentifierValue(Identifier new_id, int value){
        int ref = this.identifierTable.indexOf(new_id);
        if(ref!=(-1))
            this.identifierTable.get(ref).setValue(value);
    }
    
    // =========================================================================
    // LinkedList<Identifier>getIdentifierTable
    // returns the list of identifiers in the identifier table
    // =========================================================================
    
    public LinkedList<Identifier> getIdentifierTable(){
        return this.identifierTable;
    }
    
    // =========================================================================
    // int getValueIdentifier
    // @param Identifier new_id is the identifier to modify
    // Gives the value of the identifier present in the identifier table
    // =========================================================================
    
    public int getValueIdentifier(Identifier new_id){
        int value = 0;
        int ref = this.identifierTable.indexOf(new_id);
        if(ref!=(-1))
            value = this.identifierTable.get(ref).getValue();
        return value;        
    }
    
    // =========================================================================
    // Identifier searchIdentifier
    // @param Identifier new_id
    // looks if an identifier is present in the identifier table
    // =========================================================================
    
    public Identifier searchIdentifier(Identifier new_id){
        int ref = this.identifierTable.indexOf(new_id);
        if(ref!=(-1))
            return identifierTable.get(ref);
        else return null;
    }
    
    // =========================================================================
    // Identifier searchIdentifier
    // @param String id
    // looks if an identifier is present in the identifier table
    // =========================================================================
    
    public Identifier searchIdentifier(String id){
        Identifier found = null;
        for(Identifier ife: this.getIdentifierTable()){
            if(ife.getName() == null ? id == null : ife.getName().equals(id))
                return ife;
        }
        return found;
    }
    
    // =========================================================================
    // void discardIdentifier
    // @param Identifier new_id
    // removes the listed identifier from the identifier table
    // =========================================================================
    
    public void discardIdentifier(Identifier new_id){
        int ref = this.identifierTable.indexOf(new_id);
        if(ref!=(-1))
            this.identifierTable.remove(ref);
    }
    
    // =========================================================================
    // void print_table
    // prints the list of identifiers with their type and value
    // =========================================================================
    
    public void print_table(){
        for(Identifier in :identifierTable){
            System.out.println("Name: "+in.getName()+" | Type: "+in.getType()+" | Value: "+in.getValue());
        }
    }
    
    
    
}
