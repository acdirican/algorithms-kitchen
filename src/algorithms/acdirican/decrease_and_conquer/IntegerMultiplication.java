package algorithms.acdirican.decrease_and_conquer;

public class IntegerMultiplication {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int bruteForceIt(int a, int b) {
		int r = 0;
		for (int i = 1; i <=a; i++) {
			r += b;
		}
		return r;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int bruteForceRec(int a, int b) {
		if (b==0) {
			return 0;
		}
		return a + bruteForceIt(a, b-1);
	}
	
	
	/*
	    The problem: Compute the product of two positive integers
	    
		Can be solved by a decrease by half algorithm based on the following formulas.
		For even values of n
			a * b = b/2 * 2a
			
		For odd values of n
			n * m = * 2 m
			n * m = * 2 m + m if n > 1 and m if n = 1

	*/
	public static int russianPeasant(int a, int b) {
		if (b==0) {
			return 0;
		}
		return b%2 == 0
				? russianPeasant(2*a , b/2)
				: a + russianPeasant(2*a , (b-1)/2);
	}
	
	public static void main(String[] args) {
		System.out.println(bruteForceIt(20, 5));
		System.out.println(bruteForceRec(20, 5));
		System.out.println(russianPeasant(20, 5));
		
	}
}
