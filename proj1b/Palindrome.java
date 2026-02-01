public class Palindrome{
    //the take2

    OffByOne obo=new OffByOne();
    public Deque<Character> wordToDeque(String word){
        Deque<Character> temp=new ArrayDeque<>();
        for(int i=0;i<word.length();i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }
    public boolean isPalindrome(String word){
        for(int i=0;i<word.length();i++){
            char a=word.charAt(i);
            char b=word.charAt(word.length()-i-1);
            if(obo.equalChars(a,b)){
                continue;
            }
            return false;
        }
        return true;
    }
}
