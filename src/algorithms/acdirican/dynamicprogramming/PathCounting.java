package algorithms.acdirican.dynamicprogramming;

public class PathCounting {
	
	static int numberOfPath(int m, int n) {
		
		if (m < 1 || n < 1) {
			return 0;
		}
		
		if (m == 1 && n == 1) {
			return 1;
		}

		return numberOfPath(m-1, n) + numberOfPath(m, n-1);
	}
	
	// Returns count of possible paths to reach
    // cell at row number m and column number n from
    // the topmost leftmost cell (cell at 1, 1)
    static int numberOfPathsDP(int m, int n)
    {
        // Create a 2D table to store results
        // of subproblems
        int count[][] = new int[m][n];
 
        // Count of paths to reach any cell in
        // first column is 1
        for (int i = 0; i < m; i++)
            count[i][0] = 1;
 
        // Count of paths to reach any cell in
        // first column is 1
        for (int j = 0; j < n; j++)
            count[0][j] = 1;
 
        // Calculate count of paths for other
        // cells in bottom-up manner using
        // the recursive solution
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
 
                // By uncommenting the last part the
                // code calculates the total possible paths
                // if the diagonal Movements are allowed
                count[i][j] = count[i - 1][j] + count[i][j - 1]; //+ count[i-1][j-1];
        }
        return count[m - 1][n - 1];
    }
    
    // Returns count of possible paths to reach
    // cell at row number m and column number n from
    // the topmost leftmost cell (cell at 1, 1)
    static int numberOfPathsDPMeme(int n, int m, int DP[][])
    {
 
        if (n == 1 || m == 1)
            return DP[n][m] = 1;
 
        // Add the element in the DP table
        // If it is was not computed before
        if (DP[n][m] == 0)
            DP[n][m] = numberOfPathsDPMeme(n - 1, m, DP)
                       + numberOfPathsDPMeme(n, m - 1, DP);
 
        return DP[n][m];
    }
    
	public static void main(String[] args) {
		System.out.println(numberOfPath(3, 3));
		System.out.println(numberOfPathsDP(3,3));
		
		// Create an empty 2D table
        int DP[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                DP[i][j] = 0;
            }
        }
 
        System.out.println(numberOfPathsDPMeme(3, 4, DP));
	}
}
