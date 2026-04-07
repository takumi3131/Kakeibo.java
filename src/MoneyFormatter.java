public class MoneyFormatter {
    // 数字を受け取って、見やすい文字列（String）を返すメソッド
    public static String format(long amount) {
        long absAmount = Math.abs(amount); // マイナスを外す
        long oku = absAmount / 100000000L;
        long man = (absAmount % 100000000L) / 10000L;
        long en = absAmount % 10000L;

        String result = "";
        if (amount < 0) result += "-"; // 元がマイナスなら先頭につける
        if (oku > 0) result += oku + "億";
        if (man > 0) result += man + "万";
        if (en > 0 || (oku == 0 && man == 0)) result += en;

        return result; // 組み立てた文字列を返す
    }
}
