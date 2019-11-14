package com.ProjectJulia.Parser;

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
