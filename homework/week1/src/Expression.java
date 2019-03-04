import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Expression {
    private HashMap<String,Item> map;

    public interface Constant {
        String BLANK = "(\\s*)";
        String N = "([+-]?\\d+)";
        String M = "(x" + BLANK + "(\\^" + BLANK + N + ")?)";
        String ITEM = "(" + N + "|(((" + N + BLANK + "\\*)|[+-])?"
                + BLANK + M + "))" + BLANK;
    }

    public Expression(HashMap<String,Item> imap) {
        map = imap;
    }

    public Expression(String str) {
        checkFormat(str);
        // the following is based on str is legal
        String s0 = str.replaceAll(Constant.BLANK,"");
        String s1 = s0.replaceAll("(\\+\\+)|(--)","+");
        String s = s1.replaceAll("(\\+-)|(-\\+)","-");
        //matches the borders of each item
        Pattern r = Pattern.compile("(x[+-])|(\\d[+-])");
        Matcher m = r.matcher(s);

        HashMap<String,Item> imap = new HashMap<>();
        int st = 0;
        while (m.find()) {
            Item item = new Item(s.substring(st,m.start() + 1));
            /*check if the item is included
              true merge them
              false push item
            */
            if (imap.containsKey(item.getIndex())) {
                Item addThis = imap.get(item.getIndex());
                imap.put(item.getIndex(),item.add(addThis));
            }
            else {
                imap.put(item.getIndex(),item);
            }
            //refresh next st
            st = m.start() + 1;
        }
        //solve the m.find() == null and the last item
        Item item = new Item(s.substring(st));
        if (imap.containsKey(item.getIndex())) {
            Item addThis = imap.get(item.getIndex());
            imap.put(item.getIndex(),item.add(addThis));
        }
        else {
            imap.put(item.getIndex(),item);
        }
        map = imap;
    }

    protected void checkFormat(String str) {

        String pattern = "([x\\d]" + Constant.BLANK + "[+-])";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        int st = 0;
        while (m.find()) {
            String regx = "(\\s*)[+-](\\s*)" + Constant.ITEM;
            if (st == 0) {
                regx = "(\\s*)[+-]?(\\s*)" + Constant.ITEM;
            }
            if (!str.substring(st,m.start() + 1).matches(regx)) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
            st = m.start() + 1;
        }
        String regx = "(\\s*)[+-](\\s*)" + Constant.ITEM;
        if (st == 0) {
            regx = "(\\s*)[+-]?(\\s*)" + Constant.ITEM;
        }
        if (!str.substring(st).matches(regx)) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        /*
        if (!str.matches(Constant.PATTERN)) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }*/
    }

    public Expression derivative() {
        HashMap<String,Item> imap = new HashMap<>();
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Item item = map.get(key);
            Item nextItem = item.derivative();
            imap.put(nextItem.getIndex(),nextItem);
        }
        Expression temp = new Expression(imap);
        return temp;
    }

    public String toString() {
        String temp = "";
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Item item = map.get(key);
            temp = temp + item.toString();
        }
        if (temp.equals("")) {
            return "0";
        }
        return temp;
    }
}