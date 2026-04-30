// array-out-of-bounds exception: index too small (asst)

class Main extends Lib {
    public void main() {
	int[] abc = new int[54];
	abc[-12] = 274;
	super.printStr("Should not get here.\n");
    }
}
