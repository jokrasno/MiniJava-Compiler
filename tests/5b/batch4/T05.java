// simple break inside while loop

class Main extends Lib {
  public void main() {
    int x = 6;
    int y = 7;
    while (x > y) {
      super.printStr("This should not be printed\n");
      break;
    }
    super.printStr("This should be printed\n");
  }
}
