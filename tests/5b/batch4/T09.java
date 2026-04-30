// cascaded and nested if: comparison operators on it; strCmp

class Main extends Lib {
  public void main() {
    int a = 78;
    int b = 27;
    if (a > b) super.printStr("a is greater\n");
    else if (a < b) super.printStr("b is greater\n");
    else super.printStr("they are equal\n");
    a = 7;
    if (a > b) super.printStr("a is greater\n");
    else if (a < b) super.printStr("b is greater\n");
    else super.printStr("they are equal\n");
    b = 7;
    if (a > b) super.printStr("a is greater\n");
    else if (a < b) super.printStr("b is greater\n");
    else super.printStr("they are equal\n");
    {
      String s = "beta";
      String t = "alpha";
      if (s.compareTo(t) > 0) super.printStr("s is greater\n");
      if (s.compareTo(t) >= 0) super.printStr("s is greater or equal\n");
      if (s.compareTo(t) == 0) super.printStr("s is greater\n");
      t = "zebra";
      if (s.compareTo(t) > 0) super.printStr("s is greater\n");
      if (s.compareTo(t) >= 0) super.printStr("s is greater or equal\n");
      if (s.compareTo(t) == 0) super.printStr("s is greater\n");
      t = "beta";
      if (s.compareTo(t) > 0) super.printStr("s is greater\n");
      if (s.compareTo(t) >= 0) super.printStr("s is greater or equal\n");
      if (s.compareTo(t) == 0) super.printStr("s is greater\n");
    }
  }
}
