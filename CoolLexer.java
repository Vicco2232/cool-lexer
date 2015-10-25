
// line 1 "cool.rl"
import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Arrays;


// line 196 "cool.rl"


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

        
// line 71 "CoolLexer.java"
	{
	cs = cool_lexer_start;
	ts = -1;
	te = -1;
	act = 0;
	}

// line 252 "cool.rl"
        
// line 81 "CoolLexer.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
case 1:
	_acts = _cool_lexer_from_state_actions[cs];
	_nacts = (int) _cool_lexer_actions[_acts++];
	while ( _nacts-- > 0 ) {
		switch ( _cool_lexer_actions[_acts++] ) {
	case 37:
// line 1 "NONE"
	{ts = p;}
	break;
// line 106 "CoolLexer.java"
		}
	}

	_match: do {
	_keys = _cool_lexer_key_offsets[cs];
	_trans = _cool_lexer_index_offsets[cs];
	_klen = _cool_lexer_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _cool_lexer_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _cool_lexer_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _cool_lexer_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _cool_lexer_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _cool_lexer_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _cool_lexer_indicies[_trans];
case 3:
	cs = _cool_lexer_trans_targs[_trans];

	if ( _cool_lexer_trans_actions[_trans] != 0 ) {
		_acts = _cool_lexer_trans_actions[_trans];
		_nacts = (int) _cool_lexer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _cool_lexer_actions[_acts++] )
			{
	case 0:
// line 17 "cool.rl"
	{ push_token(new Symbol(TokenConstants.IF)); }
	break;
	case 1:
// line 18 "cool.rl"
	{ push_token(new Symbol(TokenConstants.THEN)); }
	break;
	case 2:
// line 19 "cool.rl"
	{ push_token(new Symbol(TokenConstants.ELSE)); }
	break;
	case 3:
// line 20 "cool.rl"
	{ push_token(new Symbol(TokenConstants.FI)); }
	break;
	case 4:
// line 21 "cool.rl"
	{ push_token(new Symbol(TokenConstants.CASE)); }
	break;
	case 5:
// line 22 "cool.rl"
	{ push_token(new Symbol(TokenConstants.OF)); }
	break;
	case 6:
// line 23 "cool.rl"
	{ push_token(new Symbol(TokenConstants.ESAC)); }
	break;
	case 7:
// line 24 "cool.rl"
	{ push_token(new Symbol(TokenConstants.ISVOID)); }
	break;
	case 8:
// line 25 "cool.rl"
	{ push_token(new Symbol(TokenConstants.WHILE)); }
	break;
	case 9:
// line 26 "cool.rl"
	{ push_token(new Symbol(TokenConstants.LOOP)); }
	break;
	case 10:
// line 27 "cool.rl"
	{ push_token(new Symbol(TokenConstants.POOL)); }
	break;
	case 11:
// line 35 "cool.rl"
	{
                        String token = get_token(data, ms, me);
                        AbstractSymbol sym = AbstractTable.idtable.addString(token);
                        push_token(new Symbol(TokenConstants.OBJECTID, sym));
                    }
	break;
	case 12:
// line 42 "cool.rl"
	{ push_token(new Symbol(TokenConstants.ASSIGN)); }
	break;
	case 13:
// line 43 "cool.rl"
	{ push_token(new Symbol(TokenConstants.PLUS)); }
	break;
	case 14:
// line 44 "cool.rl"
	{ push_token(new Symbol(TokenConstants.MINUS)); }
	break;
	case 15:
// line 45 "cool.rl"
	{ push_token(new Symbol(TokenConstants.MULT)); }
	break;
	case 16:
// line 46 "cool.rl"
	{ push_token(new Symbol(TokenConstants.DIV)); }
	break;
	case 17:
// line 47 "cool.rl"
	{ push_token(new Symbol(TokenConstants.LPAREN)); }
	break;
	case 18:
// line 48 "cool.rl"
	{ push_token(new Symbol(TokenConstants.RPAREN)); }
	break;
	case 19:
// line 49 "cool.rl"
	{ push_token(new Symbol(TokenConstants.NOT)); }
	break;
	case 20:
// line 50 "cool.rl"
	{ push_token(new Symbol(TokenConstants.EQ)); }
	break;
	case 21:
// line 51 "cool.rl"
	{ push_token(new Symbol(TokenConstants.LT)); }
	break;
	case 22:
// line 52 "cool.rl"
	{ push_token(new Symbol(TokenConstants.LE)); }
	break;
	case 23:
// line 53 "cool.rl"
	{ push_token(new Symbol(TokenConstants.NEG)); }
	break;
	case 24:
// line 54 "cool.rl"
	{ push_token(new Symbol(TokenConstants.LBRACE)); }
	break;
	case 25:
// line 55 "cool.rl"
	{ push_token(new Symbol(TokenConstants.RBRACE)); }
	break;
	case 26:
// line 58 "cool.rl"
	{ push_token(new Symbol(TokenConstants.COLON)); }
	break;
	case 27:
// line 59 "cool.rl"
	{ push_token(new Symbol(TokenConstants.SEMI)); }
	break;
	case 28:
// line 60 "cool.rl"
	{ push_token(new Symbol(TokenConstants.DOT)); }
	break;
	case 29:
// line 61 "cool.rl"
	{ push_token(new Symbol(TokenConstants.COMMA)); }
	break;
	case 30:
// line 62 "cool.rl"
	{ push_token(new Symbol(TokenConstants.AT)); }
	break;
	case 31:
// line 65 "cool.rl"
	{
                      String token = get_token(data, ms, me);
                      Integer i = Integer.parseInt(token);
                      AbstractSymbol sym = AbstractTable.inttable.addInt(i);
                      push_token(new Symbol(TokenConstants.INT_CONST, sym));
                    }
	break;
	case 32:
// line 71 "cool.rl"
	{
                      String token = get_token(data, ts, te);
                      if (token == "true") {
                        push_token(new Symbol(TokenConstants.BOOL_CONST, true));
                      } else {
                        push_token(new Symbol(TokenConstants.BOOL_CONST, false));
                      }
                    }
	break;
	case 33:
// line 79 "cool.rl"
	{
                      String token = get_token(data, ms, me);
                      AbstractSymbol sym = AbstractTable.stringtable.addString(token);
                      push_token(new Symbol(TokenConstants.STR_CONST, sym));
                    }
	break;
	case 34:
// line 90 "cool.rl"
	{ ms = p; }
	break;
	case 35:
// line 91 "cool.rl"
	{ me = p + 1; }
	break;
	case 38:
// line 1 "NONE"
	{te = p+1;}
	break;
	case 39:
// line 192 "cool.rl"
	{act = 1;}
	break;
	case 40:
// line 194 "cool.rl"
	{act = 3;}
	break;
	case 41:
// line 192 "cool.rl"
	{te = p+1;}
	break;
	case 42:
// line 194 "cool.rl"
	{te = p+1;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 43:
// line 192 "cool.rl"
	{te = p;p--;}
	break;
	case 44:
// line 194 "cool.rl"
	{te = p;p--;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 45:
// line 192 "cool.rl"
	{{p = ((te))-1;}}
	break;
	case 46:
// line 1 "NONE"
	{	switch( act ) {
	case 3:
	{{p = ((te))-1;} System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }
	break;
	default:
	{{p = ((te))-1;}}
	break;
	}
	}
	break;
// line 378 "CoolLexer.java"
			}
		}
	}

case 2:
	_acts = _cool_lexer_to_state_actions[cs];
	_nacts = (int) _cool_lexer_actions[_acts++];
	while ( _nacts-- > 0 ) {
		switch ( _cool_lexer_actions[_acts++] ) {
	case 36:
// line 1 "NONE"
	{ts = -1;}
	break;
// line 392 "CoolLexer.java"
		}
	}

	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	if ( _cool_lexer_eof_trans[cs] > 0 ) {
		_trans = _cool_lexer_eof_trans[cs] - 1;
		_goto_targ = 3;
		continue _goto;
	}
	}

case 5:
	}
	break; }
	}

// line 253 "cool.rl"
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

    
// line 444 "CoolLexer.java"
private static byte[] init__cool_lexer_actions_0()
{
	return new byte [] {
	    0,    1,   35,    1,   36,    1,   37,    1,   41,    1,   42,    1,
	   43,    1,   44,    1,   45,    1,   46,    2,    0,   43,    2,    1,
	   43,    2,    2,   43,    2,    3,   43,    2,    4,   43,    2,    5,
	   43,    2,    6,   43,    2,    8,   43,    2,    9,   43,    2,   10,
	   43,    2,   11,   43,    2,   12,   43,    2,   13,   43,    2,   14,
	   43,    2,   15,   43,    2,   16,   43,    2,   17,   43,    2,   18,
	   43,    2,   19,   43,    2,   20,   43,    2,   21,   43,    2,   22,
	   43,    2,   23,   43,    2,   24,   43,    2,   25,   43,    2,   26,
	   43,    2,   27,   43,    2,   28,   43,    2,   29,   43,    2,   30,
	   43,    2,   31,   43,    2,   32,   43,    2,   34,   35,    2,   38,
	   35,    2,   38,   40,    3,    7,   11,   43,    3,   38,   34,   35,
	    4,   38,   35,   33,   39,    5,   38,   34,   35,   33,   39
	};
}

private static final byte _cool_lexer_actions[] = init__cool_lexer_actions_0();


private static short[] init__cool_lexer_key_offsets_0()
{
	return new short [] {
	    0,    1,    8,   16,   52,   53,   54,   54,   54,   54,   54,   54,
	   54,   54,   54,   56,   56,   56,   58,   58,   58,   58,   58,   65,
	   74,   82,   90,   97,  105,  113,  121,  130,  138,  146,  153,  161,
	  169,  176,  185,  193,  201,  209,  216,  223,  233,  240,  248,  256,
	  264,  272,  280,  288,  296,  304,  312,  320,  328,  336,  344,  352,
	  360,  367,  376,  384,  392,  400,  407,  416,  424,  432,  439,  447,
	  454,  462,  470,  478,  485,  494,  502,  510,  517,  525,  533,  540,
	  548,  556,  564,  572,  579,  579,  579
	};
}

private static final short _cool_lexer_key_offsets[] = init__cool_lexer_key_offsets_0();


private static char[] init__cool_lexer_trans_keys_0()
{
	return new char [] {
	   34,   95,   48,   57,   65,   90,   97,  122,   95,  104,   48,   57,
	   65,   90,   97,  122,   32,   34,   40,   41,   42,   43,   44,   45,
	   46,   47,   58,   59,   60,   61,   64,   99,  101,  102,  105,  108,
	  110,  111,  112,  116,  119,  123,  125,  126,    9,   13,   48,   57,
	   65,   90,   97,  122,   34,   34,   48,   57,   45,   61,   95,   48,
	   57,   65,   90,   97,  122,   95,   97,  108,   48,   57,   65,   90,
	   98,  122,   95,  115,   48,   57,   65,   90,   97,  122,   95,  101,
	   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,
	  122,   95,   97,   48,   57,   65,   90,   98,  122,   95,  115,   48,
	   57,   65,   90,   97,  122,   95,  115,   48,   57,   65,   90,   97,
	  122,   95,  108,  115,   48,   57,   65,   90,   97,  122,   95,  115,
	   48,   57,   65,   90,   97,  122,   95,  101,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,   97,   48,
	   57,   65,   90,   98,  122,   95,   99,   48,   57,   65,   90,   97,
	  122,   95,   48,   57,   65,   90,   97,  122,   95,   97,  105,   48,
	   57,   65,   90,   98,  122,   95,  108,   48,   57,   65,   90,   97,
	  122,   95,  115,   48,   57,   65,   90,   97,  122,   95,  101,   48,
	   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,
	   95,   48,   57,   65,   90,   97,  122,   95,  102,  110,  115,   48,
	   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,
	   95,  101,   48,   57,   65,   90,   97,  122,   95,  114,   48,   57,
	   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,   97,  122,
	   95,  116,   48,   57,   65,   90,   97,  122,   95,  115,   48,   57,
	   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,   97,  122,
	   95,  115,   48,   57,   65,   90,   97,  122,   95,  118,   48,   57,
	   65,   90,   97,  122,   95,  111,   48,   57,   65,   90,   97,  122,
	   95,  105,   48,   57,   65,   90,   97,  122,   95,  100,   48,   57,
	   65,   90,   97,  122,   95,  118,   48,   57,   65,   90,   97,  122,
	   95,  111,   48,   57,   65,   90,   97,  122,   95,  105,   48,   57,
	   65,   90,   97,  122,   95,  100,   48,   57,   65,   90,   97,  122,
	   95,   48,   57,   65,   90,   97,  122,   95,  101,  111,   48,   57,
	   65,   90,   97,  122,   95,  116,   48,   57,   65,   90,   97,  122,
	   95,  111,   48,   57,   65,   90,   97,  122,   95,  112,   48,   57,
	   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,
	  101,  111,   48,   57,   65,   90,   97,  122,   95,  119,   48,   57,
	   65,   90,   97,  122,   95,  116,   48,   57,   65,   90,   97,  122,
	   95,   48,   57,   65,   90,   97,  122,   95,  102,   48,   57,   65,
	   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  111,
	   48,   57,   65,   90,   97,  122,   95,  111,   48,   57,   65,   90,
	   97,  122,   95,  108,   48,   57,   65,   90,   97,  122,   95,   48,
	   57,   65,   90,   97,  122,   95,  104,  114,   48,   57,   65,   90,
	   97,  122,   95,  101,   48,   57,   65,   90,   97,  122,   95,  110,
	   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,
	  122,   95,  117,   48,   57,   65,   90,   97,  122,   95,  101,   48,
	   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,
	   95,  104,   48,   57,   65,   90,   97,  122,   95,  105,   48,   57,
	   65,   90,   97,  122,   95,  108,   48,   57,   65,   90,   97,  122,
	   95,  101,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,
	   90,   97,  122,    0
	};
}

private static final char _cool_lexer_trans_keys[] = init__cool_lexer_trans_keys_0();


private static byte[] init__cool_lexer_single_lengths_0()
{
	return new byte [] {
	    1,    1,    2,   28,    1,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    2,    0,    0,    0,    0,    1,    3,
	    2,    2,    1,    2,    2,    2,    3,    2,    2,    1,    2,    2,
	    1,    3,    2,    2,    2,    1,    1,    4,    1,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    1,    3,    2,    2,    2,    1,    3,    2,    2,    1,    2,    1,
	    2,    2,    2,    1,    3,    2,    2,    1,    2,    2,    1,    2,
	    2,    2,    2,    1,    0,    0,    0
	};
}

private static final byte _cool_lexer_single_lengths[] = init__cool_lexer_single_lengths_0();


private static byte[] init__cool_lexer_range_lengths_0()
{
	return new byte [] {
	    0,    3,    3,    4,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    0,    0,    0
	};
}

private static final byte _cool_lexer_range_lengths[] = init__cool_lexer_range_lengths_0();


private static short[] init__cool_lexer_index_offsets_0()
{
	return new short [] {
	    0,    2,    7,   13,   46,   48,   50,   51,   52,   53,   54,   55,
	   56,   57,   58,   60,   61,   62,   65,   66,   67,   68,   69,   74,
	   81,   87,   93,   98,  104,  110,  116,  123,  129,  135,  140,  146,
	  152,  157,  164,  170,  176,  182,  187,  192,  200,  205,  211,  217,
	  223,  229,  235,  241,  247,  253,  259,  265,  271,  277,  283,  289,
	  295,  300,  307,  313,  319,  325,  330,  337,  343,  349,  354,  360,
	  365,  371,  377,  383,  388,  395,  401,  407,  412,  418,  424,  429,
	  435,  441,  447,  453,  458,  459,  460
	};
}

private static final short _cool_lexer_index_offsets[] = init__cool_lexer_index_offsets_0();


private static short[] init__cool_lexer_indicies_0()
{
	return new short [] {
	    2,    1,    4,    4,    4,    4,    3,    4,    5,    4,    4,    4,
	    3,    7,    8,    9,   10,   11,   12,   13,   14,   15,   16,   18,
	   19,   20,   21,   22,   24,   25,   26,   27,   28,   29,   30,   31,
	   32,   33,   34,   35,   36,    7,   17,   23,   23,    6,   39,   38,
	    2,    1,   41,   42,   43,   44,   45,   46,   47,   48,   50,   49,
	   51,   52,   54,   55,   53,   56,   57,   58,   59,    4,    4,    4,
	    4,   60,    4,   61,   62,    4,    4,    4,   60,    4,   63,    4,
	    4,    4,   60,    4,   64,    4,    4,    4,   60,    4,    4,    4,
	    4,   65,    4,   66,    4,    4,    4,   60,    4,   67,    4,    4,
	    4,   60,    4,   68,    4,    4,    4,   60,    4,   69,   70,    4,
	    4,    4,   60,    4,   71,    4,    4,    4,   60,    4,   72,    4,
	    4,    4,   60,    4,    4,    4,    4,   73,    4,   74,    4,    4,
	    4,   60,    4,   75,    4,    4,    4,   60,    4,    4,    4,    4,
	   76,    4,   77,   78,    4,    4,    4,   60,    4,   79,    4,    4,
	    4,   60,    4,   80,    4,    4,    4,   60,    4,   81,    4,    4,
	    4,   60,    4,    4,    4,    4,   82,    4,    4,    4,    4,   83,
	    4,   84,   85,   86,    4,    4,    4,   60,    4,    4,    4,    4,
	   87,    4,   88,    4,    4,    4,   60,    4,   89,    4,    4,    4,
	   60,    4,   90,    4,    4,    4,   60,    4,   91,    4,    4,    4,
	   60,    4,   92,    4,    4,    4,   60,    4,   93,    4,    4,    4,
	   60,    4,   94,    4,    4,    4,   60,    4,   95,    4,    4,    4,
	   60,    4,   96,    4,    4,    4,   60,    4,   97,    4,    4,    4,
	   60,    4,   68,    4,    4,    4,   60,    4,   98,    4,    4,    4,
	   60,    4,   99,    4,    4,    4,   60,    4,  100,    4,    4,    4,
	   60,    4,  101,    4,    4,    4,   60,    4,    4,    4,    4,  102,
	    4,  103,  104,    4,    4,    4,   60,    4,   68,    4,    4,    4,
	   60,    4,  105,    4,    4,    4,   60,    4,  106,    4,    4,    4,
	   60,    4,    4,    4,    4,  107,    4,  108,  109,    4,    4,    4,
	   60,    4,   68,    4,    4,    4,   60,    4,  110,    4,    4,    4,
	   60,    4,    4,    4,    4,  111,    4,  112,    4,    4,    4,   60,
	    4,    4,    4,    4,  113,    4,  114,    4,    4,    4,   60,    4,
	  115,    4,    4,    4,   60,    4,  116,    4,    4,    4,   60,    4,
	    4,    4,    4,  117,    4,  118,  119,    4,    4,    4,   60,    4,
	  120,    4,    4,    4,   60,    4,  121,    4,    4,    4,   60,    4,
	    4,    4,    4,  122,    4,  123,    4,    4,    4,   60,    4,  124,
	    4,    4,    4,   60,    4,    4,    4,    4,   40,    4,  125,    4,
	    4,    4,   60,    4,  126,    4,    4,    4,   60,    4,  127,    4,
	    4,    4,   60,    4,  128,    4,    4,    4,   60,    4,    4,    4,
	    4,  129,  130,  131,  132,    0
	};
}

private static final short _cool_lexer_indicies[] = init__cool_lexer_indicies_0();


private static byte[] init__cool_lexer_trans_targs_0()
{
	return new byte [] {
	    3,    0,    5,    3,   22,   45,    3,    3,    4,    6,    7,    8,
	    9,   10,   11,   12,   13,   14,   15,   16,   17,   20,   21,   22,
	   23,   30,   37,   43,   61,   66,   70,   72,   76,   83,   88,   89,
	   90,    3,    0,    5,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,   14,    3,    3,    3,   18,   19,    3,    3,    3,    3,
	    3,   24,   27,   25,   26,    3,   28,   29,    1,   31,   34,   32,
	   33,    3,   35,   36,    3,   38,   42,   39,   40,   41,    3,    3,
	   44,    2,   56,    3,   46,   47,   48,   49,   50,   51,   52,   53,
	   54,   55,   57,   58,   59,   60,    3,   62,   63,   64,   65,    3,
	   67,   68,   69,    3,   71,    3,   73,   74,   75,    3,   77,   80,
	   78,   79,    3,   81,   82,   84,   85,   86,   87,    3,    3,    3,
	    3
	};
}

private static final byte _cool_lexer_trans_targs[] = init__cool_lexer_trans_targs_0();


private static short[] init__cool_lexer_trans_actions_0()
{
	return new short [] {
	   17,    1,  132,   15,    1,    1,    9,    7,  121,    0,    0,    0,
	    0,    0,    0,    0,    0,  115,    0,    0,    0,    0,    0,  115,
	  115,  115,  115,  128,  115,  115,  115,  115,  115,  115,    0,    0,
	    0,   13,  115,  137,   11,   67,   70,   61,   55,  103,   58,  100,
	   64,  109,    1,   94,   97,   79,    0,    0,   52,   82,   76,  106,
	   49,    1,    1,    1,    0,   31,    1,  118,    0,    1,    1,    1,
	    0,   25,    1,    0,   37,    1,    0,    1,    1,    0,  112,   28,
	    0,    0,    1,   19,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,  118,    1,    1,    1,    1,  124,  118,    1,    1,    0,   43,
	  118,    1,    0,   73,    0,   34,    1,    1,    0,   46,    1,    1,
	    1,    0,   22,    1,    0,    1,    1,    1,    0,   40,   88,   91,
	   85
	};
}

private static final short _cool_lexer_trans_actions[] = init__cool_lexer_trans_actions_0();


private static short[] init__cool_lexer_to_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    3,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0
	};
}

private static final short _cool_lexer_to_state_actions[] = init__cool_lexer_to_state_actions_0();


private static short[] init__cool_lexer_from_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    5,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0
	};
}

private static final short _cool_lexer_from_state_actions[] = init__cool_lexer_from_state_actions_0();


private static short[] init__cool_lexer_eof_trans_0()
{
	return new short [] {
	    1,    4,    4,    0,   38,   41,   42,   43,   44,   45,   46,   47,
	   48,   49,   50,   52,   53,   54,   57,   58,   59,   60,   61,   61,
	   61,   61,   66,   61,   61,   61,   61,   61,   61,   74,   61,   61,
	   77,   61,   61,   61,   61,   83,   84,   61,   88,   61,   61,   61,
	   61,   61,   61,   61,   61,   61,   61,   61,   61,   61,   61,   61,
	  103,   61,   61,   61,   61,  108,   61,   61,   61,  112,   61,  114,
	   61,   61,   61,  118,   61,   61,   61,  123,   61,   61,   41,   61,
	   61,   61,   61,  130,  131,  132,  133
	};
}

private static final short _cool_lexer_eof_trans[] = init__cool_lexer_eof_trans_0();


static final int cool_lexer_start = 3;
static final int cool_lexer_first_final = 3;
static final int cool_lexer_error = -1;

static final int cool_lexer_en_main = 3;


// line 281 "cool.rl"
}
