import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Arrays;

%%{
    machine cool_lexer;

    # Keywords Action
    action Class    { push_token(new Symbol(TokenConstants.CLASS)); }
    action Inherits { push_token(new Symbol(TokenConstants.INHERITS)); }
    action New      { push_token(new Symbol(TokenConstants.NEW)); }
    action If       { push_token(new Symbol(TokenConstants.IF)); }
    action Then     { push_token(new Symbol(TokenConstants.THEN)); }
    action Else     { push_token(new Symbol(TokenConstants.ELSE)); }
    action Fi       { push_token(new Symbol(TokenConstants.FI)); }
    action Case     { push_token(new Symbol(TokenConstants.CASE)); }
    action Of       { push_token(new Symbol(TokenConstants.OF)); }
    action Esac     { push_token(new Symbol(TokenConstants.ESAC)); }
    action IsVoid   { push_token(new Symbol(TokenConstants.ISVOID)); }
    action While    { push_token(new Symbol(TokenConstants.WHILE)); }
    action Loop     { push_token(new Symbol(TokenConstants.LOOP)); }
    action Pool     { push_token(new Symbol(TokenConstants.POOL)); }

    # Type Action
    action TypeID   {
                        String token = get_token(data, ms, me);
                        AbstractSymbol sym = AbstractTable.idtable.addString(token);
                        push_token(new Symbol(TokenConstants.TYPEID, sym));
                    }
    action ObjectID {
                        String token = get_token(data, ms, me);
                        AbstractSymbol sym = AbstractTable.idtable.addString(token);
                        push_token(new Symbol(TokenConstants.OBJECTID, sym));
                    }

    # Operator Action
    action Assign   { push_token(new Symbol(TokenConstants.ASSIGN)); }
    action Plus     { push_token(new Symbol(TokenConstants.PLUS)); }
    action Minus    { push_token(new Symbol(TokenConstants.MINUS)); }
    action Mult     { push_token(new Symbol(TokenConstants.MULT)); }
    action Div      { push_token(new Symbol(TokenConstants.DIV)); }
    action LParen   { push_token(new Symbol(TokenConstants.LPAREN)); }
    action RParen   { push_token(new Symbol(TokenConstants.RPAREN)); }
    action Not      { push_token(new Symbol(TokenConstants.NOT)); }
    action Eq       { push_token(new Symbol(TokenConstants.EQ)); }
    action Lt       { push_token(new Symbol(TokenConstants.LT)); }
    action Le       { push_token(new Symbol(TokenConstants.LE)); }
    action Neg      { push_token(new Symbol(TokenConstants.NEG)); }
    action LBrace   { push_token(new Symbol(TokenConstants.LBRACE)); }
    action RBrace   { push_token(new Symbol(TokenConstants.RBRACE)); }

    # Special Operator Action
    action Colon    { push_token(new Symbol(TokenConstants.COLON)); }
    action Semi     { push_token(new Symbol(TokenConstants.SEMI)); }
    action Dot      { push_token(new Symbol(TokenConstants.DOT)); }
    action Comma    { push_token(new Symbol(TokenConstants.COMMA)); }
    action At       { push_token(new Symbol(TokenConstants.AT)); }

    # Constant Action
    action Integer  {
                      String token = get_token(data, ms, me);
                      Integer i = Integer.parseInt(token);
                      AbstractSymbol sym = AbstractTable.inttable.addInt(i);
                      push_token(new Symbol(TokenConstants.INT_CONST, sym));
                    }
    action Bool     {
                      String token = get_token(data, ts, te);
                      if (token == "true") {
                        push_token(new Symbol(TokenConstants.BOOL_CONST, true));
                      } else {
                        push_token(new Symbol(TokenConstants.BOOL_CONST, false));
                      }
                    }
    action String   {
                      String token = get_token(data, ms, me);
                      AbstractSymbol sym = AbstractTable.stringtable.addString(token);
                      push_token(new Symbol(TokenConstants.STR_CONST, sym));
                    }

    # Debugging Action - TODO: Remove when done
    action Quote    { System.out.println("QUOTE"); }
    action SEscape  { System.out.println("SESCAPE"); }
    action FV       { System.out.println("Entering feature_variable"); }


    action mark_start   { ms = fpc; }
    action mark_end     { me = fpc + 1; }

    action start_string   { sb = new StringBuffer(); }
    action concat_string  { sb.append(get_token(data, ms, me)); }

    # Comments
    line_comment = '--' . (any - '\n')+;
    multiline_comment = '(*' . (any+ -- '*)') . '*)';
    comment = line_comment | multiline_comment;


    # Keywords
    class = 'class' %Class;
    inherits = 'inherits' %Inherits;
    new = 'new' %New;
    if = 'if' %If;
    then = 'then' %Then;
    else = 'else' %Else;
    fi = 'fi' %Fi;
    case = 'case' %Case;
    of = 'of' %Of;
    esac = 'esac' %Esac;
    isvoid = 'isvoid' %IsVoid;
    while = 'while' %While;
    loop = 'loop' %Loop;
    pool = 'pool' %Pool;

    # Identifier
    keywords = 'class' | 'else' | 'false' | 'fi' | 'if' | 'in' | 'inherits'
                'isvoid' | 'let' | 'loop' | 'pool' | 'then' | 'while' |
                'case' | 'esac' | 'new' | 'of' | 'not' | 'true';
    identifier = ((alpha . (alnum | '_')*) - keywords) >mark_start @mark_end;

    objectid = identifier %ObjectID;
    typeid = identifier %TypeID;

    # Constants
    integer = [0-9]+ >mark_start @mark_end %Integer;
    bool = 'true' | 'false' %Bool;
    quote = '"';
    sescape = '\\' . [ \t]* . '\n';
    scontent = (any* -- quote -- '\n');
    scontent_quote = (scontent . ('\\"' . scontent)*); # >mark_start @mark_end;
    scontent_multiline = (scontent_quote . (sescape . scontent_quote)*) >mark_start @mark_end;
    string = (quote . (scontent_multiline %String) . quote);

    # Operator
    assign = '<-' %Assign;
    plus = '+' %Plus;
    minus = '-' %Minus;
    mult = '*' %Mult;
    div = '/' %Div;
    lparen = '(' %LParen;
    rparen = ')' %RParen;
    not = 'not' %Not;
    eq = '=' %Eq;
    lt = '<' %Lt;
    le = '<=' %Le;
    neg = '~' %Neg;
    lbrace = '{' %LBrace;
    rbrace = '}' %RBrace;

    # Special operator
    colon = ':' %Colon;
    semi = ';' %Semi;
    dot = '.' %Dot;
    comma = ',' %Comma;
    at = '@' %At;

    expr = (
      assign |
      plus | minus | mult | div |
      neg |
      lt | le | eq |
      not |
      lparen | rparen |
      lbrace | rbrace |
      integer |
      string |
      bool |
      if | then | else | fi |
      case | of | esac |
      while | loop | pool |
      isvoid |
      objectid |
      colon | semi | dot | comma | at |
      space
    );

    formal = objectid . space* . colon . space* . typeid;

    function_def = objectid . lparen . formal? . (comma . formal)* . rparen . lbrace . expr+ . rbrace;
    # feature_variable = objectid . space* . ':' . space* . typeid .
    #  (space* . '<-' . space* . expr+)?;
    # feature = (feature_method | feature_variable | space)+;

    inherits_stmt = 'inherits' @Inherits . space+ . typeid;
    class_stmt = class . space+ . typeid . space+ . inherits_stmt?;
    new_stmt = new . typeid;

    main := |*
        # comment;
        # class_stmt;
        # new_stmt;
        # formal;
        sescape;
        expr;
        space;
        any => { System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); };
    *|;
}%%

class CoolLexer implements java_cup.runtime.Scanner {
    // Max size of string constants
    static int MAX_STR_CONST = 1025;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();
    private int curr_lineno = 1;
    private AbstractSymbol filename;
    private BufferedReader reader;
    LinkedList<Symbol> tokens = new LinkedList<Symbol>();

    CoolLexer(java.io.Reader reader) throws IOException {
        this();
        if (null == reader) {
            throw (new Error("Error: Bad input stream initializer."));
        }
        this.reader = new java.io.BufferedReader(reader);
        run_lexer();
    }

    CoolLexer(java.io.InputStream instream) throws IOException {
        this();
        if (null == instream) {
            throw (new Error("Error: Bad input stream initializer."));
        }
        this.reader = new BufferedReader(new InputStreamReader(instream));
        run_lexer();
    }

    private CoolLexer() {
        // Initialization code here
    }

    private void run_lexer() throws IOException {
        StringBuffer sb = new StringBuffer();
        int c;
        while ((c = this.reader.read()) != -1) {
            sb.append(Character.toChars(c));
        }
        char[] data = sb.toString().toCharArray();

        int p = 0;
        int pe = data.length;
        int eof = data.length;
        int ts = 0;
        int te = 0;
        int cs;
        int act;

        // ms and me means mark start and mark end respectively
        int ms = 0;
        int me = 0;

        %%write init;
        %%write exec;
    }

    public Symbol next_token() {
        return this.tokens.poll();
    }

    void push_token(Symbol sym) {
        this.tokens.add(sym);
    }

    int get_curr_lineno() {
        return this.curr_lineno;
    }

    void set_filename(String fname) {
        filename = AbstractTable.stringtable.addString(fname);
    }

    String get_token(char[] data, int ts, int te) {
        char[] token = Arrays.copyOfRange(data, ts, te);
        return new String(token);
    }

    AbstractSymbol curr_filename() {
        return filename;
    }

    %%write data;
}
