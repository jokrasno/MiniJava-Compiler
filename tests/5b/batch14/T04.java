// cast tests -- null

class Main extends Lib {
  public void main() {
      C1 null1 = null;
      C3 null3 = (C3)null1;
      printStr("We just cast null!\n");
      null3 = (C3)null;
      printStr("We just cast null again!\n");
      
  }
}

class C2 extends C1 {
}

class C1 {
}

class C3 extends C2 {
}
