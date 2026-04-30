// method call based on runtime type; call to super
class Main extends Lib {
	public void main() {
		Main var1 = null;
		var1 = new YourClass();
		String s = var1.name(44);
		printStr(s);
	}
	public String name(int x) {
		String rtnVal = "Buddy";
		if (x > 0) rtnVal = "Muddy";
		return rtnVal;
} }
class YourClass extends Main {
	public String name(int x) {
		return "A".concat(super.name(x-20));
} }
