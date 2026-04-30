// linked list (of int) methods; conversion from array

class Main extends Lib {

  Lib lib;

  public void main() {
    int[] myArray = new int[12];
    myArray[0] = 12;
    myArray[1] = 84;
    myArray[2] = -283;
    myArray[3] = 37;
    myArray[4] = 83;
    myArray[5] = 22046;
    myArray[6] = 18;
    myArray[8] = 2212;
    myArray[9] = 11182;
    myArray[11] = 9;
    List07 lst = arrayToList(myArray);
    printIntList(lst);
    reversePrintIntList(lst);
    super.printInt(length(lst)); super.printStr("\n");
    printIntList(nTail(lst,3));
    reversePrintIntList(nTail(lst,7));
    printIntList(nTail(lst,1000));
    printIntList(nTail(lst,0));
  }

  public int length(List07 lst) {
    int rtnVal = 0;
    if (lst != null) rtnVal = 1 + length(lst.next);
    return rtnVal;
  }

  public List07 nTail(List07 lst, int n) {
    List07 rtnVal = null;
    if (lst == null || n == 0) {
      rtnVal = lst;
    }
    else {
      rtnVal =  nTail(lst.next, n-1);
    }
    return rtnVal;
  }

  public void printIntList(List07 lst) {
    helpPrintIntList(lst);
    super.printStr("\n");
  }

  public void helpPrintIntList(List07 lst) {
    if (lst != null) {
      super.printInt(lst.val);
      super.printStr(" ");
      helpPrintIntList(lst.next);
    }
  }

  public void reversePrintIntList(List07 lst) {
    helpReversePrintIntList(lst);
    super.printStr("\n");
  }

  public void helpReversePrintIntList(List07 lst) {
    if (lst != null) {
      helpReversePrintIntList(lst.next);
      super.printInt(lst.val);
      super.printStr(" ");
    }    
  }


  public List07 arrayToList(int[] arr) {
    List07 rtnVal = null;
    if (arr != null) {
      int idx = arr.length-1;
      while (idx >= 0) {
      	List07 temp = rtnVal;
        rtnVal = new List07();
        rtnVal.val = arr[idx];
        rtnVal.next = temp;
        idx--;
      }
    }
    return rtnVal;
  }
}

class List07 {
  int val;
  List07 next;
}
