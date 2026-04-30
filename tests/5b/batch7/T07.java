// method call on explicit object: void, no parameters

class Main extends Lib {
    public void main() {
	T07Helper h = new T07Helper();
	h.call();
    }
}

class T07Helper extends Lib {
    public void call() {
	super.printStr("This is a message\n");
    }
}
