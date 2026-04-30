// void methods with one arg, arith/compar operators, concatStr

class Main extends Lib {

  int i;
  String str;

  public void main() {
    i = 20;
    super.printInt(mulI(2)+addI(3));
    super.printInt(i); super.printStr("\n");
    i = 21;
    super.printInt(mulI(2)-addI(3));
    super.printInt(i); super.printStr("\n");
    i = 22;
    super.printInt(mulI(2)*addI(3));
    super.printInt(i); super.printStr("\n");
    i = 23;
    super.printInt(mulI(2)*50/addI(3));
    super.printInt(i); super.printStr("\n");

    i = 24;
    super.printBool(mulI(2)==addI(3));
    super.printInt(i); super.printStr("\n");
    i = 25;
    super.printBool(mulI(2)!=addI(3));
    super.printInt(i); super.printStr("\n");
    i = 26;
    super.printBool(mulI(2)<addI(3));
    super.printInt(i);
    super.printStr("\n");
    i = 27;
    super.printBool(mulI(2)>addI(3));
    super.printInt(i);
    super.printStr("\n");
    i = 28;
    super.printBool(mulI(2)<=addI(3));
    super.printInt(i);
    super.printStr("\n");
    i = 29;
    super.printBool(mulI(2)>=addI(3));
    super.printInt(i);
    super.printStr("\n");

    str = "abc";
    super.printBool(concatStr("x").compareTo(concatStr("y")) == 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");

    str = "def";
    super.printBool(concatStr("x").compareTo(concatStr("y")) != 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");

    str = "ghi";
    super.printBool(concatStr("x").compareTo(concatStr("y")) > 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");

    str = "jkl";
    super.printBool(concatStr("x").compareTo(concatStr("y")) < 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");

    str = "mno";
    super.printBool(concatStr("x").compareTo(concatStr("y")) >= 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");

    str = "pqr";
    super.printBool(concatStr("x").compareTo(concatStr("y")) <= 0);
    super.printStr(str); super.printStr("\n");
    super.printStr(str); super.printStr("\n");
  }

  public int mulI(int n) {
    i = i * n;
    return i;
  }

  public int addI(int n) {
    i = i + n;
    return i;
  }

  public String concatStr(String s) {
    str = str.concat(s);
    return str;
  }

}
