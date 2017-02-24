import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Test {
  public static void main(String[] args) throws FileNotFoundException{

    String cryptogram = Parse_input.read_from_file("input.txt");
    Cipher_anaylsis tool = new Cipher_anaylsis(cryptogram);
    // test1.Sort_into_column(3);
    // ArrayList<String> temp = test1.get_columns();
    // for(String column: temp){
    //   System.out.println(column);
    // }

    HashMap<Character, Integer> my_map = Cipher_anaylsis.frequency_analysis(cryptogram);
    for(char chars: my_map.keySet()){
      System.out.println(chars + ", " + my_map.get(chars));
    }
    // for(int i = 1; i <= 10;  i++){
    //   System.out.println(test1.index_of_coincidence(i));
    // }

    Vigenere_Cipher test2 = new Vigenere_Cipher(cryptogram);
    int [] result = test2.determine_key_length(20, 5);
    for(int i = 0; i< result.length ; i++) {
      System.out.println("key lenght: " + result[i]);
    }

    ArrayList<String> columns =  tool.Sort_into_column(result[0]);
    int [][] keyset = test2.find_keysets(columns, 3);
    print_keyset(keyset);

    int[] keys = new int[keyset.length];
    for(int i = 0; i < keyset.length ; i++){
      keys[i] = keyset[i][0];
    }

    test2.change_to_plain_txt(columns , keys);

  }

  public static void print_keyset(int [][] keyset) {
    for(int i = 0; i < keyset.length; i++){
        System.out.println("In " + i + "column" );
      for(int j = 0; j < keyset[0].length; j++){
        System.out.print(keyset[i][j] + " ");
      }
      System.out.println();
    }
  }
}
