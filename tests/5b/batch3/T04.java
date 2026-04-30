// instance variable access-explicit

class Main extends Lib {
    public void main() {
	int[] a1 = new int[56];
	a1[43] = 77;
	int[] a2 = new int[7];
	a2[2] = 8;
	T04Subclass s =
	    new T04Subclass().init2(54, true, "hello", a1,
				    -99, a1.length < 10, "bye", a2);
	super.printInt(s.a); super.printStr(" ");
	super.printBool(s.b); super.printStr(" ");
	super.printStr(s.c); super.printStr(" ");
	super.printInt(s.d[43]); super.printStr(" ");
	super.printInt(s.aa); super.printStr(" ");
	super.printBool(s.bb); super.printStr(" ");
	super.printStr(s.cc); super.printStr(" ");
	super.printInt(s.dd[2]); super.printStr("\n");
    }
}

class T04TopClass {
    int a;
    boolean b;
    String c;
    int[] d;
    public T04TopClass init(int xa, boolean xb, String xc, int[] xd) {
	a = xa;
	b = xb;
	c = xc;
	d = xd;
	return this;
    }
}

class T04Subclass extends T04TopClass {
    int aa;
    boolean bb;
    String cc;
    int[] dd;
    public T04Subclass init2(int xa, boolean xb, String xc, int[] xd,
			     int xaa, boolean xbb, String xcc, int[] xdd) {
	super.init(xa,xb,xc,xd);
	aa = xaa;
	bb = xbb;
	cc = xcc;
	dd = xdd;
	return this;
    }

}
