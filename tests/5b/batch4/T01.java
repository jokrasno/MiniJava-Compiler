// local variable assignment with arithmetic operators

class Main extends Lib {
  public void main() {
    int z = 27;
    super.printInt(z);super.printStr("\n");
    z = z + 4;
    super.printInt(z);super.printStr("\n");
    z = z - 17;
    super.printInt(z);super.printStr("\n");
    z--;
    super.printInt(z);super.printStr("\n");
    z = z + -100;
    super.printInt(z);super.printStr("\n");
    z++;
    super.printInt(z);super.printStr("\n");
    z = z * 12;
    super.printInt(z);super.printStr("\n");
    ++z;
    super.printInt(z);super.printStr("\n");
    z = z / 41;
    super.printInt(z);super.printStr("\n");
    z = z + 2345;
    super.printInt(z);super.printStr("\n");
    z = z % 41;
    super.printInt(z);super.printStr("\n");
    --z;
    super.printInt(z);super.printStr("\n");
  }
}
