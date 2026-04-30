// null-pointer exception, inst-var access

class Main extends Lib {
    int a;
    public void main() {
	Main t = null;
	int z = t.a;
	super.printStr("Should not get here.\n");
    }
}
