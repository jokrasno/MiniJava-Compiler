// switch statement--switch-expression not an int

class Main {
    public void main() {
	int x = 4;
	switch (true) {
	case x:
	    {}
	    break;
	}
	switch (null) {
	case x:
	    {}
	    break;
	}
	switch ("Fred") {
	case x:
	    {}
	    break;
	}
    }
}