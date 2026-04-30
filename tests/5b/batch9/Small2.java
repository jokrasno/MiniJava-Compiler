// multi-level int method calls with int parameters

class Main {
	
	Lib lib;
	int z;
	int a;
	int nn;
	
	public void main() {
		lib = new Lib();
		f1(4);
	}
	
	public void f1(int i) {
		z = 4;
		int q = 5;
		a = 36;
		q = f2(i+1, q);
		lib.printInt(a); lib.printStr("\n");
		lib.printInt(z); lib.printStr("\n");
	}
	
	
	public int f2(int i, int q) {
		q = f3(i+1, q);
		lib.printInt(q); lib.printStr("\n");
		return q + i;
	}
	
	public int f3(int i, int q) {
		nn = 55;
		q = f4(i+1, q);
		lib.printInt(z); lib.printStr("\n");
		z = z + i;
		lib.printInt(nn); lib.printStr("\n");
		return q;
	}
	
	public int f4(int i, int q) {
		lib.printInt(a); lib.printStr("\n");
		a = a + i;
		nn = nn + 1;
		if (i == 7) q = f2(105, q);
		return q;
	}
}
