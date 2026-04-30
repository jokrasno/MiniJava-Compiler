// null-pointer exception, array length

class Main extends Lib {
    public void main() {
	int[] abc = null;
	int x = abc.length;
	super.printStr("Should not get here.\n");
    }
}
