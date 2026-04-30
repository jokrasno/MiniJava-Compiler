// instanceof tests

class Main extends Lib {
  public void main() {
      C1 abc11 = new C1();
      C1 abc12 = new C2();
      C1 abc13 = new C3();
      C1 abc1n = null;
      C2 abc22 = new C2();
      C2 abc23 = new C3();
      C2 abc2n = null;
      C3 abc33 = new C3();
      C3 abc3n = null;
      printBool(abc11 instanceof C1); printStr(" ");
      printBool(abc11 instanceof C2); printStr(" ");
      printBool(abc11 instanceof C3);
      printStr("\n");
      printBool(abc12 instanceof C1); printStr(" ");
      printBool(abc12 instanceof C2); printStr(" ");
      printBool(abc12 instanceof C3);
      printStr("\n");
      printBool(abc13 instanceof C1); printStr(" ");
      printBool(abc13 instanceof C2); printStr(" ");
      printBool(abc13 instanceof C3);
      printStr("\n");
      printBool(abc1n instanceof C1); printStr(" ");
      printBool(abc1n instanceof C2); printStr(" ");
      printBool(abc1n instanceof C3);
      printStr("\n");
      printBool(abc22 instanceof C1); printStr(" ");
      printBool(abc22 instanceof C2); printStr(" ");
      printBool(abc22 instanceof C3);
      printStr("\n");
      printBool(abc23 instanceof C1); printStr(" ");
      printBool(abc23 instanceof C2); printStr(" ");
      printBool(abc23 instanceof C3);
      printStr("\n");
      printBool(abc2n instanceof C1); printStr(" ");
      printBool(abc2n instanceof C2); printStr(" ");
      printBool(abc2n instanceof C3);
      printStr("\n");
      printBool(abc33 instanceof C1); printStr(" ");
      printBool(abc33 instanceof C2); printStr(" ");
      printBool(abc33 instanceof C3);
      printStr("\n");
      printBool(abc3n instanceof C1); printStr(" ");
      printBool(abc3n instanceof C2); printStr(" ");
      printBool(abc3n instanceof C3);
      printStr("\n");


  }
}

class C2 extends C1 {
}

class C1 {
}

class C3 extends C2 {
}
