
// line 1 "cool.rl"
import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Arrays;


// line 202 "cool.rl"


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

// line 258 "cool.rl"
        
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
// line 91 "cool.rl"
	{ ms = p; }
	break;
	case 35:
// line 92 "cool.rl"
	{ me = p + 1; }
	break;
	case 38:
// line 1 "NONE"
	{te = p+1;}
	break;
	case 39:
// line 198 "cool.rl"
	{act = 2;}
	break;
	case 40:
// line 200 "cool.rl"
	{act = 4;}
	break;
	case 41:
// line 197 "cool.rl"
	{te = p+1;}
	break;
	case 42:
// line 198 "cool.rl"
	{te = p+1;}
	break;
	case 43:
// line 200 "cool.rl"
	{te = p+1;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 44:
// line 198 "cool.rl"
	{te = p;p--;}
	break;
	case 45:
// line 200 "cool.rl"
	{te = p;p--;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 46:
// line 198 "cool.rl"
	{{p = ((te))-1;}}
	break;
	case 47:
// line 200 "cool.rl"
	{{p = ((te))-1;}{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 48:
// line 1 "NONE"
	{	switch( act ) {
	case 4:
	{{p = ((te))-1;} System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }
	break;
	default:
	{{p = ((te))-1;}}
	break;
	}
	}
	break;
// line 386 "CoolLexer.java"
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
// line 400 "CoolLexer.java"
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

// line 259 "cool.rl"
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

    
// line 452 "CoolLexer.java"
private static byte[] init__cool_lexer_actions_0()
{
	return new byte [] {
	    0,    1,   35,    1,   36,    1,   37,    1,   38,    1,   41,    1,
	   42,    1,   43,    1,   44,    1,   45,    1,   46,    1,   47,    1,
	   48,    2,    0,   44,    2,    1,   44,    2,    2,   44,    2,    3,
	   44,    2,    4,   44,    2,    5,   44,    2,    6,   44,    2,    8,
	   44,    2,    9,   44,    2,   10,   44,    2,   11,   44,    2,   12,
	   44,    2,   13,   44,    2,   14,   44,    2,   15,   44,    2,   16,
	   44,    2,   17,   44,    2,   18,   44,    2,   19,   44,    2,   20,
	   44,    2,   21,   44,    2,   22,   44,    2,   23,   44,    2,   24,
	   44,    2,   25,   44,    2,   26,   44,    2,   27,   44,    2,   28,
	   44,    2,   29,   44,    2,   30,   44,    2,   31,   44,    2,   32,
	   44,    2,   33,   42,    2,   34,   35,    2,   38,   35,    2,   38,
	   40,    3,    7,   11,   44,    3,   34,   33,   42,    3,   38,   34,
	   35,    4,   38,   35,   33,   39
	};
}

private static final byte _cool_lexer_actions[] = init__cool_lexer_actions_0();


private static short[] init__cool_lexer_key_offsets_0()
{
	return new short [] {
	    0,    3,    7,   11,   14,   21,   29,   66,   69,   72,   72,   72,
	   72,   72,   72,   72,   72,   72,   74,   74,   74,   76,   76,   76,
	   76,   76,   83,   86,   95,  103,  111,  118,  126,  134,  142,  151,
	  159,  167,  174,  182,  190,  197,  206,  214,  222,  230,  237,  244,
	  254,  261,  269,  277,  285,  293,  301,  309,  317,  325,  333,  341,
	  349,  357,  365,  373,  381,  388,  397,  405,  413,  421,  428,  437,
	  445,  453,  460,  468,  475,  483,  491,  499,  506,  515,  523,  531,
	  538,  546,  554,  561,  569,  577,  585,  593,  600,  600,  600
	};
}

private static final short _cool_lexer_key_offsets[] = init__cool_lexer_key_offsets_0();


private static char[] init__cool_lexer_trans_keys_0()
{
	return new char [] {
	   10,   34,   92,    9,   32,   34,   92,    9,   32,   34,   92,    9,
	   10,   32,   95,   48,   57,   65,   90,   97,  122,   95,  104,   48,
	   57,   65,   90,   97,  122,   32,   34,   40,   41,   42,   43,   44,
	   45,   46,   47,   58,   59,   60,   61,   64,   92,   99,  101,  102,
	  105,  108,  110,  111,  112,  116,  119,  123,  125,  126,    9,   13,
	   48,   57,   65,   90,   97,  122,   10,   34,   92,   10,   34,   92,
	   48,   57,   45,   61,   95,   48,   57,   65,   90,   97,  122,    9,
	   10,   32,   95,   97,  108,   48,   57,   65,   90,   98,  122,   95,
	  115,   48,   57,   65,   90,   97,  122,   95,  101,   48,   57,   65,
	   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,   97,
	   48,   57,   65,   90,   98,  122,   95,  115,   48,   57,   65,   90,
	   97,  122,   95,  115,   48,   57,   65,   90,   97,  122,   95,  108,
	  115,   48,   57,   65,   90,   97,  122,   95,  115,   48,   57,   65,
	   90,   97,  122,   95,  101,   48,   57,   65,   90,   97,  122,   95,
	   48,   57,   65,   90,   97,  122,   95,   97,   48,   57,   65,   90,
	   98,  122,   95,   99,   48,   57,   65,   90,   97,  122,   95,   48,
	   57,   65,   90,   97,  122,   95,   97,  105,   48,   57,   65,   90,
	   98,  122,   95,  108,   48,   57,   65,   90,   97,  122,   95,  115,
	   48,   57,   65,   90,   97,  122,   95,  101,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,   48,   57,
	   65,   90,   97,  122,   95,  102,  110,  115,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  101,   48,
	   57,   65,   90,   97,  122,   95,  114,   48,   57,   65,   90,   97,
	  122,   95,  105,   48,   57,   65,   90,   97,  122,   95,  116,   48,
	   57,   65,   90,   97,  122,   95,  115,   48,   57,   65,   90,   97,
	  122,   95,  105,   48,   57,   65,   90,   97,  122,   95,  115,   48,
	   57,   65,   90,   97,  122,   95,  118,   48,   57,   65,   90,   97,
	  122,   95,  111,   48,   57,   65,   90,   97,  122,   95,  105,   48,
	   57,   65,   90,   97,  122,   95,  100,   48,   57,   65,   90,   97,
	  122,   95,  118,   48,   57,   65,   90,   97,  122,   95,  111,   48,
	   57,   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,   97,
	  122,   95,  100,   48,   57,   65,   90,   97,  122,   95,   48,   57,
	   65,   90,   97,  122,   95,  101,  111,   48,   57,   65,   90,   97,
	  122,   95,  116,   48,   57,   65,   90,   97,  122,   95,  111,   48,
	   57,   65,   90,   97,  122,   95,  112,   48,   57,   65,   90,   97,
	  122,   95,   48,   57,   65,   90,   97,  122,   95,  101,  111,   48,
	   57,   65,   90,   97,  122,   95,  119,   48,   57,   65,   90,   97,
	  122,   95,  116,   48,   57,   65,   90,   97,  122,   95,   48,   57,
	   65,   90,   97,  122,   95,  102,   48,   57,   65,   90,   97,  122,
	   95,   48,   57,   65,   90,   97,  122,   95,  111,   48,   57,   65,
	   90,   97,  122,   95,  111,   48,   57,   65,   90,   97,  122,   95,
	  108,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,
	   97,  122,   95,  104,  114,   48,   57,   65,   90,   97,  122,   95,
	  101,   48,   57,   65,   90,   97,  122,   95,  110,   48,   57,   65,
	   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  117,
	   48,   57,   65,   90,   97,  122,   95,  101,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  104,   48,
	   57,   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,   97,
	  122,   95,  108,   48,   57,   65,   90,   97,  122,   95,  101,   48,
	   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,
	    0
	};
}

private static final char _cool_lexer_trans_keys[] = init__cool_lexer_trans_keys_0();


private static byte[] init__cool_lexer_single_lengths_0()
{
	return new byte [] {
	    3,    4,    4,    3,    1,    2,   29,    3,    3,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    2,    0,    0,    0,
	    0,    1,    3,    3,    2,    2,    1,    2,    2,    2,    3,    2,
	    2,    1,    2,    2,    1,    3,    2,    2,    2,    1,    1,    4,
	    1,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    1,    3,    2,    2,    2,    1,    3,    2,
	    2,    1,    2,    1,    2,    2,    2,    1,    3,    2,    2,    1,
	    2,    2,    1,    2,    2,    2,    2,    1,    0,    0,    0
	};
}

private static final byte _cool_lexer_single_lengths[] = init__cool_lexer_single_lengths_0();


private static byte[] init__cool_lexer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    3,    3,    4,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    3,    0,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    0,    0,    0
	};
}

private static final byte _cool_lexer_range_lengths[] = init__cool_lexer_range_lengths_0();


private static short[] init__cool_lexer_index_offsets_0()
{
	return new short [] {
	    0,    4,    9,   14,   18,   23,   29,   63,   67,   71,   72,   73,
	   74,   75,   76,   77,   78,   79,   81,   82,   83,   86,   87,   88,
	   89,   90,   95,   99,  106,  112,  118,  123,  129,  135,  141,  148,
	  154,  160,  165,  171,  177,  182,  189,  195,  201,  207,  212,  217,
	  225,  230,  236,  242,  248,  254,  260,  266,  272,  278,  284,  290,
	  296,  302,  308,  314,  320,  325,  332,  338,  344,  350,  355,  362,
	  368,  374,  379,  385,  390,  396,  402,  408,  413,  420,  426,  432,
	  437,  443,  449,  454,  460,  466,  472,  478,  483,  484,  485
	};
}

private static final short _cool_lexer_index_offsets[] = init__cool_lexer_index_offsets_0();


private static short[] init__cool_lexer_indicies_0()
{
	return new short [] {
	    0,    2,    3,    1,    4,    4,    5,    3,    1,    4,    4,    2,
	    3,    1,    7,    8,    7,    6,   10,   10,   10,   10,    9,   10,
	   11,   10,   10,   10,    9,   13,   14,   15,   16,   17,   18,   19,
	   20,   21,   22,   24,   25,   26,   27,   28,   30,   31,   32,   33,
	   34,   35,   36,   37,   38,   39,   40,   41,   42,   43,   13,   23,
	   29,   29,   12,   44,   46,   47,   45,   48,    2,    3,    1,   49,
	   50,   51,   52,   53,   54,   55,   56,   58,   57,   59,   60,   62,
	   63,   61,   64,   65,   66,   67,   10,   10,   10,   10,   68,    7,
	    8,    7,   44,   10,   69,   70,   10,   10,   10,   68,   10,   71,
	   10,   10,   10,   68,   10,   72,   10,   10,   10,   68,   10,   10,
	   10,   10,   73,   10,   74,   10,   10,   10,   68,   10,   75,   10,
	   10,   10,   68,   10,   76,   10,   10,   10,   68,   10,   77,   78,
	   10,   10,   10,   68,   10,   79,   10,   10,   10,   68,   10,   80,
	   10,   10,   10,   68,   10,   10,   10,   10,   81,   10,   82,   10,
	   10,   10,   68,   10,   83,   10,   10,   10,   68,   10,   10,   10,
	   10,   84,   10,   85,   86,   10,   10,   10,   68,   10,   87,   10,
	   10,   10,   68,   10,   88,   10,   10,   10,   68,   10,   89,   10,
	   10,   10,   68,   10,   10,   10,   10,   90,   10,   10,   10,   10,
	   91,   10,   92,   93,   94,   10,   10,   10,   68,   10,   10,   10,
	   10,   95,   10,   96,   10,   10,   10,   68,   10,   97,   10,   10,
	   10,   68,   10,   98,   10,   10,   10,   68,   10,   99,   10,   10,
	   10,   68,   10,  100,   10,   10,   10,   68,   10,  101,   10,   10,
	   10,   68,   10,  102,   10,   10,   10,   68,   10,  103,   10,   10,
	   10,   68,   10,  104,   10,   10,   10,   68,   10,  105,   10,   10,
	   10,   68,   10,   76,   10,   10,   10,   68,   10,  106,   10,   10,
	   10,   68,   10,  107,   10,   10,   10,   68,   10,  108,   10,   10,
	   10,   68,   10,  109,   10,   10,   10,   68,   10,   10,   10,   10,
	  110,   10,  111,  112,   10,   10,   10,   68,   10,   76,   10,   10,
	   10,   68,   10,  113,   10,   10,   10,   68,   10,  114,   10,   10,
	   10,   68,   10,   10,   10,   10,  115,   10,  116,  117,   10,   10,
	   10,   68,   10,   76,   10,   10,   10,   68,   10,  118,   10,   10,
	   10,   68,   10,   10,   10,   10,  119,   10,  120,   10,   10,   10,
	   68,   10,   10,   10,   10,  121,   10,  122,   10,   10,   10,   68,
	   10,  123,   10,   10,   10,   68,   10,  124,   10,   10,   10,   68,
	   10,   10,   10,   10,  125,   10,  126,  127,   10,   10,   10,   68,
	   10,  128,   10,   10,   10,   68,   10,  129,   10,   10,   10,   68,
	   10,   10,   10,   10,  130,   10,  131,   10,   10,   10,   68,   10,
	  132,   10,   10,   10,   68,   10,   10,   10,   10,   48,   10,  133,
	   10,   10,   10,   68,   10,  134,   10,   10,   10,   68,   10,  135,
	   10,   10,   10,   68,   10,  136,   10,   10,   10,   68,   10,   10,
	   10,   10,  137,  138,  139,  140,    0
	};
}

private static final short _cool_lexer_indicies[] = init__cool_lexer_indicies_0();


private static byte[] init__cool_lexer_trans_targs_0()
{
	return new byte [] {
	    6,    0,    6,    1,    2,    8,    6,    3,    6,    6,   25,   49,
	    6,    6,    7,    9,   10,   11,   12,   13,   14,   15,   16,   17,
	   18,   19,   20,   23,   24,   25,   26,   27,   34,   41,   47,   65,
	   70,   74,   76,   80,   87,   92,   93,   94,    6,    0,    6,    1,
	    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,   17,    6,
	    6,    6,   21,   22,    6,    6,    6,    6,    6,   28,   31,   29,
	   30,    6,   32,   33,    4,   35,   38,   36,   37,    6,   39,   40,
	    6,   42,   46,   43,   44,   45,    6,    6,   48,    5,   60,    6,
	   50,   51,   52,   53,   54,   55,   56,   57,   58,   59,   61,   62,
	   63,   64,    6,   66,   67,   68,   69,    6,   71,   72,   73,    6,
	   75,    6,   77,   78,   79,    6,   81,   84,   82,   83,    6,   85,
	   86,   88,   89,   90,   91,    6,    6,    6,    6
	};
}

private static final byte _cool_lexer_trans_targs[] = init__cool_lexer_trans_targs_0();


private static short[] init__cool_lexer_trans_actions_0()
{
	return new short [] {
	   23,    1,  121,    1,    1,  145,   21,    0,    9,   19,    1,    1,
	   13,   11,  130,    0,    0,    0,    0,    0,    0,    0,    0,  124,
	    0,    0,    0,    0,    0,  124,    7,  124,  124,  124,  141,  124,
	  124,  124,  124,  124,  124,    0,    0,    0,   17,  124,  137,  124,
	   15,   73,   76,   67,   61,  109,   64,  106,   70,  115,    1,  100,
	  103,   85,    0,    0,   58,   88,   82,  112,   55,    1,    1,    1,
	    0,   37,    1,  127,    0,    1,    1,    1,    0,   31,    1,    0,
	   43,    1,    0,    1,    1,    0,  118,   34,    0,    0,    1,   25,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,  127,    1,    1,
	    1,    1,  133,  127,    1,    1,    0,   49,  127,    1,    0,   79,
	    0,   40,    1,    1,    0,   52,    1,    1,    1,    0,   28,    1,
	    0,    1,    1,    1,    0,   46,   94,   97,   91
	};
}

private static final short _cool_lexer_trans_actions[] = init__cool_lexer_trans_actions_0();


private static short[] init__cool_lexer_to_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final short _cool_lexer_to_state_actions[] = init__cool_lexer_to_state_actions_0();


private static short[] init__cool_lexer_from_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    5,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final short _cool_lexer_from_state_actions[] = init__cool_lexer_from_state_actions_0();


private static short[] init__cool_lexer_eof_trans_0()
{
	return new short [] {
	    1,    1,    1,    7,   10,   10,    0,   45,   49,   50,   51,   52,
	   53,   54,   55,   56,   57,   58,   60,   61,   62,   65,   66,   67,
	   68,   69,   45,   69,   69,   69,   74,   69,   69,   69,   69,   69,
	   69,   82,   69,   69,   85,   69,   69,   69,   69,   91,   92,   69,
	   96,   69,   69,   69,   69,   69,   69,   69,   69,   69,   69,   69,
	   69,   69,   69,   69,  111,   69,   69,   69,   69,  116,   69,   69,
	   69,  120,   69,  122,   69,   69,   69,  126,   69,   69,   69,  131,
	   69,   69,   49,   69,   69,   69,   69,  138,  139,  140,  141
	};
}

private static final short _cool_lexer_eof_trans[] = init__cool_lexer_eof_trans_0();


static final int cool_lexer_start = 6;
static final int cool_lexer_first_final = 6;
static final int cool_lexer_error = -1;

static final int cool_lexer_en_main = 6;


// line 287 "cool.rl"
}
