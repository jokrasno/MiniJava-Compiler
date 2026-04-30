// void method call with one int arg., inst var access/asst

class Main extends Lib {

  int x;

  public void main() {
    x = 6;
    super.printInt(x); super.printStr(" ");
    incX(12);
    super.printInt(x); super.printStr(" ");
    incX(-15);
    super.printInt(x); super.printStr(" ");
    incX(-102);
    super.printInt(x); super.printStr("\n");
  }

  public void incX(int n) {
    x = x + n;
  }

}
