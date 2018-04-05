package com.brhm.githubrepos;

public class Utils {
    public static String getShortNumber(int n) {
        String s = String.valueOf(n);

        if(n > 1000)
            s = String.format("%.1fK",n/1000f);

        return s;
    }
}
