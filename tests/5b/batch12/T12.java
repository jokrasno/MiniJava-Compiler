// array-out-of-bounds exception: index barely too small (access)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	int y = abc[0];
	super.printInt(y);
	super.printStr("\n");
	int z = abc[-1];
	super.printStr("Should not get here.\n");
    }
}
