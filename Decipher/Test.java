import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Test {
  public static void main(String[] args) throws FileNotFoundException{

    String cryptogram = Parse_input.read_from_file("input.txt");
    Cipher_anaylsis test1 = new Cipher_anaylsis(cryptogram);
    // test1.Sort_into_column(3);
    // ArrayList<String> temp = test1.get_columns();
    // for(String column: temp){
    //   System.out.println(column);
    // }


    HashMap<Character, Integer> my_map = Cipher_anaylsis.frequency_analysis(cryptogram);
    for(char chars: my_map.keySet()){
      System.out.println(chars + ", " + my_map.get(chars));
    }


    for(int i = 1; i <= 10;  i++){
      System.out.println(test1.index_of_coincidence(i));
    }

  }
}
