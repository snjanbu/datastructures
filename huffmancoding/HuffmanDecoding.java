package huffmancoding;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	char ch;
	Node left,right;
	int frequency;
	
	public Node(int frequency) {
		this.frequency=frequency;
	}
	
	@Override
	public int compareTo(Node a) {
		return frequency-a.frequency;
	}
	
}

class HuffManNode extends Node{
	
	public HuffManNode(Node left,Node right,int frequency) {
		super(frequency);
		super.ch='#';
		super.left=left;
		super.right=right;
	}
}

class HuffManLeaf extends Node{
	
	public HuffManLeaf(char ch,int frequency) {
		super(frequency);
		super.ch=ch;
		super.left=left;
		super.right=right;
	}
}

public class HuffmanDecoding{
	
	Node root;
	
	Map <Character,String>characterMap;
	
	public HuffmanDecoding() {
		root=null;
		characterMap=new HashMap<>();
	}
	
	public Node buildHuffManTree(char []input,int []frequency) {
		
		PriorityQueue <Node>nodeQueue=new PriorityQueue<>();
		
		for(int i=0;i<input.length;i++) {
			nodeQueue.offer(new HuffManLeaf(input[i],frequency[i]));
		}
		
		while(nodeQueue.size()>1) {
			Node a=nodeQueue.poll();
			Node b=nodeQueue.poll();
			
			nodeQueue.offer(new HuffManNode(a, b, a.frequency+b.frequency));
		}
		
		return nodeQueue.poll();
	}
	
	public void formString(Node currentNode,StringBuilder str) {
		if(currentNode!=null) {
			if(currentNode instanceof HuffManLeaf) {
				characterMap.put(currentNode.ch, str.toString());
			}
			if(currentNode instanceof HuffManNode) {
				formString(currentNode.left,str.append("0"));
				str.deleteCharAt(str.length()-1);
				formString(currentNode.right,str.append("1"));
				str.deleteCharAt(str.length()-1);
			}
		}
	}
	
	public void printCharacterMap(){
		System.out.println(characterMap);
	}
	
	public String encodeInput(String input) {
		StringBuilder str=new StringBuilder();
		for(char ch:input.toCharArray()) {
			str.append(characterMap.get(ch));
		}
		return str.toString();
	}
	
	public void decodeFromRoot(Node root,String encodedInput) {
		StringBuilder output=new StringBuilder();
		decodeFromNode(root, root, output, encodedInput);
		System.out.println(output.toString());
	}
	
	public void decodeFromNode(Node currentNode,Node root,StringBuilder output,String input) {
		if(!input.isEmpty()) {
			Node check;
			if(input.charAt(0)=='0') {
				check=currentNode.left;
			}else {
				check=currentNode.right;
			}
			if(check instanceof HuffManLeaf) {
				output.append(check.ch);
				decodeFromNode(root, root, output, input.substring(1));
			}else {
				decodeFromNode(check, root, output, input.substring(1));
			}
		}
	}
	
	public static void main(String args[]) {
		HuffmanDecoding huffmanDecoding=new HuffmanDecoding();
		char input[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int frequency[] = { 5, 9, 12, 13, 16, 45 };
		Node root=huffmanDecoding.buildHuffManTree(input, frequency);
		huffmanDecoding.formString(root, new StringBuilder());
		huffmanDecoding.printCharacterMap();
		String testInput="ababcdcdefef";
		String encodedInput=huffmanDecoding.encodeInput(testInput);
		huffmanDecoding.decodeFromRoot(root, encodedInput);
	}
}