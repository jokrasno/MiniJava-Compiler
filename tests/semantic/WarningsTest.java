////////////////////////////////////////////////////////////////////////////////
// COMPREHENSIVE WARNING TEST FILE FOR MINIJAVA TYPE CHECKING
//
// This file tests all possible warnings in the compiler.
// All constructs here should compile successfully but generate warnings.
//
// Warnings tested:
// - Negative array size (e.g., new int[-5])
// - Negative array index (e.g., arr[-3])
// - Unreachable code (Sem5Visitor - not yet implemented)
////////////////////////////////////////////////////////////////////////////////

class Main {
    public void main() {
        //////////////////////////////////////////////////////////////////////////
        // NEGATIVE ARRAY SIZE WARNINGS
        //////////////////////////////////////////////////////////////////////////

        // Warning: negative size with single dimension
        int[] neg1 = new int[-1];
        int[] neg2 = new int[-5];
        int[] neg3 = new int[-100];

        // Warning: negative size with multi-dimensional arrays
        int[][] neg2d1 = new int[-10][];
        int[][] neg2d2 = new int[-999][];

        // Warning: negative size for multi-dimensional
        int[][][] neg3d = new int[-5][][];

        // Warning: negative size for object arrays
        String[] negStr = new String[-7];
        Main[] negObj = new Main[-3];

        //////////////////////////////////////////////////////////////////////////
        // NEGATIVE ARRAY INDEX WARNINGS
        //////////////////////////////////////////////////////////////////////////

        // Create arrays for testing
        int[] arr = new int[10];
        int[][] arr2d = new int[5][];
        String[] strArr = new String[20];

        // Warning: negative single index
        int x1 = arr[-1];
        int x2 = arr[-5];
        int x3 = arr[-100];

        // Warning: negative index in assignment
        arr[-2] = 5;
        arr[-10] = 100;

        // Warning: negative first index for 2D array
        int[][] temp1 = new int[5][];
        int y1 = temp1[-1][0];

        // Warning: negative index for object arrays
        String s = strArr[-3];
        strArr[-7] = "test";

        //////////////////////////////////////////////////////////////////////////
        // VALID CASES (should NOT generate warnings)
        //////////////////////////////////////////////////////////////////////////

        // Zero is valid (not negative)
        int[] empty = new int[0];
        int zeroIdx = empty[0];

        // Positive indices are valid
        int[] normalArr = new int[100];
        int valid1 = normalArr[0];
        int valid2 = normalArr[99];

        // Large positive sizes are valid
        int[] largeArr = new int[1000000];
        int largeIdx = largeArr[500000];

        //////////////////////////////////////////////////////////////////////////
        // EDGE CASES
        //////////////////////////////////////////////////////////////////////////

        // Very large negative numbers
        int[] hugeNeg = new int[-999999];
        int hugeIdx = arr[-999999];

        // Multiple negative operations
        int[] doubleNeg = new int[-5];
        int doubleNegIdx = arr[-5];

        //////////////////////////////////////////////////////////////////////////
        // END OF WARNING TESTS
        //////////////////////////////////////////////////////////////////////////
    }
}
