package com.ProjectJulia.Scanner;

public class Token {
	
	private int token;
	private String sequence;
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
	
	public int getToken(){
		return this.token;
	};

	public String getSequence(){
		return this.sequence;
	};

	public int getCol(){
		return this.col_num;
	};
	public int getRow(){
		return this.row_num;
	};

	public String getToken_name(){
		return this.token_name;
	}
	
	public void setToken(int code){
		this.token = 0;
	};

	public void setSequence(String seq){
		this.sequence = seq;
	};

	public void setCol(int col){
		this.col_num = col;
	};
	public void setRow(int row){
		this.row_num = row;
	};

	public void setToken_name(String name){
		this.token_name = name;
	}
}
