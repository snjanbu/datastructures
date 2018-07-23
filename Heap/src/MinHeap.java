

public class MinHeap{
	
	static void up_Heapify(int []input,int childIndex) {
		if(childIndex==0) {
			return;
		}
		int value=input[childIndex];
		while(childIndex>0 && input[(childIndex-1)/2]<value) {
			swap(input,(childIndex-1)/2,childIndex);
			childIndex=(childIndex-1)/2;
		}
	}
	
	static void swap(int []array,int a,int b) {
		int t=array[a];
		array[a]=array[b];
		array[b]=t;
	}
	
	static void heapSort(int[] input) {
		for(int i=1;i<input.length;i++) {
			up_Heapify(input,i);
		}

//		for(int i=0;i<input.length;i++) {
//			System.out.println(input[i]);
//		}
//	
		int size=input.length-1;
		for(int i=0;i<input.length;i++) {
			swap(input,0,size);
			down_Heapify(input,size);
			size--;
		}
		
		for(int i=0;i<input.length;i++) {
			System.out.println(input[i]);
		}
	}
	
	static void down_Heapify(int []input,int size) {
		int i=0,j=2*i+1;
		while(j<size) {
			if((((j+1)<size) && (input[i]>input[j]) && (input[i]>input[j+1])) || (input[i]>input[j])) {
				break;
			}
			if(((j+1)<size && input[j+1]>input[j])){
				j=j+1;
			}
			swap(input,i,j);
			i=j;
			j=2*i+1;
		}
	}
	
	public static void main(String args[]) {
		int input[]= {1,2,3,4,5};
		heapSort(input);
	}
}