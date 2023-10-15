import java.util.Arrays;
import java.util.stream.IntStream;
import java.lang.Math;

public class MountainSort {

	public static void main(String[] args) {
		int[][] Tests = {
				{},
				{6, 2, 8, 5, 7, 5, 0, 2},
				{2,2,2,2,2,2,2,2},
				{4, -7, 2, 1, 0, 2, 4, 2, -3, 3, -3, 7, -2, 7, 7},
				{-2, -2, -4, -9, -1, -6, -1, -14, -3, -15, -12, -12, -2, -8, -9},
		};
		for (int[] A: Tests) {
			try {
				System.out.println("Given array: " + Arrays.toString(A));
				A = MountainSort(A); //change the name of function if necessary
				System.out.println("Array sorted: " + Arrays.toString(A));
				System.out.println();
			} catch (Exception e) {
				System.out.println("Error for array: "+ Arrays.toString(A));
				System.out.println(e);
			}
		}

//		int[] A= {34, 12, 7, 43, 55, 41, 97, 28, 2, 62};
//
//		System.out.println("Original array: " + Arrays.toString(A));
//		System.out.println();
//
//		A = MountainSort(A);
//
//		System.out.println();
//		System.out.println("Array sorted: " + Arrays.toString(A));
	}

	public static int[] MountainSort(int[] A) {
			if (A.length == 0) {
				return A;
			}
			int low = A[0], high = A[0];
			for (int j : A) {
				if (j < low) {
					low = j;
				}
				if ( j > high) {
					high = j;
				}
			}
			int [] indexcounter = new int [high - low + 1];
			for (int j : A) {
				indexcounter[j-low] += 1;
			}
			int index = 0;
			int val = low;
			for (int i : indexcounter) {
				for (int n = i; n > 0; n--) {
					A[index] = val;
					index++;
				}
				val++;
			}

			int temp;
			int indexreverse = A.length/2;
			for(int l = A.length-1; l >= A.length/2; l--) {
				temp = A[indexreverse];
				A[indexreverse++] = A[l];
				A[l] = temp;
				if ((int)Math.ceil(A.length/4.0) == A.length - l) {
					break;
				}
			}
			return A;
			}

	//you can add your own functions if you want

}
