// call optimization where method not overridden

class Main extends Lib {
    public void main() {
	Vehicle v = new Vehicle();
	Car c = new Car();
	Honda h = new Honda();
	int sum = 0;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    sum = sum + v.price();  // cannot be optimized
	    sum = sum + c.price();  // can be optimized
	    sum = sum + h.price();  // can not optimized
	}
	super.printInt(sum);
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
class Honda extends Car {
}
