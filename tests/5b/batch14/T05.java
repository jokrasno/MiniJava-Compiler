// cast tests: null

class Main extends Lib {
  public void main() {
      C1 null1 = null;
      C2 abc2 = (C2)null1;
      printStr("WE JUST CAST NULL!\n");
  }
}

class C2 extends C1 {
}

class C1 {
}
