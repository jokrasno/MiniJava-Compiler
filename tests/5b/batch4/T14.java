// simple if: literal conditions

class Main extends Lib {
  public void main() {
      if (true) {
	  super.printStr("It's so true!\n");
      }
      else {
	  super.printStr("It's so false!\n");
      }
      if (false) {
	  super.printStr("True, I say!\n");
      }
      else {
	  super.printStr("False, I say!\n");
      }
  }
}
