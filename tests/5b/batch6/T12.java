// method call: super, indirect

class Main extends Lib {
    public void main() {
	T12Helper5 h = new T12Helper5();
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

class T12Helper1 extends Main {
}
class T12Helper2 extends T12Helper1 {
}
class T12Helper3 extends T12Helper2 {
}
class T12Helper4 extends T12Helper3 {
}

class T12Helper5 extends T12Helper4 {
    public void testMethod(int k) {
	super.printStr("====>");
	super.testMethod(k+100);
    }
    public void testMethod2(int j) {
	super.printStr("!!! ");
	super.testMethod(j-100);
    }
}
