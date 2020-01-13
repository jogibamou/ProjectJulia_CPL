// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      Node.java
// =============================================================================
// Description:
// This file is the implementation of the Node class.
// =============================================================================

package com.ProjectJulia.Parser;

import com.ProjectJulia.Scanner.Token;

import java.io.File;
import java.util.ArrayList;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class Node {

    public boolean isTerminal = true;
    private String expression;
    private Token root = null;
    private ArrayList<Node> children = new ArrayList<>();
    private Node left = null;
    private Node right = null;

    public Node(){

    }

    public Node(Token root){
        this.root = root;
    }

    public Node(String expression){
        isTerminal = false;
        this.expression = expression;
    }

    public Node(Token root, Node left, Node right){
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public Token getRoot(){
        return this.root;
    }

    public void setRoot(Token root){
        this.root = root;
    }

    /**
     * Getter for the array containing all children
     * @return
     */
    public ArrayList<Node> getChildren(){
        return this.children;
    }


    /**
     * Getter for a child at a specific index
     * @param index
     * @return
     */
    public Node getChild(int index){
        return children.get(index);
    }

    /**
     * Setter for the array containing all children
     * @param child
     */
    public void addChild(Node child) {
        this.children.add(child);
    }

    public String getExpression(){
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Node getTail(){
        return children.get(children.size()-1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

class test{

}
