public class TaskProcessor {

    public static StringBuffer deleteSymbolsFromLine(char symbol, String line) {
        StringBuffer sb = new StringBuffer(line);
        for (int i = sb.length() - 1; i >= 0; --i) {
            if (sb.charAt(i) == symbol) {
                sb.deleteCharAt(i);
            }
        }
        return sb;
    }

    public static StringBuffer insertAfterKSymbol(char symbol, String line, int k) {
        StringBuffer sb = new StringBuffer(line);
        if (k >= 0 && k <= sb.length()) {
            sb.insert(k, symbol);
        }
        return sb;
    }
}
