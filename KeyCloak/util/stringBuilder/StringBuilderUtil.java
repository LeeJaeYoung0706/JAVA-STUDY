package com.keti.iam.idthub.util.stringBuilder;

public class StringBuilderUtil {
    public static String build(String ...str){
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : str) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
