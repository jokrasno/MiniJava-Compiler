//  class Main { // class Test767 {
//   public void main() {
//      new Test767a().exec();
//    }
//  }
//  class Test767a {
//    public void exec() {
//    }
//   }
//   class Test767b extends Test767a {
//    public void testIt() {
//       int[] x = new int[this];
//    }
//   }
//   class Test767c extends Test767b {
//   }

// class Main { // class Test33 {
//    public void main() {
// 	new Test33a().exec();
//     }
// }
// class Test33a {
//     public void exec() {
//     }
//     public int foo() {
//         int[] x = null;
// 	return x[-10];
//     }
// }


//  class Main { // class Test770 {
//   public void main() {
//      new Test770a().exec();
//    }
//  }
//  class Test770a {
//    public void exec() {
//    }
//   }
//   class Test770b extends Test770a {
//    public void testIt() {
//       int[] x = new int[voidMethod()];
//    }
//   }
//   class Test770c extends Test770b {
//   }

//  class Main { // class Test104 {
//    public void main() {
//      new Test104a().exec();
//    }
//  }
//  class Test104a {
//    public void exec() {
//    }
//   }
//   class Test104b extends Test104a {
//    public void voidMethod() {}
//    public   int testMethod() {
//     int xx = 5;
//     return   this;
//    }
//   }
//   class Test104c extends Test104b {
//   }

//  class Main { // class Test161 {
//   public void main() {
//      new Test161a().exec();
//    }
//  }
//  class Test161a {
//    public void exec() {
//       boolean x = !this;
//    }
//    public void voidMethod() {}
//  }

// class Main {
//     public void main() {
// 	new Error39a().exec();
//     }
// }
// class Error39a extends Lib {
//     public void exec() {
//     }
//     public void printStr(String x, int k) {
//     }
// }

//  class Main { // class Test167 {
//   public void main() {
//      new Test167a().exec();
//    }
//  }
//  class Test167a {
//    public void exec() {
//       boolean x = this.length;
//    }
//    public void voidMethod() {}
//  }

 class Main { // class Test709 {
  public void main() {
     new Test709a().exec();
   }
 }
 class Test709a {
   public void exec() {
   }
  }
  class Test709b extends Test709a {
   public void testIt() {
       boolean val = true; val = voidMethod();
   }
  }
 class Test709c extends Test709b {
 }