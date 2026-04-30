// int arrays: 1 dimensional; access/assignment

class Main extends Lib {
  public void main() {
    int[] a1 = new int[23];
    int[] a2 = new int[1];
    int[] a3 = new int[0];
    int[] a4 = null;
    a1[0] = 87;
    a1[4] = 72;
    a1[5] = 874;
    a1[7] = 762;
    a1[21] = 772;
    a2[0] = 121;
    super.printInt(a1.length);super.printStr(" ");
    super.printInt(a2.length);super.printStr(" ");
    super.printInt(a3.length);super.printStr("\n");
    super.printInt(a1[0]);super.printStr(" ");
    super.printInt(a1[4]);super.printStr(" ");
    super.printInt(a1[5]);super.printStr(" ");
    super.printInt(a1[6]);super.printStr(" "); 
    super.printInt(a1[7]);super.printStr(" ");
    super.printInt(a1[21]);super.printStr("\n"); 
    super.printInt(a2[0]);super.printStr("\n");
    super.printBool(a1==a2);super.printBool(a1!=a2);
    super.printBool(a1==a3);super.printBool(a1!=a3);
    super.printBool(a1==a4);super.printBool(a1!=a4);
    super.printStr("\n"); 
    super.printBool(a1==null);super.printBool(a1!=null);
    super.printBool(a2==null);super.printBool(a2!=null);
    super.printBool(a3==null);super.printBool(a3!=null);
    super.printBool(a4==null);super.printBool(a4!=null);
    super.printStr("\n"); 
    a1[4] = 3;
    a1[6] = 9;
    a2[0] = 7;
    a3 = a1;
    super.printInt(a1.length);super.printStr(" ");
    super.printInt(a2.length);super.printStr(" ");
    super.printInt(a3.length);super.printStr("\n");
    super.printInt(a1[0]);super.printStr(" ");
    super.printInt(a1[4]);super.printStr(" ");
    super.printInt(a1[5]);super.printStr(" ");
    super.printInt(a1[6]);super.printStr(" "); 
    super.printInt(a1[7]);super.printStr(" ");
    super.printInt(a1[21]);super.printStr("\n"); 
    super.printInt(a2[0]);super.printStr("\n");
    super.printBool(a1==a2);super.printBool(a1!=a2);
    super.printBool(a1==a3);super.printBool(a1!=a3);
    super.printBool(a1==a4);super.printBool(a1!=a4);
    super.printStr("\n");
  }
}
