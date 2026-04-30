// lazy and/or

class Main extends Lib {
  public void main() {
      if (true && trueMethod() && falseMethod() && trueMethod()) {
	  super.printInt(8722);
      }
      else {
	  super.printInt(2734);
      }
      if (falseMethod() && trueMethod() || falseMethod() || trueMethod()) {
	  super.printInt(764);
      }
      else {
	  super.printInt(4761);
      }
  }

    public boolean trueMethod() {
	super.printInt(4);
	return true;
    }

    public boolean falseMethod() {
	super.printInt(9);
	return false;
    }
	    
}

