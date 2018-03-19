package com.example.administrator.mpandroidchart_demo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CandleStickChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = findViewById(R.id.candleStickChart);
        mChart.getDescription().setEnabled(false);

        //设置图表绘制可见标签数量最大值. 仅在setDrawValues() 启用时生效
        mChart.setMaxVisibleValueCount(60);

        // 设置 X Y 轴 同时缩放
//        mChart.setPinchZoom(false);
        //设置  X 轴缩放
        mChart.setScaleXEnabled(true);
        //设置 Y 轴缩放
        mChart.setScaleYEnabled(false);


        //设置绘制 网格线 时的背景色是否显示
        mChart.setDrawGridBackground(false);





        //获取 X 轴
        XAxis xAxis = mChart.getXAxis();
        //设置 X 轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否显示与 X 轴垂直的线（网格线）
        xAxis.setDrawGridLines(false);
        //设置是否绘制 X 轴线
        xAxis.setDrawAxisLine(true);
        //)设置自定义格式，在绘制之前动态调整x的值。
        xAxis.setValueFormatter(new MyXFormatter(xVals_arr));



        //获取左侧 Y 轴
        YAxis leftAxis = mChart.getAxisLeft();
        //设置 Y 轴 标注个数 ，第二个参数表示是否强制，一般 false ，有可能出现不均匀的情况
        leftAxis.setLabelCount(10, false);
        //设置是否显示与 Y 轴垂直的线（网格线）
        leftAxis.setDrawGridLines(false);
        //设置是否显示 Y 轴的 轴线
        leftAxis.setDrawAxisLine(true);

        //获取 右侧 Y 轴
        YAxis rightAxis = mChart.getAxisRight();
        //设置 右侧 Y 轴是否显示
        rightAxis.setEnabled(false);

        getXValus();

        ArrayList<CandleEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < xVals_arr.length; i++) {
            float mult = (20 + 1);
            float val = (float) (Math.random() * 40) + mult;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            yVals1.add(new CandleEntry(
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close)
            );
        }

        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
        //此处设置的是 CandleEntry 构造函数，当最后一个为参数为 Drawable时， 是否显示，代替文字
        set1.setDrawIcons(false);
        //以左边坐标轴为准 还是以右边坐标轴为基准
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //设置蜡烛图上下方线条的颜色
        set1.setShadowColor(Color.DKGRAY);
        //设置蜡烛图上下方线条的宽度
        set1.setShadowWidth(0.7f);
        // open > close 时的 颜色
        set1.setDecreasingColor(Color.RED);
        //open > close 时的 画笔设置，描边还是填充等
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        // open <= close 时的 颜色
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        //open < close 时的 画笔设置，描边还是填充等
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        // open == close 时的 颜色
        set1.setNeutralColor(Color.BLACK);
        //设置点击时候显示高亮交叉线的宽度
        set1.setHighlightLineWidth(1f);
        //设置点击时候显示高亮交叉线的颜色
        set1.setHighLightColor(Color.rgb(244, 117, 117));



        CandleData data = new CandleData(set1);



        mChart.setData(data);
    }
    private void getXValus(){
        for (int i = 0; i < xVals_arr.length; i++) {
            xVals.add(xVals_arr[i]);
        }
    }


    private List<String> xVals = new ArrayList<>();
    private String[] xVals_arr = {"01-09",
            "01-10",
            "01-11",
            "01-12",
            "01-13",
            "01-14",
            "01-15",
            "01-16",
            "01-17",
            "01-18",
            "01-19",
            "01-20",
            "01-21",
            "01-22",
            "01-23",
            "01-24",
            "01-25",
            "01-26",
            "01-27",
            "01-28",
            "01-29",
            "01-30",
            "01-31"
            };
}
