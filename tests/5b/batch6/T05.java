// void method with two int args; inst-var access/asst

class Main extends Lib {

  int x;

  public void main() {
    x = 278;
    super.printInt(x); super.printStr(" ");
    mpyIncX(23, 3);
    super.printInt(x); super.printStr(" ");
    mpyIncX(3, -823);
    super.printInt(x); super.printStr(" ");
    mpyIncX(0, 8);
    super.printInt(x); super.printStr("\n");
  }

  public void mpyIncX(int m, int i) {
    x = x * m;
    x = x + i;
  }

}
