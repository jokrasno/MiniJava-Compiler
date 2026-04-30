// A program to solve the 8-queens problem, adapted from Appel/Tiger

class Main {
	
	Lib lib;
	int N;
	int[] row;
	int[] col;
	int[] diag1;
	int[] diag2;
	
	public void main() {
		lib = new Lib();
		N = 8;
		row = new int[ N ];
		col = new int [ N ];
		diag1 = new int [N+N-1];
		diag2 = new int [N+N-1];
		tryIt(0);
	}
	
	public void printboard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				String pVal = null;
				if (col[i] == j) pVal = " Q"; else pVal = " .";
				lib.printStr(pVal);
			}
			lib.printStr("\n");
		}
		lib.printStr("\n");
	}
		
	public void tryIt(int c){
		
		/*  for (int i= 0; i <= c; i++) lib.printStr("."); lib.print("\n");*/
		if (c==N) {
			printboard();
		}
		else {
			for (int r = 0; r < N; r++) {
				if (row[r]==0 && diag1[r+c]==0 && diag2[r+7-c]==0) {
					row[r]=1;
					diag1[r+c]=1;
					diag2[r+7-c]=1;
					col[c]=r;
					tryIt(c+1);
					row[r]=0;
					diag1[r+c]=0;
					diag2[r+7-c]=0;
				}
			}
		}
	}
}
