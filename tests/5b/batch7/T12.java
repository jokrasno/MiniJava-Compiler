// method call on explicit object: int/bool, int/bool parameters

class Main extends Lib {
    public void main() {
	T12Helper h = new T12Helper();
	super.printBool(h.getBool(true, 8));
	super.printStr("/");
	super.printBool(h.getBool(false, 2373));
	super.printStr("/");
	super.printInt(h.getInt(false, 23));
	super.printStr("/");
	super.printInt(h.getInt(true, 26));
	super.printStr("\n");
    }
}

class T12Helper extends Lib {
    public boolean getBool(boolean b, int n) {
	return (n < 0) != b;
    }
    public int getInt(boolean b, int n) {
	if (b) n++;
	return n;
    }
}
