// while loop (non-nested) with objects: linked list

class Main extends Lib {
  public void main() {
    Rec04 r1 = new Rec04();
    r1.s = "able";
    r1.next = null;
    Rec04 r2 = new Rec04();
    r2.s = "baker";
    r2.next = r1;
    Rec04 r3 = new Rec04();
    r3.s = "corner";
    r3.next = r2;

    Rec04 r4 = null;
    int count = 0;
    r4 = r3;
    count = 15;
    while (r4 != null) {
      super.printStr(r4.s); super.printStr(" ");
      count = count - 1;
      if (count <= 0) break;
      r4 = r4.next;
    }
    super.printStr("\n");

    r1.next = r2;
    r4 = r3;
    count = 15;
    while (r4 != null) {
      super.printStr(r4.s); super.printStr(" ");
      count = count - 1;
      if (count <= 0) break;
      r4 = r4.next;
    }
    super.printStr("\n");
  }
}

class Rec04 {
  String s;
  Rec04 next;
}
