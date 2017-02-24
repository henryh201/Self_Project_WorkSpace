/*
* Author:  Yuqi Hunag
*/

import java.util.*;
public class Primes_before_N {
      int[] primeArray;
     // int [][] result_2D;
      HashMap prime_Table;
      int Upper_Bond;

	public Primes_before_N(int n){
		this.prime_Table = new HashMap();
		this.Upper_Bond = n;
		this.primeArray = this.find_Prime(this.Upper_Bond);
		this.constructPrimeMap();
	}

  /*
  *   using two array list to implements the sevis method fod fining primes
  *   before N, keep taking integer fits the requirement from one array to
  *   another, and until have checked all possible integers before N
  */
	private int[] find_Prime(int n){

    // list size from 2 to N = N - 1
		int list_Size = n - 1;
  	int[] array1 = new int[list_Size];
		int[] array2 = new int[list_Size];
    // put the 1st known prime to array2
		array2[0] = 2;
		int number_Primes = 0;
		int count;	//  indicates the number of integers have been copied
                // from array1 to array2
    // check edge case
		if(n <= 3) number_Primes = n - 1;
    // init the list that contains all the integers >=2 before N
		for(int i = 2; i <= n; i++){
 			array1[i-2] = i;
		}
    // in the for loop, check an integer is prime by only taking the integer
    // that not divisable by previous primes to another list, and the next
    // integer will be the new prime, since we only have to test primes
    // upto sqrt(n) to know that whether it is prime, iterations will stop
    // when the primes that used for checking are larger than sqrt(N)
 		for(int i = 1; array1[i - 1] <= Math.sqrt(n); i ++){
 			//System.arraycopy(array1, 0, array2, 0, i);
 			int index = i;       // i represnet the known number of primes
 			count = 0;
 			for(int j = i; j < list_Size; j++){
 				if(array1[j] % array1[i - 1] != 0 ){
					array2[index++] = array1[j];
 					count++;
 				}
 			}
 			list_Size = i + count;
 			array1 = array2;
 			number_Primes = list_Size;
 		}
 		return trimArray(array1, number_Primes);
	}

  /*
  * return the prime array within a specific ranges
  */
	public int[] modify_Range(int[] prime_List, int start, int end){
		int[] result = new int[end - start + 1];
		int index_p = 0;
		for(int i =0; i <= prime_List.length; i++){
			if(prime_List[i] >= start && prime_List[i] <= end){
				result[index_p++] = prime_List[i];
			}

		}
		return result;
	}

  // clear all the unprime numbers
	private int[] trimArray(int[] myList, int size){
		int[] outList = new int[size];
		System.arraycopy(myList, 0, outList, 0, size);
		return outList;
	}
  //construct the HashMap for prime and its index for later checking, given that HashMap has
  // O(1) for accessing Key
	private void constructPrimeMap(){
		for(int i = 0; i < this.getPrimeArray().length ; i++){
			this.prime_Table.put(this.primeArray[i], i);
		}
	}

	public HashMap getMap(){
		return this.prime_Table;
	}

	public int getNumberofPrimes(){
		return this.Upper_Bond;
	}

	public int[] getPrimeArray(){
		return this.primeArray;
	}


	@Override
	public String toString() {
        String ouput = "";
		System.out.println("length of array: " + primeArray.length);
		for(int i  = 0; i < primeArray.length; i++){
        	ouput += primeArray[i] + " ";
        	if( (i + 1) % 20 != 0){
        		System.out.printf("%-8d", primeArray[i]);
        	}
        	else {
        		System.out.print("\n");
        	}
        }
		return ouput;
    }
}
