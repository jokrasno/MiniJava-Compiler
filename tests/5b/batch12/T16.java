// Illegal cast

class Main extends Lib {
    public void main() {
	C1 var1 = null;
	C1 abc11 = new C1();
	var1 = (C2)abc11;//error
	printStr("should not get here\n");
    }
}

class C2 extends C1 {
}

class C1 {
}
