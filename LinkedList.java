import java.util.Scanner;

public class LinkedList {
    class Node {   //class node that has data and link part
        int data;
        Node next;

        public Node(int data) { //a function that insert data and link the nodes, by default it points end to null
            this.data = data;
            this.next = null;
        }
    }

    public static Node head; //initialising a head node
    public static Node tail; // initialising a tail node
    public static int size; //for calcuating the size of the linked list

    public void addFirst(int data) {  // a function that add the node to the first of the linkedlist
        Node newNode = new Node(data); //making a node named newNode and sending it to the Node function which insert the data and provide the link to null
        size++;
        if (head == null) {        //check if head is null then making the default node as head = tail= newNode
            head = tail = newNode;
            return;
        }
        newNode.next = head; // if linked list is already created then insert newNode before head 
        head = newNode;      //and update head to be newNode
    }

    public void addLast(int data) {  //this function will add node to the last node
        Node newNode = new Node(data);   //initialising newNode
            size++;
        if (head == null) {               //check for empty linked list
            head = tail = newNode;
            return;
        }
        tail.next = newNode;          //inserting after the tail node i.e the end node
        tail = newNode;                //making the inserted node as tail i.e the last node
    }

    public void add(int index, int data) {           // adding any data to any index
        if (index < 0) {                               //default case when index is negative
            System.out.println("Invalid index");
            return;
        }
        Node newNode = new Node(data);                //initialising newNode
        size++;
        if (index == 0) {                         //inserting at beginning
            newNode.next = head;                 
            head = newNode;
            if (tail == null) {                    // check for the linked list is empty or not
                tail = head;
            }
            return;
        }
        Node current = head;                   //create a new node named current and make it head
        for (int i = 0; i < index - 1 && current != null; i++) {       
            current = current.next;           //traversing till the required node
        }
        if (current == null) {               
            System.out.println("Index out of bounds");
            return;
        }
        newNode.next = current.next;         //breaking the link and inserting newNode in between
        current.next = newNode;
        if (current == tail) {
            tail = newNode;                    //if we reach end of the list then make newNoed as tail
        }
    }

    public void print() {                    //print the linked list
        if (head == null) {         //empty linked list
            System.out.println("Linked List is Empty");
            return;
        }
        Node temp = head;       //temp ko head pe daala
        while (temp != null) {        //jab tak temp null nhi ho jata temp ke data ko print karvado
            System.out.print(temp.data + "->");      //and temp ko temp->next pe daal do
            temp = temp.next;
        }
        System.out.println("null");  //end of linked list me null print karvado
    }

    public int removeFirst(){
        if (size == 0) {
    System.out.println("Empty Linked List"); 
    return Integer.MIN_VALUE; // returning -infinity       
        }
        else if (size == 1) {
    //when head = tail i.e only one node is present
                int val = head.data;
                head = tail = null;
                size =0;
                return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast(){
        if(size == 0){
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val = head.data;
            head = tail = null;
            return val;
        }
        //prev ka index size-2 ke barabar hoga
        //because last index size-1 hota hai
        //ek loop se prev node tak pahunchenge then perform the operation
         Node prev = head; //for traversing till the previous node
        for(int i=0;i<size-2;i++){
            prev = prev.next;
        }
        int val = prev.next.data; //tail ka data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    public static int itrSearch(int key){
        Node temp = head;
        int i=0;
        while(temp!=null){
            if(temp.data == key){ //key found
                return i;
            }
            temp = temp.next;
            i++;
        }
        //key not found
        return -1;
    }
    //helper function -actual recusive function
    public int helper(Node head,int key){
        //base case
        if(head == null){
            return -1;
        }
        //condition
        if(head.data == key){
            return 0;
        }
        int idx = helper(head.next, key);
            if(idx == -1){
                return -1;
            }
        
        return idx +1;
    }
    public int recSearch(int key){
        return helper(head,key);
    }
   
    public void reverse(){
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //update head node
        head = prev;
    }
    
    public void deletenthfromEnd(int n){
        //calculate size of my linkedlist
        int size = 0;
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        if(n == size){//head ko delete karna hai
            head = head.next;
            return;
        }
        //size-n tak jana hai
        int i=1;
        int iTofind = size - n;
        Node prev = head;
        while (i< iTofind) {
            prev = prev.next;
            i++;
        }
        //node reached
        prev.next = prev.next.next;
        return;
    }

    public Node findMid(Node head){
        //using slow fast approach or turtle hare approach
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;  //it gives mid node
    }

    public boolean checkPalindrome(){
        if(head == null || head.next == null){
            return true;
        }
        //step 1 - find mid
        Node midNode = findMid(head);

        //step 2 - reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev; //right half head
        Node left  = head; //head for left part too head hi hai
        
        //step 3 - check for left half  and right half are equal or not
        while(right!=null){ //right se peeche peeche aate atte null na mil jae tab tak
            if(left.data != right.data){
                return false;
            }
            //agar false nhi hue too left ko and right ko ek ek shoft kardo
            left = left.next;
            right = right.next;
        
        }
        return true;
    }
   
    public boolean isCycle(){
        Node fast = head;
        Node slow = head;

        while(fast!=null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                return true; //cycle exists
            }
        }
        return false;
    }
   
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        LinkedList ll = new LinkedList();
       // ll.print();
        ll.addFirst(2);
        // ll.print();
        ll.addFirst(1);
        // ll.print();
        ll.addLast(3);
        // ll.print();
        ll.addLast(4);
        // ll.print();
        ll.add(2, 5);  //2 index pe 5 daal do
        ll.print(); // 
        // System.out.println(ll.size);
        // ll.removeFirst();
        //ll.removeLast();
        //ll.print();
        // System.out.println("Index is "+ ll.itrSearch(4));
        // System.out.println(ll.itrSearch(10));
        // System.out.println("Index is "+ ll.recSearch(4));
        // System.out.println(ll.recSearch(10));
        // ll.reverse(); //O(n) time complexity
        // ll.print();
       // ll.deletenthfromEnd(3);
       //System.out.println(ll.checkPalindrome());
        //System.out.println(ll.isCycle());


    }
}
