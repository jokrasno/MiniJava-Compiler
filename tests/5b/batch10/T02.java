// tests access of array immediately after creation

class Main extends Lib {
	public void main() {
		super.printInt((new int[5])[3]);
		super.printStr("\n");
	}
}
