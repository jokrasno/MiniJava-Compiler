// array-out-of-bounds exception: index barely too large (access)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	int y = abc[53];
	super.printInt(y);
	super.printStr("\n");
	int z = abc[54];
	super.printStr("Should not get here.\n");
    }
}
