
public class Shift_Cipher{
  public Shift_Cipher(){};

  // simple method for Shift the entire input according to shift_value;
  public static String shift_string(String input, int shift_value){
    String out = "";
    for(int i = 0; i < input.length(); i++){
      int new_char = ((int)input.charAt(i) - (int)'A' + shift_value) % 26;
      out += (char)(new_char + (int)'A');
    }
    return out;
  }


}
