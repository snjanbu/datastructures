package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Edge1{
	int endVertex;
	int weight;
	
	public Edge1(int endVertex,int weight) {
		this.endVertex=endVertex;
		this.weight=weight;
	}
	
	@Override
	public String toString() {
		return "{"+endVertex+","+weight+"}";
	}
}

class Graph1{
	List<Edge> G[];
	Map<Integer,Integer> parentMap;
	Map<Integer,Integer> distanceMap;
	
	public Graph1(int n) {
		G=new LinkedList[n];
		parentMap=new HashMap<>();
		distanceMap=new HashMap<>();
		for(int i=0;i<n;i++) {
			G[i]=new LinkedList<>();
			parentMap.put(i, -1);
			distanceMap.put(i, Integer.MAX_VALUE);
		}
	}
	
	public void addEdge(int u,int v,int w) {
		G[u].add(0,new Edge(v,w));
	}
	
	@Override
	public String toString() {
		String result="";
		for(int i=0;i<G.length;i++) {
			result+=i+"-->"+G[i]+"\n";
		}
		return result;
	}
	
	public void findPath(int vertex) {
		distanceMap.put(vertex, 0);
		parentMap.put(vertex, vertex);
		findShortestPath(vertex);
		System.out.println(distanceMap);
		System.out.println(parentMap);
	}
	
	public void findShortestPath(int vertex) {
		for(Edge e:G[vertex]) {
			int vertexDistance=distanceMap.get(vertex)+e.weight;
			if(distanceMap.get(e.endVertex)>vertexDistance) {
				distanceMap.put(e.endVertex, vertexDistance);
				parentMap.put(e.endVertex, vertex);
			}
			findShortestPath(e.endVertex);
		}
	}
}

public class ShortestPathAlgorithm{
	public static void main(String[] args) {
		Graph1 g=new Graph1(6);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 3, 1);
		g.addEdge(1, 2, 2);
		g.addEdge(3, 2, 1);
		g.addEdge(3, 4, 2);
		g.addEdge(2, 4, 3);
		g.addEdge(4, 5, 4);
		System.out.println(g);
		g.findPath(0);
	}
}