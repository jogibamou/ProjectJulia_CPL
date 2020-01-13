// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   3nd Deliverable
// File:      Executer.java
// =============================================================================
// Description:
// This file is the implementation of the Executer class.
// =============================================================================

package com.ProjectJulia.Executer;

import java.util.LinkedList;
import com.ProjectJulia.ExceptionPack.ParserException;
import com.ProjectJulia.Executer.ArithmeticExpression;
import com.ProjectJulia.Executer.BooleanExpression;
import com.ProjectJulia.Scanner.Token;
import com.ProjectJulia.Scanner.Tokenizer;
import com.ProjectJulia.Parser.IdentifierTable;
import com.ProjectJulia.Parser.Identifier;
import com.ProjectJulia.Statements.DisplayStatement;
import com.ProjectJulia.Statements.ForStatement;
import com.ProjectJulia.Statements.FunctionStatement;
import com.ProjectJulia.Statements.IfStatement;
import com.ProjectJulia.Statements.WhileStatement;
import com.ProjectJulia.Statements.AssignStatement;
import com.ProjectJulia.Statements.StatementList;
import com.ProjectJulia.Statements.FunctionList;
import com.ProjectJulia.Statements.Statement;
import com.ProjectJulia.Statements.funct.Argument;
import com.ProjectJulia.Statements.funct.ArgumentsList;

/**
*
* @author Giovanni Bamou Nana #000855399
*/
public class Executer extends Tokenizer{
 LinkedList<Token> tokens2;
 private Token lookahead;
 private IdentifierTable id_table;
 private final String ERROR_MESSAGE = "\n\n//========================================//\n  Error! \n//========================================//\n\n Unexpected Symbol: \"";
 private final String ERROR_LINE = "\" at line ";
 StatementList gen_list = new StatementList();
 FunctionList func_list = new FunctionList();

 
 
 // =========================================================================
 // void line_exception
 // Generic execption printed each time we find a syntax error
 // uses the global variable lookahead
 // =========================================================================
 
 private void line_exception(){
     
     print_identifierTable();
     
     throw new ParserException(ERROR_MESSAGE + lookahead.getSequence() + 
                               ERROR_LINE + lookahead.getRow());
 }
 
 // =========================================================================
 // void print_identifierTable
 // Generic function for printing the content of identifier table
 // uses the instance variable id_table
 // =========================================================================
 
 private void print_identifierTable(){
     
     System.out.println("\n\n\n//===================================="
             + "====//\n  Identifier Table\n//=========================="
             + "==============//\n\n");
     this.id_table.print_table();
     
 }
 
 // =========================================================================
 // void prt
 // Generic expression for printing
 // takes String val
 // =========================================================================
 
 private void prt(String val, int code){
     System.out.println(code+" ===> "+val);
 }
 
 // =========================================================================
 // _CONSTRUCTOR_
 // helps instantiate the parser
 // takes LinkedList<Token> used as input for parsing
 // =========================================================================
 
 public Executer(LinkedList<Token> tokens)
 {
     this.id_table = new IdentifierTable();
     this.tokens = (LinkedList<Token>) tokens.clone();
     this.tokens2 = (LinkedList<Token>) tokens.clone();

     lookahead = this.tokens.getFirst();
     
     start();
     print_identifierTable();


     if (lookahead.getToken() != Tokenizer.EPSILON)
         throw new ParserException("Unexpected symbol "+ 
                                    lookahead.getSequence()+" found");
 }
 
 // =========================================================================
 // void nextToken
 // Traverses the tokenlist sequentially
 // puts the next token in the variable lookahead
 // Skips the comments
 // =========================================================================
 
 private void nextToken()
 {
     int last_line = tokens.getFirst().getRow() + 1;
     tokens.pop();
     // at the end of input we return an epsilon token
     if (tokens.isEmpty())
         lookahead = new Token(Tokenizer.EPSILON, "", -1,last_line);
     else{
         lookahead = tokens.getFirst();
         scroll();
     }
       
 }
 
 //==========================================================================
 // SCROLL COMMENT FUNCTION
 // each time a line comment is found, nextToken until the next line
 // each time a multi-line comment, nextToken until the end of comment
 //==========================================================================
 
 private void scroll(){
     if(lookahead.getToken() == Tokenizer.LINE_COMMENT_N.getKey()){
         int line_n = lookahead.getRow();
         while(lookahead.getRow() == line_n){
             nextToken();    
         }
     }
     else if(lookahead.getToken() == Tokenizer.BEGIN_COMMENT_N.getKey())
     {
     
         
         while(lookahead.getToken() != Tokenizer.END_COMMENT_N.getKey()){
             nextToken();
         }
         
         nextToken();
     }
 }

 // =========================================================================
 // void start()
 // Parsing starting point
 // comprises the different top levels of the code
 // starts the recursive descent of the syntactic analysis
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // start : symbols forward_refs specifications globals implement
 // =========================================================================
 
 private void start() {
     prt("START",1000);
     imports();
     //implement();
     funct_list();
     statement_list(gen_list);
 }
 
 // =========================================================================
 // void imports
 // enables to make importation of the additional source files
 // list definition
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // imports: | import_def imports
 // =========================================================================
 
 private void imports(){
 	if(lookahead.getToken() == Tokenizer.RSVP_IMPO_N.getKey()){
 		//prt("IMPORT",1001);
 		import_def();
 		imports();
     }
 }
 
 // =========================================================================
 // void import_def
 // enables to make a single importation
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // import_def: IMPORT name_file
 // =========================================================================
 
 private void import_def(){
     if(lookahead.getToken() == Tokenizer.RSVP_IMPO_N.getKey()){
         //prt("IMPORT_DEF",1002);
         nextToken();
         name_file();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void name_file
 // helps define a file name
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // name_file: quote identifier dot_point identifier quote
 // =========================================================================
 
 private void name_file(){
     if(lookahead.getToken() == Tokenizer.LITERAL_QUOTE_N.getKey()){
         //prt("NAME_FILE",1003);
         nextToken();
         identifier();
         dot_point();
         identifier();
         quote();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void dot_point
 // Defines terminal: "."
 // terminal
 // =========================================================================
 
 private void dot_point(){
     if(lookahead.getToken() == Tokenizer.DOT_PTS_N.getKey()){
         //prt("DOT_POINT",1004);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void quote
 // defines terminal ' " '
 // terminal
 // =========================================================================
 
 private void quote(){
     if(lookahead.getToken() == Tokenizer.LITERAL_QUOTE_N.getKey()){
         //prt("QUOTE",1005);
         //System.out.println("<quote> -> <quote>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void symbol_def
 // defines terminal identifier
 // terminal
 // =========================================================================

 
 private void identifier(){
     if(lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()){
         //prt("IDENTIFIER",1008);
         //System.out.println("<identifier> -> <identifier>\n");
         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void chk_ptr
 // Helps define chk_ptr architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // chk_ptr:  | POINTER {pointer_flag = true;}
 // =========================================================================
 
 private void chk_ptr(){
     //add pointer reserved word
 }
 
 
 // =========================================================================
 // void array_dim_list
 // Helps define array_dim_list architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // array_dim_list: lb array_index rb | lb array_index rb array_dim_list
 // =========================================================================
 
 private void array_dim_list(){
     //prt("ARRAY_DIM_LIST",1021);
     lb();
     array_index();
     rb();
     if(lookahead.getToken() == Tokenizer.OPEN_BRACE_N.getKey()){
         array_dim_list();
     }
 }
 
 // =========================================================================
 // void lb
 // Helps define the terminal "["
 // terminal
 // =========================================================================
 
 private void lb(){
     if(lookahead.getToken() == Tokenizer.OPEN_BRACE_N.getKey()){
         //prt("LB",1022);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void array_index
 // Helps define array_index architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // array_index : IDENTIFIER | constant_value
 // =========================================================================
 
 private void array_index(){
     if(lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()){
         //prt("ARRAY_INDEX",1023);
         nextToken();
     }else if(lookahead.getToken() == Tokenizer.LITERAL_INTEGER_N.getKey()){
         //prt("ARRAY_INDEX",1023);
         nextToken();    
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void rb
 // Helps define the terminal "]"
 // terminal
 // =========================================================================
 
 private void rb(){
     if(lookahead.getToken() == Tokenizer.CLOSE_BRACE_N.getKey()){
         //prt("RB",1024);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void ret_type
 // Helps define ret_type architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // ret_type : TYPE type_name | STRUCT IDENTIFIER | STRUCTYPE IDENTIFIER 
 // =========================================================================
 
 private void ret_type(Identifier id){
//     if(lookahead.getToken() == Tokenizer.RSVP_TYPE_N.getKey()){
//         //prt("RET_TYPE",1025);
//         nextToken();
//         type_name(id);
//     }else 
     if(lookahead.getToken() == Tokenizer.RSVP_STRU_N.getKey()){
         //prt("RET_TYPE",1025);
         nextToken();
         identifier();
     }else{
         line_exception();
     }
 }

 
 // =========================================================================
 // void equal_op
 // Helps define terminal "="
 // terminal
 // =========================================================================
 
 private void equal_op(){
     if(lookahead.getToken() == Tokenizer.ASSIGNMENT_OPERATOR_N.getKey()){
         //prt("EQUAL_OP",1034);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void implement
 // Helps define implement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // implement: IMPLEMENTATIONS funct_list
 // =========================================================================
 
// private void implement(){
//         //prt("FUNC_LIST",1039);
//         funct_list();
// }
// 
 // =========================================================================
 // void func_list
 // Helps define func_list architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // func_list: funct_def | funct_list funct_def
 // =========================================================================
 
 private StatementList funct_list(){
	 //StatementList func_list = new StatementList();
	 FunctionStatement new_func = funct_def();
	 if(func_list.searchStatement(new_func)==null) {
		 func_list.addStatement(new_func);
	 }
     if(lookahead.getToken() == Tokenizer.RSVP_FUNC_N.getKey()){
         funct_list();
     }
     return func_list;
 }
 
 // =========================================================================
 // void func_def
 // Helps define func_def architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // func_def: funct_body
 // =========================================================================
 
 private FunctionStatement funct_def(){
     //prt("FUNC_DEF",1040);
     //System.out.println("<func_def> -> <identifier> ( <arg_list> ) <statement_list> end\n");
	 FunctionStatement func_state = funct_body();
	 return func_state;
 }
 
 // =========================================================================
 // void func_body
 // Helps define func_body architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // func_body: FUNCTION main_head parameters f_body
 // =========================================================================
 
 private FunctionStatement funct_body(){
     if(lookahead.getToken() == Tokenizer.RSVP_FUNC_N.getKey()){
         //prt("FUNC_BODY",1041);
         nextToken();
         Identifier id = main_head();
         FunctionStatement func_state = new FunctionStatement(id);
         if(func_list.searchStatement(func_state)==null) {
    		 func_list.addStatement(func_state);
    	 }
         ArgumentsList al = parameters();
         func_state.setArgumentsList(al);
         StatementList sl = f_body();
         func_state.setStatementList(sl);
         return func_state;
     }else{
         line_exception();
         return null;
     }
 }
 
 // =========================================================================
 // void main_head
 // Helps define main_head architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // main_head: MAIN | IDENTIFIER
 // =========================================================================
 
 private Identifier main_head(){
//     if(lookahead.getToken() == Tokenizer.RSVP_MAIN_N.getKey()){
//         //prt("MAIN_HEAD",1042);
//         nextToken();
//     }else 
     if(lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()){
         //prt("MAIN_HEAD",1042);
         Identifier id = new Identifier(lookahead.getSequence());
         identifier();
         return id;
     }else{
         line_exception();
         return new Identifier("");
     }
 }
 
 // =========================================================================
 // void parameters
 // Helps define parameters architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // parameters:  | PARAMETERS param_list
 // =========================================================================
 
 private ArgumentsList parameters(){
     if(lookahead.getToken() == Tokenizer.OPEN_BRACKET_N.getKey()){
     	
         //prt("PARAMETERS",1043);
         left_paren();
         //nextToken();
         //prt("PARAM_LIST",1044);
    	 ArgumentsList al = new ArgumentsList();
         al = param_list(al);
         right_paren();
         return al;
        
     }else{
         line_exception();
         return null;
     }
 }
 
 // =========================================================================
 // void param_list
 // Helps define param_list architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // param_list: param_def | param_def COMMA param_list 
 // =========================================================================
 
 private ArgumentsList param_list(ArgumentsList al){
     al.addArgument(new Argument(param_def()));
     if(lookahead.getToken() == Tokenizer.LITERAL_COMMA_N.getKey()){
         comma();
         al = param_list(al);
     }
     return al;
     
 }
 
 // =========================================================================
 // void param_def
 // Helps define param_def architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // param_def: identifier chk_const chk_ptr chk_array of_dec ret_type
 // =========================================================================
 
 private Identifier param_def(){
     if(lookahead.getToken() != Tokenizer.CLOSE_BRACKET_N.getKey()){
	        //prt("PARAM_DEF",1045);
	        Identifier new_id = new Identifier(lookahead.getSequence());
	        identifier();
	        chk_const();
	        chk_ptr();
	        this.id_table.addIdentifier(new_id);
	        return new_id;
     }
     else
    	 return null;
 }
 
 // =========================================================================
 // void chk_const
 // Helps define chk_const architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // chk_const:  | CONSTANT 
 // =========================================================================
 
 private void chk_const(){
     if(lookahead.getToken() == Tokenizer.RSVP_CONS_N.getKey()){
         //prt("CHK_CONST",1046);
         nextToken();
     }else{
         
     }
 }
 
 // =========================================================================
 // void f_body
 // Helps define f_body architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // f_body: declarations BEGIN statement_list exit_statment END main_head
 // =========================================================================
 
 private StatementList f_body(){
     //prt("F_BODY",1047);
     StatementList func_list = new StatementList();
     statement_list(func_list);
     endfun();
     return func_list;

 }
 
 // =========================================================================
 // void begin
 // Helps define terminal "begin"
 // terminal
 // =========================================================================
 
 private void begin(){
     if(lookahead.getToken() == Tokenizer.RSVP_BEGI_N.getKey()){
         //prt("BEGIN",1048);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void endfun
 // Helps define terminal "endfun"
 // terminal
 // =========================================================================
 
 private void endfun(){
     if(lookahead.getToken() == Tokenizer.RSVP_END_N.getKey()){
         //prt("ENDFUN",1049);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void statement_list
 // Helps define statement_list architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // statement_list: statement | statement_list statement
 // =========================================================================
 
 private void statement_list(StatementList stat_list){
 	
 	stat_list.addStatement(statement());
 	
     if     (lookahead.getToken() == Tokenizer.RSVP_IF_N.getKey() ||
             lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()||
             lookahead.getToken() == Tokenizer.RSVP_WHIL_N.getKey()||
             lookahead.getToken() == Tokenizer.RSVP_PRIN_N.getKey()||
             lookahead.getToken() == Tokenizer.RSVP_FOR_N.getKey()
             ){
         
         statement_list(stat_list);
         
     }
 }
 
 // =========================================================================
 // void statement
 // Helps define statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // statement: if_statement
 //          | assignment_statement
 //		| while_statement
 //		| print_statement
 //		| repeat_statement
 //          | for_statement
 //          | input_statement
 // =========================================================================
 
 private Statement statement(){
     if(lookahead.getToken() == Tokenizer.RSVP_IF_N.getKey()){
         //prt("STATEMENT",1050);
         //System.out.println("<statement> -> <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <for_statement>\n");
         IfStatement new_if = if_statement();
         new_if.execute();
         return new_if;
     }
     else if(lookahead.getToken() == Tokenizer.RSVP_WHIL_N.getKey()){
         //prt("STATEMENT",1050);
         //System.out.println("<statement> -> <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <for_statement>\n");
         WhileStatement new_while = while_statement();
         while(new_while.getBooleanExpression().evaluate()){
             for(Statement st: new_while.getStatementList().getStatements()){
                 st.execute();
             }
             //new_while = while_statement();

         }
         //new_while.execute();
         return new_while;

     }
     else if(lookahead.getToken() == Tokenizer.RSVP_PRIN_N.getKey()){
         //prt("STATEMENT",1050);
         //System.out.println("<statement> -> <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <for_statement>\n");
         //print_statement();
         DisplayStatement new_print = print_statement();
         StatementList sl = new StatementList();
         sl.addStatement(new_print);
         ArithmeticExpression ae = new ArithmeticExpression();
         BooleanExpression be = new BooleanExpression(ae, Tokenizer.EQ_OPERATOR_N.getKey(), ae);
    	 IfStatement foo_if = new IfStatement(be);
    	 foo_if.setStatementList(sl);
         //new_print.execute();
         return foo_if;
     }
     else if(lookahead.getToken() == Tokenizer.RSVP_FOR_N.getKey()){
         //prt("STATEMENT",1050);
         //System.out.println("<statement> -> <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <for_statement>\n");
         //for_statement();
         ForStatement new_for = for_statement();
         new_for.execute();
         return new_for;
     }
     else if(lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()){
         //prt("STATEMENT",1050);
         //System.out.println("<statement> -> <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <for_statement>\n");
         //assignment_statement();
         Identifier id = new Identifier(lookahead.getSequence());
         //System.out.print(func_list.searchStatement(id).getIdentifier().getName());
    	 if(func_list == null || func_list.searchStatement(id) == null) {
    		 AssignStatement new_assgn = assignment_statement();
             return new_assgn;
    	 }
    	 else {
    		 FunctionStatement new_func = func_list.searchStatement(id);
    		 nextToken();
    		 parameters();
        	 new_func.execute();
        	 return new_func;
    	 }
     }
     else{
         //line_exception();
         return new Statement(Tokenizer.EPSILON);
     }
 }
 
 // =========================================================================
 // void for_statement
 // Helps define for_statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // for_statement: FOR boolean_expression statement_list end
 // =========================================================================
 
 private ForStatement for_statement(){
 	
     //if(lookahead.getToken() == Tokenizer.RSVP_FOR_N.getKey()){
         //prt("FOR_STATEMENT",1051);
         //System.out.println("<for_statement> -> for <boolean_expression> <statement_list> end\n");
         
         nextToken();
         ForStatement frs = verification();
         return frs;
    // }
     //return new Statement()
 }
 
 // =========================================================================
 // void verification
 // Helps define verification architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // verification: IDENTIFIER equal_op constant_val reach do_statement 
 //               statement endfor
 // =========================================================================
 
 private ForStatement verification(){
     //prt("VERIFICATION",1052);
     StatementList for_list = new StatementList();
     Identifier new_id = new Identifier(lookahead.getSequence());
     identifier();
     equal_op();
     new_id.setValue(Integer.parseInt(lookahead.getSequence()));
     this.id_table.addIdentifier(new_id);
     int start_val = Integer.parseInt(lookahead.getSequence());
     constant_val();
     int end_val = reach();
     //do_statement();
     statement_list(for_list);
     ForStatement for_state = new ForStatement(for_list, start_val, end_val);
     for_state.setIterator(new_id);
     //for_state.execute();
     endfor();
     return for_state;
 }
 
 // =========================================================================
 // void reach
 // Helps define reach architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // reach: TO arithmetic_exp
 // =========================================================================
 
 private int reach(){
     if(lookahead.getToken() == Tokenizer.COL_OPERATOR_N.getKey()){
         //prt("REACH",1053);
         col_operator();
         ArithmeticExpression ae = new ArithmeticExpression();
         ae = arithmetic_exp(ae);
         //System.out.println(ArithmeticExpression.eval(ae));
         return (ArithmeticExpression.eval(ae));
     }else{
         line_exception();
         return Integer.MIN_VALUE;
     }
 }
 
 // =========================================================================
 // void endfor
 // Helps define terminal "endfor"
 // terminal
 // =========================================================================
 
 private void endfor(){
     if(lookahead.getToken() == Tokenizer.RSVP_END_N.getKey()){
         //prt("END_FOR",1054);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void if_statement
 // Helps define if_statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // if_statement: IF boolean_expression statement_list else_statement 
 //        statement_list endif
 // =========================================================================
 
 private IfStatement if_statement(){
     //if(lookahead.getToken() == Tokenizer.RSVP_IF_N.getKey()){
 	
         //prt("IF_STATEMENT",1055);
         //System.out.println("<if_statement> -> if <boolean_expression> <statement_list> <else_statement> end\n");

         nextToken();
         BooleanExpression be = boolean_expression();
         IfStatement if_state = new IfStatement(be);

         //nextToken(); //new line
         //then();
         StatementList if_list = new StatementList();
         statement_list(if_list);
         
         else_statement();
         StatementList else_list = new StatementList();
         statement_list(else_list);
         endif();      
         
         if(be.evaluate()) {
         	if_state.setStatementList(if_list);
         }
         else {
         	if_state.setStatementList(else_list);
         }
         
         return if_state;
//         
//     }else{
//         line_exception();
//     }
 }
 

 // =========================================================================
 // void else
 // Helps define terminal "else"
 // terminal
 // =========================================================================
 
 private void else_statement(){
     if(lookahead.getToken() == Tokenizer.RSVP_ELSE_N.getKey()){
         //prt("ELSE",1057);
         //System.out.println("<else_statement> -> else <statement_list> end\n");
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void endif
 // Helps define terminal "end" for IF control
 // terminal
 // =========================================================================
 
 private void endif(){
     if(lookahead.getToken() == Tokenizer.RSVP_END_N.getKey()){
         //prt("END_IF",1058);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void while_statement
 // Helps define while_statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // while_statement: WHILE boolean_expression statement_list ENDWHILE
 // =========================================================================
 
 private WhileStatement while_statement(){
     //if(lookahead.getToken() == Tokenizer.RSVP_WHIL_N.getKey()){
         //prt("WHILE_STATEMENT",1059);
         //System.out.println("<while_statement> -> while <boolean_expression> <statement_list> end\n");

         nextToken();
         BooleanExpression be = boolean_expression();
         WhileStatement while_state = new WhileStatement(be);

         //do_statement();
         StatementList whil_list = new StatementList();

         statement_list(whil_list);
         while_state.setStatementList(whil_list);
         end_while();
         return while_state;
//     }else{
//         line_exception();
//     }
 }
 
 // =========================================================================
 // void do_statement
 // Helps define terminal "do"
 // terminal
 // =========================================================================
 
 private void do_statement(){
     if(lookahead.getToken() == Tokenizer.RSVP_DO_N.getKey()){
         //prt("DO",1060);
         //System.out.println("<do_statement> -> <do_statement>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void end_while
 // Helps define terminal "end" for WHILE control
 // terminal
 // =========================================================================
   
 private void end_while(){
     if(lookahead.getToken() == Tokenizer.RSVP_END_N.getKey()){
         //prt("END_WHILE",1061);
         //System.out.println("<end> -> <end>");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void asssignement_statement
 // Helps define assignment_statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // assignment_statement: SET identifier assignment_operator 
 //                       arithmetic_expression
 // =========================================================================
 
 private AssignStatement assignment_statement(){
     //if(lookahead.getToken() == IDENTIFIER_N.getKey()){
         //prt("ASSIGNMENT_STATEMENT",1065);
         //System.out.println("<assignment_statement> -> <identifier> <assignment_operator> <arithmetic_expression>\n");

         //nextToken();
         Identifier id = new Identifier(lookahead.getSequence());
         Identifier tmp = id_table.searchIdentifier(id);
         identifier();
         if (tmp != null) {
        	 id = tmp;
        	 assignment_operator();
             ArithmeticExpression ae = new ArithmeticExpression();
             ArithmeticExpression result = arithmetic_exp(ae);
             ae = result;
             id.setValue(ArithmeticExpression.eval(ae));
             AssignStatement assign_state = new AssignStatement(id, ae);
             return assign_state;
         }
         else {
        	 assignment_operator();
             ArithmeticExpression ae = new ArithmeticExpression();
             ArithmeticExpression result = arithmetic_exp(ae);
             ae = result;
             id.setValue(ArithmeticExpression.eval(ae));
             this.id_table.addIdentifier(id);
             AssignStatement assign_state = new AssignStatement(id, ae);
             return assign_state;
         }
         
//     }
//     else{
//         line_exception();
//     }
 }
 
 // =========================================================================
 // void assignment_operator
 // Helps define terminal "="
 // terminal
 // =========================================================================
 
 private void assignment_operator(){
     if(lookahead.getToken() == Tokenizer.ASSIGNMENT_OPERATOR_N.getKey()){
         //prt("ASSIGNMENT_OPERATOR",1066);
         //System.out.println("<assignment_operator> -> <assignment_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void print_statement
 // Helps define print_statement architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // print_statement: print arg_list
 // =========================================================================
 
 private DisplayStatement print_statement(){
     //if(lookahead.getToken() == Tokenizer.RSVP_PRIN_N.getKey()){
         //prt("PRINT_STATEMENT",1068);
         //System.out.println("<print_statement> -> <left_paren> <arg_list> right_paren>\n");

         nextToken();
         left_paren();
         ArgumentsList al = new ArgumentsList();
         //prt("ARG_LIST",1069);
         //System.out.println("<arg_list> -> <args> <comma> <arg_list> | <args>\n");

         arg_list(al);
         DisplayStatement ps = new DisplayStatement(al);
         //ps.execute();
         right_paren();
         return ps;
//     }else{
//         line_exception();
//     }
 }
 
 // =========================================================================
 // void arg_list
 // Helps define arg_list architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // arg_list: args | args comma arg_list
 // =========================================================================
 
 private ArgumentsList arg_list(ArgumentsList al){
     al = args(al);
     if(lookahead.getToken() == Tokenizer.LITERAL_COMMA_N.getKey()){
         comma();
         al = arg_list(al);
     }
     return al;
 }
 
 // =========================================================================
 // void comma
 // Helps define terminal ","
 // terminal
 // =========================================================================
 
 private void comma(){
     if(lookahead.getToken() == Tokenizer.LITERAL_COMMA_N.getKey()){
         //prt("COMMA",1070);
         //System.out.println("<comma> -> <comma>");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void args
 // Helps define args architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // args : IDENTIFIER [array_dim_list] | quote text_value quote 
 //      | constant_val
 // =========================================================================
 
 private ArgumentsList args(ArgumentsList al){
     if(lookahead.getToken() == Tokenizer.IDENTIFIER_N.getKey()){
         //prt("ARGS",1071);
         //System.out.println("<args> -> <identifier> | <quote> <text_value> <quote> | <constant_val>\n");

         Identifier id = new Identifier(lookahead.getSequence());
         id = this.id_table.searchIdentifier(id);
         //Argument ar = new Argument(id.getValue());
         Argument ar = new Argument(id);
         al.addArgument(ar);
         identifier();
         if(lookahead.getToken() == Tokenizer.OPEN_BRACE_N.getKey()){
             array_dim_list();
         }
         return al;
     }else if(lookahead.getToken() == Tokenizer.LITERAL_QUOTE_N.getKey()){
         //prt("ARGS",1071);
         nextToken();
         text_value(al);
         quote();
         return al;
     }else if(lookahead.getToken() == Tokenizer.LITERAL_INTEGER_N.getKey()){
         //prt("ARGS",1071);
         Argument ar = new Argument(lookahead.getSequence());
         al.addArgument(ar);
         constant_val();
         return al;
     }
     else{
         line_exception();
         return null;
     }
 }
 
 // =========================================================================
 // void boolean_expression
 // Helps define boolean_expression architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // boolean_expression: arithmetic_exp relative_op arithmetic_exp | boolean_expression
 // =========================================================================
 
 private BooleanExpression boolean_expression(){
     //prt("BOOLEAN_EXPRESSION",1072);
     //System.out.println("<boolean_expression> -> <arithmetic_exp> <relative_op> <arithmetic_exp> | <boolean_expression>\n");

     ArithmeticExpression left_pane = new ArithmeticExpression();
     left_pane = arithmetic_exp(left_pane);
     int operator = lookahead.getToken();
     relative_op();
     ArithmeticExpression right_pane = new ArithmeticExpression();
     right_pane = arithmetic_exp(right_pane);
     
     BooleanExpression be = new BooleanExpression(left_pane, operator, right_pane);
     
     //prt("*************The result of this boolean expression is "+be.evaluate(),1072);
     
     return be;
 }
 
 // =========================================================================
 // void relative_op
 // Helps define relative_op architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // relative_op: le_operator | lt_operator | ge_operator | gt_operator 
 //            | eq_operator | ne_operator
 // =========================================================================
 
 private void relative_op(){
     if(lookahead.getToken() == Tokenizer.LE_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         //System.out.println("<relative_op> -> <le_operator> | <lt_operator> | <ge_operator> | <gt_operator> | <eq_operator> | <ne_operator>\n");

         le_operator();
     }else if(lookahead.getToken() == Tokenizer.LT_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         lt_operator();
     }else if(lookahead.getToken() == Tokenizer.GE_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         ge_operator();
     }else if(lookahead.getToken() == Tokenizer.GT_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         gt_operator();
     }else if(lookahead.getToken() == Tokenizer.EQ_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         eq_operator();
     }else if(lookahead.getToken() == Tokenizer.NE_OPERATOR_N.getKey()){
         //prt("RELATIVE_OP",1073);
         ne_operator();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void le_operator
 // Helps define terminal "<="
 // terminal
 // =========================================================================
 
 private void le_operator(){
     if(lookahead.getToken() == Tokenizer.LE_OPERATOR_N.getKey()){
         //prt("LE_OPERATOR",1074);
         //System.out.println("<le_operator> -> <le_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void lt_operator
 // Helps define terminal "<"
 // terminal
 // =========================================================================
 
 private void lt_operator(){
     if(lookahead.getToken() == Tokenizer.LT_OPERATOR_N.getKey()){
         //prt("LT_OPERATOR",1075);
         //System.out.println("<lt_operator> -> <lt_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void ge_operator
 // Helps define terminal "=>"
 // terminal
 // =========================================================================
 
 // =========================================================================
 // void eq_operator
 // Helps define terminal "=="
 // terminal
 // =========================================================================
 
 private void eq_operator(){
     if(lookahead.getToken() == Tokenizer.EQ_OPERATOR_N.getKey()){
         //prt("EQ_OPERATOR",1078);
         //System.out.println("<eq_operator> -> <eq_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }

private void ge_operator(){
     if(lookahead.getToken() == Tokenizer.GE_OPERATOR_N.getKey()){
         //prt("GE_OPERATOR",1076);
         //System.out.println("<ge_operator> -> <ge_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void gt_operator
 // Helps define terminal ">"
 // terminal
 // =========================================================================
 
 private void gt_operator(){
     if(lookahead.getToken() == Tokenizer.GT_OPERATOR_N.getKey()){
         //prt("GT_OPERATOR",1077);
         //System.out.println("<gt_operator> -> <gt_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void eq_operator
 // Helps define terminal "=="
 // terminal
 // =========================================================================
 
 
 
 // =========================================================================
 // void ne_operator
 // Helps define terminal "~="
 // terminal
 // =========================================================================
 
 private void ne_operator(){
     if(lookahead.getToken() == Tokenizer.NE_OPERATOR_N.getKey()){
         //prt("NE_OPERATOR",1079);
         //System.out.println("<ne_operator> -> <ne_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void arithmetic_expression
 // Helps define arithmetic_expression architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // arithmetic_expression: arithmetic_exp add_operator mulexp
 //                      | arithmetic_exp sub_operator mulexp
 //                      | mulexp
 // =========================================================================
 
 //private void arithmetic_exp(ArithmeticExpression ae){
   private ArithmeticExpression arithmetic_exp(ArithmeticExpression ae){

     //prt("ARITHMETIC_EXPRESSION",1080);
     //System.out.println("<arithmetic_exp> -> <arithmetic_exp> <add_operator> <mulexp> | <arithmetic_exp> <sub_operator> <mulexp> | <mulexp>\n");
	   
	   Identifier ido = new Identifier("+");
		 ido.setValue(0);
	     ArithmeticExpression oper_conv = new ArithmeticExpression(ADD_OPERATOR_N.getKey(), ido);  
     //ArithmeticExpression oper_conv = new ArithmeticExpression(Tokenizer.ADD_OPERATOR_N.getKey(),"+");
     
	     Identifier idr = new Identifier(null);
		 idr.setValue(0);
	     ArithmeticExpression right_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(), idr);
	 //ArithmeticExpression right_span = new ArithmeticExpression(Tokenizer.LITERAL_INTEGER_N.getKey(),"0");
     ae = oper_conv;
     ae.setRightNode(right_span);
     
     Identifier idl = new Identifier(null);
	 idl.setValue(0);
     ArithmeticExpression left_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(), idl);
     //ArithmeticExpression left_span = new ArithmeticExpression(Tokenizer.LITERAL_INTEGER_N.getKey(),"1");
     ArithmeticExpression result = this.mulexp(left_span);
     ae.setLeftNode(result);
     if(lookahead.getToken() == Tokenizer.ADD_OPERATOR_N.getKey()){
    	 Identifier id = new Identifier(lookahead.getSequence());
         //ae.setSequence(lookahead.getSequence());
         ae.setSequence(id);
         ae.setTokenCode(lookahead.getToken());
         add_operator();
         right_span = arithmetic_exp(ae.getRightNode());
         ae.setRightNode(right_span);
     }else if(lookahead.getToken() == Tokenizer.SUB_OPERATOR_N.getKey()){
    	 Identifier id = new Identifier(lookahead.getSequence());
         //ae.setSequence(lookahead.getSequence());
         ae.setSequence(id);
         ae.setTokenCode(lookahead.getToken());
         sub_operator();
         right_span = arithmetic_exp(ae.getRightNode());
         ae.setRightNode(right_span);
     }
     return ae;
    
 }
 
 // =========================================================================
 // void add_operator
 // Helps define terminal "+"
 // terminal
 // =========================================================================
 
 private void add_operator(){
     if(lookahead.getToken() == Tokenizer.ADD_OPERATOR_N.getKey()){
         //prt("ADD_OPERATOR",1081);
         //System.out.println("<add_operator> -> <add_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void sub_operator
 // Helps define terminal "-"
 // terminal
 // =========================================================================
 
 private void sub_operator(){
     if(lookahead.getToken() == Tokenizer.SUB_OPERATOR_N.getKey()){
         //prt("SUB_OPERATOR",1082);
         //System.out.println("<sub_operator> -> <sub_operator>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void mul_expression
 // Helps define mul_expression architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // mul_expression: mulexp mul_operator primary
 //               | mulexp div_operator primary
 //               | primary
 // =========================================================================
 
 //private void mulexp(ArithmeticExpression aem){

 private ArithmeticExpression mulexp(ArithmeticExpression aem){
     //prt("MULEXP",1083);
     //System.out.println("<mulexp> -> <mulexp> <mul_operator> <primary> | <mulexp> <div_operator> <primary> | <primary>\n");
	 
	 Identifier ido = new Identifier("+");
	 ido.setValue(0);
     ArithmeticExpression oper_conv = new ArithmeticExpression(ADD_OPERATOR_N.getKey(), ido);
     //ArithmeticExpression oper_conv = new ArithmeticExpression(ADD_OPERATOR_N.getKey(),"+");
	 Identifier idr = new Identifier(null);
	 idr.setValue(0);
     ArithmeticExpression right_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(), idr);
     //ArithmeticExpression right_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(),"0");
     aem = oper_conv;
     aem.setRightNode(right_span);
     Identifier idl = new Identifier(null);
	 idl.setValue(0);
     ArithmeticExpression left_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(), idl);
     //ArithmeticExpression left_span = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(),"0");
     left_span = primary(left_span);
     aem.setLeftNode(left_span); 
     if(lookahead.getToken() == MUL_OPERATOR_N.getKey()){
    	 
    	 Identifier id = new Identifier(lookahead.getSequence());
         //aem.setSequence(lookahead.getSequence());
         aem.setSequence(id);
         aem.setTokenCode(lookahead.getToken());
         mul_operator();
         mulexp(aem.getRightNode());
     }else if(lookahead.getToken() == DIV_OPERATOR_N.getKey()){
    	 Identifier id = new Identifier(lookahead.getSequence());
         //aem.setSequence(lookahead.getSequence());
         aem.setSequence(id);

         aem.setTokenCode(lookahead.getToken());
         div_operator();
         mulexp(aem.getRightNode());
     }
     ArithmeticExpression result = aem;
     return result;
 }
 
 // =========================================================================
 // void mul_operator
 // Helps define terminal "*"
 // terminal
 // =========================================================================
 
 private void mul_operator(){
     if(lookahead.getToken() == Tokenizer.MUL_OPERATOR_N.getKey()){
         //prt("MUL_OPERATOR",1084);
         //System.out.println("<mul_operator> -> <mul_operator>\n");
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void div_operator
 // Helps define terminal "/"
 // terminal
 // =========================================================================
 
 private void div_operator(){
     if(lookahead.getToken() == Tokenizer.DIV_OPERATOR_N.getKey()){
         //prt("DIV_OPERATOR",1085);
         //System.out.println("<div_operator> -> <div_operator>\n");
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void primary
 // Helps define primary architecture
 // non-terminal
 // GRAMMAR CODE:
 // =========================================================================
 // mul_expression: left_paren  arithmetic_exp right_paren
 //               | minus primary
 //               | constant_val
 //               | identifier
 // =========================================================================
 
 private ArithmeticExpression primary(ArithmeticExpression aep){
     if(lookahead.getToken() == OPEN_BRACKET_N.getKey()){
         //prt("PRIMARY",1086);
         //System.out.println("<primary> -> <left_paren> <arithmetic_exp> <left_paren> | <sub_operator> <primary> | <integer_literal> | <identifier>\n");
         left_paren();
         aep = arithmetic_exp(aep);
         right_paren();
         return aep;
     }else if(lookahead.getToken() == SUB_OPERATOR_N.getKey()){
         //prt("PRIMARY",1086);
         //System.out.println("<primary> -> <left_paren> <arithmetic_exp> <left_paren> | <sub_operator> <primary> | <integer_literal> | <identifier>\n");
    	 Identifier id = new Identifier(null);
    	 id.setValue(0);
         ArithmeticExpression new_aep = new ArithmeticExpression(LITERAL_INTEGER_N.getKey(), id);
         aep.setLeftNode(new_aep);
    	 Identifier id2 = new Identifier(lookahead.getSequence());
         aep.setSequence(id2);
         aep.setTokenCode(lookahead.getToken());
         minus();
         aep = primary(aep.getRightNode());
         return aep;
     }else if(lookahead.getToken() == IDENTIFIER_N.getKey()){
         //prt("PRIMARY",1086);
         //System.out.println("<primary> -> <left_paren> <arithmetic_exp> <left_paren> | <sub_operator> <primary> | <integer_literal> | <identifier>\n");
         Identifier id = new Identifier(lookahead.getSequence());
         Identifier tmp = id_table.searchIdentifier(id);
         if (tmp != null) {
        	 id = tmp;
         }
        //aep.setSequence(Integer.toString(this.id_table.getValueIdentifier(id)));
    	 aep.setSequence(id);
             identifier();
             if(lookahead.getToken() == OPEN_BRACKET_N.getKey()){
                 ArgumentsList al = new ArgumentsList();
                 nextToken();
                 arg_list(al);
                 right_paren();
         }
         return aep;
     }else if(lookahead.getToken() == LITERAL_INTEGER_N.getKey()){
         //prt("PRIMARY",1086);
         //System.out.println("<primary> -> <left_paren> <arithmetic_exp> <left_paren> | <sub_operator> <primary> | <integer_literal> | <identifier>\n");
    	 Identifier id = new Identifier(null);
    	 id.setValue(Integer.parseInt(lookahead.getSequence()));
         aep.setSequence(id);
         //aep.setSequence(lookahead.getSequence());
         aep.setTokenCode(lookahead.getToken());
         constant_val();
         return aep;
     }else{
         line_exception();
         return null;
     }
 }
 
 // =========================================================================
 // void left_paren
 // Helps define terminal "("
 // terminal
 // =========================================================================
 
 private void left_paren(){
     if(lookahead.getToken() == Tokenizer.OPEN_BRACKET_N.getKey()){
         //prt("LEFT_PAREN",1087);
         //System.out.println("<left_paren> -> <left_parent>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void right_paren
 // Helps define terminal ")"
 // terminal
 // =========================================================================
 
 private void right_paren(){
     if(lookahead.getToken() == Tokenizer.CLOSE_BRACKET_N.getKey()){
         //prt("RIGHT_PAREN",1088);
         //System.out.println("<right_paren> -> <right_paren>\n");

         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void minus
 // Helps define terminal "-"
 // terminal
 // =========================================================================
 
 private void minus(){
     if(lookahead.getToken() == Tokenizer.SUB_OPERATOR_N.getKey()){
         //prt("UNARY_MINUS",1089);
         nextToken();
     }else{
         line_exception();
     }
 }
 
 
 // =========================================================================
 // void constant_value
 // Helps define terminal integer
 // terminal
 // =========================================================================
 
 private void constant_val(){
     if(lookahead.getToken() == Tokenizer.LITERAL_INTEGER_N.getKey()){
         //prt("CONSTANT_VAL",1090);
         //System.out.println("<integer_literal> -> <integer_literal>\n");
         nextToken();
     }else{
         line_exception();
     }
 }
 
 // =========================================================================
 // void text_value
 // Helps define terminal string taking any value
 // terminal
 // =========================================================================
 
 private void text_value(ArgumentsList al){
     //prt("TEXT_VALUE",1091);
     //System.out.println("<text_value> -> <text_value>\n");
     Argument ar = new Argument("");
     while(lookahead.getToken() != Tokenizer.LITERAL_QUOTE_N.getKey()){
         Argument.appendArg(ar, lookahead.getSequence());
         nextToken();
     }
     al.addArgument(ar);
 }
 
 // =========================================================================
 // void mul_operator
 // Helps define terminal ":"
 // terminal
 // =========================================================================

 private void col_operator(){
 	if(lookahead.getToken() == Tokenizer.COL_OPERATOR_N.getKey()){
 		//prt("COL_OPERATOR",1092);
 		//System.out.println("<col_operator> -> <col_operator>\n");
 		nextToken();
 	}else{
 		line_exception();
 	}
 }
 
 
 
 
}