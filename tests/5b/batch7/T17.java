// method call on explicit object: object, int/bool parameter

class Main extends Lib {
    public void main() {
	T17Helper h = new T17Helper();
	super.printStr(h.getStr("Hold it!"));
	super.printStr("\n");
	super.printStr(h.getStr(null));
	super.printStr("\n");
    }
}

class T17Helper extends Lib {
    public String getStr(String xyz) {
	String rtnVal = "Default";
	if (xyz != null) rtnVal = xyz;
	return rtnVal;
    }
}
