package cn.zybwz.components;

import android.app.Application;
import android.util.Log;

public class Utils {
    public static Application application;

    public static String sec2String(int secSrc){
        String str="";
        int min=0;
        int sec=0;
        if (secSrc/60>0){
            min=secSrc/60;
            if (min>9)
                str+=min;
            else
                str="0"+min;
        }else {
            str+="00";
        }
        str+=":";

        sec=secSrc%60;
        if (sec>9)
            str+=sec;
        else
            str+="0"+sec;
        return str;
    }
}
