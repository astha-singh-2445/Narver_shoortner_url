package com.singh.astha.narvar.utils;

public final class StaticMethods {
    private StaticMethods() {
    }

    public static String shortenUrl(long number) {
        StringBuilder stringBuilder = new StringBuilder();
        while (number > 0) {
            int index = (int) number % 62;
            number /= 62;
            stringBuilder.append(Constants.BASE_62.charAt(index));
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
