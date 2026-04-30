// null-pointer exception, method call

class Main extends Lib {
    public void main() {
	Main t = null;
	t.callMe();
	super.printStr("Should not get here, either\n");
    }
    public void callMe() {
	super.printStr("Should not get here\n");
    }
}
