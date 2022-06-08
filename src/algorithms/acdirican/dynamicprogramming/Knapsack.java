package algorithms.acdirican.dynamicprogramming;

public class Knapsack {
	
	static int mostValuableSet(int[] w, int[] c, int W, int n) {
		if (n<0 || W<=0) {
			return 0;
		}
		
		int a=0;
		if (W-w[n]>=0) {
			a= c[n] + mostValuableSet(w, c, W-w[n], n-1);
		}

		return Math.max(a, mostValuableSet(w,c,W,n-1));
	}
	
	 static int mostValuableSetDP(int[] w, int[] c, int W, int n)
	    {
	        // Create a 2D table to store results
	        // of subproblems
	        int f[][] = new int[n+1][W+1];
	 

	        for (int i = 0; i <= n; i++)
	            f[i][0] = 0;
	 
	        for (int j = 0; j <= W; j++)
	            f[0][j] = 0;
	 
	        // Calculate count of paths for other
	        // cells in bottom-up manner using
	        // the recursive solution
	        for (int i = 1; i <=n; i++) {
	            for (int j = 1; j <=W; j++) {
	            	if (j - w[i-1]>=0) {
	            		 f[i][j] = Math.max(f[i-1][j], c[i-1] + f[i-1][j-w[i-1]]);
	            	}
	            	else {
	            		f[i][j] = f[i-1][j];
	            	}
	            }
	        }
	        return f[n][W];
	    }
	public static void main(String[] args) {
		int W=30;
		int[] w = {1,6,3,5,2,4};
		int[] c = {5,3,4,2,6,7};
		
		System.out.println(mostValuableSet(w,c,W, w.length-1));
		System.out.println(mostValuableSetDP(w,c,W, w.length));
	}
}
