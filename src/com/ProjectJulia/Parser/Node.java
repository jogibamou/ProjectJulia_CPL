package com.ProjectJulia.Parser;

import com.ProjectJulia.Scanner.Token;
import javafx.util.Pair;

public class Node {

    private Token root = null;
    private Node left = null;
    private Node right = null;

    public Node(){

    }

    public Node(Token root){

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

    public Node getLeft(){
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight(){
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
