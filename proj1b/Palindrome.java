public class Palindrome{
    //the take2

    private OffByOne obo=new OffByOne();
    public Deque<Character> wordToDeque(String word){
        Deque<Character> temp=new ArrayDeque<>();
        for(int i=0;i<word.length();i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }
    public boolean isPalindrome(String word){
        if(word.length()==1){
            return true;
        }
        for(int i=0;i<word.length();i++){
            char a=word.charAt(i);
            char b=word.charAt(word.length()-i-1);
            if(a==b){
                continue;
            }
            return false;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
