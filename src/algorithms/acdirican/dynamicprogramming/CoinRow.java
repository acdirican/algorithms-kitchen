package algorithms.acdirican.dynamicprogramming;

public class CoinRow {
	
	public static int max(int a, int b) {
		if(a > b) {
			return a;
		}
		return b;
	}

	public static int coin_row_dp(int[] C, int n) {

		  if(n==1) return C[0];
		  if(n==2) return max(C[0],C[1]);

		  int[] F =  new int[n+1];
		  int i;
		  F[0] = 0; 
		  F[1] = C[0];

		  for(i = 2 ; i <= n ; i++) {
		    F[i] = max(C[i-1] + F[i-2], F[i-1]);
		  }
		  return F[n];
		  
	}
	
	/*
	 * F( n ) = max{Cn + F(n-2), F(n 1)} for n > 1,
	 * 
	 * F(0) = 0, F(1)=C₁
	 */
	public static int coin_row_rec(int[] C, int n) {
		  if(n==0) return 0;
		  if(n==1) return C[0];
		  
		

		  return  max(C[n-1] + coin_row_rec(C, n-2), coin_row_rec(C, n-1));
		  
	}
	
	public static void main(String[] args) {
		int[] C = {5, 1, 2, 10, 6, 2};
		System.out.println(coin_row_dp(C, C.length));
		System.out.println(coin_row_rec(C, C.length));
	}
}
