// null-pointer exception, array assignment

class Main extends Lib {
    public void main() {
	int[] abc = null;
	abc[4] = 24;
	super.printStr("Should not get here.\n");
    }
}
