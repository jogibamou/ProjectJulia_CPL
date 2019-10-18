package com.ProjectJulia.Scanner;

import com.ProjectJulia.ExceptionPack.ParserException;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
//import com.ProjectJulia.Parser.Parser;

public class Scanner {
	private File filename;
    private Tokenizer tokenizer;
    
    // =========================================================================
    // _constructor_ Scanner(String file)
    // Builds a new instance of the object Scanner
    // Initialize the Tokenizer and start reading the file
    // Verifies the file extension
    // Prints out the result to the command line
    // Print an error message if an unknown token is found
    // String file: contains the path to the file that has to be parsed
    // =========================================================================
    
    public Scanner(String file){
        
        
        this.filename = new File(file);
        this.tokenizer = Tokenizer.initTokenizer();
        try{
            
            // =================================================================
            // Verifies if the extension is the right one
            // =================================================================
            String [] parts = this.filename.getAbsolutePath().split("[.]");
            if(!parts[1].equals("jl")){
                throw new ParserException("\n\nNon valid file extension."
                        + " Use a file with the extension \".jl\"");
            }
            
            scan();

        }catch (ParserException e) {
            System.out.println(e.getMessage());       
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        
            
    }
    
    // =========================================================================
    // void scan() thrwows IOException
    // Reads each line of the input file
    // tokenize them and add the reported result to the tokens list
    // Detect the line number during the execution
    // Print the tokens list after reading the whole file
    // =========================================================================
    
    
    private void scan() throws IOException {
	
        FileInputStream fis = new FileInputStream(this.filename);

 
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
	String line = null;
        int row_number = 1;
	while ((line = br.readLine()) != null) {
		tokenizer.tokenize(line,row_number);
                row_number++;
	}
 
	br.close();
        
        System.out.println("\n\n\n//===================================="
                + "====//\n  Scanner Result\n//=========================="
                + "==============//\n\n");
        printTokens();
        /*System.out.println("\n\n\n//===================================="
                + "====//\n  Parser Result\n//=========================="
                + "==============//\n\n");
        */
    }
    
    // =========================================================================
    // Tokenizer getTokenList
    // Returns the list of tokens found by the scanner
    // This method will be used in the parser to get the different tokens
    // =========================================================================
    
    public LinkedList<Token> getTokenList(){
        return tokenizer.getTokens();
    }
    
    // =========================================================================
    // void print Tokens
    // Uses the list of tokens
    // Print the line number, column number, token code
    // and token sequence of the token analyzed
    // =========================================================================

    private void printTokens(){
        
        for (Token tok : this.tokenizer.getTokens()) {
            if(tok.token!=5052 && tok.token!=5055){
                System.out.println("row: " +tok.row_num+ " , col: " 
                        + tok.col_num + " | token_code: " + tok.token 
                        + " | token_sequence: " + tok.sequence);
            }
        }
        
    }
    
    // =========================================================================
    // void main(String[]args)
    // Initialize a scanner and start scanning the file
    // Prints an EOF statement to inform the parser the reading is complete
    // =========================================================================
    
    public static void main(String[]args){
        //if(args.length==2){
    	if(true){
            try{
                Scanner scan = new Scanner("test1.jl");
                //Parser parse = new Parser(scan.getTokenList());
            }
            catch(ParserException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("\n\n ===> Error while reading. No file as "
                    +"input. Please enter a file name with extension .jl");
        }
    }
    
}

