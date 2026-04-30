// garbage-collection test

class Main extends Lib {
	int seed;
	public void main() {
		TesterList myList = null;
		seed = 27465;
		for (int i = 0; i < 500; i++) {
			myList = new TesterList().init(nextRandom(), myList);
		}
		
		for (int i = 2; i < 362; i=i+29) {
			printInt(i);
			printStr(" ...\n");
			for (int j = 27; j < 382; j=j+37) {
				myList = churn(i, j, myList);
			}
		}
		
		
		int itemsOnLine = 0;
		for (TesterList p = myList; p != null; p=p.next) {
			printInt(p.val);
			if (itemsOnLine%20 == 19) {
				printStr("\n");
			}
			else {
				printStr(" ");
			}
			itemsOnLine++;
		}
		printStr("\n");
	}
	public int nextRandom() {
		seed = 27423 + (seed*18347)/37;
		int rtnVal = seed % 100;
		if (rtnVal < 0) {
			rtnVal = rtnVal + 100;
		}
		return rtnVal;
	}
	public TesterList churn(int interval, int count, TesterList orig) {
		TesterList rtnVal = orig;
		if (orig == null) {
			// skip work if null
		}
		else {
			// find end of list; make circular
			TesterList p = orig;
			int length = 0;
			for (; p.next != null; p=p.next) {
				length++;
			}
			p.next = orig;
			
			// do 'count' replacements
			for (int i = 0; i < count; i++) {
				// skip interval-1 slots; do replacement
				rtnVal = skip(interval-1, rtnVal);
				TesterList newOne =
					new TesterList().init(nextRandom(), rtnVal.next.next);
				rtnVal.next = newOne;
				int[] dummy = new int[100]; // to gobble some memory
			}
			
			// re-break the list
			TesterList temp = rtnVal.next;
			rtnVal.next = null;
			rtnVal = temp;
		}
		return rtnVal;
	}
	// assumes circular list; skips n elements
	public TesterList skip(int n, TesterList list) {
		for (int i = 0; i < n; i++) {
			list = list.next;
		}
		return list;
	}
}

class TesterList {
	int val;
	TesterList next;
	public TesterList init(int val, TesterList next) {
		this.val = val;
		this.next = next;
		return this;
	}
	
}

