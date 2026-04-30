// while loop 3-nested

class Main extends Lib {
    public void main() {
	int x = 0;
	while (x < 1000) {
	    int y = x;
	    while (y < x + 126) {
		int z = y;
		while (z < y + 12) {
		    super.printInt(z);
		    super.printStr("\n");
		    z = z + 2;
		}
		super.printInt(y);
		super.printStr("*\n");
		y = y + 17;
	    }
	    super.printInt(x);
	    super.printStr("*\n");
	    x = x + 84;
	}
    }
}
