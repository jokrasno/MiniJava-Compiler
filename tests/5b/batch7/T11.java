// method call on explicit object: int/bool, no parameters

class Main extends Lib {
    public void main() {
	T11Helper h = new T11Helper();
	super.printBool(h.getBool());
	super.printStr("/");
	super.printInt(h.getInt());
	super.printStr("\n");
    }
}

class T11Helper extends Lib {
    public boolean getBool() {
	return false;
    }
    public int getInt() {
	return -333;
    }
}
