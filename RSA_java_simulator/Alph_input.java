/*
* this class is used to simulate the RSA encrytion mechinism and with the ability
* to decrypty the encryted digts list
*/
import java.util.*;
public class Alph_input {
	private HashMap<String , Integer> hmap;
	private HashMap<String, String> demap;
	private ArrayList<Integer> input_int;
	private ArrayList<Integer> encryptList;
	private int primeMod;
	private int powerofInt;
	private String decipher = "";
	private String stringInput;


	public Alph_input(int primeMod, int power){
		if(primeMod <=2525) {
			System.out.println("modulo should larger than 2525");
			System.exit(1);
		}
		this.primeMod = primeMod;
		this.powerofInt = power;
		creatMap();

	}

	public Alph_input(int primeMod, int power, String input){
		if(primeMod <=2525) {
			System.out.println("modulo should larger than 2525");
			System.exit(1);
		}
		this.stringInput  = input.toUpperCase();
		this.primeMod = primeMod;
		this.powerofInt = power;
		creatMap();
		parseString(stringInput);
		encrypt();
	}


	private void creatMap() {
		hmap = new HashMap<String, Integer>();
		demap = new HashMap<String, String>();
		for(int i = 0; i < 26 ; i++){
				hmap.put((char)('A' + i) + "", i);
				if(i < 10) {
				demap.put("0" + i,(char)('A' + i) + "");
				}
				demap.put(i + "",(char)('A' + i) + "");
		}
		hmap.put(" ", 26);
		demap.put("26" , " ");
	}

	private  void parseString(String a){
		input_int = new ArrayList<Integer>();
		int temp = 0;
		for(int i = 0; i < a.length(); i++){
			if(i % 2 == 0) {
				temp += hmap.get(a.charAt(i) + "") * 100;
			}
			if(i % 2 == 1) {
				temp += hmap.get(a.charAt(i) + "");
				input_int.add(temp);
				temp = 0;
			}
		}
		// if the input is odd number of chars
		if(temp != 0) {
			// compensate a space to the end
			input_int.add(temp + 26);
		}
	}

	private void encrypt(){
		encryptList = new ArrayList<Integer>();
		for(int i = 0; i < input_int.size(); i++) {
			int base = input_int.get(i);
			int value = 1;
			for(int j = 0; j < this.powerofInt ; j++){
				value *= base;
				value %= primeMod;
			}
			encryptList.add(value);
		}
	}


	/*
	* functions used to find the inverse of number under modulo
	* return the integer result
	*/
 public int find_inverse_key(){
	 // create an instance of Prime_function class and used euler.phi and
	 // euclidean_inverse() function to find the inverse
	 Prime_function ref_1 = new Prime_function();
	 int phi_value = ref_1.euler_Phi(this.primeMod);
	 return ref_1.euclidean_inverse(this.powerofInt, phi_value);
 }

	/*
	*   companion function used to parse the input key,
	*   if the input key is "0", function will find the inverseKey using existed method
	*   from Prime_function class
	*/
	public void decipher(String input, String deString){
		int inverseKey = 0;
		// when the user type enter
		if(input.equals("")) {
			inverseKey = this.find_inverse_key();
		}
		else inverseKey = Integer.parseInt(input);
		if(inverseKey == -1) {
			System.out.println("Sorry, the inverse number do not exist for the inputs you provided ");
			 System.exit(1);
		}
		decipher(inverseKey , deString);
	}
	/*
	*  decipher function will parse the input string integers and also
	*  decrypty the string based on the inverse key value
	*/
	public void decipher(int inverseofpower, String deString){
		String[] buff = deString.split(" ");
		for(int i = 0; i < buff.length; i++){
			String temp = buff[i];
			for(int j = 0; j < temp.length(); j++){
				if(temp.charAt(j) != '0'){
					temp.substring(j);
					break;
				}
			}
			if(temp == "") temp += "0";
			int base = Integer.parseInt(temp);


			//TODO use fast algo to calculate the power of prime
			int value = 1;
			for(int k = 1; k <= inverseofpower; k++){
				value *= base;
				value %= this.primeMod;
			}
			String mask = value + "";
			int diff = 4 - mask.length();
			for(int k = 0; k < diff; k++ ){
				mask = "0" + mask;
			}
			this.decipher += (demap.get(mask.substring(0, 2)) + demap.get(mask.substring(2)));
		}
	}

	/*
	*  outprint the digits block that the inpu string has converted to
	*/
	public void printInput(){
		for(int i = 0; i < input_int.size(); i++){
			System.out.print(input_int.get(i) + " ");
		}
		System.out.println();
	}
	public void printEncrypt(){
		for(int i = 0; i < encryptList.size(); i++){
			String mask = encryptList.get(i) + "";
			int diff = 4 - mask.length();
			for(int j = 0; j < diff; j++){
				mask = "0" + mask;
			}
			System.out.print(mask + " ");
		}
		System.out.println();
	}
	public void printString(){
		System.out.println(this.stringInput);
	}
	public void printDecifpher(){
		System.out.println("The decrypty String is listed below: \n" + this.decipher  + "\n");
	}

	public ArrayList<Integer> getInput_int(){
		return this.input_int;
	}


	public ArrayList<Integer> getEncrypt(){
		return this.encryptList;
	}

	public String getString(){
		return this.stringInput;
	}

}
