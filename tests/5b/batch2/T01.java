// math with local int variables; a couple assignments

class Main extends Lib {
  int len;
  public void main() {
    int aaa = new Main().len;
    int q = 3;
    int a = 30;
    int b = a * 2;
    int c = a / 7 + 3 * q;
    int d = c * c * c / (a + b);
    super.printInt(q);
    super.printStr("\n");
    super.printInt(a);
    super.printStr("\n");
    super.printInt(b);
    super.printStr("\n");
    super.printInt(c);
    super.printStr("\n");
    super.printInt(d);
    super.printStr("\n");
    a = a + 1;
    b = b + 1;
    super.printInt(q);
    super.printStr("\n");
    super.printInt(a);
    super.printStr("\n");
    super.printInt(b);
    super.printStr("\n");
    super.printInt(c);
    super.printStr("\n");
    super.printInt(d);
    super.printStr("\n");
  }
}
