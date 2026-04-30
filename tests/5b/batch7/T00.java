// array creation/access; inst-var access

class Main extends Lib {

  int i;

  public void main() {
    int[] a = new int[10];
    i = 5;
    for (int j = 0; j < a.length; j++) {
      a[j] = j*j;
    }
    a[incrI()] = a[incrI()] + 1;
    for (int j = 0; j < a.length; j++) {
      super.printInt(a[j]);
      super.printStr(" ");
    }
   super.printStr("\n");
  }

  public int incrI() { i++; return i; }

}

