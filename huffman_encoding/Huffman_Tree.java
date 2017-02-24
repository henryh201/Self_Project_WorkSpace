/*
* the main class for the Huffman_Tree structure
*
*/
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman_Tree{
  final int ASCII_SIZE = 128;
  int[] val_Freq;  // array stores the char and corresponding frequence;
  PiriorityQueue minque;    //  to sort each node with assending order
  HashMap<char , String>  myMap;      // hashmap used to store the result from Huffman_Tree
  ListNode head;      // the head for the huffman_binary tree
  // so that it is faster for converting to huffman_tree

  /*
  * conver the value from val_Freq to listNOde that lives in the minque
  */
  public void convert_to_Listnode() {
    minque = new PiriorityQueue();
    // for loop to iterate through all the node and extract nodes with frequence
    // larger than 0 to the minPiriorityQueue
    for(int i = 0 ; i < this.ASCII_SIZE; i++){
      int frequence = val_Freq[i];
      if( frequence > 0){
        Binary_Node node = new Binary_Node( (char)i , frequence);
        minque.add(node);
      }
    }
  }


  /*
  * methods that help to parsing the input file and update the int[]val_Freq
  * used to create the huffman tree structure, input "filename"
  */
  public void read_Frequence(String fileName){
    // create the new array with size of ASCII_SIZE
    val_Freq = new int[ASCII_SIZE];
    //create scanner to go through the file that ask for encoding
    // and check exception for openning file
    // TODO
    scanner  scr = new Scanner(new File(fileName)) ;
    while(scr.hasNext()){
      String buffer = scr.nextLIne();
      for(int i = 0; i < buffer.length(); i++){
        val_Freq[(int)buffer.charAt(i)]++;     // increaments the frequence
      }
    }
    //close the scanner and the file reader

  }

  /*
  * read the file and convert each characters to corresponding huffman code
  */
  public void parsing_Data(String fileName){

  }

  /*
  *  method that used to construct the huffman_tree according the result
  *  from the PiriorityQueue by keep removing data from the queue
  *
  *  tree is created s.t,  the two nodes with lowest frequence is popped out
  *  and linked by a new parent node that has the sum of frequence, keep doing
  *  so untile the queue is empty;
  */
  public void construct_Tree(){
    // if the Minqueue is not empty keep dequeue node from the queue;
    while(!minque.isEmpty()){


    }
  }
  /*
  *  recursion through the tree to get the encrypted code and store it to the
  *  map
  */
  )
  public void convert_to_map(listNode node){
    // when the node is not internal node, add it to the hashmap
    if(node.getVal != null) {
      this.myMap.add(node.getVal , node.huffman_code);
    }
    // base case, when the chilren are null, return;
    if(node.getLeft == null || node.getRight == null) {
      return;
    }
    // code to assign 0 to left node and 1 to right node
    node.getLeft.appendCode("0");
    node.getRight.appendCode("1");
    convert_to_map(node.getLeft);
    convert_to_map(node.getRight);
  }
}
