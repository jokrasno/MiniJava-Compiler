// method call on explicit object: object, int/bool parameter

class Main extends Lib {
    public void main() {
	T16Helper h = new T16Helper();
	super.printStr(h.getStr1(27));
	super.printStr("\n");
	super.printStr(h.getStr2(false));
	super.printStr("\n");
    }
}

class T16Helper extends Lib {
    public String getStr1(int n) {
	String rtnVal = "Purple";
	if (n < 20) rtnVal = "Green";
	return rtnVal;
    }
    public String getStr2(boolean b) {
	String rtnVal = "Black";
	if (b) rtnVal = "Red";
	return rtnVal;
    }
}
