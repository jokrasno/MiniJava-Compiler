// array-out-of-bounds exception: index too small (access)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	int x = abc[-23];
	super.printStr("Should not get here.\n");
    }
}
