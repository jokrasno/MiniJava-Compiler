// performs various operations, including on lists

class Main {
	
	Lib lib;
	
	public void main() {
		lib = new Lib();
		
		int z = 3;
		int ww = z + 9;
		int qqq = ww * z;
		int[]a = new int[20];
		int[][] mat = new int[5][];
		for (int i = 0; i < mat.length; i++) {
			mat[i] = new int[4];
		}
		IListS0 il = null;
		int count = 0;
		for (int z2 = 0; z2 <mat.length; z2++) {
			for (int zz = 0; zz < mat[z2].length; zz++) {
				count++;
				mat[z2][zz] = count;
			}
		}
		
		lib.printInt(a[4]);
		lib.printStr(" ");
		lib.printInt(a[2]);
		lib.printStr("\n");
		for (z = 0; z < a.length; z++) a[z] = z*4+3;
		lib.printInt(z);
		lib.printStr(" ");
		{
			int art = 555;
			lib.printInt(art+222); lib.printStr(" ");
			ww = ww + -1;
			lib.printInt(qqq); lib.printStr(" ");
			lib.printInt(ww); lib.printStr("\n");
		}
		
		if (z > 5) lib.printStr("z is greater than 5\n");
		if (z <= 3) lib.printStr("z is less than or equal to 3\n");
		
		lib.printStr("This is a string\n");
		
		lib.printStr("jello ".concat("is nice")); lib.printStr("\n");
		
		lib.printInt(34+7); lib.printStr(" ");
		lib.printInt(34-7); lib.printStr(" ");
		lib.printInt(34*7); lib.printStr(" ");
		lib.printInt(34/7); lib.printStr(" ");
		lib.printBool(34>7); lib.printStr(" ");
		lib.printBool(34<7); lib.printStr(" ");
		lib.printBool(34>=7); lib.printStr(" ");
		lib.printBool(34<=7); lib.printStr(" ");
		lib.printBool(34==7); lib.printStr(" ");
		lib.printBool(34!=7); lib.printStr(" ");
		lib.printStr("\n");
		while (ww > 0) {
			lib.printInt(ww);
			lib.printStr(" ");
			if (ww == 5) break;
			ww = ww - 1;
		}
		lib.printStr("\n");
		for (int ii = 2; ii <= 20; ii++) {
			lib.printInt(ii*11);
			lib.printStr(" ");
			if (ii == 12) break;
		}
		lib.printStr("\n");
		lib.printInt(a[4]);
		lib.printStr(" ");
		lib.printInt(a[2]);
		lib.printStr("\n");
		for (int z2 = 0; z2 < mat.length; z2++) {
			for (int zz = 0; zz < mat.length; zz++) {
				if (z2 < mat[zz].length) lib.printInt(mat[zz][z2]);
				else {lib.printStr("**"); lib.printStr(" ");}
			}
			lib.printStr("\n");
		}
		il = new IListS0().init(il,34);
		il = new IListS0().init(il,22);
		il = new IListS0().init(il,-5);
		il = new IListS0().init(il,78);
		il = new IListS0().init(il,2);
		il = new IListS0().init(il,9002);
		IListS0 p = il;
		while (p != null) {
			lib.printInt(p.val);
			lib.printStr(" ");
			p = p.next;
			lib.printStr("\n");
		}
	}
}

class IListS0  {
	IListS0 next;
	int val;
	public IListS0 init(IListS0 il, int i) {
		next = il;
		val = i;
		return this;
	}
}
