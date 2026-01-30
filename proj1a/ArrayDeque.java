public class ArrayDeque <T>{

    private T[] data;
    private int size;
    public ArrayDeque(){
        data=(T[])new Object[8];
        size=0;
    }

    private boolean isFull(){
        return size==data.length;
    }
    public void addFirst(T item){
        if(isFull()){
            T[] temp=(T[])new Object[size*2];
            for(int i=0;i<size;i++){
                temp[i]=data[i];
            }
            data=temp;
        }
        for(int i=size;i>0;i--){
            data[i]=data[i-1];
        }
        data[0]=item;
        size++;
    }

    public void addLast(T item){
        if(isFull()){
            T[] temp=(T[])new Object[size*2];
            for(int i=0;i<size;i++){
                temp[i]=data[i];
            }
            data=temp;
        }
        data[size]=item;
        size++;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.println(data[i]);
        }
    }

    public T removeFirst(){
        T temp=data[0];
        for(int i=1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        return temp;
    }

    public T removeLast(){
        size--;
        return data[size];
    }

    public T get(int index){
        return data[index-1];
    }
}
