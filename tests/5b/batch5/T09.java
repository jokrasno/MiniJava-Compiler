// library method: readInt

class Main extends Lib {
  public void main() {
    super.printStr("jello\n");
    super.printStr(" and\n");
    super.printStr("  mustard\n");
    int n = super.readInt();
    int k = super.readInt();
    super.printInt(k);
    super.printStr(":");
    super.printInt(n);
    super.printStr("\n");
  }
}
