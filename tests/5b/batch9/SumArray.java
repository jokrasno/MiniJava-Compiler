// sums the elements in an array

class Main extends Lib {
	public int numSum(int[] anArray) {
		int sum = 0;
		for (int i = 0; i < anArray.length; i++)
			sum = sum + anArray[i];
		return sum;
	}
	public void readArray(int[] anArray) {
		for (int i = 0; i < anArray.length; i++)
			anArray[i] = super.readInt();
	}
	public void main() {
		int[] myArray = new int[100];
		readArray(myArray);
		printInt(numSum(myArray));
	}
}
