// string-literal equality (==)

class Main extends Lib {
    String a;
    String b;
    String c;
    public void main() {
	this.init();
	String s = "potion";
	String t = "lotion";

	super.printBool(a == a); super.printStr("\n");
	super.printBool(a == b); super.printStr("\n");
	super.printBool(a == c); super.printStr("\n");
	super.printBool(a == s); super.printStr("\n");
	super.printBool(a == t); super.printStr("\n");

	super.printBool(b == a); super.printStr("\n");
	super.printBool(b == b); super.printStr("\n");
	super.printBool(b == c); super.printStr("\n");
	super.printBool(b == s); super.printStr("\n");
	super.printBool(b == t); super.printStr("\n");

	super.printBool(c == a); super.printStr("\n");
	super.printBool(c == b); super.printStr("\n");
	super.printBool(c == c); super.printStr("\n");
	super.printBool(c == s); super.printStr("\n");
	super.printBool(c == t); super.printStr("\n");

	super.printBool(s == a); super.printStr("\n");
	super.printBool(s == b); super.printStr("\n");
	super.printBool(s == c); super.printStr("\n");
	super.printBool(s == s); super.printStr("\n");
	super.printBool(s == t); super.printStr("\n");

	super.printBool(t == a); super.printStr("\n");
	super.printBool(t == b); super.printStr("\n");
	super.printBool(t == c); super.printStr("\n");
	super.printBool(t == s); super.printStr("\n");
	super.printBool(t == t); super.printStr("\n");
    }
    public Main init() {
	a = "potion";
	b = "potion";
	c = new T00Helper().getString();
	return this;
    }
}
class T00Helper {
    public String getString() {
	return "potion";
    }
}
