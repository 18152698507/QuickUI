package cn.zybwz.quickuimodule.utils

import android.content.Context

class Utils {
    companion object{
        fun dip2px(context:Context,dpValue:Int):Int{
            val scale = context.resources.displayMetrics.density
            return ((dpValue * scale + 0.5f).toInt())
        }
    }
}