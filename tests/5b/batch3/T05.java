// instance variable access-explicit

class Main extends Lib {
    public void main() {
	int[] a1 = new int[56];
	a1[43] = 62;
	int[] a2 = new int[7];
	a2[2] = 5;
	T05Subclass s =
	    new T05Subclass().init2(-1, true, "tuba", a1,
				    87, a1.length < 10, "horn", a2);
	s.printAll();
    }
}

class T05TopClass extends Lib {
    int a;
    boolean b;
    String c;
    int[] d;
    public T05TopClass init(int xa, boolean xb, String xc, int[] xd) {
	  a = xa;
	  b = xb;
	  c = xc;
	  d = xd;
	  return this;
    }
}

class T05Subclass extends T05TopClass {
    int aa;
    boolean bb;
    String cc;
    int[] dd;
    public T05Subclass init2(int xa, boolean xb, String xc, int[] xd,
			     int xaa, boolean xbb, String xcc, int[] xdd) {
	super.init(xa,xb,xc,xd);
	aa = xaa;
	bb = xbb;
	cc = xcc;
	dd = xdd;
	return this;
    }

    public void printAll() {
	super.printInt(dd[2]); super.printStr(" ");
	super.printStr(cc); super.printStr(" ");
	super.printBool(bb); super.printStr(" ");
	super.printInt(aa); super.printStr(" ");
	super.printInt(d[43]); super.printStr(" ");
	super.printStr(c); super.printStr(" ");
	super.printBool(b); super.printStr(" ");
	super.printInt(a); super.printStr("\n");
    }
}
