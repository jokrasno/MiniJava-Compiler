// simple for loop with conditional break

class Main extends Lib {
  public void main() {
    int z = 20;
    for (int i = 50; i <= 100; i++) {
      if (z > 25) break;
      super.printInt(i); super.printStr(" ");
    }
    super.printStr("\n");
  }
}
