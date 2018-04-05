package com.brhm.githubrepos;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    public static String getShortNumber(int n) {
        String s = String.valueOf(n);

        if(n > 1000)
            s = String.format("%.1fK",n/1000f);

        return s;
    }

    public static String getLastMonthSearchFilter() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH,-1);

        return "created:>"+format1.format(c.getTime());
    }
}
