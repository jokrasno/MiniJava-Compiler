// misc. operators, if

class els {
	
}

class Main extends Lib {
	els e;
	int intx;
	int x;
	
	public void main() {
		test1();
		test2();
		test3();
		test4();
	}
	
	public void test1() {
		intx = 456;
		x = 76;
		e = null;
		int a = 55;
		int b = 65;
		if (a > b) {
			a++;
		}
		els e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}
	
	public void test2() {
		intx = 653;
		x = 23;
		e = null;
		int a = 845;
		int b = 354;
		if (a > b) {
			a++;
		}
		els/**/e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}
	
	public void test3() {
		intx = 4634;
		x = 2532;
		e = null;
		int a = 75454;
		int b = 63235;
		if (a > b) {
			a++;
		}
		 els	e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}

	/**
	 * 
	 */
	public void test4() {
		intx = 73;
		x = 363;
		e = null;
		int a = -63;
		int b = 3574;
		if (a > b) {
			a++;
		}
		els
e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}
	/**
	 * 
	 */
	public void test5() {
		intx = 456;
		x = 735;
		e = null;
		int a = -632;
		int b = 6364;
		if (a > b) {
			a++;
		}
		els e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}
	/**
	 * 
	 */
	public void test6() {
		intx = 734;
		x = 7763434;
		e = null;
		int a = -63443;
		int b = 36434;
		if (a > b) {
			a++;
		}
		// want to have a form feed here
		els e = new els();
		if (e == null) {
			printStr("null\n");
		}
		else {
			printStr("not null\n");
		}
		printInt(x);
		printStr("\n");
		printInt(intx);
		printStr("\n");
	}
}
