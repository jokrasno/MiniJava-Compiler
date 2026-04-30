// string-compares with the empty string

class Main {

  Lib lib;


  public void main() {
    lib = new Lib();
    String[] sa = new String[5];
    lib.printStr("the size of sa is ");
    lib.printInt(sa.length);
    lib.printStr("\n");
    sa[0] = "b";
    sa[1] = "";
    sa[2] = "aba";
    sa[3] = "a";
    sa[4] = "aab";
    for (int i = 0; i < sa.length; i++) {
      for (int j = 0; j < sa.length; j++) {
        String greater = null;
        if (sa[i].compareTo(sa[j]) > 0) greater = ""; else greater = " not";
        String less = null;
        if (sa[i].compareTo(sa[j]) > 0) less = ""; else less = " not";
        String equal = null;
        if (sa[i].compareTo(sa[j]) > 0) equal = ""; else equal = " not";
        lib.printStr("\"");
        lib.printStr(sa[i]);
        lib.printStr("\" is");
        lib.printStr(greater);
        lib.printStr(" greater than \"");
        lib.printStr(sa[j]);
        lib.printStr("\"\n");

        lib.printStr("\"");
        lib.printStr(sa[i]);
        lib.printStr("\" is");
        lib.printStr(equal);
        lib.printStr(" equal to \"");
        lib.printStr(sa[j]);
        lib.printStr("\"\n");

        lib.printStr("\"");
        lib.printStr(sa[i]);
        lib.printStr("\" is");
        lib.printStr(less);
        lib.printStr(" less than \"");
        lib.printStr(sa[j]);
        lib.printStr("\"\n");
      }
    }
  }
}
