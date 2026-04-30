// array-out-of-bounds exception: index too large (access)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	int x = abc[73];
	super.printStr("Should not get here.\n");
    }
}
