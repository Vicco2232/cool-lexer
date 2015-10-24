/*
 *  The scanner definition for COOL.
 */

import java_cup.runtime.Symbol;

%%

%{

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */

    // Max size of string constants
    static int MAX_STR_CONST = 1025;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    private int curr_lineno = 1;
    int get_curr_lineno() {
        return curr_lineno;
    }

    private AbstractSymbol filename;

    void set_filename(String fname) {
        filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
        return filename;
    }
%}

%init{

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */

    // empty for now
%init}

%eofval{

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */

    switch(yy_lexical_state) {
        case YYINITIAL:
            /* nothing special to do in the initial state */
            break;
        /* If necessary, add code for other states here, e.g:
        case COMMENT:
            ...
            break;
        */
    }
    return new Symbol(TokenConstants.EOF);
%eofval}

%class CoolLexer
%cup

%state COMMENT
%state STRING

Whitespace = [\n\t\f\r\v ]

%%

<YYINITIAL>\-\-.+                 { /* Comments */ }
<YYINITIAL>\(\*                   { yybegin(COMMENT); }
<COMMENT>[^\)]+\*\)               { yybegin(YYINITIAL); }

<YYINITIAL>"=>"                   { return new Symbol(TokenConstants.DARROW); }

<YYINITIAL>class                  { return new Symbol(TokenConstants.CLASS); }
<YYINITIAL>inherits               { return new Symbol(TokenConstants.INHERITS); }

<YYINITIAL>let                    { return new Symbol(TokenConstants.LET); }

<YYINITIAL>new                    { return new Symbol(TokenConstants.NEW); }

<YYINITIAL>if                     { return new Symbol(TokenConstants.IF); }
<YYINITIAL>then                   { return new Symbol(TokenConstants.THEN); }
<YYINITIAL>else                   { return new Symbol(TokenConstants.ELSE); }
<YYINITIAL>fi                     { return new Symbol(TokenConstants.FI); }

<YYINITIAL>loop                   { return new Symbol(TokenConstants.LOOP); }
<YYINITIAL>pool                   { return new Symbol(TokenConstants.POOL); }

<YYINITIAL>case                   { return new Symbol(TokenConstants.CASE); }
<YYINITIAL>of                     { return new Symbol(TokenConstants.OF); }
<YYINITIAL>esac                   { return new Symbol(TokenConstants.ESAC); }

<YYINITIAL>isvoid                 { return new Symbol(TokenConstants.ISVOID); }

<YYINITIAL>"{"                    { return new Symbol(TokenConstants.LBRACE); }
<YYINITIAL>"}"                    { return new Symbol(TokenConstants.RBRACE); }

<YYINITIAL>"("                    { return new Symbol(TokenConstants.LPAREN); }
<YYINITIAL>")"                    { return new Symbol(TokenConstants.RPAREN); }

<YYINITIAL>"@"                    { return new Symbol(TokenConstants.AT); }
<YYINITIAL>"."                    { return new Symbol(TokenConstants.DOT); }

<YYINITIAL>":"                    { return new Symbol(TokenConstants.COLON); }
<YYINITIAL>";"                    { return new Symbol(TokenConstants.SEMI); }

<YYINITIAL>"~"                    { return new Symbol(TokenConstants.NEG); }

<YYINITIAL>"*"                    { return new Symbol(TokenConstants.MULT); }
<YYINITIAL>"+"                    { return new Symbol(TokenConstants.PLUS); }
<YYINITIAL>"/"                    { return new Symbol(TokenConstants.DIV); }
<YYINITIAL>"-"                    { return new Symbol(TokenConstants.MINUS); }

<YYINITIAL>","                    { return new Symbol(TokenConstants.COMMA); }
<YYINITIAL>"<-"                   { return new Symbol(TokenConstants.ASSIGN); }

<YYINITIAL>"<"                    { return new Symbol(TokenConstants.LT); }
<YYINITIAL>"<="                   { return new Symbol(TokenConstants.LE); }
<YYINITIAL>"="                    { return new Symbol(TokenConstants.EQ); }


<YYINITIAL>(true|false)           {
                                    String s = yytext();
                                    if (s == "true") {
                                      return new Symbol(TokenConstants.BOOL_CONST, true);
                                    } else {
                                      return new Symbol(TokenConstants.BOOL_CONST, false);
                                    }
                                  }

<YYINITIAL>(\-|\+)?[0-9]+         {
                                    Integer i = Integer.parseInt(yytext());
                                    AbstractSymbol sym = AbstractTable.inttable.addInt(i);
                                    return new Symbol(TokenConstants.INT_CONST, sym);
                                  }

<YYINITIAL>\"                     { yybegin(STRING); }
<STRING>\"                        {
                                    String s = yytext();
                                    System.out.println(s);
                                    if (s.charAt(s.length() - 1) != '\\') {
                                      AbstractSymbol sym = AbstractTable.stringtable.addString(s);
                                      yybegin(YYINITIAL);
                                      return new Symbol(TokenConstants.STR_CONST, sym);
                                    }
                                  }

<YYINITIAL>[A-Za-z][A-Za-z0-9_]+  {
                                    AbstractSymbol sym = AbstractTable.idtable.addString(yytext());
                                    return new Symbol(TokenConstants.OBJECTID, sym);
                                  }

<YYINITIAL>{Whitespace}           { /* Ignore whitespace */ }

.                                 { System.err.println("LEXER BUG - UNMATCHED: " +
                                    yytext()); }
