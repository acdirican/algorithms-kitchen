package algorithms.acdirican.bruteforce.exhaustivesearch;

/**
 * This class includes the brute force solution of Knapsack problem.
 * 
 * @author Ahmet Cengizhan
 * @version 12.05.2022
 */
public class Knapsack {

	/**
	 * Print all possible knapsack fits
	 * @param graph
	 * @return
	 */
	public static int printAllFits(int items[], int[] weights, int[] values, int W) {
		int n=0;
		//Let's try all combinations
		for (int r = 1; r <=values.length; r++) {
			// A temporary array to store all combination one by one
	        int data[]=new int[r];
			n += printAllFits(items, weights, values, W, data, 0, items.length-1, 0, r);
		}
		return n;
	}

	private static int printAllFits(int[] arr, int[] weights, int[] values, int W, int[] data, int start, int end, int current, int r) {
		// Current combination is ready to be printed, print it
        if (current == r) {
        	if (sumWeight(data, weights, r) == W) {
        		print(data, r);
        	}
            
            return 1;
        }
        
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        int n=0;
        for (int i=start; i<=end && end-i+1 >= r-current; i++)
        {
            data[current] = arr[i];
            n += printAllFits(arr, weights, values, W, data, i+1, end, current+1, r);
        }
        
		return n;
	}
	

	private static void swap(int[] arr, int c, int i) {
		int t = arr[c];
		arr[c] = arr[i];
		arr[i] = t;
		
	}

	private static int sumWeight(int[] arr, int[] weights, int r) {
		int s = 0;
		for (int i = 0; i < r; i++) {
			s += weights[arr[i]];

		}
		return s;
	}
	
	private static void print(int[] arr, int r) {
		 for (int j=0; j<r; j++) {
            System.out.print(arr[j]+" ");
        }
        System.out.println("");
	}

	
	public static void main(String[] args) {
	
		int[] items =  {0,1,2,3,4,5,6,7,8};
		int[] weights= {2,4,3,10,7,6,6,8,12};
		int[] values = {8,6,15,9,4,11,12,3,6};
		int W = 30;
		int n = printAllFits(items, weights, values, W);
		System.out.println(n);
	}
}
