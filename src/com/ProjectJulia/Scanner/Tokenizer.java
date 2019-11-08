package com.ProjectJulia.Scanner;

import com.ProjectJulia.ExceptionPack.ParserException;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private class TokenInfo {
    	private Pattern regex;
    	private int token_code;
    	private String token_name;

        public TokenInfo(Pattern regex, int token_code, String token_Name) {
            super();
            this.regex = regex;
            this.token_code = token_code;
            this.token_name = token_Name;

        }
        
        public Pattern getRegex(){
            return this.regex;
        };
		
		public int getTokenCode(){
		    return this.token_code;
        };
		
		public String getTokenName(){
		    return this.token_name;
        };
		
		public void setRegex(Pattern pat){
		    this.regex = pat;
        };
		
		public void setTokenCode(int code){
		    this.token_code = code;
        };
		
		public void setTokenName(String name){
		    this.token_name = name;
        };
        
    }

    //==========================================================================
    //LEXICAL RULES
    //These are the rules specified for the different operators, and identifiers
    //Contains regex of the different operators, literals and identifiers
    //they will be used to scan the sample code
    //==========================================================================


    private static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9_]*";
    private static final String LITERAL_INTEGER = "[0-9]+";
    private static final String END_COMMENT = "[=][#]";
    private static final String ASSIGNMENT_OPERATOR = "[=]";
    private static final String LE_OPERATOR = "[<][=]";
    private static final String LT_OPERATOR = "[<]";
    private static final String GE_OPERATOR = "[=][>]";
    private static final String GT_OPERATOR = "[>]";
    private static final String EQ_OPERATOR = "[=][=]";
    private static final String NE_OPERATOR = "[!][=]";
    private static final String UNADD_OPERATOR = "[+]" + IDENTIFIER;
    private static final String UNSUB_OPERATOR = "[-]" + IDENTIFIER;

    private static final String ADD_OPERATOR = "[+]";
    private static final String SUB_OPERATOR = "[-]";
    private static final String MUL_OPERATOR = "[^/|^*][*][^/|^*]";
    private static final String DIV_OPERATOR = "[^/|^*][/][^/|^*]";
    private static final String POW_OPERATOR = "[\\^]";
    private static final String LITERAL_QUOTE = "[\"]";
    //private static final String BEGIN_COMMENT       = "[#][=]"+ LITERAL_TEXT;
    //private static final String LINE_COMMENT        = "[#]" + LITERAL_TEXT;
    private static final String LITERAL_COMMA = "[,]";
    private static final String OPEN_BRACKET = "[(]";
    private static final String CLOSE_BRACKET = "[)]";
    private static final String OPEN_BRACE = "[\\[]";
    private static final String CLOSE_BRACE = "[\\]]";
    private static final String WHITE_SPACE = "[\t]+|[\r]+|[\f]+|[ ]+";
    private static final String DOT_PTS = "[.]";
    private static final String LITERAL_TEXT = "[^\"\\\\\\r\\n]*(?:\\\\.[^\"\\\\\\r\\n]*)*";

    private static final String BEGIN_COMMENT = "[#][=]" + LITERAL_TEXT;
    private static final String LINE_COMMENT = "[#]" + LITERAL_TEXT;

    private static final String OTHERS = ".+";
    private static final String MOD_OPERATOR = "[%]";
    private static final String OR_OPERATOR = "[|][|]";
    private static final String AND_OPERATOR = "[&][&]";



    //==========================================================================
    //RSVP_[ELEMENT]
    //These are the lexical rules for the reserved words
    //Contains regex of the different words that are reserved by the language
    //they will be used to verify the
    //==========================================================================

    private static final String RSVP_BEGI = "begin";
    private static final String RSVP_WHIL = "while";
    private static final String RSVP_IF = "if";
    private static final String RSVP_FOR = "for";
    private static final String RSVP_TRY = "try";
    private static final String RSVP_RETU = "return";
    private static final String RSVP_BRK = "break";
    private static final String RSVP_CONT = "continue";
    private static final String RSVP_FUNC = "function";
    private static final String RSVP_MACR = "macro";
    private static final String RSVP_QUOT = "quote";
    private static final String RSVP_LET = "let";
    private static final String RSVP_LOCL = "local";
    private static final String RSVP_GLOB = "global";
    private static final String RSVP_CONS = "const";
    private static final String RSVP_DO = "do";
    private static final String RSVP_STRU = "struct";
    private static final String RSVP_MODU = "module";
    private static final String RSVP_BMOD = "baremodue";
    private static final String RSVP_USNG = "using";
    private static final String RSVP_IMPO = "import";
    private static final String RSVP_EXPO = "export";
    private static final String RSVP_END = "end";
    private static final String RSVP_ELSE = "else";
    private static final String RSVP_ELIF = "elseif";
    private static final String RSVP_CATC = "catch";
    private static final String RSVP_FINL = "finally";
    private static final String RSVP_TRUE = "true";
    private static final String RSVP_FALS = "false";
    private static final String RSVP_IN = "in";
    private static final String RSVP_PRIN = "print";
    private static final String RSVP_PRLN = "println";



 /*
    private static final String RSVP_REFE = "references";
    private static final String RSVP_PRIN = "printer";
    private static final String RSVP_ENUM = "enum";
    private static final String RSVP_MAIN = "main";
    private static final String RSVP_PARA = "parameters";
    private static final String RSVP_ENDF = "endfun";
    private static final String RSVP_ENDI = "endif";
    private static final String RSVP_OF   = "of";
    private static final String RSVP_IS   = "is";
    private static final String RSVP_THEN = "then";
    private static final String RSVP_REPE = "repeat";
    private static final String RSVP_UNTI = "until";
    private static final String RSVP_ENDR = "endrepeat";
    private static final String RSVP_DISP = "display";
    private static final String RSVP_SET  = "set";
    private static final String RSVP_DEFI = "define";
    private static final String RSVP_COLO = ":";
    private static final String RSVP_VARI = "variables";
    private static final String RSVP_ENDW = "endwhile";
    private static final String RSVP_SHOR = "short";
    private static final String RSVP_MVOI = "mvoid";
    private static final String RSVP_DESC = "description";
    private static final String RSVP_INPU = "input";
    private static final String RSVP_TO   = "to";
    private static final String RSVP_EXIT = "exit";
   	*/


    //==========================================================================
    //LEXICAL RULES NUMBERS
    //Each token has a code that is associated to it
    //It will be used in the parser definition for syntax analysis
    //The different codes will be associated to the token regex in key list
    //==========================================================================


    private static final Pair < Integer, String > IDENTIFIER_N = new Pair < > (6001, "IDENTIFIER_N");
    private static final Pair < Integer, String > LITERAL_INTEGER_N = new Pair < > (6002, "LITERAL_INTEGER_N");
    private static final Pair < Integer, String > ASSIGNMENT_OPERATOR_N = new Pair < > (6003, "ASSIGNMENT_OPERATOR_N");
    private static final Pair < Integer, String > LE_OPERATOR_N = new Pair < > (6004, "LE_OPERATOR_N");

    private static final Pair < Integer, String > LT_OPERATOR_N = new Pair < > (6005, "LT_OPERATOR_N");
    private static final Pair < Integer, String > GE_OPERATOR_N = new Pair < > (6006, "GE_OPERATOR_N");
    private static final Pair < Integer, String > GT_OPERATOR_N = new Pair < > (6007, "GT_OPERATOR_N");
    private static final Pair < Integer, String > EQ_OPERATOR_N = new Pair < > (6008, "EQ_OPERATOR_N");
    private static final Pair < Integer, String > NE_OPERATOR_N = new Pair < > (6009, "NE_OPERATOR_N");
    private static final Pair < Integer, String > ADD_OPERATOR_N = new Pair < > (6010, "ADD_OPERATOR_N");
    private static final Pair < Integer, String > SUB_OPERATOR_N = new Pair < > (6011, "SUB_OPERATOR_N");
    private static final Pair < Integer, String > MUL_OPERATOR_N = new Pair < > (6012, "MUL_OPERATOR_N");
    private static final Pair < Integer, String > DIV_OPERATOR_N = new Pair < > (6013, "DIV_OPERATOR_N");
    private static final Pair < Integer, String > POW_OPERATOR_N = new Pair < > (6014, "POW_OPERATOR_N");
    private static final Pair < Integer, String > LITERAL_QUOTE_N = new Pair < > (6015, "LITERAL_QUOTE_N");
    private static final Pair < Integer, String > LINE_COMMENT_N = new Pair < > (6016, "LINE_COMMENT_N");
    private static final Pair < Integer, String > BEGIN_COMMENT_N = new Pair < > (6017, "BEGIN_COMMENT_N");
    private static final Pair < Integer, String > END_COMMENT_N = new Pair < > (6018, "END_COMMENT_N");
    private static final Pair < Integer, String > LITERAL_COMMA_N = new Pair < > (6019, "LITERAL_COMMA_N");
    private static final Pair < Integer, String > OPEN_BRACKET_N = new Pair < > (6020, "OPEN_BRACKET_N");
    private static final Pair < Integer, String > CLOSE_BRACKET_N = new Pair < > (6021, "CLOSE_BRACKET_N");
    private static final Pair < Integer, String > OPEN_BRACE_N = new Pair < > (6022, "OPEN_BRACE_N");
    private static final Pair < Integer, String > CLOSE_BRACE_N = new Pair < > (6023, "CLOSE_BRACE_N");
    private static final Pair < Integer, String > WHITE_SPACE_N = new Pair < > (6024, "WHITE_SPACE_N");
    private static final Pair < Integer, String > DOT_PTS_N = new Pair < > (6025, "DOT_PTS_N");
    private static final Pair < Integer, String > LITERAL_TEXT_N = new Pair < > (6026, "LITERAL_TEXT_N");
    private static final Pair < Integer, String > MOD_OPERATOR_N = new Pair < > (6027, "MOD_OPERATOR_N");
    private static final Pair < Integer, String > UNADD_OPERATOR_N = new Pair < > (6028, "MOD_OPERATOR_N");
    private static final Pair < Integer, String > OR_OPERATOR_N = new Pair < > (6029, "MOD_OPERATOR_N");
    private static final Pair < Integer, String > AND_OPERATOR_N = new Pair < > (6030, "MOD_OPERATOR_N");
    private static final Pair < Integer, String > UNSUB_OPERATOR_N = new Pair < > (6031, "MOD_OPERATOR_N");;


    //==========================================================================
    //RESERVED WORDS NUMBERS
    //Each reserved has a code that is associated to it
    //It will be used in the parser definition for syntax analysis
    //The different codes will be associated to the token regex in key list
    //==========================================================================



    private static final Pair < Integer, String > RSVP_BEGI_N = new Pair < > (5001, "RSVP_BEGI_N");
    private static final Pair < Integer, String > RSVP_WHIL_N = new Pair < > (5002, "RSVP_WHIL_N");
    private static final Pair < Integer, String > RSVP_IF_N = new Pair < > (5003, "RSVP_IF_N");
    private static final Pair < Integer, String > RSVP_FOR_N = new Pair < > (5004, "RSVP_FOR_N");
    private static final Pair < Integer, String > RSVP_TRY_N = new Pair < > (5005, "RSVP_TRY_N");
    private static final Pair < Integer, String > RSVP_RETU_N = new Pair < > (5006, "RSVP_RETU_N");
    private static final Pair < Integer, String > RSVP_BRK_N = new Pair < > (5007, "RSVP_BRK_N");
    private static final Pair < Integer, String > RSVP_CONT_N = new Pair < > (5008, "RSVP_CONT_N");
    private static final Pair < Integer, String > RSVP_FUNC_N = new Pair < > (5009, "RSVP_FUNC_N");
    private static final Pair < Integer, String > RSVP_MACR_N = new Pair < > (5010, "RSVP_MACR_N");
    private static final Pair < Integer, String > RSVP_QUOT_N = new Pair < > (5011, "RSVP_QUOT_N");
    private static final Pair < Integer, String > RSVP_LET_N = new Pair < > (5012, "RSVP_LET_N");
    private static final Pair < Integer, String > RSVP_LOCL_N = new Pair < > (5013, "RSVP_LOCL_N");
    private static final Pair < Integer, String > RSVP_GLOB_N = new Pair < > (5014, "RSVP_GLOB_N");
    private static final Pair < Integer, String > RSVP_CONS_N = new Pair < > (5015, "RSVP_CONS_N");
    private static final Pair < Integer, String > RSVP_DO_N = new Pair < > (5016, "RSVP_DO_N");
    private static final Pair < Integer, String > RSVP_STRU_N = new Pair < > (5017, "RSVP_STRU_N");
    private static final Pair < Integer, String > RSVP_MODU_N = new Pair < > (5018, "RSVP_MODU_N");
    private static final Pair < Integer, String > RSVP_BMOD_N = new Pair < > (5019, "RSVP_BMOD_N");
    private static final Pair < Integer, String > RSVP_USNG_N = new Pair < > (5020, "RSVP_USNG_N");
    private static final Pair < Integer, String > RSVP_IMPO_N = new Pair < > (5021, "RSVP_IMPO_N");
    private static final Pair < Integer, String > RSVP_EXPO_N = new Pair < > (5022, "RSVP_EXPO_N");
    private static final Pair < Integer, String > RSVP_END_N = new Pair < > (5023, "RSVP_END_N");
    private static final Pair < Integer, String > RSVP_ELSE_N = new Pair < > (5024, "RSVP_ELSE_N");
    private static final Pair < Integer, String > RSVP_ELIF_N = new Pair < > (5025, "RSVP_ELIF_N");
    private static final Pair < Integer, String > RSVP_CATC_N = new Pair < > (5026, "RSVP_CATC_N");
    private static final Pair < Integer, String > RSVP_FINL_N = new Pair < > (5027, "RSVP_FINL_N");
    private static final Pair < Integer, String > RSVP_TRUE_N = new Pair < > (5028, "RSVP_TRUE_N");
    private static final Pair < Integer, String > RSVP_FALS_N = new Pair < > (5029, "RSVP_FALS_N");
    private static final Pair < Integer, String > RSVP_IN_N = new Pair < > (5030, "RSVP_IN_N");
    private static final Pair < Integer, String > RSVP_PRIN_N = new Pair < > (5031, "RSVP_PRIN_N");
    private static final Pair < Integer, String > RSVP_PRLN_N = new Pair < > (5032, "RSVP_PRLN_N");

    //==========================================================================
    //tokenInfos
    //linked list of TokenInfo
    //contains the differnt tokens regular experession correspondents
    //Used to make the comparisons during scanning to find literals
    //==========================================================================

    private LinkedList < TokenInfo > tokenInfos;

    //==========================================================================
    //tokens
    //Linked list of Token
    //Contains the differnt tokens that will be found in the sample file
    //==========================================================================

    private LinkedList < Token > tokens;

    // =========================================================================
    // __constructor__ Tokenizer()
    // initialize tokenInfos and tokens
    // =========================================================================

    public Tokenizer() {
        tokenInfos = new LinkedList < TokenInfo > ();
        tokens = new LinkedList < Token > ();
    }

    // =========================================================================
    // void add(String regex, int token)
    // Add a new lexical sample to tokenInfos
    // String regex : represents the regex associated to the lexical sample
    // int token : represents the integer code associated to the token
    // =========================================================================

    public void  add(String regex, Pair < Integer, String > token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token.getKey(), token.getValue()));
    }

    // =========================================================================
    // void tokenize(String str, int line_number)
    // Take a line of code and scan it to identify the different tokens
    // The different tokens found are added to a list of tokens
    // In case one token is undefined, an error message is printed out
    // String str: contains the line string to be scanned
    // int line_number: indicated the number of the line on which we are working
    // =========================================================================

    public void tokenize(String str, int line_number) {

        String s = new String(str);
        int col_num = 1;

        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info: tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    if (info.token_code != WHITE_SPACE_N.getKey()) {
                        tokens.add(new Token(info.token_code, tok, col_num, line_number, info.token_name));
                    } else {
                        col_num--;
                    }
                    s = m.replaceFirst("");
                    break;
                }
            }
            if (!match) throw new ParserException("Unexpected character in input: " + s);
            col_num++;
        }

    }


    // =========================================================================
    // LinkedList<Token> getTokens()
    // Return the linkedList containing the different tokens detected
    // Returns the attribute tokens
    // =========================================================================

    public LinkedList < Token > getTokens() {
        return tokens;
    }
    
    public LinkedList < TokenInfo > getTokenInfos() {
        return tokenInfos;
    }
    
    public static Tokenizer initTokenizer() {

        //======================================================================
        //The different tokenizer used will be taken from the lexical sample
        //To this will be added a reader that will read through the file
        //That reader will provide the different lines to be read
        //======================================================================

        Tokenizer tokenizer = new Tokenizer();


        //======================================================================
        //Reserved Words
        //======================================================================

        tokenizer.add(RSVP_BEGI, RSVP_BEGI_N);
        tokenizer.add(RSVP_WHIL, RSVP_WHIL_N);
        tokenizer.add(RSVP_IF, RSVP_IF_N);
        tokenizer.add(RSVP_FOR, RSVP_FOR_N);
        tokenizer.add(RSVP_TRY, RSVP_TRY_N);
        tokenizer.add(RSVP_RETU, RSVP_RETU_N);
        tokenizer.add(RSVP_BRK, RSVP_BRK_N);
        tokenizer.add(RSVP_CONT, RSVP_CONT_N);
        tokenizer.add(RSVP_FUNC, RSVP_FUNC_N);
        tokenizer.add(RSVP_MACR, RSVP_MACR_N);
        tokenizer.add(RSVP_QUOT, RSVP_QUOT_N);
        tokenizer.add(RSVP_LET, RSVP_LET_N);
        tokenizer.add(RSVP_LOCL, RSVP_LOCL_N);
        tokenizer.add(RSVP_GLOB, RSVP_GLOB_N);
        tokenizer.add(RSVP_CONS, RSVP_CONS_N);
        tokenizer.add(RSVP_DO, RSVP_DO_N);
        tokenizer.add(RSVP_STRU, RSVP_STRU_N);
        tokenizer.add(RSVP_MODU, RSVP_MODU_N);
        tokenizer.add(RSVP_BMOD, RSVP_BMOD_N);
        tokenizer.add(RSVP_USNG, RSVP_USNG_N);
        tokenizer.add(RSVP_IMPO, RSVP_IMPO_N);
        tokenizer.add(RSVP_EXPO, RSVP_EXPO_N);
        tokenizer.add(RSVP_END, RSVP_END_N);
        tokenizer.add(RSVP_ELSE, RSVP_ELSE_N);
        tokenizer.add(RSVP_ELIF, RSVP_ELIF_N);
        tokenizer.add(RSVP_CATC, RSVP_CATC_N);
        tokenizer.add(RSVP_FINL, RSVP_FINL_N);
        tokenizer.add(RSVP_TRUE, RSVP_TRUE_N);
        tokenizer.add(RSVP_FALS, RSVP_FALS_N);
        tokenizer.add(RSVP_IN, RSVP_IN_N);
        tokenizer.add(RSVP_IN, RSVP_IN_N);
        tokenizer.add(RSVP_PRLN, RSVP_PRLN_N);


        //======================================================================
        //Operators
        //======================================================================

        tokenizer.add(LITERAL_INTEGER, LITERAL_INTEGER_N);
        tokenizer.add(EQ_OPERATOR, EQ_OPERATOR_N);
        //        tokenizer.add(LE_OPERATOR,          LE_OPERATOR_N);
        tokenizer.add(LT_OPERATOR, LT_OPERATOR_N);
        tokenizer.add(GE_OPERATOR, GE_OPERATOR_N);
        tokenizer.add(GT_OPERATOR, GT_OPERATOR_N);
        tokenizer.add(NE_OPERATOR, NE_OPERATOR_N);
        tokenizer.add(END_COMMENT, END_COMMENT_N);
        tokenizer.add(ASSIGNMENT_OPERATOR, ASSIGNMENT_OPERATOR_N);
        tokenizer.add(UNADD_OPERATOR, UNADD_OPERATOR_N);
        tokenizer.add(UNSUB_OPERATOR, UNSUB_OPERATOR_N);

        tokenizer.add(ADD_OPERATOR, ADD_OPERATOR_N);
        tokenizer.add(SUB_OPERATOR, SUB_OPERATOR_N);
        tokenizer.add(MUL_OPERATOR, MUL_OPERATOR_N);
        tokenizer.add(DIV_OPERATOR, DIV_OPERATOR_N);
        tokenizer.add(OR_OPERATOR, OR_OPERATOR_N);
        tokenizer.add(AND_OPERATOR, AND_OPERATOR_N);

        tokenizer.add(OPEN_BRACKET, OPEN_BRACKET_N);
        tokenizer.add(CLOSE_BRACKET, CLOSE_BRACKET_N);
        tokenizer.add(LITERAL_QUOTE, LITERAL_QUOTE_N);
        tokenizer.add(LITERAL_COMMA, LITERAL_COMMA_N);
        tokenizer.add(BEGIN_COMMENT, BEGIN_COMMENT_N);
        tokenizer.add(LINE_COMMENT, LINE_COMMENT_N);
        tokenizer.add(IDENTIFIER, IDENTIFIER_N);
        tokenizer.add(OPEN_BRACE, OPEN_BRACE_N);
        tokenizer.add(CLOSE_BRACE, CLOSE_BRACE_N);
        tokenizer.add(MOD_OPERATOR, MOD_OPERATOR_N);
        tokenizer.add(WHITE_SPACE, WHITE_SPACE_N);
        tokenizer.add(DOT_PTS, DOT_PTS_N);
        tokenizer.add(POW_OPERATOR, POW_OPERATOR_N);
        tokenizer.add(LITERAL_TEXT, LITERAL_TEXT_N);


        //        tokenizer.add(OTHERS,               OTHER);


        return tokenizer;

    }

    //==========================================================================
    //Main function
    //Used for testing of the different lexical samples
    //Takes one line and return the differnt tokens found
    //Does not give information about the line or the column
    //==========================================================================


    public static void main(String[] args) {

        Tokenizer tokenizer = initTokenizer();

        try {
            tokenizer.tokenize("define varm2 array[MM] of type integer", 9);

            for (Token tok: tokenizer.getTokens()) {
                System.out.println("row: " + tok.getRow() + " , col: " + tok.getCol() + " | Token: " + tok.getToken_name() + " | token_sequence: " + tok.getSequence());
            }
        } catch (ParserException e) {
            System.out.println("\n\n" + e.getMessage());
        }
    }

}
