// "infinite" for -loop with simple conditional break

class Main extends Lib {
  public void main() {
    int a = 6;
    for (;;) {
      super.printInt(a);
      super.printStr(" ");
      a = a - 1;
      if (a <= 0) break;
    }
    super.printStr("\n");
  }
}
