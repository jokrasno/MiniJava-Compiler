// empty switch statement with default that includes other cases

class Main extends Lib {
  public void main() {
      for (int i = 0; i < 20; i++) {
	  switch (i*3) {
	  case 15: default: case 24: case 33: case 34:
	      super.printStr("Hit default case\n");
	      break;
	  }
      }
      super.printStr("Done with Test 2\n");
  }
}
