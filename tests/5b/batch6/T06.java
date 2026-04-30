// String-method with string/int args; for-loop; concat

class Main extends Lib {

  Lib lib;

  public void main() {
    super.printStr(replString("q", 30));
    super.printStr("\n");
    super.printStr(replString("Speaking of exams ...", -4));
    super.printStr("\n");
    super.printStr(replString("ha ", 12));
    super.printStr("\n");
    super.printStr(replString("Home Alone", 1));
    super.printStr("\n");
    super.printStr(replString("Good-night, David.", 0));
    super.printStr("\n");
    super.printStr(replString("Mayday! ", 2));
    super.printStr("\n");
  }

  public String replString(String s, int count) {
    String rtnVal = "";
    for (int i = 0; i < count; i++) {
      rtnVal = rtnVal.concat(s);
    }
    return rtnVal;
  }

}

