public class ArrayDeque <T> implements Deque<T>{

    private T[] data;
    private int size;
    public ArrayDeque(){
        data=(T[])new Object[8];
        size=0;
    }

    private boolean isFull(){
        return size==data.length;
    }
    @Override
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
    @Override
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
    @Override
    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.println(data[i]);
        }
    }
    @Override
    public T removeFirst(){
        T temp=data[0];
        for(int i=1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        return temp;
    }
    @Override
    public T removeLast(){
        size--;
        return data[size];
    }
    @Override
    public T get(int index){
        return data[index-1];
    }
}
