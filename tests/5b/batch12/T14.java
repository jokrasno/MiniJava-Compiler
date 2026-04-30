// array-creation exception: negative size

class Main extends Lib {
    public void main() {
	int[] abc = new int[0];
	super.printStr("We got here\n");
	int[] def = new int[-1];
	super.printStr("Should not get here.\n");
    }
}
