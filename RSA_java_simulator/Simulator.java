import java.util.*;

public class Simulator {

	public static void main(String[] args) {



		Scanner scr = new Scanner(System.in);
		String choice = "";

		do {

			System.out.println("1 for encrypt\n" + "2 for decipher\n" + "3 for exit");
			choice = scr.nextLine().trim();
			if(choice.equals("1")) {
				System.out.print("Enter the modulo and the power, seperate by space. ");
				System.out.println("Note: modulo should larger than 2525 to make the Algo work");
				String[] temp = scr.nextLine().split(" ");
				System.out.println("Enter word input seperate by space");
				String input = scr.nextLine();
				Alph_input test1 = new Alph_input(Integer.parseInt(temp[0].trim()), Integer.parseInt(temp[1].trim()), input.trim());
				System.out.println("the string is converted to corresponding digits block");
				test1.printInput();
				System.out.println("Encrypt list");
				test1.printEncrypt();
			}

			else if(choice.equals("2")) {
				System.out.print("Enter the modulo and the power, seperate by space. ");
				System.out.println("Note: modulo should larger than 2525 to make the Algo work");
				String[] temp = scr.nextLine().split(" ");
				System.out.println("Enter dcipher int string input seperate by space");
				String input = scr.nextLine();
				Alph_input test2 = new Alph_input(Integer.parseInt(temp[0].trim()), Integer.parseInt(temp[1].trim()));
				System.out.println("Enter the inverse key  or Just type Enter if you do not know.");
				String key = 	scr.nextLine();
				test2.decipher(key, input);
				test2.printDecifpher();
			}

		}while(!choice.equals("3"));
		scr.close();


	}

}
