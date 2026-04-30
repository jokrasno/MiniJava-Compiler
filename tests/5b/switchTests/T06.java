// switch statements with internal breaks

class Main extends Lib {
  public void main() {
      for (int i = 0; i < 20; i++) {
	  switch (i) {
	  case 0: case 12:
	      super.printStr("Case 1\n");
	      break;
	  case 2: case 9: case 3: case -5:
	      if (i > 5) {
		  String ss = "Case 2a";
		  super.printStr(ss);
		  super.printStr("\n");
		  break;
	      }
	      super.printStr("Case 2b\n");
	      break;
	  default:
	      int n = i*2;
	      if (n < 31) {
		  super.printStr("Case 3a\n");
		  if (n < 12) {
		      super.printStr("Case 3b\n");
		      break;
		  }
	      }
	      super.printStr("Case 3c\n");
	      break;
	  }
      }
      super.printStr("Done with Test 6\n");
  }
}

