// cast tests

class Main extends Lib {
  public void main() {
      C3 var3 = null;
      C1 abc11 = new C1();
      var3 = (C3)abc11;//error
      printStr("Should not get here\n");
  }
}

class C2 extends C1 {
}

class C1 {
}

class C3 extends C2 {
}
