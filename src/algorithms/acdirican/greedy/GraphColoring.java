package algorithms.acdirican.greedy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * The result of graph coloring
 */
class Result {

	/**
	 * Number of colors used for graph coloring
	 */
	int numberOfColors;
	/**
	 * Colors used
	 */
	int[] colors;

	public Result(int numberOfVertices, int k) {
		this.numberOfColors = k;
		this.colors = new int[numberOfVertices];
	}

	@Override
	public String toString() {
		return "Result [numberOfColors=" + numberOfColors + ", colors=" + Arrays.toString(colors) + "]";
	}
	
}

public class GraphColoring {

	/**
	 * Graph coloring algorithm: always give the smallest color number to a vertex
	 * colors: c1,c2,c3,c4,..,ck
	 * 
	 * @param adj the input graph
	 * @return
	 */
	public static Result colorGraph(int[][] adj) {

		int k = 0;
		int[] colors = new int[adj.length];
		Arrays.fill(colors, -1);
		
		for (int i = 0; i < adj.length; i++) {
			// Put all the neighboring nodes'colors into the set
			Set<Integer> neighborColors = getNeighborColors(adj, i, colors);

			// Try to find the color with the smallest numbers, which has not been given to
			// any neighbor of i
			int selectedColor = -1;
			for (int c = 0; c < k; c++) {
				if (!neighborColors.contains(c)) {
					selectedColor = c;
					break;
				}
			}

			// Understand if any previously selected color (between 1 and k) has been
			// selected
			if (selectedColor == -1) {
				selectedColor = k;
				k++;
			}

			colors[i] = selectedColor;
		}

		Result r = new Result(adj.length, k);

		for (int i = 0; i < adj.length; i++) {
			r.colors[i] = colors[i];
		}
		return r;
	}

	/**
	 * Greeds algorithms The vertex with the biggest degree is first colors:
	 * c1,c2,c3,c4,..,ck
	 * 
	 * @param adj the input graph
	 * @return
	 */
	public static int colorGreedy(int[][] adj) {

		int k = 0;
		int[] colors = new int[adj.length];

		/*
		 * degrees = {2,4,7,1,5} sortedByDegree = {0,1,2,3,4}
		 * 
		 * after sorting by degree
		 * 
		 * degrees = {7,5,4,2,1} sortedByDegree = {2,4,1,0,3}
		 * 
		 */
		int[] degrees = computeDegrees(adj);
		int[] sortedByDegree = sortedByDegree(degrees);

		for (int v = 0; v < sortedByDegree.length; v++) {
			int i = sortedByDegree[v];
			// Put all the neighboring nodes'colors into the set
			Set<Integer> neighborColors = getNeighborColors(adj, i, colors);

			// Try to find the color with the smallest numbers, which has not been given to
			// any neighbor of i
			int selectedColor = 0;
			for (int c = 1; c <= k; c++) {
				if (!neighborColors.contains(c)) {
					selectedColor = c;
					break;
				}
			}

			// Understand if any previously selected color (between 1 and k) has been
			// selected
			if (selectedColor == 0) {
				selectedColor = k + 1;
				k++;
			}

			colors[i] = selectedColor;

		}
		return k;
	}

	/**
	 * Greeds algorithms The vertex with the biggest degree is first colors:
	 * c1,c2,c3,c4,..,ck
	 * 
	 * @param adj the input graph
	 * @return an integer array with number of
	 */
	public static Result colorWelshPowel(int[][] adj) {

		int k = 0;
		int[] colors = new int[adj.length];
		Arrays.fill(colors, -1);

		/*
		 * degrees = {2,4,7,1,5} sortedByDegree = {0,1,2,3,4}
		 * 
		 * after sorting by degree
		 * 
		 * degrees = {7,5,4,2,1} sortedByDegree = {2,4,1,0,3}
		 * 
		 */
		int[] degrees = computeDegrees(adj);
		// System.out.println(Arrays.toString(degrees));

		int[] sortedByDegree = sortedByDegree(degrees);

		// System.out.println(Arrays.toString(degrees));
		// System.out.println(Arrays.toString(sortedByDegree));

		for (int v = 0; v < sortedByDegree.length; v++) {
			int i = sortedByDegree[v];

			if (colors[i] == -1) {
				
				int nextColor = k++;
				colors[i] = nextColor;
				for (int u = v + 1; u < sortedByDegree.length; u++) {
					int j = sortedByDegree[u];
					if (colors[j] == -1 && adj[i][j] == 0) {
						Set<Integer> neighborColors = getNeighborColors(adj, j, colors);
						if (!neighborColors.contains(nextColor)) {
							colors[j] = nextColor;
						}
					}
				}
				
			}
		}

		Result r = new Result(adj.length, k);

	    r.colors = Arrays.copyOf(colors, colors.length);

		return r;
	}

	private static Set<Integer> getNeighborColors(int[][] adj, int i, int[] colors) {
		Set<Integer> neighborColors = new HashSet<>();
		for (int j = 0; j < adj[i].length; j++) {
			if (i!= j && adj[i][j] != 0 && colors[j] != -1) {
				neighborColors.add(colors[j]);
			}
		}
		return neighborColors;
	}

	private static int[] sortedByDegree(int[] degrees) {

		int[] sortedByDegree = new int[degrees.length];
		for (int i = 0; i < degrees.length; i++) {
			sortedByDegree[i] = i;
		}

		// Sort by degree, apply the same operation to the sortedByDegree array
		for (int i = 0; i < sortedByDegree.length; i++) {
			int max = i;
			for (int j = i; j < sortedByDegree.length; j++) {
				if (degrees[j] >= degrees[max]) {
					max = j;
				}
			}

			swap(degrees, max, i);
			swap(sortedByDegree, max, i);

		}

		return sortedByDegree;
	}

	private static int[] computeDegrees(int[][] adj) {
		int[] degrees = new int[adj.length];
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].length; j++) {
				degrees[i] += adj[i][j];
			}
		}
		return degrees;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) throws IOException {
	
		String inputPath = "";
		if (args.length == 0) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter input file path=");
			inputPath = sc.nextLine();
		} else {
			inputPath = args[0];
		}

		// Read the graph from file
		int[][] adj = createGraphFromFile(inputPath);

		// Get the result of coloring
		Result r = null;
		
		//r = colorGraph(adj);
		
		//r = colorWelshPowel(adj);
		
	//	System.out.println(verify(adj, r.colors));
		System.out.println(colorWelshPowel(adj));
		System.out.println(colorGraph(adj));
		// Save the result
		//saveResult(r);

	}

	private static void saveResult(Result r) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("output.txt");
		writer.println(r.numberOfColors);
		for (int i = 0; i < r.colors.length; i++) {
			writer.print(r.colors[i] + " ");
		}
		writer.flush();
		writer.close();
	}

	private void test() {

		int[][] g1 = { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 1, 1 }, 
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 1, 1, 0, 0 } };

		int[][] g2 = { { 0, 1, 0, 0, 0, 1, 0, 1 }, { 1, 0, 1, 0, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 1 }, { 1, 1, 0, 1, 1, 0, 1, 0 } };

		System.out.println(colorGreedy(g2));
		System.out.println(colorWelshPowel(g2));

	}

	private static int[][] createGraphFromFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));

		// Read first line to get number of vertices and adges
		String line = reader.readLine();
		String[] parts = line.split("\s");
		int numberOfVertices = Integer.parseInt(parts[1]);
		int numberOfEdges = Integer.parseInt(parts[2]);
		int[][] graph = new int[numberOfVertices][numberOfVertices];

		// Read the remaining lines to get edges
		while ((line = reader.readLine()) != null) {
			parts = line.split("\s");
			int u = Integer.parseInt(parts[1]) - 1;

			int v = Integer.parseInt(parts[2]) - 1;
			graph[u][v] = 1;
			graph[v][u] = 1;
		}

		reader.close();
		return graph;
	}

	static int V = 0;
	private static boolean isSafeToColor(int[][] graph, int[] color) {
		  for (int i = 0; i < V; i++)
		    for (int j = i + 1; j < V; j++)
		      if (graph[i][j] == 1 && color[j] == color[i])
		        return false;
		  return true;
		}
		 
		private static void printColorArray(int[] color) {
		  System.out.println("Solution colors are: ");
		  for (int i = 0; i < color.length; i++) {
		    System.out.println(color[i]);
		  }
		}
		private static boolean graphColoring(int[][] graph, int m, int i, int[] color) {
		  if (i == V) {
		    if (isSafeToColor(graph, color)) {
		      printColorArray(color);
		      return true;
		    }
		    return false;
		  }
		  for (int j = 1; j <= m; j++) {
		    color[i] = j;
		    if (graphColoring(graph, m, i + 1, color))
		      return true;
		 
		    color[i] = 0;
		  }
		 
		  return false;
		}
		
		public static boolean verify(int[][] adj, int[] colors) {
			
			for(int i=0; i<adj.length; i++) {
				Set<Integer> neigs = getNeighborColors(adj, i, colors);
				
				if (neigs.contains(colors[i])) {
					return false;
				}
			}
			
			return true;
			
		}
}