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
                      // String token = get_token(data, ms, me);
                      String token = sb.toString();
                      AbstractSymbol sym = AbstractTable.stringtable.addString(token);
                      push_token(new Symbol(TokenConstants.STR_CONST, sym));
                    }

    # Error Action
    action EOFComment       { push_token(new Symbol(TokenConstants.ERROR, "EOF in string constant")); }
    action UnmatchedComment { push_token(new Symbol(TokenConstants.ERROR, "Unmatched *)")); }

    # Debugging Action - TODO: Remove when done
    action Quote      { System.out.println("QUOTE"); }
    action SEscape    { System.out.println("SESCAPE"); }
    action FV         { System.out.println("Entering feature_variable"); }


    action mark_start   { ms = me = fpc; }
    action mark_end     { me = fpc + 1; }


    # String Action
    action string_start             { sb = new StringBuffer(); }
    action string_concat            { sb.append(get_token(data, ms, me)); }
    action string_concat_backspace  { sb.append('\b'); }
    action string_concat_tab        { sb.append('\t'); }
    action string_concat_newline    { sb.append('\n'); }
    action string_concat_formfeed   { sb.append('\f'); }
    action string_concat_quote      { sb.append('"'); }
    action string_concat_char       { sb.append(fc); }

    action Newline  { this.curr_lineno += 1; }


    newline = '\n' %Newline;

    # Comments
    line_comment = '--' . (any - '\n')+;
    multiline_comment = '(*' . (((any - '\n')|newline)+ -- '*)') . '*)';
    eof_comment = ('(*' . (any+ -- '*)')) %EOFComment;
    unmatched_comment = '*)' %UnmatchedComment;

    comment = line_comment | multiline_comment | eof_comment | unmatched_comment;


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


    # String Constant
    quote = '"';

    sbackspace = '\\b' %string_concat_backspace;
    stab = '\\t' %string_concat_tab;
    snewline = '\\n' %string_concat_newline;
    sformfeed = '\\f' %string_concat_formfeed;
    squote = '\\"' %string_concat_quote;
    schar = ('\\' . (any - 'b' - 't' - 'n' -'f' - '"')) @string_concat_char;
    sescape = ('\\' . [ \t]* . newline) %string_concat_newline;

    sspecials = sbackspace | stab | snewline | sformfeed | squote | schar | sescape;

    scontent = (any* -- quote -- '\\') >mark_start @mark_end %string_concat;
    scontent_quote = (scontent . (sspecials . scontent)*);
    string = ((quote >string_start) . (scontent_quote %String) . quote);


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

    inherits_stmt = 'inherits' @Inherits . space+ . typeid;
    class_stmt = class . space+ . typeid . space+ . inherits_stmt?;
    new_stmt = new . typeid;

    main := |*
      (
        newline |
        comment |
        class_stmt |
        new_stmt |
        formal |
        expr |
        space
      );
      any => { push_token(new Symbol(TokenConstants.ERROR, fc)); };
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
    LinkedList<Integer> lineNumbers = new LinkedList<Integer>();

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
        this.lineNumbers.add(new Integer(this.curr_lineno));
    }

    public Integer next_lineno() {
        return this.lineNumbers.poll();
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
