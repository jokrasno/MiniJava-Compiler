// method call on explicit object: void, object parameter

class Main extends Lib {
    public void main() {
	T09Helper h = new T09Helper();
	h.call("Willy");
	h.call("Wonka");
    }
}

class T09Helper extends Lib {
    public void call(String s) {
	super.printStr("<<");
	super.printStr(s);
	super.printStr("<<\n");
    }
}
