import java.util.Scanner;

public class Permutations {
    public static void permutations(String prefix, String suffix){
        if (suffix.length()==0){
            System.out.println(prefix);
            return;
        }
        else {
            for (int i = 0; i < suffix.length(); i++) {
                char character = suffix.charAt(i);
                String x = prefix + character;
                String y = suffix.substring(0, i) + suffix.substring(i + 1);
                permutations(x, y);
            }
        }
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println(("enter string: "));
        String x = "";
        String y = scanner.nextLine();
        permutations(x,y);
    }

}
