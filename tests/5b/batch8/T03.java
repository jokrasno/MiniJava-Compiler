// class that models Java's Integer wrapper-class

class Main extends Lib {

  public void main() {
    abc();
  }

  public void inner(IntHolder3 h) {
    h.val = h.val + 1;
  }

  public void abc() {
    IntHolder3 z = new IntHolder3();
    z.val = 12;
    inner(z);
    super.printInt(z.val); super.printStr(" ");
    inner(z);
    inner(z);
    super.printInt(z.val); super.printStr(" ");
    for (int i = 1; i <= 5; i++) inner(z);
    super.printInt(z.val); super.printStr("\n");
  }
}

class IntHolder3 {
  int val;
}
