// if-statement with constant condition

class Main extends Lib {
    public void main() {
	int sum = 0;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    if (4 > 10) {
		sum = sum + 23;
	    }
	    else {
		sum = sum - 2;
	    }
	    if (4 > -2) {
		sum = sum + 19;
	    }
	    else {
		sum = sum - 5;
	    }
	}
	super.printInt(sum);
	super.printStr("\n");
    }
}
