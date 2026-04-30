// list operation

class Main {
	
	Lib lib;
	
	public void main() {
		lib = new Lib();
		
		lib.printInt(plus(12,24)); lib.printStr(" ");
		IListS1 il = new IListS1().init(null,9002);
		il = new IListS1().init(il,78);
		il = new IListS1().init(il,34);
		il = new IListS1().init(il,22);
		il = new IListS1().init(il,2);
		
		lib.printInt(length(il)); lib.printStr(" ");
		lib.printInt(listSum(il)); lib.printStr(" ");
		il = truncate(il, 50);
		lib.printInt(length(il)); lib.printStr(" ");
		lib.printInt(listSum(il)); lib.printStr(" ");
		lib.printStr("\n");
	}
	
	
	public int plus(int a, int b) { return a+b; }
	public int length(IListS1 lst) {
		int rtnVal = 0;
		if (lst == null) rtnVal = 0;
		else rtnVal = 1 + length(lst.next);
		return rtnVal;
	}
	public int listSum(IListS1 lst) {
		int rtnVal = 0;
		if (lst == null) rtnVal = 0;
		else rtnVal = lst.val + listSum(lst.next);
		return rtnVal;
	}
	public IListS1 truncate(IListS1 lst, int hi) {
		IListS1 rtnVal = null;
		if (lst != null) {
			if (lst.val <= hi) {
				lst.next = truncate(lst.next,hi);
				rtnVal = lst;
			}
		}
		return rtnVal;
	}
	
	
}

class IListS1  {
	IListS1 next;
	int val;
	public IListS1 init(IListS1 il, int i) {
		next = il;
		val = i;
		return this;
	}
}
