//Name- Hansi Hettiarachchi  Index no: 110213P
import java.util.Date;
import java.io.*;
import java.util.Scanner;

class Queue{//Queue which hold Integer objects
    int Size;
    int Head;
    int Tail;
    Integer[] queue;

    Queue(int size){
        Size=size;
        Head=0;
        Tail=0;
        queue=new Integer[Size];
    }

    public boolean isFull(){
        if(queue[Head]!=null && Head==Tail){
            return true;
        }
        else{
            return false;
        }
    }
    public void Enqueue(Integer obj){//method to add values to queue if it is not full
        if(this.isFull()){
            System.out.println("Queue is Full");
        }
        else{
            //Tail holds the index of array where new element should be assigned
            queue[Tail]=obj;
            //update value of Tail of queue
                if(Tail==Size-1){
                    Tail=0;
                }
                else{
                    Tail=Tail+1;
               }
       }
    }
    public Integer Dequeue(){ 
        Integer Temp=null;
            if(queue[Head]!=null){//if queue is not empty
                Temp=queue[Head];
                //update value of Head
                if(Head==Size-1){
                    Head=0;
                }
                else{
                    Head=Head+1;
                }
            }
            return Temp;
        }
}

//Queue_2 dequeue 1st inserted element(Head of queue), when insert more elements than length of the queue and  insert new element
class Queue_2 extends Queue{ 
    Queue_2(int size){
        super(size);
    }
    public void insert(Integer obj){
        //if queue is full needs to dequeue head 
        if(this.isFull()){
            Object Temp=Dequeue();
        }
        //after the dequeue enqueue new element
        Enqueue(obj);
    }
    
    public void print(){ //print queue from head to tail
        int k=0;
        if(this.queue[Head]!=null){
            System.out.println(this.queue[Head]);
        }
        if(this.Head==this.Size-1){
            k=0;
        }
        else{
            k=this.Head+1;
        }
        while(k!=this.Tail){
            if(this.queue[k]!=null){
                System.out.println(this.queue[k]);
                if(k==this.Size-1){
                    k=0;
                }
                else{
                    k=k+1;
                }
            }
        }
    }
}
 //class E has attributes for name and a type object
//instances of E use when need to access name of an object of T type without the details of type object
class E<T>{ 
    String Name;
    T T_obj;

    E(T object,String name){
        T_obj=object;
        Name=name;
    }
}

class Node<T>{ //Node is a element of Double Link List
    Node next;
    Node prev;
    //link list can have various type of objects and need to access them by their names rather than the content while searching
    //therefore Node holds a E type object
    E<T> E_obj;

    Node(T object,String name){
        next=null;
        prev=null;
        E_obj=new E(object,name);
    }

}
class LinkList<T>{
    Node<T> Head;
    int Size; //size = number of nodes in the link list
    
    LinkList(){
        Head=null;
        Size=0;
    }
    
    public void add(T obj,String name){
        Node<T> node=new Node(obj,name); //create a Node object using obj and its name
        //update attributes of node which is going to insert to the link list
        node.next=Head; 
        node.prev=null;
        //if list is not emply update attributes of Head of link list
        if(this.Head!=null){
            Head.prev=node;
        }
        //update Head of link list after adding new node
        Head=node;
        Size++; //increase size variable by one
    }
    
   public T search(String name){//search in the link list is done by comparing names of the nodes
        //if link list is empty return a null value
        if(Head==null){
            return null;
        }
        //if link list is not empty
        else{
            Node<T> temp=Head;
            //compare name needs to search with name of Head node
            if(Head.E_obj.Name.equals(name)){
                return Head.E_obj.T_obj;
            }
            else{ //if node need to find is not the Head of list check other nodes  
                while(temp!=null){
                    if(temp.E_obj.Name.equals(name)){
                        return temp.E_obj.T_obj;
                    }
                    temp=temp.next;
                }
            }
        //if link list have the node which has searched, return the type object of corresponding node
        if(temp!=null){ 
            return temp.E_obj.T_obj;
        }
        else{//if list does not has the node return null
            return null;
        }
        }
    }
    
    public void delete(String name){//method to delete a node which has the given name
        //search node from the list
        Node<T> temp=new Node(this.search(name),name);
        //if node exist in the list remove it
        if(temp!=null){
            if(temp.prev!=null){
               temp.prev.next=temp.next;
            }
            else{
                Head=temp.next;
            }
            if(temp.next!=null){
                temp.next.prev=temp.prev;
            }
        }
    }

}



abstract class HashTable <T>{ //is abstract because findKey method and search depend on type of T
    int Size;//size=no: of slots in table
    int no_of_elements; //=no: of whole elements in the hash table
    //used chaining to handle collisions
    //therefore used a array of link lists as the table
    LinkList<T>[] Table; 
    
    HashTable(int size){
        no_of_elements=0;
        Size=size;
        Table=new LinkList[size];
        for(int i=0;i<size;i++){
            Table[i]=new LinkList();
        }
    }
    abstract int findKey(String input);//method to find key value for a given input object
    
    //method to add given elemnt to the hash table
    public void addElement(T obj,String obj_name,int key){
        Table[key].add(obj,obj_name);   
        no_of_elements++; //with each add no of elements increse by 1
    }
    abstract T search(String name);
    
    public void delete(String name){
        T temp= search(name);
        temp=null;
    }
}

//user_of_ProductOrVendor uses to create user lists within Book and Vendor.
//Data stored in this object use to calculate aggregate rating of paticular Book or Vendor
class user_of_ProductOrVendor{ 
            //  String Name;
            //int n; //n=no of times the user has rated a product
    User UserRefference;
    //Rate list stores rates which are made by this user to a book or vendor
    LinkList<Integer> RateList;

    user_of_ProductOrVendor(String name,User user){
        UserRefference=user;
        RateList=new LinkList();
    }
    //method insertRate add the given rate to the Ratelist and update n
    public void insertRate(int rate){
        RateList.add(rate,null); //nodes of RateList do not have names.Therefore each adding passes a null value as its name       
    }
}

class UserList_of_ProductOrVendor extends HashTable<user_of_ProductOrVendor>{ //user list within book and vendor
    int current_key; //current key hold key value whoch is recently found 

    UserList_of_ProductOrVendor(int size){
        super(size);
        current_key=0;
    }
    int findKey(String input){//find key value of input
        int key=0;
        int sum=0;
        int i=0;
        //find sum of ASCII values of letters in the input string
        int n=input.length();
        char[] letters=new char[n];
        for(i=0;i<n;i++){
            letters[i]=input.charAt(i);         
        }
        for(i=0;i<n;i++){
            sum=sum+letters[i];
        }
        //do calculations to find key value
        key=sum%Size;
        current_key=key; //update current_key
        
        return key;
    }
    
    //add method adds the given object to the hash table if it is not in the table 
    public void add(String user_name,int rate,User user){
        
        //search the given object from list by comparing its name
        user_of_ProductOrVendor temp=this.search(user_name);
        
        //if the corresponding object is not exist within the table
        if(temp==null){
            //create new object
            user_of_ProductOrVendor user_of_productorvendor=new user_of_ProductOrVendor(user_name,user);
            //update rate list of the created object
            user_of_productorvendor.insertRate(rate/*, user*/);
            //add the created object to the hash table
            addElement(user_of_productorvendor,user_name,current_key);   
        }
        //if the corresponding object is allready included in the hash table
        else{
            //update rate list of object in hash table
            temp.insertRate(rate); 
        }
    }
    
    //method to search an object, which has the given name from the hash table
    public user_of_ProductOrVendor search(String user_name){
        current_key=findKey(user_name);//find key of the name
        //if Head of the link list at slot corresponding to the key value is not empty
        if(Table[current_key].Head!=null){
            //do search in the link list
            user_of_ProductOrVendor search_result=Table[current_key].search(user_name);
            return search_result;
        }
        //if head is null list never includes the object which needs to find
        else{
            return null;
        }
    }
}

//A min heap which is created by vendor objects.
//values compared by aggregate rating of each vendor
class MinHeap{
    int Length;
    int Heap_size;
    Vendor[] heap;
    
    MinHeap(int length){
        Length=length;
        Heap_size=0;
        heap=new Vendor[Length];
    }
    //add method adds the given object to the heap if it is not full
    public void add(Vendor obj){
        if(Heap_size<Length){
            heap[Heap_size]=obj;
            Heap_size++;
        }
        //if heap is full it prints an error
        else{
            System.out.println("Heap full");
        }
    }

    public void Heapify(int index){
        int right=2*index+2; //find index of the right node of the node at given index
        int left=right-1;//find index of the left node of the node at given index
        int min=index;//assume the node which has maximum value is the node at index
        //if left node is exist and its value is greater than current maximum value replace max by left
        if(left<this.Heap_size &&  heap[left].AggregateRating<heap[min].AggregateRating){           
            min=left;
        }
        //if right node is exist and its value is greater than current maximum value replace max by right
        if(right<this.Heap_size && heap[right].AggregateRating<heap[min].AggregateRating){
            min=right;
        }
        //if any child node of node at index has large value than it, swap the nodes
        if(min!=index){
            Vendor temp=heap[index];
            heap[index]=heap[min];
            heap[min]=temp;
            //after swap call heapify to node at index max
            Heapify(min);               
        }
    }
    
    //method which builds a min heap using an array
    public void buildHeap(){
        //call heapify on each node starting from bottom nodes which has at least one child to the root
        for(int i=(Heap_size/2)-1;i>=0;i--){
            Heapify(i);
        }
    }
    
    public void heapSort(){
        //build a heap before sorting
        buildHeap();
        //sort the heap
        int tempSize=Heap_size-1;
        for(int i=tempSize;i>=1;i--){
            Vendor temp=heap[0];
            heap[0]=heap[i];
            heap[i]=temp;
            Heap_size--;
            Heapify(0);        
    }
    }
    
    public void print(){ //method to print the heap
        for(int i=0;i<Length-1;i++){
            if(heap[i]!=null){
            System.out.println(heap[i].Name);
            }
        }
    }
}

class Book{
    String Name;
    double AggregateRating;
    VendorList_of_Book Vendor_List; //Book has a vendor list which includes details about vendors who sell this book
    UserList_of_ProductOrVendor User_List; //Book has a user list which includes user names and ratings which are added to this book by each user
    Queue_2 Ratings; //Ratings holds reacent ratings only
    
    Book(String name){
        Name=name;
        AggregateRating=0;
        Vendor_List=new VendorList_of_Book(47);     
        User_List=new UserList_of_ProductOrVendor(47);
        Ratings =new Queue_2(5);
    }
    
    public void addRating(int Rate,String user_name,User user){
        Ratings.insert(Rate); //add rating to the queue of recent ratings
        User_List.add(user_name, Rate,user); //update User_List(add rate of the user) 
    }
    public void addVendor(Vendor vendor){ //if vendor is not included in vendor list add the vendor to the list
        Vendor_List.add(vendor);
    }

    public void getRecentRatings(){ //prints recent ratings which are added to the book
        for(int i=0;i<Ratings.Size;i++){
            if(Ratings.queue[i]!=null){
                System.out.println(Ratings.queue[i]);
            }
            else{
                break;
            }
        }
    }
    public void findAggregateRating(){
        double sigma_r=0;
        double w=0;
        double sigma_w_r=0;
        double sigma_w_k=0;
        
        for(int i=0;i<this.User_List.Size;i++){
            if(this.User_List.Table[i]!=null){
                Node<user_of_ProductOrVendor> user=this.User_List.Table[i].Head;
                while(user!=null){
                    sigma_r=0;
                    Node<Integer> rate=user.E_obj.T_obj.RateList.Head;
                    while(rate!=null){
                        sigma_r=sigma_r+rate.E_obj.T_obj;
                        rate=rate.next;
                    }
                    
                    w=2-(1.0/user.E_obj.T_obj.UserRefference.counter);

                    sigma_w_r=sigma_w_r+(w*sigma_r);
                    
                    sigma_w_k=sigma_w_k+(w*user.E_obj.T_obj.RateList.Size);
                    
                    user=user.next;
                }
            }
        }
        
        this.AggregateRating=sigma_w_r/sigma_w_k;
            //System.out.println("aggragate rate of " +this.Name + " - " + this.AggregateRating);
    }
    
    public void topRatedVendors(){ //method to find top rated vendors of this book
       //create min heap and copy vendor list to it
       MinHeap Vendor_heap=new MinHeap(Vendor_List.no_of_elements);
       Vendor_List.copy(Vendor_heap);
       
       //sort the heap
       Vendor_heap.heapSort();
       
       //print details of top rated vendors
       for(int i=0;i<Vendor_heap.Length;i++){
            System.out.println((Vendor_heap.heap[i]).Name + " - " + Vendor_heap.heap[i].AggregateRating);
        }
    
    }
}
       
class Vendor{
    String Name;
    double AggregateRating;
    BookList_of_Vendor Book_List;//Vendor has a book list which includes details about books he sell
    UserList_of_ProductOrVendor User_List;//Vendor has a user list which includes user names and ratings which are added to this vendor by each user
    Queue_2 Ratings; //Ratings holds reacent ratings only
    
    Vendor(String name){
        Name=name;
        AggregateRating=0;
        Book_List=new BookList_of_Vendor(47);
        User_List=new UserList_of_ProductOrVendor(47);
        Ratings =new Queue_2(5);
    }
    
    public void addRating(int Rate,String user_name,User user){
       
        Ratings.insert(Rate); //add rating to the queue of recent ratings
        User_List.add(user_name, Rate,user); //update User_List(add rate of the user) 
    }
    public void addBook(Book book){//if book is not included in book list add it to the list
        Book_List.add(book);
    }

    public void getRecentRatings(){//prints recent ratings which are added to the book
        for(int i=0;i<Ratings.Size;i++){
            if(Ratings.queue[i]!=null){
                System.out.println(Ratings.queue[i]);
            }
            else{
                break;
            }
        }
    }
    public void findAggregateRating(){
        double sigma_r=0;
        double w=0;
        double sigma_w_r=0;
        double sigma_w_k=0;

        for(int i=0;i<this.User_List.Size;i++){
            if(this.User_List.Table[i]!=null){
                Node<user_of_ProductOrVendor> user=this.User_List.Table[i].Head;
                while(user!=null){
                    sigma_r=0;
                    Node<Integer> rate=user.E_obj.T_obj.RateList.Head;
                    while(rate!=null){
                        sigma_r=sigma_r+rate.E_obj.T_obj;
                        rate=rate.next;
                    }
                    
                    w=2-(1.0/user.E_obj.T_obj.UserRefference.counter);

                    sigma_w_r=sigma_w_r+(w*sigma_r);
                    
                    sigma_w_k=sigma_w_k+(w*user.E_obj.T_obj.RateList.Size);
                    
                    user=user.next;
                }
            }
        }
        
        this.AggregateRating=sigma_w_r/sigma_w_k;
            //System.out.println("aggragate rate of " + this.Name+ " - " +this.AggregateRating);
    }
    
    public void showProducts(){ //method prints name and aggregate rating of each product which sell by this vendor
        for(int i=0;i<Book_List.Size;i++){
            Node<Book> temp=Book_List.Table[i].Head;
            while(temp!=null){
                System.out.println(temp.E_obj.Name + " - " + temp.E_obj.T_obj.AggregateRating);
                temp=temp.next;
            }
        }
    }
}

class User{
    String Name;
    int counter; //counter holds number of times the user has rated a product/vendor
    
    User(String name){
        Name=name;
        counter=0;
    }
     public void countRate(){//method to update counter of the user
        counter++;
     }   
}

class BookList extends HashTable<Book>{
    int current_key; //current key stores the recently found key
    BookList(int size){
        super(size);
        current_key=0;
    }
    int findKey(String input){//find key value of input
        int key=0;
        int sum=0;
        int i=0;
        //find sum of ASCII values of letters in the input string
        int n=input.length();
        char[] letters=new char[n];
        for(i=0;i<n;i++){
            letters[i]=input.charAt(i);         
        }
        for(i=0;i<n;i++){
            sum=sum+letters[i];
        }
        //do calculations to find key value
        key=sum%Size;
        current_key=key;

        return key;
    }
    
    //add method adds the given object to the hash table if it is not in the table
    public Book add(String book_name){
        //search the given object from list by comparing its name
        Book temp=this.search(book_name);
        
        //if the corresponding object is not exist within the table
        if(temp==null){
 
            //create new object, add it and return it
            Book book=new Book(book_name);
            addElement(book,book_name,current_key);
            return book;
        }
        //if the corresponding object exist within the table return it
        else{

            return temp;
        }
    }
    
    //method to search an object, which has the given name from the hash table
    public Book search(String book_name){
        current_key=findKey(book_name);
        //if Head of the link list at slot corresponding to the key value is not empty
        if(Table[current_key].Head!=null){
            //do search in the link list
            Book search_result=Table[current_key].search(book_name);
            return search_result;
        }
        //if head is null list never includes the object which needs to find
        else{
            return null;
        }
    }
}

class BookList_of_Vendor extends BookList{ // a polymorphic type of BookList
    BookList_of_Vendor(int size){
        super(size);
    }
    //add method of BookList_of_Vendor gets a book object as the argument
    //add a rerence to a book object which is exist, if it is not in the list
    public void add(Book book){ 
        int value=0;
        Book temp=this.search(book.Name);       
        if(temp==null){
            addElement(book,book.Name,current_key);
        }
    }
}

class VendorList extends HashTable<Vendor>{
    int current_key; //current key stores the recently found key
    VendorList(int size){
        super(size);
        current_key=0;
    }
    int findKey(String input){//find key value of input
        int key=0;
        int sum=0;
        int i=0;
        //find sum of ASCII values of letters in the input string
        int n=input.length();
        char[] letters=new char[n];
        for(i=0;i<n;i++){
            letters[i]=input.charAt(i);         
        }
        for(i=0;i<n;i++){
            sum=sum+letters[i];
        }
        //do calculations to find key value
        key=sum%Size;
        current_key=key;

        return key;
    }
    
    //add method adds the given object to the hash table if it is not in the table
    public Vendor add(String vendor_name){
        //search the given object from list by comparing its name
        Vendor temp=this.search(vendor_name);     
        //if the corresponding object is not exist within the table
        if(temp==null){
                
            //create new object, add it and return it
            Vendor vendor=new Vendor(vendor_name);
            addElement(vendor,vendor_name,current_key);
            return vendor;
        }
        //if the corresponding object exist within the table return it
        else{
               
            return temp;
        }
    }
    
    //method to search an object, which has the given name from the hash table
    public Vendor search(String vendor_name){
        current_key=findKey(vendor_name);
        //if Head of the link list at slot corresponding to the key value is not empty
        if(Table[current_key].Head!=null){
            //do search in the link list
            Vendor search_result=Table[current_key].search(vendor_name);
            return search_result;
        }
        //if head is null list never includes the object which needs to find
        else{
            return null;
        }
    }
}

class VendorList_of_Book extends VendorList{ // a polymorphic type of VendorList
    VendorList_of_Book(int size){
         super(size);
    }
    //add method of VendorList_of_Vendor gets a vendor object as the argument
    //add a rerence to a vendor object which is exist, if it is not in the list    
    public void add(Vendor vendor){
        Vendor temp=this.search(vendor.Name);
        if(temp==null){
            addElement(vendor,vendor.Name,current_key);
        }
    }
    public void copy(MinHeap vendor_heap){ //method to copy list to an array

        for(int i=0;i<this.Size;i++){
            if(this.Table[i]!=null){
                Node<Vendor> node=this.Table[i].Head;
                while(node!=null){
                    vendor_heap.add(node.E_obj.T_obj);
                    node=node.next;
                }
            }
        }
    }   
}

class UserList extends HashTable<User>{
    int current_key; //current key stores the recently found key
    UserList(int size){
        super(size);
        current_key=0;
    }
    int findKey(String input){//find key value of input
        int key=0;
        int sum=0;
        int i=0;
        //find sum of ASCII values of letters in the input string
        int n=input.length();
        char[] letters=new char[n];
        for(i=0;i<n;i++){
            letters[i]=input.charAt(i);         
        }
        for(i=0;i<n;i++){
            sum=sum+letters[i];
        }
        //do calculations to find key value
        key=sum%Size;
        current_key=key;

        return key;
    }
    
    //add method adds the given object to the hash table if it is not in the table
    public User add(String user_name){
        //search the given object from list by comparing its name
        User temp=this.search(user_name);
        //if the corresponding object is not exist within the table
            
        if(temp==null){
            
            //create new object, add it and return it
            User user=new User(user_name);
            addElement(user,user_name,current_key);
                            
            return user;
        }
        //if the corresponding object exist within the table return it
        else{
            
            return temp;
        }
    }
    
    //method to search an object, which has the given name from the hash table
    public User search(String user_name){
        current_key=findKey(user_name);
        //if Head of the link list at slot corresponding to the key value is not empty
        if(Table[current_key].Head!=null){
            //do search in the link list
            User search_result=Table[current_key].search(user_name);
            return search_result;
        }
         //if head is null list never includes the object which needs to find
        else{
            return null;
        }
    }
}

//input string given by a user creates UserInput objects
class UserInput{
    Date date;
    String User_Name;
    String Book_Name;
    String Vendor_Name;
    int Vendor_Rating;
    int Book_Rating;
    
    //split input string and assign values to variables
    UserInput(String input){
        String[] inputlist=input.split(" ");
        findDate(inputlist[0]);
        User_Name=inputlist[1];
        Book_Name=inputlist[2];
        Vendor_Name=inputlist[3];
        Vendor_Rating=Integer.parseInt(inputlist[4]);
        Book_Rating=Integer.parseInt(inputlist[5]);
    }
   
  //create a Date object using an input string time stamp
   private void findDate(String s){
        String[] split1=s.split("T");
        String date1=split1[0];
        String time=split1[1];
        
        String[] splitDate=date1.split("-");
        String[] splitTime=time.split(":");
        
        date=new Date(Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2]),Integer.parseInt(splitTime[0]),Integer.parseInt(splitTime[1]));
        
   }
}


//A max heap which is created by UserInput objects.
//values compared by time of each input
class MaxHeap{ 
    int Length;
    int Heap_size;
    UserInput[] heap;
    
    MaxHeap(int length){
        Length=length;
        Heap_size=0;
        heap=new UserInput[Length];
    }
    //add method adds the given object to the heap if it is not full
    public void add(String st){
        UserInput user_input=new UserInput(st);
        if(Heap_size<=Length){
            heap[Heap_size]=user_input;
            Heap_size++;
        }
        //if heap is full it prints an error
        else{
            System.out.println("Heap full");
        }
    }
    public void Heapify(int index){
        int right=2*index+2; //find index of the right node of the node at given index
        int left=right-1;//find index of the left node of the node at given index
        int max=index;//assume the node which has maximum value is the node at index
        //if left node is exist and its value is greater than current maximum value replace max by left
        if(left<this.Heap_size &&  heap[left].date.after(heap[max].date)){           
            max=left;
        }
        //if right node is exist and its value is greater than current maximum value replace max by right
        if(right<this.Heap_size && heap[right].date.after(heap[max].date)){
            max=right;
        }
        //if any child node of node at index has large value than it, swap the nodes
        if(max!=index){
            UserInput temp=heap[index];
            heap[index]=heap[max];
            heap[max]=temp;
            //after swap call heapify to node at index max
            Heapify(max);               
        }
    }
    
    //method which builds a max heap using an array
    public void buildHeap(){
        //call heapify on each node starting from bottom node which has at least one child to the root
        for(int i=(Heap_size/2)-1;i>=0;i--){
            Heapify(i);
        }
    }
    public void heapSort(){
        //build a heap before sorting
        buildHeap();
        //sort the heap
        int tempSize=Heap_size-1;
        for(int i=tempSize;i>=1;i--){
            UserInput temp=heap[0];
            heap[0]=heap[i];
            heap[i]=temp;
            Heap_size--;
            Heapify(0);
        }
    }
}
//Book market has book list which hold details about books sell in the market,
//vednor list which hpld details about vendors who sell the books and
//user list which hold details about users who access market
class BookMarket{
    BookList book_list;
    VendorList vendor_list;           
    UserList user_list;
    MaxHeap UserInputs;
    
    BookMarket(){
        book_list= new BookList(47);
        vendor_list=new VendorList(47);
        user_list= new UserList(47);
        UserInputs=new MaxHeap(1500); //Assume maximum number of inputs given at a time =1500
    }
    
    //method to read inputs from the input file and create UserInputs
    public void getInputs(String file_name){
        UserInput input=null;
        try{
            BufferedReader br = new BufferedReader( new FileReader(file_name));
            String st = br.readLine();
            while(st != null) {
                UserInputs.add(st); //add created UserInput objects to the max heap
                st = br.readLine();              
            }
        }catch(Exception ex){
            System.out.println("error");
        }
    }
    
    //method which update details of the market after getting inputs
    public void update(){
        //sort userinputs by time before do updates
        UserInputs.heapSort();;
        
        //for each UserInput object in the heap
        for(int i=0;i<UserInputs.Length;i++){
            if(UserInputs.heap[i]!=null){
            //if user does not exist in user_list create new user and add or find user in user_list
            User user=user_list.add(UserInputs.heap[i].User_Name);
            user.countRate(); //increase counter of user by 1
            
            
            //if book does not exist in book_list create new book and add or find book in book_list
            Book book=book_list.add(UserInputs.heap[i].Book_Name);
            //if vendor does not exist in vendor_list create new vendor and add or find vendor in vendor_list
            Vendor vendor=vendor_list.add(UserInputs.heap[i].Vendor_Name);
            
            //add book rate to the ratings queue in book 
            //if user_of_ProductOrVendor which has user_name does not exist in User_List of book create new object and update it 
            //or update object in User_List
            book.addRating(UserInputs.heap[i].Book_Rating, user.Name,user);
            //add refference of vendor object to the vendor list of book if it is not included
            book.addVendor(vendor);
 
            //add vendor rate to the ratings queue in vendor 
            //if user_of_ProductOrVendor which has user_name does not exist in User_List of vendor create new object and update it 
            //or update object in User_List
            vendor.addRating(UserInputs.heap[i].Vendor_Rating, user.Name,user);
            //add reffrence of book object to the Book list of vendor if it is not included
            vendor.addBook(book);;                        
            }

            
        }
            //find aggregate rate of each book
            for(int k=0;k<book_list.Size;k++){
                Node<Book> temp=book_list.Table[k].Head;
                    while(temp!=null){
                        temp.E_obj.T_obj.findAggregateRating(); 
                        temp=temp.next;
                    }
            }
            System.out.println("\n");
            //find aggregate rate of each vendor
            for(int k=0;k<vendor_list.Size;k++){
                    //System.out.println("\t" + k);
                Node<Vendor> temp=vendor_list.Table[k].Head;
                    while(temp!=null){
                            //System.out.println("\n " +temp.E_obj.Name);
                        temp.E_obj.T_obj.findAggregateRating(); 
                        temp=temp.next;
                    }
            } 
            
    }
    
    public void bookSearch(String book_name){
        //List 5 most recent ratings for the product
        Book temp=this.book_list.search(book_name);
        if(temp!=null){
            System.out.println("\n 5 most recent ratings for the "+book_name );
            temp.Ratings.print();       
    
        //Overall aggregate rating of product
        System.out.println("\n Overall aggregate rating= " +temp.AggregateRating);
        
        //Top rated vendors of the product
        System.out.println("\n Top rated vendors");
        temp.topRatedVendors();
        }
        
        else{
            System.out.println("\n Book " + book_name + " is not available");
        }
    }
    public void vendorSearch(String vendor_name){
        //List 5 most recent ratings for the product
        Vendor temp=this.vendor_list.search(vendor_name);
        if(temp!=null){
        System.out.println("\n 5 most recent ratings for the "+vendor_name );       
        temp.Ratings.print();
        
        //Overall aggregate rating of product
        System.out.println("\n Overall aggregate rating= " +temp.AggregateRating);
        
        //List of products that vendor sells and overall aggregate rating of each product
        System.out.println("\n List of products that vendor sells and overall aggregate rating of each product");
        temp.showProducts();
        }
        else{
            System.out.println("\n Vendor " + vendor_name + " is not available");
        }
    }
}
public class tester {
    BookMarket book_market=new BookMarket();
    Scanner input=new Scanner(System.in);
    String s=null;
    
    public void addInputs(){
        book_market.getInputs("input2.txt");
        //book_market.getInputs("input.txt");
        book_market.update(); //update the sytem with new data       
    }

    
    public void getUserInput(){ 
        s=input.nextLine();
        while(!s.equals("-1")){
            this.test();
            s=input.nextLine();
        }
        

    }
    
    public void test(){
        if(s.equals("V")){
            System.out.println("*********** Vendor Search **************");
            System.out.print("Please give vendor name- \t");
            s=input.nextLine();
            
            book_market.vendorSearch(s);
        }
        if(s.equals("B")){
            System.out.println("*********** Book Search **************");
            System.out.print("Please give book name- \t");
            s=input.nextLine();
            
            book_market.bookSearch(s);
        }
    }
    public static void main(String[] args) {
        tester T=new tester();
        T.addInputs();
        T.getUserInput();
    }
}

