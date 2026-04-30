// method call on explicit object: int/bool, object parameter

class Main extends Lib {
    public void main() {
	T13Helper h = new T13Helper();
	super.printBool(h.getBool("Funny"));
	super.printStr("/");
	super.printBool(h.getBool(null));
	super.printStr("/");
	super.printInt(h.getInt("Why"));
	super.printStr("\n");
    }
}

class T13Helper extends Lib {
    public boolean getBool(Object z) {
	return z != null;
    }
    public int getInt(String s) {
	return -s.length();
    }
}
