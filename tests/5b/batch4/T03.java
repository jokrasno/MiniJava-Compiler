// while loop (non-nested) with objects: linked list

class Main extends Lib {
  public void main() {
  Rec03 r1 = new Rec03();
  r1.s = "able";
  r1.next = null;
  Rec03 r2 = new Rec03();
  r2.s = "baker";
  r2.next = r1;
  Rec03 r3 = new Rec03();
  r3.s = "corner";
  r3.next = r2;
  Rec03 r4 = null;
  r4 = r3;
  while (r4 != null) {
    super.printStr(r4.s); super.printStr(" ");
    r4 = r4.next;
  }
  super.printStr("\n");
  }
}

class Rec03 {
  String s;
  Rec03 next;
}
