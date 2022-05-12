package algorithms.acdirican.bruteforce;

/**
 * This class includes the permutation algorithm for an array
 * 
 * @author Ahmet Cengizhan Dirican
 * @version 12.05.2022
 */
public class Permutation {
	
	private static int printPermutation(char[] arr) {
		return printPermutation(arr);
	}
	private static int printPermutation(char[] arr, int current) {

		if (current == arr.length) {
			System.out.println(arr);
			return 1;
		}

		int n = 0;
		for (int i = current; i < arr.length; i++) {
			swap(arr, current, i);
			n += printPermutation(arr, current + 1);
			swap(arr, i, current);
		}

		return n;

	}
	
public static void swap(char[] arr, int current, int i) {
	char temp = arr[current];
	arr[current] = arr[i];
	arr[i] = temp;
	
}}
