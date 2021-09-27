import java.util.ArrayList;
import java.util.LinkedList;

// the implementation for the graph is from geeksforgeeks 
// url: https://www.geeksforgeeks.org/shortest-path-unweighted-graph/ 
public class Graph {
	
	
	public static void addEgde(ArrayList<ArrayList<Integer>> graph, int user, int friend) {
		graph.get(user).add(friend);
		graph.get(friend).add(user);
	}
	
	public static int calculateShortestDistance(ArrayList<ArrayList<Integer>> graph, int source, int destination, int size){
		int pred[] = new int[size];
		int dist[] = new int[size];
		
		if (BFS(graph, source, destination, size, pred, dist) == false) {
			return -1;
		}
		
		
		LinkedList<Integer> path = new LinkedList<Integer>();
		int crawl = destination;
		path.add(crawl);
		while (pred[crawl] != -1) {
			path.add(pred[crawl]);
			crawl = pred[crawl];
		}
		return dist[destination];
	}
		
	// Breadth-first search  
	public static boolean BFS(ArrayList<ArrayList<Integer>> graph, int source,int destination, int size, int pred[], int dist[]){
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		
		boolean visited[] = new boolean[size];
		
		
		for (int i = 0; i < size; i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
		
		
		visited[source] = true;
		dist[source] = 0;
		queue.add(source);
		
		
		while (!queue.isEmpty()) {
			int u = queue.remove();
			for (int i = 0; i < graph.get(u).size(); i++) {
				if (visited[graph.get(u).get(i)] == false) {
					visited[graph.get(u).get(i)] = true;
					dist[graph.get(u).get(i)] = dist[u] + 1;
					pred[graph.get(u).get(i)] = u;
					queue.add(graph.get(u).get(i));
					
					
					if (graph.get(u).get(i) == destination)
						return true;
				}
			}
		}
		return false;
	}

}

