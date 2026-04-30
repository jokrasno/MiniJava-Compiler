// ! operator, == operator on ints

class Main extends Lib {
  public void main() {
    int a = 3;
    int b = -35;
    int c = 0;
    super.printInt(a);super.printStr(" ");
    super.printBool(!(a==0));super.printStr(" ");
    super.printBool(!!(a==0));
    super.printStr("\n");

    super.printInt(b);super.printStr(" ");
    super.printBool(!(b==0));super.printStr(" ");
    super.printBool(!!(b==0));
    super.printStr("\n");

    super.printInt(c);super.printStr(" ");
    super.printBool(!(c==0));super.printStr(" ");
    super.printBool(!!(c==0));
    super.printStr("\n");
  }
}
