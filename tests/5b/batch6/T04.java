// method returning int; no arguments; inst var access/asst

class Main extends Lib {

  int x;
  int y;

  public void main() {
    x = 6;
    y = 88;
    super.printInt(x); super.printStr(" ");
    super.printInt(y); super.printStr("\n");
    y = incXRtnOld();
    super.printInt(x); super.printStr(" ");
    super.printInt(y); super.printStr("\n");
    y = incXRtnOld();
    super.printInt(x); super.printStr(" ");
    super.printInt(y); super.printStr("\n");
    x = 872;
    y = incXRtnOld();
    super.printInt(x); super.printStr(" ");
    super.printInt(y); super.printStr("\n");
  }

  public int incXRtnOld() {
    int old = x;
    x = x + 1;
    return old;
  }

}
