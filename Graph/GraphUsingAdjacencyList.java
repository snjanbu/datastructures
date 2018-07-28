package main;

import java.util.LinkedList;
import java.util.List;
class Edge{
	int endVertex,weight;
	
	public Edge(int endVertex,int weight) {
		this.endVertex=endVertex;
		this.weight=weight;
	}
	
	@Override
	public String toString() {
		return "{"+endVertex+","+weight+"}";
	}
	
}


class Graph {

	List<Edge> []G;
	
	public Graph(int n) {
		G=new LinkedList[n];
		for(int i=0;i<n;i++) {
			G[i]=new LinkedList<>();
		}
	}
	
	public void addEdge(int u,int v,int w) {
		G[u].add(0,new Edge(v, w));
	}
	
	public boolean isConnected(int u,int v) {
		for(Edge e:G[u]) {
			if(e.endVertex==v) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String result="";
		for(int i=0;i<G.length;i++) {
			result+=i+"-->"+G[i]+"\n";
		}
		return result;
	}
	
}

public class GraphUsingAdjacencyList{
	public static void main(String[] args) {
		Graph graph=new Graph(3);
		System.out.println(graph);
		graph.addEdge(0, 1, 2);
		graph.addEdge(0, 2, 5);
		System.out.println(graph);
		System.out.println(graph.isConnected(0, 1));
		System.out.println(graph.isConnected(1, 2));
	}
}