// for-loop with side-effect method call in test

class Main extends Lib {

  int j;

  public void main() {
    j = 30;
    for (int i = j-23; i < setJ(j+2); i = i + 5) {
      super.printStr("x ");
      super.printInt(j);
      super.printStr("\n");
    }
  }

  public int setJ(int val) {
    j = val;
    return j;
  }

  public int sum(int a, int b) { return a+b; }

}
