import java.math.BigInteger;
import java.util.Scanner;

class matrix {
    private String[][] mat;
    public matrix(){
        mat = null;
    }


    public matrix(int order) {
        mat = new String[order][order];
    }

    public matrix(String str) {

        if (illegalInput(str)) {
            System.out.println("Illegal Input!");
            System.exit(0);
        }

        int order;
        String[] strs = str.split("[{},]");
        int temp1 = 0;
        while(strs[temp1].equals("")){
            temp1 += 1;
        }
        int i;
        for (i = temp1; i < strs.length; i++) {
            if (!strs[i].equals(""))
                continue;
            else
                break;
        }
        order = i - temp1;
        if (order == 0) {
            System.out.println("Empty Matrix!");
            System.exit(0);
        }
        String[][] m = new String[order][order];
        int j;
        for (i = 0; i < strs.length; i += temp1 + order) {
            for (j = 0; j < order; j++) {
                m[i / (temp1 + order)][j] = strs[i + temp1 + j];
            }
        }
        mat = m;
    }

    protected int getOrder() {
        return mat.length;
    }

    protected boolean illegalInput(String a) {
        if (a.matches("\\{(\\{\\d+(,\\d+)*\\})(,\\{\\d+(,\\d+)*\\})*\\}") || a.matches("\\{\\d+\\}")) {
            String[] strs = a.split("\\},");
            int i;
            for (i = 0; i < strs.length; i ++) {
                if (strs.length != strs[i].split(",").length) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    protected matrix add(matrix addThis) {
        int i, j, order;
        order = getOrder();
        if (order != addThis.getOrder()) {
            System.out.println("Illegal Operation!");
            System.exit(0);
        }
        matrix temp = new matrix(order);
        for (i = 0; i < order; i++) {
                for (j = 0; j < order; j++) {
                    BigInteger a = new BigInteger(mat[i][j]);
                    BigInteger b = new BigInteger(addThis.mat[i][j]);
                    temp.mat[i][j] = a.add(b).toString();
                }
        }
        return temp;
    }

    protected matrix sub(matrix subThis) {
        int i, j, order;
        order = getOrder();

        if (order != subThis.getOrder()) {
            System.out.println("Illegal Operation!");
            System.exit(0);
        }
        matrix temp = new matrix(order);
        for (i = 0; i < order; i++) {
            for (j = 0; j < order; j++) {
                BigInteger a = new BigInteger(mat[i][j]);
                BigInteger b = new BigInteger(subThis.mat[i][j]);
                temp.mat[i][j] = a.subtract(b).toString();
            }
        }
        return temp;
    }

    protected matrix transpose() {
        int order;
        order = getOrder();

        matrix temp = new matrix(order);
        int i, j;
        for (i = 0; i < order; i++) {
            for (j = 0; j < order; j++) {
                temp.mat[i][j] = mat[j][i];
            }
        }
        return temp;
    }

    protected matrix multiply(matrix multiplyThis) {
        int i, j, k, order;
        order = getOrder();
        if (order != multiplyThis.getOrder()) {
            System.out.println("Illegal Operation!");
            System.exit(0);
        }
        matrix temp = new matrix(order);
        for (i = 0; i < order; i++) {
            for (j = 0; j < order; j++) {
                BigInteger element = new BigInteger("0");
                for (k = 0; k < order; k++) {
                    BigInteger a = new BigInteger(mat[i][k]);
                    BigInteger b = new BigInteger(multiplyThis.mat[k][j]);
                    element = element.add(a.multiply(b));
                }
                temp.mat[i][j] = element.toString();
            }
        }
        return temp;
    }

    public String toString() {
        String s = new String();
        int i, j, order;
        order = getOrder();
        for (i = 0; i < order; i++) {
            for (j = 0; j < order; j++) {
                s += mat[i][j];
                s += '\t';
            }
            s = s + '\n';
        }
        return (s);
    }
}

public class matrixCal {
    int[][] matrix1, matrix2, answer;
    int dim;
    char operator;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        matrix m1 = new matrix(keyboard.nextLine());
        String op;
        op = keyboard.nextLine();

        if (op.equals("t")) {
            System.out.print(m1.transpose().toString());
        } else if (op.equals("+")) {
            matrix m2 = new matrix(keyboard.nextLine());
            System.out.print(m1.add(m2).toString());
        } else if (op.equals("-")) {
            matrix m2 = new matrix(keyboard.nextLine());
            System.out.print(m1.sub(m2).toString());
        } else if (op.equals("*")) {
            matrix m2 = new matrix(keyboard.nextLine());
            System.out.print(m1.multiply(m2).toString());
        } else {
            System.out.println("Illegal Input!");
        }
        keyboard.close();
    }
}