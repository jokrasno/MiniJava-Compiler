// local string variables; print/concat

class Main extends Lib {
  public void main() {
    String q = "";
    String a = "This is the time";
    super.printStr(q);
    super.printStr("\n");
    super.printStr(a);
    super.printStr("\n");
    String b = "!!!";
    String c = "orange";
    a = b.concat(c);
    super.printStr(a);
  }
}

