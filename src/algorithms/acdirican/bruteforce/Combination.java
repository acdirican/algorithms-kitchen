package algorithms.acdirican.bruteforce;

public class Combination {
	public static int printCombinations(int[] arr, int r) {
		return printCombinations(arr, new int[arr.length], 0, arr.length-1,0, r);
	}
	
	/**
	 *                					arr[start]  +  f(start + 1 , end, current + 1 , r)
	 *                					arr[start + 1]   +  f(start + 2 , end, current + 1, r) 
	 * f(arr, start, end, current, r) = ...
	 *                					arr[start + end]   +  f(start + (end+1) , end, current + 1, r) 
	 * @param arr
	 * @param data
	 * @param start
	 * @param end
	 * @param current
	 * @param r
	 * @return
	 */
	private static int printCombinations(int[] arr, int[] data, int start, int end, int current,
			int r) {
		//Data is filled with a combination 
		if (current == r) {
			for (int j = 0; j < r; j++) {
				System.out.print(data[j] + " ");
			}
			System.out.println("");
			return 1;
		}

		/*
		 * Fill the data's 'current' element with the arr' next elementi then make the recursive call to fill the rest of the data 
		 */
		int n = 0;
		for (int i = start; i<=end && (end - i + 1) >= r - current; i++) {
			data[current] = arr[i];
			n += printCombinations(arr, data, i + 1, end, current + 1, r);
		}

		return n;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		int n = arr.length;
		for (int r = 1; r <=5; r++) {
			System.out.println("* C(" + n + ", " + r + ")");
			int c = printCombinations(arr, r);
			System.out.println("total:" + c);
			System.out.println("---------");
		}
		
	}
}
