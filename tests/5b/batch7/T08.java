// method call on explicit object: void, int/bool parameter

class Main extends Lib {
    public void main() {
	T08Helper h = new T08Helper();
	h.call(true);
	h.call(false);
    }
}

class T08Helper extends Lib {
    public void call(boolean b) {
	if (b) {
	    super.printStr("Is this a message?\n");
	}
	else {
	    super.printStr("What message\n");
	}
    }
}
