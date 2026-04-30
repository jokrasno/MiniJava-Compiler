// some simple linked list operations

class Main {
	public void main() {
		Lib lib = new Lib();
		IntList01 myList = null;
		for (int i = 1; i < 100; i = (i*20+2)/11) {
			myList = new IntList01().init(i, myList);
		}
		for (IntList01 p = myList; p != null; p = p.next) {
			lib.printInt(p.val);
			lib.printStr(" ");
		}
		lib.printStr("\n");
		for (IntList01 p = myList; p != null && p.next != null; p = p.next) {
			p.next = p.next.next;
		}
		for (IntList01 p = myList; p != null; p = p.next) {
			lib.printInt(p.val);
			lib.printStr(" ");
		}
		lib.printStr("\n");
	}
}

class IntList01 {
	int val;
	IntList01 next;
	public IntList01 init(int v, IntList01 nxt) {
		val = v;
		next = nxt;
		return this;
	}
}
