// constant folding

class Main extends Lib {
    public void main() {
	int sum = 0;
	boolean b1 = true;
	boolean b2 = true;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    int a = 3 * 3 + 4 / 3 * 7;
	    int b = 73 % 23;
	    int c = 8 - 3;
	    sum = sum + a + b + c;
	    b1 = 10 > 4 && 10 < 200;
	    b2 = 10 == 34 || 10 != 38;
	}
	super.printInt(sum);
	super.printStr("\n");
	super.printBool(b1);
	super.printStr("\n");
	super.printBool(b2);
	super.printStr("\n");
    }
}
