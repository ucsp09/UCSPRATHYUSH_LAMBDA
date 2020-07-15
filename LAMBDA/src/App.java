package LAMBDA.src;

import java.util.List;
import java.util.Arrays;
import java.util.function.Predicate;

@FunctionalInterface
interface FuncInterface{
    void getPalindromes(List<String> list);
    default Double average(List<Integer> list)
    {   
        Double average=list.stream().mapToInt(val->val).average().orElse(0.0);
        return average;
    }
    default void startWithA(List<String> list)
    {
        Predicate<String> startsWithA=(n)->n.startsWith("a");
        Predicate<String> threeLetterLong=(n)->n.length()==3;
        list.stream()
            .filter(startsWithA.and(threeLetterLong))
            .forEach((n) -> System.out.println("Strings, which starts with 'A' and three letter long is : " + n));
    }
}

 class MethodReference {
    public static boolean isPalindrome(String str)
    {
        int len=str.length();
        if(len==0)
            return false;
        for(int i=0;i<len/2;i++)
        {
            if(str.charAt(i)!=str.charAt(len-1-i))
            {
                return false;
            }
        }
        return true;
    }
    public  static void findPalindromes(List<String> list)
    {
        Predicate<String> isPalindrome=(n)->isPalindrome(n);
        list.stream()
            .filter(isPalindrome)
            .forEach((n)-> System.out.println("Strings which are palindromes are:"+n));       
    }
}
public class App{
    public static void main(String[] args)
    {
        FuncInterface obj=MethodReference::findPalindromes;
        List<Integer> intList=Arrays.asList(1,2,3,4,5,6,7,8,9);
        System.out.println(obj.average(intList));
        List<String> strList=Arrays.asList("aba","Abstract","dad","tenet","Building","Classes","apt");
        obj.startWithA(strList);
        obj.getPalindromes(strList);
    }
}