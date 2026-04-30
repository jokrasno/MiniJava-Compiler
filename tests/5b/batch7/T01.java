// 2D array, doubly-nested for-loop, % operator; inst var.
class Main extends Lib {

  int i;

  public void main() {
    int size = 11;
    int[][] a = new int[size][];
    for (int j = 0; j < a.length; j++) {
        a[j] = new int[size];
    }
    int k = 24;
    i = 5;
    for (int j = 0; j < a.length; j++) {
      a[incrI()%a.length][incrI()%a.length] = 28;
      for (int jj = 0; jj < a.length; jj++) {
        for (k = 0; k < a[j].length; k++) {
          super.printInt(a[jj][k]);
          super.printStr((((" "))));
        }
        super.printStr("\n");
      }
      super.printStr("\n");
    }
  }

  public int incrI() {
   i = i + 1;
   return i;
  }

}
