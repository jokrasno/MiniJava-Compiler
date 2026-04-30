// divide-by-zero exception

class Main extends Lib {
    public void main() {
	int a = 0;
	int b = 24;
	super.printStr("We got here\n");
	int c = b/a;
	super.printInt(c);
	super.printStr(" ... should not get here.\n");
    }
}
