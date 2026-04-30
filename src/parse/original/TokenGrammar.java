
package parse;
import java.util.List;

// enhancements in assignment:
// - none

import errorMsg.*;

public class TokenGrammar implements wrangLR.runtime.MessageObject
{

    public TokenGrammar(ErrorMsg em)
    {
        errorMsg = em;
    }
    private ErrorMsg errorMsg;

    public void error(int pos, CompError err)
    {
        errorMsg.error(pos, err);
    }

    public void warning(int pos, CompWarning warn)
    {
        errorMsg.warning(pos, warn);
    }


    public void reportTok(int pos, String s)
    {
        errorMsg.token(pos, s);
    }

    // These 2 methods are needed by WrangLR
    // DO NOT USE THEM! They will not pass tests
    public void warning(int pos, String warn)
    {
        errorMsg.info(pos, warn);
    }
    public void error(int pos, String err)
    {
        errorMsg.info(pos, err);
    }


    //////////////////////////////////////////////////////////////////////
    //////// Syntactic-level grammar, which simply recognizes any sequence
    //////// of tokens, and  prints each one
    //////////////////////////////////////////////////////////////////////

    // start-production
    //: start ::= white* token*

    // productions that recognize and print each type of token
    //: token ::= # `boolean =>
    public void sawBoolean(int pos)
    {
        reportTok(pos, "`boolean");
    }
    //: token ::= # `class =>
    public void sawClass(int pos)
    {
        reportTok(pos, "`class");
    }
    //: token ::= # `extends =>
    public void sawExtends(int pos)
    {
        reportTok(pos, "`extends");
    }
    //: token ::= # `void =>
    public void sawVoid(int pos)
    {
        reportTok(pos, "`void");
    }
    //: token ::= # `int =>
    public void sawInt(int pos)
    {
        reportTok(pos, "`int");
    }
    //: token ::= # `while =>
    public void sawWhile(int pos)
    {
        reportTok(pos, "`while");
    }
    //: token ::= # `if =>
    public void sawIf(int pos)
    {
        reportTok(pos, "`if");
    }
    //: token ::= # `else =>
    public void sawElse(int pos)
    {
        reportTok(pos, "`else");
    }
    //: token ::= # `for =>
    public void sawFor(int pos)
    {
        reportTok(pos, "`for");
    }
    //: token ::= # `break =>
    public void sawBreak(int pos)
    {
        reportTok(pos, "`break");
    }
    //: token ::= # `this =>
    public void sawThis(int pos)
    {
        reportTok(pos, "`this");
    }
    //: token ::= # `false =>
    public void sawFalse(int pos)
    {
        reportTok(pos, "`false");
    }
    //: token ::= # `true =>
    public void sawTrue(int pos)
    {
        reportTok(pos, "`true");
    }
    //: token ::= # `super =>
    public void sawSuper(int pos)
    {
        reportTok(pos, "`super");
    }
    //: token ::= # `null =>
    public void sawNull(int pos)
    {
        reportTok(pos, "`null");
    }
    //: token ::= # `return =>
    public void sawReturn(int pos)
    {
        reportTok(pos, "`return");
    }
    //: token ::= # `instanceof =>
    public void sawInstanceof(int pos)
    {
        reportTok(pos, "`instanceof");
    }
    //: token ::= # `new =>
    public void sawNew(int pos)
    {
        reportTok(pos, "`new");
    }
    //: token ::= # `abstract =>
    public void sawAbstract(int pos)
    {
        reportTok(pos, "`abstract");
    }
    //: token ::= # `assert =>
    public void sawAssert(int pos)
    {
        reportTok(pos, "`assert");
    }
    //: token ::= # `byte =>
    public void sawByte(int pos)
    {
        reportTok(pos, "`byte");
    }
    //: token ::= # `case =>
    public void sawCase(int pos)
    {
        reportTok(pos, "`case");
    }
    //: token ::= # `catch =>
    public void sawCatch(int pos)
    {
        reportTok(pos, "`catch");
    }
    //: token ::= # `char =>
    public void sawChar(int pos)
    {
        reportTok(pos, "`char");
    }
    //: token ::= # `const =>
    public void sawConst(int pos)
    {
        reportTok(pos, "`const");
    }
    //: token ::= # `continue =>
    public void sawContinue(int pos)
    {
        reportTok(pos, "`continue");
    }
    //: token ::= # `default =>
    public void sawDefault(int pos)
    {
        reportTok(pos, "`default");
    }
    //: token ::= # `do =>
    public void sawDo(int pos)
    {
        reportTok(pos, "`do");
    }
    //: token ::= # `double =>
    public void sawDouble(int pos)
    {
        reportTok(pos, "`double");
    }
    //: token ::= # `enum =>
    public void sawEnum(int pos)
    {
        reportTok(pos, "`enum");
    }
    //: token ::= # `final =>
    public void sawFinal(int pos)
    {
        reportTok(pos, "`final");
    }
    //: token ::= # `finally =>
    public void sawFinally(int pos)
    {
        reportTok(pos, "`finally");
    }
    //: token ::= # `float =>
    public void sawFloat(int pos)
    {
        reportTok(pos, "`float");
    }
    //: token ::= # `goto =>
    public void sawGoto(int pos)
    {
        reportTok(pos, "`goto");
    }
    //: token ::= # `implements =>
    public void sawImplements(int pos)
    {
        reportTok(pos, "`implements");
    }
    //: token ::= # `import =>
    public void sawImport(int pos)
    {
        reportTok(pos, "`import");
    }
    //: token ::= # `interface =>
    public void sawInterface(int pos)
    {
        reportTok(pos, "`interface");
    }
    //: token ::= # `long =>
    public void sawLong(int pos)
    {
        reportTok(pos, "`long");
    }
    //: token ::= # `native =>
    public void sawNative(int pos)
    {
        reportTok(pos, "`native");
    }
    //: token ::= # `package =>
    public void sawPackage(int pos)
    {
        reportTok(pos, "`package");
    }
    //: token ::= # `private =>
    public void sawPrivate(int pos)
    {
        reportTok(pos, "`private");
    }
    //: token ::= # `protected =>
    public void sawProtected(int pos)
    {
        reportTok(pos, "`protected");
    }
    //: token ::= # `public =>
    public void sawPublic(int pos)
    {
        reportTok(pos, "`public");
    }
    //: token ::= # `short =>
    public void sawShort(int pos)
    {
        reportTok(pos, "`short");
    }
    //: token ::= # `static =>
    public void sawStatic(int pos)
    {
        reportTok(pos, "`static");
    }
    //: token ::= # `strictfp =>
    public void sawStrictfp(int pos)
    {
        reportTok(pos, "`strictfp");
    }
    //: token ::= # `switch =>
    public void sawSwitch(int pos)
    {
        reportTok(pos, "`switch");
    }
    //: token ::= # `synchronized =>
    public void sawSynchronized(int pos)
    {
        reportTok(pos, "`synchronized");
    }
    //: token ::= # `throw =>
    public void sawThrow(int pos)
    {
        reportTok(pos, "`throw");
    }
    //: token ::= # `throws =>
    public void sawThrows(int pos)
    {
        reportTok(pos, "`throws");
    }
    //: token ::= # `transient =>
    public void sawTransient(int pos)
    {
        reportTok(pos, "`transient");
    }
    //: token ::= # `try =>
    public void sawTry(int pos)
    {
        reportTok(pos, "`try");
    }
    //: token ::= # `volatile =>
    public void sawVolatile(int pos)
    {
        reportTok(pos, "`volatile");
    }

    //: token ::= # `! =>
    public void sawNot(int pos)
    {
        reportTok(pos, "`!");
    }
    //: token ::= # `!= =>
    public void sawNotEqual(int pos)
    {
        reportTok(pos, "`!=");
    }
    //: token ::= # `% =>
    public void sawRemainder(int pos)
    {
        reportTok(pos, "`%");
    }
    //: token ::= # `&& =>
    public void sawAnd(int pos)
    {
        reportTok(pos, "`&&");
    }
    //: token ::= # `* =>
    public void sawTimes(int pos)
    {
        reportTok(pos, "`*");
    }
    //: token ::= # `( =>
    public void sawLpar(int pos)
    {
        reportTok(pos, "`(");
    }
    //: token ::= # `) =>
    public void sawRpar(int pos)
    {
        reportTok(pos, "`)");
    }
    //: token ::= # `{ =>
    public void sawLbrace(int pos)
    {
        reportTok(pos, "`{");
    }
    //: token ::= # `} =>
    public void sawRbrace(int pos)
    {
        reportTok(pos, "`}");
    }
    //: token ::= # `- =>
    public void sawMinus(int pos)
    {
        reportTok(pos, "`-");
    }
    //: token ::= # `+ =>
    public void sawPlus(int pos)
    {
        reportTok(pos, "`+");
    }
    //: token ::= # `= =>
    public void sawAssign(int pos)
    {
        reportTok(pos, "`=");
    }
    //: token ::= # `== =>
    public void sawEqual(int pos)
    {
        reportTok(pos, "`==");
    }
    //: token ::= # `[ =>
    public void sawLbrack(int pos)
    {
        reportTok(pos, "`[");
    }
    //: token ::= # `] =>
    public void sawRbrack(int pos)
    {
        reportTok(pos, "`]");
    }
    //: token ::= # `|| =>
    public void sawOr(int pos)
    {
        reportTok(pos, "`||");
    }
    //: token ::= # `< =>
    public void sawLess(int pos)
    {
        reportTok(pos, "`<");
    }
    //: token ::= # `<= =>
    public void sawLessEq(int pos)
    {
        reportTok(pos, "`<=");
    }
    //: token ::= # `, =>
    public void sawComma(int pos)
    {
        reportTok(pos, "`,");
    }
    //: token ::= # `> =>
    public void sawGreater(int pos)
    {
        reportTok(pos, "`>");
    }
    //: token ::= # `>= =>
    public void sawGreaterEq(int pos)
    {
        reportTok(pos, "`>=");
    }
    //: token ::= # `. =>
    public void sawDot(int pos)
    {
        reportTok(pos, "`.");
    }
    //: token ::= # `; =>
    public void sawSemi(int pos)
    {
        reportTok(pos, "`;");
    }
    //: token ::= # `++ =>
    public void sawPlusPlus(int pos)
    {
        reportTok(pos, "`++");
    }
    //: token ::= # `-- =>
    public void sawMinusMinus(int pos)
    {
        reportTok(pos, "`--");
    }
    //: token ::= # `/ =>
    public void sawSlash(int pos)
    {
        reportTok(pos, "`/");
    }
    //: token ::= # `: =>
    public void sawColon(int pos)
    {
        reportTok(pos, "`:");
    }

    //: token ::= # ID =>
    public void identifier(int pos, String s)
    {
        reportTok(pos, "identifier: "+s);
    }

    //: token ::= # INT_LITERAL =>
    public void intLit(int pos, int n)
    {
        reportTok(pos, "integer literal: "+n);
    }

    //: token ::= # STRING_LITERAL =>
    public void stringLit(int pos, String s)
    {
        reportTok(pos, "string literal: "+s);
    }

    //: token ::= # CHAR_LITERAL =>
    public void charLit(int pos, int n)
    {
        reportTok(pos, "character literal with ASCII value: "+n);
    }

    //////////////////////////////////////////
    /// Your modifications should start here
    //////////////////////////////////////////

    //================================================================
    // the actual tokens
    //================================================================

    // reserved words
    //: `class ::= "class" !idChar white*
    //: reserved ::= `class
    //: `else ::= "else" !idChar white*
    //: reserved ::= `else

    //special-token characters
    //: `!= ::= "!=" white*
    //: `+ ::= "+" !"+" white*

    // a numeric literal
    //: INT_LITERAL ::= # digit++ white* =>
    public int convertToInt(int pos, List<Character> s)
    {
        try
        {
            return Integer.parseInt(s.toString());
        }
        catch (NumberFormatException nfx)
        {
            error(pos, CompError.OutOfRange(s.toString()));
            return 0;
        }
    }
    // handle octal numbers
    // INT_LITERAL ::= # "0" oct_digit++ =>
    public int convertToOct(int pos, Character c, List<Character> s)
    {
        try
        {
            return Integer.parseInt(s.toString());
        }
        catch (NumberFormatException nfx)
        {
            error(pos, CompError.OutOfRange(s.toString()));
            return 0;
        }
    }
    // handle hexadecimal numbers
    // INT_LITERAL ::= # hex_start hex_digit++ =>
    public int convertToOct(int pos, List<Character> s)
    {
        try
        {
            return Integer.parseInt(s.toString());
        }
        catch (NumberFormatException nfx)
        {
            error(pos, CompError.OutOfRange(s.toString()));
            return 0;
        }
    }

    //================================================================
    // character patterns -- "helper symbols"
    //================================================================

    // a character that can be a non-first character in an identifier
    //: idChar ::= letter => pass
    //: idChar ::= digit => pass
    //: idChar ::= "_" => pass

    //: letter ::= {"a".."z" "A".."Z"} => pass

    // hex_start ::= "0" {"x" "X"}
    // hex_digit ::= !letter {"0".."9" "a".."f" "A".."F"} => pass
    // oct_digit ::= !hex_digit {"0".."7"} => pass
    //: digit ::= {"0".."9"} => pass


    //================================================================
    // whitespace
    //================================================================

    // whitespace
    //: white ::= {" " 9 12} // space or tab or form feed
    //: white ::= eol
    //: white ::= illegal => void


    // single-line comment: // followed by any characters until end of line or end of file
    //: white ::= "//" sl_char** eol => void
    //: white ::= # "//" sl_char** eof =>
    public void unterminatedSingleLineComment(int pos, Character c1, Character c2)
    {
        errorMsg.error(pos, CompError.UnterminatedSingleLineComment());
    }


    // multi-line comment:
    //: white ::= "/*" ml_char** "*/" => void
    //: white ::= # "/*" ml_char** eof =>
    public void unterminatedMultiLineComment(int pos, Character c1, Character c2)
    {
        errorMsg.error(pos, CompError.UnterminatedMultiLineComment());
    }


    // to handle the common end-of-line sequences on different types
    // of systems, we treat the sequence CR+LF as an end of line.
    // Otherwise, we treat CR or LF appearing separately each as an
    // end of line.
    //: eol ::= {10} registerNewline
    //: eol ::= {13} {10} registerNewline
    //: eol ::= {13} !{10} registerNewline

    // empty symbol which registers a new line at the position reduced
    //: registerNewline ::= # =>
    public void registerNewline(int pos)
    {
        errorMsg.newline(pos-1);
    }

    // Potentially useful definitions
    // printable is any character than shows up when you type
    //: printable ::= !eol !{42} {32..126} => pass

    // sl_char: any character except newline (for single-line comments)
    //: sl_char ::= {32..126}

    // ml_char: any character except */ (for multi-line comments)
    //: ml_char ::= {32..41 43..46 48..126}
    //: ml_char ::= "/" !"*"
    //: ml_char ::= eol
    //: ml_char ::= "*" !"/"
    //: ml_char ::= {0..9 11 12 14..31 127..255}
    // Detect /* inside multi-line comment (nested comment) - / is ASCII 47, * is ASCII 42
    //: ml_char ::= # "/" "*" =>
    public void commentInComment(int pos, Character c1, Character c2)
    {
        errorMsg.warning(pos, CompWarning.CommentInComment());
    }

    // Illegal character - treat as whitespace but print an error
    //: illegal ::= # {0..8 11 14..31 127..255}

    // The end of file is when we don't match any character.
    // eof is the end of file.  It does not match any character.
    //: eof ::= !{0..255} => void

    //////////// DUMMY TOKEN AND WHITESPACE DEFINITIONS ////////////
    // Once you create a real definition for one of the below, it will
    // reduce the number of states of you remove (or comment out) the
    // dummy definition.
    ////////////////////////////////////////////////////////////////
    
    //: `! ::= "!" !"=" white*
    //: `% ::= "%" white*
    //: `&& ::= "&&" white*
    //: `* ::= "*" white*
    //: `( ::= "(" white*
    //: `) ::= ")" white*
    //: `{ ::= "{" white*
    //: `} ::= "}" white*
    //: `- ::= "-" !"-" white*
    //: `= ::= "=" !"=" white*
    //: `== ::= "==" white*
    //: `[ ::= "[" white* 
    //: `] ::= "]" white*
    //: `|| ::= "||" white*
    //: `< ::= "<" !"=" white*
    //: `<= ::= "<=" white*
    //: `, ::= "," white*
    //: `> ::= ">" !"=" white*
    //: `>= ::= ">=" white*
    //: `: ::= ":" white*
    //: `. ::= "." white*
    //: `; ::= ";" white*
    //: `++ ::= "++" white*
    //: `-- ::= "--" white*
    //: `/ ::= !white "/" !"/" !"*" white*
    //: `boolean ::= "boolean" !idChar white*
    //: reserved ::= `boolean
    //: `extends ::= "extends" !idChar white*
    //: reserved ::= `extends
    //: `void ::= "void" !idChar white*
    //: reserved ::= `void
    //: `int ::= "int" !idChar white*
    //: reserved ::= `int
    //: `while ::= "while" !idChar white*
    //: reserved ::= `while
    //: `if ::= "if" !idChar white*
    //: reserved ::= `if
    //: `for ::= "for" !idChar white*
    //: reserved ::= `for
    //: `break ::= "break" !idChar white*
    //: reserved ::= `break
    //: `this ::= "this" !idChar white*
    //: reserved ::= `this
    //: `false ::= "false" !idChar white*
    //: reserved ::= `false
    //: `true ::= "true" !idChar white*
    //: reserved ::= `true
    //: `super ::= "super" !idChar white*
    //: reserved ::= `super
    //: `null ::= "null" !idChar white*
    //: reserved ::= `null
    //: `return ::= "return" !idChar white*
    //: reserved ::= `return
    //: `instanceof ::= "instanceof" !idChar white*
    //: reserved ::= `instanceof
    //: `new ::= "new" !idChar white*
    //: reserved ::= `new
    //: `abstract ::= "abstract" !idChar white*
    //: reserved ::= `abstract
    //: `assert ::= "assert" !idChar white*
    //: reserved ::= `assert
    //: `byte ::= "byte" !idChar white*
    //: reserved ::= `byte
    //: `case ::= "case" !idChar white*
    //: reserved ::= `case
    //: `catch ::= "catch" !idChar white*
    //: reserved ::= `catch
    //: `char ::= "char" !idChar white*
    //: reserved ::= `char
    //: `const ::= "const" !idChar white*
    //: reserved ::= `const
    //: `continue ::= "continue" !idChar white*
    //: reserved ::= `continue
    //: `default ::= "default" !idChar white*
    //: reserved ::= `default
    //: `do ::= "do" !idChar white*
    //: reserved ::= `do
    //: `double ::= "double" !idChar white*
    //: reserved ::= `double
    //: `enum ::= "enum" !idChar white*
    //: reserved ::= `enum
    //: `final ::= "final" !idChar white*
    //: reserved ::= `final
    //: `finally ::= "finally" !idChar white*
    //: reserved ::= `finally
    //: `float ::= "float" !idChar white*
    //: reserved ::= `float
    //: `goto ::= "goto" !idChar white*
    //: reserved ::= `goto
    //: `implements ::= "implements" !idChar white*
    //: reserved ::= `implements
    //: `import ::= "import" !idChar white*
    //: reserved ::= `import
    //: `interface ::= "interface" !idChar white*
    //: reserved ::= `interface
    //: `long ::= "long" !idChar white*
    //: reserved ::= `long
    //: `native ::= "native" !idChar white*
    //: reserved ::= `native
    //: `package ::= "package" !idChar white*
    //: reserved ::= `package
    //: `private ::= "private" !idChar white*
    //: reserved ::= `private
    //: `protected ::= "protected" !idChar white*
    //: reserved ::= `protected
    //: `public ::= "public" !idChar white*
    //: reserved ::= `public
    //: `short ::= "short" !idChar white*
    //: reserved ::= `short
    //: `static ::= "static" !idChar white*
    //: reserved ::= `static
    //: `strictfp ::= "strictfp" !idChar white*
    //: reserved ::= `strictfp
    //: `switch ::= "switch" !idChar white*
    //: reserved ::= `switch
    //: `synchronized ::= "synchronized" !idChar white*
    //: reserved ::= `synchronized
    //: `throw ::= "throw" !idChar white*
    //: reserved ::= `throw
    //: `throws ::= "throws" !idChar white*
    //: reserved ::= `throws
    //: `transient ::= "transient" !idChar white*
    //: reserved ::= `transient
    //: `try ::= "try" !idChar white*
    //: reserved ::= `try
    //: `volatile ::= "volatile" !idChar white*
    //: reserved ::= `volatile
    //: ID ::= !reserved !digit idChar++ white* => text

    //================================================================
    // String and character literals
    //================================================================

    // STRING_LITERAL: double-quoted string
    //: st_char ::= {32..33 35..91 93..126} => pass
    //: st_char ::= # {127..255} =>
    public Character illegalInString(int pos, Character c)
    {
        errorMsg.error(pos, CompError.IllegalChar(c));
        return c;
    }
    // Escape sequences in strings
    //: st_char ::= "\" {34 92 39 110 116 102 114} =>
    public Character stringEscape(Character c1, Character c2)
    {
        switch (c2) {
            case 34:  // "\" - double quote
                return (char)34;
            case 92:  // "\\" - backslash
                return (char)92;
            case 39:  // "\'" - single quote
                return (char)39;
            case 110: // "\n" - newline (ASCII 10)
                return (char)10;
            case 116: // "\t" - tab (ASCII 9)
                return (char)9;
            case 102: // "\f" - form feed (ASCII 12)
                return (char)12;
            case 114: // "\r" - carriage return (ASCII 13)
                return (char)13;
            default:
                return c2;
        }
    }
    //: STRING_LITERAL ::= {34} st_char** {34} white* => text


    
    // Unterminated string literal (reached end of line or EOF)
    //: STRING_LITERAL ::= # {34} st_char** eol =>
    public String unterminatedString(int pos, Character c1, List<Character> s)
    {
        errorMsg.error(pos, CompError.UnterminatedString());
        return "";
    }
    //: STRING_LITERAL ::= # {34} st_char** eof =>
    public String unterminatedStringEOF(int pos, Character c1, List<Character> s)
    {
        errorMsg.error(pos, CompError.UnterminatedString());
        return "";
    }

    // CHAR_LITERAL: single-quoted character
    //: ch_char ::= {32..38 40..91 93..126} => pass
    //: ch_char ::= # {127..255} =>
    public Character illegalInChar(int pos, Character c)
    {
        errorMsg.error(pos, CompError.IllegalChar(c));
        return c;
    }
    // Escape sequences in character literals
    //: ch_char ::= "\" {34 92 39 110 116 102 114} =>
    public Character charEscape(Character c1, Character c2)
    {
        switch (c2) {
            case 34:  // "\"" - double quote
                return (char)34;
            case 92:  // "\\" - backslash
                return (char)92;
            case 39:  // "\'" - single quote
                return (char)39;
            case 110: // "\n" - newline (ASCII 10)
                return (char)10;
            case 116: // "\t" - tab (ASCII 9)
                return (char)9;
            case 102: // "\f" - form feed (ASCII 12)
                return (char)12;
            case 114: // "\r" - carriage return (ASCII 13)
                return (char)13;
            default:
                return c2;
        }
    }
    //: CHAR_LITERAL ::= {39} ch_char {39} white* =>
    public int charLitValue(Character c1, Character c2, Character c3) {
        return c2;
    }

    // Empty char literal ''
    //: CHAR_LITERAL ::= # {39} {39} white* =>
    public int emptyChar(int pos, Character c1, Character c2)  
    {
        errorMsg.error(pos, CompError.EmptyChar());
        return 0;
    }

    // Multi-char char literal
    //: CHAR_LITERAL ::= # {39} ch_char ch_char++ {39} white* =>
    public int multiChar(int pos, Character c1, Character c2, List<Character> rest, Character c3)
    {
        errorMsg.error(pos, CompError.BigChar(c2, rest));
        return c2;
    }

    // Unterminated char literal
    //: CHAR_LITERAL ::= # {39} ch_char eol =>
    public int unterminatedCharEOL(int pos, Character c1, Character c2)
    {
        errorMsg.error(pos, CompError.UnterminatedChar());
        return 0;
    }
    //: CHAR_LITERAL ::= # {39} ch_char eof =>
    public int unterminatedCharEOF(int pos, Character c1, Character c2)
    {
        errorMsg.error(pos, CompError.UnterminatedChar());
        return 0;
    }
    //: CHAR_LITERAL ::= # {39} eol =>
    public int unterminatedEmptyCharEOL(int pos, Character c1)
    {
        errorMsg.error(pos, CompError.UnterminatedChar());
        return 0;
    }
    //: CHAR_LITERAL ::= # {39} eof =>
    public int unterminatedEmptyCharEOF(int pos, Character c1)
    {
        errorMsg.error(pos, CompError.UnterminatedChar());
        return 0;
    }

}
