package com.ProjectJulia.Parser;

import com.ProjectJulia.Scanner.Token;
import com.ProjectJulia.Scanner.Tokenizer;

import java.util.LinkedList;

public class Parser {

    private LinkedList<Token> tokenLinkedList;

    public Parser(LinkedList<Token> tokenLinkedList){
        this.tokenLinkedList = tokenLinkedList;
//        printTokens();
        try{
            expressionTree(tokenLinkedList);
        } catch (ParseError parseError){
            System.out.println(parseError);
        }
    }

    /**
     * This method does something probably. I honestly don't know.
     * I blacked out, woke up, and found that my alter ego wrote all this code.
     * I know this comment probably isn't helpful. IDK who you even are.
     * But I just thought that you should know
     *
     * @param tokenLinkedList LinkedList of Token objects to be parsed
     * @return Node object denoting the local structure of the Abstract Syntax Tree
     * @throws ParseError Object containing the error message in the event of a parsing error
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

    private Node parseStatment(LinkedList<Token> tokenLinkedList) throws  ParseError{
        Node node = new Node();

        switch (tokenLinkedList.peek().getToken_name()){
            case "RSVP_IF_N":
                node.setRoot(tokenLinkedList.pop());
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


    private Node parseIf(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    private Node parseWhile(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    private Node parseAssign(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    private Node parseFor(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }

    private Node parsePrint(LinkedList<Token> tokenLinkedList) throws  ParseError {
        Node node = new Node();

        return node;
    }




    private static Node expressionTree(LinkedList<Token> tokenLinkedList) throws ParseError{
        if(tokenLinkedList.peek().getToken_name() == "RSVP_FUNC_N"){
            Node exp = new Node(tokenLinkedList.pop());

            return expressionTree(tokenLinkedList, exp);
        } else{
            throw new ParseError("Syntax Unrecognized");
        }
    }

    private static Node expressionTree(LinkedList<Token> tokenLinkedList, Node tree) throws ParseError{
        throw new ParseError("Syntax Unrecognized");
    }

    private void printTokens(){
        System.out.println("TEST: " + this.tokenLinkedList.peek().getToken_name());
        System.out.println("TEST: " + this.tokenLinkedList.pop().getToken_name());
    }


}
