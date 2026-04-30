// cast and instanceof up-casting optimization

class Main extends Lib {
    public void main() {
	boolean b = true;
	boolean b2 = true;
	boolean b3 = true;
	Vehicle v = new Vehicle();
	Car c = new Car();
	Vehicle v1 = null;
	Vehicle v2 = null;
	Vehicle nullV = null;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    b = c instanceof Vehicle;
	    b2 = c instanceof Car;
	    b3 = nullV instanceof Vehicle;
	    v1 = (Vehicle)c;
	    v2 = (Vehicle)v;
	}
	super.printBool(b);
	super.printStr("\n");
	super.printBool(b2);
	super.printStr("\n");
	super.printBool(b3);
	super.printStr("\n");
	super.printInt(v1.price());
	super.printStr("\n");
	super.printInt(v2.price());
	super.printStr("\n");
    }
}

class Vehicle {
    public int price() {
	return 1234;
    }
}
class Car extends Vehicle {
    public int price() {
	return 4321;
    }
}
