package algorithms.acdirican.dynamicprogramming;

public class CoinCollector {
	/*
	 * The recurrence:
		F( i , j ) = max{ i-1, j ), i , j-1)} + c ij for 1 ≤ i ≤ n , 1 ≤ j ≤ m
		where Cij = 1 if there is a coin in cell (i,j ), and Cij = 0 otherwise
		F(0, j) = 0 for 1 ≤ j ≤ m and F( i , 0) = 0 for 1 ≤ i ≤ n.
	 */
	static int collectRec(int[][] coins, int m, int n) {
		if (m<0 || n<0 || coins[m][n]==-1 ) {
			return 0;
		}
		
		int c= coins[m][n];
	
		return c + Math.max(collectRec(coins, m-1, n), collectRec(coins, m, n-1));
	}
	
	static int collectDP(int[][] coins) {
		
		int[][] f =  new int[coins.length + 1 ][coins[0].length + 1];
		for(int i=0;i<coins.length; i++) {
			f[i][0] = coins[i][0];
		}
		
		for(int j=0; j<coins[0].length; j++) {
			f[0][j] = coins[0][j];
		}
		
		for(int i=1;i<=coins.length; i++) {
			for  (int j=1; j<=coins[i-1].length; j++) {
				System.out.println("F" + i + "," + j + ")=F(" + (i-1) + "," + j + ") + F(" + i + "," + (j-1) + ")");
				f[i][j] = coins[i-1][j-1] + Math.max(f[i-1][j], f[i][j-1]);
			}
		}
		return f[coins.length][coins[0].length];
	}
	
	public static void main(String[] args) {
		int[][] coins = {
				{0,0,0,0,1,0},
				{0,1,0,0,0,0},
				{0,0,0,0,1,0},
				{0,0,1,0,0,0},
				{0,0,0,1,0,0},
				{1,0,0,0,0,0}
		};
		System.out.println(collectDP(coins));
		System.out.println(collectRec(coins, coins.length-1,coins[0].length-1));
		
	}
}
