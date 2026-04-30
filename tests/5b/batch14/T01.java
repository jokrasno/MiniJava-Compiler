// cast tests

class Main extends Lib {
  public void main() {
      C1 var1 = null;
      C2 var2 = null;
      C1 abc12 = new C2();
      C2 abc22 = new C2();
      var1 = (C1)abc12;
      var1 = (C2)abc12;
      var2 = (C2)abc12;
      var1 = (C1)abc22;
      var1 = (C2)abc22;
      var2 = (C2)abc22;
      printStr("Jumbles of Crumbles!\n");
  }
}

class C2 extends C1 {
}

class C1 {
}
