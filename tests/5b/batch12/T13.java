// array-out-of-bounds exception: index barely too small (asst)

class Main extends Lib {
    public void main() {
	int[] abc = new int[9];
	abc[0] = 24;
	super.printStr("We got here\n");
	abc[-1] = 22;
	super.printStr("Should not get here.\n");
    }
}
