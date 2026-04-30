// assignment to parameter

class Main extends Lib {
    public void main(){
	int k = 4;
	int z = callMe(k);
	super.printInt(k);
	super.printInt(z);
    }
    public int callMe(int y) {
	super.printInt(y);
	y++;
	super.printInt(y);
	return y*y;
    }
}
