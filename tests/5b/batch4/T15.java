// simple if: equality conditions

class Main extends Lib {
  public void main() {
      int z = 5;
      if (z > 0) {
	  super.printStr("It's so true!\n");
      }
      else {
	  super.printStr("It's so false!\n");
      }
      if (0 > z) {
	  super.printStr("True, I say!\n");
      }
      else {
	  super.printStr("False, I say!\n");
      }
  }
}
