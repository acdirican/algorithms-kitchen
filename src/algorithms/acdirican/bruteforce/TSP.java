package algorithms.acdirican.bruteforce;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * abcde
 * bacde
 * cbade
 * dbcae
 * ebcda
 */

/*
 * a bcde
 * abcde
 * acbde
 * adcbe
 * aecde
 * 
 *
 */

/*
 * ab cde
 * cde
 * dce
 * edc
 */

/**
 * This class includes the brute force solution of Traveling Salesman problem.
 * 
 * @author Ahmet Cengizhan
 * @version 12.05.2022
 */

public class TSP {
	
	

	private static List<Integer> tsp(int[][] adj, int node, List<Integer> visited) {
		if (visited.contains(node)) {
			return null;
		}
		
		visited.add(node);
		
		boolean allVisited = true;
		for (int i = 0; i <adj.length; i++) {
			if (!visited.contains(i)) {
				allVisited = false;
			}
		}
		
		if (allVisited) {
			System.out.println(visited + " : "  + calcLength(adj, visited));
			return visited;
		}
		
		int n = 0;
		List<Integer>  shortest = null;
		for (int i=0; i<adj[node].length; i++) { 
			if (adj[node][i]>0) {
				List<Integer> path = tsp(adj, i, new ArrayList<Integer>(visited));
				if (path!=null) {
					if (shortest == null) {
						shortest = path;
					}
					else {
						if (calcLength(adj, shortest) > calcLength(adj, path)){
							shortest = path;
						}
					}
				}
			}
		}
		return shortest;
	}
		
	private static int calcLength(int[][] adj, List<Integer> path) {
		int u = path.get(0);
		int l = 0;
		for (int i=1;i<path.size(); i++) {
			int v = path.get(i);
			l += adj[u][v];
			u=v;
		}
		return l;
	}
	
	public static void main(String[] args) {
//		int n = permutations("abcde".toCharArray(), 0);
//		System.out.println(n);
		
		int[][] adj = {
				{0,70,8,6,9},
				{7,0,3,8,5},
				{8,3,0,4,12},
				{6,8,4,0,11},
				{9,5,12,11,0}
		};
		
		List<Integer> shortest = tsp(adj, 0, new ArrayList<Integer>());
		System.out.println(shortest);
	}
	
	
}
