// unreachable code: stmt following true/eval-condition for (with break)
 //import lib.*;

 class Main { // class Test897 {
  public void main() {
     new Test897a().exec();
   }
 }
 class Test897a {
   public   void exec() {
     int a = 4;
       for(int j = 0; 6==6; ) /* note: reachable in Java */ {
          break;
     }
       foo();
   }
   public void foo() {}
 }