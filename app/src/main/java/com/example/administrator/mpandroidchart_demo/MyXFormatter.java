package com.example.administrator.mpandroidchart_demo;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * 作者：luoshen on 2018/3/19 0019 15:03
 * 邮箱：rsf411613593@gmail.com
 * 说明：
 */

public class MyXFormatter implements IAxisValueFormatter {
    private String[] xVals_arr;

    public MyXFormatter(String[] xVals_arr) {
        this.xVals_arr = xVals_arr;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value < 0 || value > (xVals_arr.length - 1)){//使得两侧柱子完全显示
            return "";
        }
        return xVals_arr[((int)value)];


    }

}
