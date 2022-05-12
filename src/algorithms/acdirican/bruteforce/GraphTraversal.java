package algorithms.acdirican.bruteforce;

/**
 * This class includes the implementations of brute force graph traversal algorithms
 * 
 * @author Ahmet Cengizhan
 * @version 12.05.2022
 */
public class GraphTraversal {
	/**
	 * Travers the given graph using depth first search algorithm.
	 * 
	 * @param adj
	 * @param node
	 */
	public static void dfs(int[][] adj, int node) {
		dfsHelper(adj, node, new boolean[adj.length]);
	}

	private static void dfsHelper(int[][] adj, int node, boolean[] visited) {
		if (visited[node])
			return;
		visited[node] = true;

		for (int i = 0; i < adj[node].length; i++) {
			if (adj[node][i] == 1) {
				dfsHelper(adj, i, visited);
			}
		}
		System.out.print(node + " -> ");

	}
	
	public static void main(String[] args) {
		
		int[][] adj = { 
			  { 0, 1, 1, 1, 0 }
			, { 1, 0, 0, 1, 1 }
			, { 1, 0, 0, 1, 0 }
			, { 1, 1, 1, 0, 1 }
			, { 0, 1, 0, 1, 0 } 
		};
		
		GraphTraversal.dfs(adj, 0);
	}
}
