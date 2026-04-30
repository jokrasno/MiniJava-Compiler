// int method with int parm(s) that modifies inst var.

class Main extends Lib {

  int i;

  public void main() {
    int i = 17;
    Rec04 r = new Rec04().init(setI(i*2), setI(i*7));
    super.printInt(i); super.printStr(" ");
    super.printInt(r.a); super.printStr(" ");
    super.printInt(r.b); super.printStr("\n");
  }

  public int setI(int val) {
    i = val;
    return i;
  }

}

class Rec04 {

  int a;
  int b;

  public Rec04 init(int a, int b) {
    this.a = a;
    this.b = b;
    return this;
  }

}
