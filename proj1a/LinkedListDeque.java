public class LinkedListDeque<T> {

    private static class Node<T>{
        private T data;
        private Node<T> previous;
        private Node<T> next;

        Node(Node<T> p,T d,Node<T> n){
            data=d;
            previous=p;
            next=n;
        }
        Node(T d,Node<T> n){
            data=d;
            next=n;
            previous=null;
        }
        Node(Node<T> n,T d){
            data=d;
            next=null;
            previous=n;
        }
        public Node(T i) {
            previous = null;
            data = i;
            next = null;
        }
        @Override
        public String toString(){
            return "("+data+")";
        }
    }

    Node<T> sentinel;
    Node<T> lastsentinel;
    int size;
    public LinkedListDeque (){
        sentinel=null;
        lastsentinel=null;
        size=0;
    }
    public LinkedListDeque(T item) {
        sentinel = new Node<>(item);
        lastsentinel = sentinel;
        size = 1;
    }
    public void addFirst(T item){
        sentinel=new Node<>(item,sentinel);
        if(size>0){
            sentinel.next.previous=sentinel;
        }
        else{
            lastsentinel=sentinel;
        }
        size+=1;
    }

    public void addLast(T item){
        lastsentinel=new Node<>(lastsentinel,item);
        if(size>0){
            lastsentinel.previous.next=lastsentinel;
        }
        else{
            sentinel=lastsentinel;
        }
        size+=1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node<T> temp=sentinel;
        while(temp!=null){
            System.out.print(temp.data);
            temp=temp.next;
        }
    }

    public T removeFirst(){
        if(size!=0){
            Node <T> temp=sentinel;
            if(size!=1){
                sentinel=sentinel.next;
                sentinel.previous=null;
            }
            else{
                sentinel=null;
                lastsentinel=null;
            }
            temp.next=null;
            T data=temp.data;
            temp=null;
            size--;
            return data;
        }
        else{
            return null;
        }
    }

    public T removeLast(){
        if(size!=0){
            Node<T> temp=lastsentinel;
            if(size!=1){
                lastsentinel=lastsentinel.previous;
                lastsentinel.next=null;
            }
            else{
                sentinel=null;
                lastsentinel=null;
            }
            temp.previous=null;
            T data=temp.data;
            temp=null;
            size--;
            return data;
        }
        else{
            return null;
        }
    }

    public T get(int index){
        T data;
        if(index<0||index>size){
            return null;
        }
        else{
            Node<T> temp=sentinel;
            while(index>0){
                temp=temp.next;
                index--;
            }
            data=temp.data;
        }
        return data;
    }

    private T getRecursiveHelper(Node<T> curr,int index){
        if(index==0){
            return curr.data;
        }
        return getRecursiveHelper(curr.next,index-1);
    }

    public T getRecursive(int index){
        if(index<0||index>=size){
            return null;
        }
        else{
            return getRecursiveHelper(sentinel.next,index);
        }
    }
}