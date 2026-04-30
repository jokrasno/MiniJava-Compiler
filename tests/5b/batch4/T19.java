// for loop, 4-nested

class Main extends Lib {
    public void main() {
	for (int i = 'a'; i < 'd'; i++) {
	    for (int j = 'a'; j < 'd'; j++) {
		for (int k = 'a'; k < 'd'; k++) {
		    for (int n = 'a'; n < 'd'; n++) {
			super.printInt(i);super.printStr(" ");
			super.printInt(j);super.printStr(" ");
			super.printInt(k);super.printStr(" ");
			super.printInt(n);super.printStr("\n");
		    }
		}
	    }
	}
    }
}
