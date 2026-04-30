class Main {
  public static void main(String[] args) {
    TestBinaryOpErrors t = new TestBinaryOpErrors();
    t.test();
  }
}
class TestBinaryOpErrors {
  public void test() {
    int x = this + true;     // Plus: both wrong
    int y = false - null;    // Minus: both wrong
    int z = this * this;     // Times: both wrong
    int w = null / null;     // Divide: both wrong
  }
}
