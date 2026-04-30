// method call on explicit object: object, no parameters

class Main extends Lib {
    public void main() {
	T15Helper h = new T15Helper();
	super.printStr(h.getStr());
	super.printStr("\n");
    }
}

class T15Helper extends Lib {
    public String getStr() {
	return "Red Skelton!!";
    }
}
