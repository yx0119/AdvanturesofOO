import java.math.BigInteger;
import java.util.Scanner;

public class AplusB3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int aa = scan.nextInt();
        String a = scan.next();
        int bb = scan.nextInt();
        String b = scan.next();
        int c = scan.nextInt();
        BigInteger a1 = new BigInteger(a,aa);
        BigInteger b1 = new BigInteger(b,bb);
        String ans = a1.add(b1).toString(c);
        System.out.println(ans);
        scan.close();
    }
}
