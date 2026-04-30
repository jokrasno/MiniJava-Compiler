// array<->string conversion

class Main {
	
	Lib lib;
	int buffer;
	
	public void main() {
		lib = new Lib();
		String myString = "This is a FUNNY thing.";
		int[] myArray = stringToIntArray(myString);
		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i] >= 'A' && myArray[i] <= 'Z')
				myArray[i] = myArray[i] + ('a')-'A';
		}
		lib.printStr(intArrayToString(myArray));
		lib.printStr("\n");
	}
	
	public String helper(int first, int last, int[] arr) {
		int size = last-first+1;
		String rtnVal = "";
		if (size == 0) rtnVal = "";
		else if (size == 1) {
			int val = arr[first];
			if (val < 0 || val > 255) val = 0;
			rtnVal = lib.intToChar(val);
		}
		else {
			int split = first + size/2;
			rtnVal =
				helper(first,split-1,arr).concat(
						helper(split,last,arr));
		}
		return rtnVal;
	}
	  
  public String intArrayToString(int[] arr) {
    return helper(0, arr.length-1, arr);
  }

	
  public int[] stringToIntArray(String str) {
    int limit = str.length();
    int[] rtnVal = new int[limit];
    for (int i = 0; i < limit; i++) {
      rtnVal[i] = str.substring(i,i+1).charAt(0);
    }
    return rtnVal;
  }
}
