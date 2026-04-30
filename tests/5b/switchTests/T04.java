// switch statement testing prime numbers

class Main extends Lib {
  public void main() {
      for (int i = 0; i <= 50; i++) {
	  String s = "";
	  switch (i) {
	  case 0: case 1:
	      s = "neither prime nor composite";
	      break;
	  default:
	      s = "composite";
	      break;
	  case 2: case 3: case 5: case 7: case 11: case 13: case 17: case 19:
	  case 23: case 29: case 31: case 37: case 41: case 43: case 47:
	      s = "prime";
	      break;
	  }
	  super.printInt(i);
	  super.printStr(" is ");
	  super.printStr(s);
	  super.printStr("\n");
      }
      super.printStr("Done with Test 4\n");
  }
}

