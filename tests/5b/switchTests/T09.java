// switch statement with lots of cases

class Main extends Lib {
    public void main() {
	int sum = 0;
	// repeat these so that the relative effect of the optimizations
	// is increased
	for (int i = 0; i < 2000; i++) {
	    switch (i*3) {
	    case 3*3: sum = sum - 23; break;
	    case 4*3: sum = sum * 5; break;
	    case 5*3:
	    case 6*3:
	    case 7*3:
	    case 92*3:
	    case 84*3:
	    case 76*3:
	    case 71:
	    case 99*3:
	    case 87*3: sum = sum + (i % 67); break;
	    case 11*3:
	    case 28:
	    case 1:
	    case 53*3: sum = sum * 2; break;
	    case 26*3:
	    case 91*3: sum = sum / 13; break;
	    case 27*3:
	    case 48*3:
	    case 98*3:
	    default: sum++; break;
	    case 19*3:
	    case 75*3:
	    case 16*3: sum = sum * 3; break;
	    case 85*3:
	    case 0*3:
	    case 1*3:
	    case 2*3:
	    case 8*3: sum = sum * 11; break;
	    case 22:
	    case 9*3:
	    case 88*3:
	    case 24*3:
	    case 73*3: sum = sum * 19; break;
	    }
	}
	super.printInt(sum);
	super.printStr("\n");
    }
}
