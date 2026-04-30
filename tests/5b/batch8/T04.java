// multi-level method calls, int+object parameters

class Main extends Lib {

  public void main() {
    level1Fcn(276);
  }

  public void level1Fcn(int x) {
    IntHolder4 xx = new IntHolder4();
    xx.val = x;
    super.printInt(xx.val); super.printStr("\n");
    level2Fcn(103, xx);
    super.printInt(xx.val); super.printStr("\n");
  }


  public void level2Fcn(int z, IntHolder4 x) {
    if (z >= 0)
      level3Fcn(z-23, x);
    else {
      super.printInt(x.val+z);super.printStr("\n");
      x.val = x.val + 63;
    }
  }

  public void level3Fcn(int q, IntHolder4 x) {
    level4Fcn(q+6, x);
  }

  public void level4Fcn(int r, IntHolder4 x) {
    level2Fcn(-44, x);
  }

}

class IntHolder4 {
  int val;
}
