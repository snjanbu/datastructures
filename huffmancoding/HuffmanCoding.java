import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

 class Node implements Comparable<Node>{
	int frequency;
	char ch;
	Node left,right;
	
	public Node(int frequency) {
		this.frequency=frequency;
	}
	
	@Override
	public int compareTo(Node b) {
		return frequency-b.frequency;
	}
}
class HuffManNode extends Node{
	
	public HuffManNode(int frequency,Node left,Node right) {
		super(frequency);
		super.ch='#';
		super.left=left;
		super.right=right;
	}
	
}

class HuffManLeaf extends Node{
	
	public HuffManLeaf(int frequency,char ch) {
		super(frequency);
		super.ch=ch;
		super.left=null;
		super.right=null;
	}
}

public class HuffmanCoding{
	
	Node root;
	
	static Map <Character,String>characterMap;
	
	public HuffmanCoding() {
		root=null;
		characterMap=new HashMap<>();
	}
	
	public Node buildTree(int []frequency,char []data) {
		PriorityQueue <Node>nodeQueue=new PriorityQueue<>();
		for(int i=0;i<frequency.length;i++) {
			nodeQueue.offer(new HuffManLeaf(frequency[i], data[i]));
		}
		
		while(nodeQueue.size()>1) {
			Node a=nodeQueue.poll();
			Node b=nodeQueue.poll();
			nodeQueue.offer(new HuffManNode(a.frequency+b.frequency, a, b));
		}
		
		return nodeQueue.poll();
	}
	
	
	public static void formString(Node currentNode,StringBuilder str) {
		if(currentNode!=null) {
			if(currentNode instanceof HuffManLeaf) {
				HuffManLeaf current=(HuffManLeaf)currentNode;
				characterMap.put(current.ch, str.toString());
			}

			if(currentNode instanceof HuffManNode) {
				HuffManNode current=(HuffManNode)currentNode;
				formString(current.left,str.append("0"));
				str.deleteCharAt(str.length()-1);
				formString(current.right,str.append("1"));
				str.deleteCharAt(str.length()-1);
			}
		}
		System.out.println(characterMap);
	}
	
	public static void main(String args[]) {
		char []ch= {'A','B','C','D','E','F'};
		int []frequency= {5,9,12,13,16,45};
		HuffmanCoding h=new HuffmanCoding();
		Node root=h.buildTree(frequency,ch);
		formString(root,new StringBuilder());
	}
}