// garbage-collection test

class Main extends Lib {
	int seed;
	public void main() {
		AbsList list = null;
		seed = 798764;
		createGarbage(1);
		for (int i = 0; i < 2; i++) {
			list = withMoreAdded(list);
		}
		list = new StringArrayElement().init(1, "abc", 0, list);
		createGarbage(10000);
		printList(list);
	}
	public AbsList withMoreAdded(AbsList lst) {
		lst = new IntArrayListElem().
			init(newPosSeed()%15, newSeed(), 1+newPosSeed()%10000, lst);
		lst = new StringArrayElement().
			init(newPosSeed()%9, randomString(), newPosSeed()%10000, lst);
		lst = new CombinedElement().
			init(newSeed(), randomString(), lst);
		return lst;
	}
	public void createGarbage(int count) {
		for (int i = 0; i < count; i++) {
			withMoreAdded(null);
		}
	}
	public int newPosSeed() {
		int rtnVal = newSeed();
		if (rtnVal < 0) {
			rtnVal = -rtnVal;
		}
		if (rtnVal < 0) {
			rtnVal = 0;
		}
		return rtnVal;
	}
	public int newSeed() {
		seed = seed * 378976223;
		return seed;
	}
	public String randomString() {
		int genner = newPosSeed();
		String rtnVal = "";
		while (genner > 0) {
			int mod = genner % 26;
			genner = genner / 26;
			rtnVal = rtnVal.concat(intToChar('a'+mod));
		}
		return rtnVal;
	}
	public void printList(AbsList list) {
		for (AbsList lst = list; lst != null; lst = lst.next) {
			int intLimit = lst.intSize();
			int stringLimit = lst.stringSize();
			for (int j = 0; j < intLimit; j++) {
				printInt(lst.intAt(j));
				printStr("\n");
			}
			for (int j = 0; j < stringLimit; j++) {
				printStr(lst.stringAt(j));
				printStr("\n");
			}
			printStr("=======================================\n");
		}
	}
	
}

class AbsList {
	AbsList next;
	public int intAt(int idx) {
		return 0;
	}
	public String stringAt(int idx) {
		return "";
	}
	public int intSize() {
		return 0;
	}
	public int stringSize() {
		return 0;
	}
	public AbsList linkTo(AbsList other) {
		next = other;
		return this;
	}
}

class IntArrayListElem extends AbsList {
	int[] vals;
	public IntArrayListElem init(int size, int seed, int mod, AbsList nxt) {
		vals = new int[size];
		for (int i = 0; i < size; i++) {
			vals[i] = seed % mod;
			seed = seed * 26715341;
		}
		next = nxt;
		return this;
	}
	public int intAt(int idx) {
		int rtnVal = 0;
		if (idx < 0 || idx >= vals.length) {
			rtnVal = super.intAt(idx);
		}
		else {
			rtnVal = vals[idx];
		}
		return rtnVal;
	}
	public int intSize() {
		return vals.length;
	}
}
class StringArrayElement extends AbsList {
	String[] strArray;
	public StringArrayElement init(int size, String prefix, int start, AbsList nxt) {
		Lib lib = new Lib();
		strArray = new String[size];
		for (int i = 0; i < size; i++) {
			strArray[i] = prefix.concat(lib.intToString(i+start));
		}
		next = nxt;
		return this;
	}
	public String stringAt(int idx) {
		String rtnVal = "";
		if (idx >= 0 && idx < strArray.length) {
			rtnVal = strArray[idx];			
		}
		return rtnVal;
	}
	public int stringSize() {
		return strArray.length;
	}
}
class CombinedElement extends AbsList {
	String str;
	int num;

	public CombinedElement init(int numVal, String strVal, AbsList nxt) {
		str = strVal;
		num = numVal;
		next = nxt;
		return this;
	}
	public int intAt(int idx) {
		int rtnVal = 0;
		if (idx == 0) {
			rtnVal = num;
		}
		return rtnVal;
	}
	public String stringAt(int idx) {
		String rtnVal = "";
		if (idx == 0) {
			rtnVal = str;
		}
		return rtnVal;
	}
	public int intSize() {
		return 1;
	}
	public int stringSize() {
		return 1;
	}
}

