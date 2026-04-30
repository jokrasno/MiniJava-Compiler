// empty switch statement with default

class Main extends Lib {
  public void main() {
      for (int i = 0; i < 20; i++) {
	  switch (i*3) {
	  default:
	      super.printStr("Hit default case\n");
	      break;
	  }
      }
      super.printStr("Done with Test 1\n");
  }
}

