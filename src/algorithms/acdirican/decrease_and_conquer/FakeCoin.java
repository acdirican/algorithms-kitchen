package algorithms.acdirican.decrease_and_conquer;

/*
 *  There are n identically looking coins one of which is fake.
	There is a balance scale but there are no weights; the scale can
	tell whether two sets of coins weigh the same and, if not, which
	of the two sets is heavier (but not by how much). Design an
	efficient algorithm for detecting the fake coin. Assume that
	the fake coin is known to be lighter than the genuine ones.
 */

public class FakeCoin {
	
	/**
	 * By searching
	 * @param coins
	 * @return
	 */
	public static int bruteForce(int[] coins) {
		int fake = 0;
		for (int i = 1; i < coins.length; i++) {
			if (coins[i]<coins[fake]) {
				fake = i;
			}	
		}
		return fake;
	}
	
	
	public static int decreaseByFactor2(int[] coins) {
		return decreaseByFactor2Helper(coins,0,coins.length-1);
	}

	public static int scale(int[] coins, int l, int h) {
		int n = h - l + 1;
		int mid = (l+h)/2;
		
		int b = mid;
		if (n%2 != 0) {
			b--;
		}
			
		int balanced = 0;
		for (int i = l; i <b; i++) {
			int r =b + i + 1;
			if (coins[i] < coins[r]) {
				balanced = 1;
				break;
			}
			else if (coins[l] > coins[r]){
				balanced = 2;
				break;
			}
			
		}
		
		if (balanced != 0) {
			return balanced;
		}
		else {
			return coins[mid]<coins[l] ? 3 : 0;
		}
	}
	
	public static int decreaseByFactor2Helper(int[] coins, int l, int h) {
		if (l>h) {
			return -1;
		}
		
		if (l == h) {
			return l;
		}
		
		
		
		int mid = (h+l)/2;
		int balance = scale(coins, l, h);
		System.out.println(l + " " + h + " b->" + balance);
		if (balance == 0) {
			return 0;
		}
		else if (balance == 1) {
			return decreaseByFactor2Helper(coins, l, mid) ;
		}
		else if (balance == 2) {
			return decreaseByFactor2Helper(coins, mid + 1, h); 
		}
		
		return mid;
	}
	
	private static  int decreaseByFactor2(int[] coins, int l, int h) {
		
		if (h-l<1 || l>h) {
			return -1;
		}
		
		if (h-l==1) {
			
			if (coins[h]<coins[l]) {
				return h; 
			}
			else if (coins[l]<coins[h]){
				return l;
			}
			return -1;
		}
		
		int mid = (l+h)/2;
	
		int f1 =  decreaseByFactor2(coins, l, mid);
		if (f1 != -1) {
			return f1;
		}
		int f2 =  decreaseByFactor2(coins, mid+1, h);
		if (f2!=-1) {
			return f2;
		}
		if (coins[mid]<coins[l]) {
			return mid;
		}
		else if (coins[l]<coins[h]) {
			return l;
		}
		else if (coins[h]<coins[mid]) {
			return h;
		}
		return -1;
	}
	
	public static int decreaseByFactor3(int[] coins) {
		return decreaseByFactor3(coins,0,coins.length-1);
	}
	
	private static  int decreaseByFactor3(int[] coins, int l, int h) {
		if (h-l<2 || l>h) {
			if (coins[h]<coins[l]) {
				return h; 
			}
			else if (coins[l]<coins[h]){
				return l;
			}
			return -1;
		}
		
		int mid = (l+h)/2;

		if (h-l==2) {
			if (coins[h]<coins[mid]) {
				return h; 
			}
			else if (coins[l]<coins[mid]){
				return l;
			}
			else if (coins[mid]<coins[h]){
				return mid;
			}
			return -1;
		}
		
		
		int p = (l+h)/3;
		int f1 =  decreaseByFactor3(coins, l, p);
		if (f1 != -1) {
			return f1;
		}
		
		int f2 =  decreaseByFactor3(coins, p+1, 2*p);
		if (f2!=-1) {
			return f2;
		}
		
		int f3 =  decreaseByFactor3(coins, 2*p+1, h);
		if (f3!=-1) {
			return f2;
		}
		
		
		if (coins[mid]<coins[l]) {
			return mid;
		}
		else if (coins[l]<coins[h]) {
			return l;
		}
		else if (coins[h]<coins[mid]) {
			return h;
		}else if (coins[p]<coins[l]) {
			return mid;
		}
		else if (coins[p+1]<coins[h]) {
			return l;
		}
		else if (coins[2*p]<coins[mid]) {
			return h;
		}else if (coins[2*p+1]<coins[mid]) {
			return h;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] coins = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2};
		System.out.println(bruteForce(coins));
		System.out.println(decreaseByFactor2(coins));
	//	System.out.println(decreaseByFactor3(coins));
	}

}
