package datastructure;

import java.awt.print.Printable;
import java.util.Arrays;
import java.util.function.Predicate;



public class MinHeap {
	private int heapSize; //size of the arr
	private int[] arr;
	private int endIndex; //length of the heap
	
	public static void main(String[] args) {
		MinHeap mHeap = new MinHeap();
		mHeap.insert(1);
		mHeap.insert(9);
		mHeap.insert(5);
		mHeap.insert(4);		
		mHeap.insert(9);
		mHeap.insert(5);
		mHeap.insert(4);
		mHeap.insert(9);
		mHeap.insert(5);
		int out;
		out = mHeap.removeTop();
		mHeap.show();

	}
	
	public void show() {
		for(int i=0;i<=endIndex;i++) {
			System.err.println(arr[i]);
		}
		
	}
	
	public MinHeap() {
		this.heapSize = 10;
		this.arr = new int[heapSize];
		this.endIndex = -1;
	}
	

	public void insert(int x) {
		arr[endIndex +1] = x;
		endIndex += 1;
		shitUp(endIndex);
		if(endIndex > heapSize -2) {
			System.err.println("enlarge the arr");
			enlarge();
		}
	}
	
	public int getTop() {
		return arr[0];
	}

	public int removeTop() {
		if(endIndex == -1) {
			System.err.println("heap is empty!");
			return -1;
		}
		
		int min = arr[0];
		arr[0] = arr[endIndex];
		shiftDown(0);
		endIndex -= 1;
		return min;
	}
	
	private void enlarge() {
        heapSize = heapSize*2;  
        int[] newArr = new int[heapSize];  
        for(int i=0;i<arr.length;i++){  
            newArr[i] = arr[i];  
        }  
        arr = newArr; 
	}
	
	//keep the minHeap correct ,asume that left and right children are minHeap
	private void shiftDown(int index) {
		//here is a strange func from blog.....but its ok
		  for(int i = index * 2 + 1;i <= endIndex;i = i * 2 + 1){  
	            if(arr[i+1]<arr[i] && i+1 <= endIndex){  
	                i++;  
	            }  
	            int father = (i-1)/2;  
	            if(arr[i] < arr[father]){  
	                int temp = arr[i];  
	                arr[i] = arr[father];  
	                arr[father] = temp;  
	            }  
	            else{  
	                return;  
	            }  
	        }  
	}
	
	private void shitUp(int index) {
		for(int i = index;i > 0;i = (i-1)/2) {
			int fatherIndex = (i-1)/2;
            if(arr[i] < arr[fatherIndex]){  
                int temp = arr[i];  
                arr[i] = arr[fatherIndex];  
                arr[fatherIndex] = temp;  
            }else {
            	return;
            }
		}
	}
	
}
