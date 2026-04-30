// nested blocks with variable declarations

class Main extends Lib {
  public void main() {
    int a = 3;
    int b = 73;
    int c = 0;
    {
      int d = a*a;
      c = d/2 + d/b;
    }
    int result = 0;
    {
      int k = 1;
      b = b + k;
      super.printInt(b); super.printStr(" ");
      {
        int d = b/2;
        int e = d*d;
        super.printInt(e); super.printStr(" ");
        {
          int f = a+b+c+d+e;
          result = f/3;
        }
      }
    }
    super.printInt(result);
    super.printStr("\n");
  }
}
