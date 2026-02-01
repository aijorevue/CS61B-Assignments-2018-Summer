import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void test_addFirst_addLast(){
        StudentArrayDeque<Integer> student=new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> actual=new ArrayDequeSolution<>();

        StringBuilder record=new StringBuilder();

        for(int i=0;i<10000;i++){
            int rand=StdRandom.uniform(4);
            switch(rand){
                case 0: {
                    int value=StdRandom.uniform(10000);
                    student.addFirst(value);
                    actual.addFirst(value);
                    record.append("addFirst(" + value + ")\n");
                    break;
                }
                case 1: {
                    int value=StdRandom.uniform(10000);
                    student.addLast(value);
                    actual.addLast(value);
                    record.append("addLast(" + value + ")\n");
                    break;
                }
                case 2: {
                    if (!student.isEmpty()) {
                        record.append("removeFirst()\n");
                        assertEquals(record.toString(),
                                actual.removeFirst(),
                                student.removeFirst());
                    }
                    break;
                }
                case 3: {
                    if (!student.isEmpty()) {
                        record.append("removeLast()\n");
                        assertEquals(record.toString(),
                                actual.removeLast(),
                                student.removeLast());
                    }
                    break;
                }
                default:
                    break;
            }
         //
         //   if(rand<0.5){
         //       student.addFirst(i);
         //       actual.addFirst(i);
         //       assertEquals("addFirst("+i+")\n"+"addFirst("+"i"+")\n"
         //               ,student.removeFirst()
         //               ,actual.removeFirst());
         //   }
         //   else{
         //       student.addLast(i);
         //       actual.addLast(i);

         //       assertEquals("addLast("+i+")\n"+"addLast("+"i"+")\n"
         //               ,student.removeLast()
         //               ,actual.removeLast());
         //   }

        }
    }
}
