// null-pointer exception, array access

class Main extends Lib {
    public void main() {
	int[] abc = null;
	int z = abc[3];
	super.printStr("Should not get here.\n");
    }
}
