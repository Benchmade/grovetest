package org.grove.common.stream;

public class NumberUtil {
    public static double getDouble(String string) {
        if ((string == null) || (string.length() == 0)) {
            return 0.0;
        }

        try {
            return Double.parseDouble(string);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static long getLong(String string) {
        if ((string == null) || (string.length() == 0)) {
            return 0L;
        }

        try {
            return Long.parseLong(string);
        } catch (Exception e) {
            return 0L;
        }
    }

    public static int getInt(String string) {
        if ((string == null) || (string.length() == 0)) {
            return 0;
        }

        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }
    }
}
