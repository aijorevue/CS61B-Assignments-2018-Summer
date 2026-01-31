public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque<Character> temp=new ArrayDeque<>();
        for(int i=0;i<word.length();i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }
}
