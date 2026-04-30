// assignment: complex lvalue

class Main extends Lib {
    public void main(){
	T09Helper h1 = new T09Helper().init(4,88);
	h1.print();
	ident(h1).first = 72;
	h1.print();
	h1.me().second = 442;
	h1.print();
	ident(ident(ident(ident(h1)))).first = 145;
	h1.print();
	h1.me().me().me().me().me().me().me().me().second = 9876;
	h1.print();
    }
    public T09Helper ident(T09Helper h) {
	return h;
    }

}
class T09Helper extends Main {
    int first;
    int second;
    public T09Helper init(int first, int second) {
	this.first = first;
	this.second = second;
	return this;
    }
    public T09Helper me() {
	return this;
    }
    public void print() {
	super.printInt(first);
	super.printStr(" ");
	super.printInt(second);
	super.printStr("\n");
    }
}
