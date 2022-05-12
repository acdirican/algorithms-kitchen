package algorithms.acdirican.bruteforce;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class includes several brute force algorithms
 * 
 * @author Ahmet Cengizhan
 * @version 12.05.2022
 */
public class Algorithms {

	/**
	 * Finds the closest pair of the given points
	 * 
	 * @param points
	 * @return
	 */
	static int[] closestPair(Point[] points) {
		int p1 = -1;
		int p2 = -1;

		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int diffx = points[i].getX() - points[j].getX();
				int diffy = points[i].getY() - points[j].getY();
				double dist = Math.sqrt(Math.pow(diffx, 2) + Math.pow(diffy, 2));
				if (dist < minDist) {
					p1 = i;
					p2 = j;
					minDist = dist;
				}
			}
		}

		return new int[] { p1, p2 };
	}

	/**
	 * Bubble sort
	 * 
	 * @param arr
	 */
	static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * Selection sort
	 * 
	 * @param arr
	 */
	static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}

	}

	/**
	 * Search the array b in array a;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static int patternSearch(int[] a, int[] b) {
		int count = 0;
		for (int i = 0; i < a.length - b.length + 1; i++) {
			boolean match = true;
			for (int j = 0; j < b.length; j++) {
				if (a[i + j] != b[j]) {
					match = false;
					break;
				}
			}
			if (match) {
				count++;
			}
			// eþleþme var mý?
		}
		return count;
	}

	
	

}
