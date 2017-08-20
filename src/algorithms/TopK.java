package algorithms;
import datastructure.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class TopK {
	public static void main(String[] args) {
		int n = 3000000;
		int k = 100000;

		testOrderArray(n, k);
		testHeap(n, k);
	}
	public static void testOrderArray(int n,int k) {
		int[] arr = new int[n];
		Random random = new Random();
		
		for(int i=0;i<arr.length;i++) {
			arr[i] = random.nextInt();
		}
		
		long startTime=System.currentTimeMillis();   //获取开始时间 
		int length = arr.length;
		OrderArray orderArray = new OrderArray(k); 

		for(int i=0;i<length;i++) {
			if(i<=k-1) {
				orderArray.addElem(arr[i]);
			}else {
				if(arr[i] > orderArray.getMin()) {
					orderArray.delElem(orderArray.getMin());
					orderArray.addElem(arr[i]);
				}
			}
		}
//		orderArray.show();
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("orderarr程序运行时间： "+(endTime-startTime)+"ms");  
	}
	
	public static void testHeap(int n,int k) {
		int[] arr = new int[n];
		Random random = new Random();

		for(int i=0;i<arr.length;i++) {
			arr[i] = random.nextInt();
		}
		
		int length = arr.length;
		MinHeap mHeap = new MinHeap();
		
		long startTime=System.currentTimeMillis();   //获取开始时间 
		
		for(int i=0;i<length;i++) {
			if(i<=k-1) {
				mHeap.insert(arr[i]);
			}else {
				if(arr[i] > mHeap.getTop()) {
					mHeap.removeTop();
					mHeap.insert(arr[i]);
				}
			}
		}
//		mHeap.show();
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("heap程序运行时间： "+(endTime-startTime)+"ms");  
		
	}
	
}

