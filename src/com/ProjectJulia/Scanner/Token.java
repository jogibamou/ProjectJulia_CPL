package com.ProjectJulia.Scanner;

public class Token {
	
	private final int token;
	private final String sequence;
	private int col_num;
	private int row_num;
	private String token_name;
	
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
	
	public int getToken();
	public String getSequence();
	public int getCol();
	public int getRow();
	
	public setToken(int code);
	public setSequence(String seq);
	public setCol(int col);
	public setRow(int row);
	
}
