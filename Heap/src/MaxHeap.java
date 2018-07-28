package Heap.src;


public class MaxHeap{
	
	static void upHeapify(int []input,int childIndex) {
		if(childIndex==0) {
			return;
		}
		int value=input[childIndex];
		while(value<input[(childIndex-1)/2]) {
			swap(input,childIndex,(childIndex-1)/2);
			childIndex=(childIndex-1)/2;
		}
	}
	
	static void swap(int []input,int i,int j) {
		int t=input[i];
		input[i]=input[j];
		input[j]=t;
	}
	
	static void heapSort(int []input) {
		for(int i=1;i<input.length;i++) {
			upHeapify(input,i);
		}
		
		int size=input.length-1;
		for(int i=0;i<input.length;i++) {
			swap(input,0,size);
			size--;
			downHeapify(input,size);
		}
	}
	
	static void downHeapify(int []input,int size) {
		int i=0,j=2*i+1;
		while(j<size) {
			if(((j+1)<size) && (input[i]<input[j]) && (input[i]<input[j]) || (input[i]<input[j])) {
				break;
			}
			if(((j+1)<size) && (input[j]>input[j+1])){
				j=j+1;
			}
			swap(input,i,j);
			i=j;
			j=2*i+1;
		}
	}
	
	public static void main(String args[]) {
		int []input= {5,4,3,2,1};
		heapSort(input);
		for(int i=0;i<input.length;i++) {
			System.out.println(input[i]);
		}
	}
}