

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
    
		// header of the remaining array
		int Abegin = 0, Bbegin = 0;
	
		int residue = (A.length+B.length) % 2, k = (A.length+B.length) / 2;

		if (residue == 0){
			// if the total number is even, the median is the average of two number
			return ( cutForMedian(A, Abegin, B, Bbegin, k) + cutForMedian(A, Abegin, B, Bbegin, k + 1)) / 2.0;
		}
		else {
			// if odd, the median is just one single number
			return cutForMedian( A, Abegin, B, Bbegin, k + 1);
		}
	}
     
	/* crucial algorithm for the problem, solved by recursion*/
	public static int cutForMedian(int A[], int Ab, int B[], int Bb, int count) {
		// A[] and B[] represents two arrays
		// Ab and Bb represents the index that after them all the elements has not yet been cut
		// count represents the remaining number of elements waiting to be cut
		
        if (Ab >= A.length) return B[Bb + count - 1];	// important, if there's not more element in array A, then simply find the median in B
        if (Bb >= B.length) return A[Ab + count - 1];	// important, if there's not more element in array B, then simply find the median in A
        
		// if count == 1, the minimun of the A[Ab] and B[Bb] is the desired number
        if (count == 1) return Math.min(A[Ab], B[Bb]);

		/* IMPORTANT, the next several lines is trying to split the count (remaining element to be cut),
		in two parts, and attempts to perform cut on array A and B. Then,
		
		a> if both parts are enough to be cut, then compare two element at key index, and throw away the
		part before the smaller one because the median will not be in this range. (Can be proved theoretically)
		
		b> if any part is not enough to be cut, we can cut all the elements before the key index element IN THE
		OTHER ARRAY because it can be proved that this part will be SURLY cut in the future*/
        int A_key = Ab + count/2 - 1 < A.length ? A[Ab + count/2 - 1] : Integer.MAX_VALUE;
        int B_key = Bb + count/2 - 1 < B.length ? B[Bb + count/2 - 1] : Integer.MAX_VALUE;

        if (A_key < B_key) return cutForMedian(A, Ab + count/2, B, Bb , count - count/2);
        else  return cutForMedian(A, Ab, B, Bb  + count/2, count - count/2);
    }
}