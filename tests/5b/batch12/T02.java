// null-pointer exception, inst-var assignment

class Main extends Lib {
    int a;
    public void main() {
	Main t = null;
	t.a = 24;
	super.printStr("Should not get here.\n");
    }
}
