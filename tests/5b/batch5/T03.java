// library method: intToString

class Main extends Lib {
  public void main() {
    int a = -137;
    int b = -141*a;
    int c = -141*b;
    int d = -141*c;
    String aa = super.intToString(a);
    String bb = super.intToString(b);
    String cc = super.intToString(c);
    String dd = super.intToString(d);
    super.printStr(aa);
    super.printStr("\n");
    super.printStr(bb);
    super.printStr("\n");
    super.printStr(cc);
    super.printStr("\n");
    super.printStr(dd);
    super.printStr("\n");
  }
}
