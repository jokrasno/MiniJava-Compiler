// call optimization where object known to be non-null

class Main {
    public void main() {
    	new Vehicle().main();
    }
}

class Vehicle extends Lib {
	public void main() {
		int sum = 0;
		boolean b = true;
		int[] myArray = new int[4];
		// repeat these so that the relative effect of the optimizations
		// is increased
		for (int i = 0; i < 2000; i++) {
			sum = sum + "String-lit".length();
			sum = sum + new Vehicle().price();
			sum = sum + this.price();
			b = new int[4].equals(myArray);
		}
		super.printInt(sum);
		super.printStr("\n");
		super.printBool(b);
		super.printStr("\n");
	}
	public int price() {
		return 1234;
    }
}
