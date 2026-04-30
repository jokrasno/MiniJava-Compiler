// instance variable access/assign where object is 'this'

class Main {
	public void main() {
		new Vehicle().main();
	}
}

class Vehicle extends Lib {
	int myVal;
	public void main() {
		myVal = 0;
		// repeat these so that the relative effect of the optimizations
		// is increased
		for (int i = 0; i < 2000; i++) {
			myVal = this.myVal + 13;
			this.myVal = myVal - 4;
		}
		super.printInt(myVal);
		super.printStr("\n");
	}
}
