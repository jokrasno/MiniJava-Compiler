// void method call with no arguments, inst var access/asst

class Main extends Lib {

  int x;

  public void main() {
    x = 6;
    super.printInt(x); super.printStr(" ");
    incX1();
    super.printInt(x); super.printStr(" ");
    incX2();
    super.printInt(x); super.printStr(" ");
    incX3();
    super.printInt(x); super.printStr("\n");
  }

  public void incX1() {
    x = x + 1;
  }

  public void incX2() {
    x++;
  }

  public void incX3() {
    ++x;
  }
}
