package cn.zybwz.quickuimodule.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    public static int numberCount(String str){
        int count=0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)>='0'&&str.charAt(i)<='9'){
                count++;
            } else if (str.charAt(i)>='a'&&str.charAt(i)<='z'){
                count++;
            }else if (str.charAt(i)==' '||str.charAt(i)=='.'){
                count++;
            }
        }
        return count;
    }

    /**
     * 将paramValue中的汉字提取出来
     * @param paramValue
     * @return
     */
    public static String getChinese(String paramValue) {
        String str = "";
        String regex = "([\u4e00-\u9fa5]+)";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }
    public static String getSongName(String str){
        String songName="";
        if (str.contains(".mp3")){
            songName=str.replace(".map3","");
        }else if (str.contains(".m4a")){
            songName=str.replace(".m4a","");
        }
        return songName;
    }
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
