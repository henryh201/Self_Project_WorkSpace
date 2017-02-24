
import java.util.HashMap;
import java.util.ArrayList;

public class Vigenere_Cipher {

  private Cipher_anaylsis tool;
  private String cryptogram;
  private double plain_txt_coincidence = 0.066;
  private int[][] keyset;

  public Vigenere_Cipher(String cryptogram){
    this.cryptogram = cryptogram;
    tool = new Cipher_anaylsis(cryptogram);
  }

  public void plain_txt_coincidence(double coincidence){
    this.plain_txt_coincidence = coincidence;
  }

  public int[][] get_keyset(){
    return this.keyset;
  }


  // determine key length
  public int[]  determine_key_length(int level, int num_possible_length){
    double[] index_coincidence = new double[level];

    for(int i = 1; i<= level;  i++){
      index_coincidence[i - 1] = Math.abs(tool.index_of_coincidence(level) - plain_txt_coincidence);
    }
    int[] possible_lengths = new int[num_possible_length];
    // double for loop to the required num of possible lenght for decipher
    for(int i = 0; i < num_possible_length; i++){
      double min = 1;
      for(int j = 0; j < index_coincidence.length; j++){
        if(index_coincidence[j] < min) {
          // wrap around by +1 to j
          possible_lengths[i] = j + 1;
          min = index_coincidence[j];
        }
      }
      // wrap back when accesing the original index
      index_coincidence[possible_lengths[i] - 1]  = 1;
    }
    return possible_lengths;
  }

  // calcuate the entire keyset for all columns
  public int[][] find_keysets(ArrayList<String> columns, int num_possible_keys){
    this.keyset = new int[columns.size()][num_possible_keys];
    for(int i = 0; i < columns.size(); i++ ){
      keyset[i] = determine_keys_for_column(columns.get(i) , num_possible_keys);
    }
    return keyset;
  }

  // determine the shifted key for this column
  public int[] determine_keys_for_column(String input, int num_possible_keys){
    int[] possible_keys = new int[num_possible_keys];
    double[]  k_frequence = new double[26];

      for(int j = 0; j < 26 ; j++){
        String shifted_string = Shift_Cipher.shift_string(input, j);
        HashMap<Character, Integer> my_map = tool.frequency_analysis(shifted_string);
        if(my_map.containsKey('K') ) {
          k_frequence[j] = Math.abs(my_map.get('K') / shifted_string.length() - this.plain_txt_coincidence);
        }
        else  k_frequence[j] = 0;
      }

      for(int i = 0; i < num_possible_keys; i++){
        double min = 1;
        for(int j = 0; j < k_frequence.length; j++){
          if(k_frequence[j] < min) {
            possible_keys[i] = j;
            min = k_frequence[j];
          }
        }
        k_frequence[possible_keys[i]]  = 1;
      }
    return possible_keys;
  }
}
