// nested switch statements

class Main extends Lib {
	public void main() {
		for (int i = 0; i <20; i++) {
			switch (i) {
			case 0: case 1: case 2: case 4: case 5: case 6:
				switch (i-1) {
				case 3: case 4:
					super.printStr("Case 1\n");
					break;
				default:
					super.printStr("Case 2\n");
					switch (i*2) {
					case 8: case 9: case 10: case 12:
						super.printStr("Case 3\n");
						break;
					default:
						super.printStr("Case 4\n");
						break;
					}
					break;
				}
				break;
			case 7: case 12: case 66: case -124:
				super.printStr("Case 5\n");
				break;
			}
		}
		super.printStr("Done with Test 7\n");
	}
}
