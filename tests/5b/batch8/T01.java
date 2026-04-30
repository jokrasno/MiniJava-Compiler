// int/void multi-level method calls; create/pass arrays as parms

class Main extends Lib {

  int x;

  public void main() {
    super.printInt(top(100));super.printStr(" ");
    super.printInt(top(24));super.printStr(" ");
    super.printInt(top(4));super.printStr(" ");
    super.printInt(top(-9));super.printStr(" ");
    super.printInt(top(-9));super.printStr(" ");
    super.printInt(top(-994));super.printStr(" ");
  }

  public void bot(int k, int[] x) {
    x[0] = x[0] + k;
  }

  public void mid(int n, int[] x) {
    if (n > 0) bot(2*n, x);
    else x[0] = x[0] + n;
  }

  public int top(int x) {
    int[] spot = new int[1];
    mid(x+4, spot);
    return spot[0];
  }

}
