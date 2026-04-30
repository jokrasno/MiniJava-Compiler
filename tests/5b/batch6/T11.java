// method call: super, direct

class Main extends Lib {
    public void main() {
	T11Helper h = new T11Helper();
	h.testMethod(33);
	h.testMethod2(33);
    }
    public void testMethod(int k) {
	super.printInt(k);
	super.printStr("\n");
    }
    public void testMethod2(int k) {
	super.printInt(k);
	super.printStr(" ");
	super.printInt(k*2);
	super.printStr("\n");
    }
}

class T11Helper extends Main {
    public void testMethod(int k) {
	super.printStr("----->");
	super.testMethod(k+100);
    }
    public void testMethod2(int j) {
	super.printStr("??? ");
	super.testMethod(j-100);
    }
}
