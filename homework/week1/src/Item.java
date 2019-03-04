import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Item {
    private String coeffi;
    private String index;

    public String getCoeffi() {
        return coeffi;
    }

    public String getIndex() {
        return index;
    }

    public Item(String str) {
        String pattern = "([+-]?\\d+)|(x)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        String op = str.replaceAll(pattern + "|(\\*)|(\\^)","");
        BigInteger coef = new BigInteger("1");
        BigInteger indx = new BigInteger("0");

        if (op.equals("-")) {
            coef = new BigInteger("-1");
        }

        while (m.find()) {
            String s = m.group();
            if (s.matches("x")) {
                if (!m.find()) {
                    s = "1";
                }
                else {
                    s = m.group();
                }
                indx = indx.add(new BigInteger(s));
                break;
            }
            coef = coef.multiply(new BigInteger(s));
        }
        coeffi = coef.toString();
        index = indx.toString();
    }

    public boolean lessThanZero() {
        return coeffi.charAt(0) == '-';
    }

    protected Item add(Item addThis) {
        Item temp = new Item("");
        BigInteger coef = new BigInteger(coeffi);
        if (!index.equals(addThis.index)) {
            System.out.println("two items' indexs are not equal");
            System.exit(0);
        }
        temp.coeffi = coef.add(new BigInteger(addThis.coeffi)).toString();
        temp.index = index;
        return temp;
    }

    protected Item derivative() {
        Item temp = new Item("");
        BigInteger coef = new BigInteger(coeffi);
        BigInteger indx = new BigInteger(index);
        temp.index = indx.add(new BigInteger("-1")).toString();
        temp.coeffi = coef.multiply(indx).toString();
        return temp;
    }

    public String toString() {
        String str = "";
        if (coeffi.equals("0")) {
            return "";
        }

        if (index.equals("0")) {
            if (lessThanZero()) {
                str += coeffi;
            }
            else {
                str = str + '+' + coeffi;
            }
        }
        else {
            if (lessThanZero()) {
                if (coeffi.equals("-1")) {

                    str = str + "-x";
                }
                else {
                    str = str + coeffi + "*x";
                }
            }
            else {
                if (coeffi.equals("1")) {
                    str = str + "+x";
                }
                else {
                    str = str + "+" + coeffi + "*x";
                }
            }
            if (!index.equals("1")) {
                str = str + "^" + index;
            }
        }
        return str;
    }
}
