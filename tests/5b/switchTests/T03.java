// switch statement with default and other cases

class Main extends Lib {
  public void main() {
      for (int i = -20; i < 20; i++) {
	  switch (i) {
	  case 10: case -11:
	      super.printStr("Case 1\n");
	      break;
	  case 13: case -14:
	      super.printStr("Case 2\n");
	      break;
	  default: case 17: case -2:
	      super.printStr("Default case\n");
	      break;
	  }
      }
      super.printStr("Done with Test 3\n");
  }
}
