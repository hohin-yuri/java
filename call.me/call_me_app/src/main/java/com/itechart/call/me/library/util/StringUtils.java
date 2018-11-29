package com.itechart.call.me.library.util;

public final class StringUtils {
    public static String replaceChars(String text, String from, String to) {
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < from.length(); j++) {
                if (from.charAt(j) == text.charAt(i)) {
                    text = changeCharInPosition(i, to.charAt(j), text);
                }
            }
        }
        return text;
    }
        public static String changeCharInPosition(int position, char ch, String str){
            char[] charArray = str.toCharArray();
            charArray[position] = ch;
            return new String(charArray);
        }
    }

