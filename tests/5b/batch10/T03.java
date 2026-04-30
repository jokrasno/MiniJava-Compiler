// tests access of object immediately after creation

class Main extends Lib {
    int q;
    public void main() {
	super.printInt(new Main().init(124).q);
	super.printStr("\n");
    }
    public Main init(int k) {
	q = k;
	return this;
    }
}
