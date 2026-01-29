public class LinkedListDeque<T> {

    private class Node{
        private T data;
        private Node previous;
        private Node next;

        Node(T d,Node p,Node n){
            data=d;
            previous=p;
            next=n;
        }
        @Override
        public String toString(){
            return "("+data+")";
        }
    }

    Node sentinel;
    Node lastsentinel;
    int size;
    public LinkedListDeque (){
        sentinel=new Node(null,null,null);
        lastsentinel=sentinel;
        sentinel.next=lastsentinel;
        lastsentinel.previous=sentinel;
        size=0;
    }
   public void addFirst(T item){
       Node temp=new Node(item,null,null);
       sentinel.next.previous=temp;
       temp.next=sentinel.next;
       sentinel.next=temp;
       temp.previous=sentinel;
       size+=1;
    }

    public void addLast(T item){
        Node temp=new Node(item,null,null);
        lastsentinel.previous.next=temp;
        temp.previous=lastsentinel.previous;
        temp.next=lastsentinel;
        lastsentinel.previous=temp;
        size+=1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node temp=sentinel;
        while(temp.next!=sentinel){
            temp=temp.next;
            System.out.print(temp.toString());
        }
    }

    public T removeFirst(){
        Node temp=sentinel.next;
        sentinel.next=temp.next;
        temp.next.previous=sentinel;
        temp.next=null;
        temp.previous=null;
        size--;
        return temp.data;
    }

    public T removeLast(){
        Node temp=lastsentinel.previous;
        temp.previous.next=lastsentinel;
        lastsentinel.previous=temp.previous;
        temp.previous=null;
        temp.next=null;
        return temp.data;
    }

    public T get(int index){
        Node temp=sentinel;
        while(index>0){
            temp=temp.next;
            index--;
        }
        if(index==0){
            return temp.data;
        }
        else{
            return null;
        }
    }
    Node temp=sentinel;
    public T getRecursive(int index){
        temp=temp.next;
        if(index==0){
            return temp.data;
        }
        index--;
        return getRecursive(index);
    }
}
