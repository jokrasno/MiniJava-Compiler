// int arrays: one and two dimensional; assignment+access

class Main extends Lib {
  public void main() {
    int[] a1 = new int[23];
    int[][] a2 = new int[16][];
    a2[3] = new int[23];
    a2[4] = new int[9];
    a2[5] = a2[4];
    a2[0] = new int[40];
    a2[0][0] = 223;
    a2[3][7] = 32823;
    a2[3][8] = 324;
    a2[4][7] = 37;
    a2[5][7] = 3722;
    a2[5][8] = 65;
    a2[5][7] = 6722;
    super.printInt(a1.length);super.printStr(" ");
    super.printInt(a2.length);super.printStr(" ");
    super.printInt(a2[3].length);super.printStr(" ");
    super.printInt(a2[4].length);super.printStr("\n");
    super.printInt(a2[0][0]);super.printStr(" ");
    super.printInt(a2[3][7]);super.printStr(" ");
    super.printInt(a2[3][8]);super.printStr(" ");
    super.printInt(a2[4][7]);super.printStr(" ");
    super.printInt(a2[4][8]);super.printStr(" ");
    super.printInt(a2[5][7]);super.printStr(" ");
    super.printInt(a2[5][8]);super.printStr(" ");
    super.printInt(a2[5][7]);super.printStr("\n");
    super.printBool(a2[3]==a2[4]);
    super.printBool(a2[3]!=a2[4]);
    super.printBool(a2[4]==a2[5]);
    super.printBool(a2[3]!=a2[5]);super.printStr("\n"); 
    a2[3] = new int[88];
    a2[3][8] = 344;
    a2[4][7] = 10000;
    super.printInt(a1.length);super.printStr(" ");
    super.printInt(a2.length);super.printStr(" ");
    super.printInt(a2[3].length);super.printStr(" ");
    super.printInt(a2[4].length);super.printStr("\n");
    super.printInt(a2[0][0]);super.printStr(" ");
    super.printInt(a2[3][7]);super.printStr(" ");
    super.printInt(a2[3][8]);super.printStr(" ");
    super.printInt(a2[4][7]);super.printStr(" ");
    super.printInt(a2[4][8]);super.printStr(" ");
    super.printInt(a2[5][7]);super.printStr(" ");
    super.printInt(a2[5][8]);super.printStr(" ");
    super.printInt(a2[5][7]);super.printStr("\n");
  }
}
