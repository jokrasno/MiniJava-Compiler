// while-statement with constant condition

class Main extends Lib {
	public void main() {
		int sum = 0;
		// repeat these so that the relative effect of the optimizations
		// is increased
		for (int i = 0; i < 2000; i++) {
			int z = 5;
			while (3 < 5) {
				z++;
				if (z > i) break;
				sum = sum + i;
			}
		}
		super.printInt(sum);
		super.printStr("\n");
	}
}
