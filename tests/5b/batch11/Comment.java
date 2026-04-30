// int variables, printSTr, printInt

class Main {
  public void main() {
    Lib lib = new Lib();
    int elsee = 54321;
    int e = 7654;
    /* 
     // this is in a multiline comment
    lib.printStr("This should not print!\n"); 
     */
    // /* this is a single-line comment
    lib.printStr("This should indeed print!\n");
    /* this begins a multi-line comment
     this ends the multiline comment 
     */
    // the following is a short comment:
    /**/
    lib.printStr("This should also print!!\n");
    /***/
    lib.printStr("And so should this print this should also print!!!\n");
    int x = 5/*/*/*/*/*/345; // multiplies 5 and 345
    lib.printInt(x); lib.printStr("\n");
    // Test to ensure that /*-style comments do not nest
    /*  /*  /*  /*  /*  /*  /*
     */
     lib.printStr("Another thing that should print!!!!\n");
     // */  */  */  */  */  */
     if (x < 100) {
       lib.printStr("Hello, George. ");
     }
     else/*......*/e = 1234; // to make sure that 'else' and 'e' are separate tokens
     lib.printInt(elsee); lib.printStr("\n");
     lib.printInt(e); lib.printStr("\n");
     if (x < 100) {
       lib.printStr("Hello, George. ");
     }
     else e = 3344; // to make sure that 'else' and 'e' are separate tokens
     lib.printInt(elsee); lib.printStr("\n");
     lib.printInt(e); lib.printStr("\n");
     if (x < 100) {
       lib.printStr("Hello, George. ");
     }
     else  e = 6621; // to make sure that 'else' and 'e' are separate tokens
     lib.printInt(elsee); lib.printStr("\n");
     lib.printInt(e); lib.printStr("\n");
     if (x < 100) {
       lib.printStr("Hello, George. ");
     }
     else//
e = 634; // to make sure that 'else' and 'e' are separate tokens
     lib.printInt(elsee); lib.printStr("\n");
     lib.printInt(e); lib.printStr("\n");
  }
}
