// if-statement with empty block

class Main extends Lib {
    public void main() {
	int sum = 0;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    if (i < 1000) {
		sum = sum + 23;
	    }
	    else {
	    }
	    if (i > 500) {
	    }
	    else {
		sum = sum - 7;
	    }
	}
	super.printInt(sum);
	super.printStr("\n");
    }
}
