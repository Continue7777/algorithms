package algorithms;

public class LIS {
	public static void main(String[] args) {
	    int[] A = {2,1,5,3,4};
	    int result = LISTest(A);
	    System.out.println(result);
	    
	}
	
	public static int LISTest(int A[]) {

	    int[] d = new int[A.length];
	    int len = 1;
	    for(int i=0; i<A.length; ++i){
	        d[i] = 1;
	        for(int j=0; j<i; ++j) {
	            if(A[j]<=A[i] && d[j]+1>d[i]) {
	                d[i] = d[j] + 1;
	            }
	        }
	        if(d[i]>len) {
	        	len = d[i];
	        }
	    }
	    return len;
		
	}
}
