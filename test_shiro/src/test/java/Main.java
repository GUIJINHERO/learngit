import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            String[] arr = str.split(",");
            Comparator<String> comparator = (s1,s2) ->{
                int i ;
                if (s1.charAt(s1.length()-1) > s2.charAt(s2.length()-1)){
                    i = 1;
                }else if(s1.charAt(s1.length()-1) < s2.charAt(s2.length()-1)){
                    i = -1;
                }else{
                    i = 0;
                }
                return i;
            };
            Arrays.sort(arr,comparator.reversed());
            for(int i = 0;i<arr.length;i++){
                if(i != arr.length-1){
                    System.out.print(arr[i]+",");
                }else{
                    System.out.print(arr[i]);
                }
            }
    }
}
