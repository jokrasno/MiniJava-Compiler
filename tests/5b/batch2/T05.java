// local object variables

class Main extends Lib {
    public void main() {
	Main z = new Main();
	Main zz = new Main();
	super.printBool(z == null);
	super.printBool(zz == null);
	super.printStr("\n");
    }
}
