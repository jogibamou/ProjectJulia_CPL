package com.ProjectJulia.Scanner;

public class Token {
	
	public final int token;
	public final String sequence;
	public int col_num;
	public int row_num;
	public String token_name;
	
	// =========================================================================
    // __constructor__ Token(int token, String sequance, int col, int row)
    // initialize a token
    // int token: defines the code assigned by the tokeninfo
    // String sequence: defines the literal sequence of the token
    // int row: gives information about the line of the token
    // int col: gives information about the column of the token
    // =========================================================================
	
	public Token(int token, String sequence, int col, int row) {
		super();
		this.token = token;
		this.sequence = sequence;
		this.col_num = col;
		this.row_num = row;
	}
	public Token(int token, String sequence, int col, int row, String token_name) {
		super();
		this.token = token;
		this.sequence = sequence;
		this.col_num = col;
		this.row_num = row;
		this.token_name = token_name;
	}
}
