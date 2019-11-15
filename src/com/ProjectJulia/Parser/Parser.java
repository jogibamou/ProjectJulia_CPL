package com.ProjectJulia.Parser;

import com.ProjectJulia.Scanner.Token;

import java.util.LinkedList;

public class Parser {

    private LinkedList<Token> tokenLinkedList;

    public Parser(LinkedList<Token> tokenLinkedList){
        this.tokenLinkedList = tokenLinkedList;
//        printTokens();
        try{
            BuildeSyntaxTree(tokenLinkedList);
        } catch (ParseError parseError){
            System.out.println(parseError);
        }
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to PROGRAM statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseProgram(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();
        Node tempNode = node;
        //Build Left Tree
        if(tokenLinkedList.peek().getToken_name() == "RSVP_FUNC_N"){
            node.setRoot(tokenLinkedList.pop());
            if(tokenLinkedList.peek().getToken_name() == "IDENTIFIER_N"){
                tempNode.setLeft(new Node());
                tempNode = tempNode.getLeft();
                tempNode.setRoot(tokenLinkedList.pop());
                if(tokenLinkedList.peek().getToken_name() == "OPEN_BRACKET_N"){
                    tempNode.setLeft(new Node());
                    tempNode = tempNode.getLeft();
                    tempNode.setRoot(tokenLinkedList.pop());
                    if(tokenLinkedList.peek().getToken_name() == "CLOSE_BRACKET_N"){
                        tempNode.setLeft(new Node());
                        tempNode = tempNode.getLeft();
                        tempNode.setRoot(tokenLinkedList.pop());
                    }
                    else throw new ParseError("Expected closing brace");
                }
                else throw  new ParseError("Expected opening brace");
            }
            else throw new ParseError("Expected Identifier");
        }
        //Build Right Tree
        node.setRight(parseBlock(tokenLinkedList));
        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to BLOCK statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseBlock(LinkedList<Token> tokenLinkedList) throws  ParseError {
       return parseStatment(tokenLinkedList);
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to STATEMENT statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseStatment(LinkedList<Token> tokenLinkedList) throws  ParseError{
        Node node = new Node();

        switch (tokenLinkedList.peek().getToken_name()){
            case "RSVP_IF_N":
                return parseIf(tokenLinkedList);

            case "ASSIGNMENT_OPERATOR_N":
                node.setRoot(tokenLinkedList.pop());
                return parseAssign(tokenLinkedList);

            case "RSVP_WHIL":
                node.setRoot(tokenLinkedList.pop());

                break;

            case "RSVP_PRIN":
                node.setRoot(tokenLinkedList.pop());
                break;

            case "RSVP_FOR_N":
                node.setRoot(tokenLinkedList.pop());
                break;
        }
        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to IF statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseIf(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();
        node.setRoot(tokenLinkedList.pop());
        node.setLeft(parseBooleanExpression(tokenLinkedList));
        node.setRight(parseBlock(tokenLinkedList));
        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to WHILE statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseWhile(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to ASSIGN statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseAssign(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to FOR statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseFor(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to PRINT statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parsePrint(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to LITERAL statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseLiteral(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to TRUE & FALSE statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseBooleanExpression(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();
        if(isRelativeOperator(tokenLinkedList) == true){
//            node.setLeft(new Node(tokenLinkedList.pop());
//            if()
        }
        else{
            throw new ParseError("Expected operator");
        }

        return node;
    }

    /**
     * Determines if  next token is a BOOLEAN Operator.
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Returns true if next token of a BOOLEAN Operator.
     */
    private Boolean isRelativeOperator(LinkedList<Token> tokenLinkedList){
        switch (tokenLinkedList.peek().getToken_name()){
            case "<=":
            case ">":
            case "<":
            case ">=":
            case "==":
            case "!=":
                return true;
        }
        return false;
    }

    /**
     * Determines if  next token is an ARITHMETIC Operator.
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Returns true if next token of a ARITHMETIC Operator.
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseArithmeticExpression(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to BINARY EXPRESSION statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseBinaryExpression(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    /**
     * Determines if  next token is an ARITHMETIC Operator.
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Returns true if next token of a ARITHMETIC Operator.
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Boolean parseArithmeticOp(LinkedList<Token> tokenLinkedList){
        switch (tokenLinkedList.peek().getToken_name()){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                return true;
        }
        return false;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to FUNCTION statements
     * using recursive decent
     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseFunction(LinkedList<Token> tokenLinkedList) throws ParseError{
        Node node = new Node();
        if(tokenLinkedList.peek().getToken_name() == "RSVP_FUNC_N"){
            node.setRoot(tokenLinkedList.pop());
            if(tokenLinkedList.peek().getToken_name() == "IDENTIFIER_N"){
                node.setLeft(new Node(tokenLinkedList.pop()));
            } else{
                throw new ParseError("Expected Identifier after function definition");
            }
        }
        return node;
    }



    private Node BuildeSyntaxTree(LinkedList<Token> tokenLinkedList) throws ParseError{
        Node node;
        String parseOutput = "";

        node = this.parseProgram(tokenLinkedList);
        if(node != null){
            parseOutput += "<program> -> ";
        }
//        if(tokenLinkedList.peek().getToken_name() == "RSVP_FUNC_N"){
//            Node exp = new Node(tokenLinkedList.pop());
//
//            return expressionTree(tokenLinkedList, exp);
//        } else{
//            throw new ParseError("Syntax Unrecognized");
//        }
        System.out.println(parseOutput);
        printNodes(node);
        return node;
    }

    private void printNodes(Node node){

    }

    private static Node BuildeSyntaxTree(LinkedList<Token> tokenLinkedList, Node tree) throws ParseError{
        throw new ParseError("Syntax Unrecognized");
    }

    private void printTokens(){
        System.out.println("TEST: " + this.tokenLinkedList.peek().getToken_name());
        System.out.println("TEST: " + this.tokenLinkedList.pop().getToken_name());
    }


}
