// instance variable with same name as superclass

class Main extends Lib {
    public void main() {
	T06Subclass s = new T06Subclass().init2(43, 77);
	s.print();
	super.printStr("\n");
    }
}

class T06TopClass {
    int a;
    Lib lib;
    public int getA1() {
	return a;
    }
    public void init1(int aa) {
	a = aa;
	lib = new Lib();
    }
    public void print() {
	lib.printInt(a);
    }
}

class T06Subclass extends T06TopClass {
    int a;
    public int getA2() {
	return a;
    }
    public T06Subclass init2(int hidden, int aa) {
	super.init1(hidden);
	a = aa;
	return this;
    }
    public void print() {
	lib.printInt(a);
	lib.printStr("(");
	super.print();
	lib.printStr(")");
    }
}
