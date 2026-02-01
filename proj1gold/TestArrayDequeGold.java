import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void test_addFirst_addLast(){
        StudentArrayDeque<Integer> student=new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> actual=new ArrayDequeSolution<>();
        for(int i=0;i<100;i++){
            double rand=StdRandom.uniform();
            if(rand<0.5){
                student.addFirst(i);
                actual.addFirst(i);
                assertEquals("student:addFirst("+i+")\n"+"actual:addFirst("+"i"+")\n"
                        ,student.removeFirst()
                        ,actual.removeFirst());
            }
            else{
                student.addLast(i);
                actual.addLast(i);

                assertEquals("student:addLast("+i+")\n"+"actual:addLast("+"i"+")\n"
                        ,student.removeLast()
                        ,actual.removeLast());
            }

        }
    }
}
