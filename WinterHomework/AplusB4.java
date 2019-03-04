import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplusB4 {
    public static String getExpressionResult(String expression) {
        BigInteger ans = new BigInteger("0");

        Pattern pattern = Pattern.compile("(\\+|-){1,2}\\d+");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String opnum = matcher.group();
            BigInteger num = new BigInteger(opnum.substring(1));

            if (opnum.charAt(0) == '+') {
                ans = ans.add(num);
            } else {
                ans = ans.subtract(num);
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String expression = "+";
        while (scan.hasNext()) {
            String str = scan.next();
            expression = expression + str;
        }
        System.out.println(getExpressionResult(expression));
        scan.close();
    }
}
