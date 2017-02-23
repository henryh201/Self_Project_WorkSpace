
/*
* class that implement the functions that related to prime number
* Author: Yuqi Huang
*/
import java.util.*;
public class Prime_function{

  // give the defaut_size for finding prime number before N
  final static int DEFAULT_SIZE = 10000;
  // store all the divisors for given integer
  HashMap<Integer, Integer> divisors;
  // creat the Prime_table to find all prime numbers
  Primes_before_N prime_table;
  // store result for modulo power
  int power_result = 1;

  /*
  * the defaul contructor will create the prime_table N = 10000;
  */
  public Prime_function(){
  prime_table = new Primes_before_N(DEFAULT_SIZE);
  }

  /*
  * the overwrite contructor will create the prime_table N = size;
  */
  public Prime_function(int size){
  prime_table = new Primes_before_N(size);
  }

  /*
  * finding the inverse of a number by Eckudean, input:the base and the modulo
  * return the result, the inverse of base if we can find, otherwise, return
  * -1 indicates that inverse do not exist;
  */
  public int euclidean_inverse(int base, int modulo){

    // if base is a divisor of the modulo or modulo is a divisor of base, return
    // -1
    if(base % modulo == 0 || modulo % base == 0)  return -1;

    // if base is larger than modulo, change it to incongruent number
    int divisor = base % modulo;

    // variable to kecp tracking the value for each iterations
    int dividor = modulo;
    int divisor_represent, remainder_represent;
    divisor_represent = 0;
    remainder_represent = 1;
    do{
      int pre_represent = remainder_represent;
      remainder_represent =  -(dividor / divisor) * remainder_represent + divisor_represent;
      divisor_represent = pre_represent;
      int temp = divisor;
      divisor = dividor % divisor;
      dividor = temp;

    }while(dividor % divisor != 0);
    // check the value is returned is always positve
    remainder_represent = (remainder_represent > 0)?
    remainder_represent : modulo + remainder_represent;
    // indicates that the reverse do not exist when gcd(base, modulo) != 1;
    if(divisor != 1) return -1;
    return remainder_represent % modulo;
  }


  /*
  * findingthe inverse of a number by Gauss method, input:the base and the modulo
  * return the result, the inverse of base if we can find, otherwise, return
  * -1 indicates that inverse do not exist;
  */
  public int gauss_inverse(int base, int modulo){
    //TODO
  return 0;
  }

  /*
   * functions used to find the euler phi number for modulo calculations
   * input: the number that is in query, return the integer result
   * the fucntion's ability is limited by the power that the prime talble have
  */
  public int euler_Phi(int input){
    //TODO
    int phi = 1;
    // decompse the integer to its divisors, then the phi_value for the integer
    this.decompose_Integer(input);

    // using iterator method to go through all divisors
    Iterator itr = divisors.entrySet().iterator();
    while(itr.hasNext()){
      Map.Entry my_map = (Map.Entry)itr.next();
      // extract the base and the power
      int base = (int)my_map.getKey();
      int power = (int)my_map.getValue();
      // according to phi defination, when the power of prime divisor p > 1,
      // the result should be p^i - p^(i -1) and given that phi is multiplcative
      // functions, phi(n) = phi(p_1 ^ r1) * phi(p_2 ^ r2)*...*phi(p_k ^ rk), where
      // p_i is one of the prime divisors
      if(power > 1) {
        phi *= (powerInt(base, power) - powerInt(base, power - 1));
      }
      else {
        phi*= (powerInt(base, power) - 1);
      }
    }
    return phi;
  }


  /*
  *  decompose the integer into the its prime divisors, it will be a HashMap
  *  with each entry contains [p_i , k_i] where p_i is the prime divisor and the
  *  key, k_i is the power of divisor and the value.
  */
  public void decompose_Integer(int input){
    //TODO
    // if the input integer is larger than defaut_size value;
    if(input > DEFAULT_SIZE) {
      this.prime_table = new Primes_before_N(input);
    }
    divisors = new HashMap();

    // get all the prime_numbers before input and use the prime_table to check
    // for the divisors
    for(int e : prime_table.getPrimeArray()){
      int count = 0;
      // keep dividing the integer e to count the power of e within input
      while(input % e == 0){
        input = input / e;
        count++;
      }
      // store the value input to divsors map if the power is larger than 0;
      if(count > 0) divisors.put(e , count);
      // stop iterations when the input is 1
      if(input == 1) break;
    }
  }

  /*
  * check if a number is a prime number
  * return boolean as reuslt
  */
  public boolean check_prime(int input){
    // TODO
    return false;
  }

  /*
  * functions that print out the layout of the prime divisors
  */
  public void print_prime_divisors(){
    // using iterator method to good through the map
    Iterator itr = divisors.entrySet().iterator();
    while(itr.hasNext()){
      Map.Entry my_map = (Map.Entry)itr.next();
      System.out.println(my_map.getKey() + "^" + my_map.getValue());
    }
  }


  /*
  *  companion method used to calcluate the modulo_power only with O(logN) complexity
  *  method here stores the previous power andafe
  *
  */
  public int fast_Power(int base, int mod, int dest_Power){
     int init_Power = 1;
    modulo_power(init_Power, base, dest_Power,  mod);
    return this.power_result;
  }

  private int modulo_power(int cur_power, int val, int power, int mod){
    // base case when cur_power > then the dest power
    if(cur_power > power){
      return power;
    }
    power = modulo_power(cur_power * 2, val * val % mod,  power, mod);
    if(power >= cur_power){
      this.power_result  = this.power_result * val % mod;
      return power - cur_power;
    }
    return power;
  }


  /*
  * helper method used to calculate the power of integer.
  * input base and power, return the result;
  */
  public int powerInt(int base, int power){
    int result = 1;
    for(int i = 1;  i <= power; i++){
      result *= base;
    }
    return result;
  }
}
