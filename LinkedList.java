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

    public Node head; //initialising a head node
    public Node tail; // initialising a tail node

    public void addFirst(int data) {  // a function that add the node to the first of the linkedlist
        Node newNode = new Node(data); //making a node named newNode and sending it to the Node function which insert the data and provide the link to null
        if (head == null) {        //check if head is null then making the default node as head = tail= newNode
            head = tail = newNode;
            return;
        }
        newNode.next = head; // if linked list is already created then insert newNode before head 
        head = newNode;      //and update head to be newNode
    }

    public void addLast(int data) {  //this function will add node to the last node
        Node newNode = new Node(data);   //initialising newNode
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        LinkedList ll = new LinkedList();
        ll.print();
        ll.addFirst(2);
        ll.print();
        ll.addFirst(1);
        ll.print();
        ll.addLast(3);
        ll.print();
        ll.addLast(4);
        ll.print();
        ll.add(2, 5);  //2 index pe 5 daal do
        ll.print();
    }
}
