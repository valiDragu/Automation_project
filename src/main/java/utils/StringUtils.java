package utils;

public class StringUtils {
    public static double cleanAndParsePrice(String rawPrice) {
        return Double.parseDouble(rawPrice.replaceAll("[^0-9.]", ""));
    }
}
