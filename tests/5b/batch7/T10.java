// method call on explicit object: void, multiple parameters

class Main extends Lib {
    public void main() {
	T10Helper h = new T10Helper();
	h.call("Donald", 4, "Funny", true);
	h.call("Duck", 82, "Where", false);
    }
}

class T10Helper extends Lib {
    public void call(String s, int i, String z, boolean b) {
	if (b) {
	    super.printStr(s);
	}
	else {
	    super.printStr(z);
	    super.printStr("/");
	    super.printInt(i);
	}
	super.printStr("\n");
    }
}
