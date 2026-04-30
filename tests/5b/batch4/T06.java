// while loop (non-nested) break out of variable-decl block

class Main extends Lib {
  public void main() {
    int x = 6;
    int y = 70;
    while (true) {
      int diff = y-x;
      int halfDiff = diff/2;
      int delta = halfDiff+1;
      super.printInt(x); super.printStr("\n");
      x = x - delta;
      if (x >= y) break;
    }
    super.printInt(x); super.printStr("\n");
    super.printInt(y); super.printStr("\n");
  }
}
