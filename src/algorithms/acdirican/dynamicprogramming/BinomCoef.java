package algorithms.acdirican.dynamicprogramming;

//JAVA Code for Dynamic Programming |
//Set 9 (Binomial Coefficient)
import java.util.*;

class GFG {

	// Returns value of Binomial
	// Coefficient C(n, k)
	static int binomialCoeffRec(int n, int k)
	{

		// Base Cases
		if (k > n)
			return 0;
		if (k == 0 || k == n)
			return 1;

		// Recur
		return binomialCoeffRec(n - 1, k - 1)
			+ binomialCoeffRec(n - 1, k);
	}
	
	// Returns value of Binomial
    // Coefficient C(n, k)
    static int binomialCoeff(int n, int k)
    {
        int C[][] = new int[n + 1][k + 1];
        int i, j;
 
        // Calculate  value of Binomial
        // Coefficient in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;
 
                // Calculate value using
                // previously stored values
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
 
        return C[n][k];
    }
 
    // A utility function to return
    // minimum of two integers
    static int min(int a, int b) { return (a < b) ? a : b; }
 

	/* Driver program to test above function */
	public static void main(String[] args)
	{
		int n = 5, k = 2;
		System.out.printf("Value of C(%d, %d) is %d ", n, k,
						binomialCoeffRec(n, k));
	}
}

//This code is contributed by Arnav Kr. Mandal.
