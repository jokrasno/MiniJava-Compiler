// cast tests

class Main extends Lib {
  public void main() {
      C1 var1 = null;
      C2 var2 = null;
      C3 var3 = null;

      C1 abc13 = new C3().init3("one\n");
      C2 abc23 = new C3().init3("two\n");
      C3 abc33 = new C3().init3("three\n");
      
      var1 = (C1)abc13;
      printStr(var1.val);
      var1 = (C1)abc23;
      printStr(var1.val);
      var1 = (C1)abc33;
      printStr(var1.val);
      var1 = (C2)abc23;
      printStr(var1.val);
      var1 = (C2)abc33;
      printStr(var1.val);
      var1 = (C2)abc13;
      printStr(var1.val);
      var1 = (C3)abc33;
      printStr(var1.val);
      var1 = (C3)abc13;
      printStr(var1.val);
      var1 = (C3)abc23;
      printStr(var1.val);

      var2 = (C2)abc13;
      printStr(var2.val);
      printStr(var2.val);
      var2 = (C2)abc23;
      printStr(var2.val);
      var2 = (C3)abc23;
      printStr(var2.val);
      var2 = (C3)abc13;
      printStr(var2.val);
      var2 = (C3)abc33;
      printStr(var2.val);

      var3 = (C3)abc23;
      printStr(var3.val);
      var3 = (C3)abc13;
      printStr(var3.val);
      var3 = (C3)abc33;
      printStr(var3.val);
  }
}

class C2 extends C1 {
    public C2 init2(String s) {
	return (C2)super.init1(s);
    }
}

class C1 {
    String val;
    public C1 init1(String s) {
	val = s;
	return this;
    }
}

class C3 extends C2 {
    public C3 init3(String s) {
	return (C3)super.init1(s);
    }
}
