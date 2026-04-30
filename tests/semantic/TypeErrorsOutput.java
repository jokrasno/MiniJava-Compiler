// TypeErrors.java -- lots of errors
// This file has a number of type errors in it.

class Main {
	public void main(int k) { // wrong number of parameters for Main:main
		new Abc().start();
	}
}

class Abc {
	int xyz;
	String str;
	boolean b;
	Abc abc;
	public void start() {
		Def def = new Def();
		abc = new Abc();
		int[] z = new int[34];
		xyz.foo = 4; // error: ints do not have instance variables
		abc.jack = 5; // error: no instance variable named 'jack'
		abc.where(); // error: no method named 'where'
		b.start(6, "abc"); // error: cannot call a method on a boolean
		def.start(3, "xyz", 4); // error: wrong # parameters
		def.start(3, true); // error: parameter-type mismatch
		def[4] = 4; // error: attempt to index non-array value
		z[abc] = 34; // error: array-index must be int
		xyz = abc.length; // error: taking length of non-array
		def = new Abc(); // error: cannot assign to subclass value
		xyz = false; // error parameter-type mismatch
		b = start() == start(); // error: cannot compare void values
		b = 45; // error: assignment type incompatibility
		abc = new Def();
		def = null;
		abc = null;
		xyz = 4 + true; // error: '+' needs two ints
		xyz = b < false; // error: '<' needs two ints
		b = b && 3; // error: '&&' needs two booleans
		Def def2 = 45; // error: initializer incompatibilty
		Def def3 = new Abc(); // error: cannot initialize from superclass
		Abc abc2 = new Def();
		if (xyz) { // error: condition must be boolean
			xyz = 3;
		}
		while (def) { // error: condition must be boolean
			xyz = 4;
		}
		for (int i = 0; xyz+4; i++) { // error: condition must be boolean
			xyz = 5;
		}
		switch (def) { // error: non-int switch-expression
			case abc: // error: non-constant case label
			case 4:
			xyz = 57;
			break;
		        case xyz: // error:  non-constant case label
				xyz = 362;
				break;
		}
		switch (xyz) {
			case 4*5:
				xyz = 76;
				break;
			default:
				xyz = 273;
				break;
			case 21:
				case 26:
				xyz = 72;
				break;
			case 18+2: // error: duplicate case-label value
				xyz = 54;
				break;
			default: // error: two default labels
				xyz = 33;
				break;
		}
		switch (xyz) {
		xyz = 4; // error: switch-block starts with a non-label
		break;
		case 12:
			xyz = 726;
		case 16: // error: case label following non-break statement
			xyz = 272;
			break;
		case 26:
			xyz = 2646; // error: last statement in switch is not a break
		}
	}
	public int test() {
		return 4;
	}
}

class Def extends Abc {
        public void start(int n, String c) { // error: signature mismatch w/superclass
		Abc[] zz = new Abc[b]; // error: array-length must be int
		if (c instanceof Lib) { // unrelated "instanceof" operands
			4 = xyz; // error: attempt to assign to non-l-value
			Lib lib = (Lib)zz; // unrelated cast (array)
			lib = (Lib)abc; // error: unrelated cast
			Object o = (Object)"xyz";
			boolean b = "honk" instanceof Lib; // error: unrelated instanceof
			boolean b3 = null instanceof Lib;
			boolean b4 = abc[4] instanceof Lib; // error: indexing of non-array type
			Lib lib3 = (Lib)abc[4]; // error: indexing of non-array type
		}
	}
	public void test(int n) { // error: signature mismatch with superclass
	}
	public int myMethod() {
		return b; // error: return-exp mismatches with method return type
	}
}