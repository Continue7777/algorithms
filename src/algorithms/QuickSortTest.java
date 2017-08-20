package algorithms;

public class QuickSortTest {
	
	public static void main(String[] args) {
		int[] arr = {2,3,4,9,5,4,6};
		
		partition(arr,0,arr.length-1);
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void qsort(int[] arr,int begin,int end) {

		int index = partition(arr, begin,end);
		
		if(begin >= end -1) {
			return;	
		}
		
		qsort(arr,begin,index-1);
		qsort(arr,index+1,end);

	}
	
	//用一个哨兵来分割大于小于的两部分
	private static int partition(int[] arr,int begin,int end) {
		if(begin >= end) {
			return -1;
		}
		
		int pos = arr[begin];
		while(begin < end) {
			while(arr[end] >= pos && begin < end) {

				end --;
			}
			if(begin < end) {
				arr[begin] = arr[end];
			}
			while(arr[begin] <= pos && begin < end) {
				begin ++;
			}
			if(begin < end) {
				arr[end] = arr[begin];
			}
		}
		
		arr[begin] = pos;
		return begin;
	}
}
