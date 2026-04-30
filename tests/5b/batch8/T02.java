// array-methods with array (and other) parameters

class Main extends Lib {

  public void main() {
    int[] arr1 = intervalArray(23,8,3);
    int[] arr2 = intervalArray(0,20,-1);
    printIntArray(arr1);  
    printIntArray(arr2);  
  }

  public void printIntArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      super.printInt(arr[i]);
      super.printStr(" ");
    }
    super.printStr("\n");
  }

  public int[] intervalArray(int start, int count, int delta) {
    int[] rtnVal = new int[count];
    for (int i = 0; i < count; i++) {
      setRtnVal(i, start, rtnVal);
      start = start + delta;
    }
    return rtnVal;
  }


  public void setRtnVal(int idx, int val, int[] rtnVal) {
    helpSetRtnVal(idx, val, rtnVal);
  }

  public void helpSetRtnVal(int j, int v, int[] rtnVal) {
    rtnVal[j] = v;
  }

}
