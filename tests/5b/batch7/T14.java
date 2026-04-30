// method call on explicit object: object, multiple parameters

class Main extends Lib {
    public void main() {
	T14Helper h = new T14Helper();
	super.printStr(h.getStr("Funny", 3));
	super.printStr("\n");
    }
}

class T14Helper extends Lib {
    public String getStr(String s, int n) {
	String rtnVal = "biggggg";
	if (n < 14) rtnVal = s;
	return rtnVal;
    }
}
