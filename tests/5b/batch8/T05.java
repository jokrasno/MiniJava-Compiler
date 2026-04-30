// many-level method calls with ints/arrays

class Main extends Lib {

  public void main() {
    int[] theArray = fun0(27, 17);
    for (int i = 0; i < theArray.length; i++) {
      super.printInt(theArray[i]);
      super.printStr("\n");
    }
  }

  public void fun16(int n16, int count, Ints coll, int[] myArray) {
    coll.put(16,n16);
    if (count <= 0) {
      myArray[0] = coll.get(0);
      myArray[1] = coll.get(1);
      myArray[2] = coll.get(2);
      myArray[3] = coll.get(3);
      myArray[4] = coll.get(4);
      myArray[5] = coll.get(5);
      myArray[6] = coll.get(6);
      myArray[7] = coll.get(7);
      myArray[8] = coll.get(8);
      myArray[9] = coll.get(9);
      myArray[10] = coll.get(10);
      myArray[11] = coll.get(11);
      myArray[12] = coll.get(12);
      myArray[13] = coll.get(13);
      myArray[14] = coll.get(14);
      myArray[15] = coll.get(15);
    }
    else {
      fun16(coll.get(16)+2,count-1, coll, myArray);
    }
  }

  public void fun15(int n15, int count, Ints coll, int[] myArray) {
    coll.put(15,n15);
    if (count <= 0)
      fun16(coll.get(13)+coll.get(4),15, coll, myArray);
    else
      fun15(coll.get(15)+5, count-1, coll, myArray);
  }

  public void fun14(int n14, int count, Ints coll, int[] myArray) {
    coll.put(14,n14);
    if (count <= 0)
      fun15(coll.get(2)+coll.get(14)+3,6, coll, myArray);
    else
      fun14(coll.get(14)+3, count-1, coll, myArray);
  }

  public void fun13(int n13, int count, Ints coll, int[] myArray) {
    coll.put(13,n13);
    if (count <= 0)
      fun14(coll.get(13)-coll.get(0)+coll.get(5),7, coll, myArray);
    else
      fun13(coll.get(13)-2, count-1, coll, myArray);
  }

  public void fun12(int n12, int count, Ints coll, int[] myArray) {
    coll.put(12,n12);
    if (count <= 0)
      fun13(coll.get(5)+coll.get(6)+coll.get(7),2, coll, myArray);
    else
      fun12(coll.get(12)+11, count-1, coll, myArray);
  }

  public void fun11(int n11, int count, Ints coll, int[] myArray) {
    coll.put(11,n11);
    if (count <= 0)
      fun12(87-coll.get(9),18, coll, myArray);
    else
      fun11(coll.get(11)+7, count-1, coll, myArray);
  }

  public void fun10(int n10, int count, Ints coll, int[] myArray) {
    coll.put(10,n10);
    if (count <= 0)
      fun11(coll.get(10)+coll.get(1)+1,13, coll, myArray);
    else
      fun10(coll.get(10)+1, count-1, coll, myArray);
  }

  public void fun9(int n9, int count, Ints coll, int[] myArray) {
    coll.put(9,n9);
    if (count <= 0)
      fun10(coll.get(6)+coll.get(8),21, coll, myArray);
    else
      fun9(coll.get(9)-3, count-1, coll, myArray);
  }

  public void fun8(int n8, int count, Ints coll, int[] myArray) {
    coll.put(8,n8);
    if (count <= 0)
      fun9(coll.get(3)*5+coll.get(2)-81,10, coll, myArray);
    else
      fun8(coll.get(8)-7, count-1, coll, myArray);
  }

  public void fun7(int n7, int count, Ints coll, int[] myArray) {
    coll.put(7,n7);
    if (count <= 0)
      fun8(coll.get(7)+coll.get(6),5, coll, myArray);
    else
      fun7(coll.get(7)-1, count-1, coll, myArray);
  }

  public void fun6(int n6, int count, Ints coll, int[] myArray) {
    coll.put(6,n6);
    if (count <= 0)
      fun7(coll.get(3)+2*coll.get(2),4, coll, myArray);
    else
      fun6(coll.get(6)+13, count-1, coll, myArray);
  }

  public void fun5(int n5, int count, Ints coll, int[] myArray) {
    coll.put(5,n5);
    if (count <= 0)
      fun6(coll.get(0)+coll.get(5),9, coll, myArray);
    else
      fun5(coll.get(-11), count-1, coll, myArray);
  }

  public void fun4(int n4, int count, Ints coll, int[] myArray) {
    coll.put(4,n4);
    if (count <= 0)
      fun5(coll.get(3)-coll.get(1)+82,2, coll, myArray);
    else
      fun4(coll.get(4)+2, count-1, coll, myArray);
  }

  public void fun3(int n3, int count, Ints coll, int[] myArray) {
    coll.put(3,n3);
    if (count <= 0)
      fun4(coll.get(2)+coll.get(2)-7,8, coll, myArray);
    else
      fun3(coll.get(3)+17, count-1, coll, myArray);
  }

  public void fun2(int n2, int count, Ints coll, int[] myArray) {
    coll.put(2,n2);
    if (count <= 0)
      fun3(coll.get(1)*3+1,14, coll, myArray);
    else
      fun2(coll.get(2)+19, count-1, coll, myArray);
  }

  public void fun1(int n1, int count, Ints coll, int[] myArray) {
    coll.put(1,n1);
    if (count <= 0)
      fun2(coll.get(0)*11-n1*6,23, coll, myArray);
    else
      fun1(coll.get(1)+31, count-1, coll, myArray);
  }

  public int[] fun0(int n0, int count) {
    Ints coll = new Ints();
    coll.put(0,n0);
    int[] myArray = new int[16];
    if (count <= 0) {
      fun1(273-4*coll.get(0),9, coll, myArray);
    }
    else
      fun0(coll.get(0)+101, count-1);
    return myArray;
  }

}


class Ints {
  int val;
  Ints next;

  public int get(int idx) {
    int rtnVal = 0;
    if (idx >= 0 && next != null) {
      if (idx == 0) rtnVal = next.val;
      else rtnVal = next.get(idx-1);
    }
    return rtnVal;
  }

  public void put(int idx, int vall) {
    if (idx < 0) {
    }
    else {
      if (next == null) {
        next = new Ints();
      }
      if (idx == 0) next.val = vall;
      else next.put(idx-1, vall);
    }
  }
}

