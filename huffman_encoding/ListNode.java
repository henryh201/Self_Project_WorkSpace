/*
* Listnode class for storing data needed in the file
* in this case, data will be the frequence probability and the character
* it will store the character and the frequence corresponding to that character
*/
public class Binary_Node implement Comparator<ListnNode>{
  double frequence;
  char val;
  String huffman_code;
  Binary_Node left;
  Binary_Node right;
  /*
  * constructor for the internal node of the huffman tree
  * only receive frequence and next node withouth val
  */
  public Binary_Node(double frequence, char val){
    this.frequence = frequence;
    this.val = val;
  }

  /*
  *  constructor for the internal node of the huffman tree, that is, the node
  *  only has the frequence without the char value
  *
  */
  public Binary_Node( double frequence, Binary_Node left, Binary_Node right){
    this.frequence = frequence;
    this.left = left;
    this.right= right;
  }

  public void setLeft (Binary_Node left){
    this.left = left;
  }
  public void setRight(Binary_Node right){
    this.right = right;
  }
  public void setFreq(double frequence){
    this.frequence = frequence;
  }
  public void appendCode(String code) {
    this.huffman_code = this.huffman_code + code;
  }

  public Binary_Node getLeft(){
    return this.left;
  }
  public Binary_Node getRight(){
    return this.right;
  }
  public char getVal(){
    return this.val;
  }
  public double getFreq(){
    return this.frequence;
  }

  /*
  *  Override the compareTo nethod so that tbe PriorityQueue is able to sort the
  *  node accroding their frequences
  */
  @Override
  public int compareTo(ListNode n){
    return this.frequence - n.frequence;
  }
}
