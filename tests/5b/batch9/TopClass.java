// String method, while, break

class Classz2 extends Lib {
	int a;
	int c;
	public String myMethod(int b) {
		while (c < 10) {
			a = a + b;	
			c = c + a;
			break;
		}
		super.printInt(a);
		return "Jelly";
	}
}
class Main extends Classz2 {
	public void main() {
		super.printStr(myMethod(a));
		super.printStr("\n");
	}
}
class Classz3 extends Classz2 {
}
