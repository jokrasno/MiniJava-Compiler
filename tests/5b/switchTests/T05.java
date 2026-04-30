// switch statements with local variables

class Main extends Lib {
  public void main() {
      for (int i = 0; i <= 50; i++) {
	  String s = "";
	  switch (i) {
	  case 0: case 1:
	      String s1 = "Hello";
	      s = s1;
	      break;
	  case 2: case 4: case 6: case 7:
	      String s2 = "Good-bye";
	      s = s2;
	      break;
	  default:
	      int n = i + 5;
	      int m = n * 3;
	      s = super.intToString(m);
	      break;
	  }
	  super.printInt(i);
	  super.printStr(": ");
	  super.printStr(s);
	  super.printStr("\n");
      }
      super.printStr("Done with Test 5\n");
  }
}

