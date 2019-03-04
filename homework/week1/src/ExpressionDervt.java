import java.util.Scanner;

public class ExpressionDervt {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Expression expression = new Expression(scan.nextLine());
        String ans = expression.derivative().toString();
        if (ans.charAt(0) == '+') {
            System.out.println(ans.substring(1));
        }
        else {
            System.out.println(ans);
        }
        scan.close();
    }
}
