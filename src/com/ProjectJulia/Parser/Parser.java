package com.ProjectJulia.Parser;

import com.ProjectJulia.Scanner.Token;
import com.ProjectJulia.constant.Identifier;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Parser {

    private LinkedList<Token> tokenLinkedList;
    private Iterator<Token> currTokenIt;
    private Iterator<Token> saveTokenIt;
    private int currTokenPos = 0;

    public Parser(LinkedList<Token> tokenLinkedList){
        this.tokenLinkedList = tokenLinkedList;
        currTokenIt = tokenLinkedList.iterator();
        saveTokenIt = currTokenIt;
//        saveTokenIt = tokenLinkedList.iterator();

        try {
            parse(tokenLinkedList);
        } catch (ParseError parseError){
            System.out.println(parseError);
        }
    }

    private Node parse(LinkedList<Token> tokenLinkedList) throws ParseError{
        Node node = new Node();

        node = parseProgram();
        printTree(node);

        return node;
    }

    private Token getNextToken(){
        currTokenPos++;
        return currTokenIt.next();
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to PROGRAM statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private synchronized Node parseProgram() throws ParseError {
        Node node = new Node("<program>");
        saveTokenIt = currTokenIt;
        Token tempToken;

//        currTokenIt = saveTokenIt;
        tempToken = getNextToken();
        if(tempToken.getToken_name() == "RSVP_FUNC_N"){
            node.addChild(new Node(tempToken));
            tempToken = getNextToken();

            if(tempToken.getToken_name() == "IDENTIFIER_N"){
                node.addChild(new Node(tempToken));
                tempToken = getNextToken();

                if(tempToken.getToken_name() == "OPEN_BRACKET_N"){
                    node.addChild(new Node(tempToken));
                    tempToken = getNextToken();

                    if(tempToken.getToken_name() == "CLOSE_BRACKET_N"){
                        node.addChild(new Node(tempToken));
                    }
                    else throw new ParseError("Expected closing brace");
                }
                else throw  new ParseError("Expected opening brace");
            }
            else throw new ParseError("Expected Identifier");
        }
        else throw new ParseError("Expected name \'Function\'");

//        saveTokenIt = currTokenIt;

        try{
            node.addChild(parseBlock());
        } catch (Exception e){
            throw new ParseError(e.getMessage());
        }


        return node;
    }


    /**
     * Generates an abstract parse tree for Tokens corresponding to BLOCK statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseBlock() throws ParseError {
        Node node = new Node("<block>");
        node.addChild(parseStatement());
        return node;
    }

//    private Boolean checkAllBlockProduction(){
////        if(parseStatement())
//    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to STATEMENT statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private synchronized Node parseStatement() throws  ParseError{
        Node node = new Node("<statement>");
        Node tempNode;
//        node.addChild(tempNode);

        //Reference the node we want to revert back to before performing any manipulation
        saveTokenIt = currTokenIt;


//        this.currTokenIt = this.saveTokenIt;
        tempNode = parseIf();
        if(tempNode != null)
        {
            node.addChild(tempNode);
            return node;
        }

        this.currTokenIt = this.saveTokenIt;
        tempNode = parseAssign(tokenLinkedList);
        if(tempNode != null)
        {
            node.addChild(tempNode);
            return node;
        }

        this.currTokenIt = this.saveTokenIt;
        tempNode = parseWhile(tempNode);
        if(tempNode != null) return node;

        this.currTokenIt = this.saveTokenIt;
        tempNode = parsePrint(tempNode);
        if(tempNode != null) return node;

        this.currTokenIt = this.saveTokenIt;
        tempNode = parseFor(tempNode);
        if(tempNode != null) return node;

        return null;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to IF statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseIf() throws ParseError {
        Node node = new Node();
//        if()


//        node.setRoot(tokenLinkedList.pop());
//        node.setRight(parseBlock(tokenLinkedList));
        return null;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to WHILE statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseWhile(Node node) throws  ParseError {
         node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to ASSIGN statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseAssign(LinkedList<Token> list) throws  ParseError {
         Node node = new Node("<assignment_statement>");
         Token tempToken;
            tempToken = currTokenIt.next();

            if (tempToken.getToken_name() == "IDENTIFIER_N") {
                node.addChild(new Node(tempToken));

                tempToken = currTokenIt.next();
                currTokenPos++;
                if (tempToken.getToken_name() == "ASSIGNMENT_OPERATOR_N") {
                    node.addChild(new Node(tempToken));

                    Node tempNode = parseArithmeticExpression();
                    if (tempNode != null) {
                        node.addChild(tempNode);
                    } else throw new ParseError("Expected arithmetic expression");
                } else throw new ParseError("Expected assignment operator '='");
            } else throw new ParseError("Expected Identifier");
        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to FOR statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseFor(Node node) throws  ParseError {
         node = new Node();

        return node;
    }

    /**
     * Generates an abstract parse tree for Tokens corresponding to PRINT statements
     * using recursive decent
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parsePrint(Node node) throws  ParseError {
         node = new Node();

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
     * @return Returns true if next token of a ARITHMETIC Operator.
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseArithmeticExpression() throws  ParseError {
        Node node = new Node("<arithmetic_expression>");
        Iterator<Token> saveTokenIt = currTokenIt;
        Token tempToken;

        tempToken = currTokenIt.next();

        if(tempToken.getToken_name() == "LITERAL_INTEGER_N" ||
                tempToken.getToken_name() == "IDENTIFIER_N" ||
                isBinaryExpression()){
            node.addChild(new Node(tempToken));
            tempToken = currTokenIt.next();
        }
        return node;
    }




    private Boolean isBinaryExpression() throws  ParseError {
        if(isArithmeticOp() && parseArithmeticExpression() != null && parseArithmeticExpression() != null){
            return true;
        }
        return false;
    }

    /**
     * Determines if  next token is an ARITHMETIC Operator.
     * @return Returns true if next token of a ARITHMETIC Operator.
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Boolean isArithmeticOp(){
        switch (getNextToken().getToken_name()){
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
//     * @param tokenLinkedList LinkedList of remaining tokens to be parsed
     * @return Parent node of local Abstract Parse Tree
     * @throws ParseError Error message contain information about the specific syntax error
     */
    private Node parseFunction(LinkedList<Token> tokenLinkedList) throws ParseError{
        Node node = new Node();
//        if(tokenLinkedList.peek().getToken_name() == "RSVP_FUNC_N"){
//            node.setRoot(tokenLinkedList.pop());
//            if(tokenLinkedList.peek().getToken_name() == "IDENTIFIER_N"){
//                node.setLeft(new Node(tokenLinkedList.pop()));
//            } else{
//                throw new ParseError("Expected Identifier after function definition");
//            }
//        }
        return node;
    }


    /**
     * Prints a row of the parse tree.
     * The next row printed is that of the leftmost non-terminal.
     * @param root Root node of the tree
     */
    private void printTree(Node root){
        Stack<Node> toVisit = new Stack();
        toVisit.push(root);
        do {
            String row = "";
            Node currNode = toVisit.pop();
            row += String.format("%s -> ", currNode.getExpression());
            for (int i = 0; i < currNode.getChildren().size(); i++) {
                Node childNode = currNode.getChild(i);
                String production;
                if (childNode.isTerminal) {
                     production = String.format("%s", childNode.getRoot().getSequence());
                } else {
                    production = childNode.getExpression();
                    toVisit.push(childNode);
                }
                row += String.format("%s ", production);
            }
            System.out.println(row);
        } while (toVisit.isEmpty() == false);
    }

    private static Node BuildeSyntaxTree(LinkedList<Token> tokenLinkedList, Node tree) throws ParseError{
        throw new ParseError("Syntax Unrecognized");
    }

    private void printTokens(){
        System.out.println("TEST: " + this.tokenLinkedList.peek().getToken_name());
        System.out.println("TEST: " + this.tokenLinkedList.pop().getToken_name());
    }


}
