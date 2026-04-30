// method call: super, indirect, replacing current-class defn

class Main extends Lib {
    public void main(){
	boolean b1 =
	    pIdent(100)+
	    pIdent(9)*
	    pIdent(7)-
	    pIdent(53)/
	    pIdent(5)<
	    pIdent(24)+
	    pIdent(35)%
	    pIdent(6)+
	    pIdent(2)-
	    pIdent(147)/
	    pIdent(7)*
	    pIdent(8);
	boolean b2 = pIdent(24) > pIdent(5) + pIdent(-3);
	boolean b3 = pIdent(25) >= pIdent(6) + pIdent(22);
	boolean b4 = pIdent(26) <= pIdent(7) + pIdent(274);
	boolean b5 = pIdent(27) == pIdent(8) + pIdent(0);
	boolean b6 = pIdent(28) != pIdent(9) + pIdent(37);
	boolean b7 = pIdent(29) > pIdent(10) + pIdent(2);
	super.printBool(b1);
	super.printBool(b2);
	super.printBool(b3);
	super.printBool(b4);
	super.printBool(b5);
	super.printBool(b6);
	super.printBool(b7);
    }
    public int pIdent(int x) {
	super.printInt(x);
	super.printStr("\n");
	return x;
    }
}
