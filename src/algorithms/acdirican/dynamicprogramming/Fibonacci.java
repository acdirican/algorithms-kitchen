package algorithms.acdirican.dynamicprogramming;

import java.util.Arrays;

//Fibonacci Series using Recursion
public class Fibonacci {
	
	static int numberOfRecursiveCalls = 0; 
	/**
	 * Recursive solution
	 */
	static int withoutDP(int n) {
		numberOfRecursiveCalls++;
		if (n <= 1)
			return n;
		return withoutDP(n - 1) + withoutDP(n - 2);
	}

	/**
	 * Using a memory function
	 */
	static int withMemoryFunction(int n, int[] mem) {
		numberOfRecursiveCalls++;
		if (n <= 1)
			return n;
		
		if (mem[n]==-1) {
			mem[n] = withMemoryFunction(n - 1, mem) + withMemoryFunction(n - 2, mem);
		}
		
		return mem[n];
	}
	
	/**
	 * Tow-down iterative solution using DP
	 * 
	 * @param n
	 * @return
	 */
	static int withDP(int n) {
		/* Declare an array to store Fibonacci numbers. */
		int f[] = new int[n + 1]; // 1 extra to handle case, n = 0
		int i;

		/* 0th and 1st number of the series are 0 and 1 */
		f[0] = 0;
		f[1] = 1;

		for (i = 2; i <= n; i++) {
			/*
			 * Add the previous 2 numbers in the series and store it
			 */
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];
	}

	public static void main(String args[]) {
		int n = 9;
		
		System.out.println(withoutDP(n));
		System.out.println("Number of recursive calls:" + numberOfRecursiveCalls);
		
		System.out.println(withDP(n));
		
		int[] mem =  new int[n+1];
		Arrays.fill(mem, -1);
		
		numberOfRecursiveCalls=0;
		System.out.println(withMemoryFunction(n, mem));
		System.out.println("Number of recursive calls:" + numberOfRecursiveCalls);
	}
}
/* This code is contributed by Rajat Mishra */
