package visitor;

import syntaxtree.*;

import java.util.*;

import errorMsg.*;
import java.io.*;

//the purpose here is to emit mips code to represent string literals.
public class CG2Visitor extends Visitor
{
    // IO stream to which we will emit code
    CodeStream code;

    // Environment for string to the first node that we found with that string.
    HashMap<String,StringLit> stringEnv;

    public CG2Visitor(ErrorMsg e, PrintStream out)
    {
        stringEnv = new HashMap<String,StringLit>();
        code = new CodeStream(out, e);
    }

    private void emitUniqueString(StringLit n)
    {
        char[] chars = n.str.toCharArray();
        for(char ch : chars)
        {
            code.emit(n, "  .byte " + (int)ch);
        }

        int rem = n.str.length() % 4;
        while(rem > 0 && rem < 4)
        {
            code.emit(n, "  .byte 0");
            rem++;
        }

        code.emit(n, "  .word CLASS_String");
        code.emit(n, "  .word " + (1 + (n.str.length() + 3) / 4));
        code.emit(n, "  .word " + (-n.str.length()));
        code.emit(n, "strLit_" + n.uniqueId + ":");
    }

    public Object visit(Program p)
    {
        // This generates MIPS for string literals,
        // however it does not account for duplicate strings.
        // In order to get == to work correctly,
        // you must create your own version which does account for duplicate
        // strings.
        // StrLitSimpleGenerator.generate(p,  code);

        return super.visit(p);

        // return null;
    }

    @Override
    public Object visit(StringLit n)
    {
        StringLit rep = stringEnv.get(n.str);
        if(rep == null)
        {
            n.uniqueCgRep = n;
            stringEnv.put(n.str, n);
            emitUniqueString(n);
        }
        else
        {
            n.uniqueCgRep = rep;
        }
        return null;
    }
}
