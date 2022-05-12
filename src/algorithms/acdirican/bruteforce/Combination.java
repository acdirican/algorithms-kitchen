package algorithms.acdirican.bruteforce;

public class Combination {
	public static int printCombinations(int[] arr, int r) {
		return printCombinations(arr, new int[arr.length], 0, arr.length-1,0, r);
	}
	
	private static int printCombinations(int[] arr, int[] data, int start, int end, int current,
			int r) {
		// Current combination is ready to be printed, print it
		if (current == r) {
			for (int j = 0; j < r; j++) {
				System.out.print(data[j] + " ");
			}
			System.out.println("");
			return 1;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		int n = 0;
		for (int i = start; i <= end && end - i + 1 >= r - current; i++) {
			data[current] = arr[i];
			n += printCombinations(arr, data, i + 1, end, current + 1, r);
		}

		return n;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		printCombinations(arr, 2);
	}
}
