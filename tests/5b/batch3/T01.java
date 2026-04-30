// object: creation + int inst-var access/assign

class Main extends Lib {
  public void main() {
    IntPair01 p1 = null;
    IntPair01 p2 = new IntPair01();
    p2.first = 34;
    p2.second = 87;
    IntPair01 p3 = new IntPair01();
    p3.first = -3;
    p3.second = 2;;
    super.printInt(p2.first); super.printStr("\n");
    super.printInt(p2.second); super.printStr("\n");
    super.printInt(p3.first); super.printStr("\n");
    super.printInt(p3.second); super.printStr("\n");
    p1 = p2;
    p1.first = 99;
    p2.second = -223;
    super.printInt(p2.first); super.printStr("\n");
    super.printInt(p2.second); super.printStr("\n");
    super.printBool(p1==p2);super.printBool(p1!=p2);
    super.printBool(p1==p3);super.printBool(p1!=p3);super.printStr("\n");
  }
}

class IntPair01 {
  int first;
  int second;
}
