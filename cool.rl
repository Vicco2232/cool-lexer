import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Arrays;

%%{
    machine cool_lexer;

    action Class    { push_token(new Symbol(TokenConstants.CLASS)); }
    action Inherits { push_token(new Symbol(TokenConstants.INHERITS)); }
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

    action FV       { System.out.println("Entering feature_variable"); }


    action mark_start   { ms = fpc; }
    action mark_end     { me = fpc + 1; }

    # Comments
    line_comment = /--.*/;
    multiline_comment = '(*' . (any+ -- '*)') . '*)';
    comment = line_comment | multiline_comment;

    # Identifier
    keywords = 'class' | 'else' | 'false' | 'fi' | 'if' | 'in' | 'inherits'
                'isvoid' | 'let' | 'loop' | 'pool' | 'then' | 'while' |
                'case' | 'esac' | 'new' | 'of' | 'not' | 'true';
    identifier = ((alpha . (alnum | '_')*) - keywords) >mark_start @mark_end;

    objectid = identifier %ObjectID;
    typeid = identifier %TypeID;

    # Constants
    integer = [0-9]+;
    bool = 'true' | 'false';
    string = '"' . (any - '"') . '"';

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

    expr = (
      plus | minus | mult | div |
      neg |
      lt | le | eq |
      not |
      lparen | rparen |
      integer |
      string |
      bool |
      identifier |
      space+
    );

    formal = objectid . space* . ':' . space* . typeid;

    feature_method = identifier . '(' . formal? . (',' . formal)* . ')' . '{' . expr . '}';
    feature_variable = (objectid . space* . ':' . space* . typeid) >FV;
    # feature_variable = objectid . space* . ':' . space* . typeid .
    #  (space* . '<-' . space* . expr+)?;
    feature = (feature_method | feature_variable | space)+;

    inherits_stmt = 'inherits' @Inherits . space+ . (identifier %TypeID);
    class = 'class' @Class . space+ . (identifier %TypeID) . space+ .
            inherits_stmt? . space+ . '{' . feature* . '}';

    main := |*
        class => { System.out.println("Class Definition: " + get_token(data, ts, te)); };
    comment => { /* System.out.println("Ignoring comment: " + get_token(data, ts, te)); */ };
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
