package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Edge{
	int endVertex,weight;
	public Edge(int endVertex,int weight) {
		this.endVertex=endVertex;
		this.weight=weight;
	}
	
	public Edge(int endVertex) {
		this.endVertex=endVertex;
		this.weight=-1;
	}
	
	@Override
	public String toString() {
		return "{"+endVertex+","+weight+"}";
	}
}

class Graph{
	List<Edge> G[];
	boolean visited[];
	public Graph(int n) {
		G=new LinkedList[n];
		for(int i=0;i<n;i++) {
			G[i]=new LinkedList<>();
		}
		visited=new boolean[G.length];
	}
	
	public void addEdge(int u,int v,int w) {
		G[u].add(0,new Edge(v,w));
	}
	
	public void addEdge(int u,int v) {
		G[u].add(0,new Edge(v));
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
	
	public void bfs(int source) {
		Queue<Integer> nodeQueue=new LinkedList<>();
		nodeQueue.add(source);
		while(!nodeQueue.isEmpty()) {
			int temp=nodeQueue.poll();
			System.out.println(temp+" ");
			visited[temp]=true;
			for(Edge e:G[source]) {
				if(!visited[e.endVertex]) {
					bfs(e.endVertex);
				}
			}
		}
	}
	
	public void dfs(int source) {
		Stack<Integer> nodeStack=new Stack<Integer>();
		nodeStack.push(source);
		while(!nodeStack.isEmpty()) {
			int temp=nodeStack.pop();
			visited[temp]=true;
			System.out.println(temp+" ");
			for(Edge e:G[temp]) {
				if(!visited[e.endVertex]) {
					nodeStack.push(e.endVertex);
				}
			}
		}
	}
	
}
public class BreadthFirstSearch{
	public static void main(String[] args) {
		Graph g=new Graph(4);
		 g.addEdge(0, 1);
		 g.addEdge(0, 2);
		 g.addEdge(1, 2);
		 g.addEdge(2, 0);
		 g.addEdge(2, 3);
		 g.addEdge(3, 3);
		 System.out.println(g);
		 g.bfs(2);
		 g.dfs(2);
	}
}