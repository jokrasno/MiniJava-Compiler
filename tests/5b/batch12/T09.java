// array-out-of-bounds exception: index barely too large (asst)

class Main extends Lib {
    public void main() {
	int[] abc = new int[9];
	abc[8] = 24;
	super.printStr("We got here\n");
	abc[9] = 22;
	super.printStr("Should not get here.\n");
    }
}
