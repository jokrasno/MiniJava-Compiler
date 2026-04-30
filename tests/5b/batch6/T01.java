// recursive method (factorial)

class Main extends Lib {
  public void main() {
    super.printInt(factorial(6));
    super.printStr("\n");
  }

  public int factorial(int n) {
    int rtnVal = n;
    if (n > 0) {
      rtnVal = n*factorial(n-1);
    }
    return rtnVal;
  }
}
