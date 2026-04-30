// nested for loop with ints, strings

class Main extends Lib {
  public void main() {
    String x = "X";
    String blank = " ";
    for (int v = 1; v <= 20; v++)
      for (int h = 1; h <= 20; h++)
        if ((v+h)/2*2 == v+h)
          super.printStr(x);
        else
          super.printStr(blank);
  }
}
