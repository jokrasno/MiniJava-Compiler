// Seive of Eratosthenes to compute prime numbers <= 10000
  /* adapated from Appel/Tiger */

class Main {
	
	Lib lib;
	
	public void main() {
		lib = new Lib();
		
		/* our seive, which contains a "boolean" value integer in our range.  A
		 false value means that we have not yet found a factor for the number */
		boolean[] seive = new boolean[count()+1];
		
		/* our starting point for prime-searching */  
		int i = 2;
		
		/* increment i up to the square root of our limit */
		while (i*i <= count()) {
			/* no factors have been found: we have a prime number */
			if 	(!seive[i]) {
				/* set all multiples of this prime number to 'true' */
				for (int j = 2; j*i <= count(); j++) {
					seive[j*i] = true;
				}
			}
			/* increment index-variable */
			i++;
		}
		
		/* print the prime numbers we have found */
		for (i = 2; i <= count(); i++) {
			if (!seive[i]) {
				lib.printInt(i);
				lib.printStr(nl(" is a prime number."));
			}
		}
	}
	
	
	/* function that tells how far to compute */
	public int count() {
		return 10000;
		
	}
	
	/* function to append a newline onto the end of a string */
	public String nl(String str) {
		return str.concat("\n");
	}
}
