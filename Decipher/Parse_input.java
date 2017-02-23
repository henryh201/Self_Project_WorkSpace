import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parse_input {

  //Default contructor
  public Parse_input() {};

  // buffer reading the file input
  public static String read_from_file(String file_name) throws FileNotFoundException{
    String input = "";
    File in_file = new File(file_name);
    BufferedReader buffer = new BufferedReader(new FileReader(in_file));
    try{
      String line = buffer.readLine();
      while(line != null){
        input += line.replaceAll("\\s+", "");
        line = buffer.readLine();
      }
      buffer.close();
    } catch(FileNotFoundException e) {
      System.out.printf("Input file: %s can not found %n" , file_name);
    } catch(IOException e){
        System.out.printf("unable to read from file: %s %n", file_name);
    }
    return input.toUpperCase();
  }

}
