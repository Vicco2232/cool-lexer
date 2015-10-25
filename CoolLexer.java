
// line 1 "cool.rl"
import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Arrays;


// line 222 "cool.rl"


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

// line 278 "cool.rl"
        
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
	case 45:
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
                      // String token = get_token(data, ms, me);
                      String token = sb.toString();
                      AbstractSymbol sym = AbstractTable.stringtable.addString(token);
                      push_token(new Symbol(TokenConstants.STR_CONST, sym));
                    }
	break;
	case 34:
// line 92 "cool.rl"
	{ ms = p; }
	break;
	case 35:
// line 93 "cool.rl"
	{ me = p + 1; }
	break;
	case 36:
// line 95 "cool.rl"
	{ sb = new StringBuffer(); }
	break;
	case 37:
// line 96 "cool.rl"
	{ sb.append(get_token(data, ms, me)); }
	break;
	case 38:
// line 97 "cool.rl"
	{ sb.append('\b'); }
	break;
	case 39:
// line 98 "cool.rl"
	{ sb.append('\t'); }
	break;
	case 40:
// line 99 "cool.rl"
	{ sb.append('\n'); }
	break;
	case 41:
// line 100 "cool.rl"
	{ sb.append('\f'); }
	break;
	case 42:
// line 101 "cool.rl"
	{ sb.append('"'); }
	break;
	case 43:
// line 102 "cool.rl"
	{ sb.append(data[p]); }
	break;
	case 46:
// line 1 "NONE"
	{te = p+1;}
	break;
	case 47:
// line 213 "cool.rl"
	{te = p+1;}
	break;
	case 48:
// line 218 "cool.rl"
	{te = p+1;}
	break;
	case 49:
// line 220 "cool.rl"
	{te = p+1;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 50:
// line 213 "cool.rl"
	{te = p;p--;}
	break;
	case 51:
// line 217 "cool.rl"
	{te = p;p--;}
	break;
	case 52:
// line 218 "cool.rl"
	{te = p;p--;}
	break;
	case 53:
// line 220 "cool.rl"
	{te = p;p--;{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
	case 54:
// line 218 "cool.rl"
	{{p = ((te))-1;}}
	break;
	case 55:
// line 220 "cool.rl"
	{{p = ((te))-1;}{ System.err.println("LEXER BUG - UNMATCHED: " + get_token(data, ts, te)); }}
	break;
// line 406 "CoolLexer.java"
			}
		}
	}

case 2:
	_acts = _cool_lexer_to_state_actions[cs];
	_nacts = (int) _cool_lexer_actions[_acts++];
	while ( _nacts-- > 0 ) {
		switch ( _cool_lexer_actions[_acts++] ) {
	case 44:
// line 1 "NONE"
	{ts = -1;}
	break;
// line 420 "CoolLexer.java"
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

// line 279 "cool.rl"
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

    
// line 472 "CoolLexer.java"
private static byte[] init__cool_lexer_actions_0()
{
	return new byte [] {
	    0,    1,   14,    1,   17,    1,   35,    1,   37,    1,   44,    1,
	   45,    1,   46,    1,   47,    1,   48,    1,   49,    1,   50,    1,
	   52,    1,   53,    1,   54,    1,   55,    2,    0,   52,    2,    1,
	   52,    2,    2,   52,    2,    3,   52,    2,    4,   52,    2,    5,
	   52,    2,    6,   52,    2,    8,   52,    2,    9,   52,    2,   10,
	   52,    2,   11,   52,    2,   12,   52,    2,   13,   52,    2,   14,
	   52,    2,   15,   52,    2,   16,   52,    2,   17,   52,    2,   18,
	   52,    2,   19,   52,    2,   20,   52,    2,   21,   52,    2,   22,
	   52,    2,   23,   52,    2,   24,   52,    2,   25,   52,    2,   26,
	   52,    2,   27,   52,    2,   28,   52,    2,   29,   52,    2,   30,
	   52,    2,   31,   52,    2,   32,   52,    2,   34,   35,    2,   34,
	   37,    2,   40,   51,    2,   46,   35,    2,   46,   36,    3,    7,
	   11,   52,    3,   37,   33,   48,    3,   38,   34,   35,    3,   38,
	   34,   37,    3,   39,   34,   35,    3,   39,   34,   37,    3,   40,
	   34,   35,    3,   40,   34,   37,    3,   41,   34,   35,    3,   41,
	   34,   37,    3,   42,   34,   35,    3,   42,   34,   37,    3,   43,
	   34,   35,    3,   43,   34,   37,    3,   46,   34,   35,    4,   34,
	   37,   33,   48,    4,   43,   40,   34,   35,    4,   43,   40,   34,
	   37,    5,   38,   34,   37,   33,   48,    5,   39,   34,   37,   33,
	   48,    5,   40,   34,   37,   33,   48,    5,   41,   34,   37,   33,
	   48,    5,   42,   34,   37,   33,   48,    5,   43,   34,   37,   33,
	   48,    6,   43,   40,   34,   37,   33,   48
	};
}

private static final byte _cool_lexer_actions[] = init__cool_lexer_actions_0();


private static short[] init__cool_lexer_key_offsets_0()
{
	return new short [] {
	    0,    2,   10,   12,   17,   22,   24,   26,   28,   30,   32,   34,
	   35,   36,   38,   40,   41,   44,   51,   59,   96,   98,   99,   99,
	   99,   99,   99,  100,  101,  101,  101,  103,  103,  103,  105,  105,
	  105,  105,  105,  112,  115,  115,  124,  132,  140,  147,  155,  163,
	  171,  180,  188,  196,  203,  211,  219,  226,  235,  243,  251,  259,
	  266,  273,  283,  290,  298,  306,  314,  322,  330,  338,  346,  354,
	  362,  370,  378,  386,  394,  402,  410,  417,  426,  434,  442,  450,
	  457,  466,  474,  482,  489,  497,  504,  512,  520,  528,  535,  544,
	  552,  560,  567,  575,  583,  590,  598,  606,  614,  622,  629,  629,
	  629
	};
}

private static final short _cool_lexer_key_offsets[] = init__cool_lexer_key_offsets_0();


private static char[] init__cool_lexer_trans_keys_0()
{
	return new char [] {
	   34,   92,    9,   10,   32,   34,   98,  102,  110,  116,   34,   92,
	    9,   10,   32,   34,   92,    9,   10,   32,   34,   92,   34,   92,
	   34,   92,   34,   92,   34,   92,   34,   92,   34,   92,   42,   42,
	   41,   42,   41,   42,   10,    9,   10,   32,   95,   48,   57,   65,
	   90,   97,  122,   95,  104,   48,   57,   65,   90,   97,  122,   32,
	   34,   40,   41,   42,   43,   44,   45,   46,   47,   58,   59,   60,
	   61,   64,   92,   99,  101,  102,  105,  108,  110,  111,  112,  116,
	  119,  123,  125,  126,    9,   13,   48,   57,   65,   90,   97,  122,
	   34,   92,   42,   45,   10,   48,   57,   45,   61,   95,   48,   57,
	   65,   90,   97,  122,    9,   10,   32,   95,   97,  108,   48,   57,
	   65,   90,   98,  122,   95,  115,   48,   57,   65,   90,   97,  122,
	   95,  101,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,
	   90,   97,  122,   95,   97,   48,   57,   65,   90,   98,  122,   95,
	  115,   48,   57,   65,   90,   97,  122,   95,  115,   48,   57,   65,
	   90,   97,  122,   95,  108,  115,   48,   57,   65,   90,   97,  122,
	   95,  115,   48,   57,   65,   90,   97,  122,   95,  101,   48,   57,
	   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,
	   97,   48,   57,   65,   90,   98,  122,   95,   99,   48,   57,   65,
	   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,   97,
	  105,   48,   57,   65,   90,   98,  122,   95,  108,   48,   57,   65,
	   90,   97,  122,   95,  115,   48,   57,   65,   90,   97,  122,   95,
	  101,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  102,  110,
	  115,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,
	   97,  122,   95,  101,   48,   57,   65,   90,   97,  122,   95,  114,
	   48,   57,   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,
	   97,  122,   95,  116,   48,   57,   65,   90,   97,  122,   95,  115,
	   48,   57,   65,   90,   97,  122,   95,  105,   48,   57,   65,   90,
	   97,  122,   95,  115,   48,   57,   65,   90,   97,  122,   95,  118,
	   48,   57,   65,   90,   97,  122,   95,  111,   48,   57,   65,   90,
	   97,  122,   95,  105,   48,   57,   65,   90,   97,  122,   95,  100,
	   48,   57,   65,   90,   97,  122,   95,  118,   48,   57,   65,   90,
	   97,  122,   95,  111,   48,   57,   65,   90,   97,  122,   95,  105,
	   48,   57,   65,   90,   97,  122,   95,  100,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  101,  111,
	   48,   57,   65,   90,   97,  122,   95,  116,   48,   57,   65,   90,
	   97,  122,   95,  111,   48,   57,   65,   90,   97,  122,   95,  112,
	   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,
	  122,   95,  101,  111,   48,   57,   65,   90,   97,  122,   95,  119,
	   48,   57,   65,   90,   97,  122,   95,  116,   48,   57,   65,   90,
	   97,  122,   95,   48,   57,   65,   90,   97,  122,   95,  102,   48,
	   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,  122,
	   95,  111,   48,   57,   65,   90,   97,  122,   95,  111,   48,   57,
	   65,   90,   97,  122,   95,  108,   48,   57,   65,   90,   97,  122,
	   95,   48,   57,   65,   90,   97,  122,   95,  104,  114,   48,   57,
	   65,   90,   97,  122,   95,  101,   48,   57,   65,   90,   97,  122,
	   95,  110,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,
	   90,   97,  122,   95,  117,   48,   57,   65,   90,   97,  122,   95,
	  101,   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,
	   97,  122,   95,  104,   48,   57,   65,   90,   97,  122,   95,  105,
	   48,   57,   65,   90,   97,  122,   95,  108,   48,   57,   65,   90,
	   97,  122,   95,  101,   48,   57,   65,   90,   97,  122,   95,   48,
	   57,   65,   90,   97,  122,    0
	};
}

private static final char _cool_lexer_trans_keys[] = init__cool_lexer_trans_keys_0();


private static byte[] init__cool_lexer_single_lengths_0()
{
	return new byte [] {
	    2,    8,    2,    5,    5,    2,    2,    2,    2,    2,    2,    1,
	    1,    2,    2,    1,    3,    1,    2,   29,    2,    1,    0,    0,
	    0,    0,    1,    1,    0,    0,    0,    0,    0,    2,    0,    0,
	    0,    0,    1,    3,    0,    3,    2,    2,    1,    2,    2,    2,
	    3,    2,    2,    1,    2,    2,    1,    3,    2,    2,    2,    1,
	    1,    4,    1,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    1,    3,    2,    2,    2,    1,
	    3,    2,    2,    1,    2,    1,    2,    2,    2,    1,    3,    2,
	    2,    1,    2,    2,    1,    2,    2,    2,    2,    1,    0,    0,
	    0
	};
}

private static final byte _cool_lexer_single_lengths[] = init__cool_lexer_single_lengths_0();


private static byte[] init__cool_lexer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    3,    3,    4,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    3,    0,    0,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,    0,    0,
	    0
	};
}

private static final byte _cool_lexer_range_lengths[] = init__cool_lexer_range_lengths_0();


private static short[] init__cool_lexer_index_offsets_0()
{
	return new short [] {
	    0,    3,   12,   15,   21,   27,   30,   33,   36,   39,   42,   45,
	   47,   49,   52,   55,   57,   61,   66,   72,  106,  109,  111,  112,
	  113,  114,  115,  117,  119,  120,  121,  123,  124,  125,  128,  129,
	  130,  131,  132,  137,  141,  142,  149,  155,  161,  166,  172,  178,
	  184,  191,  197,  203,  208,  214,  220,  225,  232,  238,  244,  250,
	  255,  260,  268,  273,  279,  285,  291,  297,  303,  309,  315,  321,
	  327,  333,  339,  345,  351,  357,  363,  368,  375,  381,  387,  393,
	  398,  405,  411,  417,  422,  428,  433,  439,  445,  451,  456,  463,
	  469,  475,  480,  486,  492,  497,  503,  509,  515,  521,  526,  527,
	  528
	};
}

private static final short _cool_lexer_index_offsets[] = init__cool_lexer_index_offsets_0();


private static byte[] init__cool_lexer_trans_targs_0()
{
	return new byte [] {
	   19,    1,    0,    3,    6,    3,    7,    8,    9,    5,   10,    2,
	   19,    1,    0,    4,    5,    4,   19,    1,    0,    4,    5,    4,
	   19,    1,    0,   19,    1,    0,   19,    1,    0,   19,    1,    0,
	   19,    1,    0,   19,    1,    0,   19,    1,    0,   14,   12,   13,
	   12,   19,   13,   12,   19,   13,   12,   19,   27,   16,   40,   16,
	   19,   38,   38,   38,   38,   19,   38,   63,   38,   38,   38,   19,
	   19,   20,   21,   22,   23,   24,   25,   26,   28,   29,   31,   32,
	   33,   36,   37,   39,   41,   48,   55,   61,   79,   84,   88,   90,
	   94,  101,  106,  107,  108,   19,   30,   38,   38,   19,   19,    1,
	    0,   11,   19,   19,   19,   19,   19,   15,   19,   19,   27,   19,
	   19,   30,   19,   19,   19,   34,   35,   19,   19,   19,   19,   19,
	   38,   38,   38,   38,   19,   16,   40,   16,   19,   19,   38,   42,
	   45,   38,   38,   38,   19,   38,   43,   38,   38,   38,   19,   38,
	   44,   38,   38,   38,   19,   38,   38,   38,   38,   19,   38,   46,
	   38,   38,   38,   19,   38,   47,   38,   38,   38,   19,   38,   17,
	   38,   38,   38,   19,   38,   49,   52,   38,   38,   38,   19,   38,
	   50,   38,   38,   38,   19,   38,   51,   38,   38,   38,   19,   38,
	   38,   38,   38,   19,   38,   53,   38,   38,   38,   19,   38,   54,
	   38,   38,   38,   19,   38,   38,   38,   38,   19,   38,   56,   60,
	   38,   38,   38,   19,   38,   57,   38,   38,   38,   19,   38,   58,
	   38,   38,   38,   19,   38,   59,   38,   38,   38,   19,   38,   38,
	   38,   38,   19,   38,   38,   38,   38,   19,   38,   62,   18,   74,
	   38,   38,   38,   19,   38,   38,   38,   38,   19,   38,   64,   38,
	   38,   38,   19,   38,   65,   38,   38,   38,   19,   38,   66,   38,
	   38,   38,   19,   38,   67,   38,   38,   38,   19,   38,   68,   38,
	   38,   38,   19,   38,   69,   38,   38,   38,   19,   38,   70,   38,
	   38,   38,   19,   38,   71,   38,   38,   38,   19,   38,   72,   38,
	   38,   38,   19,   38,   73,   38,   38,   38,   19,   38,   17,   38,
	   38,   38,   19,   38,   75,   38,   38,   38,   19,   38,   76,   38,
	   38,   38,   19,   38,   77,   38,   38,   38,   19,   38,   78,   38,
	   38,   38,   19,   38,   38,   38,   38,   19,   38,   80,   81,   38,
	   38,   38,   19,   38,   17,   38,   38,   38,   19,   38,   82,   38,
	   38,   38,   19,   38,   83,   38,   38,   38,   19,   38,   38,   38,
	   38,   19,   38,   85,   86,   38,   38,   38,   19,   38,   17,   38,
	   38,   38,   19,   38,   87,   38,   38,   38,   19,   38,   38,   38,
	   38,   19,   38,   89,   38,   38,   38,   19,   38,   38,   38,   38,
	   19,   38,   91,   38,   38,   38,   19,   38,   92,   38,   38,   38,
	   19,   38,   93,   38,   38,   38,   19,   38,   38,   38,   38,   19,
	   38,   95,   98,   38,   38,   38,   19,   38,   96,   38,   38,   38,
	   19,   38,   97,   38,   38,   38,   19,   38,   38,   38,   38,   19,
	   38,   99,   38,   38,   38,   19,   38,  100,   38,   38,   38,   19,
	   38,   38,   38,   38,   19,   38,  102,   38,   38,   38,   19,   38,
	  103,   38,   38,   38,   19,   38,  104,   38,   38,   38,   19,   38,
	  105,   38,   38,   38,   19,   38,   38,   38,   38,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
	   19,    0
	};
}

private static final byte _cool_lexer_trans_targs[] = init__cool_lexer_trans_targs_0();


private static short[] init__cool_lexer_trans_actions_0()
{
	return new short [] {
	  146,    7,    5,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	  247,  194,  190,  190,  190,  190,  247,  194,  190,    5,    5,    5,
	  146,    7,    5,  229,  170,  166,  253,  212,  207,  241,  186,  182,
	  217,  154,  150,  235,  178,  174,  223,  162,  158,    0,    0,    0,
	    0,   15,    0,    0,   27,    0,    0,   27,    0,    0,    0,    0,
	   29,    5,    5,    5,    5,   27,    5,    5,    5,    5,    5,   27,
	   17,  139,   13,    0,    0,    0,    0,   13,    0,    0,    0,    0,
	    0,    0,    0,   13,  127,  127,  127,  198,  127,  127,  127,  127,
	  127,  127,    0,    0,    0,   17,  127,  127,  127,   19,  202,  130,
	  127,    3,   79,   82,   73,   67,  115,    1,   70,   21,    0,  112,
	   76,    5,  121,  106,  109,    0,    0,   91,   64,   94,   88,  118,
	    5,    5,    5,    5,   61,    0,    0,    0,   25,  133,    5,    5,
	    5,    5,    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,
	    0,    5,    5,    5,   61,    5,    5,    5,    5,   43,    5,    5,
	    5,    5,    5,   61,    5,  136,    5,    5,    5,   61,    5,    0,
	    5,    5,    5,   61,    5,    5,    5,    5,    5,    5,   61,    5,
	    5,    5,    5,    5,   61,    5,    0,    5,    5,    5,   61,    5,
	    5,    5,    5,   37,    5,    5,    5,    5,    5,   61,    5,    0,
	    5,    5,    5,   61,    5,    5,    5,    5,   49,    5,    5,    0,
	    5,    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,
	    5,    5,    5,   61,    5,    0,    5,    5,    5,   61,    5,    5,
	    5,    5,  124,    5,    5,    5,    5,   40,    5,    0,    0,    5,
	    5,    5,    5,   61,    5,    5,    5,    5,   31,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,  136,    5,    5,    5,   61,    5,    0,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    5,    5,    5,  142,    5,  136,    5,    5,
	    5,    5,   61,    5,    0,    5,    5,    5,   61,    5,    5,    5,
	    5,    5,   61,    5,    0,    5,    5,    5,   61,    5,    5,    5,
	    5,   55,    5,  136,    5,    5,    5,    5,   61,    5,    0,    5,
	    5,    5,   61,    5,    0,    5,    5,    5,   61,    5,    5,    5,
	    5,   85,    5,    0,    5,    5,    5,   61,    5,    5,    5,    5,
	   46,    5,    5,    5,    5,    5,   61,    5,    5,    5,    5,    5,
	   61,    5,    0,    5,    5,    5,   61,    5,    5,    5,    5,   58,
	    5,    5,    5,    5,    5,    5,   61,    5,    5,    5,    5,    5,
	   61,    5,    0,    5,    5,    5,   61,    5,    5,    5,    5,   34,
	    5,    5,    5,    5,    5,   61,    5,    0,    5,    5,    5,   61,
	    5,    5,    5,    5,   23,    5,    5,    5,    5,    5,   61,    5,
	    5,    5,    5,    5,   61,    5,    5,    5,    5,    5,   61,    5,
	    0,    5,    5,    5,   61,    5,    5,    5,    5,   52,  100,  103,
	   97,   29,   29,   29,   29,   29,   29,   29,   29,   29,   29,   29,
	   27,   27,   27,   27,   27,   29,   27,   27,   25,   79,   82,   73,
	   67,  115,   70,   21,  112,   76,  121,  106,  109,   91,   64,   94,
	   88,  118,   61,   25,  133,   61,   61,   61,   43,   61,   61,   61,
	   61,   61,   61,   37,   61,   61,   49,   61,   61,   61,   61,  124,
	   40,   61,   31,   61,   61,   61,   61,   61,   61,   61,   61,   61,
	   61,   61,   61,   61,   61,   61,  142,   61,   61,   61,   61,   55,
	   61,   61,   61,   85,   61,   46,   61,   61,   61,   58,   61,   61,
	   61,   34,   61,   61,   23,   61,   61,   61,   61,   52,  100,  103,
	   97,    0
	};
}

private static final short _cool_lexer_trans_actions[] = init__cool_lexer_trans_actions_0();


private static short[] init__cool_lexer_to_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    9,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0
	};
}

private static final short _cool_lexer_to_state_actions[] = init__cool_lexer_to_state_actions_0();


private static short[] init__cool_lexer_from_state_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   11,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0
	};
}

private static final short _cool_lexer_from_state_actions[] = init__cool_lexer_from_state_actions_0();


private static short[] init__cool_lexer_eof_trans_0()
{
	return new short [] {
	  546,  546,  546,  546,  546,  546,  546,  546,  546,  546,  546,  548,
	  548,  548,  548,  548,  546,  548,  548,    0,  568,  550,  551,  552,
	  553,  554,  555,  556,  557,  558,  559,  560,  561,  562,  563,  564,
	  565,  566,  633,  568,  569,  633,  633,  633,  573,  633,  633,  633,
	  633,  633,  633,  580,  633,  633,  583,  633,  633,  633,  633,  588,
	  589,  633,  591,  633,  633,  633,  633,  633,  633,  633,  633,  633,
	  633,  633,  633,  633,  633,  633,  607,  633,  633,  633,  633,  612,
	  633,  633,  633,  616,  633,  618,  633,  633,  633,  622,  633,  633,
	  633,  626,  633,  633,  629,  633,  633,  633,  633,  634,  635,  636,
	  637
	};
}

private static final short _cool_lexer_eof_trans[] = init__cool_lexer_eof_trans_0();


static final int cool_lexer_start = 19;
static final int cool_lexer_first_final = 19;
static final int cool_lexer_error = -1;

static final int cool_lexer_en_main = 19;


// line 307 "cool.rl"
}
