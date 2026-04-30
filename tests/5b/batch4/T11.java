// simple for loop with inner var decl of int

class Main extends Lib {
  public void main() {
    for (int i = 0; i <= 20; i++) {
      int j = i * i;
      super.printInt(i);super.printStr(" ");
      super.printInt(j);super.printStr(" ");
      super.printInt(j+1);super.printStr(" ");
      super.printInt(j-1);super.printStr("\n");
    }
  }
}
