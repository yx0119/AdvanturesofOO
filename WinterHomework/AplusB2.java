import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplusB2 {
    public static boolean isNotNum(String x) {
        String pattern = "^(\\+|-)?\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(x);
        return ! m.matches();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        if (scan.hasNext() || isNotNum(a) || isNotNum(b)) {
            System.out.println("WRONG FORMAT!");
        }
        else {
            BigDecimal a1 = new BigDecimal(a);
            BigDecimal b1 = new BigDecimal(b);
            System.out.println(a1.add(b1).toString());
        }
        scan.close();
    }
}
