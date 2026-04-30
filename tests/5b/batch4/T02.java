// while loop (non-nested) with int variables

class Main extends Lib {
  public void main() {
    int z = 27;
    int i = 0;
    while (z >= 0) {
      z = z - i;
      i = i + 1;
      super.printInt(z); super.printStr(" ");
    }
    super.printStr("\n");
  }
}

