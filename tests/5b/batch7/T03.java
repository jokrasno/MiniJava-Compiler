// arrays; int-methods with int arg

class Main extends Lib {

  int i;

  public void main() {
    int i = 5;
    int[] a = new int[setI(i * 3)];
    a[0] = setI(i + 2);
    a[1] = setI(i + 2);
    a[2] = setI(i + 2);
    a[3] = setI(i + 2);
    super.printInt(i); super.printStr(" ");
    super.printInt(a.length); super.printStr(" ");
    super.printInt(a[0]); super.printStr("\n");
    super.printInt(a[3]); super.printStr("\n");
    super.printInt(a[a.length-1]); super.printStr("\n");
  }

  public int setI(int val) {
    i = val;
    return val;
  }

}
