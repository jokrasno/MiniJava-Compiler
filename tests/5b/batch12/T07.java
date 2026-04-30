// array-out-of-bounds exception: index too large (asst)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	abc[73] = 274;
	super.printStr("Should not get here.\n");
    }
}
