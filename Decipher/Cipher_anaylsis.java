/*
* class for analyzing the input string
*
*/

import java.util.ArrayList;
import java.util.HashMap;


public class Cipher_anaylsis {

  private String cryptogram;
  private ArrayList<String> sorted_columns;
  //default contructor;
  public Cipher_anaylsis(){};

  public Cipher_anaylsis(String cryptogram){
    this.cryptogram = cryptogram;
  }


  public void set_cryptogram(String cryptogram){
    this.cryptogram = cryptogram;
  }

  public ArrayList<String> get_columns(){
    return this.sorted_columns;
  }

  public static HashMap<Character, Integer> frequency_analysis(String analyze_string){
    HashMap<Character, Integer> char_frequency = new HashMap<Character, Integer>();
    char[] input_line = analyze_string.toCharArray();
    for(int i = 0; i < input_line.length; i++){
      if(!char_frequency.containsKey(input_line[i])){
        char_frequency.put(input_line[i], 1);
      } else {
        char_frequency.put(input_line[i], char_frequency.get(input_line[i]) + 1);
      }
    }
    return char_frequency;
  }

  // for given level, that is, the num_columns, calculate the average index of
  // coincidence for all the columns
  public double index_of_coincidence(int level){
    if(cryptogram == null) {
      System.out.println("Please set cryptogram before continue.");
      System.exit(0);
    }
    if(level < 0) {
      System.out.println("Level must be postive Integer");
      System.exit(0);
    }
    double sum = 0;
    this.sorted_columns = Sort_into_column(level);
    for(String column: this.sorted_columns){
      sum += calcuate_index_of_coincidence(column);
    }
    return sum / level;
  }

  // using the given algo, that is, for all i  sum(Fi(Fi - 1))/N(N - 1) where
  // N is the num of chars in the array
  private double calcuate_index_of_coincidence(String input){
    HashMap my_map = frequency_analysis(input);
    double sum = 0;
    double num_chars = input.length();
    for(Object value : my_map.values()){
      int freq = (Integer)value;
      sum += freq*(freq - 1);
    }
    return sum /(num_chars * (num_chars - 1));
  }

  // convert cryptogram into specified num of column
  public ArrayList<String> Sort_into_column(int num_columns){
    // apprioximiate buff_size for each solumn
    ArrayList<String> sorted_columns = new ArrayList<String>();
    if(num_columns == 1) {
      sorted_columns.add(this.cryptogram);
      return sorted_columns;
    }
    int buff_size =  cryptogram.length()/num_columns + 1;
    char[][] cryptogram_into_columns = new char[num_columns][buff_size];
    // using modula to sort the cryptogram into specific columns
    for(int i = 0; i < cryptogram.length(); i++){
      cryptogram_into_columns[i % num_columns][i / num_columns] = cryptogram.charAt(i);
    }
    // convert char[][] to ArrayList<String>
    for(int i = 0;  i < cryptogram_into_columns.length; i++){
      String temp = new String(cryptogram_into_columns[i]);
      sorted_columns.add(temp);
    }
    return sorted_columns;
  }

}
