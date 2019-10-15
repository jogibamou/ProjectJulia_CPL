package com.ProjectJulia.Scanner;

import com.ProjectJulia.ExceptionPack.ParserException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	
	private class TokenInfo{
		public final Pattern regex;
		public final int token;
		
		public TokenInfo(Pattern regex, int token) {
			super();
			this.regex = regex;
			this.token = token;
			
		}
	}
	
	//==========================================================================
    //LEXICAL RULES 
    //These are the rules specified for the different operators, and identifiers
    //Contains regex of the different operators, literals and identifiers
    //they will be used to scan the sample code
    //==========================================================================
	
	
	private static final String IDENTIFIER          = "[a-zA-Z][a-zA-Z0-9_]*";
	private static final String LITERAL_INTEGER     = "[0-9]+";
    private static final String ASSIGNMENT_OPERATOR = "[=]";
    private static final String LE_OPERATOR         = "[<][=]";
    private static final String LT_OPERATOR         = "[<]";
    private static final String GE_OPERATOR         = "[=][>]";
    private static final String GT_OPERATOR         = "[>]";
    private static final String EQ_OPERATOR         = "[=][=]";
    private static final String NE_OPERATOR         = "[!][=]";
    private static final String ADD_OPERATOR        = "[+]";
    private static final String SUB_OPERATOR        = "[-]";
    
    private static final String MOD_OPERATOR        = "[%]";

    private static final String MUL_OPERATOR        = "[^/|^*][*][^/|^*]";
    private static final String DIV_OPERATOR        = "[^/|^*][/][^/|^*]";
    private static final String POW_OPERATOR        = "[\\^]";
    private static final String LITERAL_QUOTE       = "[\"]";
    private static final String LINE_COMMENT        = "[/][/]";
    private static final String BEGIN_COMMENT       = "[/][\\*]";
    private static final String END_COMMENT         = "[\\*][/]";
    private static final String LITERAL_COMMA       = "[,]";
    private static final String OPEN_BRACKET        = "[(]";
    private static final String CLOSE_BRACKET       = "[)]";
    private static final String OPEN_BRACE          = "[\\[]";
    private static final String CLOSE_BRACE         = "[\\]]";
    private static final String WHITE_SPACE         = "[\t]+|[\r]+|[\f]+|[ ]+";
    private static final String DOT_PTS             = "[.]";
    private static final String LITERAL_TEXT        = "[^\"\\\\\\r\\n]*(?:\\\\.[^\"\\\\\\r\\n]*)*";
    private static final String OTHERS              = ".+";
	
	
    //==========================================================================
    //RSVP_[ELEMENT]
    //These are the lexical rules for the reserved words
    //Contains regex of the different words that are reserved by the language
    //they will be used to verify the 
    //==========================================================================
    
    private static final String RSVP_BEGI = "begin";
    private static final String RSVP_WHIL = "while";
    private static final String RSVP_IF   = "if";
    private static final String RSVP_FOR  = "for";
    private static final String RSVP_TRY  = "try";
    private static final String RSVP_RETU = "return";
    private static final String RSVP_BRK  = "break";
    private static final String RSVP_CONT = "continue";
    private static final String RSVP_FUNC = "function";
    private static final String RSVP_MACR = "macro";
    private static final String RSVP_QUOT = "quote";
    private static final String RSVP_LET  = "let";
    private static final String RSVP_LOCL = "local";
    private static final String RSVP_GLOB = "global";
    private static final String RSVP_CONS = "const";
    private static final String RSVP_DO   = "do";
    private static final String RSVP_STRU = "struct";
    private static final String RSVP_MODU = "module";
    private static final String RSVP_BMOD = "baremodue";
    private static final String RSVP_USNG = "using";
    private static final String RSVP_IMPO = "import";
    private static final String RSVP_EXPO = "export";
    private static final String RSVP_END  = "end";
    private static final String RSVP_ELSE = "else";
    private static final String RSVP_ELIF = "elseif";
    private static final String RSVP_CATC = "catch";
    private static final String RSVP_FINL = "finally";
    private static final String RSVP_TRUE = "true";
    private static final String RSVP_FALS = "false";
    private static final String RSVP_IN   = "in";


    
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
    
    
    public static final int IDENTIFIER_N            = 6001;
    public static final int LITERAL_INTEGER_N       = 6002;
    public static final int ASSIGNMENT_OPERATOR_N   = 6003;
    public static final int LE_OPERATOR_N           = 6004;
    public static final int LT_OPERATOR_N           = 6005;
    public static final int GE_OPERATOR_N           = 6006;
    public static final int GT_OPERATOR_N           = 6007;
    public static final int EQ_OPERATOR_N           = 6008;
    public static final int NE_OPERATOR_N           = 6009;
    public static final int ADD_OPERATOR_N          = 6010;
    public static final int SUB_OPERATOR_N          = 6011;
    public static final int MUL_OPERATOR_N          = 6012;
    public static final int DIV_OPERATOR_N          = 6013;
    public static final int POW_OPERATOR_N          = 6014;
    public static final int LITERAL_QUOTE_N         = 6015;
    public static final int LINE_COMMENT_N          = 6016;
    public static final int BEGIN_COMMENT_N         = 6017;
    public static final int END_COMMENT_N           = 6018;
    public static final int LITERAL_COMMA_N         = 6019;
    public static final int OPEN_BRACKET_N          = 6020;
    public static final int CLOSE_BRACKET_N         = 6021;
    public static final int OPEN_BRACE_N            = 6022;
    public static final int CLOSE_BRACE_N           = 6023;
    public static final int WHITE_SPACE_N           = 6024;
    public static final int DOT_PTS_N               = 6025;
    public static final int LITERAL_TEXT_N          = 6026;
    
    
    //==========================================================================
    //RESERVED WORDS NUMBERS
    //Each reserved has a code that is associated to it
    //It will be used in the parser definition for syntax analysis
    //The different codes will be associated to the token regex in key list
    //==========================================================================
    
    
    
    private static final int RSVP_BEGI_N = 5001;
    private static final int RSVP_WHIL_N = 5002;
    private static final int RSVP_IF_N   = 5003;
    private static final int RSVP_FOR_N  = 5004;
    private static final int RSVP_TRY_N  = 5005;
    private static final int RSVP_RETU_N = 5006;
    private static final int RSVP_BRK_N  = 5007;
    private static final int RSVP_CONT_N = 5008;
    private static final int RSVP_FUNC_N = 5009;
    private static final int RSVP_MACR_N = 5010;
    private static final int RSVP_QUOT_N = 5011;
    private static final int RSVP_LET_N  = 5012;
    private static final int RSVP_LOCL_N = 5013;
    private static final int RSVP_GLOB_N = 5014;
    private static final int RSVP_CONS_N = 5015;
    private static final int RSVP_DO_N   = 5016;
    private static final int RSVP_STRU_N = 5017;
    private static final int RSVP_MODU_N = 5018;
    private static final int RSVP_BMOD_N = 5019;
    private static final int RSVP_USNG_N = 5020;
    private static final int RSVP_IMPO_N = 5021;
    private static final int RSVP_EXPO_N = 5022;
    private static final int RSVP_END_N  = 5023;
    private static final int RSVP_ELSE_N = 5024;
    private static final int RSVP_ELIF_N = 5025;
    private static final int RSVP_CATC_N = 5026;
    private static final int RSVP_FINL_N = 5027;
    private static final int RSVP_TRUE_N = 5028;
    private static final int RSVP_FALS_N = 5029;
    private static final int RSVP_IN_N   = 5030;

    
  //==========================================================================
    //tokenInfos
    //linked list of TokenInfo
    //contains the differnt tokens regular experession correspondents
    //Used to make the comparisons during scanning to find literals
    //==========================================================================
    
    private LinkedList<TokenInfo> tokenInfos;
    
    //==========================================================================
    //tokens
    //Linked list of Token
    //Contains the differnt tokens that will be found in the sample file
    //==========================================================================
    
    private LinkedList<Token> tokens;

    // =========================================================================
    // __constructor__ Tokenizer()
    // initialize tokenInfos and tokens
    // =========================================================================
    
    public Tokenizer() {
      tokenInfos = new LinkedList<TokenInfo>();
      tokens = new LinkedList<Token>();
    }
    
    // =========================================================================
    // void add(String regex, int token)
    // Add a new lexical sample to tokenInfos
    // String regex : represents the regex associated to the lexical sample
    // int token : represents the integer code associated to the token
    // =========================================================================
    
    public void add(String regex, int token) {
    tokenInfos.add(
        new TokenInfo(
        Pattern.compile("^("+regex+")"), token));
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
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    if(info.token != WHITE_SPACE_N){
                        tokens.add(new Token(info.token, tok, col_num, line_number));
                    }else{
                        col_num--;
                    }
                    s = m.replaceFirst("");
                    break;
                }
            }
            if (!match) throw new ParserException("Unexpected character in input: "+s);
            col_num++;
        }
        
    }
        
    
    // =========================================================================
    // LinkedList<Token> getTokens()
    // Return the linkedList containing the different tokens detected
    // Returns the attribute tokens
    // =========================================================================
    
    public LinkedList<Token> getTokens() {
        return tokens;
    }
    
}
