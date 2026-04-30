// int method with int parm(s) that modifies inst var.

class Main extends Lib {

  int i;

  public void main() {
    i = 28;

    super.printInt(sum(setI(i+3), setI(i*7))); super.printStr(" ");
    super.printInt(i); super.printStr("\n");
  }

  public int setI(int val) {
    i = val;
    return i;
  }

  public int sum(int a, int b) { return a+b;}

}
